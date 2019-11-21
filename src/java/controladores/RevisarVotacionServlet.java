package controladores;

import dao.impl.MySQLPartidoDAO;
import dao.impl.MySQLVotoDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

@WebServlet(name = "RevisarVotacionServlet", urlPatterns = {"/revisarVotacion"})
public class RevisarVotacionServlet extends HttpServlet {

    private static MySQLVotoDAO mySQLVotoDAO;
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
            int nulos = mySQLVotoDAO.obtenerNulos();
            
            mySQLVotoDAO = new MySQLVotoDAO();
            int blancos = mySQLVotoDAO.obtenerBlancos();
            
            request.setAttribute("nulos", nulos);
            request.setAttribute("blancos", blancos);
            
            request.getRequestDispatcher(RutasJSP.REVISAR_VOTACION).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RevisarVotacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
