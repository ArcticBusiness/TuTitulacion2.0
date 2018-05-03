package controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Pregunta;
import modelo.PreguntaDAO;

@ManagedBean
@RequestScoped
public class VerBuscaPreguntas {
    private List<Pregunta> preguntas;
    private List<Pregunta> resultados;
    private String search;
    
    public String getSearch(){
        return search;
    }
    public void setSearch(String contenido){
        search=contenido;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
    @PostConstruct
    public void ver() {
        PreguntaDAO lib = new PreguntaDAO();
        resultados= lib.buscar(search);
    }

    public List<Pregunta> getResultados() {
        return resultados;
    }

    public void setResultados(List<Pregunta> resultados) {
        this.resultados = resultados;
    }
    
    public String busca() {
        PreguntaDAO lib = new PreguntaDAO();
        resultados= lib.buscar(search);
        return "/buscar";
    }
}
