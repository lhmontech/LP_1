package org.example.service;

import org.example.DAO.BalcaoDAO;
import org.example.DAO.ClienteDAO;
import org.example.DAO.RemedioDAO;
import org.example.model.Cliente;
import org.example.model.Remedio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FarmaciaService {
    private final RemedioDAO remedioDAO = new RemedioDAO();
    private final BalcaoDAO balcaoDAO = new BalcaoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void adicionarRemedio(String nome, int quantidade) {
        Remedio remedio = remedioDAO.buscarPorNome(nome);
        if (remedio == null) {
            System.out.println("Remédio não encontrado!");
            return;
        }
        int qtdTotal = remedio.getQuantidade() + quantidade;
        remedioDAO.alterarQtdRemedio(remedio.getId(), qtdTotal);
    }

    public boolean descartarRemedio(String nome, int quantidade) {
        Remedio remedio = remedioDAO.buscarPorNome(nome);
        if (remedio == null) {
            System.out.println("Remédio não encontrado!");
            return false;
        }
        if (quantidade > remedio.getQuantidade()) {
            System.out.println("Não há " + nome + " o suficiente no estoque!");
            return false;
        } else {
            int qtdTotal = remedio.getQuantidade() - quantidade;
            remedioDAO.alterarQtdRemedio(remedio.getId(), qtdTotal);
            return true;
        }
    }

    public void aplicarPromocao(String nome, int desconto) {
        Remedio remedio = remedioDAO.buscarPorNome(nome);
        if (remedio == null) {
            System.out.println("Remédio não encontrado!");
            return;
        }
        if (desconto > 100) {
            System.out.println("Não podemos dar descontos maiores que 100%!");
        } else {
            double valorDesconto = (remedio.getPreco() * desconto) / 100;
            double precoNovo = remedio.getPreco() - valorDesconto;
            remedioDAO.alterarPrecoRemedio(remedio.getId(), precoNovo);
        }
    }

    public void atualizarPreco(String nome, double novoValor) {
        Remedio remedio = remedioDAO.buscarPorNome(nome);
        if (remedio == null) {
            System.out.println("Remédio não encontrado!");
            return;
        }
        if (novoValor < 0) {
            System.out.println("O preço deve ser maior que R$ 0,00!");
        } else {
            remedioDAO.alterarPrecoRemedio(remedio.getId(), novoValor);
        }
    }

    public Remedio buscarPorNome(String nome) {
        return remedioDAO.buscarPorNome(nome);
    }

    public boolean exporProduto(String nome, int quantidade) {
        Long remedioId = remedioDAO.buscarPorNome(nome).getId();
        Remedio remedio = balcaoDAO.verificarRemedio(remedioId);
        boolean descartado = descartarRemedio(nome, quantidade);
        if (!descartado) return false;
        if (remedio == null) {
            balcaoDAO.exporNovoRemedio(remedioId, quantidade);
        } else {
            int qtdTotal = remedio.getQuantidade() + quantidade;
            balcaoDAO.alterarQtdRemedio(remedioId, qtdTotal);
        }
        return true;
    }

    public boolean recolherProduto(String nome, int quantidade) {
        Long remedioId = remedioDAO.buscarPorNome(nome).getId();
        Remedio remedio = balcaoDAO.verificarRemedio(remedioId);
        if (remedio == null || quantidade > remedio.getQuantidade()) {
            System.out.println("Não há " + nome + " o suficiente no balcão!");
            return false;
        }
        int qtdTotal = remedio.getQuantidade() - quantidade;
        balcaoDAO.alterarQtdRemedio(remedioId, qtdTotal);
        adicionarRemedio(nome, quantidade);
        return true;
    }

    public List<String> montarListaBalcao() {
        List<String> lista = new ArrayList<>();
        for (Remedio r : balcaoDAO.buscarRemedios()) {
            for (int i = 0; i < r.getQuantidade(); i++) {
                lista.add(r.getNome());
            }
        }
        return lista;
    }

    private final Cliente cliente = clienteDAO.buscarCliente();

    public boolean comprarProduto(String nome, int quantidade) {
        Long remedioId = remedioDAO.buscarPorNome(nome).getId();
        Remedio remedio = balcaoDAO.verificarRemedio(remedioId);
        if (remedio == null || quantidade > remedio.getQuantidade()) {
            System.out.println("Não há " + nome + " o suficiente no balcão!");
            return false;
        }
        int qtdTotal = remedio.getQuantidade() - quantidade;
        double saldoCliente = cliente.getSaldo();
        double valorCompra = remedio.getPreco() * quantidade;
        if (saldoCliente < valorCompra) {
            System.out.println("Saldo insuficiente!");
            return false;
        }
        saldoCliente = saldoCliente - valorCompra;
        clienteDAO.alterarSaldo(cliente.getId(), saldoCliente);
        cliente.setSaldo(saldoCliente);
        balcaoDAO.alterarQtdRemedio(remedioId, qtdTotal);
        Remedio remedioCesta = clienteDAO.buscarRemedio(remedioId);
        if (remedioCesta != null) {
            int qtdAdicionar = remedioCesta.getQuantidade() + quantidade;
            clienteDAO.alterarQtdRemedio(remedioId, qtdAdicionar);
            clienteDAO.alterarValorPago(remedioId, remedio.getPreco());
        } else {
            clienteDAO.comprarNovoRemedio(cliente.getId(), remedioId, quantidade, remedio.getPreco());
        }
        return true;
    }

    public boolean consumirProduto(String nome, int quantidade) {
        Long remedioId = remedioDAO.buscarPorNome(nome).getId();
        Remedio remedioCesta = clienteDAO.buscarRemedio(remedioId);
        if (remedioCesta == null || quantidade > remedioCesta.getQuantidade()) {
            System.out.println("Não há " + nome + " o suficiente na cesta!");
            return false;
        }
        int qtdTomar = remedioCesta.getQuantidade() - quantidade;
        clienteDAO.alterarQtdRemedio(remedioId, qtdTomar);
        return true;
    }

    public boolean devolverProduto(String nome, int quantidade) {
        Long remedioId = remedioDAO.buscarPorNome(nome).getId();
        Remedio remedioCesta = clienteDAO.buscarRemedio(remedioId);
        if (remedioCesta == null || quantidade > remedioCesta.getQuantidade()) {
            System.out.println("Não há " + nome + " o suficiente na cesta!");
            return false;
        }
        int qtdDevolver = remedioCesta.getQuantidade() - quantidade;
        clienteDAO.alterarQtdRemedio(remedioId, qtdDevolver);
        int qtdTotal = remedioCesta.getQuantidade() + quantidade;
        balcaoDAO.alterarQtdRemedio(remedioId, qtdTotal);
        double saldoCliente = cliente.getSaldo();
        saldoCliente = saldoCliente + (remedioCesta.getPreco() * quantidade);
        clienteDAO.alterarSaldo(cliente.getId(), saldoCliente);
        cliente.setSaldo(saldoCliente);
        return true;
    }

    public Map<String, Integer> qtdRemediosCesta() {
        Map<String, Integer> mapa = new HashMap<>();
        for (Remedio r : clienteDAO.remediosCesta()) {
            mapa.put(r.getNome(), r.getQuantidade());
        }
        return mapa;
    }

    public double saldoCliente() {
        return cliente.getSaldo();
    }
}