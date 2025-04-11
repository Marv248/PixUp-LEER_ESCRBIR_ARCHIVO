package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.CancionImpl;
import org.gerdoc.pixup.model.Cancion;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class CancionCatalogo extends Catalogos<Cancion> {

    public static CancionCatalogo cancionCatalogo;
    private Cancion cancion = new Cancion();
    private CancionImpl cancionImpl = new CancionImpl();

    @Override
    public Integer buscarIdEnBD(Integer id) {
        return 0;
    }

    private CancionCatalogo( )
    {
        super();
    }

    public static CancionCatalogo getInstance( )
    {
        if(cancionCatalogo ==null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    @Override
    public Cancion newT()
    {
        return new Cancion( );
    }

    @Override
    public boolean processNewT(Cancion cancion)
    {
        System.out.println("Teclee una cancion" );
        cancion.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Cancion cancion)
    {
        System.out.println("Id de la cancion: " + cancion.getId( ) );
        System.out.println("Cancion a editar: " + cancion.getNombre( ) );
        System.out.println("Teclee el nombre nuevo del cancion: " );
        cancion.setNombre( ReadUtil.read( ) );
    }

    @Override
    public void addRegistro() {
        System.out.println("Introduzca el nombre de la canción:");
        cancion.setNombre(ReadUtil.read());
        if(cancion.buscar(cancion.getNombre())){
            System.out.println("Lo siento, la canción "+cancion.getNombre()+" ya existe \u2639");
        }
        else {
            cancionImpl.addRegistro(cancion);
            System.out.println("Registro realizado de manera exitos");
        }
    }

    @Override
    public void printAll() {
        cancionImpl.findAll();
    }

    @Override
    public File getFile()
    {
        return new File( "./Cancion.object");
    }

}
