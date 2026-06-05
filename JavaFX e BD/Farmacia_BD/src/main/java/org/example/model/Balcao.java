package org.example.model;

public class Balcao {
    private Long id;
    private Long remedioId;
    private boolean destaque;
    private int quantidadeProduto;

    public Balcao(Long id, Long remedioId, boolean destaque, int quantidadeProduto) {
        this.id = id;
        this.remedioId = remedioId;
        this.destaque = destaque;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRemedioId() {
        return remedioId;
    }

    public void setRemedioId(Long remedioId) {
        this.remedioId = remedioId;
    }

    public boolean isDestaque() {
        return destaque;
    }

    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}