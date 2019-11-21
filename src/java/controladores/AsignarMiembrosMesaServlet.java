package controladores;

import dao.impl.MySQLCiudadanoDAO;
import dao.impl.MySQLMesaDAO;
import entidades.Ciudadano;
import entidades.Mesa;
import entidades.Ubigeo;
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
        String mesaId = request.getParameter("mesa");
        String miembrounoId = request.getParameter("inputMiembrouno");
        String miembrodosId = request.getParameter("inputMiembrodos");
        String miembrotresId = request.getParameter("inputMiembrotres");
        
        try {
            mySQLMesaDAO = new MySQLMesaDAO();
            
            Mesa mesa = new Mesa();
            
            mesa.setUbigeo(new Ubigeo(Integer.parseInt(mesaId)));
            mesa.setMiembrouno(new Ciudadano(Integer.parseInt(miembrounoId)));
            mesa.setMiembrodos(new Ciudadano(Integer.parseInt(miembrodosId)));
            mesa.setMiembrotres(new Ciudadano(Integer.parseInt(miembrotresId)));
           
            boolean mesaActualizada = mySQLMesaDAO.actualizar(mesa);
            
            if (mesaActualizada) {
                request.setAttribute("mensaje", "actualizada");
            } else {
                request.setAttribute("mensaje", "no actualizada");
            }            
        } catch (Exception e) {
            request.setAttribute("mensaje", "no ha sido actualizada");
            request.getRequestDispatcher(RutasJSP.INDEX).forward(request, response);
        } finally{
            doGet(request, response);
        }
    }

}
