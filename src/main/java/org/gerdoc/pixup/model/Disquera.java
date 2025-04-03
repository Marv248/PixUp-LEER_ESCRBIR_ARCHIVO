package org.gerdoc.pixup.model;

import java.io.Serializable;

public class Disquera extends Catalogo implements Serializable {
    private String nombre;

    public Disquera()
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
        return "Disquera:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
