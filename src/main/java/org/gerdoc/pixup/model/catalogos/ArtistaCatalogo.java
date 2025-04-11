package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.ArtistaImpl;
import org.gerdoc.pixup.model.Artista;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;
import java.util.List;

public class ArtistaCatalogo extends Catalogos<Artista> {

    private static ArtistaCatalogo artistaCatalogo = null;
    private final ArtistaImpl artistaImpl = new ArtistaImpl();

    public static ArtistaCatalogo getInstance() {
        if (artistaCatalogo == null) {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Integer buscarIdEnBD(Integer id) {
        return new Artista().buscarById(id);
    }

    @Override
    public Artista newT() {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista) {
        return artistaImpl.addRegistro(artista);
    }

    @Override
    public void processEditT(Artista artista) {
        // Aquí puedes implementar lógica de edición si lo deseas
    }

    @Override
    public void addRegistro() {
        getConnection();
        try {
            System.out.println("Introduzca el nombre del artista:");
            String nombre = ReadUtil.read();

            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("El nombre del artista no puede estar vacío.");
                return;
            }

            Artista nuevoArtista = new Artista();
            nuevoArtista.setNombre(nombre);

            if (nuevoArtista.buscar(nombre)) {
                System.out.println("Lo siento, el artista ingresado ya existe ☹");
            } else {
                if (artistaImpl.addRegistro(nuevoArtista)) {
                    System.out.println("Artista agregado con éxito ✅");
                } else {
                    System.out.println("Hubo un problema al agregar el artista.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al agregar artista: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void printAll() {
        List<Artista> artistas = artistaImpl.findAll();
        if (artistas.isEmpty()) {
            System.out.println("No hay artistas registrados.");
        } else {
            artistas.forEach(System.out::println);
        }
    }

    @Override
    public File getFile() {
        return new File("artista.obj");
    }
}
