package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Cancion;

import java.sql.PreparedStatement;
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
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean edit(Integer id) {
        return false;
    }

    @Override
    public boolean addRegistro(Cancion cancion) {
        openConnection();

        if (cancion == null || cancion.getNombre() == null || cancion.getNombre().trim().isEmpty()) {
            System.out.println("La cancion proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_cancion (nombre) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cancion.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("La cancion se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la cancion: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}
