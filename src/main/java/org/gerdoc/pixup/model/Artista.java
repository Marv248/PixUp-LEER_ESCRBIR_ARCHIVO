package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.ArtistaImpl;
import org.gerdoc.pixup.jdbc.impl.GeneroMusicalImpl;

import java.util.List;

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

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        ArtistaImpl artistaImpl = new ArtistaImpl();
        List<Artista> artistaList = artistaImpl.findAll();
        if (!artistaList.isEmpty()){
            for (Artista artista:artistaList){
                if(artista.getNombre().equalsIgnoreCase(nombre)){
                    busqueda=true;
                }
            }
        }
        return busqueda;
    }
}
