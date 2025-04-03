package org.gerdoc.pixup.model;

import java.io.Serializable;

public class Disco extends Catalogo implements Serializable {
    private String nombre;

    public Disco() {}

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
        return "Disco:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
