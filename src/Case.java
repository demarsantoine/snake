import javax.swing.*;
import java.awt.*;

public class Case extends JPanel {
    protected int size;
    Coord coord;
    private static int nWidth;
    private static int nHeight;

    public Case(int x, int y, int size, int nWidth, int nHeight) {
        this.size = size;
        coord = new Coord(x,y);
    }

    public Case(Coord c, int size, int nWidth, int nHeight) {
        this.size = size;
        this.nWidth = nWidth;
        this.nHeight = nHeight;
        this.coord = new Coord(c.x,c.y);
    }

    public void setCoords(int x, int y){
        coord.setX(x);
        coord.setY(y);
    }

    public void setCoords(Coord c){
        coord.setX(c.getX());
        coord.setY(c.getY());
    }

    public void increaseX (int x){
        x += nWidth;
        if (nWidth > 0) {coord.setX((coord.getX()+x)%nWidth);};
    }

    public void increaseY (int y){ if (nHeight> 0){
        y += nHeight;
        coord.setY((coord.getY()+y)%nHeight);}
    }

    public Coord getCoords(){
        return new Coord(coord.getX(),coord.getY());
    }

    public void display (Graphics g){}
    public void update (){}

}
