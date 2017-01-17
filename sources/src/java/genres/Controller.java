/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package genres;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Fildzzz
 */
public class Controller extends HttpServlet {
       // Variable de classe
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected PrintWriter out;
    protected ontology.OntologyAccess oa;
    protected RequestDispatcher disp ; 
    
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
    throws ServletException, IOException {
        
       init(request,response);
          
          
        response.setContentType("text/html;charset=UTF-8");
        
        String id=request.getParameter("id");
         if (id != null) {
             show(id);
         }
         else {
            index();
        }
            
            disp.forward(request,response);
    } 

      // Initialisation des variables de classes
    protected void init(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        try {out = response.getWriter(); }catch(Exception e){ e.printStackTrace();}
        oa = new ontology.OntologyAccess();
    }
    
    // Liste des genres
    protected void index(){
            String results = oa.genres_index();
            request.setAttribute("results",results);
            disp = request.getRequestDispatcher("index.jsp");
    }
    
    
    // Affichage des films d'un genre
    protected void show(String id){
        String results = oa.genre_show_films(id);
         request.setAttribute("results",results);
         
         disp = request.getRequestDispatcher("show.jsp");
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
