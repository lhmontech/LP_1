package org.example;

public class Pessoa {
    private String nome;
    private String genero;
    private int idade;


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
        System.out.println(" andou 5km");
    }

    public void falar(){
        System.out.println(nome + " falou que sua idade é " + idade + " anos e seu genêro é " + genero);
    }

    public void gritar(){
        System.out.println(nome + " gritou AAAAAAA!");
    }
}
