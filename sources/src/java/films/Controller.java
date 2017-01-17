/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package films;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

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
    * AIGUILLAGE
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        init(request,response);
        response.setContentType("text/html;charset=UTF-8");
            
        if (request.getParameter("add_note") != null) {
           add_note();
        }    
        else if (request.getParameter("add_commentaire") != null) {
           add_commentaire();
        }   
        else if (request.getParameter("new") != null) {
           new_form();
        }   
        else if (request.getParameter("create") != null) {
           create();
        }
        else if(request.getParameter("id") != null)
        {
            show();
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
    
    // List des films
    protected void index() throws ServletException, IOException{

        String results = oa.films_index();
        
        request.setAttribute("results",results);
      
        disp = request.getRequestDispatcher("index.jsp");
    }
    
    // Affichage d'un film
    protected void show()  throws ServletException, IOException{
         
        String id=request.getParameter("id");
        
        String results = oa.film_show(id);
        String results2 = oa.film_show_acteurs(id);
        String results3 = oa.film_show_notes(id);
        String results4 = oa.film_show_commentaires(id);
         
        request.setAttribute("results",results);
        request.setAttribute("results2",results2);
        request.setAttribute("results3",results3);
        request.setAttribute("results4",results4);
      
        disp = request.getRequestDispatcher("show.jsp");
    }

    protected void add_note() {
        oa.film_add_note(request.getParameter("id").toString(), 
        request.getParameter("note").toString());
        oa.save();
        try {show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    protected void add_commentaire() {
        oa.film_add_commentaire(request.getParameter("id").toString(), 
        request.getParameter("login").toString(),
                request.getParameter("contenu").toString());
        oa.save();
        try {show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     // Formulaire de creation d'un nouvel acteur
    protected void new_form(){
        
        
        request.setAttribute("results",oa.realisateurs_index_select());
        
        request.setAttribute("results2",oa.acteurs_index_select());
        
        request.setAttribute("genres",oa.genres_index_select());
        
        disp = request.getRequestDispatcher("new.jsp");
    }
    
    // Ajout d'un acteur dans l'ontology en fonction des parametres données
    protected void create(){
        String titre = request.getParameter("titre").toString();
        String annee  = request.getParameter("annee").toString();
        String realisateur  = request.getParameter("realisateur").toString();
        String genre  = request.getParameter("genre").toString();
        String acteur1  = request.getParameter("acteur1").toString();
        String acteur2  = request.getParameter("acteur2").toString();
        String acteur3  = request.getParameter("acteur3").toString();
        String acteur4  = request.getParameter("acteur4").toString();
        String acteur5  = request.getParameter("acteur5").toString();
        
        Vector<String> vect = new Vector<String>();
        vect.add(acteur1);
        vect.add(acteur2);
        vect.add(acteur3);
        vect.add(acteur4);
        vect.add(acteur5);
        
        oa.film_create(titre, annee, realisateur,genre, vect); 
        oa.save();
       
       try {index();}
       catch(Exception e){ e.printStackTrace();}
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
