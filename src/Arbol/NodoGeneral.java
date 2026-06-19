/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Eduarda
 */
public class NodoGeneral<E> {
    private E dato;
    private List<NodoGeneral<E>> hijos;

    public NodoGeneral(E dato){
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public List<NodoGeneral<E>> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoGeneral<E>> hijos) {
        this.hijos = hijos;
    }
    public void agregarHijo(E datoHijo){
        hijos.add( new NodoGeneral<>(datoHijo));

    }
    public void agregarHijo(NodoGeneral<E> hijo){
        hijos.add(hijo);

    }
    public boolean esHoja(){
        return hijos.isEmpty();
    }

    public boolean eliminarHijo(NodoGeneral<E> hijo){
        return this.hijos.remove(hijo);
    }
    public int getGrado(){
        return hijos.size();
    }

    @Override
    public String toString() {
        return "NodoGeneral{" +
                "dato=" + dato +
                ", hijos=" + hijos +
                '}';
    }
}
