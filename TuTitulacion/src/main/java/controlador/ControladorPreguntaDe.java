/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.PreguntaDe;
import modelo.PreguntaDeDAO;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class ControladorPreguntaDe {

    private List<PreguntaDe> preguntas;

    @PostConstruct
    public void verPreguntas() {
        PreguntaDeDAO pd = new PreguntaDeDAO();
        preguntas = pd.mostrarPreguntas();
    }

    public List<PreguntaDe> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaDe> preguntas) {
        this.preguntas = preguntas;
    }

}
