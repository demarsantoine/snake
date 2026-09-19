import javax.swing.*;
import java.awt.*;

public class Case extends JPanel {
    protected int size;
    Coord coord;

    public Case(int x, int y, int size) {
        this.size = size;
        coord = new Coord(x,y);
    }

    public Case(Coord c, int size){
        this.size = size;
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
        coord.setX(coord.getX()+x);
    }

    public void increaseY (int y){coord.setY(coord.getY()+y);}

    public Coord getCoords(){
        return new Coord(coord.getX(),coord.getY());
    }

    public void display (Graphics g){}
    public void update (){}

}
