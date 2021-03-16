import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI extends Canvas{

    private Cell[] fita;
    private JFrame frame;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private int teste = 0, index = 6;

    public GUI(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame = new JFrame("Turing Machine");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.setResizable(false);

        fita = new Cell[21];
        for(int i=0; i<fita.length; i++){
            fita[i] = new Cell(i*50);
        }
    }

    public Cell[] getFita() { return fita; }

    public int getIndex() { return index; }

    public void initFita(String w){
        int aux = index;
        char[] letras = w.toCharArray();
        for(char c:letras){
            fita[aux].setC(c);
            aux++;
        }
    }
    public void write(Character c){
        fita[index].setC(c);
    }

    public void bombineRight(){
        for(Cell f:fita){
            f.setX(f.getX()-f.getWidth());
        }
        index++;
    }

    public void bombineLeft(){
        for(Cell f:fita){
            f.setX(f.getX()+f.getWidth());
        }
        index--;
    }

    public void successAlert(String w){
        JOptionPane.showMessageDialog(frame, "Reconheceu "+w, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failAlert(String w){
        JOptionPane.showMessageDialog(frame, "Não reconheceu "+w, "Fail", JOptionPane.ERROR_MESSAGE);
    }

    public void render(Thread t){
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


        try{
            BufferedImage image = ImageIO.read(getClass().getResource("turing.jpg"));
            g.drawImage(image, fita[index].getX(), fita[index].getY()-(int)(50*((float)image.getHeight()/(float)image.getWidth())), 50, (int)(50*((float)image.getHeight()/(float)image.getWidth())), null);
        }catch (IOException e) {
            try {
                t.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            e.printStackTrace();
        }

        g.dispose();

        bs.show();
    }
}