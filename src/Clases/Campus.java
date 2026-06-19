/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Arbol.ABB;
import Grafo.Grafo;
import Grafo.AlgoritmoGrafo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maria Eduarda
 */
public class Campus {
    private String nombre;
    private Grafo<Edificio> redCampus;
    private ABB<Aula> indiceAulas;
    private AlgoritmoGrafo<Edificio> algoritmoGrafo;

    public Campus(String nombre, int maxEdificios){
        this.nombre = nombre;
        this.redCampus = new Grafo<Edificio>(maxEdificios, false, true);
        this.indiceAulas = new ABB<>();
        this.algoritmoGrafo = new AlgoritmoGrafo<Edificio>();

    }

    //OPERACIONES CON EDIFICIOS(GRAFO)
    public boolean agregarEdificio(Edificio edificio){
        return redCampus.insertarVertice(edificio);
    }

    public boolean eliminarEdificio(Edificio edificio){
        List<Aula> aulasAEliminar = new ArrayList<>();
        for (Aula aula : indiceAulas.inorden()){
            if(aula.getEdificio().equals(edificio)){
                aulasAEliminar.add(aula);
            }
        }
        for(Aula aula : aulasAEliminar){
            indiceAulas.eliminar(aula);
        }
        return redCampus.eliminarVertice(edificio);
    }

    public Edificio buscarEdificioPorNombre(String nombre){
        for(Edificio edificio : redCampus.getVertices()){
            if(edificio.getNombre().equalsIgnoreCase(nombre)){
                return edificio;
            }
        }
        return null;
    }

    public List<Edificio> listarEdificios(){
        return redCampus.getVertices();
    }

    public boolean conectarEdificios(Edificio origen, Edificio destino, int distanciaMetros){
        if(distanciaMetros <= 0) return false;
        return redCampus.insertarArco(origen, destino, distanciaMetros);
    }

    public boolean desconectarEdificios(Edificio origen, Edificio destino){
        return redCampus.eliminarArco(origen, destino);

    }

    public List<Edificio> getEdificiosAdyacentes(Edificio edificio){
        return redCampus.getAdyacentes(edificio);
    }
    //OEPACIONES CON AULAS(ARBOL)
    public boolean agregarAula(Aula aula) {
    if (aula == null || aula.getEdificio() == null) return false;
    if (!redCampus.existeVertice(aula.getEdificio())) return false;
    
    // Primero verificar que no exista
    if (buscarAula(aula.getCodigo()) != null) return false;
    
    indiceAulas.insertar(aula);
    return aula.getEdificio().agregarAula(aula.getNivel(), aula.getCodigo());
}

    public Aula buscarAula(String codigo) {
    if (codigo == null || codigo.isEmpty()) return null;
    
    // Buscar en el ABB
    Aula aulaBuscada = new Aula(codigo, 0, null, "");
    return indiceAulas.buscar(aulaBuscada);
}

    public boolean eliminarAula(String codigo){
        Aula aula = buscarAula(codigo);
        if(aula == null) return false;
        return indiceAulas.eliminar(aula);
    }

    public boolean modificarAula(String codigoViejo,String codigoNuevo, String nuevoTipo, String nuevoNivel){
        Aula aulaVieja = buscarAula(codigoViejo);
        if(aulaVieja==null) return false;

        Aula aulaNueva= new Aula(
                codigoNuevo !=null? codigoNuevo: aulaVieja.getCodigo(),
                aulaVieja.getEdificio(),
                nuevoNivel !=null? nuevoNivel: aulaVieja.getNivel(),
                nuevoTipo !=null? nuevoTipo: aulaVieja.getTipo()
        );
        indiceAulas.eliminar(aulaVieja);
        indiceAulas.insertar(aulaNueva);
        return true;
    }

    public List<Aula> listarTodasLasAulas(){
        return indiceAulas.inorden();
    }

    //Algoritmos de Grafo
    public List<Edificio> rutaMasCortaEntreEdificios(Edificio origen, Edificio destino){
        return algoritmoGrafo.obtenerCaminoMasCorto(redCampus, origen, destino);
    }

    public int distanciaEntreEdificios(Edificio origen, Edificio destino){
        Map<Edificio, Integer> distancias = algoritmoGrafo.dijkstra(redCampus, origen);
        return distancias.getOrDefault(destino, -1);

    }

