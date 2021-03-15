import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{ // AFD = (Q, Σ, δ, q0, F)
    /*
     * L1 = {w in Σ* | w é par ou vazio}
     * L2 = {w in Σ* | w comeca com y e termina com x}
     * L3 = {w in Σ* | w é float ou for}
     */

    public Main(){

    }

    public static void main(String[] args) throws Exception {
//        Main main = new Main();
//        main.start();
        makeTransitions();
    }

    public void start(){
        GUI gui = new GUI();
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){
                    gui.render();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException i) {
                        i.getCause();
                    }

                }
            }
        };

        t.start();

    }

    // Arquivo precisa ter 'end' no final para funcionar, caso não tiver , sairá do loop através de erro!
    public static void makeTransitions() throws IOException {
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
                String linha = bf.readLine().trim();

                if(linha.endsWith("end")) break; // Verificar se o arquivo possui 'end' no final para parar de ler

                if(linha.length() == 0 || linha.charAt(0) == '@') continue;                                        // Comentário
                if(linha.contains("fita") && fita == null) fita = linha.split(" ")[1];                        // Fita
                else if(linha.contains("init") && estadoInicial == null) estadoInicial = linha.split(" ")[1]; // Estado Inicial
                else if(linha.contains("accept") && estadoFinal == null) estadoFinal = linha.split(" ")[1];   // Estado Final
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
                    listaEstados.get(pos).addTransition(caractereLido.charAt(0), StateProximo, caractereEscrito.charAt(0), direcao);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo! - " + e.getMessage());
        }

        // A partir daqui, a lista de estados já deve estar pronta e toda conectada
        listaEstados.forEach((State st) -> {
            st.transitions.forEach((Transition t) -> {
                System.out.println(st.getName() + " - " + t.getEdge().getC());
            });
        });
    }

//    public static void verify(boolean b, String w, boolean showWord) {
//        System.out.println((b ? "Reconheceu: " : "Não reconheceu: ") + (showWord ? w : ""));
//    }
}