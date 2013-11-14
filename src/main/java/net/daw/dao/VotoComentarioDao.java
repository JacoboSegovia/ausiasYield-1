/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.VotoComentarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author Diana Ortega
 */
public class VotoComentarioDao {
    
    private Mysql oMysql;
    private net.daw.helper.Enum.Connection enumTipoConexion;

    /**
     *
     * @author Diana Ortega
     * @param tipoConexion
     */
    public VotoComentarioDao(net.daw.helper.Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    /**
     *
     * @author Diana Ortega
     * @param oVotoComentarioBean
     * @return
     * @throws Exception
     */
       public VotoComentarioBean get(VotoComentarioBean oVotoComentarioBean) throws Exception {
        if (oVotoComentarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("votoComentario", oVotoComentarioBean.getId())) {
                    oVotoComentarioBean.setId(0);
                } else {
                    //oVotoComentarioBean.setId_comentario(Integer.parseInt(oMysql.getOne("votoComentario", "id_comentario", oVotoComentarioBean.getId())));
                    //oVotoComentarioBean.setId_usuario(Integer.parseInt(oMysql.getOne("votoComentario", "id_usuario", oVotoComentarioBean.getId())));
                    oVotoComentarioBean.setValor(Integer.parseInt(oMysql.getOne("votoComentario", "valor", oVotoComentarioBean.getId())));                    
                }
            } catch (Exception e) {
                throw new Exception("VotoComentarioDao.getVotoComentario: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oVotoComentarioBean.setId(0);
        }
        return oVotoComentarioBean;
    }
    
      public void set(VotoComentarioBean oVotoComentarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oVotoComentarioBean.getId() == 0) {
                oVotoComentarioBean.setId(oMysql.insertOne("votoComentario"));
            }
            //oMysql.updateOne(oVotoComentarioBean.getId(), "votoComentario", "id_comentario", oVotoComentarioBean.getId_comentario());
            //oMysql.updateOne(oVotoComentarioBean.getId(), "votoComentario", "id_usuario", oVotoComentarioBean.getNombre());
            oMysql.updateOne(oVotoComentarioBean.getId(), "votoComentario", "valor", Integer.toString(oVotoComentarioBean.getValor()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("VotoComentarioDao.setVotoComentario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    
    public void remove(VotoComentarioBean oVotoComentarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oVotoComentarioBean.getId(), "votoComentario");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.removeVotoComentario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("votoComentario", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.getPages: Error: " + e.getMessage());
        }
    }

    public ArrayList<VotoComentarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<VotoComentarioBean> arrVotoComentario = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("votoComentario", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                VotoComentarioBean oVotoComentarioBean = new VotoComentarioBean(iterador.next());
                arrVotoComentario.add(this.get(oVotoComentarioBean));
            }
            oMysql.desconexion();
            return arrVotoComentario;
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.getPage: Error: " + e.getMessage());
        }
    }

    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }

    public Integer getCount(ArrayList<FilterBean> alFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("votoComentario",  alFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.getCount: Error: " + e.getMessage());
        }
    }
    }