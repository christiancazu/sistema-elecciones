package controladores;

import dao.impl.MySQLPartidoDAO;
import dao.impl.MySQLVotoDAO;
import entidades.Ciudadano;
import entidades.Partido;
import entidades.Voto;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.RutasJSP;

@WebServlet(name = "VotacionCiudadanoServlet", urlPatterns = {"/votacionCiudadano"})
public class VotacionCiudadanoServlet extends HttpServlet {

    private static MySQLPartidoDAO mySQLPartidoDAO;
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

        mySQLPartidoDAO = new MySQLPartidoDAO();

        try {
            List<Partido> partidos = mySQLPartidoDAO.obtenerTodos();
            request.setAttribute("partidos", partidos);
            
            Ciudadano ciudadano = (Ciudadano) request.getAttribute("ciudadano");
            
            System.out.println(ciudadano.getNombres());
            request.getRequestDispatcher(RutasJSP.VOTACION_CIUDADANO).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VotacionCiudadanoServlet.class.getName()).log(Level.SEVERE, null, ex);
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

        String eleccion = request.getParameter("eleccion");
        String ciudadanoId = request.getParameter("ciudadano");
        
        System.out.println("ele " + eleccion + " " + ciudadanoId);

        Voto voto = new Voto();
        voto.setCiudadano(new Ciudadano(Integer.parseInt(ciudadanoId)));

        // asignando tipo de voto
        switch (eleccion) {
            case "blanco":
                voto.setEleccion(0);
                break;
            case "nulo":
                break;
            default:
                voto.setEleccion(Integer.parseInt(eleccion));
        }

        System.out.println("voto " + eleccion);
        
        try {
            mySQLVotoDAO = new MySQLVotoDAO();

            boolean votoEmitiado = mySQLVotoDAO.crear(voto);
            
            if (votoEmitiado) {
                response.getWriter().write("correcto");
            }
            else {
                response.getWriter().write("error");
            }
        } catch (Exception ex) {
            response.getWriter().write("error");
            Logger.getLogger(VotacionCiudadanoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
