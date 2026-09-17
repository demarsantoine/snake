import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private Image background;
    private JButton startBtn;

    public MenuPanel(Window w){
        super();
        //background = new ImageIcon("SnakeMenu.jpg").getImage();
        java.net.URL imageUrl = getClass().getResource("/SnakeMenu.jpg");

        if (imageUrl != null) {
            background = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image SnakeMenu.jpg est introuvable au chemin spécifié.");
        }

        this.setPreferredSize(new Dimension(background.getWidth(this),background.getHeight(this)));
        this.setLayout(null);

        startBtn = new JButton("Start");

        startBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        startBtn.setFocusPainted(false);
        Color startBtnColor = new Color(24, 179, 90, 255);
        startBtn.setBackground(startBtnColor);
        Color startColor = new Color(4, 48, 22, 255);
        startBtn.setForeground(startColor);
        startBtn.addActionListener(e -> {
            w.showView("LEVEL 1");
        });

        int x = (int) ((int) background.getWidth(this)*0.72);
        int y = (int) ((int) background.getHeight(this)*0.61);

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
