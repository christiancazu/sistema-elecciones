package controladores;

import dao.impl.MySQLCiudadanoDAO;
import dao.impl.MySQLUbigeoDAO;
import entidades.Ciudadano;
import entidades.Ubigeo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

@WebServlet(name = "RegistrarCiudadanoServlet", urlPatterns = {"/registrarCiudadano"})
public class RegistrarCiudadanoServlet extends HttpServlet {

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
        try {
            mySQLUbigeoDAO = new MySQLUbigeoDAO();
            request.setAttribute("ubigeos", mySQLUbigeoDAO.obtenerTodos());
            request.getRequestDispatcher(RutasJSP.REGISTRAR_CIUDADANO).forward(request, response);
        } catch (Exception e) {
        }
    }

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
        int dni = Integer.parseInt(request.getParameter(("dni")));
        String apellidos = request.getParameter("apellidos");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        int ubigeo = Integer.parseInt(request.getParameter("ubigeo"));
        String sexo = request.getParameter("sexo");
        String estadocivil = request.getParameter("estadocivil");
      
        try {
            Ciudadano ciudadano = new Ciudadano();
            ciudadano.setDni(dni);
            ciudadano.setApellidos(apellidos);
            ciudadano.setNombres(nombres);
            ciudadano.setDireccion(direccion);
            ciudadano.setUbigeo(new Ubigeo(ubigeo));
            ciudadano.setSexo(sexo.charAt(0));
            ciudadano.setEstadocivil(estadocivil.charAt(0));
            
            mySQLCiudadanoDAO = new MySQLCiudadanoDAO();            
            
            Boolean creado = mySQLCiudadanoDAO.crear(ciudadano);
            if (creado) {
                request.setAttribute("mensaje", "creado");
            } else {
                request.setAttribute("mensaje", "ya existe");
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "no ha sido creado");
        } finally{
            doGet(request, response);
        }
    }

}
