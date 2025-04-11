package org.gerdoc.pixup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public abstract class Conexion<T>
{
    public static String user = "root";
    public static String password = "admin";
    public static String db = "pixup";
    public static String server = "127.0.0.1";
    protected Connection connection;

    public Conexion()
    {
        openConnection();
    }

    public boolean testDriver()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean loadConnection(String user, String password, String db, String server)
    {
        String url = null;
        if (user == null || password == null || db == null || server == null)
        {
            return false;
        }
        if ("".equals(user) || "".equals(password) || "".equals(db) || "".equals(server))
        {
            return false;
        }
        url = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", server, db, user, password);
        try
        {
            if (!testDriver( ) )
            {
                return false;
            }
            connection = DriverManager.getConnection(url);
            return connection != null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean openConnection() {
        try {
            if( connection == null || connection.isClosed()) {
                if( !loadConnection( user, password, db, server ) ) {
                    return false;
                }
            }
            return connection.isClosed();
        }
        catch (SQLException e) {
            System.out.println(e);
            return true;
        }
    }

    public void closeConnection( )
    {
        try
        {
            if (connection == null)
            {
                return;
            }
            if (connection.isClosed())
            {
                return;
            }
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                openConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public abstract boolean addRegistro(T t);
    public abstract List<T> findAll();
}
