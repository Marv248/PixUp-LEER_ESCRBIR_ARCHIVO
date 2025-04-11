package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Colonia;
import org.gerdoc.pixup.model.Estado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColoniaImp extends Conexion<Colonia> implements Jdbc {
    private static ColoniaImp coloniaImp;

    public ColoniaImp(){super();}

    public  static ColoniaImp getInstance(){
        if(coloniaImp==null){
            coloniaImp = new ColoniaImp();
        }
        return coloniaImp;
    }

    @Override
    public List<Colonia> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Colonia> list = null;
        Colonia colonia = null;
        String sql ="Select * from tbl_colonia";


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
            list =  new ArrayList<Colonia>( );
            while( resultSet.next( ) )
            {
                colonia = new Colonia();
                colonia.setId( resultSet.getInt( "ID" ) );
                colonia.setNombre( resultSet.getString( "nombre" ) );
                list.add( colonia );
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
    public boolean addRegistro(Colonia colonia) {
        Statement statement = null;
        String sql = String.format("INSERT INTO tbl_colonia (ID, nombre) VALUES (%d, '%s')",
                colonia.getId(), colonia.getNombre());

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
