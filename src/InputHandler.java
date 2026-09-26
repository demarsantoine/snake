import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    Snake snake;
    public InputHandler(Snake s){
        super();
        this.snake = s;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*if (e.getKeyCode() == KeyEvent.VK_LEFT){
            snake.changeDirection('W');
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            snake.changeDirection('E');
        }

        if (e.getKeyCode() == KeyEvent.VK_UP){
            snake.changeDirection('N');
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            snake.changeDirection('S');
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e)    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            snake.changeDirection('W');
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            snake.changeDirection('E');
        }

        if (e.getKeyCode() == KeyEvent.VK_UP){
            snake.changeDirection('N');
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            snake.changeDirection('S');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*if (e.getKeyCode() == KeyEvent.VK_LEFT){
            snake.changeDirection('W');
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            snake.changeDirection('E');
        }

        if (e.getKeyCode() == KeyEvent.VK_UP){
            snake.changeDirection('N');
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            snake.changeDirection('S');
        }*/
    }
}
