package controladores;

import dao.impl.MySQLPartidoDAO;
import dao.impl.MySQLVotoDAO;
import java.io.IOException;
import java.util.List;
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

        try {            
            mySQLVotoDAO = new MySQLVotoDAO();
            int total = mySQLVotoDAO.obtenerTodos().size();
            
            mySQLPartidoDAO = new MySQLPartidoDAO();
            int blancos = mySQLPartidoDAO.obtenerVotosPorBusqueda("blancos");
            
            mySQLPartidoDAO = new MySQLPartidoDAO();
            int nulos = mySQLPartidoDAO.obtenerVotosPorBusqueda("nulos");
            
            mySQLPartidoDAO = new MySQLPartidoDAO();
            List<PartidoVoto> partidoVotos = mySQLPartidoDAO.obtenerPartidoVotos();

            request.setAttribute("total", total);
            request.setAttribute("nulos", nulos);
            request.setAttribute("blancos", blancos);
            request.setAttribute("partidoVotos", partidoVotos);
            
            request.getRequestDispatcher(RutasJSP.REVISAR_VOTACION).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RevisarVotacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
