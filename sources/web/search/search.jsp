<%-- 
    Document   : index
    Created on : 25-Dec-2007, 20:46:54
    Author     : Fildzzz
--%>

<%@include file="../header.jsp" %>

		        <h1>Rechercher un film ou un acteur</h1>
		      

        <form action="/moovee/search/controller" method="get">
            Requete : <input type="text" name="q" />
            <input type="submit" value="valider" />
        </form>
        
         <% if(request.getAttribute("resultsActeurs") != null) { %>
        <div class="infos">
            Résultats de la recherche pour : 
            <strong><%= request.getAttribute("q") %></strong>
        </div>
        <% } %>
        
        <% if(request.getAttribute("resultsActeurs") != null) { %>
        <%= request.getAttribute("resultsActeurs") %>
        <% } %>
        
        <% if(request.getAttribute("resultsFilms") != null) { %>
        <%= request.getAttribute("resultsFilms") %>
        <% } %>
        

        
<%@include file="../footer.jsp" %>
