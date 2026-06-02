package org.example;
public class Pessoa {
    private String nome = "Jordana";
    private String corCabelo = "Ruivo";
    private int idade = 19;


    public String setNome(String nome){
        return this.nome = nome;
    }

    public String setCabelo(String corCabelo){
        return this.corCabelo = corCabelo;
    }

    public int setIdade(int idade){
        return this.idade = idade;
    }

    public String cortar(){
        return nome + " cortou o cabelo da Maria";
    }

    public String falar(){
        return nome + " falou que sua idade é " + idade + " anos e gostaria de pintar seu cabelo de " + corCabelo;
    }

    public String pintar(){
        return nome + " pintou o cabelo de " + corCabelo;
    }
}
