package AtividadeVitor;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Visao visao = new Visao();
        new Controlador(modelo, visao);
    }
}
