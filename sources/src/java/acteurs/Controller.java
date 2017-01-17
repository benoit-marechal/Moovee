/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package acteurs;

import java.io.*;
import java.net.*;
import java.util.*;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
       init(request,response);
          
          
        response.setContentType("text/html;charset=UTF-8");
        
        String id=request.getParameter("id");
         if (id != null) {
             show(id);
         }
        else if(request.getParameter("new")!=null){
            new_form();
        }
        else if(request.getParameter("create")!=null){
            create();
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
    
    // Liste des acteurs
    protected void index(){
            String results = oa.acteurs_index();
                    
            request.setAttribute("results",results);
            disp = request.getRequestDispatcher("index.jsp");
    }
    
    // Affichage d'un acteur
    protected void show(String id){
            
         String results = oa.acteur_show(id);
         String results2 = oa.acteur_show_films(id);
         String results3 = oa.acteur_show_epoux(id);
         String results4 = oa.acteur_show_epoux_films(id);
                 
         request.setAttribute("results",results);
         request.setAttribute("results2",results2);
         request.setAttribute("results3",results3);
         request.setAttribute("results4",results4);
         disp = request.getRequestDispatcher("show.jsp");
    }
    
    // Formulaire de creation d'un nouvel acteur
    protected void new_form(){
        
        String results = oa.films_index_select();
        request.setAttribute("results",results);
        String results2 = oa.acteurs_index_select();
        request.setAttribute("results2",results2);
        disp = request.getRequestDispatcher("new.jsp");
    }
    
    // Ajout d'un acteur dans l'ontology en fonction des parametres données
    protected void create(){
        String prenom = request.getParameter("prenom").toString();
        String nom  = request.getParameter("nom").toString();
        String epoux  = request.getParameter("epoux").toString();
        String film1  = request.getParameter("film1").toString();
        String film2  = request.getParameter("film2").toString();
        String film3  = request.getParameter("film3").toString();
        String film4  = request.getParameter("film4").toString();
        String film5  = request.getParameter("film5").toString();
        
        Vector<String> vect = new Vector<String>();
        vect.add(film1);
        vect.add(film2);
        vect.add(film3);
        vect.add(film4);
        vect.add(film5);
        
        oa.acteur_create(prenom, nom, epoux, vect); 
        oa.save();
       
       index();
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
