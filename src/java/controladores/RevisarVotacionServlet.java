package controladores;

import com.google.gson.Gson;
import dao.impl.MySQLCiudadanoDAO;
import dao.impl.MySQLPartidoDAO;
import dao.impl.MySQLUbigeoDAO;
import dao.impl.MySQLVotoDAO;
import entidades.Ubigeo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.PartidoVoto;
import utils.RutasJSP;

@WebServlet(name = "RevisarVotacionServlet", urlPatterns = {"/revisarVotacion"})
public class RevisarVotacionServlet extends HttpServlet {

    private static MySQLVotoDAO mySQLVotoDAO;
    private static MySQLPartidoDAO mySQLPartidoDAO;
    private static MySQLUbigeoDAO mySQLUbigeoDAO;
    private static MySQLCiudadanoDAO mySQLCiudadanoDAO;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameterMap().containsKey("ubigeo")) {
            
            String ubigeo = request.getParameter("ubigeo");

            if (ubigeo.equals("general")) {
                votacionGeneral(request, response);
            } else {
                votacionporUbigeo(request, response);
            }
            return;
        }

        try {
            mySQLUbigeoDAO = new MySQLUbigeoDAO();
            List<Ubigeo> ubigeos = mySQLUbigeoDAO.obtenerTodos();

            request.setAttribute("ubigeos", ubigeos);

            request.getRequestDispatcher(RutasJSP.REVISAR_VOTACION).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RevisarVotacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void votacionGeneral(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            mySQLVotoDAO = new MySQLVotoDAO();
            int total = mySQLVotoDAO.obtenerTodos().size();

            mySQLPartidoDAO = new MySQLPartidoDAO();
            int blancos = mySQLPartidoDAO.obtenerVotosPorBusqueda("blancos");

            mySQLPartidoDAO = new MySQLPartidoDAO();
            int nulos = mySQLPartidoDAO.obtenerVotosPorBusqueda("nulos");

            mySQLPartidoDAO = new MySQLPartidoDAO();
            List<PartidoVoto> partidoVotos = mySQLPartidoDAO.obtenerPartidoVotos();

            // cabeceras para responder JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> datos = new HashMap();

            datos.put("nombreUbigeo", "GENERALES");
            datos.put("total", total);
            datos.put("nulos", nulos);
            datos.put("blancos", blancos);
            datos.put("partidoVotos", partidoVotos);

            String json = new Gson().toJson(datos);
            response.getWriter().write(json);

        } catch (Exception ex) {
            Logger.getLogger(RevisarVotacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void votacionporUbigeo(HttpServletRequest request, HttpServletResponse response) {
        String idUbigeo = request.getParameter("ubigeo");
        
        try {
            mySQLCiudadanoDAO = new MySQLCiudadanoDAO();
            int total = mySQLCiudadanoDAO.obtenerTodosPorUbigeoEmitidos(Integer.parseInt(idUbigeo)).size();

            mySQLPartidoDAO = new MySQLPartidoDAO();
            int nulos = mySQLPartidoDAO.obtenerVotosPorUbigeoPorBusqueda(Integer.parseInt(idUbigeo), "nulos");
            
            mySQLPartidoDAO = new MySQLPartidoDAO();
            int blancos = mySQLPartidoDAO.obtenerVotosPorUbigeoPorBusqueda(Integer.parseInt(idUbigeo), "blancos");

            mySQLPartidoDAO = new MySQLPartidoDAO();
            List<PartidoVoto> partidoVotos = mySQLPartidoDAO.obtenerPartidoVotosPorUbigeo(Integer.parseInt(idUbigeo));
            

            // cabeceras para responder JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> datos = new HashMap();
            
            mySQLUbigeoDAO = new MySQLUbigeoDAO();
            
            String nombreUbigeo = mySQLUbigeoDAO.obtenerPorId(Integer.parseInt(idUbigeo)).getNombre();         

            datos.put("nombreUbigeo", nombreUbigeo);
            datos.put("total", total);
            datos.put("nulos", nulos);
            datos.put("blancos", blancos);
            datos.put("partidoVotos", partidoVotos);
            
            String json = new Gson().toJson(datos);
            response.getWriter().write(json);

        } catch (Exception ex) {
            Logger.getLogger(RevisarVotacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
