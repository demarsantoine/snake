import java.awt.*;

public class Mouse extends Case{

    public Mouse(int x, int y, int size){
        super(x,y,size);
    }
    public Mouse(Coord c, int size){super(c,size);}

    public void display (Graphics g){
        Color body = new Color(184, 67, 67);
        g.setColor(body);

        Color contour = new Color(87, 35, 35);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }
}
