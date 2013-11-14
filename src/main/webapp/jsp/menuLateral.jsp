<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");%>
<%
    String menu = "<div class=\"well sidebar-nav\"><ul class=\"nav nav-list\">";

    menu += "<li class=\"nav-header\">Usuario</li>";

    if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("ocioso")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller\">Home</a></li>";

    if (!oContexto.getHaySesion()) {
        if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("login")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=login\">Login</a></li>";
    } else {
        if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("logout")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=logout\">Logout</a></li>";
    }
    
    
    //INCIDENCIAS-----------------------
    menu += "<li class=\"nav-header\">Incidencias</li>";
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("incidencias") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=incidencias&method=list\">Listar</a></li>";
    }
    
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("incidencias") && oContexto.getMetodo().equals("new")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=incidencias&method=new\">Nuevo</a></li>";
    }
   
    
    //Repositorio-----------------------
    menu += "<li class=\"nav-header\">Repositorio</li>";
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("repositorio") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=repositorio&method=list\">Listar</a></li>";
    }
    
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("repositorio") && oContexto.getMetodo().equals("new")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=repositorio&method=new\">Nuevo</a></li>";
    }
    
    //Estado-----------------------
    menu += "<li class=\"nav-header\">Estado</li>";
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("estado") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=estado&method=list\">Listar</a></li>";
    }
    
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("estado") && oContexto.getMetodo().equals("new")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=estado&method=new\">Nuevo</a></li>";
    }    
    
      //Voto Comentario---------------------
    menu += "<li class=\"nav-header\">Voto_Comentario</li>";
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("votoComentario") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=votoComentario&method=list\">Listar</a></li>";
    }
    
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("votoComentario") && oContexto.getMetodo().equals("new")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=votoComentario&method=new\">Nuevo</a></li>";
    }    
    
    //Lenguaje-----------------------
    menu += "<li class=\"nav-header\">Lenguaje</li>";
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("lenguaje") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=lenguaje&method=list\">Listar</a></li>";
    }
    
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("lenguaje") && oContexto.getMetodo().equals("new")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=lenguaje&method=new\">Nuevo</a></li>";
    }
    
    //Tipodocumento-----------------------
    menu += "<li class=\"nav-header\">Lenguaje</li>";
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("lenguaje") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=tipodocumento&method=list\">Listar</a></li>";
    }
    
    if (true) {//modificar permisos
        if (oContexto.getClase().equals("lenguaje") && oContexto.getMetodo().equals("new")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=tipodocumento&method=new\">Nuevo</a></li>";
    }

    menu += "</ul></div>";
%>
<%=menu%>
