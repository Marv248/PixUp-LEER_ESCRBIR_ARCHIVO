package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.MunicipioImpl;

import java.io.Serializable;
import java.util.List;

public class Municipio extends Catalogo implements Serializable{
    private String nombre;

    public Municipio()
    {
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "Municipio:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        MunicipioImpl municipioimpl = null;
        List<Municipio>municipios = municipioimpl.findAll();
        if(!municipios.isEmpty()){
            for (Municipio municipio : municipios){
                if(municipio.getNombre().equalsIgnoreCase(nombre)){
                    busqueda = true;
                }
            }
        }

        return busqueda;
    }
}

