public class Cabelo {
    private String cor;
    private String tipo;
    private Double comprimento;

    public String setCor(String cor){

        return this.cor = cor;
    }

    public String setTipo(String tipo){

        return this.tipo = tipo;
    }

    public Double setComprimento(Double comprimento){
        return this.comprimento = comprimento;
    }

    public void cortar() {
        System.out.println("cortou no comprimento " + comprimento);
    }

    public void lavar() {
        System.out.println("O cabelo do tipo " + tipo + " foi lavado");
    }

    public void pintar() {
        System.out.println("Pintou o cabelo da cor " + cor);
    }

}
