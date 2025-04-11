package org.gerdoc.pixup.model.catalogos;

import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;
import org.gerdoc.pixup.jdbc.impl.MunicipioImpl;
import org.gerdoc.pixup.model.Catalogos;
import org.gerdoc.pixup.model.Estado;
import org.gerdoc.pixup.model.Municipio;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.File;
import java.util.List;

public class MunicipioCatalogo extends Catalogos<Municipio> {
    public static MunicipioCatalogo municipioCatalogo;
    private Municipio municipio = new Municipio();
    private MunicipioImpl municipioImpl = new MunicipioImpl();
    private EstadoJdbcImpl estadoImpl = new EstadoJdbcImpl();

    @Override
    public Integer buscarIdEnBD(Integer id) {
        return new Municipio().buscarById(id);
    }

    private MunicipioCatalogo() {
        super();
        this.conexion = municipioImpl;
    }

    public static MunicipioCatalogo getInstance() {
        if (municipioCatalogo == null) {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    @Override
    public Municipio newT() {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio municipio) {
        System.out.println("Teclee un municipio:");
        municipio.setNombre(ReadUtil.read());

        List<Estado> estados = estadoImpl.findAll();
        if (estados.isEmpty()) {
            System.out.println("No hay estados disponibles para asignar.");
            return false;
        }

        System.out.println("Seleccione el ID del estado al que pertenece el municipio:");
        estados.forEach(System.out::println);
        int estadoId = ReadUtil.readInt();

        Estado estadoSeleccionado = estados.stream()
                .filter(e -> e.getId().equals(estadoId))
                .findFirst()
                .orElse(null);

        if (estadoSeleccionado == null) {
            System.out.println("Estado no válido.");
            return false;
        }

        municipio.setEstado(estadoSeleccionado);
        return true;
    }

    @Override
    public void processEditT(Municipio municipio) {
        System.out.println("Id del Municipio: " + municipio.getId());
        System.out.println("Municipio a editar: " + municipio.getNombre());
        System.out.println("Teclee el nombre nuevo del municipio: ");
        municipio.setNombre(ReadUtil.read());

        // Editar también el estado si se desea
        List<Estado> estados = estadoImpl.findAll();
        if (!estados.isEmpty()) {
            System.out.println("Seleccione el nuevo ID del estado:");
            estados.forEach(System.out::println);
            int nuevoEstadoId = ReadUtil.readInt();

            Estado nuevoEstado = estados.stream()
                    .filter(e -> e.getId().equals(nuevoEstadoId))
                    .findFirst()
                    .orElse(null);

            if (nuevoEstado != null) {
                municipio.setEstado(nuevoEstado);
            }
        }
    }

    @Override
    public void addRegistro() {
        getConnection();
        try {
            System.out.println("Introduzca el nombre del municipio:");
            String nombre = ReadUtil.read();

            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("El nombre del municipio no puede estar vacío.");
                return;
            }

            Municipio nuevoMunicipio = new Municipio();
            nuevoMunicipio.setNombre(nombre);

            List<Estado> estados = estadoImpl.findAll();
            if (estados.isEmpty()) {
                System.out.println("No hay estados registrados para asignar.");
                return;
            }

            System.out.println("Seleccione el ID del estado para este municipio:");
            estados.forEach(System.out::println);
            int estadoId = ReadUtil.readInt();

            Estado estadoSeleccionado = estados.stream().filter(e -> e.getId().equals(estadoId)).findFirst().orElse(null);

            if (estadoSeleccionado == null) {
                System.out.println("Estado no válido.");
                return;
            }

            nuevoMunicipio.setEstado(estadoSeleccionado);

            if (nuevoMunicipio.buscar(nombre)) {
                System.out.println("Lo siento, el municipio ingresado ya existe ☹");
            } else {
                if (municipioImpl.addRegistro(nuevoMunicipio)) {
                    System.out.println("Municipio agregado con éxito ✅");
                } else {
                    System.out.println("Hubo un problema al agregar el municipio.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al agregar municipio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void printAll() {
        getConnection();
        List<Municipio> municipios = municipioImpl.findAll();
        if (municipios == null || municipios.isEmpty()) {
            System.out.println("No hay municipios para mostrar.");
        } else {
            municipios.forEach(System.out::println);
        }
    }


    @Override
    public File getFile() {
        return new File("./Municipio.object");
    }

    @Override
    public List<Municipio> edit() {
        return super.edit();
    }

    @Override
    public List<Municipio> delete() {
        return super.delete();
    }
}
