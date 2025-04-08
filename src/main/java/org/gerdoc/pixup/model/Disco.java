package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.ArtistaImpl;
import org.gerdoc.pixup.jdbc.impl.DiscoImpl;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        DiscoImpl discoImpl = new DiscoImpl();
        List<Disco> discos = discoImpl.findAll();
        if (!discos.isEmpty()){
            for (Disco disco:discos){
                if(disco.getNombre().equalsIgnoreCase(nombre)){
                    busqueda=true;
                }
            }
        }
        return busqueda;
    }
}
