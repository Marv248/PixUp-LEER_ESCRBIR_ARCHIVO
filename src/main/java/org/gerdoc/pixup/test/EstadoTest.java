package org.gerdoc.pixup.test;

import org.gerdoc.pixup.model.Estado;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstadoTest {

    @Test
    public void testSetGetNombre() {
        Estado estado = new Estado();
        estado.setNombre("Jalisco");
        assertEquals("Jalisco", estado.getNombre());
    }

    @Test
    public void testSetGetId() {
        Estado estado = new Estado();
        estado.setId(1);
        assertEquals(1, estado.getId());
    }
}
