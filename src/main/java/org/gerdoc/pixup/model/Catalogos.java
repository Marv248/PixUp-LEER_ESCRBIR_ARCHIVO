package org.gerdoc.pixup.model;

import org.gerdoc.pixup.jdbc.Conexion;
import org.gerdoc.pixup.jdbc.impl.EstadoJdbcImpl;
import org.gerdoc.pixup.util.ReadUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Catalogos<T extends Catalogo> extends LecturaAccion
{
    protected Conexion conexion;
    protected List<T>list;
    protected T t;
    protected boolean flag2;
    protected File file;
    protected Catalogo catalogo = new Catalogo() {
        @Override
        public boolean buscar(String nombre) {
            return false;
        }

        @Override
        public Integer buscarById(Integer id) {
            return 0;
        }
    };
    protected Connection connection;

    public abstract Integer buscarIdEnBD(Integer id);

    public Catalogos()
    {
        list = new ArrayList<>( );
        if (conexion != null) {
            connection = conexion.getConnection();
        }
        else {
            this.conexion = new EstadoJdbcImpl();
        }
    }

    protected void getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = conexion.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isListEmpty()
    {
        return list.isEmpty();
    }

    public void print( )
    {
        if( isListEmpty( ) )
        {
            System.out.println( "No hay elementos");
        }
        list.stream().forEach( System.out::println );
    }

    public abstract T newT( );
    public abstract boolean processNewT( T t );
    public abstract void processEditT( T t );

    public abstract void addRegistro();
    public abstract void printAll();

    public List<T> edit() {
        getConnection();
        List<T> lista = new ArrayList<>();
        T entidad = newT();

        if (!(entidad instanceof Catalogo)) {
            System.out.println("El objeto no es una instancia válida de Catalogo.");
            return lista;
        }

        Catalogo catalogoT = (Catalogo) entidad;

        try {
            System.out.println("Introduzca el id a modificar: ");
            int id = ReadUtil.readInt();

            Integer resultado = buscarIdEnBD(id);

            if (resultado != null && resultado != -1) {
                System.out.println("Introduzca el nuevo nombre: ");
                String nuevoNombre = ReadUtil.read();

                String nombreTabla = catalogoT.getNombreTabla();
                String sql = "UPDATE " + nombreTabla + " SET nombre=? WHERE id=?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nuevoNombre);
                preparedStatement.setInt(2, id);

                int filasActualizadas = preparedStatement.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("El registro fue actualizado exitosamente.");

                    catalogoT.setId(id);
                    catalogoT.setNombre(nuevoNombre);
                    lista.add((T) catalogoT);
                } else {
                    System.out.println("No se actualizó ninguna fila.");
                }
            } else {
                System.out.println("No se encontró un registro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public List<T> delete() {
        getConnection();
        List<T> lista = new ArrayList<>();
        T entidad = newT();

        if (!(entidad instanceof Catalogo)) {
            System.out.println("El objeto no es una instancia válida de Catalogo.");
            return lista;
        }

        Catalogo catalogoT = (Catalogo) entidad;

        try {
            System.out.println("Introduzca el ID del registro que desea eliminar:");
            int id = ReadUtil.readInt();

            Integer resultado = buscarIdEnBD(id);

            if (resultado != null && resultado != -1) {
                String nombreTabla = catalogoT.getNombreTabla();
                String sql = "DELETE FROM " + nombreTabla + " WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                int filasEliminadas = preparedStatement.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("El registro fue eliminado exitosamente.");
                    catalogoT.setId(id);
                    lista.add((T) catalogoT);
                } else {
                    System.out.println("No se eliminó ninguna fila.");
                }

                preparedStatement.close();
            } else {
                System.out.println("No se encontró un registro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return lista;
    }


    public void remove( )
    {
        if( isListEmpty( ) )
        {
            System.out.println( "No hay elementos a remover" );
            return;
        }
        flag2 = true;
        while ( flag2 )
        {
            System.out.println( "Ingrese el id del elemento a borrar" );
            print( );
            t = list.stream().filter( e -> e.getId().equals( ReadUtil.readInt( ) ) ).findFirst().orElse( null );
            if( t==null )
            {
                System.out.println( "Id incorrecto, intentelo nuevamente" );
            }
            else
            {
                list.remove( t );
                flag2 = false;
                System.out.println( "Elemento borrado" );
            }
        }
    }

    @Override
    public void procesaOpcion()
    {
        switch (opcion)
        {
            case 1:
                addRegistro( );
                break;
            case 2:
                edit();
                break;
            case 3:
                delete( );
                break;
            case 4:
                printAll();
                break;
            case 5:
                salir();
                break;
        }
    }

    private void leerArchivo()
    {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try
        {
            file = getFile( );
            fileInputStream = new FileInputStream( file );
            objectInputStream = new ObjectInputStream( fileInputStream );
            list = (List<T>) objectInputStream.readObject( );
            objectInputStream.close( );
            fileInputStream.close( );
            System.out.println( "Archivo leido con exito");
            for (T elemento : list) {
                System.out.println(elemento);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public abstract File getFile( );

    private void salir(){
        conexion.closeConnection();
    }

    private void guardarArchivo()
    {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            if( isListEmpty() )
            {
                System.out.println( "No hay elementos para guardar");
            }
            else {
                file = getFile();
                fileOutputStream = new FileOutputStream(file);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(list);
                objectOutputStream.close();
                fileOutputStream.close();
                System.out.println("Archivo guardado con exito");
                System.out.println("Ruta: " + file.getAbsolutePath());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("Menú:");
        System.out.println("Seleccione una opcion:");
        System.out.println("1.-Agregar");
        System.out.println("2.-Editar");
        System.out.println("3.-Borrar");
        System.out.println("4.-Imprimir");
        System.out.println("5.-Salir");
    }

    @Override
    public int valorMinMenu( )
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 5;
    }

}