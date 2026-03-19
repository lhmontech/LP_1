package org.example;

public class Remedio {
    private String nome;
    private String tipo;
    private Double preco;

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

    public void tomar() {
        System.out.println("Tomou o " + nome);
    }

    public void comprar() {
        System.out.println("comprou o " + nome + " por " + preco);
    }

    public void curar() {
        System.out.println("O " + nome + " serve para curar sintomas do tipo " + tipo);
    }

}
