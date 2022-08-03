package Parcial1.LucioAlonso;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Muestra las ventanas para que el usuario pueda interactuar y sus menues.
 *
 * @author Lucio Alonso
 */
public class InterfazGrafica extends JFrame {

    /**
     * Representan el objeto de tipo Consultor que se van a emplear en la clase
     * InterfazGrafica.
     */
    private final Consultor consultor;
    /**
     * Representan el objeto de tipo Identificador que se van a emplear en la
     * clase InterfazGrafica.
     */
    private final Identificador identificador;
    /**
     * Representan el objeto de tipo Vendedor que se van a emplear en la clase
     * InterfazGrafica.
     */
    private final Vendedor vendedor;
    /**
     * Representan el objeto de tipo Administrador que se van a emplear en la
     * clase InterfazGrafica.
     */
    private final Administrador administrador;
    /**
     * Representa el usuario
     */
    private String usr;

    /**
     * Representa la contraseña
     */
    private String pwd;

    /**
     * Representan algunos de los objetos que mas se repiten en la clase
     * InterfazGrafica.
     */
    private String vehiculosDatos;
    private boolean agregarBand = false;
    private JPanel panel;
    private JLabel logo;
    private JTextField cajaDeTexto;
    private JPasswordField cajaDeTexto2;
    private JLabel etiqueta1;
    private JLabel etiqueta2;
    private JLabel etiqueta3;
    private JLabel etiqueta4;

    /**
     * Crea una instancia de la clase InterfazGrafica.
     */
    public InterfazGrafica() {
        consultor = new Consultor();
        identificador = new Identificador(usr, pwd);
        vendedor = new Vendedor();
        administrador = new Administrador();
    }

    /**
     * Genera una ventana.
     */
    public void ventana() {
        setSize(800, 400);
        setTitle("Avis");
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel();
        panelPrincipal();
    }

