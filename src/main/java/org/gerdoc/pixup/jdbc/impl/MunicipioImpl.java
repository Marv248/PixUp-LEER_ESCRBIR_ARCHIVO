package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.Jdbc;
import org.gerdoc.pixup.model.Estado;
import org.gerdoc.pixup.model.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioImpl extends Conexion<Municipio> implements Jdbc {

    private static MunicipioImpl municipioJdbc;

    public MunicipioImpl() {
        super();
    }

    public static MunicipioImpl getInstance() {
        if (municipioJdbc == null) {
            municipioJdbc = new MunicipioImpl();
        }
        return municipioJdbc;
    }

    @Override
    public List<Municipio> findAll() {
        List<Municipio> municipioList = new ArrayList<>();
        String sql = "SELECT * FROM tbl_municipio";
        getConnection();

        try {
            if (openConnection()) {
                System.out.println("No se pudo abrir la conexión.");
                return municipioList;
            }

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                EstadoJdbcImpl estadoImpl = new EstadoJdbcImpl();

                while (resultSet.next()) {
                    Municipio municipio = new Municipio();
                    municipio.setId(resultSet.getInt("ID"));
                    municipio.setNombre(resultSet.getString("nombre"));

                    int idEstado = resultSet.getInt("id_estado");
                    Estado estado = estadoImpl.findById(idEstado);
                    municipio.setEstado(estado);

                    municipioList.add(municipio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener municipios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return municipioList;
    }


    @Override
    public boolean addRegistro(Municipio municipio) {
        getConnection();

        if (municipio == null || municipio.getNombre() == null || municipio.getNombre().trim().isEmpty()
                || municipio.getEstado() == null || municipio.getEstado().getId() == null) {
            System.out.println("Datos inválidos para insertar el municipio.");
            return false;
        }

        String sql = "INSERT INTO tbl_municipio (nombre, id_estado) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, municipio.getNombre());
            preparedStatement.setInt(2, municipio.getEstado().getId());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El municipio se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el municipio: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Municipio> edit() {
        return List.of();
    }
}
