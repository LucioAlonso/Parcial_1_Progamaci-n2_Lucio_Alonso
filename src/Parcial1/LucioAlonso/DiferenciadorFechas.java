package Parcial1.LucioAlonso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que realiza el control de las fechas.
 *
 * @author Lucio Alonso
 */
public class DiferenciadorFechas {

    /**
     * Realiza el cambio de una variable String a una variable de tipo Date.
     *
     * @param fecha Representa la fecha a cambiar.
     * @return Devuelve un dato de tipo Date.
     * @throws ParseException Almacena un error.
     */
    public Date stringaFecha(String fecha) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha1 = formato.parse(fecha);

        return fecha1;
    }

    /**
     * Realiza el calculo de diferencia de dias.
     *
     * @param fechaInicio Representa la fecha de inicio.
     * @param fechaFin Representa la fecha final.
     * @return Devuelve un entero con la diferencias de dias.
     * @throws ParseException Almacena un error.
     */
    public int diferenciarDias(String fechaInicio, String fechaFin) throws ParseException {
        int dif = 1;

        Date fechaIncialdate = stringaFecha(fechaInicio);
        Date fechaFinaldate = stringaFecha(fechaFin);

        dif = (int) ((fechaFinaldate.getTime() - fechaIncialdate.getTime()) / (1000 * 60 * 60 * 24));

        if (dif == 0) {
            return 1;
        }
        return dif;
    }
}
