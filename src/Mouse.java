import javax.swing.*;
import java.awt.*;

public class Mouse extends MovingCase{

    public Mouse(int x, int y, int size,  int nWidth, int nHeight) {
        super(x,y,size, nWidth, nHeight);
        java.net.URL imageUrl = getClass().getResource("/Mouse.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image Mouse.png est introuvable au chemin spécifié.");
        }
        direction = 'E';
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
    }

    /*public void display (Graphics g){
        Color body = new Color(184, 67, 67);
        g.setColor(body);

        Color contour = new Color(87, 35, 35);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }*/

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
