package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.ColoniaImp;
import org.gerdoc.pixup.jdbc.impl.MunicipioImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Colonia;
import org.gerdoc.pixup.model.Municipio;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class ColoniaCatalogo extends Catalogos<Colonia> {
    public static ColoniaCatalogo coloniaCatalogo;
    private Colonia colonia = new Colonia();
    private ColoniaImp coloniaImp = new ColoniaImp();

    @Override
    public Integer buscarIdEnBD(Integer id) {
        return 0;
    }

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
    public void addRegistro() {
        System.out.println("Introduzca el nombre del Municipio:");
        this.colonia.setNombre(ReadUtil.read());
        if(colonia.buscar(colonia.getNombre())){
            System.out.println("Lo siento, la Colonia " + this.colonia.getNombre()+" ya existe \u2639");
        }
        else {
            coloniaImp.addRegistro(colonia);
            System.out.println("Colonia agregada con éxito");
        }
    }

    @Override
    public void printAll() {
        coloniaImp.findAll();
    }

    @Override
    public File getFile()
    {
        return new File( "./Colonia.object");
    }
}
