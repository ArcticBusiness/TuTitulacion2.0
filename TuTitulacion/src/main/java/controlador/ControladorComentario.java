/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Comentario;
import modelo.ComentarioDAO;
import modelo.ComentarioEn;
import modelo.ComentarioEnDAO;
import modelo.Pregunta;
import modelo.PreguntaDAO;
import modelo.PreguntaDe;
import modelo.PreguntaDeDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import util.SessionUtils;

/**
 *
 * @author miguel
 */
@Named(value = "controladorComentario")
//@SessionScoped
@ManagedBean
@ViewScoped
public class ControladorComentario {
    
    private int idPregunta;
    private Pregunta pregunta;
    private String comentario;
    private ComentarioDAO comentarioDao;
    private List<Comentario> comentarios;
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    

    /**
     * Creates a new instance of ControladorComentario
     */
    public ControladorComentario() {
        comentarioDao = new ComentarioDAO();
    }
    
    public ComentarioDAO getComentarioDao() {
        return comentarioDao;
    }

    public void setComentarioDao(ComentarioDAO comentarioDao) {
        this.comentarioDao = comentarioDao;
    }
    
    public List<Comentario> getComentarios(){
        return comentarios;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
    
    
     /**
     * peticion que guarda un comentario
     */
    public String comentar(){
        System.out.println("Creando Comentario");
        Comentario c = new Comentario();
        c.setContenidoComentario(comentario);
        c.setFecha(new Date());
        ComentarioDAO cd = new ComentarioDAO();
        cd.guarda(c);
        
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.getUsuarioByEmail((String)SessionUtils.getSession().getAttribute("username"));
        
        PreguntaDAO pdao = new PreguntaDAO();
        Pregunta p = pdao.getPreguntaById(idPregunta);
        
        ComentarioEn cEn = new ComentarioEn(c ,p ,u);     
        ComentarioEnDAO cendao = new ComentarioEnDAO();
        cendao.guarda(cEn);
        return "/index?faces-redirect=true"; 
        
    }
    @PostConstruct
    public void verComentarios(){
        ComentarioDAO cd = new ComentarioDAO();
        comentarios = cd.mostrarComentarios();
    }

}