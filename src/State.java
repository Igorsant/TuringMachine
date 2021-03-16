import java.util.ArrayList;
import java.util.List;

public class State {
    private final String name;
    private Boolean isFinal = false;
    private final List<Transition> transitions = new ArrayList<Transition>();

    public State(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
    public void setFinal() { this.isFinal = true; }
    public Boolean isFinal() { return this.isFinal; }

    //caractereLido.charAt(0), StateProximo, caractereEscrito.charAt(0), direcao
    public State addTransition(State target, Character c, Character write, Character dir) {
        return addTransiton(target, write, dir, Edge.instance(c));
    }
    private State addTransiton(State target, Character write, Character dir, Edge... edges) {
        for (Edge e : edges) {
            Transition transition = new Transition(target, e, write, dir);
            if(transitions.contains(transition))
                continue;
            transitions.add(transition);
        }
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
