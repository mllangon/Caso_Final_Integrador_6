package Mapas_Asociacion;

import java.util.HashMap;
import java.util.Map;

public class GestorMapas {
    private Map<String, String> mapaDatos;

    public GestorMapas() {
        this.mapaDatos = new HashMap<>();
    }

    public void agregarAsociacion(String clave, String valor) {
        mapaDatos.put(clave, valor);
    }

    public void eliminarAsociacion(String clave) {
        mapaDatos.remove(clave);
    }

    public String recuperarValor(String clave) {
        return mapaDatos.getOrDefault(clave, "No encontrado");
    }
}
