package org.example;

public class Pessoa {
    private String nome = "Cláudia";
    private String corRoupa = "Azul";
    private String funcao = "Feirante";


    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(String nome) {
        return nome;
    }

    public void setRoupa(String corRoupa){
        this.corRoupa = corRoupa;
    }

    public String getRoupa(String corRoupa) {
        return corRoupa;
    }

    public String getFuncao(String funcao){
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String vender(){
        return "A " + funcao + " vendeu suas frutas";
    }

    public String falar(){
        return nome + " falou com a feirante de roupa " + corRoupa;
    }

    public String comprar(){
        return nome + " comprou um abacaxi";
    }
}
