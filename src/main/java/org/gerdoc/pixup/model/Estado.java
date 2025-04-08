package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;

import java.io.Serializable;
import java.util.List;

public class Estado extends Catalogo  implements Serializable
{
    private String nombre;

    public Estado()
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
    public boolean buscar(String nombreEstado) {
        boolean busqueda = false;
        EstadoJdbcImpl estadoJdbc = new EstadoJdbcImpl();
        List<Estado> estados = estadoJdbc.findAll();
        if (!estados.isEmpty()) {
            for (Estado estado : estados) {
                if (estado.getNombre().equalsIgnoreCase(nombreEstado)) {
                    busqueda = true;
                }
            }
        }
        return busqueda;
    }

    @Override
    public String toString()
    {
        return "Estado:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
