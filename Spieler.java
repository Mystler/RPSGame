public abstract class Spieler {
    //Attribute aller Spieler
    protected int plID;
    protected String plName;
    protected boolean plHuman;
    protected int plHP;
    protected int plMaxHP;
    protected int plGameCount;
    protected int plWinCount;
    protected int plRemisCount;
    protected int plLoseCount;
    
    //Standardkonstruktor, wird für erbende Klassen benötigt
    //Bitte Kontruktor der entsprechenden Unterklasse ausführen!
    protected Spieler(){
        plID = 0;
        plName = "Manfred";
        plMaxHP = 3;
        plHuman = true;
        reset();
    }
    
    //Reset der Daten
    public void reset(){
        plGameCount = 0;
        plWinCount = 0;
        plLoseCount = 0;
        plRemisCount = 0;
        plHP = plMaxHP;
    }
    
    //Abruf des Spielernamens
    public String getName(){
        return plName;
    }
    
    //Ist der Spieler menschlich?
    public boolean isHuman(){
        return plHuman;
    }
    
    //Eintragen eines Gewinnes
    public void addWin(){
        plGameCount++;
        plWinCount++;
    }
    
    //Eintragen eines Remis
    public void addRemis(){
        plGameCount++;
        plRemisCount++;
    }
    
    //Eintragen einer Niederlage
    public void addLose(){
        plGameCount++;
        plLoseCount++;
        plHP--;
    }
    
    //Rückgabe der Statistiken
    public int getWin(){
        return plWinCount;
    }
    public int getRemis(){
        return plRemisCount;
    }
    public int getLose(){
        return plLoseCount;
    }
    public int getGames(){
        return plGameCount;
    }
    public int getHP(){
        return plHP;
    }
    
    //Zusammensetzen des Rate-Strings
    public String getRatio(){
        return "Spiele: " + getGames() + " | Siege: "
            + getWin() + " | Niederlagen: " + getLose();
    }
}
