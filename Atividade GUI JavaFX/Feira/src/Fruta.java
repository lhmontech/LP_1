public class Fruta {
    private String nome;
    private String cor;
    private Double preco;


    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(String nome) {
        return nome;
    }

    public String getCor(String cor){
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco(Double preco){
        return this.preco = preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void vender(){
        System.out.println("A fruta" + nome + "foi vendida por " + preco);
    }

    public void estragar(){
        System.out.println(nome + " estragou ");
    }

    public void comer(){
        System.out.println("João comeu a fruta de cor " + cor);
    }
}
