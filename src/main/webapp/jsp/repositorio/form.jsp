<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.RepositorioBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String titulo = "";
    String contenido = "";
    Integer id_usuario = 0;
    Integer id_lenguaje = 0;
    Integer id_documento = 0;
    String fecha = "";
    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        RepositorioBean oRepositorioBean = (RepositorioBean) oContexto.getParametro();
        id = oRepositorioBean.getId();
        titulo = oRepositorioBean.getTitulo();
        contenido = oRepositorioBean.getContenido();
        id_usuario = oRepositorioBean.getId_usuario();
        id_lenguaje = oRepositorioBean.getId_lenguaje();
        id_documento = oRepositorioBean.getId_documento();
        fecha = formato.format(oRepositorioBean.getFecha());
    }
    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de repositorio</h1>
<form class="semantic" action="Controller" method="post" id="repositorioForm" onsubmit="hola();">
    <fieldset>
        <legend>Formulario de Repositorio</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="repositorio" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="titulo">Titulo: </label> 
            <input <%=strControlEnabled%> id="titulo" name="titulo" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=titulo%>" /><br />
        </div>
        <div>
            <label for="contenido">Contenido: </label>
            <textarea <%=strControlEnabled%> id="contenido" name="contenido" type="text" size="30" ><%=contenido%></textarea><br />
        </div>
        <div>
            <label for="id_usuario">Id_usuario: </label>
            <input <%=strControlEnabled%> id="id_usuario" name="id_usuario" type="text" size="30" maxlength="50" value="<%=id_usuario%>" /><br />
        </div>
        <div>
            <label for="id_lenguaje">Id_lenguaje: </label>
            <input <%=strControlEnabled%> id="id_lenguaje" name="id_lenguaje" type="text" size="30" maxlength="50" value="<%=id_lenguaje%>" /><br />
        </div>
        <div>
            <label for="id_documento">Id_documento: </label>
            <input <%=strControlEnabled%> id="id_documento" name="id_documento" type="text" size="30" maxlength="50" value="<%=id_documento%>" /><br />
        </div>
        <div>
            <label for="fecha">Fecha: </label> 
            <input <%=strControlEnabled%> id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /> <br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
