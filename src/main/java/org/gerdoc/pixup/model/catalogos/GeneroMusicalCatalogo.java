package org.gerdoc.pixup.model.catalogos;

import jdk.dynalink.linker.LinkerServices;
import org.gerdoc.pixup.jdbc.impl.GeneroMusicalImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.GeneroMusical;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;
import java.util.List;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical> {

    public static GeneroMusicalCatalogo generoMusicalCatalogo;
    private GeneroMusical generoMusical= new GeneroMusical();
    private GeneroMusicalImpl generoMusicalImpl =new GeneroMusicalImpl();
    private List<GeneroMusical> generos;

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
        System.out.println("Introduzca el nombre del género músical:");
        generoMusical.setNombre(ReadUtil.read());
        generoMusicalImpl.  addRegistro(generoMusical);
    }

    @Override
    public void printAll() {
        generos = generoMusicalImpl.findAll();
        for(GeneroMusical generoMusical1 : generos){
            System.out.println(generoMusical1.getId()+"] " + generoMusical1.getNombre());
        }
    }

    @Override
    public File getFile()
    {
        return new File( "./GeneroMusical.object");
    }
}
