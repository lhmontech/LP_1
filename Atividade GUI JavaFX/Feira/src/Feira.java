public class Feira {
    static void main(){
        Pessoa pe = new Pessoa();
        Fruta ft = new Fruta();
        Barraca bc = new Barraca();

        ft.setNome("Banana");
        ft.setCor("Amarela");
        ft.setPreco(2.5);
        pe.setNome("Claudia");
        pe.setRoupa("Azul");
        pe.setFuncao("Feirante");
        bc.setTamanho(3.30);
        bc.setConteudo("Bananas");
        bc.setNome("Claudia");

        pe.vender();
        ft.comer();
        ft.vender();
        pe.falar();
        bc.expor();
        bc.armazenar();

    }
}