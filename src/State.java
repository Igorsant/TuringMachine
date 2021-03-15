import java.util.ArrayList;
import java.util.List;

public class State {
    public final List<Transition> transitions = new ArrayList<>(); // public pra debug
    private final String name;
    private Boolean isFinal = false;

    public State(String name) { this.name = name; }
    public String getName() { return this.name; }
    public void setFinal() { this.isFinal = true; }
    public Boolean isFinal() { return this.isFinal; }

    public State addTransition(Character cLido, State target, Character cEscrito, String direcao) {
        return addTransition(target, Edge.instance(cLido), cEscrito, direcao);
    }

    private State addTransition(State sProximo, Edge eAtual, Character cEscrito, String direcao) {
        Transition transition = new Transition(sProximo, eAtual, cEscrito, direcao);
        if(!transitions.contains(transition)) transitions.add(transition);
        return this;
    }

    public Transition transition(Character _c) {
        for(Transition t: transitions) {
            Edge e = t.getEdge();
            if(e.getC()!=null && e.getC().equals(_c))
                return t;
        }
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof State) {
            State s = (State) o;
            return s.getName().equals(this.getName());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}