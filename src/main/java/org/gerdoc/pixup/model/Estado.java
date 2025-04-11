package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;

import java.io.Serializable;
import java.util.List;

public class Estado extends Catalogo  implements Serializable
{
    Estado estado;
    String nombreTabla="tbl_estado";

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }


    @Override
    public Catalogo getInstance() {
        if(estado == null){
            estado = new Estado();
        }
        return estado;
    }

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
    public Integer buscarById(Integer id) {
        int i = -1;
        EstadoJdbcImpl estadoImpl = new EstadoJdbcImpl();
        List<Estado> estadoList = estadoImpl.findAll();

        if (estadoList != null && !estadoList.isEmpty()) {
            for (Estado estado : estadoList) {
                if (estado.getId() != null && estado.getId().equals(id)) {
                    i = id;
                    break;
                }
            }
        }
        return i;
    }



    @Override
    public String toString()
    {
        return "Estado:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre;
    }
}
