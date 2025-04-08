package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Disco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscoImpl extends Conexion<Disco> implements Jdbc {
    private static DiscoImpl discoImpl;

    public DiscoImpl()
    {
        super( );
    }


    public static DiscoImpl getInstance( )
    {
        if( discoImpl == null )
        {
            discoImpl = new DiscoImpl();
        }
        return discoImpl;
    }

    @Override
    public List<Disco> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Disco> list = null;
        Disco disco = null;
        String sql ="Select * from tbl_disco";


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
            list =  new ArrayList<Disco>( );
            while( resultSet.next( ) )
            {
                disco = new Disco();
                disco.setId( resultSet.getInt( "ID" ) );
                disco.setNombre( resultSet.getString( "nombre" ) );
                list.add( disco );
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
    public boolean addRegistro(Disco disco) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_disco (ID, nombre) VALUES (%d, '%s')",
                disco.getId(), disco.getNombre());

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