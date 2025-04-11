package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Cancion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionImpl extends Conexion<Cancion> implements Jdbc {
    private static CancionImpl cancionImpl;

    public CancionImpl()
    {
        super( );
    }


    public static CancionImpl getInstance( )
    {
        if( cancionImpl == null )
        {
            cancionImpl = new CancionImpl();
        }
        return cancionImpl;
    }

    @Override
    public List<Cancion> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Cancion> list = null;
        Cancion cancion = null;
        String sql ="Select * from tbl_cancion";


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
            list =  new ArrayList<Cancion>( );
            while( resultSet.next( ) )
            {
                cancion = new Cancion();
                cancion.setId( resultSet.getInt( "ID" ) );
                cancion.setNombre( resultSet.getString( "nombre" ) );
                list.add( cancion );
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
    public List edit() {
        return List.of();
    }

    @Override
    public boolean addRegistro(Cancion cancion) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_cancion (ID, nombre) VALUES (%d, '%s')",
                cancion.getId(), cancion.getNombre());

        try {
            statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(sql);
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
