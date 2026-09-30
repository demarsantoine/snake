import java.awt.*;

public class MovingCase extends Case{


    Image image;
    char direction;

    public MovingCase(){
        super(0,0, 0, 0, 0);
        direction = 'W';
    }

    public MovingCase(int x, int y, int size, int nWidth, int nHeight){
        super(x,y, size,  nWidth, nHeight);
        direction = 'W';
    }

    public MovingCase(Coord c, int size, int nWidth, int nHeight){
        super(c,size, nWidth, nHeight);
        direction = 'W';
    }

    public void setDirection(char c){this.direction = c;};
    public char getDirection(){return direction;};

    /*public void display (Graphics g){
        Color body = new Color(187, 70, 248);
        Color contour = new Color(50, 21, 67);
        int x1 = 0,x2= 0, x3= 0, y1= 0, y2= 0, y3 = 0;
        if (direction == 'W'){
            x1 = coord.x*size + size;
            y1 = coord.y*size;
            x2 = coord.x*size;
            y2 = coord.y*size + size/2;
            x3 = coord.x*size + size;
            y3 = coord.y*size + size;
        }

        if (direction == 'E'){
            x1 = coord.x*size;
            y1 = coord.y*size;
            x2 = coord.x*size + size;
            y2 = coord.y*size + size/2;
            x3 = coord.x*size;
            y3 = coord.y*size + size;
        }

        if (direction == 'N'){
            x1 = coord.x*size;
            y1 = coord.y*size + size;
            x2 = coord.x*size + size/2;
            y2 = coord.y*size;
            x3 = coord.x*size + size;
            y3 = coord.y*size + size;
        }

        if (direction == 'S'){
            x1 = coord.x*size;
            y1 = coord.y*size;
            x2 = coord.x*size + size/2;
            y2 = coord.y*size + size;
            x3 = coord.x*size + size;
            y3 = coord.y*size;
        }

        g.setColor(body);
        g.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
        g.setColor(contour);
        g.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);

    }*/

    public void display (Graphics g){
        //this.setSize(size,size);
        this.paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        if (image != null) {
            int x = coord.getX() * size;
            int y = coord.getY() * size;

            double angle = 0;
            switch (direction) {
                case 'W':
                    angle = 0; // Pas de rotation (par défaut)
                    break;
                case 'S':
                    angle = -Math.PI / 2; // 90 degrés
                    break;
                case 'E':
                    angle = Math.PI; // 180 degrés
                    break;
                case 'N':
                    angle = Math.PI / 2; // -90 degrés (ou 270)
                    break;
            }

            double centerX = x + size / 2.0;
            double centerY = y + size / 2.0;

            // Application de la rotation autour du centre de l'image
            g2d.rotate(angle, centerX, centerY);

            g2d.drawImage(image, x, y, size, size, this);

            // Libération du contexte graphique temporaire
            g2d.dispose();

        }
        else System.out.println("image is null");
    }
}
