/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology;

import com.hp.hpl.jena.rdf.model.*;
//import com.hp.hpl.jena.vocabulary.*;
import com.hp.hpl.jena.ontology.*; // Pour OntModel 
  import com.hp.hpl.jena.query.* ; // Pour les requetes
import java.io.*;
import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import javax.xml.transform.*; 
import javax.xml.transform.sax.*; 
import javax.xml.transform.dom.*; 
import javax.xml.transform.stream.*; 
import java.util.*; 

import com.hp.hpl.jena.util.FileManager;


/**
 *
 * @author Fildzzz
 */
public class OntologyAccess {
 // Repertoire de travail
       public static String root_dir = "E:\\Donnees\\Fac\\Master_2\\Semestre_1\\projet-si-application\\programmation\\moovee\\";
       public static String f_ontology = root_dir+"moovee.owl";
       public static  String f_results = root_dir+"results.sparql";
     //  public static  String f_xslt = root_dir+"style.xsl";
       public static  String f_html = root_dir+"results.html";
       
       public static String base_prefix = "http://www.moovee.com/ontology#";
       
       public OntModel model;
       
       // CONSTRUCTEUR
       public OntologyAccess() {
         model = ModelFactory.createOntologyModel();
          InputStream in = FileManager.get().open("E:\\Donnees\\Fac\\Master_2\\Semestre_1\\projet-si-application\\programmation\\moovee\\moovee.owl");
          if (in != null)
              model.read(in,"");
       }
       
       // Recherche dans le nom et le prenom des acteurs
       public String search_acteurs(String q){
             String queryStringActeurs =  "SELECT ?prenom ?nom ?id \n"+
                       "WHERE {  ?id :prenom ?prenom  .\n"+
                       "?id :nom ?nom .\n" +
                       "FILTER (regex(?nom, \""+q+"\",\"i\") || regex(?prenom, \""+q+"\",\"i\") )  }";

        String resultsActeurs = this.processSPARQLandXSLT(queryStringActeurs,"acteurs_index.xsl");
        return resultsActeurs;
       }
       
       // Recherche les film par leur titre
       public String search_films(String q){
                   String queryStringFilms =  "SELECT ?id ?titre \n"+
                       "WHERE {  ?id :titre ?titre .\n"+
                       "FILTER (regex(?titre, \""+q+"\",\"i\") )  }";

        String resultsFilms = this.processSPARQLandXSLT(queryStringFilms,"films_index.xsl");
        return resultsFilms;
       }
       
              // Recherche les film par leur titre
       public String search_films_annee(String q){
                   String queryStringFilms =  "SELECT ?id ?titre \n"+
                       "WHERE {  ?id :titre ?titre .\n"+
                       "?id :annee ?annee .\n"+
                       "FILTER (?annee="+q+" )  }";

        String resultsFilms = this.processSPARQLandXSLT(queryStringFilms,"films_index.xsl");
        return resultsFilms;
       }
       
          
              // Recherche les film par leur titre
       public String search_films_realisateur(String q){
                   String queryStringFilms =  "SELECT ?id ?titre \n"+
                       "WHERE {  ?id :titre ?titre .\n"+
                       "?id :est_realise_par ?realisateur .\n"+
                       "?realisateur :nom ?rnom .\n"+
                       "?realisateur :prenom ?rpnom .\n"+
                        "FILTER (regex(?rnom, \""+q+"\",\"i\") )  }";

        String resultsFilms = this.processSPARQLandXSLT(queryStringFilms,"films_index.xsl");
        return resultsFilms;
       }
       
       // Liste des films
       public String films_index(){
        String queryString ="SELECT ?film ?titre "+
                            "WHERE { ?film :titre ?titre"+
                            "}";
        String results = this.processSPARQLandXSLT(queryString,"films_index.xsl");
        return results;
        
       }
       
       // Liste des films pour le combo box
       public String films_index_select(){
        String queryString ="SELECT ?film ?titre "+
                            "WHERE { ?film :titre ?titre"+
                            "}";
        String results = this.processSPARQLandXSLT(queryString,
                "films_index_select.xsl");
        return results;
        
       }
       
