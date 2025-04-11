package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Estado;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;
import java.util.List;

public class EstadoCatalogo extends Catalogos<Estado>
{
    public EstadoJdbcImpl estadoJdbc = new EstadoJdbcImpl();
    public static Estado estado = new Estado();
    public static EstadoCatalogo estadoCatalogo;

    private EstadoCatalogo( )
    {
        super();
        this.conexion = new EstadoJdbcImpl();
    }

    @Override
    public Integer buscarIdEnBD(Integer id) {
        return new Estado().buscarById(id);
    }

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {
            estadoCatalogo = new EstadoCatalogo();
        }
        return estadoCatalogo;
    }

    @Override
    public Estado newT()
    {
        return new Estado( );
    }

    @Override
    public boolean processNewT(Estado estado)
    {
        System.out.println("Teclee un estado" );
        estado.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Estado estado)
    {
        System.out.println("Id del Estado: " + estado.getId( ) );
        System.out.println("Estado a editar: " + estado.getNombre( ) );
        System.out.println("Teclee el nombre nuevo del estado: " );
        estado.setNombre( ReadUtil.read( ) );
    }

    @Override
    public void addRegistro() {
        getConnection();
        try {
            System.out.println("Introduzca el nombre del estado:");
            String nombre = ReadUtil.read();

            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("El nombre del estado no puede estar vacío.");
                return;
            }

            Estado nuevoEstado = new Estado();
            nuevoEstado.setNombre(nombre);

            if (nuevoEstado.buscar(nombre)) {
                System.out.println("Lo siento, el estado ingresado ya existe ☹");
            } else {
                if (estadoJdbc.addRegistro(nuevoEstado)) {
                    System.out.println("Estado agregado con éxito ✅");
                } else {
                    System.out.println("Hubo un problema al agregar el estado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al agregar estado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void printAll() {
        getConnection();
        estadoJdbc.findAll().forEach(System.out::println);
    }

    @Override
    public File getFile()
    {
        return new File( "./Estado.object");
    }

    @Override
    public List<Estado> edit() {
        return super.edit();
    }

    @Override
    public List<Estado> delete() {
        return super.delete();
    }
}
