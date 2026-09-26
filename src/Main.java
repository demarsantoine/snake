import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int width = 1280;
    private static int height = 720;
    public static void main(String[] args) {
        //Créer la fenêtre
        Window w = new Window(width, height);

        //Créer les niveaux
        List<Level> levels = new ArrayList<>();
        levels.add(new Lvl1(w));
        levels.add(new Lvl2(w));

        //Créer le menu
        MenuPanel menuPanel = new MenuPanel(w, levels);

        //Ajouter les niveaux et le menu au cardpanel
        w.addToCardLayout(menuPanel,"MENU");
        for (int i= 0; i< levels.size(); i++) {
            w.addToCardLayout(levels.get(i),"LEVEL "+(i+1));
        }

        //Lancer le menu
        w.showView("MENU");
        w.setVisible(true);

    }

}
