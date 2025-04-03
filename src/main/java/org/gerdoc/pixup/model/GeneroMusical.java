package org.gerdoc.pixup.model;

import java.io.Serializable;

public class GeneroMusical extends Catalogo implements Serializable {
    private String nombre;

    public GeneroMusical() {}

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
        return "Genero Musical:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
