package org.gerdoc.pixup.test;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;
import org.gerdoc.pixup.model.Estado;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.*;

public class EstadoImplTest {
    private static final EstadoJdbcImpl estadoJdbc = EstadoJdbcImpl.getInstance();
    private static int testId;

    @Test
    public void testAddRegistro() {
        Estado estado = new Estado();
        estado.setNombre("TestEstado");

        boolean result = estadoJdbc.addRegistro(estado);
        assertTrue("El estado debería guardarse correctamente.", result);

        // Buscar estado insertado para usar su ID en pruebas posteriores
        List<Estado> estados = estadoJdbc.findAll();
        Estado inserted = estados.stream()
                .filter(e -> "TestEstado".equalsIgnoreCase(e.getNombre()))
                .findFirst().orElse(null);

        assertNotNull("El estado recién insertado debería existir en la base de datos.", inserted);
        testId = inserted.getId();
    }

    @Test
    public void testFindAll() {
        List<Estado> estados = estadoJdbc.findAll();
        assertNotNull("La lista de estados no debe ser nula.", estados);
        assertFalse("La lista no debería estar vacía si hay estados registrados.", estados.isEmpty());
        if(!estados.isEmpty()){
            for (Estado estado : estados) {
                System.out.println("- " + estado.getNombre());
            }
        }
    }

    @Test
    public void testFindById() {
        Estado estado = estadoJdbc.findById(10);

        assertNotNull("El estado debería encontrarse por su ID.", estado);
        assertEquals("El nombre del estado debería ser 'TestEstado'.", "TestEstado", estado.getNombre());

        if (estado != null) {
            System.out.println("Estado encontrado:");
            System.out.println("ID: " + estado.getId());
            System.out.println("Nombre: " + estado.getNombre());
        }
    }

    @Test
    public void testRemover() {
        boolean removed = estadoJdbc.remover(10);
        System.out.println("¿Se eliminó el estado con ID 10?: " + removed);
        assertNotNull("La operación de eliminación no debe retornar null", removed);
    }

    @Test
    public void testEdit() {
        // Simular entrada por consola para nuevo nombre
        String nuevoNombre = "EstadoEditadoId10";
        ByteArrayInputStream input = new ByteArrayInputStream(nuevoNombre.getBytes());
        System.setIn(input); // Simula el input que espera ReadUtil.read()

        // Intentar editar el estado con ID 9
        boolean edited = estadoJdbc.edit(9);

        // Validación de que la edición se realizó
        System.out.println("¿Se editó el estado con ID 9?: " + edited);
        assertTrue("El estado con ID 9 debería editarse correctamente (si existe)", edited);

        // Confirmar que el nombre fue actualizado
        Estado updated = estadoJdbc.findById(9);
        assertNotNull("El estado con ID 9 debería existir tras la edición", updated);
        assertEquals("El nombre del estado debería haberse actualizado", nuevoNombre, updated.getNombre());
    }
}
