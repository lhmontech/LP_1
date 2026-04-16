public class Prateleira {
    private Double tamanho;
    private String cor;
    private String conteudo;

    public Double setTamanho(Double tamanho){
        return this.tamanho = tamanho;
    }

    public String setCor(String cor){
        return this.cor = cor;
    }

    public String setConteudo(String conteudo){
        return this.conteudo = conteudo;
    }
    
    public void armazenar() {
        System.out.println("A prateleira " + cor + " armazena " + conteudo);
    }

    public void expor() {
        System.out.println("A prateleira " + cor + " expõe " + conteudo);
    }

    public void organizar() {
        System.out.println("A prateleira com tamanho " + tamanho + " serve para organizar os produtos " + conteudo);
    }
}
