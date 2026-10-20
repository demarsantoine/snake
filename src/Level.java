import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public abstract class Level extends JPanel {
    //protected Grid grid;
    int number;
    int name;
    protected Snake snake;
    protected Mouse mouse;
    protected LinkedList<Mouse> mouses;
    protected Image background;
    protected JLabel scorePanel;
    protected JPanel mainContainer;
    protected Window w;
    //protected InputHandler inputHandler;
    protected JLabel scoreLabel;
    protected Timer timer;
    protected Image backgroundImage;

    int nWidth;
    int nHeight;
    int caseSize;
    int score;
    int speed;

    public Level(Window w) {
        super();
        this.setFocusable(true);
        this.w = w;
        mainContainer = new JPanel();
        mouses = new LinkedList<>();
        java.net.URL imageUrl = getClass().getResource("/Grass.jpg");

        if (imageUrl != null) {
            backgroundImage = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Erreur : L'image Grass.jpg est introuvable au chemin spécifié.");
        }

        //Récupérer l'inputmap et actionmap du panneau
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        //associer les touches aux Strings identifiants d'actions
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        //associer les identifiants aux actions concrètes
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('N');
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('S');
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('W');
            }
        });
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                snake.changeDirection('E');
            }
        });


    }

    protected void setSize(){
        Dimension d = new Dimension(nWidth*caseSize,nHeight*caseSize);
        this.setPreferredSize(d);
    };

    public int getCaseSize(){return caseSize;}
    public int getNWidth(){return nWidth;}
    public int getNHeight(){return nHeight;}
    protected void checkMouses(){
        Mouse eatenMouse = null;
        //Boolean eaten = false;
        for (Mouse m : mouses) {
            if (snake.headCoords().equals(m.getCoords())) {
                System.out.println("Miam !");
                snake.setAte(true);
                eatenMouse = m;
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
        if (eatenMouse!=null){
            mouses.remove(eatenMouse);
            mouses.add(this.newMouse());
        }


        /*for (Mouse m : mouses){
            if (snake.headCoords().equals(m.getCoords())){
                System.out.println("Miam");
                snake.setAte(true);
                mouses.remove(m);
                mouses.add(this.newMouse());
                score++;
                scoreLabel.setText("Score: "+ score);
            }
        }*/

    }
    protected void checkMouse(){
        if (snake.headCoords().equals(mouse.getCoords())) {
            System.out.println("Miam");
            snake.setAte(true);
            //updateSpeed();
            mouseJump();
            score++;
            scoreLabel.setText("Score: "+ score);
        }
    }
    protected void mouseJump(){
        Coord c = new Coord(0,0);
        do {
            int x = (int) (Math.random() * nWidth);
            int y = (int) (Math.random() * nHeight);
            c.setX(x);
            c.setY(y);
        } while (this.isCoordFree(c));
        mouse.setCoords(c);
    }
    protected void update(){
        snake.updatePosition();
        if (this.loseCondition()) this.lost();
        else {
            this.checkMouses();
            if(this.winCondition())this.won();
            this.updateMouses();
            this.repaint();
        }
    }
    protected void addMouses(int n){
        for (int i = 0; i<n;i++){
            mouses.add(this.newMouse());
        }
    }
    protected Mouse newMouse (){
        Coord c = new Coord(0,0);
        do {
            int x = (int) (Math.random() * nWidth);
            int y = (int) (Math.random() * nHeight);
            c.setX(x);
            c.setY(y);
        } while (!isCoordFree(c));
        return new Mouse(c,caseSize, nWidth,nHeight);
    }
    protected void won(){
        System.out.println("You win !!");
        timer.stop();

        this.showEndScreen(true);
    }
    protected void lost(){
        System.out.println("You Lose !!");
        timer.stop();
        this.showEndScreen(false);
    }
    protected boolean loseCondition(){
        return snake.selfBitten();
    }
    protected boolean winCondition(){
        return score >=10;
    }
    protected boolean isCoordFree(Coord c){
        boolean free = snake.isCoordFree(c);
        for (Mouse m : mouses){
            //System.out.println("Compare" + m.getCoords() + " and " + c + " : " + !m.getCoords().equals(c));
            if (m.getCoords().equals(c)){ free = false;};
        }
        return free;
    }

    protected void updateMouse(Mouse m){};
    protected void updateMouses(){}

    private void showEndScreen(boolean won) {
        // S'assurer que le niveau utilise un layout qui permet de superposer (ex: BorderLayout)
        this.setLayout(new BorderLayout());

        EndPanel endPanel = new EndPanel(w,number, won);

        this.add(endPanel, BorderLayout.CENTER);

        // Indispensable en Swing pour dire au conteneur de prendre en compte le nouveau composant
        this.revalidate();
        this.repaint();

        // Ne pas oublier de donner le focus au panneau de fin pour que les boutons réagissent bien si besoin
        endPanel.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = nWidth*caseSize;
        int height = nHeight*caseSize;
        g.drawImage(backgroundImage, 0, 0, width, height, this);

        for (Mouse m : mouses){
            m.display(g);
        }
        //mouse.display(g);
        snake.display(g);


        if (background != null) {
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        //else System.out.println("background is null");
    }

    @Override
    public String toString() {
        return "LEVEL "+number;
    }

    public void destroy(){
        mouses.clear();
        if (timer != null) {
            timer.stop();
        }
    }
}
