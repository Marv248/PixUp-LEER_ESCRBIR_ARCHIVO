package org.gerdoc.pixup.jdbc.impl;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.model.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaImpl extends Conexion<Artista> {

    @Override
    public boolean addRegistro(Artista artista) {
        openConnection();

        if (artista == null || artista.getNombre() == null || artista.getNombre().trim().isEmpty()) {
            System.out.println("El artista proporcionado no es válido.");
            return false;
        }

        String sql = "INSERT INTO tbl_artista (nombre) VALUES (?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, artista.getNombre());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("El artista se ha agregado correctamente.");
                return true;
            } else {
                System.out.println("⚠ No se insertó ningún registro.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el artista: " + e.getMessage());
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

    @Override
    public boolean remover(Integer id) {
        if (id == null || id <= 0) {
            System.out.println("ID inválido.");
            return false;
        }

        getConnection();
        String sql = "DELETE FROM tbl_artista WHERE id = ?";
        boolean removed = false;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            removed = rowsAffected > 0;

            if (removed) {
                System.out.println("Artista con ID " + id + " ha sido eliminado correctamente.");
            } else {
                System.out.println("No se encontró artista con ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el artista: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return removed;
    }


    @Override
    public boolean edit(Integer id) {
        List<Artista> artistas = findAll();

        if (artistas == null || artistas.isEmpty()) {
            System.out.println("No hay artistas registrados aún.");
            return false;
        }

        System.out.println("Artistas registrados:");
        for (Artista artista : artistas) {
            System.out.println("- ID: " + artista.getId() + " | Nombre: " + artista.getNombre());
        }

        if (id == null || id <= 0) {
            System.out.println("ID inválido.");
            return false;
        }

        System.out.print("Introduzca el nuevo nombre del artista: ");
        String nuevoNombre = org.gerdoc.pixup.util.ReadUtil.read();

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }

        getConnection();
        String sql = "UPDATE tbl_artista SET nombre = ? WHERE id = ?";
        boolean updated = false;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre.trim());
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();
            updated = rowsAffected > 0;

            if (updated) {
                System.out.println("Artista con ID " + id + " actualizado a '" + nuevoNombre + "'");
            } else {
                System.out.println("No se encontró el artista con ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al editar el artista: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return updated;
    }

}
