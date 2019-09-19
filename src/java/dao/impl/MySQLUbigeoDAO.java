package dao.impl;

import dao.IUbigeoDAO;
import entidades.Ubigeo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MySQLConexion;

public class MySQLUbigeoDAO implements IUbigeoDAO {

    private static final String OBTENER_TODOS = "SELECT * FROM ubigeo";
    
    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    public MySQLUbigeoDAO() {
        connection = MySQLConexion.conectar();
        pstm = null;
        rs = null;
    }
    
    @Override
    public Ubigeo obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ubigeo> obtenerTodos() {
        List<Ubigeo> ubigeos = null;        
        try {
            connection = MySQLConexion.conectar();
            pstm = connection.prepareStatement(OBTENER_TODOS);
            rs = pstm.executeQuery();
            
            ubigeos = new ArrayList<>();
            while (rs.next()) {
                Ubigeo ciudadano = new Ubigeo();
                ciudadano.setId(rs.getInt("id"));
                ciudadano.setNombre(rs.getString("nombre"));
                ubigeos.add(ciudadano);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUbigeoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return ubigeos;
    }

    @Override
    public boolean crear(Ubigeo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Ubigeo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
