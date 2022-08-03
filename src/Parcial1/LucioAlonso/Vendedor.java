package Parcial1.LucioAlonso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que extiende de Usuarios con sus metodos sobrescritos.
 *
 * @author Lucio Alonso
 */
public class Vendedor extends Usuarios {

    /**
     * Representa el objeto ControladorReservas.
     */
    protected final ControladorReservas controladorReservas;

    /**
     * Crea una instancia de la clase Vendedor.
     */
    public Vendedor() {
        controladorReservas = new ControladorReservas();
    }

    /**
     * Realiza la lectura del archivo "Reservas.txt" y almasena los datos en un
     * String.
     *
     * @return String con todos los datos del archivo "Reservas.txt".
     */
    @Override
    public String lectorDeReservas() {
        String reservas = "";
        try (BufferedReader br = new BufferedReader(new FileReader("Reservas.txt"))) {
            String lector;
            while ((lector = br.readLine()) != null) {
                reservas = reservas + "\n";
                reservas = reservas + lector;
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
        return reservas;
    }

    /**
     * No realiza nada.
     *
     * @return no devuelve nada.
     */
    @Override
    public String lectorDeStock() {
        return null;
    }

    /**
     * Realiza la busqueda de las reservas recibiendo un dato como guia.
     *
     * @param dni Representa el DNI a buscar.
     * @param pos Representa la posicion a comparar. 0 = Nombre. 1 = DNI. 2 =
     * Telefono. 3 = Direccion. 4 = Monto a pagar. 5 = Patente del vehiculo
     * alquilado. 6 = Fecha de Alquiler. 7 = Fecha de Fin del Alquiler.
     * @return Devuelve un String con todas las reservas activas con ese DNI.
     */
    @Override
    public String buscadorReservasActivas(String dni, int pos) {
        String[] reservasDatos = lectorDeReservas().split(":");
        String listado = null;
        for (int i = 10; i < reservasDatos.length; i = i + 9) {
            //System.out.println(reservasDatos[10]);
            if ((reservasDatos[i + pos].equals(dni))) {
                //System.out.println(reservasDatos[17]);
                if (reservasDatos[i + 7].equals("true")) {
                    listado = listado + (":") + reservasDatos[i] + (":") + reservasDatos[i + 1] + (":") + reservasDatos[i + 2] + (":") + reservasDatos[i + 3] + (":") + reservasDatos[i + 4] + (":") + reservasDatos[i + 5] + (":") + reservasDatos[i + 6] + ":";
                    System.out.println(listado);
                }
            }
            //System.out.println(reservasDatos[i]);
        }
        return listado;
    }

    /**
     * Realiza la busqueda de las reservas recibiendo un dato como guia.
     *
     * @param dni Representa el DNI a buscar.
     * @param pos Representa la posicion a comparar. 0 = Nombre. 1 = DNI. 2 =
     * Telefono. 3 = Direccion. 4 = Monto a pagar. 5 = Patente del vehiculo
     * alquilado. 6 = Fecha de Alquiler. 7 = Fecha de Fin del Alquiler.
     * @return Devuelve un String con todas las reservas finalizadas con ese
     * DNI.
     */
    @Override
    public String buscadorReservasFinalizadas(String dni, int pos) {
        String[] reservasDatos = lectorDeReservas().split(":");
        String listado = null;

        for (int i = 10; i < reservasDatos.length; i = i + 9) {
            //System.out.println(reservasDatos[10]);
            if ((reservasDatos[i + pos].equals(dni))) {
                System.out.println(reservasDatos[i + 7]);
                if (!reservasDatos[i + 7].equals("true")) {
                    listado = listado + (":") + reservasDatos[i] + (":") + reservasDatos[i + 1] + (":") + reservasDatos[i + 2] + (":") + reservasDatos[i + 3] + (":") + reservasDatos[i + 4] + (":") + reservasDatos[i + 5] + (":") + reservasDatos[i + 6] + ":" + reservasDatos[i + 7] + ":";
                    System.out.println(listado);
                }
            }
            //System.out.println(reservasDatos[i]);
        }
        return listado;
    }

    /**
     * Realiza la separacion de los datos.
     *
     * @param datos Representan los datos pasados como argumento.
     * @return Devuelve un String[] con los datos separados.
     */
    @Override
    public String[] separadorDatos(String datos) {
        if (datos != null) {
            String[] a = datos.split(":");
            return a;
        }
        return null;
    }

    /**
     * Realiza el listado de los modelos de los vehiculos.
     *
     * @param vehiculosDatos Representa los datos separados de los vehiculos.
     * @return Devuelve un String[] con los modelos de los vehiculos.
     */
    @Override
    public String[] listarModelos(String[] vehiculosDatos) {
        String[] vehiculosModelos = new String[vehiculosDatos.length / 8];
        int contador = 0;
        for (int i = 10; i < vehiculosDatos.length; i = i + 8) { //creo que era con 9 me tengo que fijar
            if (!vehiculosDatos[i + 5].equals("true")) {
                System.out.println(vehiculosDatos[i]);
                vehiculosModelos[contador] = vehiculosDatos[i];
                contador++;
            }
        }
        return vehiculosModelos;
    }

    /**
     * Realiza el listado de los vehiculos leidos del archivo "Stock.txt".
     *
     * @return Devuelve un String[] con los datos de los vehiculos.
     */
    @Override
    public String[] listarVehiculos() {
        String vehiculos = lectorDeStock();

        String[] vehiculosDatos = vehiculos.split(":");
        return vehiculosDatos;
    }

    /**
     * Realiza el listado de los datos del vehiculo a buscar.
     *
     * @param vehiculos Representa los datos de los vehiculos
     * @param vehiculoBuscar Representa la patente del vehiculo solicitado.
     * @return Devuelve un String con los datos del vehiculo solicitado.
     */
    @Override
    public String vehiculosDatos(String[] vehiculos, String vehiculoBuscar) {
        String datos = null;

        for (int i = 0; i < vehiculos.length; i++) {
            if (vehiculos[i].equals(vehiculoBuscar)) {
                datos = vehiculos[i - 1] + ":" + vehiculos[i] + ":" + vehiculos[i + 1] + ":" + vehiculos[i + 2] + ":" + vehiculos[i + 3] + ":" + vehiculos[i + 4] + ":" + vehiculos[i + 5] + ":;";
            }
        }
        return datos;
    }

}
