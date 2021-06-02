/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.adsi2141449a;

import edu.sena.entity.adsi2141449a.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Josarta
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "edu.sena_adsi2141449a_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public boolean registrarUsuario(Usuario usuReg){
        try {
            Query qt = em.createNativeQuery("INSERT INTO tbl_usuario (usu_tipo_documento, usu_documento, usu_nombres, usu_apellidos, usu_correo, usu_clave) VALUES (?, ?, ?, ?, ?, ?)");
            qt.setParameter(1, usuReg.getUsuTipoDocumento());
            qt.setParameter(2, usuReg.getUsuDocumento());
            qt.setParameter(3, usuReg.getUsuNombres());
            qt.setParameter(4, usuReg.getUsuApellidos());
            qt.setParameter(5, usuReg.getUsuCorreo());
            qt.setParameter(6, usuReg.getUsuClave());
            qt.executeUpdate();
            return true;            
        } catch (Exception e) {
            System.out.println("Error::registrarUsuario -> " + e.getMessage());
            return false;
        }
    }
    
    
    @Override
    public Usuario iniciarSession(String correoIn, String claveIn){
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuCorreo =:correoIn AND u.usuClave = :claveIn");
            q.setParameter("correoIn", correoIn);
            q.setParameter("claveIn", claveIn);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error::iniciarSession -> " + e.getMessage());
            return null;
        }
    }
    
    
    
    
    
}
