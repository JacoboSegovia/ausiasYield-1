/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BolsaBean;
import net.daw.dao.DocumentoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BolsaParam;

/**
 *
 * @author Alvaro
 */
public class BolsaNew1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        BolsaParam oBolsaParam = new BolsaParam(request);
        BolsaBean oBolsaBean = new BolsaBean();
        //DocumentoDao oDocumentoDao1 = new DocumentoDao(oContexto.getEnumTipoConexion());
        //DocumentoDao oDocumentoDao2 = new DocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oBolsaBean = oBolsaParam.load(oBolsaBean);
            oBolsaBean = oBolsaParam.load(oBolsaBean);
            //oBolsaBean.setDocumento1(oDocumentoDao1.get(oBolsaBean.getDocumento1()));
            //oBolsaBean.setDocumento2(oDocumentoDao2.get(oBolsaBean.getDocumento2()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/bolsa/form.jsp");
        return oBolsaBean;
    }
}
