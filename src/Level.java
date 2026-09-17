import javax.swing.*;
import java.awt.*;

public abstract class Level extends JPanel {
    //protected Grid grid;
    protected Snake snake;
    protected Image background;
    protected JLabel scorePanel;
    protected JPanel mainContainer;

    int nWidth;
    int nHeight;
    int caseSize;
    int score;
    int speed;

    public Level(Window w) {
        super();
        mainContainer = new JPanel();


    }

    protected void setSize(){
        Dimension d = new Dimension(nWidth*caseSize,nHeight*caseSize);
        this.setPreferredSize(d);
    };

    public int getCaseSize(){return caseSize;}
    public int getNWidth(){return nWidth;}
    public int getNHeight(){return nHeight;}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.display(g);

        if (background != null) {
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        else System.out.println("background is null");
    }

    protected Dimension coordonnes(int x, int y){
        return new Dimension(x*caseSize,y*caseSize);
    }
}
