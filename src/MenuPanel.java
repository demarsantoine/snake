import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuPanel extends JPanel {

    private Image background;
    private JButton startBtn;
    private JComboBox<Level> levelComboBox;

    public MenuPanel(Window w, List<Level> levels){
        super();

        java.net.URL imageUrl = getClass().getResource("/SnakeMenu.jpg");

        if (imageUrl != null) {
            background = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image SnakeMenu.jpg est introuvable au chemin spécifié.");
        }

        this.setPreferredSize(new Dimension(background.getWidth(this),background.getHeight(this)));
        this.setLayout(null);

        //configuration du choix niveau (ComboBox = menu déroulant)
        levelComboBox = new JComboBox<>(levels.toArray(new Level[0]));
        levelComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        int x = (int) (background.getWidth(this) * 0.72);
        int y = (int) (background.getHeight(this) * 0.61);
        levelComboBox.setBounds(x, y - 70, 200, 50);

        //Créer une renderer pour centrer le texte
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        levelComboBox.setRenderer(renderer);

        this.add(levelComboBox);

        //configuration du bouton start
        startBtn = new JButton("Start");

        startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        startBtn.setFocusPainted(false);
        Color startBtnColor = new Color(24, 179, 90, 255);
        startBtn.setBackground(startBtnColor);
        Color startColor = new Color(4, 48, 22, 255);
        startBtn.setForeground(startColor);
        startBtn.addActionListener(e -> {
            // Récupérer le niveau sélectionné par l'utilisateur
            Level selectedLevel = (Level) levelComboBox.getSelectedItem();

            if (selectedLevel != null) {
                System.out.println("Niveau choisi : " + selectedLevel);
                w.showView(selectedLevel.toString());
            }
            else w.showView(1);
            //levels.getFirst().requestFocusInWindow();
        });


        startBtn.setBounds(x, y, 200, 80);
        this.add(startBtn);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        else System.out.println("background is null");
    }

}
