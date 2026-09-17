import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Case> snake;
    private char direction;
    private char tale;
    private Level lvl;

    public Snake(Level lvl){
        this.lvl = lvl;
        snake = new ArrayList<Case>();
        direction = 'W';
        tale = 'E';
        snake.add(new SnakeBody(lvl.getNWidth()/2,lvl.getNHeight()/2,lvl.getCaseSize()));

    }

    public Snake(int x, int y, Level lvl){

        snake = new ArrayList<Case>();
        direction = 'W';
        tale = 'E';
        snake.add(new SnakeBody(x,y, lvl.getCaseSize()));
    }

    public void changeDirection(char c){
        direction = c;
    }

    public void updateTale(char c){
        tale = c;
    }

    public void grow(){

    };

    public void display (Graphics g){
        for (Case aCase : snake) {
            aCase.display(g);
        }


    }
}
