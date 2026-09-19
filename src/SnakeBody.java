import java.awt.*;

public class SnakeBody extends Case {



    public SnakeBody(){
        super(0,0, 0);

    }

    public SnakeBody(int x, int y, int size){
        super(x,y, size);
    }

    public SnakeBody(Coord c, int size){super(c,size);}

    public void display (Graphics g){
        Color body = new Color(38, 168, 10);
        g.setColor(body);

        Color contour = new Color(42, 60, 8);
        g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
        g.setColor(contour);
        g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
    }



}
