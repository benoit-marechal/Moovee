<%-- 
    Document   : header
    Created on : 02-Jan-2008, 20:47:42
    Author     : Fildzzz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>Moovee : Recherche sémantique dans le domaine de la cinématographie</title>
		<link href="<%=request.getContextPath()%>/images/icon16.gif" rel="shortcut icon" title="images/x-icon" type="application/images/x-icon+xml" />
		<link href="<%=request.getContextPath()%>/stylesheets/w_default.css" media="screen" rel="Stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/stylesheets/w_layout.css" media="screen" rel="Stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/stylesheets/w_footer.css" media="screen" rel="Stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/stylesheets/w_menu.css" media="screen" rel="Stylesheet" type="text/css" />
		<script src="<%=request.getContextPath()%>/javascripts/prototype.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/javascripts/effects.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/javascripts/dragdrop.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/javascripts/controls.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/javascripts/application.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/javascripts/function_v1.js" type="text/javascript"></script>
	
             <!-- <style>
                label {
                display:block;
                width:150px;
                float:left;
                }
            </style>
            -->
         </head>
	<body>
			
<!-- W_Menu -->
<script src="<%=request.getContextPath()%>/javascripts/w_menu.js" type="text/javascript" ></script>
<link href="<%=request.getContextPath()%>/stylesheets/w_menu.css" media="screen" rel="Stylesheet" type="text/css" />
<div id="header">
	<div id="header-primary"  class="menu-single">
		<ul>
                     
                       
			<li><img alt="mooVee" class="menu_logo" src="<%=request.getContextPath()%>/images/logo.jpg" /></li>

                         <li class="menu_item">
				<a href="<%=request.getContextPath()%>/genres/controller"><strong>Genres</strong><em id="menu01" class="ltn_arrow"></em></a>
			</li>
                          <li class="menu_item">
				<a href="<%=request.getContextPath()%>/films/controller"><strong>Films</strong><em id="menu02" class="ltn_arrow"></em></a>
			</li>
                          <li class="menu_item">
				<a href="<%=request.getContextPath()%>/acteurs/controller"><strong>Acteurs</strong><em id="menu03" class="ltn_arrow"></em></a>
			</li>
                 
			<li >
				<strong>Recherche :</strong> <form action="<%=request.getContextPath()%>/search/controller" class="menu_search_form" id="search" method="get">
					<input class="menu_search_txt" id="item_body" name="q" size="30" type="text" value="Saisissez votre recherche..." />
					<input id="top-submit" class="menu_search_btn" type="submit" alt="" value="" />
				</form>
				
			</li>
			
			<li >
				<a href="<%=request.getContextPath()%>/search/controller?advanced=true"><strong>Recherche avanc&eacute;e</strong></a>
			</li>	
          
	</div>	
	<!-- Submenu Genres  -->
		<div id="pan_menu01" class="submenu" style="display:none;">
			<ul>
				<li style="margin:0"><a href="zr" style="height:19px;">Com&eacute;die</a></li>
				<li><a href="<%=request.getContextPath()%>/genres/controller?id=Horreur" style="height:19px;">Horreur</a></li>
				<li><a href="<%=request.getContextPath()%>/genres/controller?id=Action" style="height:19px;">Action</a></li>
				<li><a href="<%=request.getContextPath()%>/genres/controller?id=Thriller" style="height:19px;">Thriller</a></li>
				<li><a href="<%=request.getContextPath()%>/genres/controller?id=Drame" style="height:19px;">Drame</a></li>
				<li><a href="<%=request.getContextPath()%>/genres/controller?id=Romance" style="height:19px;">Romance</a></li>
			</ul>
		</div>
                <div id="pan_menu02" class="submenu" style="display:none;">
			<ul>
				<li><a href="<%=request.getContextPath()%>/films/controller" style="height:19px;">Lister</a></li>
				<li><a href="<%=request.getContextPath()%>/films/controller?new" style="height:19px;">Creer</a></li>
			</ul>
		</div>
                <div id="pan_menu03" class="submenu" style="display:none;">
			<ul>
				<li><a href="<%=request.getContextPath()%>/acteurs/controller" style="height:19px;">Lister</a></li>
				<li><a href="<%=request.getContextPath()%>/acteurs/controller?new" style="height:19px;">Creer</a></li>
			</ul>
		</div>
</div>

<div class="block_left">
   <div class="block_left_item">
     <div class="block_left_item2">