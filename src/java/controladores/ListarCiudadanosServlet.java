package controladores;

import dao.impl.MySQLCiudadanoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

@WebServlet(urlPatterns = {"/listarCiudadanos"})
public class ListarCiudadanosServlet extends HttpServlet {

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
        try {
            mySQLCiudadanoDAO = new MySQLCiudadanoDAO();
            request.setAttribute("ciudadanos", mySQLCiudadanoDAO.obtenerTodos());
            request.getRequestDispatcher(RutasJSP.LISTAR_CIUDADANOS).forward(request, response);
        } catch (Exception e) {
        }
    }

}
