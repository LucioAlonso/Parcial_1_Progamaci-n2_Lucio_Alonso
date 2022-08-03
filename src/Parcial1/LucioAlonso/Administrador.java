package Parcial1.LucioAlonso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Realiza todas las acciones de un usuario Vendedor mas las propios metodos de
 * Administrador
 *
 * @author Lucio Alonso
 */
public class Administrador extends Vendedor {

    /**
     * Representa objeto DiferenciadorFechas.
     */
    private final DiferenciadorFechas diferenciadorFechas;

    /**
     * Crea una instancia de la clase Administrador.
     */
    public Administrador() {
        diferenciadorFechas = new DiferenciadorFechas();
    }

    /**
     * Realiza la escritura en el archivo "Stock.txt" con el nuevo vehiculo
     * pasando todos los datos con el objeto vehiculo.
     *
     * @param vehiculo al objeto Vehiculo que tiene los datos del mismo.
     */
    public void agregadorDeStock(Vehiculos vehiculo) {
        System.out.println(vehiculo.getPatente());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(("Stock.txt"), true))) {
            bw.newLine();
            bw.write(":" + vehiculo.getPatente() + ":" + vehiculo.getMarca() + ":" + vehiculo.getModelo() + ":" + vehiculo.getColor() + ":" + vehiculo.getGasolina() + ":" + vehiculo.getPrecio() + ":" + vehiculo.getEstado() + ":;");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Realiza la escritura en el archivo "Stock.txt" con el nuevo vehiculo
     * pasando todos los datos en un solo String.
     *
     * @param vehiculos Representa un String con los datos de todos los
     * vehiculos.
     */
    public void agregadorDeStock(String vehiculos) {
        String[] vehiculosLista = vehiculos.split(";");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(("Stock.txt"), false))) {
            for (String vehiculosLista1 : vehiculosLista) {
                bw.write(vehiculosLista1 + ";");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Realiza la busqueda de un vehiculo a traves de la patente.
     *
     * @param patente Representa la patente del vehiculo a buscar.
     * @return Devuelve un String con los datos del vehiculo solicitado.
     */
    public String buscadorDeStock(String patente) {
        String vehiculos = lectorDeStock();
        String[] vehiculosLista = separadorDatos(vehiculos);
        String vehiculoBuscado = null;
        for (int i = 0; i < vehiculosLista.length; i++) {
            if (vehiculosLista[i].equals(patente)) {
                vehiculoBuscado = ":" + vehiculosLista[i] + ":" + vehiculosLista[i + 1] + ":" + vehiculosLista[i + 2] + ":" + vehiculosLista[i + 3] + ":" + vehiculosLista[i + 4] + ":" + vehiculosLista[i + 5] + ":" + vehiculosLista[i + 6] + ":";
            }
        }
        return vehiculoBuscado;
    }

    /**
     * Realiza la busqueda de las reservas realizadas en base a la patente de un
     * vehiculo pasada como argumento.
     *
     * @param patente Representa la patente del vehiculo buscado en las
     * reservas.
     *
     * @return Devuelve un String con los datos de reservas del vehiculo
     * solicitado.
     */
    public String buscadorDeReservas(String patente) {
        String reservas = lectorDeReservas();
        String[] reservasLista = separadorDatos(reservas);
        String reservasBuscado = null;
        for (int i = 0; i < reservasLista.length - 5; i++) {
            if (reservasLista[i + 5].equals(patente)) {
                reservasBuscado = ":" + reservasLista[i] + ":" + reservasLista[i + 1] + ":" + reservasLista[i + 2] + ":" + reservasLista[i + 3] + ":" + reservasLista[i + 4] + ":" + reservasLista[i + 5] + ":" + reservasLista[i + 6] + ":";
            }
        }
        return reservasBuscado;
    }

    /**
     * Realiza el borrado de un vehiculo pasando la patente como parametro.
     *
     * @param vehiculos Representa la lista de vehiculos cargados en el sistema.
     * @param vehiculoPatente Representa la patente del vehiculo solicitado.
     */
    public void borradorDeAutos(String vehiculos, String vehiculoPatente) {
        System.out.println("Patente buscada: " + vehiculoPatente);
        String[] vehiculosArray = vehiculos.split(";");
        String vehiculosNuevaLista = "";
        for (int i = 0; i < vehiculosArray.length; i++) {
            if (!vehiculosArray[i].equals("\n" + vehiculoPatente)) {
                System.out.println(vehiculosArray[i] + " --> " + vehiculoPatente);
                vehiculosNuevaLista = vehiculosNuevaLista + vehiculosArray[i] + ";";
            }
        }
        //System.out.println(vehiculosNuevaLista);
        agregadorDeStock(vehiculosNuevaLista);
    }

    /**
     * Realiza la actualizacion de las reservas del archivo "Reservas.txt".
     *
     * @param patente Representa la patente del vehiculo reservado a actualizar.
     * @throws ParseException Almacena un error.
     */
    public void actualizadorDeReservas(String patente) throws ParseException {
        String datos = "";
        String[] reservas = lectorDeReservas().split(":");
        String reservaBorrar = buscadorDeReservas(patente);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFin = dtf.format(LocalDateTime.now());
        int pagar = 1;
        for (int i = 10; i < reservas.length - 8; i = i + 9) {
            //System.out.println("Va por: "+reservas[i+7]);
            if (reservas[i + 4].equals(patente)) {
                pagar = (diferenciadorFechas.diferenciarDias(reservas[i + 6], fechaFin));
                pagar = pagar * Integer.valueOf(reservas[i + 4]);
                datos = datos + ":" + reservas[i] + ":" + reservas[i + 1] + ":" + reservas[i + 2] + ":" + reservas[i + 3] + ":" + pagar + ":" + reservas[i + 5] + ":" + reservas[i + 6] + ":" + dtf.format(LocalDateTime.now()) + ":;";

            } else if (!reservas[i + 4].equals(patente)) {
                datos = datos + ":" + reservas[i] + ":" + reservas[i + 1] + ":" + reservas[i + 2] + ":" + reservas[i + 3] + ":" + reservas[i + 4] + ":" + reservas[i + 5] + ":" + reservas[i + 6] + ":" + reservas[i + 7] + ":;";
                //System.out.println("Donde NO hay que cambiar la fecha: " +datos);
            }
        }
        //System.out.println("Datos tiene: "+datos);
        controladorReservas.agregadorDeReservas(datos);
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
     * Realiza la lectura del archivo "Stock.txt" y almasena los datos en una
     * variable.
     *
     * @return String con todos los datos del archivo "Stock.txt".
     */
    @Override
    public String lectorDeStock() {
        String vehiculos = "";
        try (BufferedReader br = new BufferedReader(new FileReader("Stock.txt"))) {
            String lector;
            while ((lector = br.readLine()) != null) {
                vehiculos = vehiculos + "\n";
                vehiculos = vehiculos + lector;
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
        return vehiculos;
    }

    /**
     * Realiza la busqueda de las reservas recibiendo un dato como guia.
     *
     * @param patente Representa la patente a buscar.
     * @param pos Representa la posicion a comparar. 0 = Nombre. 1 = DNI. 2 =
     * Telefono. 3 = Direccion. 4 = Monto a pagar. 5 = Patente del vehiculo
     * alquilado. 6 = Fecha de Alquiler. 7 = Fecha de Fin del Alquiler.
     *
     * @return Devuelve un String con el listado de reservas activas.
     */
    @Override
    public String buscadorReservasActivas(String patente, int pos) {
        String[] reservasDatos = lectorDeReservas().split(":");
        String listado = null;
        for (int i = 10; i < reservasDatos.length; i = i + 9) {
            //System.out.println(reservasDatos[10]);
            if ((reservasDatos[i + pos].equals(patente))) {
                //System.out.println(reservasDatos[17]);
                if (reservasDatos[i + 7].equals("true")) {
                    listado = listado + (":") + reservasDatos[i] + (":") + reservasDatos[i + 1] + (":") + reservasDatos[i + 2] + (":") + reservasDatos[i + 3] + (":") + reservasDatos[i + 4] + (":") + reservasDatos[i + 5] + (":") + reservasDatos[i + 6] + ":";
                    //System.out.println(listado);
                }
            }
            //System.out.println(reservasDatos[i]);
        }
        return listado;
    }

    /**
     * Realiza la busqueda de las reservas finalizadas recibiendo un dato como
     * guia.
     *
     * @param dni Representa el dni a buscar.
     * @param pos Representa la posicion a comparar. 0 = Nombre. 1 = DNI. 2 =
     * Telefono. 3 = Direccion. 4 = Monto a pagar. 5 = Patente del vehiculo
     * alquilado. 6 = Fecha de Alquiler. 7 = Fecha de Fin del Alquiler.
     * @return Devuelve un String con el listado de reservas activas.
     */
    @Override
    public String buscadorReservasFinalizadas(String dni, int pos) {
        String[] reservasDatos = lectorDeReservas().split(":");
        String listado = null;

        for (int i = 10; i < reservasDatos.length; i = i + 9) {
            //System.out.println(reservasDatos[10]);
            if ((reservasDatos[i + pos].equals(dni))) {
                //System.out.println(reservasDatos[i + 7]);
                if (!reservasDatos[i + 7].equals("true")) {
                    listado = listado + (":") + reservasDatos[i] + (":") + reservasDatos[i + 1] + (":") + reservasDatos[i + 2] + (":") + reservasDatos[i + 3] + (":") + reservasDatos[i + 4] + (":") + reservasDatos[i + 5] + (":") + reservasDatos[i + 6] + ":" + reservasDatos[i + 7] + ":";
                    //System.out.println(listado);
                }
            }
            //System.out.println(reservasDatos[i]);
        }
        return listado;
    }

    /**
     * Realiza la separacion de los datos pasando un string.
     *
     * @param datos Representa un string con los datos a separar.
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
     * Realiza el listado de los modelos pasando los datos como String[]
     *
     * @param vehiculosDatos Datos de todos los vehiculos separados.
     * @return Devuelve un String[] con los modelos de los vehiculos.
     */
    @Override
    public String[] listarModelos(String[] vehiculosDatos) {
        String[] vehiculosModelos = new String[vehiculosDatos.length / 9];
        int contador = 0;
        for (int i = 10; i < vehiculosDatos.length; i = i + 8) {
            //System.out.println(vehiculosDatos[i]);
            vehiculosModelos[contador] = vehiculosDatos[i];
            contador++;
        }
        return vehiculosModelos;
    }

     /**
     * Realiza el listado de los vehiulos.
     *
     * @return Devuelve un String[] con los vehiculos.
     */
    @Override
    public String[] listarVehiculos() {
        String vehiculos = lectorDeStock();

        String[] vehiculosDatos = vehiculos.split(":");
        return vehiculosDatos;
    }

    /**
     * Realiza la busqueda de un vehiculo
     * @param vehiculos Reapresenta la lista de vehiculos como String[].
     * @param vehiculoBuscar Representa el vehiculo a buscar.
     * @return Devuelve los datos del vehiculo buscado.
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
