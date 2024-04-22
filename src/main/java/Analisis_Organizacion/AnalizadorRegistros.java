package Analisis_Organizacion;

import java.util.List;
import java.util.stream.Collectors;

public class AnalizadorRegistros {

    public static List<String> filtrarRegistros(List<String> registros, String criterio) {
        return registros.stream()
                .filter(r -> r.contains(criterio))
                .collect(Collectors.toList());
    }
}
