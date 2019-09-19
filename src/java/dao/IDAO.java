package dao;

import java.util.List;

public interface IDAO<T> {

    T obtenerPorId(int id) throws Exception;

    List<T> obtenerTodos() throws Exception;

    boolean crear(T entity) throws Exception;

    boolean actualizar(T entity) throws Exception;

    boolean eliminar(int id) throws Exception;
    
}
