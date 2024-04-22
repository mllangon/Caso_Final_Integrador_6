package Gestion_Datos;

public class Pareja<T, U> {
    private T primerElemento;
    private U segundoElemento;

    public Pareja(T primerElemento, U segundoElemento) {
        this.primerElemento = primerElemento;
        this.segundoElemento = segundoElemento;
    }

    public T getPrimerElemento() {
        return primerElemento;
    }

    public void setPrimerElemento(T primerElemento) {
        this.primerElemento = primerElemento;
    }

    public U getSegundoElemento() {
        return segundoElemento;
    }

    public void setSegundoElemento(U segundoElemento) {
        this.segundoElemento = segundoElemento;
    }

    @Override
    public String toString() {
        return "(" + primerElemento + ", " + segundoElemento + ")";
    }
}
