public class Coord {
    int x;
    int y;
    private static int nWidth;
    private static int nHeight;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    //@Override
    public boolean equals(Coord c){
        return x == c.x && y == c.y;
    }
    @Override
    public String toString() {
        return "{" + x + ","+ y + '}';
    }
}
