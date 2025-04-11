package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.GeneroMusicalImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.GeneroMusical;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical> {

    public static GeneroMusicalCatalogo generoMusicalCatalogo;
    private GeneroMusical generoMusical= new GeneroMusical();
    private GeneroMusicalImpl generoMusicalImpl =new GeneroMusicalImpl();

    @Override
    public Integer buscarIdEnBD(Integer id) {
        return 0;
    }

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
    public void addRegistro() {
        System.out.println("Introduzca el nombre del género musical.");
        this.generoMusical.setNombre(ReadUtil.read());
        if(generoMusical.buscar(generoMusical.getNombre())){
            System.out.println("Lo siento, el genero "+generoMusical.getNombre()+" ya existe \u2639");
        }
        else {
            generoMusicalImpl.addRegistro(generoMusical);
            System.out.println("Registro realizado de manera exitosa");
        }
    }

    @Override
    public void printAll() {
        generoMusicalImpl.findAll();
    }

    @Override
    public File getFile()
    {
        return new File( "./GeneroMusical.object");
    }
}
