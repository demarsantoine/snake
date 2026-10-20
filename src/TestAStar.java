import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class TestAStar extends Level {

    Mouse mouse;
    ArrayList<Coord> path;
    Coord cible;

    private static class Node  implements Comparable<Node>{
        Coord coord;
        Node previous;
        char prevDir;
        int g; //distance to start
        int h; //distance to end *HEURISTIC*
        int f; //g+h
        int snakeDist; //distance to snake's head

        public Node(Coord coord, Node previous, char prevDir, int g, int h, int snakeDist){
            this.coord = coord;
            this.previous = previous;
            this.prevDir = prevDir;
            this.g = g;
            this.h = h;
            this.f = g+h;
            this.snakeDist = snakeDist;

        }

        @Override
        // return -n if this avant o
        // return 0 if this == o
        // return n if this après o
        public int compareTo (Node o) {
            int comp = this.f - o.f;
            if (comp == 0) comp = this.h - o.h;
            //if (comp ==0) comp = this.snakeDist - o.snakeDist;
            return comp;
        };

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(coord);
            sb.append("  *****  g : " + g + ", h : " + h + ", sDist : " +  snakeDist + " ***** ");
            if (previous != null) sb.append(" Previous = " + previous.coord.toString());
            return sb.toString();
        }

        public void display (Graphics g, int size){
            Color body = new Color(13, 149, 227);
            g.setColor(body);

            Color contour = new Color(13, 149, 227);
            g.fillRect(coord.getX()*size,coord.getY()*size,size, size);
            g.setColor(contour);
            g.drawRect(coord.getX()*size,coord.getY()*size,size, size);
        }

    }

    public TestAStar(Window w){
        super(w);
        number = 0;
        nWidth = 10;
        nHeight = 10;
        caseSize = 60;
        speed = 200;

        this.setSize();
        Color BGColor = new Color(183, 227, 142);
        this.setBackground(BGColor);

        this.setLayout(null);
        scoreLabel = new JLabel("Score: "+ score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(400,0, 100, 40);
        this.add(scoreLabel);

        snake = new Snake(this);
        mouse = new Mouse (new Coord (0,0), caseSize, nWidth, nHeight);
        cible = new Coord(6 ,6);
        path = shortestPath(mouse.getCoords(),cible );
        //this.inputHandler = new InputHandler(snake);
        //this.addKeyListener(this.inputHandler);

        this.requestFocusInWindow();

        timer = new Timer(speed, e -> {this.update();});


        timer.start();
    }


    //Renvoie le shortest path ATTENTION sans tenir compte du modulo
    private ArrayList<Coord> shortestPath(Coord start, Coord end) {
        Coord sCoord = snake.headCoords();
        ArrayList<Coord> path = new ArrayList<>();
        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        Node startingNode = new Node (start, null, 'C', 0, manhattanDistance(start,end, nWidth, nHeight), manhattanDistance(start,sCoord, nWidth, nHeight));
        heap.add(startingNode);
        boolean found = false;
        Node current = startingNode;
        while (!found) {

            current = heap.poll();
            System.out.println(current.toString());
            if (current.coord.equals(end)) {
                found = true;
                while (!current.coord.equals(start)){
                    path.addFirst(current.coord);
                    current = current.previous;
                }
            }
            else {
                int cX = current.coord.getX();
                int cY = current.coord.getY();
                int g = current.g + 1;
                int h, snakeDist;

                //ajouter le N à la heap
                Coord NCoord = new Coord(cX, (cY- 1 + nWidth ) % nWidth); //On utilise le modulo ici
                if (isCoordFree(NCoord)) {
                    h = manhattanDistance(NCoord, end, nWidth, nHeight);
                    snakeDist = manhattanDistance(NCoord, sCoord, nWidth, nHeight);
                    System.out.println("      adding N : " + NCoord + " --- g = " +g+ "; h = " + h + "  f = " + (g+h) + " s = " + snakeDist);
                    Node Nnode = new Node(NCoord, current, 'S', g, h, snakeDist);
                    heap.add(Nnode);
                }

                //ajouter le E à la heap
                Coord ECoord = new Coord((cX + 1) % nWidth, cY); //On utilise le modulo ici
                if (isCoordFree(ECoord)) {
                    h = manhattanDistance(ECoord, end, nWidth, nHeight);
                    snakeDist = manhattanDistance(ECoord, sCoord, nWidth, nHeight);
                    System.out.println("      adding E : " + ECoord + "---- g = " +g+ "; h = " + h +"  f = " + (g+h));
                    Node Enode = new Node(ECoord, current, 'W', g, h, snakeDist);
                    heap.add(Enode);
                }

                //ajouter le S à la heap
                Coord SCoord = new Coord(cX, (cY + 1) % nWidth); //On utilise le modulo ici
                if (isCoordFree(SCoord)) {
                    h = manhattanDistance(SCoord, end, nWidth, nHeight);
                    snakeDist = manhattanDistance(SCoord, sCoord, nWidth, nHeight);
                    System.out.println("      adding S : " + SCoord + "---- g = " +g+ "; h = " + h +"  f = " + (g+h));
                    Node Snode = new Node(SCoord, current, 'N', g, h, snakeDist);
                    heap.add(Snode);
                }

                //ajouter le W à la heap
                Coord WCoord = new Coord((cX + nWidth - 1) % nWidth, cY); //On utilise le modulo ici
                if (isCoordFree(WCoord)) {
                    h = manhattanDistance(WCoord, end, nWidth, nHeight);
                    snakeDist = manhattanDistance(WCoord, sCoord, nWidth, nHeight);
                    System.out.println("      adding W : " + WCoord + "---- g = " +g+ "; h = " + h +"  f = " + (g+h));
                    Node Wnode = new Node(WCoord, current, 'S', g, h, snakeDist);
                    heap.add(Wnode);
                }
            }

        }


        return path;
    }


    //renvoie la distance entre 2 coord ATTENTION sans tenir compte du modulo
    //public static int manhattanDistance(Coord a, Coord b){return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());}

    //renvoie la distance entre 2 coord ATTENTION sans tenir compte du modulo
    public static int manhattanDistance(Coord a, Coord b, int width, int height){
        int normX = Math.abs(a.getX() - b.getX());
        int distX =  Math.min(normX, width - normX);

        int normY = Math.abs(a.getY() - b.getY());
        int distY =  Math.min(normY, height-normY);

        return distX + distY;
    }



    public void drawPath(Graphics g){
        //if (path.isEmpty()){System.out.println("Path is empty");}
        for (Coord c : path){
            Color body = new Color(13, 149, 227, 60);
            g.setColor(body);

            Color contour = new Color(13, 149, 227);
            g.fillRect(c.getX()*caseSize,c.getY()*caseSize,caseSize, caseSize);
            g.setColor(contour);
            g.drawRect(c.getX()*caseSize,c.getY()*caseSize,caseSize, caseSize);
        }
    }

    public void drawCible(Graphics g){

        Color body = new Color(239, 51, 51, 100);
        g.setColor(body);

        Color contour = new Color(253, 1, 1);
        g.fillRect(cible.getX()*caseSize,cible.getY()*caseSize,caseSize, caseSize);
        g.setColor(contour);
        g.drawRect(cible.getX()*caseSize,cible.getY()*caseSize,caseSize, caseSize);

    }

    @Override
    public void updateMouse(Mouse m){
        if (!path.isEmpty()) {
            Coord next = path.getFirst();
            path.removeFirst();
            m.setCoords(next);
        }
    }

    @Override
    public void update() {
        //this.updateMouse(mouse);
        this.repaint();
    }

    @Override
    public String toString() {
        return "TEST LEVEL";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = nWidth*caseSize;
        int height = nHeight*caseSize;
        g.drawImage(backgroundImage, 0, 0, width, height, this);

         mouse.display(g);

        //mouse.display(g);
        snake.display(g);

        drawPath(g);
        drawCible(g);

        if (background != null) {
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        //else System.out.println("background is null");
    }
}
