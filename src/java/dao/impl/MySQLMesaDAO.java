package dao.impl;

import dao.IMesaDAO;
import entidades.Ciudadano;
import entidades.Mesa;
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

public class MySQLMesaDAO implements IMesaDAO {

    private static final String OBTENER_TODOS = "SELECT * FROM mesa";
    private static final String OBTENER_POR_ID = "SELECT * FROM mesa WHERE id = ? LIMIT 1";
    private static final String CREAR = "INSERT INTO mesa VALUES(null, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE mesa SET ubigeo = ?, miembrouno = ?, miembrodos = ?, miembrotres = ? WHERE id = ?";
    private static final String ELIMINAR = "DELETE FROM mesa WHERE id = ?";
    
    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    public MySQLMesaDAO() {
        connection = MySQLConexion.conectar();
        pstm = null;
        rs = null;
    }
    
    @Override
    public Mesa obtenerPorId(int id) throws Exception {
        Mesa mesa = null;
        try {
            pstm = connection.prepareStatement(OBTENER_POR_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            rs.next();
            
            mesa = new Mesa();

            mesa.setId(rs.getInt("id"));
            mesa.setUbigeo(new Ubigeo(rs.getInt("ubigeo")));
            mesa.setMiembrouno(new Ciudadano(rs.getInt("miembrouno")));
            mesa.setMiembrodos(new Ciudadano(rs.getInt("miembrodos")));
            mesa.setMiembrotres(new Ciudadano(rs.getInt("miembrotres")));
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return mesa;
    }

    @Override
    public List<Mesa> obtenerTodos() throws Exception {
        List<Mesa> mesas = null;
        try {
            pstm = connection.prepareStatement(OBTENER_TODOS);
            rs = pstm.executeQuery();

            mesas = new ArrayList<>();
            
            while (rs.next()) {
                Mesa mesa = new Mesa();

                mesa.setId(rs.getInt("id"));
                mesa.setUbigeo(new Ubigeo(rs.getInt("ubigeo")));
                mesa.setMiembrouno(new Ciudadano(rs.getInt("miembrouno")));
                mesa.setMiembrodos(new Ciudadano(rs.getInt("miembrodos")));
                mesa.setMiembrotres(new Ciudadano(rs.getInt("miembrotres")));

                mesas.add(mesa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return mesas;
    }

    @Override
    public boolean crear(Mesa mesa) throws Exception {
        boolean creado = false;
        try {
            pstm = connection.prepareStatement(CREAR);
            
            pstm.setInt(1, mesa.getUbigeo().getId());
            pstm.setInt(2, mesa.getMiembrouno().getId());
            pstm.setInt(3, mesa.getMiembrodos().getId());
            pstm.setInt(4, mesa.getMiembrotres().getId());
            
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
    public boolean actualizar(Mesa mesa) throws Exception {
        boolean actualizado = false;
        try {
            pstm = connection.prepareStatement(ACTUALIZAR);
            
            pstm.setInt(1, mesa.getUbigeo().getId());
            pstm.setInt(2, mesa.getMiembrouno().getId());
            pstm.setInt(3, mesa.getMiembrodos().getId());
            pstm.setInt(4, mesa.getMiembrotres().getId());
            
            pstm.setInt(5, mesa.getId());
            
            pstm.executeUpdate();
            
            actualizado = true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return actualizado;
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        boolean eliminado = false;
        try {
            pstm = connection.prepareStatement(ELIMINAR);
            
            pstm.setInt(1, id);
            pstm.executeUpdate();
            
            eliminado = true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return eliminado;
    }

}
