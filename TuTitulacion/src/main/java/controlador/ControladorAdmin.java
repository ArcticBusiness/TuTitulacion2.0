package controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class ControladorAdmin {

    private List<Usuario> usuarios;

    /**
     *
     * @param email
     * @return
     */
    public String eliminaUsuario(String email) {
        
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.getUsuarioByEmail(email);

        try {
            PreguntaDeDAO pdedao = new PreguntaDeDAO();
            PreguntaDe pde = pdedao.getPreguntaDeByIdUsuario(u.getIdUsuario());
            Pregunta p = pde.getPregunta();
            pdedao.elimina(pde);
            PreguntaDAO pdao = new PreguntaDAO();
            pdao.elimina(p);
        } catch (Exception e) {
        }
        try {
            ComentarioEnDAO cendao = new ComentarioEnDAO();
            ComentarioEn cen = cendao.getComentarioEnByIdUsuario(u.getIdUsuario());
            Comentario c = cen.getComentario();
            cendao.elimina(cen);
            ComentarioDAO cdao = new ComentarioDAO();
            cdao.elimina(c);
        } catch (Exception e) {
        }
        udao.elimina(u);
        usuarios = udao.mostrarUsuarios();
        return "/index.xhtml";
    }

    /**
     *
     */
    @PostConstruct
    public void verUsuarios() {
        UsuarioDAO udao = new UsuarioDAO();
        this.usuarios = udao.mostrarUsuarios();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
