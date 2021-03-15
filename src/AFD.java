public class AFD { // AFD = (Q, Σ, δ, q0, F)
    protected State q;
    protected String w = "";

    public AFD(State q, String w) {
        this.q = q;
        this.w = w;
    }
    public boolean run() {
        if(q==null || w==null)
            return false;
        for(Character c: this.w.toCharArray()) {

            Transition transition = q.transition(c);
            if(transition == null) {
                System.out.println(c + " nao pertence ao alfabeto ou nao possui transicao!!");
                return false;
            }

            State qNext = transition.getState();
            System.out.println(q.getName() + "(" + c + ") -> " + qNext.getName());
            q = qNext;
        }
        return q.isFinal();
    }
}
