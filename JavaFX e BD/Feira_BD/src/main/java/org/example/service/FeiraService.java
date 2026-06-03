package org.example.service;

import org.example.DAO.BarracaDAO;
import org.example.DAO.FrutaDAO;
import org.example.model.Fruta;

import java.util.ArrayList;
import java.util.List;

public class FeiraService {
    private final FrutaDAO frutaDAO = new FrutaDAO();
    private final BarracaDAO barracaDAO = new BarracaDAO();

    //------ Métodos relacionados à fruta ------
    public void adicionarFruta(String tipo, int quantidade) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
            return;
        }
        int qtdTotal = fruta.getQuantidade() + quantidade;
        frutaDAO.alterarQtdFruta(fruta.getId(), qtdTotal);
    }


    public boolean descartarFruta(String tipo, int quantidade) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
            return false;
        }
        if (quantidade > fruta.getQuantidade()) {
            System.out.println("Não há " + tipo + "s o suficiente no estoque!");
            return false;
        } else {
            int qtdTotal = fruta.getQuantidade() - quantidade;
            frutaDAO.alterarQtdFruta(fruta.getId(), qtdTotal);
            return true;
        }
    }

    public void aplicarPromocao(String tipo, int desconto) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
        }
        if (desconto > 100) {
            System.out.println("Não podemos dar descontos maiores que 100%!");
        } else {
            double valorDesconto = (fruta.getValor() * desconto) / 100;
            double precoNovo = fruta.getValor() - valorDesconto;
            frutaDAO.alterarPrecoFruta(fruta.getId(), precoNovo);
        }

    }

    public void atualizarPreco(String tipo, double novoValor) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
        }
        if (novoValor < 0) {
            System.out.println("O preço deve ser maior que R$ 0,00!");
        } else {
            frutaDAO.alterarPrecoFruta(fruta.getId(), novoValor);
        }
    }

    public Fruta buscarPorTipo(String tipo) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        return fruta;
    }

    //------ Métodos relacionados à barraca ------
    public void exporProduto(String tipo, int quantidade) {
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta fruta = barracaDAO.verificarFruta(frutaId);
        boolean descartado = descartarFruta(tipo, quantidade);
        if (!descartado) return;
        if (fruta == null) {
            barracaDAO.exporNovaFruta(frutaId, quantidade);
        } else {
            int qtdTotal = fruta.getQuantidade() + quantidade;
            barracaDAO.alterarQtdFruta(frutaId, qtdTotal);
        }
    }

    public void recolherProduto(String tipo, int quantidade) {
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta fruta = barracaDAO.verificarFruta(frutaId);
        if (fruta == null || quantidade > fruta.getQuantidade()) {
            System.out.println("Não há " + tipo + "s o suficiente na barraca!");
            return;
        }
        int qtdTotal = fruta.getQuantidade() - quantidade;
        barracaDAO.alterarQtdFruta(frutaId, qtdTotal);
        adicionarFruta(tipo, quantidade);
    }

    public List<String> montarListaBarraca() {
        List<String> lista = new ArrayList<>();
        for (Fruta f : barracaDAO.buscarFrutas()) {
            for (int i = 0; i < f.getQuantidade(); i++) {
                lista.add(f.getTipo());
            }
        }
        return lista;
    }

}
