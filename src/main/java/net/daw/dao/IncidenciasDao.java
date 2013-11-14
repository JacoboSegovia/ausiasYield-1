/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.daw.bean.IncidenciasBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

/**
 *
 * @author al037877
 */
public class IncidenciasDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    /**
     *
     * @author Enrique Gimeno
     * @param tipoConexion
     */
    public IncidenciasDao(Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    /**
     *
     * @author Enrique Gimeno
     * @param oIncidenciasBean
     * @return
     * @throws Exception
     */
    public IncidenciasBean get(IncidenciasBean oIncidenciasBean) throws Exception {
        if (oIncidenciasBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("incidencias", oIncidenciasBean.getId())) {
                    oIncidenciasBean.setId(0);
                } else {

                    oMysql.conexion(enumTipoConexion);
                    oIncidenciasBean.setResumen(oMysql.getOne("incidencias", "resumen", oIncidenciasBean.getId()));
                    oIncidenciasBean.setCambios(oMysql.getOne("incidencias", "cambios", oIncidenciasBean.getId()));
//                    oIncidenciasBean.setId_estado(Integer.parseInt(oMysql.getOne("incidencias", "id_estado", oIncidenciasBean.getId())));
//                    oIncidenciasBean.setId_repositorio(Integer.parseInt(oMysql.getOne("incidencias", "id_repositorio", oIncidenciasBean.getId())));
//                    oIncidenciasBean.setId_usuario(Integer.parseInt(oMysql.getOne("incidencias", "id_usuario", oIncidenciasBean.getId())));
                    oIncidenciasBean.setFechaAlta(oMysql.getOne("incidencias", "fechaalta", oIncidenciasBean.getId()));
                    oIncidenciasBean.setFechaResolucion(oMysql.getOne("incidencias", "fecharesolucion", oIncidenciasBean.getId()));

                }


            } catch (Exception e) {
                throw new Exception("IncidenciasDao.getIncidencias: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oIncidenciasBean.setId(0);
        }
        return oIncidenciasBean;
    }

    public void set(IncidenciasBean oIncidenciasBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oIncidenciasBean.getId() == 0) {
                oIncidenciasBean.setId(oMysql.insertOne("incidencias"));
            }
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id", String.valueOf(oIncidenciasBean.getId()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "resumen", oIncidenciasBean.getResumen());
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "cambios", oIncidenciasBean.getCambios());
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id_estado", String.valueOf(oIncidenciasBean.getId_estado()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id_repositorio", String.valueOf(oIncidenciasBean.getId_repositorio()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id_usuario", String.valueOf(oIncidenciasBean.getId_usuario()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "fechaalta", oIncidenciasBean.getFechaAlta());
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "fecharesolucion", oIncidenciasBean.getFechaResolucion());
            oMysql.commitTrans();       

        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("IncidenciasDao.setIncidencias: Error: " + e.getMessage());

        }

    }

    public void remove(IncidenciasBean oIncidenciasBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oIncidenciasBean.getId(), "incidencias");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("IncidenciasDao.removeIncidencias: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

     public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("incidencias", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("IncidenciasDao.getPages: Error: " + e.getMessage());
        }
    }

    public int getCount( ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("incidencias",  hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("IncidenciasDao.getCount: Error: " + e.getMessage());
        }
    }
  
    public ArrayList<IncidenciasBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<IncidenciasBean> arrIncidencias = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("incidencias", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                IncidenciasBean oIncidenciasBean = new IncidenciasBean(iterador.next());
                arrIncidencias.add(this.get(oIncidenciasBean));
            }
            oMysql.desconexion();
            return arrIncidencias;
        } catch (Exception e) {
            throw new Exception("IncidenciasDao.getPage: Error: " + e.getMessage());
        }
    }

    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }
}