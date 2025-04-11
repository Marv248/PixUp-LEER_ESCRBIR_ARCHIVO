package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.model.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaImpl extends Conexion<Artista> {

    @Override
    public boolean addRegistro(Artista artista) {
        getConnection();
        String sql = "INSERT INTO tbl_artista (nombre) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, artista.getNombre());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Artista> findAll() {
        List<Artista> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_artista";

        try {
            getConnection();
            if (openConnection()) {
                System.out.println("No se pudo abrir la conexión.");
                return list;
            }
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Artista artista = new Artista();
                artista.setId(rs.getInt("ID"));
                artista.setNombre(rs.getString("nombre"));
                list.add(artista);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            closeConnection();
        }
        return list;
    }

}
