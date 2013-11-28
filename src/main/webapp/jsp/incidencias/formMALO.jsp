<%-- 
    Document   : form
    Created on : 07-nov-2013, 12:20:07
    Author     : al037431
--%>

<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="net.daw.helper.Enum.Connection"%>
<%@page import="net.daw.data.Mysql"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.IncidenciasBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_usuario = "";
    String resumen = "";
    String cambios = "";
    String id_estado = "";
    String id_repositorio = "";
    String fechaAlta = "";
    String fechaResolucion = "";
    String nomid_estado="";
    String titid_repositorio= "";
    String nomid_usuario= "";

    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        IncidenciasBean oIncidenciasBean = (IncidenciasBean) oContexto.getParametro();
        id = oIncidenciasBean.getId();
        resumen = oIncidenciasBean.getResumen();
        cambios = oIncidenciasBean.getCambios();
        
        
        
        fechaAlta = oIncidenciasBean.getFechaAlta();
        fechaResolucion = oIncidenciasBean.getFechaResolucion();
    }
     IncidenciasBean oIncidenciasBean = (IncidenciasBean) oContexto.getParametro();
    id_estado = Integer.toString(oIncidenciasBean.getEstado().getId());
    if (oIncidenciasBean.getEstado().getId() > 0) {
       nomid_estado = oIncidenciasBean.getEstado().getNombre();
    }
    
    
    id_repositorio = Integer.toString(oIncidenciasBean.getRepositorio().getId());
    if (oIncidenciasBean.getEstado().getId() > 0) {
       titid_repositorio = oIncidenciasBean.getRepositorio().getTitulo();
    }
    
    
     id_usuario = Integer.toString(oIncidenciasBean.getUsuario().getId());
    if (oIncidenciasBean.getUsuario().getId() > 0) {
       nomid_usuario = Integer.toString(oIncidenciasBean.getUsuario().getId());
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
<h1><%=strTitulo%> de cliente</h1>
<form class="semantic" action="Controller" method="post" id="incidenciasForm">
    <fieldset>
        <legend>Formulario de Incidencias</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="incidencias" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="Resumen_incidencias">Resumen Incidencias </label> 
            <input <%=strControlEnabled%> id="Resumen_incidencias" name="Resumen_incidencias" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=resumen%>" /><br />
        </div>
        <div>
            <label for="cambios">Cambios </label>
            <textarea <%=strControlEnabled%> id="cambios" name="cambios" type="text" size="30" maxlength="50" value="<%=cambios%>" ></textarea><br />
        </div>

      
        <div class="control-group">
        <label class="control-label" for="id_estado">Id_Estado: </label> 
        <div class="controls">                
            <input readonly="true" id="id_estado" class="input-mini"
                   name="id_estado" type="text" size="5" maxlength="5"
                   value="<a%=id_estado%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="estado" />
            <span class="alert alert-success"><%=nomid_estado%></span>
        </div>
    </div>  
        
        
        <div class="control-group">
        <label class="control-label" for="id_repositorio">Id_Repositorio: </label> 
        <div class="controls">                
            <input readonly="true" id="id_repositorio" class="input-mini"
                   name="id_repositorio" type="text" size="5" maxlength="5"
                   value="<a%=id_repositorio%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="repositorio" />
            <span class="alert alert-success"><%=titid_repositorio%></span>
        </div>
    </div>
        
        
        
        
        
        <div class="control-group">
        <label class="control-label" for="id_usuario">Id_Usuario: </label> 
        <div class="controls">                
            <input readonly="true" id="id_usuario" class="input-mini"
                   name="id_usuario" type="text" size="5" maxlength="5"
                   value="<a%=id_usuario%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
            <span class="alert alert-success"><%=nomid_usuario%></span>
        </div>
    </div> 
       
        
        
        
        
        
        <div>
            <label for="fechaAlta">Fecha Alta: </label> 
            <input <%=strControlEnabled%> id="fechaAlta" name="fechaAlta" type="date" size="30" maxlength="50" value="<%=fechaAlta%>" /> <br />
        </div>
        <div>
            <label for="fechaResolucion">Fecha Resolución: </label> 
            <input <%=strControlEnabled%> id="fechaResolucion" name="fechaResolucion" type="date" size="30" maxlength="50" value="<%=fechaResolucion%>" /> <br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
