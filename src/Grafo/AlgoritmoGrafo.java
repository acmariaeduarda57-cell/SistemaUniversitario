/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Maria Eduarda
 */
public class AlgoritmoGrafo<E>{
    public Map<E, Integer> dijkstra(Grafo<E> grafo, E origen){
        if(!grafo.existeVertice(origen)) return new HashMap<>();

        Map<E, Integer> distancias = new HashMap<>();
        Map<E,E> previos = new HashMap<>();
        Set<E> noVisitados = new HashSet<>();

        for(E vertice : grafo.getVertices()){
            distancias.put(vertice, Integer.MAX_VALUE);
            previos.put(vertice, null);
            noVisitados.add(vertice);
        }
        distancias.put(origen, 0);

        while (!noVisitados.isEmpty()){
            E actual = null;
            int minDist = Integer.MAX_VALUE;
            for(E vertice : noVisitados){
                if(distancias.get(vertice)< minDist){
                    minDist = distancias.get(vertice);
                    actual = vertice;
                }
            }
            if( actual== null|| minDist==Integer.MAX_VALUE) break;

            noVisitados.remove(actual);

            for(E adyacente: grafo.getAdyacentes(actual)){
                if(!noVisitados.contains(adyacente)) continue;

                int peso = grafo.getPeso(actual,adyacente);
                int nuevaDist = distancias.get(actual)+ peso;

                if(nuevaDist< distancias.get(adyacente)){
                    distancias.put(adyacente, nuevaDist);
                    previos.put(adyacente, actual);
                }
            }
        }
        return distancias;
    }
    public List<E> obtenerCaminoMasCorto(Grafo<E> grafo, E origen, E destino){
        if(!grafo.existeVertice(origen)|| !grafo.existeVertice(destino)){
            return new ArrayList<>();
        }
        Map<E, Integer> distancias = new HashMap<>();
        Map<E,E> previos = new HashMap<>();
        Set<E> noVisitados = new HashSet<>();

        for(E vertice : grafo.getVertices()){
            distancias.put(vertice, Integer.MAX_VALUE);
            previos.put(vertice, null);
            noVisitados.add(vertice);
        }
        distancias.put(origen, 0);

        while (!noVisitados.isEmpty()){
            E actual = null;
            int minDist = Integer.MAX_VALUE;
            for(E vertice : noVisitados){
                if(distancias.get(vertice)< minDist){
                    minDist = distancias.get(vertice);
                    actual = vertice;
                }
            }
            if( actual== null|| actual.equals(destino)) break;

            noVisitados.remove(actual);

            for(E adyacente: grafo.getAdyacentes(actual)){
                if(!noVisitados.contains(adyacente)) continue;

                int peso = grafo.getPeso(actual,adyacente);
                int nuevaDist = distancias.get(actual)+ peso;

                if(nuevaDist< distancias.get(adyacente)){
                    distancias.put(adyacente, nuevaDist);
                    previos.put(adyacente, actual);
                }
            }
        }
        List<E> camino = new ArrayList<>();
        E actual = destino;
        while (actual != null){
            camino.add(0, actual);
            actual = previos.get(actual);
        }
        return  camino;
    }

    public List<E> bfs(Grafo<E> grafo, E origen){
        if(!grafo.existeVertice(origen)) return new ArrayList<>();

        List<E> recorrido = new ArrayList<>();
        Set<E> visitados = new HashSet<>();
        Queue<E> cola = new LinkedList<>();

        visitados.add(origen);
        cola.offer(origen);

        while(!cola.isEmpty()){
            E actual = cola.poll();
            recorrido.add(actual);

            for(E adyacente: grafo.getAdyacentes(actual)){
                if(!visitados.contains(adyacente)){
                    visitados.add(adyacente);
                    cola.offer(adyacente);
                }
            }
        }
        return recorrido;
    }
    public List<E> bfsCompleto(Grafo<E> grafo){
        List<E> recorrido = new ArrayList<>();
        Set<E> visitados = new HashSet<>();

        for(E vertice : grafo.getVertices()){
            if(!visitados.contains(vertice)){
                Queue<E> cola = new LinkedList<>();
                visitados.add(vertice);
                cola.offer(vertice);

                while(!cola.isEmpty()){
                    E actual = cola.poll();
                    recorrido.add(actual);

                    for(E adyacente: grafo.getAdyacentes(actual)){
                        if(!visitados.contains(adyacente)){
                            visitados.add(adyacente);
                            cola.offer(adyacente);
                        }
                    }
                }
            }
        }
        return recorrido;
    }

    public List<E> dfs(Grafo<E> grafo, E origen){
        if(!grafo.existeVertice(origen)) return new ArrayList<>();

        List<E> recorrido = new ArrayList<>();
        Set<E> visitados = new HashSet<>();
        Stack<E> pila = new Stack<>();

        pila.push(origen);

        while (!pila.isEmpty()){
            E actual = pila.pop();
            if(!visitados.contains(actual)){
                visitados.add(actual);
                recorrido.add(actual);

                for(E adyacente: grafo.getAdyacentes(actual)){
                    if(!visitados.contains(adyacente)){
                        pila.push(adyacente);
                    }
                }
            }
        }
        return recorrido;
    }

    public List<E> dfsRec(Grafo<E> grafo, E origen){
        List<E> recorrido = new ArrayList<>();
        Set<E> visitados = new HashSet<>();
        dfsRecAux(grafo, origen, visitados, recorrido);
        return recorrido;
    }
    public void dfsRecAux(Grafo<E> grafo, E actual, Set<E> visitados, List<E> recorrido){
        if(visitados.contains(actual)) return;
        visitados.add(actual);
        recorrido.add(actual);

        for(E adyacente: grafo.getAdyacentes(actual)){
            dfsRecAux(grafo,adyacente,visitados,recorrido);
        }
    }
}
