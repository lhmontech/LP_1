package org.example;
public class Cadeira {
    private Double tamanho = 1.5;
    private String cor = "Preta";
    private String material = "Couro";

    public Double setTamanho(Double tamanho){
        return this.tamanho = tamanho;
    }

    public String setCor(String cor){
        return this.cor = cor;
    }

    public String setMaterial(String material){
        return this.material = material;
    }

    public String sentar() {
        return "Sentou na cadeira cor " + cor;
    }

    public String girar() {
        return "A cadeira de tamanho " + tamanho + " gira";
    }

    public String regular() {
        return "A cadeira que tem material " + material + " permite regular altura";
    }
}
