package org.example;
public class Fruta {
    private String nome = "Maçã";
    private String cor = "Amarela";
    private Double preco = 2.99;


    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(String nome) {
        return nome;
    }

    public String getCor(String cor){
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco(Double preco){
        return this.preco = preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String vender(){
        return "A fruta " + nome + " foi vendida por " + preco;
    }

    public String estragar(){
        return "A " + nome + " estragou ";
    }

    public String comer(){
        return "Alguém comeu a fruta de cor " + cor;
    }
}
