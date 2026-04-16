public class Farmacia {
    static void main(){
        Pessoa pe = new Pessoa();
        Remedio rm = new Remedio();
        Prateleira pr = new Prateleira();

        rm.setNome("Loratadina");
        rm.setTipo("Antialérgico");
        rm.setPreco(7.5);
        String pessoaNome = pe.setNome("Lucas");
        pe.setIdade(24);
        pe.setGenero("Masculino");
        pr.setTamanho(2.40);
        pr.setCor("Branca");
        pr.setConteudo("Shampoo");

        System.out.print(pessoaNome);
        pe.andar();
        System.out.print(pessoaNome);
        rm.tomar();
        rm.curar();
        pe.falar();

    }
}