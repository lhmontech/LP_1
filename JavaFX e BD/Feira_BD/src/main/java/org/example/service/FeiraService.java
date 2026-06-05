package org.example.service;

import org.example.DAO.BarracaDAO;
import org.example.DAO.ClienteDAO;
import org.example.DAO.FrutaDAO;
import org.example.model.Cliente;
import org.example.model.Fruta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeiraService {
    private final FrutaDAO frutaDAO = new FrutaDAO();
    private final BarracaDAO barracaDAO = new BarracaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

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
            return;
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
    public boolean exporProduto(String tipo, int quantidade) {
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta fruta = barracaDAO.verificarFruta(frutaId);
        boolean descartado = descartarFruta(tipo, quantidade);
        if (!descartado) return false;
        if (fruta == null) {
            barracaDAO.exporNovaFruta(frutaId, quantidade);
        } else {
            int qtdTotal = fruta.getQuantidade() + quantidade;
            barracaDAO.alterarQtdFruta(frutaId, qtdTotal);
        }
        return true;
    }

    public boolean recolherProduto(String tipo, int quantidade) {
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta fruta = barracaDAO.verificarFruta(frutaId);
        if (fruta == null || quantidade > fruta.getQuantidade()) {
            System.out.println("Não há " + tipo + "s o suficiente na barraca!");
            return false;
        }
        int qtdTotal = fruta.getQuantidade() - quantidade;
        barracaDAO.alterarQtdFruta(frutaId, qtdTotal);
        adicionarFruta(tipo, quantidade);
        return true;
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

    //------ Métodos relacionados ao cliente ------
    private final Cliente cliente = clienteDAO.buscarCliente();

    public boolean comprarProduto(String tipo, int quantidade){
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta fruta = barracaDAO.verificarFruta(frutaId);
        if (fruta == null || quantidade > fruta.getQuantidade()) {
            System.out.println("Não há " + tipo + "s o suficiente na barraca!");
            return false;
        }
        int qtdTotal = fruta.getQuantidade() - quantidade;
        double saldoCliente = cliente.getSaldo();
        double valorCompra = fruta.getValor() * quantidade;
        if (saldoCliente < valorCompra) {
            System.out.println("Saldo insuficiente!");
            return false;
        }
        saldoCliente = saldoCliente - valorCompra;
        clienteDAO.alterarSaldo(cliente.getId(), saldoCliente);
        cliente.setSaldo(saldoCliente);
        barracaDAO.alterarQtdFruta(frutaId, qtdTotal);
        Fruta frutaSacola = clienteDAO.buscarFruta(frutaId);
        if (frutaSacola != null){
            int qtdAdicionar = frutaSacola.getQuantidade() + quantidade;
            clienteDAO.alterarQtdFruta(frutaId, qtdAdicionar);
            clienteDAO.alterarValorPago(frutaId, fruta.getValor());
        } else {
            clienteDAO.comprarNovaFruta(cliente.getId(), frutaId, quantidade, fruta.getValor());
        }
        return true;
    }

    public boolean consumirProduto(String tipo, int quantidade){
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta frutaSacola = clienteDAO.buscarFruta(frutaId);
        if (frutaSacola == null || quantidade > frutaSacola.getQuantidade()){
            System.out.println("Não há " + tipo + "s o suficiente na sacola!");
            return false;
        }
        int qtdComer = frutaSacola.getQuantidade() - quantidade;
        clienteDAO.alterarQtdFruta(frutaId, qtdComer);
        return true;
    }

    public boolean devolverProduto(String tipo, int quantidade){
        Long frutaId = frutaDAO.buscarPorTipo(tipo).getId();
        Fruta frutaSacola = clienteDAO.buscarFruta(frutaId);
        if (frutaSacola == null || quantidade > frutaSacola.getQuantidade()){
            System.out.println("Não há " + tipo + "s o suficiente na sacola!");
            return false;
        }
        int qtdDevolver = frutaSacola.getQuantidade() - quantidade;
        clienteDAO.alterarQtdFruta(frutaId, qtdDevolver);
        int qtdTotal = frutaSacola.getQuantidade() + quantidade;
        barracaDAO.alterarQtdFruta(frutaId, qtdTotal);
        double saldoCliente = cliente.getSaldo();
        saldoCliente = saldoCliente + (frutaSacola.getValor() * quantidade);
        clienteDAO.alterarSaldo(cliente.getId(), saldoCliente);
        cliente.setSaldo(saldoCliente);
        return true;
    }

    public Map<String, Integer> qtdFrutasSacola(){
        Map<String, Integer> mapa = new HashMap<>();
        for (Fruta f : clienteDAO.frutasSacola()) {
            mapa.put(f.getTipo(), f.getQuantidade());
        }
        return mapa;
    }

    public double saldoCliente() {
        double saldoAtual = cliente.getSaldo();
        return saldoAtual;
    }

}
