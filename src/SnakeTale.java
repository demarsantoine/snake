import java.awt.*;

public class SnakeTale extends Case {



    char direction;
    public SnakeTale(){
        super(0,0, 0);

    }

    public SnakeTale(int x, int y, int size){
        super(x,y, size);
        direction = 'W';
    }

    public SnakeTale(Coord c, int size){super(c,size);}

    public void setDirection(char c){this.direction = c;};

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

    public void display (Graphics g){
        Color body = new Color(38, 168, 10);
        Color contour = new Color(42, 60, 8);
        int x1 = 0,x2= 0, x3= 0, y1= 0, y2= 0, y3 = 0;
        if (direction == 'E'){
            x1 = coord.x*size + size;
            y1 = coord.y*size;
            x2 = coord.x*size;
            y2 = coord.y*size + size/2;
            x3 = coord.x*size + size;
            y3 = coord.y*size + size;
        }

        if (direction == 'W'){
            x1 = coord.x*size;
            y1 = coord.y*size;
            x2 = coord.x*size + size;
            y2 = coord.y*size + size/2;
            x3 = coord.x*size;
            y3 = coord.y*size + size;
        }

        if (direction == 'S'){
            x1 = coord.x*size;
            y1 = coord.y*size + size;
            x2 = coord.x*size + size/2;
            y2 = coord.y*size;
            x3 = coord.x*size + size;
            y3 = coord.y*size + size;
        }

        if (direction == 'N'){
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
