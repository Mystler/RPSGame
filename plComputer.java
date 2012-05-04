
public class plComputer extends Spieler{
    public plComputer(int id, String name){
        plID = id;
        plName = name + (" (Comp.)");
        plMaxHP = 3;
        plHuman = false;
        reset();
    }
}
