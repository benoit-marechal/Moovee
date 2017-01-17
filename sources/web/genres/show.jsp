<%-- 
    Document   : show
    Created on : 31-Dec-2007, 21:28:59
    Author     : Fildzzz
--%>
<%@include file="../header.jsp" %>
        <h2>GenreController : show</h2>
        <% if(request.getAttribute("results") != null) { %>
            <%= request.getAttribute("results") %>
        <% } %>
<%@include file="../footer.jsp" %>
