/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author al037877
 */
public class BolsaBean {

    private int id = 0;
    private int id_documento1;
    private int id_documento2;
    private Date fecha;
    private DocumentoBean documento1;
    private DocumentoBean documento2;

    public BolsaBean() {

    }

    public BolsaBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_documento1() {
        return id_documento1;
    }

    public void setId_documento1(int id_documento1) {
        this.id_documento1 = id_documento1;
    }

    public int getId_documento2() {
        return id_documento2;
    }

    public void setId_documento2(int id_documento2) {
        this.id_documento2 = id_documento2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DocumentoBean getDocumento1() {
        return documento1;
    }

    public void setDocumento1(DocumentoBean documento1) {
        this.documento1 = documento1;
    }

    public DocumentoBean getDocumento2() {
        return documento2;
    }

    public void setDocumento2(DocumentoBean documento2) {
        this.documento2 = documento2;
    }    
}
