<%-- 
    Document   : sparql
    Created on : 07-Jan-2008, 20:41:42
    Author     : Fildzzz
--%>

<%@include file="../header.jsp" %>
        <h1>Execution de requetes sparql</h1>
        
        <form action="/moovee/search/controller" method="get">
            Requete : <textarea name="q" rows="10" >requete....</textarea>
            <input type="hidden" name="sparql" value="true" />
            <input type="submit" value="valider" />
        </form>
        
         <% if(request.getAttribute("results") != null) { %>
        <div class="infos">
            Résultats de la recherche pour : 
            <strong><%= request.getAttribute("q") %></strong>
            
        </div>
        
        <%= request.getAttribute("results") %>
        
        <% } %>
       
        
<%@include file="../footer.jsp" %>
