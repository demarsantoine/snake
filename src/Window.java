import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel mainContainer;
    private static Level currentLevel;

    public Window(int width, int height) {
        super("Snake Aventure");
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(mainContainer);

    }
    public void showView(String viewName) {
        //Afficher le niveau
        cardLayout.show(mainContainer, viewName);

        //Trouver le composant qui est afficher
        for (Component comp : mainContainer.getComponents()) {
            if (comp.isVisible()){
                mainContainer.setPreferredSize(comp.getPreferredSize());
                this.pack();
                this.setLocationRelativeTo(null);
                //Lui donner le focus pour les touches (plus nécessaire)
                javax.swing.SwingUtilities.invokeLater(comp::requestFocusInWindow);
                break;
            }
        }
    }

    public void showView(int level) {

        if (currentLevel != null) {
            currentLevel.destroy();
            mainContainer.remove(currentLevel);
        }
        switch (level) {
            case 1 :
                currentLevel = new Lvl1(this);
                break;
            case 2 :
                currentLevel = new Lvl2(this);
                break;
            case 3 :
                currentLevel = new Lvl3(this);
                break;
            default : currentLevel = null;
        }
        mainContainer.add(currentLevel, "Level "+level);
        showView("Level " + level);
    }
    public void addToCardLayout(JPanel panel, String viewName) {
        this.setSize(panel.getPreferredSize());
        mainContainer.add(panel,viewName);
    }
}
