package org.gerdoc.pixup.jdbc.impl;


import org.gerdoc.pixup.model.Estado;
import org.gerdoc.pixup.util.ReadUtil;
import org.junit.Test;

import java.util.List;


public class EstadoJdbcImplTest{
    private EstadoJdbcImpl estadoJdbc = new EstadoJdbcImpl();
    @Test
    public void findAll() {
        List<Estado> estados = estadoJdbc.findAll();
        estados.forEach(System.out::println);
    }

    @Test
    public void addRegistro() {
        Estado estado = new Estado();
        estado.setNombre("CDMX");
        estadoJdbc.addRegistro(estado);
    }



}