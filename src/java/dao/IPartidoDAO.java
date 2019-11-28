package dao;

import entidades.Partido;
import java.util.List;
import modelos.PartidoVoto;

public interface IPartidoDAO extends IDAO<Partido> {

    int obtenerVotosPorBusqueda(String busqueda) throws Exception;

    List<PartidoVoto> obtenerPartidoVotos() throws Exception;
    
    List<PartidoVoto> obtenerPartidoVotosPorUbigeo(int idUbigeo) throws Exception;
    
    int obtenerVotosPorUbigeoPorBusqueda(int idUbigeo, String busqueda) throws Exception;
    
}
