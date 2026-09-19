import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Case> snake;
    private char direction;
    private char taleDir;
    private Level lvl;
    boolean ate;

    public Snake(Level lvl){
        this.lvl = lvl;
        this.ate = false;
        snake = new ArrayList<Case>();
        direction = 'W';
        taleDir = 'E';
        int x =lvl.getNWidth()/2;
        int y =lvl.getNHeight()/2;
        snake.add(new SnakeHead(x,y,lvl.getCaseSize()));
        ((SnakeHead)snake.getFirst()).setDirection(direction);
        snake.addLast(new SnakeBody(x+1,y,lvl.getCaseSize()));
        snake.addLast(new SnakeTale(x+2,y,lvl.getCaseSize()));
        ((SnakeTale)snake.getLast()).setDirection(direction);

    }

    public Snake(int x, int y, Level lvl){

        snake = new ArrayList<Case>();
        this.ate = false;
        direction = 'W';
        taleDir = 'E';
        snake.add(new SnakeBody(x,y, lvl.getCaseSize()));
        snake.add(new SnakeBody(x+1,y,lvl.getCaseSize()));
    }

    public Coord headCoords(){
        return snake.getFirst().getCoords();
    };
    public int headX() {return snake.getFirst().getX();}
    public int headY() {return snake.getFirst().getY();}

    public void changeDirection(char c){
        direction = c;
    }

    public void updateTale(char c){
        taleDir = c;
    }

    public void setAte(boolean a){
        ate = a;
    };

    public boolean updatePosition(){
        Case tale = snake.getLast();
        if(ate) {
            snake.removeLast();
            snake.addLast(new SnakeBody(tale.getCoords(),lvl.getCaseSize()));
            snake.addLast(tale);
            ate = false;
        }
        for (int i = snake.size() - 1; i > 0; i--) {
            Coord newC = snake.get(i-1).getCoords();
            snake.get(i).setCoords(newC);
        }
        if (direction == taleDir){direction = Snake.opposite(taleDir);}
        if (direction == 'W'){
            int x = (snake.getFirst().getCoords().getX()<=0)? lvl.getNWidth()-1 : -1;
            snake.getFirst().increaseX(x);
            ((SnakeHead)snake.getFirst()).setDirection(direction);
            taleDir = 'E';
        }
        if (direction == 'E'){
            int x = (snake.getFirst().getCoords().getX()>= lvl.getNWidth()-1)? -(lvl.getNWidth()-1) : 1;
            snake.getFirst().increaseX(x);
            ((SnakeHead)snake.getFirst()).setDirection(direction);
            taleDir = 'W';
        }
        if (direction == 'N'){
            int y = (snake.getFirst().getCoords().getY()<=0)? lvl.getNHeight()-1 : -1;
            snake.getFirst().increaseY(y);
            ((SnakeHead)snake.getFirst()).setDirection(direction);
            taleDir = 'S';
        }
        else if (direction == 'S'){
            int y = (snake.getFirst().getCoords().getY()>= lvl.getNHeight()-1)? -(lvl.getNHeight()-1) : 1;
            snake.getFirst().increaseY(y);
            ((SnakeHead)snake.getFirst()).setDirection(direction);
            taleDir = 'N';
        }
        return !this.selfBitten();
    };

    public static char opposite(char c){
        if (c == 'W') return 'E';
        if (c == 'E') return 'W';
        if (c == 'N') return 'S';
        if (c == 'S') return 'N';
        else return 'W';
    }

    public boolean isCoordFree(Coord c){
        boolean free = true;
        for (int i = snake.size() - 1; i > 0; i--) {
            free = c.equals(snake.get(i).getCoords());
        }
        return free;
    }

    public boolean selfBitten(){
        boolean bite = false;
        Coord headCoord = snake.getFirst().getCoords();
        for (int i = 3; i < snake.size() ; i++) {
            if (headCoord.equals(snake.get(i).getCoords())) bite = true;
        }
        return bite;
    }


    public void display (Graphics g){
        for (Case aCase : snake) {
            aCase.display(g);
        }


    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Snake (" + snake.size() + ") : ");
        for (Case aCase : snake) {
            s.append(aCase.getCoords().toString());
        }
        return s.toString();
    }
}
