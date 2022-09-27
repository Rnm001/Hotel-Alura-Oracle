package modelodatos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class comparador {
    public static void main(String[] args) {

        // simulacion datos obtenidos de los input
        // debe tener el formato aaaa-mm-dd
        String txtFechaE = "2019-05-05";
        String txtFechaS = "2019-05-10";


        // se tienen que convertir a LocalDate
        LocalDate dateEntrada = LocalDate.parse(txtFechaE);
        LocalDate dateSalida = LocalDate.parse(txtFechaS);



        // el metodod ChronoUnit.DAYS.between() recibe parametros que implementen la interfece
        // Temporal LocalDate es una clase que implementa esa interfaz
        Long duracionEstadia =  ChronoUnit.DAYS.between(dateEntrada, dateSalida);
        short valorEstadia = 2000;
        System.out.println(duracionEstadia*valorEstadia);
        

    }
}