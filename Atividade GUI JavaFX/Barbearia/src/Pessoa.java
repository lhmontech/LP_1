public class Pessoa {
    private String nome;
    private String corCabelo;
    private int idade;


    public String setNome(String nome){
        return this.nome = nome;
    }

    public String setCabelo(String corCabelo){
        return this.corCabelo = corCabelo;
    }

    public int setIdade(int idade){
        return this.idade = idade;
    }

    public void cortar(){
        System.out.println(nome + " cortou o cabelo da Maria");
    }

    public void falar(){
        System.out.println(nome + " falou que sua idade é " + idade + " anos e gostaria de pintar seu cabelo de " + corCabelo);
    }

    public void pintar(){
        System.out.println(nome + " pintou o cabelo de " + corCabelo);
    }
}
