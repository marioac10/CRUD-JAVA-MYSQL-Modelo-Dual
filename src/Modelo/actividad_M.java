/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Mario Ac
 */
public class actividad_M {
    private int idTema;
    private String nombretema;
    private String entregable;
    private int idUnidad;
    private String nombreUnidad;
    private String numeroAct;
    
    public String toString(){
        
        return this.nombretema;
        
    }
    
    public String getNumeroAct() {
        return numeroAct;
    }

    public void setNumeroAct(String numeroAct) {
        this.numeroAct = numeroAct;
    }
    
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getNombretema() {
        return nombretema;
    }

    public void setNombretema(String nombretema) {
        this.nombretema = nombretema;
    }

    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }
}