    /**
     * Genera un panel para una ventana.
     */
    public void panel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        panel.updateUI();
    }

    /**
     * Genera un estilo de panel interactivo principal para que el usuario pueda
     * interactuar con el y moverse entre los menues y haga consultas.
     */
    public void panelPrincipal() {
        JButton iniciarSesion;
        JButton buscar;
        panel.removeAll();
        logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(0, -25, 800, 400);
        panel.add(logo);
        //
        etiqueta1 = new JLabel();
        etiqueta1.setText("Realizar Consulta");
        etiqueta1.setBounds(300, 80, 200, 18);
        etiqueta1.setFont(new Font("cooper black", 0, 20));
        panel.add(etiqueta1);

        etiqueta2 = new JLabel();
        etiqueta2.setText("DNI:");
        etiqueta2.setBounds(290, 105, 70, 10);
        panel.add(etiqueta2);

        JTextField dni = new JTextField();
        dni.setBounds(315, 103, 100, 15);
        panel.add(dni);

        JTextArea reservasActuales = new JTextArea();
        reservasActuales.setBounds(175, 135, 200, 220);
        reservasActuales.setEditable(false);
        panel.add(reservasActuales);

        JTextArea reservasFinalizadas = new JTextArea();
        reservasFinalizadas.setBounds(395, 135, 200, 220);
        reservasFinalizadas.setEditable(false);
        panel.add(reservasFinalizadas);
        //
        iniciarSesion = new JButton();
        iniciarSesion.setText("Iniciar Sesión");
        iniciarSesion.setBounds(650, 320, 115, 30);
        panel.add(iniciarSesion);

        buscar = new JButton();
        buscar.setText("Buscar");
        buscar.setBounds(415, 102, 75, 15);
        panel.add(buscar);

        panel.updateUI();

        ActionListener oyenteBuscar = (ActionEvent ae) -> {

            reservasActuales.setText(null);
            reservasFinalizadas.setText(null);
            int tam = 0;
            String[] listaReservasActivas = consultor.separadorDatos(consultor.buscadorReservasActivas(dni.getText(), 1));
            String[] listaReservasFinalizadas = consultor.separadorDatos(consultor.buscadorReservasFinalizadas(dni.getText(), 1));

            reservasActuales.append("Reservas Activas: \n");
            if (listaReservasActivas == null) {
                System.out.println("NO HAY NADA ");
            } else if (listaReservasActivas.length == 1) {
                reservasActuales.append("\n -Fecha de Alquiler: " + listaReservasActivas[7] + "\n -Patente: " + listaReservasActivas[6] + "\n");
            } else {
                for (int i = 0; i < listaReservasActivas.length; i += 8) {
                    //System.out.println(listaReservas[i + 5]);
                    reservasActuales.append("\n -Fecha de Alquiler: " + listaReservasActivas[i + 7] + "\n -Patente: " + listaReservasActivas[i + 6] + "\n");
                }
            }

            //arreglar o ver como reemplazarlo
            reservasFinalizadas.append("Reservas Finalizadas: \n");
            if (listaReservasFinalizadas == null) {
                System.out.println("NO HAY NADA ");
            } else if (listaReservasFinalizadas.length == 1) {
                reservasFinalizadas.append("\n   -Fecha de Alquiler: " + listaReservasFinalizadas[7] + "\n   -Fecha de Finalizacion: " + listaReservasFinalizadas[6] + "\n   -Patente: " + listaReservasFinalizadas[4] + "\n     -Pagar: $" + listaReservasFinalizadas[3] + "\n");
            } else {
                for (int i = 0; i < listaReservasFinalizadas.length; i += 9) {
                    //System.out.println(listaReservasFinalizadas[i]);
                    reservasFinalizadas.append("\n -Fecha de Alquiler: " + listaReservasFinalizadas[i + 7] + "\n -Fecha de Finalizacion: " + listaReservasFinalizadas[i + 8] + "\n -Patente: " + listaReservasFinalizadas[i + 6] + "\n   -Pagar: $" + listaReservasFinalizadas[i + 5] + "\n");
                }
            }
        };
        buscar.addActionListener(oyenteBuscar);

        ActionListener oyenteInciarSesion = (ActionEvent ae) -> {
            panelInicioSesion();
        };
        iniciarSesion.addActionListener(oyenteInciarSesion);

    }

    /**
     * Genera un estilo de panel interactivo para que el usuario inicie sesion.
     * Valida si este puede ingresar al sistema y realizar cambio y que tipo de
     * usuario es.
     */
    public void panelInicioSesion() {
        panel.removeAll();
        logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(0, -25, 800, 400);
        panel.add(logo);

        etiqueta1 = new JLabel();
        etiqueta1.setText("Usuario:");
        etiqueta1.setBounds(295, 150, 200, 50);
        panel.add(etiqueta1);

        etiqueta2 = new JLabel();
        etiqueta2.setText("Contraseña:");
        etiqueta2.setBounds(273, 180, 200, 50);
        panel.add(etiqueta2);

        etiqueta3 = new JLabel();
        etiqueta3.setText("Inicio de Sesion");
        etiqueta3.setBounds(295, 130, 200, 25);
        etiqueta3.setFont(new Font("arial", Font.BOLD, 20));
        panel.add(etiqueta3);

        etiqueta4 = new JLabel();
        etiqueta4.setText("usuario o contraseña invalidos");
        etiqueta4.setForeground(Color.red);
        etiqueta4.setBounds(295, 197, 200, 50);
        etiqueta4.setVisible(false);
        panel.add(etiqueta4);

        JButton iniciar = new JButton();
        iniciar.setText("Iniciar");
        iniciar.setBounds(450, 230, 75, 20);
        panel.add(iniciar);

        JButton volver = new JButton();
        volver.setText("Volver");
        volver.setBounds(650, 320, 115, 30);
        panel.add(volver);

        cajaDeTexto = new JTextField();
        cajaDeTexto.setBounds(350, 166, 100, 20);

        panel.add(cajaDeTexto);

        cajaDeTexto2 = new JPasswordField();
        cajaDeTexto2.setBounds(350, 195, 100, 20);
        cajaDeTexto2.setEchoChar('*');
        panel.add(cajaDeTexto2);

        panel.updateUI();

        ActionListener oyenteVolver = (ActionEvent ae) -> {
            panelPrincipal();
        };
        volver.addActionListener(oyenteVolver);

        ActionListener oyenteIniciar = (ActionEvent ae) -> {
            switch (identificador.identificar(cajaDeTexto.getText(), cajaDeTexto2.getText())) {
                case 1:
                    panelPrincipalUsuario(cajaDeTexto.getText(), 1);
                    break;
                case 2:
                    panelPrincipalUsuario(cajaDeTexto.getText(), 2);
                    break;
                default:
                    etiqueta4.setVisible(true);
                    break;
            }
        };
        iniciar.addActionListener(oyenteIniciar);

    }

    /**
     * Genera un estilo de panel interactivo que se adapta segun el tipo de
     * usuario que se ingresa. Tambien permite hacer consultas.
     *
     * @param usr Representa el dato del usuario.
     * @param tipo Representa el dato del tipo de usuario.
     */
    private void panelPrincipalUsuario(String usr, int tipo) {
        //TODA ESTA PARTE SE PUEDE HACER CON POLIMORFISMO O CON HERENCIA
        //Aca va el code del vendedor
        panel.removeAll();
        logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(0, -25, 800, 400);
        panel.add(logo);

        etiqueta1 = new JLabel();
        etiqueta1.setText("Usuario: " + usr);
        etiqueta1.setBounds(5, 2, 200, 18);
        panel.add(etiqueta1);

        etiqueta2 = new JLabel();
        etiqueta2.setText("Tipo: Vendedor");
        etiqueta2.setBounds(695, 2, 100, 18);
        panel.add(etiqueta2);

        etiqueta3 = new JLabel();
        etiqueta3.setText("Realizar Consulta");
        etiqueta3.setBounds(465, 80, 200, 18);
        etiqueta3.setFont(new Font("cooper black", 0, 20));
        panel.add(etiqueta3);

        etiqueta4 = new JLabel();
        etiqueta4.setText("DNI:");
        etiqueta4.setBounds(455, 105, 70, 10);
        panel.add(etiqueta4);

        JTextField dni = new JTextField();
        dni.setBounds(480, 103, 100, 15);
        panel.add(dni);

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        buscar.setBounds(580, 103, 75, 15);
        panel.add(buscar);

        JButton nuevaReserva = new JButton();
        nuevaReserva.setText("Nueva Reserva");
        nuevaReserva.setBounds(100, 150, 150, 40);
        panel.add(nuevaReserva);

        JButton cerrarSesion = new JButton();
        cerrarSesion.setText("Cerrar Sesion");
        cerrarSesion.setBounds(660, 340, 115, 15);
        panel.add(cerrarSesion);

        JTextArea reservasActuales = new JTextArea();
        reservasActuales.setBounds(355, 120, 180, 220);
        reservasActuales.setEditable(false);
        panel.add(reservasActuales);

        JTextArea reservasFinalizadas = new JTextArea();
        reservasFinalizadas.setBounds(560, 120, 180, 220);
        reservasFinalizadas.setEditable(false);
        panel.add(reservasFinalizadas);

        panel.updateUI();

        if (tipo == 2) {
            //Aca va el code del Admin 
            etiqueta2.setText("Tipo: Admin");
            etiqueta2.setBounds(710, 2, 100, 18);

            JButton stock = new JButton();
            stock.setText("Control de Stock");
            stock.setBounds(100, 200, 150, 40);
            panel.add(stock);

            ActionListener oyenteControlStock = (ActionEvent ae) -> {
                panelControlVehiculos(usr, tipo);
            };

            ActionListener oyenteNuevaReserva = (ActionEvent ae) -> {
                panelNuevaReserva(usr, 2);
            };

            stock.addActionListener(oyenteControlStock);
            nuevaReserva.addActionListener(oyenteNuevaReserva);

        } else {
            ActionListener oyenteNuevaReserva = (ActionEvent ae) -> {
                panelNuevaReserva(usr, 1);
            };
            nuevaReserva.addActionListener(oyenteNuevaReserva);
        }

        ActionListener oyenteCerrarSesion = (ActionEvent ae) -> {
            panelPrincipal();
        };

        cerrarSesion.addActionListener(oyenteCerrarSesion);

        ActionListener oyenteBuscar = (ActionEvent ae) -> {

            reservasActuales.setText(null);
            reservasFinalizadas.setText(null);
            int tam = 0;
            String[] listaReservasActivas = consultor.separadorDatos(consultor.buscadorReservasActivas(dni.getText(), 1));
            String[] listaReservasFinalizadas = consultor.separadorDatos(consultor.buscadorReservasFinalizadas(dni.getText(), 1));

            reservasActuales.append("Reservas Activas: \n");
            if (listaReservasActivas == null) {
                System.out.println("NO HAY NADA ");
            } else if (listaReservasActivas.length == 1) {
                reservasActuales.append("\n -Fecha de Alquiler: " + listaReservasActivas[7] + "\n -Patente: " + listaReservasActivas[6] + "\n");
            } else {
                for (int i = 0; i < listaReservasActivas.length; i += 8) {
                    //System.out.println(listaReservas[i + 5]);
                    reservasActuales.append("\n -Fecha de Alquiler: " + listaReservasActivas[i + 7] + "\n -Patente: " + listaReservasActivas[i + 6] + "\n");
                }
            }

            //arreglar o ver como reemplazarlo
            reservasFinalizadas.append("Reservas Finalizadas: \n");
            if (listaReservasFinalizadas == null) {
                System.out.println("NO HAY NADA ");
            } else if (listaReservasFinalizadas.length == 1) {
                reservasFinalizadas.append("\n   -Fecha de Alquiler: " + listaReservasFinalizadas[7] + "\n   -Fecha de Finalizacion: " + listaReservasFinalizadas[6] + "\n   -Patente: " + listaReservasFinalizadas[4] + "\n     -Pagar: $" + listaReservasFinalizadas[3] + "\n");
            } else {
                for (int i = 0; i < listaReservasFinalizadas.length; i += 9) {
                    //System.out.println(listaReservasFinalizadas[i]);
                    reservasFinalizadas.append("\n -Fecha de Alquiler: " + listaReservasFinalizadas[i + 7] + "\n -Fecha de Finalizacion: " + listaReservasFinalizadas[i + 8] + "\n -Patente: " + listaReservasFinalizadas[i + 6] + "\n   -Pagar: $" + listaReservasFinalizadas[i + 5] + "\n");
                }
            }
        };
        buscar.addActionListener(oyenteBuscar);

    }

    /**
     * Genera un estilo de panel interactivo en el cual se realizan las
     * actualizaciones de los vehiculos disponibles.
     *
     * @param usr Representa el dato del usuario.
     * @param tipo Representa el dato del tipo de usuario.
     */
    private void panelControlVehiculos(String usr, int tipo) {
        panel.removeAll();
        logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(0, -25, 800, 400);
        panel.add(logo);

        etiqueta1 = new JLabel();
        etiqueta1.setText("Usuario: " + usr);
        etiqueta1.setBounds(5, 2, 200, 18);
        panel.add(etiqueta1);

        etiqueta2 = new JLabel();
        etiqueta2.setText("Tipo: Admin");
        etiqueta2.setBounds(710, 2, 100, 18);
        panel.add(etiqueta2);

        etiqueta3 = new JLabel();
        etiqueta3.setText("Control de Stock");
        etiqueta3.setBounds(300, 80, 200, 18);
        etiqueta3.setFont(new Font("cooper black", 0, 20));
        panel.add(etiqueta3);

        etiqueta4 = new JLabel();
        etiqueta4.setText("Vehiculo:");
        etiqueta4.setFont(new Font("", 0, 20));
        etiqueta4.setBounds(105, 130, 200, 20);
        panel.add(etiqueta4);

        JLabel etiqueta5 = new JLabel();
        etiqueta5.setText("Estado:");
        etiqueta5.setFont(new Font("", 0, 20));
        etiqueta5.setBounds(115, 180, 200, 20);
        panel.add(etiqueta5);
        /*
        
        TENGO QUE HACER ACA LA BARRA DESPLEGABLE PARA QUE APAREZCAN LOS AUTOS DISPONIBLES
        Recordar tambien poner una opcion que sea para agregar mas autos o quitar los que estan.
        Y que dependiendo que opcion se elige, las casillas se pueden modificar o no.
        
         */
        JComboBox vehiculosLista = new JComboBox(administrador.listarModelos(administrador.listarVehiculos()));
        vehiculosLista.setBounds(200, 130, 175, 20);
        vehiculosLista.setSelectedItem(null);
        panel.add(vehiculosLista);

        String[] estado = {"Sin reservar", "Reservado"};
        JComboBox vehiculosEstado = new JComboBox(estado);
        vehiculosEstado.setBounds(200, 180, 100, 20);
        vehiculosEstado.setSelectedItem(null);
        vehiculosEstado.setEditable(false);
        panel.add(vehiculosEstado);

        JTextField vehiculoNuevo = new JTextField();
        vehiculoNuevo.setBounds(200, 130, 175, 20);
        vehiculoNuevo.setVisible(false);
        panel.add(vehiculoNuevo);

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        buscar.setBounds(105, 105, 80, 20);
        panel.add(buscar);

        JLabel etiqueta6 = new JLabel();
        etiqueta6.setText("Patente:");
        etiqueta6.setFont(new Font("", 0, 20));
        etiqueta6.setBounds(400, 130, 200, 20);
        panel.add(etiqueta6);

        JTextField patente = new JTextField();
        patente.setBounds(480, 132, 75, 20);
        patente.setEditable(false);
        panel.add(patente);

        JLabel etiqueta12 = new JLabel();
        etiqueta12.setText("Modelo:");
        etiqueta12.setFont(new Font("", 0, 20));
        etiqueta12.setBounds(600, 130, 200, 20);
        panel.add(etiqueta12);

        JTextField modelo = new JTextField();
        modelo.setBounds(675, 133, 75, 20);
        modelo.setEditable(false);
        panel.add(modelo);

        JLabel etiqueta7 = new JLabel();
        etiqueta7.setText("Gasolina:");
        etiqueta7.setFont(new Font("", 0, 20));
        etiqueta7.setBounds(393, 180, 200, 20);
        panel.add(etiqueta7);

        JTextField gasolina = new JTextField();
        gasolina.setBounds(480, 183, 75, 20);
        gasolina.setEditable(false);
        panel.add(gasolina);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JLabel etiqueta8 = new JLabel(); //Tener en cuenta que esta se registra al momento de hacer la reserva
        etiqueta8.setText("Fecha de Hoy: " + dtf.format(LocalDateTime.now()));
        etiqueta8.setFont(new Font("", 0, 15));
        etiqueta8.setBounds(105, 275, 200, 20);
        panel.add(etiqueta8);

        JLabel etiqueta10 = new JLabel(); //Si esta la opcion de agregar, el precio se debe poder cambiar
        etiqueta10.setText("Precio:");
        etiqueta10.setFont(new Font("", 0, 20));
        etiqueta10.setBounds(115, 230, 200, 20);
        panel.add(etiqueta10);

        JTextField precio = new JTextField();
        precio.setBounds(200, 233, 75, 20);
        precio.setEditable(false);
        panel.add(precio);

        JLabel etiqueta11 = new JLabel();
        etiqueta11.setText("Color:");
        etiqueta11.setFont(new Font("", 0, 20));
        etiqueta11.setBounds(393, 230, 200, 20);
        panel.add(etiqueta11);

        JTextField color = new JTextField();
        color.setBounds(480, 233, 75, 20);
        color.setEditable(false);
        panel.add(color);

        JButton aceptar = new JButton();
        aceptar.setText("Aplicar");
        aceptar.setBounds(540, 300, 100, 40);
        aceptar.setEnabled(false);
        panel.add(aceptar);

        JButton agregar = new JButton();
        agregar.setText("Agregar Nuevo");
        agregar.setBounds(410, 300, 120, 40);
        panel.add(agregar);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setBounds(650, 300, 100, 40);
        panel.add(cancelar);

        panel.updateUI();

        ActionListener oyenteCancelar = (ActionEvent ae) -> {
            panelPrincipalUsuario(usr, tipo);
        };
        cancelar.addActionListener(oyenteCancelar);

        ActionListener oyenteBuscar = (ActionEvent ae) -> {
            String a = (String) vehiculosLista.getSelectedItem();
            agregarBand = false;
            agregar.setEnabled(true);
            vehiculosLista.setVisible(true);
            vehiculoNuevo.setVisible(false);
            vehiculoNuevo.setEnabled(true);
            modelo.setEditable(false);
            patente.setEditable(false);
            gasolina.setEditable(false);
            vehiculosEstado.setEditable(false);
            vehiculosEstado.setEnabled(true);
            precio.setEditable(false);
            color.setEditable(false);

            if (a != null) {
                aceptar.setEnabled(true);
            }

            String vehiculosDatos = consultor.vehiculosDatos(administrador.listarVehiculos(), a);
            System.out.println(vehiculosDatos); //de pruebas
            String[] datos = consultor.separadorDatos(vehiculosDatos);

            patente.setText(datos[0]);
            modelo.setText(datos[2]);
            color.setText(datos[3]);
            gasolina.setText(datos[4]);
            precio.setText(datos[5]);

            if (datos[6].equals("false")) {
                vehiculosEstado.setSelectedItem("Sin reservar");
            } else {
                vehiculosEstado.setSelectedItem("Reservado");
            }
        };
        buscar.addActionListener(oyenteBuscar);

        ActionListener oyenteAplicar = (ActionEvent ae) -> {

            Vehiculos vehiculo;
            String a = (String) vehiculosLista.getSelectedItem();
            String b = (String) vehiculosEstado.getSelectedItem();
            String vehiculosDatos = consultor.vehiculosDatos(administrador.listarVehiculos(), a);
            String[] datos = consultor.separadorDatos(vehiculosDatos);
            aceptar.setEnabled(false);
            agregar.setEnabled(true);
            buscar.setEnabled(true);
            vehiculosEstado.setEnabled(true);
            if (agregarBand == false) {

                if (b.equals("Sin reservar") && datos[6].equals("true")) {
                    vehiculo = new Vehiculos(patente.getText(), vehiculosLista.getSelectedItem().toString(), modelo.getText(), color.getText(), gasolina.getText(), precio.getText(), "false");
                    administrador.borradorDeAutos(administrador.lectorDeStock(), administrador.buscadorDeStock(datos[0].toString()));
                    administrador.agregadorDeStock(vehiculo);
                    try {
                        administrador.actualizadorDeReservas(datos[5]);
                    } catch (ParseException ex) {
                        Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (b.equals("Reservado") && datos[6].equals("false")) {
                    vehiculo = new Vehiculos(patente.getText(), vehiculosLista.getSelectedItem().toString(), modelo.getText(), color.getText(), gasolina.getText(), precio.getText(), "true");
                    administrador.borradorDeAutos(administrador.lectorDeStock(), administrador.buscadorDeStock(datos[0]));
                    administrador.agregadorDeStock(vehiculo);
                }
                panel.updateUI();
            } else {
                if (color.getText().equals("") || patente.getText().equals("") || gasolina.getText().equals("") || precio.getText().equals("") || modelo.getText().equals("")) {
                    System.out.print("NO ESTAN TODOS LOS DATOS COLOCADOS");
                    aceptar.setEnabled(true);
                    agregar.setEnabled(false);
                    buscar.setEnabled(false);
                    vehiculosEstado.setEnabled(false);
                } else {
                    vehiculo = new Vehiculos(patente.getText(), vehiculoNuevo.getText(), modelo.getText(), color.getText(), gasolina.getText(), precio.getText(), "false");
                    System.out.println("Estoy aca false");
                    administrador.agregadorDeStock(vehiculo);
                    System.out.println("Estoy aca false");

                    panel.updateUI();
                }
                panelPrincipalUsuario(usr, tipo);
            }

        };
        aceptar.addActionListener(oyenteAplicar);

        ActionListener oyenteAgregar = (ActionEvent ae) -> {
            agregarBand = true;
            patente.setEditable(true);
            precio.setEditable(true);
            color.setEditable(true);
            modelo.setEditable(true);
            buscar.setEnabled(false);
            patente.setText(null);
            gasolina.setText(null);
            modelo.setText(null);
            vehiculosEstado.setSelectedItem("Sin reservar");
            vehiculosEstado.setEnabled(false);
            gasolina.setEditable(true);
            vehiculosLista.setSelectedItem(null);
            vehiculosLista.setVisible(false);
            precio.setText(null);
            color.setText(null);

            vehiculoNuevo.setVisible(true);
            vehiculoNuevo.setEnabled(true);
            vehiculoNuevo.setText(null);

            agregar.setEnabled(false);
            aceptar.setEnabled(true);
        };
        agregar.addActionListener(oyenteAgregar);

    }

    /**
     * Genera un estilo de panel interactivo en el cual se realizan las
     * reservas.
     *
     * @param usr Representa el dato del usuario.
     * @param tipo Representa el dato del tipo de usuario.
     */
    private void panelNuevaReserva(String usr, int tipo) {

        panel.removeAll();
        logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(0, -25, 800, 400);
        panel.add(logo);

        etiqueta1 = new JLabel();
        etiqueta1.setText("Usuario: " + usr);
        etiqueta1.setBounds(5, 2, 200, 18);
        panel.add(etiqueta1);

        etiqueta2 = new JLabel();
        etiqueta2.setBounds(695, 2, 100, 18);
        panel.add(etiqueta2);
        etiqueta2.setText("Tipo: Vendedor");

        if (tipo == 2) {
            etiqueta2.setText("Tipo: Admin");
            etiqueta2.setBounds(710, 2, 100, 18);
        }

        etiqueta2 = new JLabel();
        etiqueta2.setText("NUEVA RESERVA");
        etiqueta2.setBounds(300, 75, 200, 18);
        etiqueta2.setFont(new Font("cooper black", 0, 20));
        panel.add(etiqueta2);

        etiqueta3 = new JLabel();
        etiqueta3.setText("Nombre:");
        etiqueta3.setFont(new Font("", 0, 20));
        etiqueta3.setBounds(105, 130, 200, 20);
        panel.add(etiqueta3);

        JTextField nombre = new JTextField();
        nombre.setBounds(185, 132, 200, 20);
        panel.add(nombre);

        etiqueta4 = new JLabel();
        etiqueta4.setText("DNI:");
        etiqueta4.setFont(new Font("", 0, 20));
        etiqueta4.setBounds(141, 180, 200, 20);
        panel.add(etiqueta4);

        JTextField dni = new JTextField();
        dni.setBounds(185, 182, 200, 20);
        panel.add(dni);

        JLabel etiqueta5 = new JLabel();
        etiqueta5.setText("Direccion:");
        etiqueta5.setFont(new Font("", 0, 20));
        etiqueta5.setBounds(400, 130, 200, 18);
        panel.add(etiqueta5);

        JTextField direccion = new JTextField();
        direccion.setBounds(490, 132, 200, 20);
        panel.add(direccion);

        JLabel etiqueta6 = new JLabel();
        etiqueta6.setText("Telefono:");
        etiqueta6.setFont(new Font("", 0, 20));
        etiqueta6.setBounds(400, 180, 200, 18);
        panel.add(etiqueta6);

        JTextField telefono = new JTextField();
        telefono.setBounds(490, 182, 200, 20);
        panel.add(telefono);

        JLabel etiqueta7 = new JLabel();
        etiqueta7.setText("Vehiculo:");
        etiqueta7.setFont(new Font("", 0, 20));
        etiqueta7.setBounds(100, 230, 200, 18);
        panel.add(etiqueta7);

        JLabel etiqueta8 = new JLabel();
        etiqueta8.setText("Precio:");
        etiqueta8.setFont(new Font("", 0, 20));
        etiqueta8.setBounds(420, 230, 200, 18);
        panel.add(etiqueta8);

        //ACA ME TENGO QUE MOVER
        JLabel precio = new JLabel();
        precio.setText("");
        precio.setFont(new Font("", 0, 20));
        precio.setBounds(490, 230, 200, 20);
        panel.add(precio);

        JLabel etiqueta9 = new JLabel();
        etiqueta9.setText("Fecha Actual:");
        etiqueta9.setFont(new Font("", 0, 16));
        etiqueta9.setBounds(100, 280, 200, 18);
        panel.add(etiqueta9);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        JLabel fecha = new JLabel();
        fecha.setText(dtf.format(LocalDateTime.now()));
        fecha.setFont(new Font("", 0, 16));
        fecha.setBounds(200, 280, 200, 18);
        panel.add(fecha);

        JComboBox vehiculosDisponibles = new JComboBox(consultor.listarModelos(consultor.listarVehiculos()));
        vehiculosDisponibles.setBounds(200, 233, 175, 20);
        vehiculosDisponibles.setSelectedItem(null);
        panel.add(vehiculosDisponibles);

        JButton aceptar = new JButton();
        aceptar.setText("Aceptar");
        aceptar.setBounds(540, 300, 100, 40);
        panel.add(aceptar);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setBounds(650, 300, 100, 40);
        panel.add(cancelar);

        panel.updateUI();

        vehiculosDisponibles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = (String) vehiculosDisponibles.getSelectedItem();
                vehiculosDatos = consultor.vehiculosDatos(consultor.listarVehiculos(), a);
                String[] datos = consultor.separadorDatos(vehiculosDatos);
                precio.setText(datos[5]);
                System.out.println(vehiculosDatos);
            }
        });

        ActionListener oyenteAceptar = (ActionEvent ae) -> {
            //ESTO DE ACA LO TENGO QUE SACAR CUANDO ORDENE TODO
            String a = (String) vehiculosDisponibles.getSelectedItem();
            vehiculosDatos = consultor.vehiculosDatos(consultor.listarVehiculos(), a);
            String[] datos = consultor.separadorDatos(vehiculosDatos);
            Vehiculos vehiculo = new Vehiculos(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], "true");
            Reservas reserva = new Reservas(nombre.getText(), dni.getText(), telefono.getText(), direccion.getText(), datos[5], datos[0], dtf.format(LocalDateTime.now()), "true");
            
            if (!nombre.getText().equals("") || !dni.getText().equals("") || !telefono.getText().equals("") || !direccion.getText().equals("") || !a.equals("")) {
                vendedor.controladorReservas.agregadorDeReservas(reserva);
                administrador.borradorDeAutos(administrador.lectorDeStock(), administrador.buscadorDeStock(datos[0].toString()));
                administrador.agregadorDeStock(vehiculo);
            }

            panelPrincipalUsuario(usr, tipo);
        };
        aceptar.addActionListener(oyenteAceptar);

        ActionListener oyenteCancelar = (ActionEvent ae) -> {
            panelPrincipalUsuario(usr, tipo);
        };
        cancelar.addActionListener(oyenteCancelar);

    }

}
