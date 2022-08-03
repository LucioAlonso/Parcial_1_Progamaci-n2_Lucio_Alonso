package Parcial1.LucioAlonso;

/**
 * Clase Abstracta que hereda Vendedor, Consultor.
 *
 * @author Lucio Alonso
 */
public abstract class Usuarios {

    /**
     * Metodo Abstracto lectorDeReservas.
     *
     * @return Devuelve un String.
     */
    public abstract String lectorDeReservas();

    /**
     * Metodo Abstracto lectorDeStock.
     *
     * @return Devuelve un String.
     */
    public abstract String lectorDeStock();

    /**
     * Metodo Abstracto buscadorReservasActivas.
     *
     * @param dni Representa el DNI a buscar.
     * @param pos Representa la posicion a comparar. 0 = Nombre. 1 = DNI. 2 =
     * Telefono. 3 = Direccion. 4 = Monto a pagar. 5 = Patente del vehiculo
     * alquilado. 6 = Fecha de Alquiler. 7 = Fecha de Fin del Alquiler.
     *
     * @return Devuelve un String.
     */
    public abstract String buscadorReservasActivas(String dni, int pos);

    /**
     * Metodo Abstracto buscadorReservasFinalizadas.
     *
     * @param dni Representa el DNI a buscar.
     * @param pos Representa la posicion a comparar. 0 = Nombre. 1 = DNI. 2 =
     * Telefono. 3 = Direccion. 4 = Monto a pagar. 5 = Patente del vehiculo
     * alquilado. 6 = Fecha de Alquiler. 7 = Fecha de Fin del Alquiler.
     *
     * @return Devuelve un String.
     */
    public abstract String buscadorReservasFinalizadas(String dni, int pos);

    /**
     * Metodo Abstracto separadorDatos.
     *
     * @param datos Representa los datos a separar.
     * @return Devuelve un String.
     */
    public abstract String[] separadorDatos(String datos);

    /**
     * Metodo Abstracto listarModelos.
     *
     * @param vehiculosDatos Representa los datos de los vehiculos.
     * @return Devuelve un String[].
     */
    public abstract String[] listarModelos(String[] vehiculosDatos);

    /**
     * Metodo Abstracto listarVehiculos.
     *
     * @return Devuelve un String[].
     */
    public abstract String[] listarVehiculos();

    /**
     * Metodo Abstracto vehiculosDatos.
     *
     * @param vehiculos Representa los vehiculos.
     * @param vehiculoBuscar Representa la patente del vehiculo a buscar.
     * @return Devuelve un String[].
     */
    public abstract String vehiculosDatos(String[] vehiculos, String vehiculoBuscar);
}
