package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.impl.MunicipioImpl;

import java.io.Serializable;
import java.util.List;

public class Municipio extends Catalogo implements Serializable {
    private String nombreTabla = "tbl_municipio";
    private Municipio municipio;
    private Estado estado;

    public Municipio() {}

    @Override
    public Catalogo getInstance() {
        if (municipio == null) {
            municipio = new Municipio();
        }
        return municipio;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombre) {
        this.nombreTabla = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Municipio:\n" +
                "\tid=" + id +
                "\n\tNombre='" + nombre + "'" +
                "\n\tEstado=" + (estado != null ? estado.getNombre() : "Sin estado");
    }

    @Override
    public boolean buscar(String nombre) {
        boolean busqueda = false;
        MunicipioImpl municipioImpl = new MunicipioImpl();
        List<Municipio> municipios = municipioImpl.findAll();

        if (municipios != null && !municipios.isEmpty()) {
            for (Municipio municipio : municipios) {
                if (municipio.getNombre().equalsIgnoreCase(nombre)) {
                    busqueda = true;
                    break;
                }
            }
        }

        return busqueda;
    }

    @Override
    public Integer buscarById(Integer id) {
        int i = -1;
        MunicipioImpl municipioImpl = new MunicipioImpl();
        List<Municipio> municipioList = municipioImpl.findAll();

        if (municipioList != null && !municipioList.isEmpty()) {
            for (Municipio municipio1 : municipioList) {
                if (municipio1.getId() != null && municipio1.getId().equals(id)) {
                    i = id;
                    break;
                }
            }
        }
        return i;
    }
}
