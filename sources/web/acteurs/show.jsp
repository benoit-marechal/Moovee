<%-- 
    Document   : show
    Created on : 31-Dec-2007, 21:28:18
    Author     : Fildzzz
--%>

<%@include file="../header.jsp" %>
        <h2>ActeurController : show</h2>
          
         <% if(request.getAttribute("results") != null) { %>
         <h3><%= request.getAttribute("results") %></h3>
        <% } %>  
          
        <h4>Joue dans :</h4>
          <% if(request.getAttribute("results2") != null) { %>
        <%= request.getAttribute("results2") %>
        <% } %>
        
        <% if((request.getAttribute("results3") != null) && !request.getAttribute("results3").equals("<ul/>")) { %>
        <br />
        <h4>Epoux(se) de :</h4>
        <%= request.getAttribute("results3") %>
        <% } else { %>
        <br />
        Pas d'epoux/se acteur/actrice.
         <% } %>
       
       
       <% if((request.getAttribute("results3") != null) && (!request.getAttribute("results4").equals("<ul/>")) ) { %>
          <br />
        <h4>Qui joue dans :</h4> 
        <%= request.getAttribute("results4") %>
        <% } %>

<%@include file="../footer.jsp" %>