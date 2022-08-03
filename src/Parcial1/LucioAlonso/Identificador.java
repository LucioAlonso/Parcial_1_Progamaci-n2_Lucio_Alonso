package Parcial1.LucioAlonso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Realiza la autentificacion de un cierto usuario con una contraseña.
 *
 * @author Lucio Alonso
 */
public class Identificador {

    /**
     * Respresenta la contraseña del usuario.
     */
    private final String pwd;

    /**
     * Respresenta el nombre del usuario.
     */
    private final String usr;

    /**
     * Crea una instancia de la clase Identificador.
     *
     * @param usr Representa el usuario pasado como parametro.
     * @param pwd Representa la contraseña pasada como parametro.
     */
    public Identificador(String usr, String pwd) {
        this.usr = usr;
        this.pwd = pwd;
    }

    /**
     * Realiza la comprobacion de la existencia de del usuario recibido con la
     * contraseña recibida.
     *
     * @param usr Representa el usuario pasada como argumento.
     * @param pwd Representa la contraseña pasada como argumento.
     * @return Devuelve un numero entero dependendiendo que tipo de usuario
     * ingresa al sistema. 0 = no ingreso. 1 = vendedor. 2 = administrador
     */
    public int identificar(String usr, String pwd) {
        Boolean band = false;
        try (BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"))) {
            String lector;

            while ((lector = br.readLine()) != null) {
                if (lector.equals(usr) && band == false) {  //Compara User
                    lector = br.readLine();
                    band = true;
                    if (band == true && lector.equals(pwd) && !lector.equals("-vendedor") && !lector.equals("-admin") && usr.isEmpty() == false && pwd.isEmpty() == false) {  //Compara PWS
                        System.out.println("\nIngresando al sistema...");
                        lector = br.readLine();
                        if (lector.equals("-vendedor")) {
                            return 1;
                        } else if (lector.equals("-admin")) {
                            return 2;
                        } else {
                            return 0;
                        }

                    } else {
                        System.out.println("El usuario o la contraseña son invalidos");
                        band = false;
                        return 0;
                    }
                } else {
                    band = false;
                }
                band = false;
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
        return 0;
    }

}
