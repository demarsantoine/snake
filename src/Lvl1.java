public class Lvl1 extends Level{

    public Lvl1(Window w){
        super(w);
        nWidth = 50;
        nHeight = 50;
        caseSize = 10;

        this.setSize();

        snake = new Snake(this);
    }
}
