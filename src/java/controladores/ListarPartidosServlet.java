package controladores;

import dao.impl.MySQLPartidoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="ListarPartidosServlet", urlPatterns={"/listarPartidos"})
public class ListarPartidosServlet extends HttpServlet {
   
    private static MySQLPartidoDAO mySQLPartidoDAO;
    
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            mySQLPartidoDAO = new MySQLPartidoDAO();
            request.setAttribute("partidos", mySQLPartidoDAO.obtenerTodos());
            request.getRequestDispatcher(RutasJSP.LISTAR_PARTIDOS).forward(request, response);
        } catch (Exception e) {
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

}
