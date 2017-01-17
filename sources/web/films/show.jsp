<%-- 
    Document   : show
    Created on : 31-Dec-2007, 21:28:59
    Author     : Fildzzz
--%>
<%@include file="../header.jsp" %>
        <h2>FilmController : show</h2>
        <% if(request.getAttribute("results") != null) { %>
        <%= request.getAttribute("results") %>
        <% } %>
        
           <% if(request.getAttribute("results2") != null) { %>
           <br /><hr />
           <h4>Acteurs</h4>
        <%= request.getAttribute("results2") %>
        <% } %>
        
           <% if(request.getAttribute("results3") != null) { %>
           <br /><hr />
           <h4>Notes</h4>
            <%= request.getAttribute("results3") %>
            <% } %>


           <% if(request.getAttribute("results4") != null) { %>
           <br /><hr />
           <h4>Commentaires</h4>
            <%= request.getAttribute("results4") %>
            <% } %>        
<br /><hr />
        <h5>Ajout d'une note</h5>
        <form name="f2" action="/moovee/films/controller?add_note" method="POST">
           <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
           
            <label for="note">Note</label>
                <select id="note" name="note">
                <%
                for(int i=10;i>=0;i--) {
                %>
                    <option><%= i %></option>
                <% }%>
               </select><br />
             <input type="submit" value="Valider" />
        </form>
        
        <br /><hr />
        <h5>Ajout d'un commentaire</h5>
        <form name="f2" action="/moovee/films/controller?add_commentaire" method="POST">
           <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
           
            <label for="contenu">Login</label>
              <input type="text" id="login" name="login" />
            <label for="contenu">Commentaire</label>
              <input type="text" id="contenu" name="contenu" />
              <br />
             <input type="submit" value="Valider" />
        </form>
         <br /> <br /> 
<%@include file="../footer.jsp" %>
