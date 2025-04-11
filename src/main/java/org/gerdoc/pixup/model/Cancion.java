package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.ArtistaImpl;
import org.gerdoc.pixup.jdbc.impl.CancionImpl;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        CancionImpl cancionImpl = new CancionImpl();
        List<Cancion> cancionList = cancionImpl.findAll();
        if (!cancionList.isEmpty()){
            for (Cancion cancion:cancionList){
                if(cancion.getNombre().equalsIgnoreCase(nombre)){
                    busqueda=true;
                }
            }
        }
        return busqueda;
    }

    @Override
    public Integer buscarById(Integer id) {
        return 0;
    }
}
