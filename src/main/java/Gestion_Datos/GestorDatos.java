package Gestion_Datos;

public class GestorDatos<T, U> {
    private ListaPares<T, U> listaPares;

    public GestorDatos() {
        this.listaPares = new ListaPares<>();
    }

    public void agregarPareja(T primerElemento, U segundoElemento) {
        Pareja<T, U> nuevaPareja = new Pareja<>(primerElemento, segundoElemento);
        listaPares.agregarPareja(nuevaPareja);
        System.out.println("Pareja agregada: " + nuevaPareja);
    }

    public void eliminarPareja(T primerElemento, U segundoElemento) {
        Pareja<T, U> parejaAEliminar = new Pareja<>(primerElemento, segundoElemento);
        listaPares.eliminarPareja(parejaAEliminar);
        System.out.println("Pareja eliminada: " + parejaAEliminar);
    }

    public void mostrarPares() {
        System.out.println("Listando todas las parejas:");
        listaPares.mostrarPares();
    }
}
