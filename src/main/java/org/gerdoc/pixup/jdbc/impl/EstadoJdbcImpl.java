package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Artista;
import org.gerdoc.pixup.model.Estado;
import org.gerdoc.pixup.util.ReadUtil;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Estado> findAll() {
        List<Estado> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_estado";

        getConnection();

        try (
                Connection conn = connection;
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                Estado estado = new Estado();
                estado.setId(resultSet.getInt("ID"));
                estado.setNombre(resultSet.getString("nombre"));
                list.add(estado);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return list;
    }



    @Override
    public List edit() {
        return List.of();
    }

    @Override
    public boolean addRegistro(Estado estado) {
        getConnection();

        if (estado == null || estado.getNombre() == null || estado.getNombre().trim().isEmpty()) {
            System.out.println("El estado proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_estado (nombre) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, estado.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("El estado se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el estado: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

}
