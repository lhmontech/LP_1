package org.example.model;

import org.example.DAO.FrutaDAO;

import java.time.LocalDate;

public class Fruta {
    private Long id;
    private String tipo;
    private double valor;
    private int quantidade;

    public Fruta(Long id, String tipo, double valor, int quantidade) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Fruta(Long id, String tipo, int quantidade) {
        this.id = id;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
