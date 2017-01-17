<%-- 
    Document   : new
    Created on : 22-Jan-2008, 19:59:12
    Author     : Fildzzz
--%>
<%@include file="../header.jsp" %>
        <h2>ActeurController : New</h2>
        <h3>Formulaire d'ajout d'un nouvel acteur</h3>
        
        <form name="f1" action="/moovee/acteurs/controller?create=true" method="POST">
         
            <label for="prenom">Prenom</label>
            <input type="text" id="prenom" name="prenom"/>
            <br />
            <label for="nom">Nom</label>
            <input type="text" id="nom" name="nom"/>
            <br />
            
          <% if(request.getAttribute("results2") != null) { %>
           <label for="epoux">A pour epoux(se)</label>
                <select id="epoux" name="epoux">
                    <option value="">...Choississez un(e) epoux(se)...</option>
                     <%= request.getAttribute("results2") %>
               </select><br />
          <% } %>
          <% if(request.getAttribute("results") != null) { %>
             <label for="film1">Joue dans</label>
                <select id="film1" name="film1">
                    <option value="">...Choississez un film...</option>
                     <%= request.getAttribute("results") %>
               </select><br />
             <label for="film2">Joue dans</label>
                <select id="film2" name="film2">
                    <option value="">...Choississez un film...</option>
                    <%= request.getAttribute("results") %>
               </select><br />
             <label for="film3">Joue dans</label>
                <select id="film3" name="film3">
                    <option value="">...Choississez un film...</option>
                    <%= request.getAttribute("results") %>
                </select><br />
             <label for="film4">Joue dans</label>
                <select id="film4" name="film4">
                    <option value="">...Choississez un film...</option>
                <%= request.getAttribute("results") %>
                </select><br />
             <label for="film5">Joue dans</label>
               <select id="film5" name="film5">
                    <option value="">...Choississez un film...</option>
                <%= request.getAttribute("results") %>
               </select><br />
               <% } %> 
            <input type="submit" value="Valider" /> 
        </form>

<%@include file="../footer.jsp" %>