package org.gerdoc.pixup.model;

import java.io.Serializable;

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
}

