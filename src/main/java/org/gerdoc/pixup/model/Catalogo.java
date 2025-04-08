package org.gerdoc.pixup.model;

import java.io.Serializable;

public abstract class Catalogo implements Serializable {
    protected Integer id;

    public Catalogo() {}

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Catalogo:\n" +
                "\tid=" + id;
    }

    public abstract boolean buscar(String nombre);
}