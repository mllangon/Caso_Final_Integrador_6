package Indexacion_Visualizacion;

import java.util.Collections;
import java.util.List;

public class VisualizadorArchivos {
    public void ordenarYMostrarArchivos(List<String> rutas) {
        Collections.sort(rutas);
        rutas.forEach(System.out::println);
    }
}
