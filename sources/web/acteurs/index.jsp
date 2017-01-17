<%@include file="../header.jsp" %>
        <h2>ActeurController : index</h2>
           
          <% if(request.getAttribute("results") != null) { %>
        <%= request.getAttribute("results") %>
        <% } %>
<%@include file="../footer.jsp" %>
