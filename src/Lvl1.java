import javax.swing.*;
import java.awt.*;

public class Lvl1 extends Level{

    public Lvl1(Window w){
        super(w);
        nWidth = 50;
        nHeight = 50;
        caseSize = 10;
        speed = 200;

        this.setSize();
        Color BGColor = new Color(183, 227, 142);
        this.setBackground(BGColor);

        this.setLayout(null);
        scoreLabel = new JLabel("Score: "+ score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(400,0, 100, 40);
        this.add(scoreLabel);

        snake = new Snake(this);
        mouse = newMouse();
        this.inputHandler = new InputHandler(snake);
        this.addKeyListener(this.inputHandler);

        this.requestFocusInWindow();

        timer = new Timer(speed, e -> {
            //System.out.println(snake.toString());
            //System.out.println("Score: "+ score);

            if (snake.updatePosition()) {
                checkMouse();
                this.repaint();
            }
            else this.lost();
        });


        timer.start();
    }

    private Mouse newMouse (){
        Coord c = new Coord(0,0);
        do {
            int x = (int) (Math.random() * nWidth);
            int y = (int) (Math.random() * nHeight);
            c.setX(x);
            c.setY(y);
        } while (snake.isCoordFree(c));
        return new Mouse(c,caseSize);
    }

    private void mouseJump(){
        Coord c = new Coord(0,0);
        do {
            int x = (int) (Math.random() * nWidth);
            int y = (int) (Math.random() * nHeight);
            c.setX(x);
            c.setY(y);
        } while (snake.isCoordFree(c));
        mouse.setCoords(c);
    }

    private void checkMouse(){
        if (snake.headCoords().equals(mouse.getCoords())) {
            System.out.println("Miam");
            snake.setAte(true);
            updateSpeed();
            mouseJump();
            score++;
            scoreLabel.setText("Score: "+ score);
        }
    }

    private void updateSpeed(){
        if (speed>5) speed -=5;
    }

    private void lost(){
        System.out.println("Lost");
        timer.stop();
    }
}
