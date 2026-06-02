package org.example;

public class Remedio {
    private String nome = "Loratadina";
    private String tipo = "Antialérgico";
    private Double preco = 9.50;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String tomar() {
        return "Alguém tomou um " + nome;
    }

    public String comprar() {
        return "Alguém comprou o " + nome + " por R$ " + preco;
    }

    public String curar() {
        return "O " + nome + " serve para curar sintomas do tipo " + tipo;
    }

}
