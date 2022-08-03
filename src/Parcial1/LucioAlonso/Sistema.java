package Parcial1.LucioAlonso;

/**
 * Realiza el inicio de la interfaz grafica.
 *
 * @author Lucio
 */
public class Sistema {

    private final InterfazGrafica interfazGrafica;
    private String usr;
    private String pwd;

    /**
     * Crea una instancia de la clase Sistema.
     */
    public Sistema() {
        interfazGrafica = new InterfazGrafica();
    }

    /**
     * Realiza activa y llama a la InterfazGrafica.
     */
    public void Menu() {
        interfazGrafica.ventana();
    }
}
