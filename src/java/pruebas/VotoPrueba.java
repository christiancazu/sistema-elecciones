package pruebas;

import dao.impl.MySQLPartidoDAO;
import java.util.List;
import modelos.PartidoVoto;

public class VotoPrueba {
    public static MySQLPartidoDAO mySQLPartidoDAO = new MySQLPartidoDAO();
    
    public static void main(String[] args) throws Exception {
        contar();
    }

    private static void contar() throws Exception {
        
        System.out.println("blancos -> " + mySQLPartidoDAO.obtenerVotosPorBusqueda("blancos"));
 
        mySQLPartidoDAO = new MySQLPartidoDAO();
        
        List<PartidoVoto> partidoVotos = mySQLPartidoDAO.obtenerPartidoVotos();
        partidoVotos.forEach(cnsmr -> System.out.println(cnsmr.getPartido().getNombre() + " -> " + cnsmr.getVotos()));
    
        mySQLPartidoDAO = new MySQLPartidoDAO();
        
        System.out.println("nulos -> " + mySQLPartidoDAO.obtenerVotosPorBusqueda("nulos"));
    }
}
