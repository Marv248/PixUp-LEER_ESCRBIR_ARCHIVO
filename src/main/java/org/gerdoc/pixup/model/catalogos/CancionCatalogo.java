package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.model.Cancion;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class CancionCatalogo extends Catalogos<Cancion> {

    public static CancionCatalogo cancionCatalogo;
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
    public File getFile()
    {
        return new File( "./Cancion.object");
    }

}
