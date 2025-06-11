package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Disco;

import java.sql.PreparedStatement;
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
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean edit(Integer id) {
        return false;
    }

    @Override
    public boolean addRegistro(Disco disco) {
        openConnection();

        if (disco == null || disco.getNombre() == null || disco.getNombre().trim().isEmpty()) {
            System.out.println("El disco proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_disco (nombre) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, disco.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("El disco se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el disco: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}