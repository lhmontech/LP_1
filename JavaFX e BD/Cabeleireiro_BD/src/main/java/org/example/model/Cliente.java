package org.example.model;

public class Cliente {
    private Long id;
    private String nome;
    private char genero;
    private String telefone;

    public Cliente(Long id, String nome, char genero, String telefone) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