       // Affiche les detail sur un film
       public String film_show(String id){
  
            String queryString  = "SELECT ?film ?titre ?annee ?path ?pathex ?realisateurnom ?realisateurprenom \n"+
                                   "WHERE {\n"+
                                    "?film :titre ?titre .\n"+
                                    "?film :annee ?annee .\n"+ 
                                    "?film :a_pour_image ?image .\n"+ 
                                    "?image :path ?path .\n"+
                                    "?film :a_pour_extrait ?extrait .\n"+ 
                                    "?extrait :path ?pathex .\n"+
                                    "?film :est_realise_par ?realisateur .\n"+ 
                                    "?realisateur :nom ?realisateurnom .\n"+  
                                    "?realisateur :prenom ?realisateurprenom \n"+  
                                    "FILTER(regex(str(?film),\""+id+"\",\"i\"))"+
                                   "}";
         String results = this.processSPARQLandXSLT(queryString,"films_show.xsl");
         return results;
       }
       
       // Affiche les acteurs d'un films donnée
       public String film_show_acteurs(String id){
            String  queryString = "SELECT ?acteur ?nom ?prenom\n"+
            "WHERE {\n"+
            "?film :a_pour_acteur ?acteur .\n"+
            "?acteur :nom ?nom .\n"+
            "?acteur :prenom ?prenom\n"+
            "FILTER(regex(str(?film),\""+id+"\",\"i\"))\n"+
            "}";
         String results2 = this.processSPARQLandXSLT(queryString,"films_show_acteurs.xsl");
         return results2;
       }       
       // Affiche les notes d'un film donnée
       public String film_show_notes(String id){
            String  queryString = "SELECT ?note\n"+
            "WHERE {\n"+
            "?film :a_pour_note ?note\n"+
            "FILTER(regex(str(?film),\""+id+"\"))\n"+
            "}";
         String results2 = this.processSPARQLandXSLT(queryString,"films_show_notes.xsl");
         return results2;
       }
       
           
       // Affiche les commentaires d'un films donnée
       public String film_show_commentaires(String id){
            String  queryString = "SELECT ?contenu ?login\n"+
            "WHERE {\n"+
            "?commentaire :contenu ?contenu .\n"+
            "?commentaire :login ?login .\n"+
            "?commentaire :est_commentaire_de ?film .\n"+
            "FILTER(regex(str(?film),\""+id+"\"))\n"+
            "}";
         String results = this.processSPARQLandXSLT(queryString,"commentaires.xsl");
         return results;
       }
       
       // Ajoute une note a un film dans l'ontology
       public String  film_add_note(String id,String note){
          Resource film_resource = model.getResource(base_prefix + id);
          film_resource.addProperty(model.getProperty(base_prefix+ "a_pour_note"),note);
          save();
          return "success";
       }
       
       
       public String  film_add_commentaire(String id,String login,String contenu){
           // Recupe du film grace à l'id
          Resource film_resource = model.getResource(base_prefix + id);
          
          double a = Math.random()*1400000;
         
          Resource resource_type = model.getResource(base_prefix + "Commentaires");
          
          // Creation de la resource Commentaire avec le contenu et le login 
          Resource type = model.createResource(base_prefix+"commenaire_"+a, resource_type);
          type.addProperty(model.getProperty(base_prefix+ "contenu"),contenu);
          type.addProperty(model.getProperty(base_prefix+ "login"),login);
          type.addProperty(model.getProperty(base_prefix+ "est_commentaire_de"),film_resource);
          
          // Ajout du commentaire au film donnée par son id
          film_resource.addProperty(model.getProperty(base_prefix+
                  "a_pour_commentaire"),type);
          save();
          return "success";
       }
       
       
       // Affiche les acteurs d'un films donnée
//       public String film_show_image(String id){
//            String  queryString = "SELECT  ?film ?path ?prenom\n"+
//            "WHERE {\n"+
//            "?film :a_pour_acteur ?acteur .\n"+
//            "?acteur :nom ?nom .\n"+
//            "?acteur :prenom ?prenom\n"+
//            "FILTER(regex(str(?film),\""+id+"\",\"i\"))\n"+
//            "}";
//            
//            
//         String results2 = this.processSPARQLandXSLT(queryString,"films_show_acteurs.xsl");
//          
//         return results2;
//       }
       
       // Liste des acteurs
       public String acteurs_index(){
            String queryString ="SELECT ?acteur ?prenom ?nom "+
                                    "WHERE { ?acteur :nom ?nom .\n"+
                                    "?acteur :prenom ?prenom .\n"+
                                    "?acteur rdf:type ?type ."+
                                    "FILTER(regex(str(?type),\"Acteurs\")) }";
            

            String results = this.processSPARQLandXSLT(queryString,"acteurs_index.xsl");
            return results;
       }
              
