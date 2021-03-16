import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Main{ // AFD = (Q, Σ, δ, q0, F)
    /*
     * L1 = {w in Σ* | w é par ou vazio}
     * L2 = {w in Σ* | w comeca com y e termina com x}
     * L3 = {w in Σ* | w é float ou for}
     */
    private GUI gui = null;
    public static void main(String[] args) throws InterruptedException{
        Main main = new Main();

        main.start();
    }

    public void start(){
        this.gui = new GUI();
        try{
            makeTransitions(gui);
        }catch (IOException i){
            i.printStackTrace();
        }

    }

    public void makeTransitions(GUI gui) throws IOException {
        ArrayList<State> listaEstados = new ArrayList<>();

        try{
            // Abrir janela para selecionar arquivo
            JFileChooser jfc = new JFileChooser();
            jfc.showDialog(null, "Selecionar");
            File f = jfc.getSelectedFile();
            if(!f.exists()) return;

            String fita, estadoInicial, estadoFinal;
            fita = estadoInicial = estadoFinal = null;

            BufferedReader bf = new BufferedReader(new FileReader(f));

            // Ler arquivo
            while(true) {
                String linha = bf.readLine();

                if(linha == null) break; // Verificar se o arquivo possui 'end' no final para parar de ler

                if(linha.length() == 0 || linha.charAt(0) == '@') continue;                                        // Comentário
                if(linha.contains("fita") && fita == null) fita = linha.split(" ")[1];                         // Fita
                else if(linha.contains("init") && estadoInicial == null){
                    estadoInicial = linha.split(" ")[1]; //Estado Inicial
                    listaEstados.add(new State(estadoInicial));
                }else if(linha.contains("accept") && estadoFinal == null){
                    estadoFinal = linha.split(" ")[1];   // Estado Final
                    listaEstados.add(new State(estadoFinal));
                }
                else if(linha.contains(",") && linha.split(",").length == 5) {                                // Estados...

                    String estadoAtual, caractereLido, proximoEstado, caractereEscrito, direcao;

                    String[] valores = linha.split(",");
                    estadoAtual = valores[0];
                    caractereLido = valores[1];
                    proximoEstado = valores[2];
                    caractereEscrito = valores[3];
                    direcao = valores[4];

                    State StateAtual = null, StateProximo = null;

                    for(State st: listaEstados) {
                        if(st.getName().equals(estadoAtual)) StateAtual = st;
                        if(st.getName().equals(proximoEstado)) StateProximo = st;
                        if(st.getName().equals(estadoFinal)) st.setFinal();
                    }

                    if(StateAtual == null) {
                        StateAtual = new State(estadoAtual);
                        listaEstados.add(StateAtual);
                    }

                    if(StateProximo == null) {
                        StateProximo = new State(proximoEstado);
                        listaEstados.add(StateProximo);
                    }

                    int pos = listaEstados.indexOf(StateAtual);

                    // Adicionar na lista de estados de acordo com a linha de instrução do arquivo
                    listaEstados.get(pos).addTransition(StateProximo, caractereLido.charAt(0), caractereEscrito.charAt(0), direcao.charAt(0));
                }
            }

            State inicial = null;
            for(State s:listaEstados){
                if(s.getName().equals(estadoInicial)){
                    inicial = s;
                    break;
                }
            }
            AFD afd = new AFD(inicial, fita);
            gui.initFita(fita);
            Thread t = new Thread(){
                @Override
                public void run() {
                    while (true){
                        gui.render(this);
                        try{
                            Thread.sleep(50);
                        }catch (InterruptedException i){
                            i.getCause();
                        }

                    }
                }
            };
            t.start();
            verify(afd.run(gui), fita);

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo! - " + e.getMessage());
        }

    }

    public void verify(boolean b, String w) {
        if(b) {
            gui.successAlert(w);
            return;
        }
        gui.failAlert(w);
        System.out.println("Nao reconheceu: " + w);
    }
}