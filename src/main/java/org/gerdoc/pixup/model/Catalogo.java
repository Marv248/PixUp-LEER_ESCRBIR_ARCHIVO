package org.gerdoc.pixup.model;

import java.io.Serializable;

public abstract class Catalogo implements Serializable {
    protected Integer id;
    protected String nombre;
    protected String nombreTabla;
    protected Catalogo catalogo;

    public Catalogo() {}

    public Catalogo getInstance(){
        if (catalogo!=null){
            catalogo = new Catalogo() {
                @Override
                public boolean buscar(String nombre) {
                    return false;
                }

                @Override
                public Integer buscarById(Integer id) {
                    return 0;
                }
            };
        }
        return catalogo;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    @Override
    public String toString()
    {
        return "Catalogo:\n" +
                "\tid=" + id;
    }

    public abstract boolean buscar(String nombre);
    public abstract Integer buscarById(Integer id);
}