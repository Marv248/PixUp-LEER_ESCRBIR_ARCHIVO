package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.DiscoImpl;
import org.gerdoc.pixup.jdbc.impl.DisqueraImpl;
import org.gerdoc.pixup.model.Cancion;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Disco;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class DiscoCatalogo extends Catalogos<Disco> {

    public static DiscoCatalogo discoCatalogo;
    private Disco disco = new Disco();
    private DiscoImpl discoImpl = new DiscoImpl();

    private DiscoCatalogo( )
    {
        super();
    }

    public static DiscoCatalogo getInstance( )
    {
        if(discoCatalogo ==null)
        {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT()
    {
        return new Disco( );
    }

    @Override
    public boolean processNewT(Disco disco)
    {
        System.out.println("Teclee un disco" );
        disco.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Disco disco)
    {
        System.out.println("Id del disco: " + disco.getId( ) );
        System.out.println("Disco a editar: " + disco.getNombre( ) );
        System.out.println("Teclee el nombre nuevo del disco: " );
        disco.setNombre( ReadUtil.read( ) );
    }

    @Override
    public void addRegistro() {
        System.out.println("Ingrese el nombre del disco:");
        this.disco.setNombre(ReadUtil.read());
        if(disco.buscar(disco.getNombre())){
            System.out.println("Lo siento, el disco " + disco.getNombre()+" ya existe \u2639");
        }
        else {
            discoImpl.addRegistro(disco);
            System.out.println("Registro realizado sin errores");
        }
    }

    @Override
    public File getFile()
    {
        return new File( "./Disco.object");
    }
}
