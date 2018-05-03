package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.CodigoVerificacionDe;
import modelo.CodigoVerificacionDeDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ControladorVerificacionCorreo {

    private String codigoVerificacion;
    private Integer fkUsuario;

    public ControladorVerificacionCorreo() {
    }

    public String verificaCuenta() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("j_idt7:j_idt10");
        this.fkUsuario = Integer.parseInt(value);
        CodigoVerificacionDeDAO cDAO = new CodigoVerificacionDeDAO();
        CodigoVerificacionDe cvd = cDAO.getVerificacion(this.fkUsuario, this.codigoVerificacion);
        if (cvd != null) {
            System.out.println("Código correcto");
            UsuarioDAO uDAO = new UsuarioDAO();
            Usuario u = uDAO.getUsuarioById(fkUsuario);
            u.setCorreoVerificado(true);
            uDAO.actualiza(u);
            cDAO.elimina(cvd);
            return "index";
        } else {    
            System.out.println("Código de verificación incorrecto");                
            return "verificacion_correo";
        }
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
