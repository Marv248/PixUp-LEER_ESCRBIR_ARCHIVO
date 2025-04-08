package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Municipio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MunicipioImpl extends Conexion<Municipio> implements Jdbc{
    private static MunicipioImpl municipioJdbc;

    public MunicipioImpl()
    {
        super( );
    }


    public static MunicipioImpl getInstance( )
    {
        if( municipioJdbc == null )
        {
            municipioJdbc = new MunicipioImpl();
        }
        return municipioJdbc;
    }

    @Override
    public List<Municipio> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Municipio> list = null;
        Municipio municipio = null;
        String sql ="Select * from tbl_municipio";


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
            list =  new ArrayList<Municipio>( );
            while( resultSet.next( ) )
            {
                municipio = new Municipio();
                municipio.setId( resultSet.getInt( "ID" ) );
                municipio.setNombre( resultSet.getString( "nombre" ) );
                list.add( municipio );
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
    public boolean addRegistro(Municipio municipio) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_municipio (ID, nombre) VALUES (%d, '%s')",
                municipio.getId(), municipio.getNombre());

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
