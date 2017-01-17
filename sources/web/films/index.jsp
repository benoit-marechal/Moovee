<%-- 
    Document   : index
    Created on : 31-Dec-2007, 14:16:36
    Author     : Fildzzz
--%>

<%@include file="../header.jsp" %>
        <h2>FilmController : index</h2>
        
          <% if(request.getAttribute("results") != null) { %>
        <%= request.getAttribute("results") %>
        <% } %>
        
<%@include file="../footer.jsp" %>