package org.gerdoc.pixup.model;

import java.io.Serializable;

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
}
