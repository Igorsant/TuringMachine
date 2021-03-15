public class Transition {
    private final State state;
    private final Edge edge;
    public Transition(final State s, final Edge e) {
        this.state = s;
        this.edge = e;
    }
    public Edge getEdge() { return edge; }
    public State getState() { return state; }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Transition) {
            Transition t = (Transition) o;
            return t.getEdge().equals(edge) && t.getState().equals(state);
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hc = state != null? state.hashCode():0;
        hc = 13*hc + (edge!=null?edge.hashCode():0);
        return hc;
    }
    @Override
    public String toString() {
        return edge + "-->" + state.getName();
    }
}
