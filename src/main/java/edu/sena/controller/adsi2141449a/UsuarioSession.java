/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.adsi2141449a;

import edu.sena.entity.adsi2141449a.Usuario;
import edu.sena.facade.adsi2141449a.UsuarioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Josarta
 */
@Named(value = "usuarioSession")
@SessionScoped
public class UsuarioSession implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private Usuario usuReg = new Usuario();
    private Usuario usuLog = new Usuario();
    private String correoIn = "";
    private String claveIn = "";

    private String mensajes = "";

    /**
     * Creates a new instance of UsuarioSession
     */
    public UsuarioSession() {
    }

    public void registarUsuario() {
        if (usuarioFacadeLocal.registrarUsuario(usuReg)) {
            mensajes = "regOk";
        } else {
            mensajes = "regBad";
        }
        usuReg = new Usuario();
    }

    public void validarUsuario() throws IOException {
        usuLog = usuarioFacadeLocal.iniciarSession(correoIn, claveIn);
        if(usuLog == null){
            mensajes= "iniBad";
        }else{
            if(!usuLog.getUsuEstado()){
              mensajes= "iniBlo";
            }else{
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect("usuario/index.xhtml");
            }
           
        }
        
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public Usuario getUsuLog() {
        return usuLog;
    }

    public void setUsuLog(Usuario usuLog) {
        this.usuLog = usuLog;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClaveIn() {
        return claveIn;
    }

    public void setClaveIn(String claveIn) {
        this.claveIn = claveIn;
    }

}
