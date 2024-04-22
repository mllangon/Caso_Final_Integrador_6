package Indexacion_Visualizacion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IndexadorArchivos {
    private List<String> listaRutas;

    public IndexadorArchivos() {
        this.listaRutas = new ArrayList<>();
    }

    public void indexarDirectorio(File directorio) {
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        listaRutas.add(archivo.getAbsolutePath());
                    } else if (archivo.isDirectory()) {
                        indexarDirectorio(archivo);  // Llamada recursiva para subdirectorios
                    }
                }
            }
        }
    }

    public List<String> getListaRutas() {
        return listaRutas;
    }
}
