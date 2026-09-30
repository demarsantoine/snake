import javax.swing.*;
import java.awt.*;

public class Lvl1 extends Level{

    public Lvl1(Window w){
        super(w);
        number = 1;
        nWidth = 20;
        nHeight = 20;
        caseSize = 50;
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
        this.addMouses(1);
        //mouse = newMouse();
        //this.inputHandler = new InputHandler(snake);
        //this.addKeyListener(this.inputHandler);

        this.requestFocusInWindow();

        timer = new Timer(speed, e -> {this.update();});


        timer.start();
    }

    private void updateSpeed(){
        if (speed>5) speed --;
    }


}
