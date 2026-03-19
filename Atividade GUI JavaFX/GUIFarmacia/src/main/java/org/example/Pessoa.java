package org.example;

import javafx.scene.image.ImageView;

public class Pessoa {
    private String nome = "Lucas";
    private String genero;
    private int idade;
    private ImageView imagem;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void andar(){
        imagem.setX(-350.0);
    }

    public String falar(){
        return "Olá, me chamo " + nome;
    }

    public String gritar(){
        return "AAAAAAA!";
    }
}
