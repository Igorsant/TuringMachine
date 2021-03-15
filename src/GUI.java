import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GUI extends Canvas{

    private Cell[] fita;
    private JFrame frame;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    public GUI(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame = new JFrame("Turing Machine");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        fita = new Cell[21];

        for(int i=0; i < fita.length; i++){
            fita[i] = new Cell(i*50);
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.green);
        g.fillRect(0,0, WIDTH, HEIGHT);

        for (Cell c:fita) {
            c.render(g);
        }

        g.dispose();


        bs.show();
    }
}