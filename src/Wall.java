import javax.swing.*;
import java.awt.*;

public class Wall extends Case{
    Image image;
    public Wall (int x, int y, int size, int nWidth, int nHeight){
        super(x,y,size,nWidth,nHeight);
        java.net.URL imageUrl = getClass().getResource("/Roc.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image Mouse.png est introuvable au chemin spécifié.");
        }

    }
    public Wall(Coord c, int size, int nWidth, int nHeight){
        super(c.x,c.y,size,nWidth,nHeight);
    }

    /*@Override
    public void display (Graphics g){
        Color body = new Color(62, 55, 55);
        g.setColor(body);

        Color contour = new Color(5, 5, 5);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }*/

    @Override
    public void display (Graphics g){
        if (image != null) {
            int x = coord.getX() * size;
            int y = coord.getY() * size;
            g.drawImage(image,x,y,size,size,this);
        }
    }
}
