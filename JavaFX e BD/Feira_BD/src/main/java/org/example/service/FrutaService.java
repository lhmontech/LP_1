package org.example.service;

import org.example.DAO.FrutaDAO;
import org.example.model.Fruta;

public class FrutaService {
    private final FrutaDAO frutaDAO = new FrutaDAO();

    public void adicionarFruta(String tipo, int quantidade) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
        }
        int qtdTotal = fruta.getQuantidade() + quantidade;
        frutaDAO.alterarQtdFruta(fruta.getId(), qtdTotal);
    }


    public void descartarFruta(String tipo, int quantidade) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
        }
        if (quantidade > fruta.getQuantidade()) {
            System.out.println("Não há " + tipo + "s o suficiente para descartar!");
        } else {
            int qtdTotal = fruta.getQuantidade() - quantidade;
            frutaDAO.alterarQtdFruta(fruta.getId(), qtdTotal);
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

}
