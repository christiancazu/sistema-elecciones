package dao.impl;

import dao.IVotoDAO;
import entidades.Voto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MySQLConexion;

public class MySQLVotoDAO implements IVotoDAO {

    private static final String OBTENER_TODOS = "SELECT * FROM voto";
    private static final String ASIGNAR_EMITIDO = "UPDATE ciudadano SET emitido = 1 where id = ?";
    private static final String VERIFICAR_VOTO_EMITIDO = "SELECT * FROM voto where ciudadano = ?";

    private static final String CREAR = "INSERT INTO voto VALUES(null, ?, ?)";

    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    public MySQLVotoDAO() {
        connection = MySQLConexion.conectar();
        pstm = null;
        rs = null;
    }
    
    @Override
    public Voto obtenerPorId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Voto> obtenerTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean crear(Voto voto) throws Exception {
        boolean creado = false;

        try {
            // verificando si el usuario ya emitió el voto
            pstm = connection.prepareStatement(VERIFICAR_VOTO_EMITIDO);
            
            pstm.setInt(1, voto.getCiudadano().getId());
            
            rs = pstm.executeQuery();  
            
            // retorna falso si encuentra al ciudadano en la tabla voto
            if (rs.next()) return false;
            
            pstm = null;
            
            pstm = connection.prepareStatement(CREAR);
            
            pstm.setInt(1, voto.getCiudadano().getId());
            if (voto.getEleccion() == null) {
                pstm.setString(2, null);
            } else {
                pstm.setInt(2, voto.getEleccion());
            }
            
            pstm.executeUpdate();
            
            // asignar voto emitido en tabla ciudadano 
            pstm = connection.prepareStatement(ASIGNAR_EMITIDO);
            
            pstm.setInt(1, voto.getCiudadano().getId());
            
            pstm.executeUpdate();
            
            creado = true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return creado;
    }

    @Override
    public boolean actualizar(Voto voto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
