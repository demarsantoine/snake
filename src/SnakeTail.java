import javax.swing.*;

public class SnakeTail extends MovingCase {

    public SnakeTail(){
        super(0,0, 0, 0, 0);

    }

    public SnakeTail(int x, int y, int size, int nWidth, int nHeight){
        super(x,y, size, nWidth, nHeight);

        java.net.URL imageUrl = getClass().getResource("/SnakeTail.png");

        if (imageUrl != null) {
            image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image SnakeTail.png est introuvable au chemin spécifié.");
        }
        direction = 'W';
    }

    public SnakeTail(Coord c, int size, int nWidth, int nHeight){super(c,size, nWidth, nHeight);}


    @Override
    public void setCoords(Coord c){
        int sumX = this.coord.getX()-c.getX() ;
        int sumY = this.coord.getY()-c.getY() ;

        if(sumY == 0){
            direction = (sumX == 1)? 'W' : 'E';
        }

        if(sumX == 0){
            direction = (sumY == 1)? 'N' : 'S';
        }


        coord.setX(c.getX());
        coord.setY(c.getY());
    }
}
