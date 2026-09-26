import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Level extends JPanel {
    //protected Grid grid;
    int number;
    int name;
    protected Snake snake;
    protected Mouse mouse;
    protected LinkedList<Mouse> mouses;
    protected Image background;
    protected JLabel scorePanel;
    protected JPanel mainContainer;
    //protected InputHandler inputHandler;
    protected JLabel scoreLabel;
    protected Timer timer;

    int nWidth;
    int nHeight;
    int caseSize;
    int score;
    int speed;

    public Level(Window w) {
        super();
        this.setFocusable(true);
        mainContainer = new JPanel();
        mouses = new LinkedList<>();

        //Récupérer l'inputmap et actionmap du panneau
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        //associer les touches aux Strings identifiants d'actions
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        //associer les identifiants aux actions concrètes
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('N');
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('S');
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('W');
            }
        });
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('E');
            }
        });


    }

    protected void setSize(){
        Dimension d = new Dimension(nWidth*caseSize,nHeight*caseSize);
        this.setPreferredSize(d);
    };

    public int getCaseSize(){return caseSize;}
    public int getNWidth(){return nWidth;}
    public int getNHeight(){return nHeight;}
    protected void checkMouses(){
        Mouse eatenMouse = null;
        //Boolean eaten = false;
        for (Mouse m : mouses) {
            if (snake.headCoords().equals(m.getCoords())) {
                System.out.println("Miam !");
                snake.setAte(true);
                //iterator.remove();
                eatenMouse = m;
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
        if (eatenMouse!=null){
            mouses.remove(eatenMouse);
            mouses.add(this.newMouse());
        }


        /*for (Mouse m : mouses){
            if (snake.headCoords().equals(m.getCoords())){
                System.out.println("Miam");
                snake.setAte(true);
                mouses.remove(m);
                mouses.add(this.newMouse());
                score++;
                scoreLabel.setText("Score: "+ score);
            }
        }*/

    }
    protected void checkMouse(){
        if (snake.headCoords().equals(mouse.getCoords())) {
            System.out.println("Miam");
            snake.setAte(true);
            //updateSpeed();
            mouseJump();
            score++;
            scoreLabel.setText("Score: "+ score);
        }
    }
    protected void mouseJump(){
        Coord c = new Coord(0,0);
        do {
            int x = (int) (Math.random() * nWidth);
            int y = (int) (Math.random() * nHeight);
            c.setX(x);
            c.setY(y);
        } while (this.isCoordFree(c));
        mouse.setCoords(c);
    }
    protected void update(){
        if (snake.updatePosition()) {
            this.checkMouses();
            this.updateMouses();
            this.repaint();
            if(this.winCondition())this.won();
        }
        else this.lost();
    }
    protected void addMouses(int n){
        for (int i = 0; i<n;i++){
            mouses.add(this.newMouse());
        }
    }
    protected Mouse newMouse (){
        Coord c = new Coord(0,0);
        do {
            int x = (int) (Math.random() * nWidth);
            int y = (int) (Math.random() * nHeight);
            c.setX(x);
            c.setY(y);
        } while (isCoordFree(c));
        return new Mouse(c,caseSize, nWidth,nHeight);
    }
    protected void won(){
        System.out.println("You win !!");
        timer.stop();
    }
    protected void lost(){
        System.out.println("You Lose !!");
        timer.stop();
    }
    protected boolean winCondition(){
        return score >=10;
    }
    protected boolean isCoordFree(Coord c){
        boolean free = snake.isCoordFree(c);
        for (Mouse m : mouses){
            if (m.getCoords().equals(c)) free = false;
        }
        return free;
    }

    protected void updateMouse(){};
    protected void updateMouses(){}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Mouse m : mouses){
            m.display(g);
        }
        //mouse.display(g);
        snake.display(g);


        if (background != null) {
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        //else System.out.println("background is null");
    }

    @Override
    public String toString() {
        return "LEVEL "+number;
    }

    protected Dimension coordonnes(int x, int y){
        return new Dimension(x*caseSize,y*caseSize);
    }
}
