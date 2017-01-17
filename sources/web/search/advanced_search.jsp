<%-- 
    Document   : form_advanced_search
    Created on : 03-Jan-2008, 12:54:16
    Author     : Fildzzz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../header.jsp" %>
  <h1>Recherche avancée des films ou d'acteurs</h1>
        
        <h2>Recherche de films</h2>
        <form action="/moovee/search/controller" method="get">
            <label for="titre">Titre</label><input type="text" id="titre" name="titre"/><br />
            <input type="hidden" name="advanced" value="true" />
                    <input type="submit" value="valider" />
        </form>   
                <form action="/moovee/search/controller" method="get">
            <label for="annee">Année</label>
                <select id="annee"  name="annee">
                <option>...Choisissez une année...</option>
                <%
                for(int i=2008;i>=1895;i--) {
                %>
                    <option><%= i %></option>
                <% }%> 
                </select><br />
                <input type="hidden" name="advanced" value="true" />
                <input type="submit" value="valider" />
        </form>  
           
            <form action="/moovee/search/controller" method="get">
            <label for="realisateur">Réalisateur</label><input type="text" id="realisateur"  name="realisateur"/><br />
           <input type="hidden" name="advanced" value="true" />
                    <input type="submit" value="valider" />
        </form>       
              
        
        <% if(request.getAttribute("resultsFilms") != null) { %>
        <%= request.getAttribute("resultsFilms") %>
        <% } %>
        

        


<%@include file="../footer.jsp" %>