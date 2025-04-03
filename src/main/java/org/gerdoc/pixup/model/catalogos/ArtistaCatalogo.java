package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.model.Artista;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class ArtistaCatalogo extends Catalogos<Artista> {

    public static ArtistaCatalogo artistaCatalogo;
    private ArtistaCatalogo( )
    {
        super();
    }

    public static ArtistaCatalogo getInstance( )
    {
        if(artistaCatalogo ==null)
        {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT()
    {
        return new Artista( );
    }

    @Override
    public boolean processNewT(Artista artista)
    {
        System.out.println("Teclee un disco" );
        artista.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Artista artista)
    {
        System.out.println("Id del artista: " + artista.getId( ) );
        System.out.println("Artista a editar: " + artista.getNombre( ) );
        System.out.println("Teclee el nombre nuevo del artista: " );
        artista.setNombre( ReadUtil.read( ) );
    }

    @Override
    public File getFile()
    {
        return new File( "./Artista.object");
    }
}
