package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Artista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistaImpl extends Conexion<Artista> implements Jdbc {
    private static ArtistaImpl artistaImpl;

    public ArtistaImpl()
    {
        super( );
    }


    public static ArtistaImpl getInstance( )
    {
        if( artistaImpl == null )
        {
            artistaImpl = new ArtistaImpl();
        }
        return artistaImpl;
    }

    @Override
    public List<Artista> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Artista> list = null;
        Artista artista = null;
        String sql ="Select * from tbl_artista";


        try
        {
            if( openConnection() )
            {
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery( sql );
            if( resultSet == null )
            {
                return null;
            }
            list =  new ArrayList<Artista>( );
            while( resultSet.next( ) )
            {
                artista = new Artista();
                artista.setId( resultSet.getInt( "ID" ) );
                artista.setNombre( resultSet.getString( "nombre" ) );
                list.add( artista );
            }
            resultSet.close( );
            closeConnection( );
            return list;
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    @Override
    public boolean addRegistro(Artista artista) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_artista (ID, nombre) VALUES (%d, '%s')",
                artista.getId(), artista.getNombre());

        try {
            statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(sql);
            closeConnection();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}