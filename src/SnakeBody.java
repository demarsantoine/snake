import javax.swing.*;

public class SnakeBody extends MovingCase {

    public SnakeBody(){
        super(0,0, 0, 0, 0);

    }

    public SnakeBody(int x, int y, int size, int nWidth, int nHeight){
        super(x,y, size, nWidth, nHeight);

        java.net.URL imageUrl = getClass().getResource("/SnakeBody.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image SnakeBody.png est introuvable au chemin spécifié.");
        }
        direction = 'W';
    }

    public SnakeBody(Coord c, int size, int nWidth, int nHeight){
        super(c,size, nWidth, nHeight);

        java.net.URL imageUrl = getClass().getResource("/SnakeBody.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image SnakeBody.png est introuvable au chemin spécifié.");
        }
        direction = 'W';
    }

    /*public void display (Graphics g){
        Color body = new Color(187, 70, 248);
        g.setColor(body);

        Color contour = new Color(39, 8, 60);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }*/




}
