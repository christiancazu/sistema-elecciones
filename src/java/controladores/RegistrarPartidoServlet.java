package controladores;

import dao.impl.MySQLCiudadanoDAO;
import dao.impl.MySQLPartidoDAO;
import entidades.Ciudadano;
import entidades.Partido;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.ManejadorArchivo;
import utils.RutasJSP;

@MultipartConfig
@WebServlet(name="RegistrarPartidoServlet", urlPatterns={"/registrarPartido"})
public class RegistrarPartidoServlet extends HttpServlet {

    private static MySQLCiudadanoDAO mySQLCiudadanoDAO;
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
            mySQLCiudadanoDAO = new MySQLCiudadanoDAO();
            request.setAttribute("ciudadanos", mySQLCiudadanoDAO.obtenerTodosNoMiembrosDeMesa());
            request.getRequestDispatcher(RutasJSP.REGISTRAR_PARTIDO).forward(request, response);
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
        
        
        String nombre = request.getParameter("nombre");
        int ciudadano = Integer.parseInt(request.getParameter("ciudadano"));
        Part imagen = request.getPart("imagen");
        
        try {
            String imagenGenerada = ManejadorArchivo.generateFullFileName(imagen);
            
            Partido partido = new Partido();
            
            partido.setNombre(nombre);
            partido.setCiudadano(new Ciudadano(ciudadano));
            partido.setImagen(imagenGenerada);
            
            mySQLPartidoDAO = new MySQLPartidoDAO();
            
            boolean partidoRegistrado = mySQLPartidoDAO.crear(partido);
            
            if (partidoRegistrado) {
                ManejadorArchivo.save(imagen, imagenGenerada);
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
