package Parcial1.LucioAlonso;

/**
 * Representa al Objeto Reservas
 *
 * @author Lucio Alonso
 */
public class Reservas {

    private final String nombre;
    private final String dni;
    private final String telefono;
    private final String direccion;
    private final String precio;
    private final String patente;
    private final String fechareserva;
    private final String estado;

    /**
     * Crea una instancia de la clase Reservas.
     *
     * @param nombre Reprenseta el nombre.
     * @param dni Representa el DNI.
     * @param telefono Representa el Telefono.
     * @param direccion Representa la direccion.
     * @param precio Representa el precio.
     * @param patente Representa la patente.
     * @param fechareserva Representa la fecha de reserva.
     * @param estado Representa el estado de la reserva.
     */
    public Reservas(String nombre, String dni, String telefono, String direccion, String precio, String patente, String fechareserva, String estado) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.precio = precio;
        this.patente = patente;
        this.fechareserva = fechareserva;
        this.estado = estado;
    }

    /**
     * Realiza el pasaje de todos los datos del objeto pasados como String.
     *
     * @return Devuelve un string con todos los datos.
     */
    @Override
    public String toString() {
        return "(" + nombre + ", " + dni + ", " + telefono + ", " + direccion + ", " + precio + ", " + patente + ", " + fechareserva + ", " + estado + ")";
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getPrecio() {
        return precio;
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
    public String getFechareserva() {
        return fechareserva;
    }

    /**
     * Realiza el pasaje del dato solicitado.
     *
     * @return Devuelve un string con todos los datos.
     */
    public String getEstado() {
        return estado;
    }

}
