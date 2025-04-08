package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.ColoniaImp;

import java.io.Serializable;
import java.util.List;

public class Colonia extends Catalogo implements Serializable {

    private String nombre;

    public Colonia() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "Colonia:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        ColoniaImp coloniaImp = null;
        List<Colonia> colonias = coloniaImp.findAll();
        if(!colonias.isEmpty()){
            for (Colonia colonia : colonias){
                if(colonia.getNombre().equalsIgnoreCase(nombre)){
                    busqueda = true;
                }
            }
        }
        else busqueda = false;

        if(!busqueda) System.out.println("Colonia no encontrada \u2339");
        return busqueda;
    }
}
