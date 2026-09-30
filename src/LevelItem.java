public class LevelItem {
    int number;
    int difficulty;

    LevelItem(int number){
        this.number = number;
    }
    public int getNumber(){return number;}
    public int getDifficulty(){return difficulty;}

    @Override
    public String toString(){
        return "Level " + number;
    }
}
