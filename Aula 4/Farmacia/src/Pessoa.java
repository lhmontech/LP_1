public class Pessoa {
    private String nome;
    private String genero;
    private int idade;


    public String setNome(String nome){
        return this.nome = nome;
    }

    public String setGenero(String genero){
        return this.genero = genero;
    }

    public int setIdade(int idade){
        return this.idade = idade;
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
