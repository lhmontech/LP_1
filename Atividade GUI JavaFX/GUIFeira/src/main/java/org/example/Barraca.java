package org.example;
public class Barraca {
    private String nomeDono = "Márcio";
    private String conteudo = "Bananas";
    private Double tamanho = 2.5;


    public String getNome(String nomeDono){
        return nomeDono;
    }

    public void setNome(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getConteudo(String conteudo){
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Double getTamanho(Double tamanho){
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public String armazenar(){
        return "A barraca da " + nomeDono + "armazena frutas";
    }

    public String expor(){
        return "A barraca da " + nomeDono + " expõe " + conteudo;
    }

    public String organizar(){
        return "A barraca com tamanho " + tamanho + "m organiza as frutas" + conteudo;
    }
}
