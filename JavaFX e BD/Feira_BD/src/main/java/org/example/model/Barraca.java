package org.example.model;

import java.util.List;

public class Barraca {
    private Long id;
    private Long frutaId;
    private boolean destaque;
    private int quantidadeProduto;

    public Barraca(Long id, Long frutaId, boolean destaque, int quantidadeProduto) {
        this.id = id;
        this.frutaId = frutaId;
        this.destaque = destaque;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrutaId() {
        return frutaId;
    }

    public void setFrutaId(Long frutaId) {
        this.frutaId = frutaId;
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
