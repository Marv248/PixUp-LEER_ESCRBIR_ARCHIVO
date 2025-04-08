package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Estado;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class EstadoCatalogo extends Catalogos<Estado>
{
    public EstadoJdbcImpl estadoJdbc = new EstadoJdbcImpl();
    public static Estado estado = new Estado();
    public static EstadoCatalogo estadoCatalogo;
    private EstadoCatalogo( )
    {
        super();
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
        System.out.println("Introduzaca el nombre del estado: ");
        estado.setNombre(ReadUtil.read());
        if (estado.buscar(estado.getNombre())){
            System.out.println("Lo siento, el estado ingresado ya existe \u2639");
        }
        else
        {
            estadoJdbc.addRegistro(estado);
            System.out.println("Estado agregado con éxito");
        }
    }

    @Override
    public File getFile()
    {
        return new File( "./Estado.object");
    }

}
