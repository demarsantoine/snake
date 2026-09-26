import javax.swing.*;
import java.awt.*;

public class Lvl2 extends Level{

    int mouseCounter;

    public Lvl2(Window w){
        super(w);
        number= 2;
        nWidth = 50;
        nHeight = 50;
        caseSize = 10;
        speed = 100;
        mouseCounter = 0;

        this.setSize();
        Color BGColor = new Color(183, 227, 142);
        this.setBackground(BGColor);

        this.setLayout(null);
        scoreLabel = new JLabel("Score: "+ score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(400,0, 100, 40);
        this.add(scoreLabel);

        snake = new Snake(this);
        //mouse = newMouse();
        this.addMouses(3);
        //this.inputHandler = new InputHandler(snake);
        //this.addKeyListener(this.inputHandler);

        this.requestFocusInWindow();

        timer = new Timer(speed, e -> {this.update();});


        timer.start();
    }

    @Override
    protected void updateMouse(){
        Coord mouseCoord = new Coord(mouse.getX(), mouse.getY());
        mouseCounter++;
        mouseCounter %= 3;
        if(mouseCounter == 0){
            int move = (((int)(Math.random()*2))==1)? 1 : -1 ;
            int axis = (((int)(Math.random()*2))==1)? 1 : -1 ;
            if (axis == 1){
                mouse.increaseX(move%nWidth);
            }
            else mouse.increaseY(move%nHeight);

        }

    }

    @Override
    protected void updateMouses(){
        mouseCounter++;
        mouseCounter %= 3;
        if(mouseCounter == 0) {
            for (Mouse m : this.mouses) {
                Coord mouseCoord = new Coord(m.getX(), m.getY());
                int move = (((int) (Math.random() * 2)) == 1) ? 1 : -1;
                int axis = (((int) (Math.random() * 2)) == 1) ? 1 : -1;
                if (axis == 1) {
                    m.increaseX(move % nWidth);
                } else m.increaseY(move % nHeight);
            }
        }
    }

    private void updateSpeed(){
        if (speed>5) speed --;
    }


}
