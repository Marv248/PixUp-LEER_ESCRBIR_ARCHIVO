package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.GeneroMusicalImpl;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        GeneroMusicalImpl generoMusicalImpl = new GeneroMusicalImpl();
        List<GeneroMusical> generoMusicalList = generoMusicalImpl.findAll();
        if (!generoMusicalList.isEmpty()){
            for (GeneroMusical genero:generoMusicalList){
                if(genero.getNombre().equalsIgnoreCase(nombre)){
                    busqueda=true;
                }
            }
        }
        return busqueda;
    }
}
