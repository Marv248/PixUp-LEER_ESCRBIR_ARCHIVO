package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.GeneroMusical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalImpl extends Conexion<GeneroMusical> implements Jdbc {
    private static GeneroMusicalImpl generoMusicalImpl;

    public GeneroMusicalImpl()
    {
        super( );
    }


    public static GeneroMusicalImpl getInstance( )
    {
        if( generoMusicalImpl == null )
        {
            generoMusicalImpl = new GeneroMusicalImpl();
        }
        return generoMusicalImpl;
    }

    @Override
    public List<GeneroMusical> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<GeneroMusical> list = null;
        GeneroMusical generoMusical = null;
        String sql ="Select * from tbl_genero_musical";


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
            list =  new ArrayList<GeneroMusical>( );
            while( resultSet.next( ) )
            {
                generoMusical = new GeneroMusical();
                generoMusical.setId( resultSet.getInt( "ID" ) );
                generoMusical.setNombre( resultSet.getString( "nombre" ) );
                list.add( generoMusical );
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
    public boolean addRegistro(GeneroMusical generoMusical) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_genero_musical (ID, nombre) VALUES (%d, '%s')",
                generoMusical.getId(), generoMusical.getNombre());

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