package pruebas;

import dao.impl.MySQLCiudadanoDAO;
import entidades.Ciudadano;
import java.util.List;

public class CiudadanoPrueba {

    public static MySQLCiudadanoDAO mySQLCiudadanoDAO = new MySQLCiudadanoDAO();
    
    public static void main(String[] args) {
        
        obtenerTodosPorUbigeo(1);
        
    }
    
    public static void obtenerTodosPorUbigeo(int id) {
        try {
            List<Ciudadano> ciudadanos = mySQLCiudadanoDAO.obtenerTodosPorUbigeo(id);
            ciudadanos.stream().forEach(ciudadano -> System.out.println(ciudadano.getNombres()));
        } catch (Exception e) {
        }
    }
    
}
