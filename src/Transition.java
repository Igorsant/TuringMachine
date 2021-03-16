public class Transition {
    private final State state;
    private final Edge edge;
    private final Character write;
    private final Character dir;

    public Transition(final State s, final Edge e, final Character write, final Character dir) {
        this.state = s;
        this.edge = e;
        this.write = write;
        this.dir = dir;
    }
    public Edge getEdge() { return edge; }
    public State getState() { return state; }
    public Character getWrite(){ return write; }
    public Character getDir() { return dir; }

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
