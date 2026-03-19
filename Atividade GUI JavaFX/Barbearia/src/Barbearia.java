public class Barbearia {
    static void main(){
        Pessoa pe = new Pessoa();
        Cadeira cd = new Cadeira();
        Cabelo cb = new Cabelo();

        cd.setMaterial("Couro");
        cd.setCor("Preta");
        cd.setTamanho(1.5);
        pe.setNome("Anna");
        pe.setIdade(27);
        pe.setCabelo("Loiro");
        cb.setComprimento(0.30);
        cb.setCor("Ruivo");
        cb.setTipo("cacheado");
        
        pe.cortar();
        cd.girar();
        cd.regular();
        pe.falar();
        cb.cortar();
        cb.lavar();

    }
}