/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.RepositorioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;

/**
 *
 * @author al037877
 */
public class RepositorioDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    /**
     *
     * @author Alvaro Crego
     * @param tipoConexion
     */
    public RepositorioDao(Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    /**
     *
     * @author Alvaro Crego
     * @param oRepositorioBean
     * @return
     * @throws Exception
     */
    public RepositorioBean get(RepositorioBean oRepositorioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oRepositorioBean.setTitulo(oMysql.getOne("repositorio", "titulo", oRepositorioBean.getId()));
            oRepositorioBean.setContenido(oMysql.getOne("repositorio", "contenido", oRepositorioBean.getId()));
            oRepositorioBean.setId_usuario(Integer.parseInt(oMysql.getOne("repositorio", "id_usuario", oRepositorioBean.getId())));
            oRepositorioBean.setId_lenguaje(Integer.parseInt(oMysql.getOne("repositorio", "id_lenguaje", oRepositorioBean.getId())));
            oRepositorioBean.setId_documento(Integer.parseInt(oMysql.getOne("repositorio", "id_documento", oRepositorioBean.getId())));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            oRepositorioBean.setFecha(formato.parse(oMysql.getOne("repositorio", "fecha", oRepositorioBean.getId())));
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getRespositorio: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oRepositorioBean;
    }

    public void set(RepositorioBean oRepositorioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oRepositorioBean.getId() == 0) {
                oRepositorioBean.setId(oMysql.insertOne("repositorio"));
            }
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "titulo", oRepositorioBean.getTitulo());
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "contenido", oRepositorioBean.getContenido());
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "id_usuario", Integer.toString(oRepositorioBean.getId_usuario()));
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "id_lenguaje", Integer.toString(oRepositorioBean.getId_lenguaje()));
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "id_documento", Integer.toString(oRepositorioBean.getId_documento()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("RepositorioDao.setRepositorio: Error: " + e.getMessage());

        }      
    
    }
    public void remove(RepositorioBean oRepositorioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oRepositorioBean.getId(), "repositorio");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("RepositorioDao.removeRepositorio: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
/*
    public int getPages(int intRegsPerPag, HashMap<String, String> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("repositorio", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getPages: Error: " + e.getMessage());
            
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<RepositorioBean> getPage(int intRegsPerPag, int intPage, HashMap<String, String> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<RepositorioBean> arrProducto = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("repositorio", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                RepositorioBean oRepositorioBean = new RepositorioBean(iterador.next());
                arrProducto.add(this.get(oRepositorioBean));
            }
            oMysql.desconexion();
            return arrProducto;
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }*/

    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }
}