       // Liste des realisateurs
       public String realisateurs_index(){
            String queryString ="SELECT ?realisateur  "+
                                    "WHERE { ?realisateur :nom ?nom .\n"+
                                    "?realisateur :prenom ?prenom .\n"+
                                    "?realisateur rdf:type ?type ."+
                                    "FILTER(regex(str(?type),\"Realisateurs\")) }";


            String results = this.processSPARQLandXSLT(queryString,"acteurs_index.xsl");
            return results;
       }
       
           
       // Liste des realisateurs
       public String realisateurs_index_select (){
            String queryString ="SELECT ?realisateur  "+
                                    "WHERE { ?realisateur :nom ?nom .\n"+
                                    "?realisateur :prenom ?prenom .\n"+
                                    "?realisateur rdf:type ?type ."+
                                    "FILTER(regex(str(?type),\"Realisateurs\")) }";
            String results = this.processSPARQLandXSLT(queryString,"acteurs_index_select.xsl");
            return results;
       }
       
       // Liste des acteurs
       public String acteurs_index_select(){
            String queryString ="SELECT ?acteur ?prenom ?nom "+
                                    "WHERE { ?acteur :nom ?nom .\n"+
                                    "?acteur :prenom ?prenom .\n"+
                                    "?acteur rdf:type ?type ."+
                                    "FILTER(regex(str(?type),\"Acteurs\")) }";
            

            String results = this.processSPARQLandXSLT(queryString,"acteurs_index_select.xsl");
            return results;
       }
       
       
       // Detail sur un acteur identifié par son id
       public String acteur_show(String id){
                       String queryString1  = "SELECT ?acteur ?nom ?prenom \n"+
                                   "WHERE {\n"+
                                    "?acteur :nom ?nom .\n"+
                                    "?acteur :prenom ?prenom \n"+ 
                                    "FILTER(regex(str(?acteur),\""+id+"\",\"i\"))"+
                                   "}";
            
         String results = this.processSPARQLandXSLT(queryString1,"acteurs_show.xsl");
         return results;
       }
       
       // Affiche des films dans lesquel l'acteur identifié par son id joue
       public String acteur_show_films(String id){
         String queryString2  = "SELECT ?film ?titre \n"+
                                   "WHERE {\n"+
                                    "?acteur :joue_dans ?film .\n"+
                                    "?film :titre ?titre .\n"+ 
                                    "FILTER regex(str(?acteur),\""+id+"\")"+
                                   "}";
            
         String results2 = this.processSPARQLandXSLT(queryString2,"films_index.xsl");
         return results2;
       }
       
       // l'epoux/se de l'acteur donnée par son id.
       public String acteur_show_epoux(String id){
         String queryString3  = "SELECT ?acteur2 ?nom ?prenom \n"+
                                   "WHERE {\n"+
                                    "?acteur :a_pour_epoux ?acteur2 .\n"+
                                    "?acteur2 :nom ?nom .\n"+ 
                                    "?acteur2 :prenom ?prenom\n"+ 
                                    "FILTER regex(str(?acteur),\""+id+"\")"+
                                   "}";
            
         String results3 = this.processSPARQLandXSLT(queryString3,"acteurs_index.xsl");
         
         return results3;
       }
       
       // Liste des film dans lequel l'epoux de l'acteur donnée par son id joue
       public String acteur_show_epoux_films(String id){
           
          String queryString4  = "SELECT ?film ?titre \n"+
                                   "WHERE {\n"+
                                    "?acteur :a_pour_epoux ?acteur2 .\n"+
                                    "?acteur2 :nom ?nom .\n"+ 
                                    "?acteur2 :prenom ?prenom .\n"+ 
                                    "?acteur2 :joue_dans ?film .\n"+ 
                                    "?film :titre ?titre\n"+ 
                                    "FILTER regex(str(?acteur),\""+id+"\")"+
                                   "}";

         String results4 = this.processSPARQLandXSLT(queryString4,"films_index.xsl");
         return results4;
       }
       
       
       // Liste des genres
       public String genres_index(){
        String queryString ="SELECT DISTINCT ?type \n"+
                            "WHERE {\n"+
                            "?film :a_pour_genre ?genre .\n"+
                            "?genre :type ?type \n"+
                            "}";
  
        String results = this.processSPARQLandXSLT(queryString,"genres_index.xsl");
        return results;
        
       } 
       // Liste des genres
       public String genres_index_select(){
        String queryString ="SELECT ?type \n"+
                            "WHERE {\n"+
                            "?film :a_pour_genre ?genre .\n"+
                            "?genre :type ?type \n"+
                            "}";
  
        String results = this.processSPARQLandXSLT(queryString,"genres_index_select.xsl");
        return results;
        
       }

