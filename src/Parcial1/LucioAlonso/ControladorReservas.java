package Parcial1.LucioAlonso;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que realiza el control y modificacion del archivo "Reservas.txt".
 *
 * @author Lucio Alonso
 */
public class ControladorReservas {

    /**
     * Realiza el listado de los modelos de los vehiculos pasando estos como
     * argumento.
     *
     * @param vehiculosDatos Representa el listado de datos de todos los
     * vehiculos disponibles.
     * @return Devuelve un String[] con los datos de los vehiculos disponibles.
     */
    public String[] listarModelosDisponibles(String[] vehiculosDatos) {
        String[] vehiculosModelos = new String[vehiculosDatos.length / 8];
        int contador = 0;
        for (int i = 10; i < vehiculosDatos.length; i = i + 8) {
            if (!vehiculosDatos[i + 5].equals("true")) {
                System.out.println(vehiculosDatos[i]);
                vehiculosModelos[contador] = vehiculosDatos[i];
                contador++;
            }
        }
        return vehiculosModelos;
    }

    /**
     * Realiza el agregado de las reservas en el archivo "Reservas.txt" pasando
     * el objeto reserva con todos los datos.
     *
     * @param reserva Representa al objeto reservas con todos los datos para
     * subir.
     */
    public void agregadorDeReservas(Reservas reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(("Reservas.txt"), true))) {
            bw.newLine();
            bw.write(":" + reserva.getNombre() + ":" + reserva.getDni() + ":" + reserva.getTelefono() + ":" + reserva.getDireccion() + ":" + reserva.getPrecio() + ":" + reserva.getPatente() + ":" + reserva.getFechareserva() + ":" + reserva.getEstado() + ":;");
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    /**
     * Realiza el agregado de las reservas en el archivo "Reservas.txt" pasando
     * los datos como argumentos todos juntos.
     *
     * @param reservasLista Representa un String con la lista de reservas que
     * estan en el archivo "Reservas.txt".
     */
    public void agregadorDeReservas(String reservasLista) {
        String[] datos = reservasLista.split(";");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(("Reservas.txt"), false))) {
            bw.write(":NOMBRE:DNI:TELEFONO:DIRECCION:MONTO A PAGAR:PATENTE:FECHA DE RESERVA:FECHA DE FINALIZACION (true es que sigue activa):\n");
            for (int i = 0; i < datos.length; i++) {
                bw.write(datos[i] + ";\n");
                //System.out.println("ESTOY EN ACTUALIZADOR DE STOCK");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

}
