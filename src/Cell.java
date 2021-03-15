import java.awt.*;

public class Cell {
    private int x;
    private int y;
    private int width;
    private int height;
    private Character c;

    public Cell(int x){
        this.x = x;
        this.y = 200;
        this.width = 50;
        this.height = 50;
        this.c = ' ';
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(x,y,width,height);
        g.setColor(Color.gray);
        g.fillRect(x,y,1,height);
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.BOLD, 10));
        g.drawString(Character.toString(c), x+(int)(width/2), y+(int)(height/2)+5);
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
