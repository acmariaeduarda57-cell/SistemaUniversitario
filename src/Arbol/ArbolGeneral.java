/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Maria Eduarda
 */
public class ArbolGeneral<E>{
    private NodoGeneral<E> raiz;

    public ArbolGeneral(){
        this.raiz = null;
    }
    public ArbolGeneral(E datoRaiz){
        this.raiz = new NodoGeneral<>(datoRaiz);
    }
    public NodoGeneral<E> getRaiz(){
        return raiz;
    }
    public boolean isEmpty(){
        return raiz == null;
    }

    //Recorrido BFS
    public List<E> recorridoBFS(){
        List<E> resultado = new ArrayList<>();
        if(raiz == null) return resultado;

        Queue<NodoGeneral<E>> cola = new LinkedList<>();
        cola.offer(raiz);

        while(!cola.isEmpty()){
            NodoGeneral<E> actual = cola.poll();
            resultado.add(actual.getDato());

            for(NodoGeneral<E> hijo: actual.getHijos()){
                cola.offer(hijo);
            }
        }
        return resultado;
    }

    //Recorrido DFS
    public List<E> recorridoDFS(){
        List<E> resultado = new ArrayList<>();
        if(raiz == null) return resultado;

        Stack<NodoGeneral<E>> pila = new Stack<>();
        pila.push(raiz);

        while(!pila.isEmpty()){
            NodoGeneral<E> actual = pila.pop();
            resultado.add(actual.getDato());

            List<NodoGeneral<E>>hijos = actual.getHijos();
            for(int i = hijos.size()-1;i>=0; i--){
                pila.push(hijos.get(i));
            }
        }
        return resultado;
    }

    public boolean buscar(E dato){
        return buscarRecursivo(raiz, dato);

    }

    private boolean buscarRecursivo(NodoGeneral<E> nodo,E dato){
        if(nodo==null) return false;
        if(nodo.getDato().equals(dato)) return true;

        for(NodoGeneral<E>hijo: nodo.getHijos()){
            if(buscarRecursivo(hijo,dato)){
                return true;
            }
        }
        return false;
    }
    public NodoGeneral<E> buscarNodo(E dato){
        return buscarNodoRecursivo(raiz, dato);
    }
    private NodoGeneral<E>  buscarNodoRecursivo(NodoGeneral<E> nodo,E dato){
        if(nodo==null) return null;
        if(nodo.getDato().equals(dato)) return nodo;

        for(NodoGeneral<E>hijo: nodo.getHijos()){
            NodoGeneral<E> encontrado = buscarNodoRecursivo(hijo, dato);
            if(encontrado!= null) return encontrado;
        }
        return null;
    }

    public boolean insertar(E datoPadre,E datoHijo){
        // Si el árbol está vacío, crear raíz con el dato padre
    if (raiz == null) {
        raiz = new NodoGeneral<>(datoPadre);
        raiz.agregarHijo(datoHijo);
        return true;
    }
    
    // Buscar el nodo padre
    NodoGeneral<E> padre = buscarNodo(datoPadre);
    if (padre != null) {
        // Verificar que el hijo no exista ya
        for (NodoGeneral<E> hijo : padre.getHijos()) {
            if (hijo.getDato().equals(datoHijo)) {
                return false; // El hijo ya existe
            }
        }
        padre.agregarHijo(datoHijo);
        return true;
    }
    return false;
    }

    public void insertarRaiz(E dato){
        if(raiz==null){
            raiz= new NodoGeneral<>(dato);
        }
    }
    public List<E> obtenerCamino(E dato){
        List<E> camino = new ArrayList<>();
        if(obtenerCaminoRec(raiz, dato, camino)){
            return camino;
        }
        return null;
    }

    public boolean obtenerCaminoRec(NodoGeneral<E> nodo, E dato, List<E> camino){
        if(nodo == null) return false;
        camino.add(nodo.getDato());
        if(nodo.getDato().equals(dato)) return true;

        for(NodoGeneral<E> hijo : nodo.getHijos()){
            if(obtenerCaminoRec(hijo, dato, camino)) return true;
        }
        camino.remove(camino.size()-1);
        return false;
    }

    public int getAltura(){
        return getAlturaRec(raiz);
    }

    public int getAlturaRec(NodoGeneral<E> nodo){
        if(nodo == null|| nodo.esHoja()) return 0;
        int maxAltura = 0;
        for(NodoGeneral<E> hijo: nodo.getHijos()){
            maxAltura = Math.max(maxAltura, 1+ getAlturaRec(hijo));
        }
        return maxAltura;
    }

    public void imprimirEstructura(){
        imprimirEstructuraRec(raiz, 0);
    }

    public void imprimirEstructuraRec(NodoGeneral<E> nodo, int nivel){
        if(nodo == null) return;
        String indentacion = " ".repeat(nivel);
        System.out.println(indentacion +"---"+ nodo.getDato());
        for(NodoGeneral<E> hijo: nodo.getHijos()){
            imprimirEstructuraRec(hijo, nivel +1);
        }
    }


}
