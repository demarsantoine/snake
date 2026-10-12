import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {

    public WinPanel(Window w, int currentLevel) {
        super();
        setOpaque(false);
        setLayout(new GridBagLayout());

        String lost = "You lose !!!";

        JPanel contentBox = new JPanel();
        contentBox.setBackground(new Color(0, 0, 0, 200));
        contentBox.setLayout(new BoxLayout(contentBox, BoxLayout.Y_AXIS));
        contentBox.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel(lost);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentBox.add(title);
        contentBox.add(Box.createVerticalStrut(15));

        JButton retry = new JButton("Retry");
        retry.setAlignmentX(Component.CENTER_ALIGNMENT);
        retry.addActionListener(e -> {w.showView(currentLevel);});
        contentBox.add(retry);
        contentBox.add(Box.createVerticalStrut(10));


        JButton exit = new JButton("Menu");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(e -> {w.showView("MENU");});
        contentBox.add(exit);
        //contentBox.add(Box.createVerticalStrut(10));

        this.add(contentBox);
    }

    // Permet de dessiner un fond semi-transparent propre
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 120)); // Noir avec transparence
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
