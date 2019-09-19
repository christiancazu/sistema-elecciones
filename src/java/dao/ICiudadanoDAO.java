package dao;

import entidades.Ciudadano;

public interface ICiudadanoDAO extends IDAO<Ciudadano> {
    Ciudadano identificar(String usuario, String clave) throws Exception;
}