       // Affiche des films d'un genre donnee
       public String genre_show_films(String id){
         String queryString2  = "SELECT  ?film ?titre \n"+
                                   "WHERE { ?film :a_pour_genre ?genre .\n"+
                                    "?film :titre ?titre .\n"+
                                    "?genre :type ?type .\n"+ 
                                    "FILTER regex(str(?genre),\""+id+"\")"+
                                   "}";
     
         String results2 = this.processSPARQLandXSLT(queryString2,"films_index.xsl");
         return results2;
       }
       
       // Permet l'ajout d'un acteurprenom
       public void acteur_create(String prenom,String nom,
               String epoux_id,List<String> films ){

          Resource resource_type = model.getResource(base_prefix + "Acteurs");  
          String uri  = base_prefix+prenom+""+ nom;
          Resource type = model.createResource(uri, resource_type);
       
          type.addProperty(model.getProperty(base_prefix+ "nom"),nom);
          type.addProperty(model.getProperty(base_prefix + "prenom"),prenom); 
          
          if (!epoux_id.equals("")) {
          Resource resource_epoux = model.getResource(base_prefix + epoux_id);
          type.addProperty(model.getProperty(base_prefix + "a_pour_epoux"),resource_epoux);     
          }
          
          if (films!=null && films.size()>0){
              for(int i = 0;i<films.size()-1;i++){
                  String film = films.get(i);
                  if(!film.equals("")) {
                   Resource resource_film = model.getResource(base_prefix + film);
                type.addProperty(model.getProperty(base_prefix + "joue_dans"),resource_film);     
             
                  }
               }
          }
          save();
       }
       
              
       // Permet l'ajout d'un acteurprenom
//       public void note_create(String note,String film_id ){
//
//          Resource resource_type = model.getResource(base_prefix + "Notes");
//          Resource type = model.createResource(note, resource_type);
//          
//          Resource film_resource = model.getResource(base_prefix + film_id);
//          type.addProperty(model.getProperty(base_prefix+ "est_note_de"),film_resource);
//          
//         // save();
//       }
//       
       
       // Permet l'ajout d'un film
       public String film_create(String titre,String annee,String realisateur_id, String genre,List<String> acteurs ){
       
         Resource resource_type = model.getResource(base_prefix + "Films");
          
          String titre_id = titre.replace(" ","_");
        
          String uri  = base_prefix+titre_id;
          
          Resource type = model.createResource(uri, resource_type);
          type.addProperty(model.getProperty(base_prefix+ "titre"),titre);
          type.addProperty(model.getProperty(base_prefix + "annee"),annee); 
          
          // realisateur
          Resource resource_realisateur = model.getResource(base_prefix +realisateur_id);
          type.addProperty(model.getProperty(base_prefix + "est_realise_par"),resource_realisateur);
          
          // extrait
          extrait_create(titre_id);
          
          Resource resource_extrait = model.getResource(base_prefix + titre_id+ "_extrait");
         type.addProperty(model.getProperty(base_prefix + "a_pour_extrait"),resource_extrait);
          
          // image
         image_create(titre_id);
         Resource resource_image = model.getResource(base_prefix + titre_id+ "_Image");
         type.addProperty(model.getProperty(base_prefix + "a_pour_image"),resource_image);
          
          // genre
          Resource resource_genre = model.getResource(base_prefix + genre);
          type.addProperty(model.getProperty(base_prefix + "a_pour_genre"),resource_genre);     
          
          // acteurs
          if (acteurs!=null && acteurs.size()>0){
              for(int i = 0;i<acteurs.size()-1;i++){
                Resource resource_acteur = model.getResource(base_prefix + acteurs.get(i));
                type.addProperty(model.getProperty(base_prefix + "a_pour_acteur"),resource_acteur);     
             }
          }
           save();
           
           return "success";
       }
       
       // Permet d'ajouter un extrait
        public void extrait_create(String titre_id){
          Resource resource_type = model.getResource(base_prefix + "Extraits");
          String uri  = base_prefix+titre_id+"_extrait";
          Resource type = model.createResource(uri, resource_type);
          type.addProperty(model.getProperty(base_prefix+ "path"),titre_id+".flv");
          type.addProperty(model.getProperty(base_prefix+ "est_extrait_de"),titre_id);
          save();
        }
        
       // Permet d'ajouter une image (on suppose que le fichier est deja présent)
        public void image_create(String titre_id){
          Resource resource_type = model.getResource(base_prefix + "Images");
          String uri  = base_prefix+titre_id+"_Image";
          Resource type = model.createResource(uri, resource_type);
          type.addProperty(model.getProperty(base_prefix+ "path"),titre_id+".jpg");
          type.addProperty(model.getProperty(base_prefix+ "est_image_de"),titre_id);
          save();
        }
       
       
       // Sauvegarde de l'ontology
       public void save(){
             try { model.write(new FileOutputStream(new File(f_ontology)));
            }catch(Exception e){ e.printStackTrace();}
       
       }
       
