package org.example;

public class Prateleira {
    private Double tamanho = 2.3;
    private String cor = "Branca";
    private String conteudo = "Antialérgicos";

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String armazenar() {
        return "A prateleira " + cor + " armazena " + conteudo;
    }

    public String expor() {
        return "A prateleira " + cor + " expõe " + conteudo;
    }

    public String organizar() {
        return "A prateleira com tamanho " + tamanho + " serve para organizar os produtos " + conteudo;
    }
}
