/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.DocumentoBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author al037294
 */
public class DocumentoDao {
    
    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public DocumentoDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("documento", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("DocumentoDao.getPages: Error: " + e.getMessage());
        }
    }

    public ArrayList<DocumentoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<DocumentoBean> arrDocumento = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("documento", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                DocumentoBean oDocumentoBean = new DocumentoBean(iterador.next());
                arrDocumento.add(this.get(oDocumentoBean));
            }
            oMysql.desconexion();
            return arrDocumento;
        } catch (Exception e) {
            throw new Exception("DocumentoDao.getPage: Error: " + e.getMessage());
        }
    }

    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }

    public DocumentoBean get(DocumentoBean oDocumentoBean) throws Exception {
        if (oDocumentoBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("documento", oDocumentoBean.getId())) {
                    oDocumentoBean.setId(0);
                } else {
                    oDocumentoBean.setNombre(oMysql.getOne("documento", "nombre", oDocumentoBean.getId()));
                    oDocumentoBean.setApe1(oMysql.getOne("documento", "ape1", oDocumentoBean.getId()));
                    oDocumentoBean.setApe2(oMysql.getOne("documento", "ape2", oDocumentoBean.getId()));
                    oDocumentoBean.setEmail(oMysql.getOne("documento", "email", oDocumentoBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("DocumentoDao.getDocumento: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oDocumentoBean.setId(0);
        }
        return oDocumentoBean;
    }

    public void set(DocumentoBean oDocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oDocumentoBean.getId() == 0) {
                oDocumentoBean.setId(oMysql.insertOne("documento"));
            }
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "nombre", oDocumentoBean.getNombre());
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "ape1", oDocumentoBean.getApe1());
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "ape2", oDocumentoBean.getApe2());
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "email", oDocumentoBean.getEmail());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("DocumentoDao.setDocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(DocumentoBean oDocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oDocumentoBean.getId(), "documento");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("DocumentoDao.removeDocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
