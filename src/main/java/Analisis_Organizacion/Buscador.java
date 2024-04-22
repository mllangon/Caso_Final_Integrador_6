package Analisis_Organizacion;

import java.util.List;

public class Buscador {

    public static int buscarNombre(List<String> nombres, String target) {
        return java.util.Collections.binarySearch(nombres, target);
    }
}
