package modelo;
// Generated 9/04/2018 11:24:07 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Comentario generated by hbm2java
 */
public class Comentario  implements java.io.Serializable {


     private int idComentario;
     private String contenidoComentario;
     private Date fecha;
     private Set comentarioEns = new HashSet(0);

    public Comentario() {
    }

	
    public Comentario(int idComentario) {
        this.idComentario = idComentario;
    }
    public Comentario(int idComentario, String contenidoComentario, Date fecha, Set comentarioEns) {
       this.idComentario = idComentario;
       this.contenidoComentario = contenidoComentario;
       this.fecha = fecha;
       this.comentarioEns = comentarioEns;
    }
   
    public int getIdComentario() {
        return this.idComentario;
    }
    
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
    public String getContenidoComentario() {
        return this.contenidoComentario;
    }
    
    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Set getComentarioEns() {
        return this.comentarioEns;
    }
    
    public void setComentarioEns(Set comentarioEns) {
        this.comentarioEns = comentarioEns;
    }




}


