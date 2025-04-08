package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.MunicipioImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Municipio;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends Catalogos <Municipio>{
    public static MunicipioCatalogo municipioCatalogo;
    private Municipio municipio = new Municipio();
    private MunicipioImpl municipioImpl = new MunicipioImpl();

    private MunicipioCatalogo( )
    {
        super();
    }

    public static MunicipioCatalogo getInstance( )
    {
        if(municipioCatalogo==null)
        {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    @Override
    public Municipio newT()
    {
        return new Municipio( );
    }

    @Override
    public boolean processNewT(Municipio municipio)
    {
        System.out.println("Teclee un municipio" );
        municipio.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Municipio municipio)
    {
        System.out.println("Id del Municipio: " + municipio.getId( ) );
        System.out.println("Municipio a editar: " + municipio.getNombre( ) );
        System.out.println("Teclee el nombre nuevo del municipio: " );
        municipio.setNombre( ReadUtil.read( ) );
    }

    @Override
    public void addRegistro() {
        System.out.println("Introduzca el nombre del Municipio:");
        this.municipio.setNombre(ReadUtil.read());
        if(this.municipio.buscar(municipio.getNombre())){
            System.out.println("Lo siento, Municipio no encontrado \u2639");
        }
        else{
            municipioImpl.addRegistro(municipio);
            System.out.println("Municipio agregado con éxito");
        }
    }

    @Override
    public File getFile()
    {
        return new File( "./Municipio.object");
    }
}
