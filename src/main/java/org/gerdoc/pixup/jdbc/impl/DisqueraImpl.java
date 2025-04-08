package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Disquera;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraImpl extends Conexion<Disquera> implements Jdbc {
    private static DisqueraImpl disqueraImpl;

    public DisqueraImpl()
    {
        super( );
    }


    public static DisqueraImpl getInstance( )
    {
        if( disqueraImpl == null )
        {
            disqueraImpl = new DisqueraImpl();
        }
        return disqueraImpl;
    }

    @Override
    public List<Disquera> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Disquera> list = null;
        Disquera disquera = null;
        String sql ="Select * from tbl_disquera";


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
            list =  new ArrayList<Disquera>( );
            while( resultSet.next( ) )
            {
                disquera = new Disquera();
                disquera.setId( resultSet.getInt( "ID" ) );
                disquera.setNombre( resultSet.getString( "nombre" ) );
                list.add( disquera );
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
    public boolean addRegistro(Disquera disquera) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_disquera (ID, nombre) VALUES (%d, '%s')",
                disquera.getId(), disquera.getNombre());

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