    public List<Edificio> recorridoBFS(Edificio inicio){
        return algoritmoGrafo.bfs(redCampus, inicio);
    }
    public List<Edificio> recorridoDFS(Edificio inicio){
        return algoritmoGrafo.dfs(redCampus, inicio);
    }

    public String obtenerRutaCompleta(String codigoOrigen,String codigoDestino){
        Aula aulaOrigen = buscarAula(codigoOrigen);
        Aula aulaDestino = buscarAula(codigoDestino);

        if(aulaOrigen== null){
            return "ERROR: Aula origen"+ codigoOrigen+"no encontrada";

        }
        if(aulaDestino== null){
            return "ERROR: Aula destino"+ codigoDestino+"no encontrada";

        }
        Edificio edificioOrigen = aulaOrigen.getEdificio();
        Edificio edificioDestino = aulaDestino.getEdificio();

        StringBuilder resultado = new StringBuilder();
        resultado.append("RUTA COMPLETA:").append(codigoOrigen).append("---").append(codigoDestino);


        //SALIDA DEL EDIFICIO ORIGEN
        resultado.append("1- SALIDA DEL EDIFICIO:").append(edificioOrigen.getNombre());
        List<String> caminoOrigen = edificioOrigen.getCamninoHastaAula(codigoOrigen);
        if(caminoOrigen!=null && !caminoOrigen.isEmpty()){
            resultado.append(" Desde aula").append(codigoOrigen).append("hasta la salida:\n");
            for(int i= caminoOrigen.size() -1; i>= 0; i--){
                resultado.append("    .").append(caminoOrigen.get(i)).append("\n");
            }
        } else{
            resultado.append("  (No se pudo determinar el camino interno)\n");
        }
        resultado.append("\n");

        //Camino entre edificios
        resultado.append("2- Camino entre Edificios\n");
        if(edificioOrigen.equals(edificioDestino)){
            resultado.append(" Mismo edificio.Sin desplazamiento externo.\n");
        } else{
            List<Edificio> rutaEdificios = rutaMasCortaEntreEdificios(edificioOrigen, edificioDestino);
            int distanciaTotal = distanciaEntreEdificios(edificioOrigen, edificioDestino);

            if(rutaEdificios != null && !rutaEdificios.isEmpty()){
                resultado.append("Ruta:");
                for(int i= 0; i< rutaEdificios.size(); i ++){
                    resultado.append(rutaEdificios.get(i).getNombre());
                    if(i< rutaEdificios.size()-1) resultado.append("---");
                }
                resultado.append("Distancia total:").append(distanciaTotal).append("metros.\n");
            } else{
                resultado.append("No hay ruta disponible entre los edificios.\n");
            }
        }
        resultado.append("\n");


        //Entrada al edificio destino

        resultado.append("3- Entrada al edificio:").append(edificioDestino.getNombre()).append("\n");
        List<String> caminoDestino = edificioDestino.getCamninoHastaAula(codigoDestino);
        if(caminoDestino != null && !caminoDestino.isEmpty()){
            resultado.append("Desde la entrada hasta el aula").append(codigoDestino).append(":\n");
            for(String paso: caminoDestino){
                resultado.append("   .").append(paso).append("\n");
            }
        } else{
            resultado.append("  (No se pudo determinar el camino interno)\n");
        }
        resultado.append("\n");

        resultado.append("Ruta calculada exitosamente.\n");

        return  resultado.toString();

    }

    public void mostrarMapaCampus(){
        System.out.println("MAPA DEL CAMPUS");
        System.out.println("Nombre:"+ nombre);
        System.out.println("Número de edificios:"+ redCampus.getNumVertices());
        System.out.println("Número de aulas inexadas:"+ indiceAulas.getTamaño());
        System.out.println("Tipo de grafo: No dirigido, ponderado");
        System.out.println("Estructura interna: Arbol General por Edificio");
        System.out.println("Indice de aulas: ABB");
    }

    public String getNombre() {
        return nombre;
    }
    public Grafo<Edificio> getRedCampus(){
        return  redCampus;
    }

    public ABB<Aula> getIndiceAulas(){
        return indiceAulas;
    }
}