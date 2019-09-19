package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {

    protected static Connection conexion;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/elecciones";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getting MySQL connection
     *
     */
    public static Connection conectar() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    /**
     * closing MySQL connection if is opened
     *
     * @throws Exception
     */
    public static void cerrar() throws Exception {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
