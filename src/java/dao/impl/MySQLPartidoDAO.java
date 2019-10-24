package dao.impl;

import dao.IPartidoDAO;
import entidades.Ciudadano;
import entidades.Partido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MySQLConexion;

public class MySQLPartidoDAO implements IPartidoDAO {

    private static final String OBTENER_TODOS = "SELECT * FROM partido";
    private static final String OBTENER_POR_ID = "SELECT * FROM partido WHERE id = ? LIMIT 1";
    private static final String CREAR = "INSERT INTO partido VALUES(null, ?, ?, ?)";
    private static final String ASIGNAR_CANDIDATO = "UPDATE ciudadano SET candidato = 1 where id = ?";
    private static final String ACTUALIZAR = "UPDATE partido SET nombre = ?, imagen = ?, ciudadano = ? WHERE id = ?";
    private static final String ELIMINAR = "DELETE FROM partido WHERE id = ?";
    
    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    public MySQLPartidoDAO() {
        connection = MySQLConexion.conectar();
        pstm = null;
        rs = null;
    }
    
    @Override
    public Partido obtenerPorId(int id) throws Exception {
        Partido partido = null;
        try {
            pstm = connection.prepareStatement(OBTENER_POR_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            rs.next();
            
            partido = new Partido();

            partido.setId(rs.getInt("id"));
            partido.setNombre(rs.getString("nombre"));
            partido.setImagen(rs.getString("imagen"));
            partido.setCiudadano(new Ciudadano(rs.getInt("ciudadano")));
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return partido;
    }

    @Override
    public List<Partido> obtenerTodos() throws Exception {
        List<Partido> partidos = null;
        try {
            pstm = connection.prepareStatement(OBTENER_TODOS);
            rs = pstm.executeQuery();

            partidos = new ArrayList<>();
            
            while (rs.next()) {
                Partido partido = new Partido();

                partido.setId(rs.getInt("id"));
                partido.setNombre(rs.getString("nombre"));
                partido.setImagen(rs.getString("imagen"));
                partido.setCiudadano(new Ciudadano(rs.getInt("ciudadano")));

                partidos.add(partido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return partidos;
    }

    @Override
    public boolean crear(Partido partido) throws Exception {
        boolean creado = false;
        try {
            pstm = connection.prepareStatement(CREAR);
            
            pstm.setString(1, partido.getNombre());
            pstm.setString(2, partido.getImagen());
            pstm.setInt(3, partido.getCiudadano().getId());
            
            pstm.executeUpdate();
            
            pstm = connection.prepareStatement(ASIGNAR_CANDIDATO);
            pstm.setInt(1, partido.getCiudadano().getId());
            
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
    public boolean actualizar(Partido partido) throws Exception {
        boolean actualizado = false;
        try {
            pstm = connection.prepareStatement(ACTUALIZAR);
            
            pstm.setString(1, partido.getNombre());
            pstm.setString(2, partido.getImagen());
            pstm.setInt(3, partido.getCiudadano().getId());

            
            pstm.setInt(4, partido.getId());
            
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
