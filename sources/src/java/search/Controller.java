/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package search;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import ontology.OntologyAccess;

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
        
        String advanced = request.getParameter("advanced");
        String sparql = request.getParameter("sparql");
        
        if (request.getParameter("advanced")!=null)
        {
           if (request.getParameter("titre")!=null) { advanced_search_titre(request.getParameter("titre"));}
           else if (request.getParameter("annee")!=null) { advanced_search_annee(request.getParameter("annee"));}
           else if (request.getParameter("realisateur")!=null) { advanced_search_realisateur(request.getParameter("realisateur"));}
           
           disp = request.getRequestDispatcher("advanced_search.jsp"); 
        }
        else if(sparql!=null){
            String q = request.getParameter("q");
            if (q!=null) { sparql(q); }
            disp = request.getRequestDispatcher("sparql.jsp"); 
        }
        else {
            String q = request.getParameter("q");
            if (q!=null) { search(q); }
            request.setAttribute("q",q);
            disp = request.getRequestDispatcher("search.jsp");
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
    
    
    // Recherche simple
    protected void search(String q){

        String resultsActeurs = oa.search_acteurs(q);
        String resultsFilms = oa.search_films(q);
        
        request.setAttribute("resultsActeurs",resultsActeurs);
        request.setAttribute("resultsFilms",resultsFilms);
    }
    
     protected void advanced_search_titre(String titre) {

        String resultsFilms = oa.search_films(titre); 
        request.setAttribute("resultsFilms",resultsFilms);
     }
     protected void advanced_search_annee(String annee) {

        String resultsFilms = oa.search_films_annee(annee); 
        request.setAttribute("resultsFilms",resultsFilms);
     }
     protected void advanced_search_realisateur(String r) {

        String resultsFilms = oa.search_films_realisateur(r); 
        request.setAttribute("resultsFilms",resultsFilms);
     }
    // Recherche avancée
    protected void advanced_search() {
            System.out.println("----------------------");
            String titre=request.getParameter("titre");
            String annee=request.getParameter("annee");
            String genre=request.getParameter("genre");
            String realisateur=request.getParameter("realisateur");
            
            System.out.println("annee "+annee);
            System.out.println("titre "+titre);
            System.out.println("genre " +genre);
            System.out.println("realisateur "+realisateur);
            System.out.println("----------------------");

//               String queryString =  "SELECT ?titre ?annee  ?realisateur_prenom ?realisateur_nom\n"+
//               "WHERE { \n"+
//               "?film :titre ?titre .\n" +
//               "?film :annee ?annee .\n" +
//               "?film :est_realise_par ?realisateur .\n" +
//               "?realisateur :nom ?realisateur_nom .\n" +
//               "?realisateur :prenom ?realisateur_prenom }";
               
 String queryString =  "SELECT ?titre ?annee  ?realisateur_prenom ?realisateur_nom\n"+
               "WHERE { \n"+
               "?film :titre ?titre .\n" +
               "?film :annee ?annee .\n" +
               "?film :est_realise_par ?realisateur .\n" +
               "?realisateur :nom ?realisateur_nom .\n" +
               "?realisateur :prenom ?realisateur_prenom \n" +
               "FILTER ( regex(?realisateur_nom, \""+realisateur+"\",\"i\") && regex(?titre, \""+titre+"\",\"i\")  ) }";

                String resultsFilms = oa.processSPARQLandXSLT(queryString,"films_index.xsl");
                request.setAttribute("resultsFilms",resultsFilms);
                
            }
       
    // Execution de requete  SPARQL
        protected void sparql(String q){
            q = q.replace("\\r", "\n");
           
            System.out.println(q);
                String results = oa.processSPARQLandXSLT(q,"style.xsl");
                request.setAttribute("q",q);
                request.setAttribute("results",results);
                
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
