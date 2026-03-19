public class Cadeira {
    private Double tamanho;
    private String cor;
    private String material;

    public Double setTamanho(Double tamanho){
        return this.tamanho = tamanho;
    }

    public String setCor(String cor){
        return this.cor = cor;
    }

    public String setMaterial(String material){
        return this.material = material;
    }

    public void Sentar() {
        System.out.println("Sentou na cadeira cor " + cor);
    }

    public void girar() {
        System.out.println("A cadeira de tamanho " + tamanho + " gira");
    }

    public void regular() {
        System.out.println("A cadeira que tem material " + material + " permite regular altura");
    }
}
