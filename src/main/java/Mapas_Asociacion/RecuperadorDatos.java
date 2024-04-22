package Mapas_Asociacion;

import java.util.Map;

public class RecuperadorDatos {
    private Map<String, String> mapa;

    public RecuperadorDatos(Map<String, String> mapa) {
        this.mapa = mapa;
    }

    public String buscarDato(String clave) {
        return mapa.getOrDefault(clave, "Dato no encontrado");
    }
}
