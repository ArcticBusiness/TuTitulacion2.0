/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioDAO;
import util.SessionUtils;

/**
 *
 * @author Felipe
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private String pwd;
    private String user;

    /**
     * 
     * @return 
     */
    public String iniciarSesion() {
        UsuarioDAO em = new UsuarioDAO();
        Usuario usuario = em.encuentra(user, pwd);
        if (usuario != null) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "/index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Cuenta o contraseña incorrecta",
                            "Por favor, intentelo de nuevo"));
            return "iniciarSesion";
        }
    }

    /**
     * 
     * @return 
     */
    public String cerrarSesion() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/iniciarSesion";
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
