import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel mainContainer;

    public Window(int width, int height) {
        super("Snake Aventure");
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(mainContainer);

    }
    public void showView(String viewName) {
        cardLayout.show(mainContainer, viewName);

        for (Component comp : mainContainer.getComponents()) {
            if (comp.isVisible()){
                mainContainer.setPreferredSize(comp.getPreferredSize());
                this.pack();
                this.setLocationRelativeTo(null);
                break;
            }
        }
    }
    public void addToCardLayout(JPanel panel, String viewName) {
        this.setSize(panel.getPreferredSize());
        mainContainer.add(panel,viewName);
    }
}
