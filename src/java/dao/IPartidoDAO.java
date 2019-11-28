package dao;

import entidades.Partido;
import java.util.List;
import modelos.PartidoVoto;

public interface IPartidoDAO extends IDAO<Partido> {

    int obtenerVotosPorBusqueda(String busqueda) throws Exception;

    List<PartidoVoto> obtenerPartidoVotos() throws Exception;
    
}
