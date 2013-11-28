
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.bean.DocumentoBean"%>
<%@page import="net.daw.bean.BolsaBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Boolean privado = false;
    Integer id = 0;
    Integer id_documento1 = 0;
    Integer id_documento2 = 0;
    Date fecha = new Date();
    String documento1 = "";
    String documento2 = "";

    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        BolsaBean oBolsaBean = (BolsaBean) oContexto.getParametro();
        id = oBolsaBean.getId();
        id_documento1 = oBolsaBean.getId_documento1();
        id_documento2 = oBolsaBean.getId_documento2();
        fecha = new SimpleDateFormat("yyyy-MM-dd").parse(oBolsaBean.getFecha().toString());
        documento1 = oBolsaBean.getDocumento1().getTitulo();
        documento2 = oBolsaBean.getDocumento1().getTitulo();
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
        id_documento1 = 0;
        id_documento2 = 0;
    }
%>
<h1><%=strTitulo%> de bolsa</h1>
<form class="semantic" action="Controller" method="post" id="bolsaForm">
    <fieldset>
        <legend>Formulario de Bolsa</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="bolsa" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="id_documento1">Documento1 </label> 
            <div class="controls">                
                <input readonly="true" id="id_documento1" class="input-mini"
                       name="id_documento1" type="text" size="5" maxlength="5"
                       value="<%=id_documento1%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento1" />
                <span class="alert alert-success"><%=documento1%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_documento2">Documento2 </label> 
            <div class="controls">                
                <input readonly="true" id="id_producto" class="input-mini"
                       name="id_documento2" type="text" size="5" maxlength="5"
                       value="<%=id_documento2%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento2" />
                <span class="alert alert-success"><%=documento2%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha </label> 
            <div class="controls">                
                <input <%=strControlEnabled%> id="fecha" class="input-medium"
                       name="fecha" type="date"
                       value="<%=fecha%>" />  
            </div>
        </div>
        <div>
            <label for="titulo">Privado: </label> 
            <input <%=strControlEnabled%> id="privado" name="privado" type="checkbox" autofocus="autofocus" value="1"  <% if (privado) {%><%="checked=\"checked\""%><% }%>/><br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
