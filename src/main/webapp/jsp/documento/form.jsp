<%@page import="java.util.Date"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.DocumentoBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String titulo = "";
    String contenido = "";
    Date fecha = new Date();
    Integer nota = 0;
    Integer id_usuario = 0;
    String etiquetas = "";
    String descId_usuario = "";
    DocumentoBean oDocumentoBean = (DocumentoBean) oContexto.getParametro();
    id = oDocumentoBean.getId();
    titulo = oDocumentoBean.getTitulo();
    contenido = oDocumentoBean.getContenido();
    //fecha
    nota = oDocumentoBean.getNota();
    id_usuario = oDocumentoBean.getUsuario().getId();
    if (oDocumentoBean.getUsuario().getId() > 0) {
        descId_usuario = oDocumentoBean.getUsuario().getLogin();
    }

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    } else {
        strValueBoton = "Guardar";
    }
%>
<h1>Formulario de producto</h1>
<hr/>
<form class="form-horizontal" action="Controller" method="post" id="clienteForm">
    <fieldset>
        <!-- <legend>Formulario de producto</legend> -->
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="producto" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="codigo">Titulo: </label>
            <div class="controls">
                <input <%=strControlEnabled%>  id="codigo" name="codigo"
                                               type="text" size="30" maxlength="50" autofocus="autofocus"
                                               value="<%=titulo%>" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="descripcion">Contenido: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="descripcion"
                                               name="descripcion" type="text" size="30" maxlength="50"
                                               value="<%=contenido%>" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="precio">Fecha: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="precio"
                                               name="precio" type="text" size="30" maxlength="50"
                                               value="<%=fecha%>" /> 
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="precio">Nota: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="precio"
                                               name="precio" type="text" size="30" maxlength="50"
                                               value="<%=nota%>" /> 
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_usuario">Id_Usuario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario" class="input-mini"
                       name="id_usuario" type="text" size="5" maxlength="5"
                       value="<%=id_usuario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="tipoproducto" />

                <span class="alert alert-success"><%=descId_usuario%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="precio">Etiquetas: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="precio"
                                               name="precio" type="text" size="30" maxlength="50"
                                               value="<%=etiquetas%>" /> 
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>
    </fieldset>
</form>