       // Méthode executant une requete SPARQL, la parsant, et retournant l'html du résultat
       public String processSPARQLandXSLT(String queryString,String f_xslt){
           // MAJ du chemin vers le fichier XSLT.
           f_xslt = root_dir+f_xslt;
       

     // Ajout des prefix
    queryString ="PREFIX : <http://www.moovee.com/ontology#>\n" + 
                 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
                 "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"+
                  queryString;

  Query query = QueryFactory.create(queryString) ;
 
  QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
  
  
  try {
    ResultSet results = qexec.execSelect() ;
    try {
         ResultSetFormatter.outputAsXML(new FileOutputStream(f_results),results);
         creerHTML(f_results, f_xslt, f_html);
    }
    catch(Exception e){ e.printStackTrace(); }

  } finally { qexec.close() ; }
  
        //////////////////////////////////////////////
    BufferedReader lecteurAvecBuffer = null;
    String ligne;
    String results="";
    try {
       lecteurAvecBuffer = new BufferedReader(new FileReader(f_html));

        while ((ligne = lecteurAvecBuffer.readLine()) != null)
                results+=ligne;
        lecteurAvecBuffer.close();
    }
    catch(Exception e){ e.printStackTrace();}
    return results;
       }
       
       
       // Methode trouvée sur develloppez
      public  void creerHTML(String xml, String xsl, String html) throws Exception{
		// Création de la source DOM
		DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructeur = fabriqueD.newDocumentBuilder();
		File fileXml = new File(xml);
		Document document = constructeur.parse(fileXml);
        Source source = new DOMSource(document);

        // Création du fichier de sortie
        File fileHtml = new File(html);
        Result resultat = new StreamResult(fileHtml);

        // Configuration du transformer
        TransformerFactory fabriqueT = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(xsl);
        Transformer transformer = fabriqueT.newTransformer(stylesource);
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        // encoding="ISO-8859-1"
        
        // Transformation
        transformer.transform(source, resultat);
	}
      
      public String testinsert2(){
          
          acteur_create("Benoit","Marechal","AngelinaJoly", null);
//          String res= "";
//          String prenom="Benoit";
//          String nom="Marechal";
//          
//          
//          String personneURI  = "http://www.moovee.com/ontology#"+prenom+"" + nom;
//          Resource Personne = model.getResource("http://www.moovee.com/ontology#Acteurs");
//          Resource personne = model.createResource(personneURI, Personne);
//          personne.addProperty(model.getProperty("http://www.moovee.com/ontology#nom"),nom);
//          personne.addProperty(model.getProperty("http://www.moovee.com/ontology#prenom"),prenom);  
//          Resource Angi = model.getResource("http://www.moovee.com/ontology#AngelinaJoly");
//          personne.addProperty(model.getProperty("http://www.moovee.com/ontology#a_pour_epoux"),Angi);     
          
          
//          Resource 
//          model.createIndividual(arg0);
          return ""; 
      }
      
      public String TESTINSERT(){
            // MAJ du chemin vers le fichier XSLT.
         
         
          
          

     // Ajout des prefix
//    String queryString ="INSERT\n" + 
//                        "{ <http://example/book3> dc:title    \"A new book\" ;\n" + 
//                        "dc:creator  \"A.N.Other\" .\n}";
//                  queryString ="PREFIX : <http://www.moovee.com/ontology#>\n" + 
//                 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
//                 "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"+
//                  queryString;

  // Ajout des prefix
    String queryString ="SELECT ?acteur ?prenom ?nom "+
                                    "WHERE { ?acteur :nom ?nom .\n"+
                                    "?acteur :prenom ?prenom .\n"+
                                    "?acteur rdf:type ?type ."+
                                    "FILTER(regex(str(?type),\"Acteurs\")) }";
           
    queryString ="PREFIX : <http://www.moovee.com/ontology#>\n" + 
                 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
                 "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"+
                  queryString;

  Query query = QueryFactory.create(queryString) ;
 
  QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
  
  String res = "RIEN DEDANSSSSSSSSSSSS";
  try {
       Model results = qexec.execDescribe() ;
  // Model results = qexec.execConstruct() ;
      
   //res = acteurs_index();
   // System.out.println(res);
  } finally {// qexec.close() ;
      
  }
  return res;
       }
       
}
