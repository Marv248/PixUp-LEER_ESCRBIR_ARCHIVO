package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.ArtistaImpl;

import java.io.Serializable;

public class Artista extends Catalogo implements Serializable {
    private Integer id;
    private String nombre;

    @Override
    public boolean buscar(String nombre) {
        ArtistaImpl artistaImpl = new ArtistaImpl();
        for (Artista artista : artistaImpl.findAll()) {
            if (artista.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer buscarById(Integer id) {
        ArtistaImpl artistaImpl = new ArtistaImpl();
        for (Artista artista : artistaImpl.findAll()) {
            if (artista.getId().equals(id)) {
                return id;
            }
        }
        return -1;
    }

    @Override
    public String getNombreTabla() {
        return "tbl_artista";
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
