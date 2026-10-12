import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Lvl2 extends Level{

    int mouseCounter;

    public Lvl2(Window w){
        super(w);
        this.number= 2;
        nWidth = 30;
        nHeight = 30;
        caseSize = 30;
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
    protected void updateMouse(Mouse m){
        Coord mouseCoord = m.getCoords();
        //System.out.println("mouseCoord :" +mouseCoord);
        int move = (((int) (Math.random() * 2)) == 1) ? 1 : -1;
        char dir = 'W';
        if (((int) (Math.random() * 2)) == 1) {
            mouseCoord.setX((mouseCoord.getX()+move+nWidth)%nWidth);
            if (move == 1 ) dir ='E';
        }
        else {
            mouseCoord.setY((mouseCoord.getY() + move + nHeight) % nHeight);
            if (move == 1 ) dir = 'S';
            else dir = 'N';
        }
        m.setDirection(dir);
        //System.out.println("new mouseCoord :" + mouseCoord);
        if (this.isCoordFree(mouseCoord)){
            m.setCoords(mouseCoord);

        }

    }

    @Override
    protected void updateMouses(){
        mouseCounter++;
        mouseCounter %= 2;
        if(mouseCounter == 0) {
            for (Mouse m : this.mouses) {
                this.updateMouse(m);
            }
        }
    }

    private void updateSpeed(){
        if (speed>5) speed --;
    }


}
