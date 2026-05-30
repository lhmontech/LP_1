package org.example.model;

import java.time.LocalDate;

public class Fruta {
    private Long id;
    private String tipo;
    private double valor;
    private LocalDate validade;

    public Fruta(Long id, String tipo, double valor, LocalDate validade) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.validade = validade;
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

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void adicionarFruta() {

    }

    public void descartarFruta() {

    }

    public void aplicarPromocao() {

    }

}
