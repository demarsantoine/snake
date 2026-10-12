import javax.swing.*;
import java.awt.*;

public class Mouse extends MovingCase{

    int count;
    boolean alerted;

    public Mouse(int x, int y, int size,  int nWidth, int nHeight) {
        super(x,y,size, nWidth, nHeight);
        java.net.URL imageUrl = getClass().getResource("/Mouse.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image Mouse.png est introuvable au chemin spécifié.");
        }
        direction = 'E';
        count = 0;
        alerted = false;
    }
    public Mouse(Coord c, int size, int nWidth, int nHeight){
        super(c,size, nWidth, nHeight );
        java.net.URL imageUrl = getClass().getResource("/Mouse.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image Mouse.png est introuvable au chemin spécifié.");
        }
        direction = 'E';
        count = 0;
        alerted = false;
    }

    /*public void display (Graphics g){
        Color body = new Color(184, 67, 67);
        g.setColor(body);

        Color contour = new Color(87, 35, 35);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }*/

    public boolean isInSight (Coord c){
        int mX = this.coord.getX();
        int mY = this.coord.getY();

        int X = c.getX();
        int Y = c.getY();

        boolean inSight = false;

        switch(direction){
            case 'N' :
                for (int x = mX-2; x <= mX+2; x++ ){
                    for (int y = mY-5; y <= mY; y++ ){}
                }
            case 'S':
            case 'E' :
            case 'W' :
        }


        return true;
    }

    public void update (){
        count += 1;
        count %= (alerted)? 2:4;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isAlerted() {
        return alerted;
    }

    public void setAlerted(boolean alerted) {
        this.alerted = alerted;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        int x = coord.getX() * size;
        int y = coord.getY() * size;
        if (direction == 'E' && image != null) {
            int translationX =x+size;
            g2d.translate(translationX,y);
            g2d.scale(-1,1);
            g2d.drawImage(image, 0, 0, size, size, null);
            g2d.dispose();
        }
        else super.paintComponent(g);
    }
}
