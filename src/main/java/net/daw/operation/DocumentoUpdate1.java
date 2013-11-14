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
 * @author al037294
 */
public class DocumentoUpdate1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/documento/form.jsp");
        DocumentoBean oDocumentoBean;
        DocumentoDao oDocumentoDao;
        oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
        oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);
        } catch (Exception e) {
            throw new ServletException("DocumentoController: Update Error: Phase 1: " + e.getMessage());
        }

            try {
                oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
        return oDocumentoBean;
    }
}
