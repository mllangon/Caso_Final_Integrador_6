// Pareja.java
package Gestion_Datos;

public class Pareja {
    private int primerElemento;
    private int segundoElemento;

    public Pareja(int primerElemento, int segundoElemento) {
        this.primerElemento = primerElemento;
        this.segundoElemento = segundoElemento;
    }

    public int getPrimerElemento() {
        return primerElemento;
    }

    public void setPrimerElemento(int primerElemento) {
        this.primerElemento = primerElemento;
    }

    public int getSegundoElemento() {
        return segundoElemento;
    }

    public void setSegundoElemento(int segundoElemento) {
        this.segundoElemento = segundoElemento;
    }

    @Override
    public String toString() {
        return "(" + primerElemento + ", " + segundoElemento + ")";
    }
}
