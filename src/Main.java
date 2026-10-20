import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int width = 1280;
    private static int height = 720;
    private static int nLevel = 5;
    public static void main(String[] args) {
        //Créer la fenêtre
        Window w = new Window(width, height);

        //Créer les niveaux
        List<Level> levels = new ArrayList<>();
        levels.add(new Lvl1(w));
        levels.add(new Lvl2(w));
        //-------------------------------------
        List<LevelItem> levelsList = new ArrayList<>();
        for (int i = 0; i < nLevel; i++) {
           levelsList.add(new LevelItem(i));
        }

        //Créer le menu
        MenuPanel menuPanel = new MenuPanel(w, levelsList);

        //Ajouter les niveaux et le menu au cardpanel
        w.addToCardLayout(menuPanel,"MENU");
        for (int i= 0; i< levels.size(); i++) {
            w.addToCardLayout(levels.get(i),"LEVEL "+(i+1));
        }

        //Lancer le menu
        w.showView("MENU");
        w.setVisible(true);

    }

    private static void testDist(){
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random()*9);
            int y = (int) (Math.random()*9);
            Coord s = new Coord(x,y);

            x = (int) (Math.random()*9);
            y = (int) (Math.random()*9);
            Coord e = new Coord(x,y);

            System.out.println(s + " ---> " + e + "  dist = " + TestAStar.manhattanDistance(s,e,10,10));
        }
    }

}
