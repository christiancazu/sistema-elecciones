    package dao.impl;

import dao.ICiudadanoDAO;
import entidades.Ciudadano;
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

public class MySQLCiudadanoDAO implements ICiudadanoDAO {

    private static final String OBTENER_TODOS = "SELECT * FROM ciudadano";
    private static final String OBTENER_POR_ID = "SELECT * FROM ciudadano WHERE id = ? LIMIT 1";
    private static final String CREAR = "INSERT INTO ciudadano VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE ciudadano SET dni = ?, apellidos = ?, nombres = ?, ubigeo = ?, direccion = ?, sexo = ?, estadocivil = ?, candidato = ?, usuario = ?, clave = ? WHERE id = ?";
    private static final String ELIMINAR = "DELETE FROM ciudadano WHERE id = ?";
    private static final String IDENTIFICAR = "SELECT * FROM ciudadano WHERE usuario = ? AND clave = ? LIMIT 1";
    
    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    public MySQLCiudadanoDAO() {
        connection = MySQLConexion.conectar();
        pstm = null;
        rs = null;
    }

    @Override
    public Ciudadano obtenerPorId(int id) throws Exception {
        Ciudadano ciudadano = null;
        try {
            pstm = connection.prepareStatement(OBTENER_POR_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            rs.next();
            
            ciudadano = new Ciudadano();

            ciudadano.setId(rs.getInt("id"));
            ciudadano.setDni(rs.getInt("dni"));
            ciudadano.setApellidos(rs.getString("apellidos"));
            ciudadano.setNombres(rs.getString("nombres"));
            ciudadano.setDireccion(rs.getString("direccion"));
            ciudadano.setSexo(rs.getString("sexo").charAt(0));
            ciudadano.setEstadocivil(rs.getString("estadocivil").charAt(0));
            ciudadano.setUbigeo(new Ubigeo(rs.getInt("ubigeo")));
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return ciudadano;
    }

    @Override
    public List<Ciudadano> obtenerTodos() throws Exception {
        List<Ciudadano> ciudadanos = null;
        try {
            pstm = connection.prepareStatement(OBTENER_TODOS);
            rs = pstm.executeQuery();

            ciudadanos = new ArrayList<>();
            
            while (rs.next()) {
                Ciudadano ciudadano = new Ciudadano();

                ciudadano.setId(rs.getInt("id"));
                ciudadano.setDni(rs.getInt("dni"));
                ciudadano.setApellidos(rs.getString("apellidos"));
                ciudadano.setNombres(rs.getString("nombres"));
                ciudadano.setDireccion(rs.getString("direccion"));
                ciudadano.setSexo(rs.getString("sexo").charAt(0));
                ciudadano.setEstadocivil(rs.getString("estadocivil").charAt(0));
                ciudadano.setUbigeo(new Ubigeo(rs.getInt("ubigeo")));

                ciudadanos.add(ciudadano);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return ciudadanos;
    }

    @Override
    public boolean crear(Ciudadano ciudadano) throws Exception {
        boolean creado = false;
        try {
            pstm = connection.prepareStatement(CREAR);
            
            pstm.setInt(1, ciudadano.getDni());
            pstm.setString(2, ciudadano.getApellidos());
            pstm.setString(3, ciudadano.getNombres());
            pstm.setInt(4, ciudadano.getUbigeo().getId());
            pstm.setString(5, ciudadano.getDireccion());
            pstm.setString(6, String.valueOf(ciudadano.getSexo()));
            pstm.setString(7, String.valueOf(ciudadano.getEstadocivil()));
            pstm.setBoolean(8, ciudadano.getCandidato());
            pstm.setString(9, Integer.toString(ciudadano.getDni()));
            pstm.setString(10, Integer.toString(ciudadano.getDni()));
            
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
    public boolean actualizar(Ciudadano ciudadano) throws Exception {
        boolean actualizado = false;
        try {
            pstm = connection.prepareStatement(ACTUALIZAR);
            
            pstm.setInt(1, ciudadano.getDni());
            pstm.setString(2, ciudadano.getApellidos());
            pstm.setString(3, ciudadano.getNombres());
            pstm.setInt(4, ciudadano.getUbigeo().getId());
            pstm.setString(5, ciudadano.getDireccion());;
            pstm.setString(6, String.valueOf(ciudadano.getSexo()));
            pstm.setString(7, String.valueOf(ciudadano.getEstadocivil()));
            pstm.setBoolean(8, ciudadano.getCandidato());
            pstm.setString(9, Integer.toString(ciudadano.getDni()));
            pstm.setString(10, Integer.toString(ciudadano.getDni()));
            
            pstm.setInt(11, ciudadano.getId());
            
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

    @Override
    public Ciudadano identificar(String usuario, String clave) throws Exception {
        Ciudadano ciudadano = null;
        try {
            pstm = connection.prepareStatement(IDENTIFICAR);
            pstm.setString(1, usuario);
            pstm.setString(2, clave);
            rs = pstm.executeQuery();

            rs.next();
            
            ciudadano = new Ciudadano();

            ciudadano.setId(rs.getInt("id"));
            ciudadano.setDni(rs.getInt("dni"));
            ciudadano.setApellidos(rs.getString("apellidos"));
            ciudadano.setNombres(rs.getString("nombres"));
            ciudadano.setDireccion(rs.getString("direccion"));
            ciudadano.setSexo(rs.getString("sexo").charAt(0));
            ciudadano.setEstadocivil(rs.getString("estadocivil").charAt(0));
            ciudadano.setUbigeo(new Ubigeo(rs.getInt("ubigeo")));
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCiudadanoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConexion.cerrar();
        }
        return ciudadano;
    }

}
