package org.gerdoc.pixup.test;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;
import org.gerdoc.pixup.model.Estado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EstadoImplTest {

    @Test
    public void testFindAll() {
        EstadoJdbcImpl estadoImpl = new EstadoJdbcImpl();
        List<Estado> estados = estadoImpl.findAll();
        assertNotNull(estados);
        assertFalse(estados.isEmpty(), "La lista no debería estar vacía si hay estados registrados.");
    }
}
