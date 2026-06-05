package org.example.model;

import java.util.List;

public class Cliente {
    private Long id;
    private String nome;
    private double saldo;
    private List<Remedio> cesta;

    public Cliente(Long id, String nome, double saldo, List<Remedio> cesta) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.cesta = cesta;
    }

    public Cliente(Long id, double saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Remedio> getCesta() {
        return cesta;
    }

    public void setCesta(List<Remedio> cesta) {
        this.cesta = cesta;
    }
}