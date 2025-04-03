package org.gerdoc.pixup.model;

import java.io.Serializable;

public class Cancion extends Catalogo implements Serializable {
    private String nombre;

    public Cancion() {}

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
        return "Cancion:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
