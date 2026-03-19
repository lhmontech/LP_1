public class Pessoa {
    private String nome;
    private String corRoupa;
    private String funcao;


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

    public void vender(){
        System.out.println("A " + funcao + " vendeu suas frutas");
    }

    public void falar(){
        System.out.println(nome + " falou com a feirante de roupa " + corRoupa);
    }

    public void comprar(){
        System.out.println(nome + " comprou um abacaxi");
    }
}
