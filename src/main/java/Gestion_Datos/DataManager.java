package Gestion_Datos;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<Pareja> parejas;

    public DataManager() {
        this.parejas = new ArrayList<>();
    }

    public void addPareja(Pareja pareja) {
        parejas.add(pareja);
    }

    public boolean removePareja(Pareja pareja) {
        return parejas.remove(pareja);
    }

    public List<Pareja> getParejas() {
        return parejas;
    }

    public void clearParejas() {
        parejas.clear();
    }
}
