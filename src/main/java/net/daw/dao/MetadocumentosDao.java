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
import net.daw.bean.MetadocumentosBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author Alvaro
 */
public class MetadocumentosDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    /**
     *
     * @param tipoConexion
     * @throws Exception
     */
    public MetadocumentosDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    /**
     *
     * @param intRegsPerPag
     * @param alFilter
     * @param hmOrder
     * @return
     * @throws Exception
     */
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("metadocumentos", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     *
     * @param hmFilter
     * @return
     * @throws Exception
     */
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("metadocumentos", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("MetadocumentosDao.getCount: Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param intRegsPerPag
     * @param intPage
     * @param alFilter
     * @param hmOrder
     * @return
     * @throws Exception
     */
    public ArrayList<MetadocumentosBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<MetadocumentosBean> arrMetadocumentos = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("metadocumentos", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean(iterador.next());
                arrMetadocumentos.add(this.get(oMetadocumentosBean));
            }
            oMysql.desconexion();
            return arrMetadocumentos;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     *
     * @param strLink
     * @param intPageNumber
     * @param intTotalPages
     * @param intNeighborhood
     * @return
     * @throws Exception
     */
    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }

    /**
     *
     * @param oMetadocumentosBean
     * @return
     * @throws Exception
     */
    public MetadocumentosBean get(MetadocumentosBean oMetadocumentosBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            //ProductoBean oProductoBean = new ProductoBean();
            DocumentoBean oDocumentoBean = new DocumentoBean();

            //oProductoBean.setId(Integer.parseInt(oMysql.getOne("compra", "id_producto", oMetadocumentosBean.getId())));
            oDocumentoBean.setId(Integer.parseInt(oMysql.getOne("metadocumentos", "id_documento", oMetadocumentosBean.getId())));

            oMetadocumentosBean.setOrden(Integer.parseInt(oMysql.getOne("metadocumentos", "orden", oMetadocumentosBean.getId())));

            //ProductoDao oProductoDao = new ProductoDao(enumTipoConexion);
            DocumentoDao oDocumentoDao = new DocumentoDao(enumTipoConexion);

            //oProductoBean = oProductoDao.get(oProductoBean);
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);

            //oMetadocumentosBean.setProducto(oProductoBean);
            oMetadocumentosBean.setId_documento(oDocumentoBean);

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ProductoDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oMetadocumentosBean;
    }

    /**
     *
     * @param oMetadocumentosBean
     * @throws Exception
     */
    public void set(MetadocumentosBean oMetadocumentosBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oMetadocumentosBean.getId() == 0) {
                oMetadocumentosBean.setId(oMysql.insertOne("metadocumentos"));
            }
            // oMysql.updateOne(oMetadocumentosBean.getId(), "compra", "id_metadocumento", oMetadocumentosBean.getDocumento().getId().toString());
            oMysql.updateOne(oMetadocumentosBean.getId(), "metadocumentos", "id_documento", Integer.toString(oMetadocumentosBean.getId_documento().getId()));
            oMysql.updateOne(oMetadocumentosBean.getId(), "metadocumentos", "orden", Integer.toString(oMetadocumentosBean.getOrden()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("MetadocumentosDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     *
     * @param oMetadocumentosBean
     * @throws Exception
     */
    public void remove(MetadocumentosBean oMetadocumentosBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oMetadocumentosBean.getId(), "metadocumentos");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("MetadocumentosDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
