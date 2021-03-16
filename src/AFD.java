public class AFD { // AFD = (Q, Σ, δ, q0, F)
    protected State q;
    protected String w = "";

    public AFD(State q, String w) {
        this.q = q;
        this.w = w;
    }
    public boolean run(GUI gui) {
        int a=0;
        if(q==null || w==null)
            return false;

            while (!q.isFinal()){
                Character atual = gui.getFita()[gui.getIndex()].getC();
                if(atual.equals(' ')){
                    atual = '_';
                }

                try{
                    Thread.sleep(1000);
                }catch (InterruptedException i){
                    i.printStackTrace();
                }
                Transition transition = q.transition(atual);
                if(transition == null){
                    if(q.transition('*') == null){
                        System.out.println(atual + " nao pertence ao alfabeto ou nao possui transicao!!");
                        return false;
                    }else{
                        transition = q.transition('*');
                    }
                }

                if(!transition.getWrite().equals('*')){
                    if(transition.getWrite().equals('_')){
                        gui.write(' ');
                    }else{
                        gui.write(transition.getWrite());
                    }
                }


                if(transition.getDir().equals('>')){
                    gui.bombineRight();
                }else if(transition.getDir().equals('<')){
                    gui.bombineLeft();
                }

                State qNext = transition.getState();
                System.out.println(q.getName() + "(" + atual + ") -> " + qNext.getName());
                q = qNext;
            }




        return q.isFinal();
    }
}
