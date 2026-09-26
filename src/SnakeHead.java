import java.awt.*;

public class SnakeHead extends Case{

    char direction;
    public SnakeHead(){
        super(0,0, 0, 0, 0);

    }

    public SnakeHead(int x, int y, int size, int nWidth, int nHeight){
        super(x,y, size,  nWidth, nHeight);
        direction = 'W';
    }

    public SnakeHead(Coord c, int size, int nWidth, int nHeight){super(c,size, nWidth, nHeight);}

    public void setDirection(char c){this.direction = c;};

    public void display (Graphics g){
        Color body = new Color(187, 70, 248);
        Color contour = new Color(50, 21, 67);
        int x1 = 0,x2= 0, x3= 0, y1= 0, y2= 0, y3 = 0;
        if (direction == 'W'){
            x1 = coord.x*size + size;
            y1 = coord.y*size;
            x2 = coord.x*size;
            y2 = coord.y*size + size/2;
            x3 = coord.x*size + size;
            y3 = coord.y*size + size;
        }

        if (direction == 'E'){
            x1 = coord.x*size;
            y1 = coord.y*size;
            x2 = coord.x*size + size;
            y2 = coord.y*size + size/2;
            x3 = coord.x*size;
            y3 = coord.y*size + size;
        }

        if (direction == 'N'){
            x1 = coord.x*size;
            y1 = coord.y*size + size;
            x2 = coord.x*size + size/2;
            y2 = coord.y*size;
            x3 = coord.x*size + size;
            y3 = coord.y*size + size;
        }

        if (direction == 'S'){
            x1 = coord.x*size;
            y1 = coord.y*size;
            x2 = coord.x*size + size/2;
            y2 = coord.y*size + size;
            x3 = coord.x*size + size;
            y3 = coord.y*size;
        }

        g.setColor(body);
        g.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
        g.setColor(contour);
        g.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);

    }
}
