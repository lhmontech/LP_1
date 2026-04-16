public class Remedio {
    private String nome;
    private String tipo;
    private Double preco;

    public String setNome(String nome){

        return this.nome = nome;
    }

    public String setTipo(String tipo){

        return this.tipo = tipo;
    }

    public Double setPreco(Double preco){
        return this.preco = preco;
    }
    
    public void tomar() {
        System.out.println("Tomou o " + nome);
    }

    public void comprar() {
        System.out.println("comprou o " + nome + " por " + preco);
    }

    public void curar() {
        System.out.println("O " + nome + " serve para curar sintomas do tipo " + tipo);
    }

}
