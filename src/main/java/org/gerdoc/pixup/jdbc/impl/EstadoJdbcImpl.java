package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Estado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EstadoJdbcImpl extends Conexion<Estado> implements Jdbc
{
    private static EstadoJdbcImpl estadoJdbc;

    public EstadoJdbcImpl()
    {
        super( );
    }


    public static EstadoJdbcImpl getInstance( )
    {
        if( estadoJdbc == null )
        {
            estadoJdbc = new EstadoJdbcImpl();
        }
        return estadoJdbc;
    }

    @Override
    public List<Estado> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Estado> list = null;
        Estado estado = null;
        String sql ="Select * from tbl_estado";


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
            list =  new java.util.ArrayList<Estado>( );
            while( resultSet.next( ) )
            {
                estado = new Estado();
                estado.setId( resultSet.getInt( "ID" ) );
                estado.setNombre( resultSet.getString( "nombre" ) );
                list.add( estado );
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
    public boolean addRegistro(Estado estado) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_estado (ID, nombre) VALUES (%d, '%s')",
                estado.getId(), estado.getNombre());

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
