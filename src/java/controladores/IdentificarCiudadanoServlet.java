package controladores;

import dao.impl.MySQLCiudadanoDAO;
import entidades.Ciudadano;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;
import utils.VerificarRol;

@WebServlet(name = "IdentificarCiudadanoServlet", urlPatterns = {"/identificarCiudadano"})
public class IdentificarCiudadanoServlet extends HttpServlet {

    private static MySQLCiudadanoDAO mySQLCiudadanoDAO;
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        
        Ciudadano ciudadano = null;
        try {
            mySQLCiudadanoDAO = new MySQLCiudadanoDAO();
            ciudadano = mySQLCiudadanoDAO.identificar(usuario, clave);           
                        
            if (ciudadano.getId().equals(null)) {
                throw new Error();
            }
            if (VerificarRol.esAdmin(ciudadano)) {
                ListarCiudadanosServlet lcs = new ListarCiudadanosServlet();
                lcs.doGet(request, response);
                return;
            }
            request.setAttribute("ciudadano", ciudadano);
            request.getRequestDispatcher(RutasJSP.VOTACION_CIUDADANO).forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("mensaje", "no es válido");
            request.getRequestDispatcher(RutasJSP.INDEX).forward(request, response);
        }
    }

}
