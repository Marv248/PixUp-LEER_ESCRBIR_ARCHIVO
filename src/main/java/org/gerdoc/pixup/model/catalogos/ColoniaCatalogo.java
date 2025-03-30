package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Colonia;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class ColoniaCatalogo extends Catalogos<Colonia> {
    public static ColoniaCatalogo coloniaCatalogo;
    private ColoniaCatalogo( )
    {
        super();
    }

    public static ColoniaCatalogo getInstance( )
    {
        if(coloniaCatalogo ==null)
        {
            coloniaCatalogo = new ColoniaCatalogo();
        }
        return coloniaCatalogo;
    }

    @Override
    public Colonia newT()
    {
        return new Colonia( );
    }

    @Override
    public boolean processNewT(Colonia colonia)
    {
        System.out.println("Teclee una colonia" );
        colonia.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Colonia colonia)
    {
        System.out.println("Id de la colonia: " + colonia.getId( ) );
        System.out.println("Colonia a editar: " + colonia.getNombre( ) );
        System.out.println("Teclee el nombre nuevo de la nueva colonia: " );
        colonia.setNombre( ReadUtil.read( ) );
    }

    @Override
    public File getFile()
    {
        return new File( "./Colonia.object");
    }
}
