/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Arbol.ArbolGeneral;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Maria Eduarda
 */
public class Edificio {
    private String nombre;
    private String direccion;
    private ArbolGeneral<String> estructura;

    public Edificio(String nombre){
        this.nombre = nombre;
        this.direccion = "";
        this.estructura = new ArbolGeneral<>(" ENTRADA PRINCIPAL");
    }

    public Edificio(String nombre,String direccion){
        this(nombre);
        this.direccion = direccion;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArbolGeneral<String> getEstructura() {
        return estructura;
    }


    //Agrega un nuevo nivel al edificio
    public boolean agregarNivel(String nivel, String padre){
        return estructura.insertar(padre, nivel);
    }

    public boolean agregarNivel(String nivel){
        return estructura.insertar("ENTRADA PRINCIPAL", nivel);
    }

    //Agrega un aula a un nivel especifico
    public boolean agregarAula(String nivel, String codigoAula) {
    // Verificar si el nivel existe, si no, crearlo
    if (estructura.buscarNodo(nivel) == null) {
        // Intentar crear el nivel bajo la entrada principal
        boolean nivelCreado = estructura.insertar("ENTRADA PRINCIPAL", nivel);
        if (!nivelCreado) {
            // Si no se pudo crear, intentar con "ENTRADA" (sin PRINCIPAL)
            nivelCreado = estructura.insertar("ENTRADA", nivel);
            if (!nivelCreado) {
                return false;
            }
        }
    }
    // Insertar el aula en el nivel
    return estructura.insertar(nivel, codigoAula);
}

    //Obtiene el camino desde la entrada hasta un aula especifica
    public List<String> getCamninoHastaAula(String codigoAula){
        return estructura.obtenerCamino(codigoAula);
    }


    public void mostrarEstructura(){
        System.out.println("ESTRUCTURA DEL EDIFICIO:"+ nombre.toUpperCase());
        estructura.imprimirEstructura();
    }

public void mostrarEstructuraCompleta() {
    System.out.println("=== ESTRUCTURA DEL EDIFICIO: " + nombre.toUpperCase() + " ===");
    System.out.println("Dirección: " + direccion);
    System.out.println("Estructura interna:");
    estructura.imprimirEstructura();
    System.out.println("=== FIN DE ESTRUCTURA ===");
}
    
    @Override
    public String toString() {
        return "Edificio{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Edificio edificio = (Edificio) o;
        return Objects.equals(nombre, edificio.nombre) && Objects.equals(direccion, edificio.direccion) && Objects.equals(estructura, edificio.estructura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccion, estructura);
    }
}
