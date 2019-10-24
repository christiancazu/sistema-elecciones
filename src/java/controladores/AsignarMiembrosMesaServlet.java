package controladores;

import dao.impl.MySQLCiudadanoDAO;
import dao.impl.MySQLUbigeoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

@WebServlet(name="AsignarMiembrosMesaServlet", urlPatterns={"/AsignarMiembrosMesa"})
public class AsignarMiembrosMesaServlet extends HttpServlet {
   
    private static MySQLUbigeoDAO mySQLUbigeoDAO;
    private static MySQLCiudadanoDAO mySQLCiudadanoDAO;
    
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
            mySQLUbigeoDAO = new MySQLUbigeoDAO();
            request.setAttribute("ubigeos", mySQLUbigeoDAO.obtenerTodos());
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
