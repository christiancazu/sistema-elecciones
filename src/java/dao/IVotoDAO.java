package dao;

import entidades.Voto;

public interface IVotoDAO extends IDAO<Voto> {
    
    int obtenerNulos() throws Exception;
    
    int obtenerBlancos() throws Exception;

}
