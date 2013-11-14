/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.DocumentoBean;
import net.daw.dao.DocumentoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.DocumentoParam;

/**
 *
 * @author Alvaro
 */
public class DocumentoUpdate2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        if ("id_usuario".equals(oContexto.getSearchingFor())) {
            oContexto.setVista("jsp/usuario/list.jsp");
            oContexto.setClase("usuario");
            oContexto.setMetodo("selectone");
            oContexto.setFase("1");
            oContexto.setClaseRetorno("documento");
            oContexto.setMetodoRetorno("update");
            oContexto.setFaseRetorno("1");
            oContexto.removeParam("id_usuario");
            oContexto.removeParam("page");
            return null;
        } else {
            oContexto.setVista("jsp/mensaje.jsp");
            DocumentoBean oDocumentoBean = new DocumentoBean();
            DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
            DocumentoParam oDocumentoParam = new DocumentoParam(request);
            oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);
            try {
                oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
            } catch (NumberFormatException e) {
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            try {
                oDocumentoDao.set(oDocumentoBean);
            } catch (Exception e) {
                throw new ServletException("DocumentoController: Update Error: Phase 2: " + e.getMessage());
            }
            String strMensaje = "Se ha añadido la información del documento con id=" + Integer.toString(oDocumentoBean.getId()) + "<br />";            
            strMensaje += "<a href=\"Controller?class=documento&method=view&id=" + oDocumentoBean.getId() + "\">Ver documento creado</a><br />";
            return strMensaje;
        }
    }
}
