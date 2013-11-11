/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.DocumentoBean;

/**
 *
 * @author al037294
 */
public class DocumentoParam {
    
    private final HttpServletRequest request;

    public DocumentoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public DocumentoBean loadId(DocumentoBean oDocumento) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oDocumento.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oDocumento.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oDocumento;
    }

    public DocumentoBean load(DocumentoBean oDocumento) throws NumberFormatException {
        try {
            if ((request.getParameter("titulo") != null)) {
                oDocumento.setTitulo(request.getParameter("titulo"));
            }
            if ((request.getParameter("contenido") != null)) {
                oDocumento.setContenido(request.getParameter("contenido"));
            }
            if ((request.getParameter("fecha") != null)) {
                //oDocumento.setFecha(request.getParameter("fecha"));
            }
            if ((request.getParameter("nota") != null)) {
                oDocumento.setNota(Integer.parseInt(request.getParameter("nota")));
            }
            if ((request.getParameter("id_usuario") != null)) {
                oDocumento.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("etiquetas") != null)) {
                oDocumento.setEtiquetas(request.getParameter("etiquetas"));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oDocumento;
    }
}
