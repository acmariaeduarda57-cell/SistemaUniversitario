/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Objects;

/**
 *
 * @author Maria Eduarda
 */
public class Aula implements Comparable<Aula> {
    private String codigo;
    private Edificio edificio;
    private String nivel;
    private String tipo;

    public Aula(String codigo, int i, Edificio edificio, String nivel) {
        this.codigo = codigo;
        this.edificio = edificio;
        this.nivel = nivel;
        this.tipo = "Teoria";
    }

    public Aula(String codigo,Edificio edificio, String nivel, String tipo){
       this(codigo, 0, edificio, nivel);
       this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "codigo='" + codigo + '\'' +
                ", edificio=" + edificio +
                ", nivel='" + nivel + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(codigo, aula.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public int compareTo(Aula o) {
        if (o == null) return 1;
    return this.codigo.compareTo(o.codigo);
    }

}
