public class Barraca {
    private String nomeDono;
    private String conteudo;
    private Double tamanho;


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

    public void armazenar(){
        System.out.println("A barraca da " + nomeDono + "armazena frutas");
    }

    public void expor(){
        System.out.println("A barraca da " + nomeDono + " expõe " + conteudo);
    }

    public void organizar(){
        System.out.println("A barraca com tamanho " + tamanho + " organiza as frutas");
    }
}
