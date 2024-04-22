package Gestion_Datos;

import java.util.ArrayList;
import java.util.List;

public class ListaPares<T, U> {
    private List<Pareja<T, U>> pares;

    public ListaPares() {
        this.pares = new ArrayList<>();
    }

    public void agregarPareja(Pareja<T, U> pareja) {
        pares.add(pareja);
    }

    public void eliminarPareja(Pareja<T, U> pareja) {
        pares.remove(pareja);
    }

    public void mostrarPares() {
        pares.forEach(System.out::println);
    }
}
