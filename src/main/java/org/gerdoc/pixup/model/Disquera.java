package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.ArtistaImpl;
import org.gerdoc.pixup.jdbc.impl.DiscoImpl;
import org.gerdoc.pixup.jdbc.impl.DisqueraImpl;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        DisqueraImpl disqueraImpl = new DisqueraImpl();
        List<Disquera> disqueraList = disqueraImpl.findAll();
        if (!disqueraList.isEmpty()){
            for (Disquera disquera:disqueraList){
                if(disquera.getNombre().equalsIgnoreCase(nombre)){
                    busqueda=true;
                }
            }
        }
        return busqueda;
    }
}
