/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Maria Eduarda
 */
public class Grafo<E> {
    private List<E> vertices;
    private int[][] matrizAdyacencia;
    private boolean dirigido;
    private boolean ponderado;
    private int capacidadMaxima;

    public Grafo(int capacidadMaxima, boolean dirigido, boolean ponderado){
        this.capacidadMaxima = capacidadMaxima;
        this.vertices = new ArrayList<>();
        this.matrizAdyacencia = new int[capacidadMaxima][capacidadMaxima];
        this.dirigido = dirigido;
        this.ponderado = ponderado;

        for(int i =0; i< capacidadMaxima; i++){
            for(int j= 0; j< capacidadMaxima; j++){
                if(ponderado){
                    matrizAdyacencia[i][j] =(i==j)?0: Integer.MAX_VALUE;
                } else{
                    matrizAdyacencia[i][j] =0;
                }
            }
        }
    }

    public Grafo(int capacidadMaxima,boolean dirigido){
        this(capacidadMaxima,dirigido, false);

    }
    public boolean insertarVertice(E vertice){
        if(vertices.size()>= capacidadMaxima) return false;
        if(vertices.contains(vertice)) return false;
        vertices.add(vertice);
        return true;
    }
    public boolean eliminarVertice(E vertice){
        int pos = vertices.indexOf(vertice);
        if(pos ==-1) return false;

        vertices.remove(pos);

        for(int i =pos;i< vertices.size(); i++){
            for(int j=0; j<capacidadMaxima; j++){
                matrizAdyacencia[i][j] = matrizAdyacencia[i+1][j];
            }
        }
        for(int i =0;i< vertices.size()+1;i++){
            for(int j= pos; j< vertices.size(); j++){
                 matrizAdyacencia[i][j]= matrizAdyacencia[i][j+1];
            }
        }
        return true;
    }

    public E getVertice(int pos){
        if(pos< 0|| pos>= vertices.size()) return null;
        return vertices.get(pos);
    }

    public int getPosicion(E vertice){
        return vertices.indexOf(vertice);
    }
    public List<E> getVertices(){
        return new ArrayList<>(vertices);
    }

    public int getNumVertices(){
        return vertices.size();
    }
    public boolean existeVertice(E vertice){
        return vertices.contains(vertice);
    }



    public boolean insertarArco(E origen, E destino, int peso){
        int posOrigen = vertices.indexOf(origen);
        int posDestino = vertices.indexOf(destino);

        if(posOrigen==-1|| posDestino ==-1) return false;

        if(ponderado){
            matrizAdyacencia[posOrigen][posDestino] = peso;
        } else{
            matrizAdyacencia[posOrigen][posDestino] = 1;
        }
        if(!dirigido){
            if(ponderado){
                matrizAdyacencia[posDestino][posOrigen]= peso;
            } else{
                matrizAdyacencia[posDestino][posOrigen]= 1;
            }
        }
        return true;
    }

    public boolean insertarArco(E origen, E destino){
        return insertarArco(origen, destino, 1);
    }

    public boolean eliminarArco(E origen, E destino){
        int posOrigen = vertices.indexOf(origen);
        int posDestino = vertices.indexOf(destino);

        if(posOrigen ==-1|| posDestino ==-1) return false;

        if(ponderado){
            matrizAdyacencia[posOrigen][posDestino]= Integer.MAX_VALUE;
        } else{
            matrizAdyacencia[posOrigen][posDestino] =0;
        }

        if(!dirigido){
            if(ponderado){
                matrizAdyacencia[posDestino][posOrigen]= Integer.MAX_VALUE;
            } else{
                matrizAdyacencia[posDestino][posOrigen]= 0;
            }
        }
        return true;
    }

    public boolean existeArco(E origen, E destino){
        int posOrigen = vertices.indexOf(origen);
        int posDestino = vertices.indexOf(destino);
        if(posOrigen ==-1|| posDestino ==-1) return false;

        if(ponderado){
            return matrizAdyacencia[posOrigen][posDestino]!= Integer.MAX_VALUE && matrizAdyacencia[posOrigen][posDestino] != 0;
        } else{
           return matrizAdyacencia[posOrigen][posDestino] !=0;
        }
    }

    public int getPeso(E origen,E destino){
        int posOrigen = vertices.indexOf(origen);
        int posDestino = vertices.indexOf(destino);
        if(posOrigen ==-1|| posDestino ==-1) return -1;
        return matrizAdyacencia[posOrigen][posDestino];
    }

    public List<E> getAdyacentes(E vertice){
        int pos = vertices.indexOf(vertice);
        if(pos==-1) return new ArrayList<>();

        List<E> adyacentes = new ArrayList<>();
        for(int i =0; i< vertices.size(); i++){
            if(ponderado){
                if(matrizAdyacencia[pos][i]!= Integer.MAX_VALUE && matrizAdyacencia[pos][i]!=0){
                    adyacentes.add(vertices.get(i));
                }
            } else{
                if(matrizAdyacencia[pos][i] !=0){
                    adyacentes.add(vertices.get(i));
                }
            }
        }
        return adyacentes;
    }
    public int getGrado(E vertice){
        return getAdyacentes(vertice).size();
    }

    public void imprimirMatriz(){
        System.out.println("Matriz de Adyacencia");
        System.out.println("   ");
        for(int i= 0; i< vertices.size(); i++){
            System.out.printf("%-6s",vertices.get(i));
        }
        System.out.println();

        for(int i =0; i< vertices.size(); i ++){
            System.out.printf("%-5", vertices.get(i));
            for(int j=0; j< vertices.size();j++){
                int valor= matrizAdyacencia[i][j];
                String mostrar;
                if(ponderado&& valor == Integer.MAX_VALUE){
                    mostrar = "...";
                } else if(!ponderado && valor ==0){
                    mostrar ="0";
                } else{
                    mostrar = String.valueOf(valor);
                }
                System.out.printf("%-6s", mostrar);
            }
            System.out.println();
        }

    }

}