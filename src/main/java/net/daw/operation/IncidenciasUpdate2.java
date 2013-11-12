/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.IncidenciasBean;
import net.daw.dao.IncidenciasDao;
import net.daw.helper.Contexto;
import net.daw.parameter.IncidenciasParam;

/**
 *
 * @author Jacobo
 */
public class IncidenciasUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        IncidenciasBean oIncidenciasBean = new IncidenciasBean();
        IncidenciasDao oIncidenciasDao = new IncidenciasDao(oContexto.getEnumTipoConexion());
        IncidenciasParam oIncidenciasParam = new IncidenciasParam(request);
        oIncidenciasBean = oIncidenciasParam.loadId(oIncidenciasBean);
        try {
            oIncidenciasBean = oIncidenciasParam.load(oIncidenciasBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            //oIncidenciasDao.set(oIncidenciasBean);
        } catch (Exception e) {
            throw new ServletException("IncidenciasController: Update Error: Phase 2: " + e.getMessage());
        }
        return "Se ha modificado la información de la incidencia con id=" + Integer.toString(oIncidenciasBean.getId());
    }
}