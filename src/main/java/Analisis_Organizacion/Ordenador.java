package Analisis_Organizacion;

import java.util.Collections;
import java.util.List;

public class Ordenador {

    public static void ordenarVentas(List<String> ventas, boolean ascendente) {
        if (ascendente) {
            Collections.sort(ventas);
        } else {
            ventas.sort(Collections.reverseOrder());
        }
    }
}
