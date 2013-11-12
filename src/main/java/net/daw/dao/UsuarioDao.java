package net.daw.dao;

import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;

public class UsuarioDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public UsuarioDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public UsuarioBean getFromLogin(UsuarioBean oUsuario) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            String strId = oMysql.getId("usuario", "login", oUsuario.getLogin());
            if (strId == null) {
                oUsuario.setId(0);
            } else {
                oUsuario.setId(Integer.parseInt(strId));
                oUsuario.setPassword(oMysql.getOne("usuario", "password", oUsuario.getId()));
            }
            oMysql.desconexion();
            return oUsuario;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPage: Error: " + e.getMessage());
        }
    }
    
    public UsuarioBean get(UsuarioBean oUsuario) throws Exception {
        if (oUsuario.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("usuario", oUsuario.getId())) {
                    oUsuario.setId(0);
                } else{
                    oUsuario.setLogin(oMysql.getOne("usuario", "login", oUsuario.getId()));
                }
            
            } catch (Exception e) {
            throw new Exception("UsuarioDao.get: Error: " + e.getMessage());
        }
        }else{
            oUsuario.setId(0);
        }
        return oUsuario;
        
    }
}
