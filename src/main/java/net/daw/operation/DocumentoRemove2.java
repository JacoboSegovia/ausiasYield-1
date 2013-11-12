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
public class DocumentoRemove2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp"); 
        DocumentoBean oDocumentoBean = new DocumentoBean();   
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
        try {
            DocumentoDao oDocumentoDAO = new DocumentoDao(oContexto.getEnumTipoConexion());
            oDocumentoDAO.remove(oDocumentoBean);
        } catch (Exception e) {
            throw new ServletException("DocumentoController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del documento con id=" + Integer.toString(oDocumentoBean.getId()));
        return Mensaje;
    }
    
}
