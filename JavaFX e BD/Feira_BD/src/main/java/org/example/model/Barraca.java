package org.example.model;

import java.util.List;

public class Barraca {
    private Long id;
    private String tipoProduto;
    private List<Fruta> mostruario;
    private int quantidadeProduto;

    public Barraca(Long id, String tipoProduto, List<Fruta> mostruario, int quantidadeProduto) {
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.mostruario = mostruario;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public List<Fruta> getMostruario() {
        return mostruario;
    }

    public void setMostruario(List<Fruta> mostruario) {
        this.mostruario = mostruario;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public void exporProduto() {

    }

    public void recolherProduto() {

    }

    public void organizarProdutos() {

    }
}
