import java.awt.*;
import java.util.ArrayList;

public class Lvl3 extends Lvl2{
    ArrayList<Wall> walls;
    public Lvl3(Window w){
        walls = new ArrayList<>();
        super(w);
        this.number= 3;
        this.setWalls();
    }

    protected void setWalls(){
        for (int i = 0; i<nHeight ; i++){
            walls.add(new Wall((int)nWidth/2, i, caseSize, nWidth, nHeight));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Wall w : walls){
        w.display(g);
        }
    }

    @Override
    protected boolean isCoordFree(Coord c){
        boolean free = super.isCoordFree(c);
        if (free){
            for (Wall w : walls){
                if (w.getCoords().equals(c)){free = false;}
            }
        }
        return free;
    }

    @Override
    protected boolean loseCondition(){
        return super.loseCondition() || hitWall();
    }

    protected boolean hitWall(){
        Coord hc = snake.headCoords();
        for (Wall w : walls) {
            if (w.getCoords().equals(hc)) {
                return true;
            }
        }
        return false;
    }
}
