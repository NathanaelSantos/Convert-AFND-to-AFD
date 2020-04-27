package ConvertAFND;

import java.util.ArrayList;
import java.util.List;
import ConvertAFND.EstadoFinal;
import ConvertAFND.EstadoInicial;
import ConvertAFND.Estados;

public class Automato {

    private List<Transicao> transition = new ArrayList();
    private List<Estados> estado = new ArrayList();
    private List<EstadoFinal> estadofinal = new ArrayList();
    private EstadoInicial estadoincial = new EstadoInicial();
    private String alfabeto;

    public List<Transicao> getTransition() {
        return transition;
    }

    public void setTransition(List<Transicao> transition) {
        this.transition = transition;
    }

    public List<Estados> getEstado() {
        return estado;
    }

    public void setEstado(List<Estados> estado) {
        this.estado = estado;
    }

    public List<EstadoFinal> getEstadofinal() {
        return estadofinal;
    }

    public void setEstadofinal(List<EstadoFinal> estadofinal) {
        this.estadofinal = estadofinal;
    }

    public EstadoInicial getEstadoincial() {
        return estadoincial;
    }

    public void setEstadoincial(EstadoInicial estadoincial) {
        this.estadoincial = estadoincial;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }
}
