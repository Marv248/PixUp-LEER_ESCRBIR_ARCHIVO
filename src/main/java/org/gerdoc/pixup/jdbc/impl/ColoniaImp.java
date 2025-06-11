package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Colonia;
import org.gerdoc.pixup.model.Estado;

import java.sql.PreparedStatement;
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
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean edit(Integer id) {
        return false;
    }

    @Override
    public boolean addRegistro(Colonia colonia) {
        openConnection();

        if (colonia == null || colonia.getNombre() == null || colonia.getNombre().trim().isEmpty()) {
            System.out.println("La colonia proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_colonia (nombre) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, colonia.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("La colonia se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la colonia: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}
