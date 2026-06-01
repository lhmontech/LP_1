package org.example.service;

import org.example.DAO.FrutaDAO;
import org.example.model.Fruta;

public class FrutaService {
    private final FrutaDAO frutaDAO = new FrutaDAO();

    public void adicionarFruta(String tipo, int quantidade) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        int qtdTotal = fruta.getQuantidade() + quantidade;
        frutaDAO.alterarQtdFruta(fruta.getId(), qtdTotal);
    }


    public void descartarFruta(String tipo, int quantidade) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        int qtdTotal = fruta.getQuantidade() - quantidade;
        frutaDAO.alterarQtdFruta(fruta.getId(), qtdTotal);
    }

    public void aplicarPromocao(String tipo, int desconto) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        double valorDesconto = (fruta.getValor() * desconto) / 100;
        double precoNovo = fruta.getValor() - valorDesconto;
        frutaDAO.alterarPrecoFruta(fruta.getId(), precoNovo);
    }

    public void atualizarPreco(String tipo, double novoValor) {
        Fruta fruta = frutaDAO.buscarPorTipo(tipo);
        frutaDAO.alterarPrecoFruta(fruta.getId(), novoValor);
    }
}
