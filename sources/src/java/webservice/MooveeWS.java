/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ontology.OntologyAccess;
/**
 *
 * @author Fildzzz
 */
@WebService()
public class MooveeWS {
    private OntologyAccess oa = new OntologyAccess();
     /**
     * Web service search_actor 
     */
    @WebMethod(operationName = "search_acteurs")
    public String search_acteurs(@WebParam(name = "q")
    String q) {
        return oa.search_acteurs(q);
    }

    @WebMethod(operationName = "search_films")
    public String search_films(@WebParam(name = "q")
    String q) {
        return oa.search_films(q);
    }
    
    @WebMethod(operationName = "films_index")
    public String films_index() { return oa.films_index(); }

    @WebMethod(operationName = "film_show")
    public String film_show(@WebParam(name = "id")
            String id) { return oa.film_show(id); }
    
    @WebMethod(operationName = "film_show_acteurs")
    public String film_show_acteurs(@WebParam(name = "id")
            String id) { return oa.film_show_acteurs(id); }
    
    @WebMethod(operationName = "film_show_notes")
    public String film_show_notes(@WebParam(name = "id")
            String id) { return oa.film_show_notes(id); }  
    
    @WebMethod(operationName = "film_show_commentaires")
    public String film_show_commentaires(@WebParam(name = "id")
            String id) { return oa.film_show_commentaires(id); }   
    
    @WebMethod(operationName = "film_add_note")
    public String film_add_note(@WebParam(name = "id")
            String id,@WebParam(name = "note")
            String note) { 
        return oa.film_add_note(id,note); 
    }    
    
    @WebMethod(operationName = "film_add_commentaire")
    public String film_add_commentaire(@WebParam(name = "id")
            String id,@WebParam(name = "login")
            String login,@WebParam(name = "contenu")
            String contenu) { 
        return oa.film_add_commentaire(id,login,contenu); 
    }
    
    
    @WebMethod(operationName = "film_create")
    public String film_create(
            @WebParam(name = "titre") String titre,
            @WebParam(name = "annee") String annee,
            @WebParam(name = "realisateur") String realisateur,
            @WebParam(name = "genre") String genre
            ) { 
        return oa.film_create(titre,annee,realisateur,genre,null); 
    }    
        
    @WebMethod(operationName = "acteurs_index")
    public String acteurs_index() { return oa.acteurs_index(); }
    
    @WebMethod(operationName = "acteur_show")
    public String acteur_show(@WebParam(name = "id")
            String id) { return oa.acteur_show(id); }
        
    @WebMethod(operationName = "acteur_show_films")
    public String acteur_show_films(@WebParam(name = "id")
            String id) { return oa.acteur_show_films(id); }
    
            
    @WebMethod(operationName = "acteur_show_epoux")
    public String acteur_show_epoux(@WebParam(name = "id")
            String id) { return oa.acteur_show_epoux(id); }
                
    @WebMethod(operationName = "acteur_show_epoux_films")
    public String acteur_show_epoux_films(@WebParam(name = "id")
            String id) { return oa.acteur_show_epoux_films(id); }
    
    @WebMethod(operationName = "genres_index")
    public String genres_index() { return oa.genres_index(); }
    
    @WebMethod(operationName = "genre_show_films")
    public String genre_show_films(@WebParam(name = "id")
            String id) { return oa.genre_show_films(id); }
    
        @WebMethod(operationName = "testinsert")
    public String testinsert() { return oa.TESTINSERT(); }
        
        @WebMethod(operationName = "testinsert2")
    public String testinsert2() { return oa.testinsert2(); }
        
}
