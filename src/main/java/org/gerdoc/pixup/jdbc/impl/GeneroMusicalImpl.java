package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.GeneroMusical;

import java.sql.PreparedStatement;
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
        getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<GeneroMusical> list = null;
        GeneroMusical generoMusical = null;
        String sql ="Select * from tbl_genero_musical";
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery( sql );
            list =  new ArrayList<GeneroMusical>( );
            while( resultSet.next( ) )
            {
                generoMusical = new GeneroMusical();
                generoMusical.setId( resultSet.getInt( "id" ) );
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
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean edit(Integer id) {
        return false;
    }


    @Override
    public boolean addRegistro(GeneroMusical generoMusical) {
        getConnection();

        if (generoMusical == null || generoMusical.getNombre() == null || generoMusical.getNombre().trim().isEmpty()) {
            System.out.println("El generoMusical proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_genero_musical (descripcion) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, generoMusical.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("El generoMusical se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el generoMusical: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}