<%-- 
    Document   : new
    Created on : 22-Jan-2008, 19:59:12
    Author     : Fildzzz
--%>
<%@include file="../header.jsp" %>
        <h2>FilmController : New</h2>
        <h3>Formulaire d'ajout d'un nouveau film</h3>
        
        <form name="f1" action="/moovee/films/controller?create=true" method="POST">
         
            <label for="titre">Titre</label>
            <input type="text" id="titre" name="titre"/>
            <br />
             <label for="annee">Année</label>
                <select id="annee"  name="annee">
                <option>...Choisissez une année...</option>
                <%  for(int i=2008;i>=1895;i--) {  %>
                    <option><%= i %></option>
                <% }%>
               </select><br />
            
            
          <% if(request.getAttribute("genres") != null) { %>
           <label for="genre">Genre</label>
                <select id="genre" name="genre">
                    <option value="">...Choississez un genre...</option>
                    <option value="Action">Action</option>
                    <option value="Thriller">Thriller</option>
                     
               </select><br />
          <% } %>
            
          <% if(request.getAttribute("results") != null) { %>
           <label for="realisateur">Réalisateur</label>
                <select id="realisateur" name="realisateur">
                    <option value="">...Choississez un réalisateur...</option>
                     <%= request.getAttribute("results") %>
               </select><br />
          <% } %>
          <% if(request.getAttribute("results2") != null) { %>
             <label for="acteur1">A pour acteur</label>
                <select id="acteur1" name="acteur1">
                    <option value="">...Choississez un acteur...</option>
                     <%= request.getAttribute("results2") %>
               </select><br />
             <label for="acteur2">A pour acteur</label>
                <select id="acteur2" name="acteur2">
                    <option value="">...Choississez un acteur..</option>
                    <%= request.getAttribute("results2") %>
               </select><br />
             <label for="acteur3">A pour acteur</label>
                <select id="acteur3" name="acteur3">
                    <option value="">...Choississez un acteur..</option>
                    <%= request.getAttribute("results2") %>
                </select><br />
             <label for="acteur4">A pour acteur</label>
                <select id="acteur4" name="acteur4">
                    <option value="">...Choississez un acteur..</option>
                <%= request.getAttribute("results2") %>
                </select><br />
             <label for="acteur5">A pour acteur</label>
               <select id="acteur5" name="acteur5">
                    <option value="">...Choississez un acteur..</option>
                <%= request.getAttribute("results2") %>
               </select><br />
               <% } %> 
            <input type="submit" value="Valider" /> 
        </form>

<%@include file="../footer.jsp" %>