/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import modelo.ComentarioEn;
import modelo.ComentarioEnDAO;

@ManagedBean
@SessionScoped
public class ControladorComentarioEn implements Serializable{
    
    private List<ComentarioEn> comentarios;
    private int identificadorP; //almacenar el identificador de la Pregunta 
                                //de la que se mostraran sus comentarios
    private String titulo; 
    
    /*
    @PostConstruct
    public void verComentarios() {
        ComentarioEnDAO ced = new ComentarioEnDAO();
        comentarios = ced.mostrarComentariosDe(1);
        //comentarios = ced.mostrarComentariosDe(identificadorP);
    }*/
    public String verComentariosEn(int id, String s){
        System.out.println("Metodo verComentariosEn");
        System.out.println(id);
        System.out.println("****************");
        if(!"".equals(id)){
            identificadorP=id;
            titulo=s;
            ComentarioEnDAO cdao = new ComentarioEnDAO();
            //mostramos los comentarios del id de la pregunta 
            comentarios = cdao.mostrarComentariosDe(id);
            return "vistaPregunta?faces-redirect=true";
        }
        return "";
        
    }

    public List<ComentarioEn> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEn> comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdentificadorP() {
        return identificadorP;
    }

    public void setIdentificadorP(int identificadorP) {
        this.identificadorP = identificadorP;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

}