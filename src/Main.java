import javax.swing.*;
import java.awt.*;

public class Main {
    private static int width = 1280;
    private static int height = 720;
    public static void main(String[] args) {
        Window w = new Window(width, height);

        MenuPanel menuPanel = new MenuPanel(w);
        Level lvl1 = new Lvl1(w);

        w.addToCardLayout(menuPanel,"MENU");
        w.addToCardLayout(lvl1, "LEVEL 1");
        w.showView("MENU");
        w.setVisible(true);

    }

}
