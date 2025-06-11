package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Disquera;

import java.sql.PreparedStatement;
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
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean edit(Integer id) {
        return false;
    }

    @Override
    public boolean addRegistro(Disquera disquera) {
        openConnection();

        if (disquera == null || disquera.getNombre() == null || disquera.getNombre().trim().isEmpty()) {
            System.out.println("La disquera proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_disquera (nombre) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, disquera.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("La disquera se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la disquera: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}