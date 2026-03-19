package org.example;

public class Prateleira {
    private Double tamanho;
    private String cor;
    private String conteudo;

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

    public void armazenar() {
        System.out.println("A prateleira " + cor + " armazena " + conteudo);
    }

    public void expor() {
        System.out.println("A prateleira " + cor + " expõe " + conteudo);
    }

    public void organizar() {
        System.out.println("A prateleira com tamanho " + tamanho + " serve para organizar os produtos " + conteudo);
    }
}
