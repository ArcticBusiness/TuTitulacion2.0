package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Set;
import javax.faces.application.FacesMessage;
import modelo.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import modelo.CodigoVerificacionDe;
import modelo.CodigoVerificacionDeDAO;
import modelo.UsuarioDAO;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ControladorUsuario implements Serializable {

    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String confirmacionContrasenia;
    private String correoElectronico;
    private String urlImagen;
    private UploadedFile fotoPerfil;

    /**
     * Creates a new instance of ControladorUsuario
     */
    public ControladorUsuario() {
    }

    /**
     *
     * @return
     */
    public String registra() {
        Usuario u = new Usuario(nombreUsuario, contrasenia, correoElectronico, urlImagen, false);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Usuario>> validationErrors = validator.validate(u);
        boolean coinciden = contrasenia.equals(confirmacionContrasenia);
        boolean hayArchivo = !fotoPerfil.getFileName().equals("");
        boolean esFoto = false;
        if (hayArchivo) {
            if (fotoPerfil.getFileName().length() > 5) {
                String tipo = fotoPerfil.getFileName().substring(fotoPerfil.getFileName().length() - 3, fotoPerfil.getFileName().length());
                if (tipo.equals("jpg") || tipo.equals("png") || tipo.equals("gif")) {
                    esFoto = true;
                }
            }
        }
        if (!validationErrors.isEmpty() || !coinciden || (hayArchivo && !esFoto)) {
            if (!coinciden) {
                FacesContext.getCurrentInstance().addMessage("registro_form:pswd2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", null));
            }
            if (hayArchivo && !esFoto) {
                FacesContext.getCurrentInstance().addMessage("registro_form:foto", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El archivo debe ser jpg, png o gif", null));
            }
            for (ConstraintViolation<Usuario> error : validationErrors) {
                System.out.println(error.getMessageTemplate() + "::" + error.getPropertyPath() + "::" + error.getMessage());
                switch (error.getPropertyPath().toString()) {
                    case "contrasenia":
                        FacesContext.getCurrentInstance().addMessage("registro_form:pswd1", new FacesMessage(FacesMessage.SEVERITY_ERROR, error.getMessage(), null));
                        break;
                    case "nombreUsuario":
                        FacesContext.getCurrentInstance().addMessage("registro_form:nombreUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, error.getMessage(), null));
                        break;
                    case "correoElectronico":
                        FacesContext.getCurrentInstance().addMessage("registro_form:correo", new FacesMessage(FacesMessage.SEVERITY_ERROR, error.getMessage(), null));
                        break;
                }
            }
            fotoPerfil = null;
            return "registrar";
        } else {
            UsuarioDAO uDao = new UsuarioDAO();
            uDao.guarda(u);
            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
            VerificationMailSender vms = (VerificationMailSender) context.getBean("verificationMailSender");
            Random random = new Random();
            String codigo = String.format("%d%d%d%d%d%d", random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10));
            this.idUsuario = u.getIdUsuario();
            CodigoVerificacionDe cvd = new CodigoVerificacionDe(codigo, this.idUsuario);
            CodigoVerificacionDeDAO cdvDAO = new CodigoVerificacionDeDAO();
            cdvDAO.guarda(cvd);
            vms.sendMail("tutitulacion@gmail.com", correoElectronico, "Confirmación", "Tu código de verificación es: " + codigo);
            if (hayArchivo && esFoto) {
                String nuevoNombreFoto = u.getIdUsuario() + "." + fotoPerfil.getFileName().substring(fotoPerfil.getFileName().length() - 3, fotoPerfil.getFileName().length());
                copyFile(nuevoNombreFoto);
                u.setUrlImagen("/src/main/webapp/img_usuarios/" + nuevoNombreFoto);
                uDao.actualiza(u);
            } else {
                u.setUrlImagen("/src/main/webapp/img_usuarios/default.png");
                uDao.actualiza(u);
            }
            return "verificacion_correo";
        }
    }

    /**
     *
     * @param fileName
     */
    private void copyFile(String fileName) {
        try {
            OutputStream out = new FileOutputStream(new File("/home/miguel/NetBeansProjects/IngenieriaSoftware/TuTitulacion/src/main/webapp/img_usuarios/" + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            InputStream in = fotoPerfil.getInputstream();
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("New file created!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 
     * @return 
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * 
     * @param nombreUsuario 
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * 
     * @return 
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * 
     * @param contrasenia 
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * 
     * @return 
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * 
     * @param correoElectronico 
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getConfirmacionContrasenia() {
        return confirmacionContrasenia;
    }

    public void setConfirmacionContrasenia(String confirmacionContrasenia) {
        this.confirmacionContrasenia = confirmacionContrasenia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UploadedFile getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(UploadedFile fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

}
