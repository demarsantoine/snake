import java.awt.*;
import java.util.ArrayList;

public class Lvl4 extends Lvl3{

    public Lvl4(Window w){
        super(w);
        this.number= 4;
        walls = new ArrayList<Wall>();
        this.setWalls();
        this.addMouses(1);
    }

    @Override
    protected void setWalls(){
        for (int i = 0; i<nWidth ; i++){
            walls.add(new Wall(i,0, caseSize, nWidth, nHeight));
            walls.add(new Wall(i,nHeight-1, caseSize, nWidth, nHeight));
        }
        for (int i = 1; i<nWidth-1 ; i++){
            walls.add(new Wall(0,i, caseSize, nWidth, nHeight));
            walls.add(new Wall(nWidth-1,i, caseSize, nWidth, nHeight));
        }
    }


    @Override
    protected void updateMouse(Mouse m){
        Coord headCoord = snake.headCoords();
        int sx = headCoord.getX();
        int sy = headCoord.getY();
        Coord mouseCoord = m.getCoords();
        int mx = mouseCoord.getX();
        int my = mouseCoord.getY();
        if (m.isInSight(headCoord)) {
            char dir = 'O';
            if (mx - my >= sx - sy && mx + my < sx + sy) {
                dir = 'N';
                mouseCoord = new Coord(mx, my - 1);
            } else if (mx - my <= sx - sy && mx + my < sx + sy) {
                dir = 'W';
                mouseCoord = new Coord(mx - 1, my);
            } else if (mx - my > sx - sy && mx + my > sx + sy) {
                dir = 'E';
                mouseCoord = new Coord(mx + 1, my);
            } else if (mx - my < sx - sy && mx + my > sx + sy) {
                dir = 'S';
                mouseCoord = new Coord(mx, my + 1);
            }
            m.setDirection(dir);
            //System.out.println("new mouseCoord :" + mouseCoord);
            if (this.isCoordFree(mouseCoord)) {
                m.setCoords(mouseCoord);
            }
        }
    }

    @Override
    protected boolean isCoordFree(Coord c){
        boolean free = super.isCoordFree(c);
        if (free){
            if (c.getX() == 0 || c.getX() == nWidth-1){ free = false;}
            if (c.getY() == 0 || c.getY() == nHeight-1){ free = false;}
        }
        return free;
    }

    @Override
    protected boolean hitWall(){
        Coord hc = snake.headCoords();
        if (hc.getX() == 0 || hc.getX() == nWidth-1){ return true;}
        else return hc.getY() == 0 || hc.getY() == nHeight - 1;
    }

}
