package controladores;

import dao.impl.MySQLCiudadanoDAO;
import dao.impl.MySQLMesaDAO;
import entidades.Ciudadano;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

@WebServlet(name="AsignarMiembrosMesaServlet", urlPatterns={"/AsignarMiembrosMesa"})
public class AsignarMiembrosMesaServlet extends HttpServlet {
   
    private static MySQLCiudadanoDAO mySQLCiudadanoDAO;
    private static MySQLMesaDAO mySQLMesaDAO;
   
    
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
            mySQLMesaDAO = new MySQLMesaDAO();
            mySQLCiudadanoDAO = new MySQLCiudadanoDAO();
            
            List<Ciudadano> ciudadanos = mySQLCiudadanoDAO.obtenerTodos();

            request.setAttribute("mesas", mySQLMesaDAO.obtenerTodos());
            request.setAttribute("ciudadanos", ciudadanos);
            request.getRequestDispatcher(RutasJSP.ASIGNAR_MIEMBROS_MESA).forward(request, response);
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
