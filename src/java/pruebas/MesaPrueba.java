package pruebas;

import dao.impl.MySQLMesaDAO;
import entidades.Ciudadano;
import entidades.Mesa;
import entidades.Ubigeo;

public class MesaPrueba {

    public static MySQLMesaDAO mySQLMesaDAO = new MySQLMesaDAO();
    
    public static void main(String[] args) {
        crear();
    }
    
    public static void crear() {
        Mesa mesa = new Mesa();
        mesa.setUbigeo(new Ubigeo(1));
        mesa.setMiembrouno(new Ciudadano(2));
        mesa.setMiembrodos(new Ciudadano(3));
        mesa.setMiembrotres(new Ciudadano(10));
        
        try {
            boolean crear = mySQLMesaDAO.crear(mesa);
            System.out.println("creado: " + crear);
        } catch (Exception e) {
        }
        
    }
    
}
