package Parcial1.LucioAlonso;

/**
 * Representa al Objeto Vehiculos
 *
 * @author Lucio Alonso
 */
public class Vehiculos {

    private final String patente;
    private final String marca;
    private final String modelo;
    private final String color;
    private final String gasolina;
    private final String precio;
    private final String estado;

    /**
     * Crea una instancia de la clase Vehiculos.
     *
     * @param patente Reprensenta la patente.
     * @param marca Representa la marca.
     * @param modelo Representa el modelo.
     * @param color Representa el color.
     * @param gasolina Representa el tamaño del tanque de gasolina.
     * @param precio Representa el precio.
     * @param estado Representa el estado.
     */
    public Vehiculos(String patente, String marca, String modelo, String color, String gasolina, String precio, String estado) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.gasolina = gasolina;
        this.precio = precio;
        this.estado = estado;
    }

    Vehiculos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Realiza el pasaje de todos los datos del objeto pasados como String.
     *
     * @return Devuelve un string con todos los datos.
     */
    @Override
    public String toString() {
        return "(" + patente + ", " + marca + ", " + modelo + ", " + color + ", " + gasolina + ", " + precio + ", " + estado + ")";
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getPatente() {
        return patente;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getColor() {
        return color;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getGasolina() {
        return gasolina;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getPrecio() {
        return precio;
    }

}
