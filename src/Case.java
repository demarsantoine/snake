import javax.swing.*;
import java.awt.*;

public class Case extends JPanel {
    protected int size;
    protected int x;
    protected int y;

    public Case(int x, int y, int size) {
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public Case(Dimension d, int size){
        this.size = size;
        this.x = d.width;
        this.y = d.height;
    }

    public void updateCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void display (Graphics g){}
    public void update (){}

}
