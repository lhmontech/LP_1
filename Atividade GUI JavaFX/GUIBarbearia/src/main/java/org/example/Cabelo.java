package org.example;

public class Cabelo {
    private String cor = "Loiro";
    private String tipo = "Liso";
    private Double comprimento = 20.0;

    public String setCor(String cor){

        return this.cor = cor;
    }

    public String setTipo(String tipo){

        return this.tipo = tipo;
    }

    public Double setComprimento(Double comprimento){
        return this.comprimento = comprimento;
    }

    public String cortar() {
        return "cortou no comprimento " + comprimento + "cm";
    }

    public String lavar() {
        return "O cabelo do tipo " + tipo + " foi lavado";
    }

    public String pintar() {
        return "Pintou o cabelo da cor " + cor;
    }

}
