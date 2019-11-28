package dao;

import entidades.Ciudadano;
import java.util.List;

public interface ICiudadanoDAO extends IDAO<Ciudadano> {
    
    Ciudadano identificar(String usuario, String clave) throws Exception;
    
    List<Ciudadano> obtenerTodosPorUbigeo(int ubigeo) throws Exception;
    
    List<Ciudadano> obtenerTodosPorUbigeoEmitidos(int ubigeo) throws Exception;
    
    List<Ciudadano> obtenerTodosNoMiembrosDeMesa() throws Exception;
    
}
