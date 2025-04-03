package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.GeneroMusical;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical> {

    public static GeneroMusicalCatalogo generoMusicalCatalogo;
    private GeneroMusicalCatalogo( )
    {
        super();
    }

    public static GeneroMusicalCatalogo getInstance( )
    {
        if(generoMusicalCatalogo ==null)
        {
            generoMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return generoMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT()
    {
        return new GeneroMusical( );
    }

    @Override
    public boolean processNewT(GeneroMusical generoMusical)
    {
        System.out.println("Teclee un genero musical" );
        generoMusical.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(GeneroMusical generoMusical)
    {
        System.out.println("Id del generoMusical: " + generoMusical.getId( ) );
        System.out.println("GeneroMusical a editar: " + generoMusical.getNombre( ) );
        System.out.println("Teclee el nombre nuevo del generoMusical: " );
        generoMusical.setNombre( ReadUtil.read( ) );
    }

    @Override
    public File getFile()
    {
        return new File( "./GeneroMusical.object");
    }
}
