package com.viajes.web.models;

import java.io.Serializable;

// Java Beans tiene que:
// implementar Serializable, 
// los atributos privados,
// constructor vacío

public class Cliente implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String contacto;

    //se define vacío ya que no se le otorgarán valores desde la app
    public Cliente(){
    
    }
    
    public int getId(){
            return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public String getContacto(){
        return contacto;
    }
    
    public void setContacto(String contacto){
        this.contacto = contacto;
    }
    }

