/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

/**
 *
 * @author Maria Eduarda
 */
public class NodoABB <E extends Comparable<E>>{
    private E dato;
    private NodoABB<E> izquierdo;
    private NodoABB<E> derecho;

    public NodoABB(E dato){
        this.dato = dato;
        this.izquierdo= izquierdo;
        this.derecho = derecho;

    }
    public E getDato(){
        return dato;
    }

    public void setDato(E dato){
        this.dato = dato;
    }

    public NodoABB<E> getIzquierdo(){
        return izquierdo;
    }

    public void setIzquierdo(NodoABB<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB<E> derecho) {
        this.derecho = derecho;
    }
    public boolean esHoja(){
        return izquierdo ==null && derecho== null;
    }

    @Override
    public String toString() {
        return "NodoABB{" +
                "dato=" + dato +
                ", izquierdo=" + izquierdo +
                ", derecho=" + derecho +
                '}';
    }
}
