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
public class ABB<E extends Comparable<E>>{
    private NodoABB<E> raiz;
    private int tamaño;

    public ABB(){
        this.raiz= null;
        this.tamaño = 0;
    }

    public boolean isEmpty(){
        return raiz == null;
    }
    public int getTamaño(){
        return tamaño;
    }

    public void insertar(E dato){
        if(dato ==null) return;
        raiz = insertarRec(raiz, dato);
        tamaño ++;
    }

    public NodoABB<E> insertarRec(NodoABB<E> nodo, E dato){
        if(nodo == null){
            return new NodoABB<>(dato);
        }

        int comparacion = dato.compareTo(nodo.getDato());
        if(comparacion < 0 ){
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(),dato));
        }  else if( comparacion > 0){
            nodo.setDerecho(insertarRec(nodo.getDerecho(),dato));
        }
        return nodo;
    }

    public E buscar(E dato){
        NodoABB<E> nodo = buscarRec(raiz, dato);
        return nodo != null? nodo.getDato() : null;
    }

    private NodoABB<E> buscarRec(NodoABB<E> nodo, E dato){
        if(nodo== null) return null;

        int comparacion = dato.compareTo(nodo.getDato());
        if(comparacion ==0) return nodo;
        if(comparacion <0) return buscarRec(nodo.getIzquierdo(),dato);
        return buscarRec(nodo.getDerecho(), dato);
    }
    public boolean existe(E dato){
        return buscar(dato) != null;
    }


    public boolean eliminar(E dato){
        if(!existe(dato)) return false;
        raiz = eliminarRec( raiz, dato);
        tamaño --;
        return true;
    }

    private NodoABB<E> eliminarRec(NodoABB<E> nodo, E dato){
        if(nodo == null) return null;

        int comparacion = dato.compareTo(nodo.getDato());

        if(comparacion <0){
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), dato));
        } else if( comparacion > 0) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), dato));
        } else {
            if(nodo.getIzquierdo() == null){
                return nodo.getDerecho();
            }
            if(nodo.getDerecho()== null){
                return nodo.getIzquierdo();
            }
            NodoABB<E> sucesor = encontrarMin(nodo.getDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getDato()));
        }
        return nodo;

    }
    private NodoABB<E> encontrarMin(NodoABB<E> nodo){
        NodoABB<E> actual = nodo;
        while(actual.getIzquierdo()!= null){
            actual = actual.getIzquierdo();
        }
        return actual;
    }
    public boolean modificar(E datoViejo, E datoNuevo){
        if(datoViejo==null|| datoNuevo == null) return false;
        if(!existe(datoViejo)) return false;

        eliminar(datoViejo);
        eliminar(datoNuevo);
        return true;
    }

    //Preorden
    public List<E> preorden(){
        List<E> resultado = new ArrayList<>();
        preordenRecursivo(raiz, resultado);
        return resultado;
    }
    private void preordenRecursivo(NodoABB<E> nodo, List<E> resultado){
        if(nodo == null) return;

        resultado.add(nodo.getDato());
        preordenRecursivo(nodo.getIzquierdo(), resultado);
        preordenRecursivo(nodo.getDerecho(), resultado);

    }

    //Inorden
    public List<E> inorden(){
        List<E> resultado = new ArrayList<>();
        inordenRecursivo(raiz, resultado);
        return resultado;
    }
    private void inordenRecursivo(NodoABB<E> nodo, List<E> resultado){
        if(nodo == null) return;

        inordenRecursivo(nodo.getIzquierdo(), resultado);
        resultado.add(nodo.getDato());
        inordenRecursivo(nodo.getDerecho(), resultado);

    }

    //Postorden
    public List<E> postorden(){
        List<E> resultado = new ArrayList<>();
        postordenRecursivo(raiz, resultado);
        return resultado;
    }
    private void postordenRecursivo(NodoABB<E> nodo, List<E> resultado){
        if(nodo == null) return;

        postordenRecursivo(nodo.getIzquierdo(), resultado);
        postordenRecursivo(nodo.getDerecho(), resultado);
        resultado.add(nodo.getDato());

    }

    public void imprimirEstructura(){
        imprimirEstructuraRec(raiz, 0);
    }

    public void imprimirEstructuraRec(NodoABB<E> nodo, int nivel){
        if(nodo == null) return;
        String indentacion = " ".repeat(nivel);
        System.out.println(indentacion +"---"+ nodo.getDato());
        imprimirEstructuraRec(nodo.getIzquierdo(), nivel +1);
        imprimirEstructuraRec(nodo.getDerecho(),nivel +1);
    }
}
