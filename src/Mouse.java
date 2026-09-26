import java.awt.*;

public class Mouse extends Case{

    public Mouse(int x, int y, int size,  int nWidth, int nHeight) {
        super(x,y,size, nWidth, nHeight);
    }
    public Mouse(Coord c, int size, int nWidth, int nHeight){super(c,size, nWidth, nHeight );}

    public void display (Graphics g){
        Color body = new Color(184, 67, 67);
        g.setColor(body);

        Color contour = new Color(87, 35, 35);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }
}
