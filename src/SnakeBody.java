import java.awt.*;

public class SnakeBody extends Case {



    public SnakeBody(){
        super(0,0, 0, 0, 0);

    }

    public SnakeBody(int x, int y, int size, int nWidth, int nHeight){
        super(x,y, size, nWidth, nHeight);
    }

    public SnakeBody(Coord c, int size, int nWidth, int nHeight){super(c,size, nWidth, nHeight);}

    public void display (Graphics g){
        Color body = new Color(187, 70, 248);
        g.setColor(body);

        Color contour = new Color(39, 8, 60);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }



}
