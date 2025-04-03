package org.gerdoc.pixup.model;

public class Artista extends Catalogo{
    private String nombre;

    public Artista() {}

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
        return "Artista:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
