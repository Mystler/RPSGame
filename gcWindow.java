import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

//Die Klasse für das Anzeigefenster
public class gcWindow extends Frame implements ActionListener, KeyListener{
    private static final long serialVersionUID = -5832913855637863804L;
    
    //Globale Variablen der beiden Spieler
    //Spieler 1 als plHuman, da er zwangsweise menschlich ist
    private plHuman spieler1;
    //Spieler 2 als Spieler, da er PC oder NPC sein kann
    private Spieler spieler2;
    //aktive Auswahl der Spieler (1 - Stein, 2 - Papier, 3 - Schere, 0 - keine)
    private int elSpieler1 = 0;
    private int elSpieler2 = 0;
    
    //Images für das Graphics System
    private BufferedImage iPlayer1;
    private BufferedImage iPlayer2;
    private BufferedImage iElement1;
    private BufferedImage iElement2;
    private BufferedImage iHP1;
    private BufferedImage iHP2;
    private BufferedImage iHelp1;
    private BufferedImage iHelp2;
    
    //Die globalen AWT-Objekte der Spielerauswahl
    private Label lblName1;
    private TextField txtName1;
    private Label lblName2;
    private Choice chName2;
    private TextField txtName2;
    private Button btnName;
    //Array der Components des Bildschirms (init im Konstruktor)
    private Component screen1[];
    
    //AWT-Objekte des Spielbildschirms
    private boolean kActive = false;
    private Label lblSpieler1;
    private Label lblSpieler2;
    private Label lblRate1;
    private Label lblRate2;
    private Label lblHP1;
    private Label lblHP2;
    private Label lblStatus;
    private Button btnRestart;
    //Array der Components des Bildschirms (init im Konstruktor)
    private Component screen2[];
    
    //Konstruktor des Fensters
    public gcWindow(){
        //Allg. Aufbau des Fensters
        super("RPS Game");
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                System.exit(0);
            }
        });
        setLayout(null);
        setLocation(100, 100);
        setSize(240, 230);
        setResizable(false);
        setFocusable(true);
        addKeyListener(this);
        
        //Aufbau der Spielerauswahl, sichtbar
        lblName1 = new Label("Spieler 1:");
        lblName1.setBounds(20, 40, 200, 20);
        add(lblName1);
        txtName1 = new TextField("");
        txtName1.setBounds(20, 60, 200, 20);
        add(txtName1);
        lblName2 = new Label("Spieler 2:");
        lblName2.setBounds(20, 100, 200, 20);
        add(lblName2);
        chName2 = new Choice();
        chName2.setBounds(20, 120, 200, 20);
        chName2.add("Mensch");
        chName2.add("Computer");
        add(chName2);
        txtName2 = new TextField("");
        txtName2.setBounds(20, 150, 200, 20);
        add(txtName2);
        btnName = new Button("OK");
        btnName.setBounds(80, 180, 80, 25);
        add(btnName);
        btnName.addActionListener(this);
        
        //Aufbau der Spielanzeige, unsichtbar
        lblSpieler1 = new Label();
        lblSpieler1.setBounds(20, 40, 280, 20);
        add(lblSpieler1);
        lblSpieler2 = new Label();
        lblSpieler2.setBounds(340, 40, 280, 20);
        add(lblSpieler2);
        lblRate1 = new Label();
        lblRate1.setBounds(20, 60, 280, 20);
        add(lblRate1);
        lblRate2 = new Label();
        lblRate2.setBounds(340, 60, 280, 20);
        add(lblRate2);
        lblHP1 = new Label("HP:");
        lblHP1.setBounds(20, 80, 30, 20);
        add(lblHP1);
        lblHP2 = new Label("HP:");
        lblHP2.setBounds(340, 80, 30, 20);
        add(lblHP2);
        lblStatus = new Label();
        lblStatus.setBounds(240, 300, 200, 20);
        add(lblStatus);
        btnRestart = new Button("Neustart");
        btnRestart.setBounds(260, 440, 80, 25);
        add(btnRestart);
        btnRestart.setVisible(false);
        btnRestart.addActionListener(this);
        
        /*Bauen des Component-Arrays und Übertragen in den
        globalen Array (ohne temporäre Variable folgt eine
        NullPointerException da die Elemente noch nicht
        initialisiert wurden)*/
        Component screen1tmp[] = {lblName1,
                txtName1, lblName2, chName2, txtName2, btnName};
        Component screen2tmp[] = {lblSpieler1, lblSpieler2,
                lblRate1, lblRate2, lblHP1, lblHP2, lblStatus};
        screen1 = screen1tmp;
        screen2 = screen2tmp;
        
        hideScreen2();
    }
    
    //Anzeigen und Verstecken der Components
    public void showScreen1(){
        for(int i = 0; i < screen1.length; i++){
            screen1[i].setVisible(true);
        }
    }
    public void showScreen2(){
        for(int i = 0; i < screen2.length; i++){
            screen2[i].setVisible(true);
        }
    }
    public void hideScreen1(){
        for(int i = 0; i < screen1.length; i++){
            screen1[i].setVisible(false);
        }
    }
    public void hideScreen2(){
        /*Zur Erfüllung der Aufgabe verzichte ich hier auf
         die angenehme for-Schleife und bastle mir diese
         mit einer while-Schleife selbst.*/
        int i = 0;
        while(i < screen2.length){
            screen2[i].setVisible(false);
            i++;
        }
    }
    
    //Verarbeitung der elementspezifischen Eingaben der Spieler
    public void eingabe(int spieler, int auswahl){
        if(elSpieler1 == 0 || elSpieler2 == 0){
            //Zurücksetzen der Element-Anzeige
            iElement1 = null;
            iElement2 = null;
            repaint();
            //Filterung eines möglichen Programmierfehlers
            if(auswahl > 3 || auswahl < 1){
                System.err.println("Programmfehler bei spieler in eingabe()");
                System.exit(0);
                return;
            }
            //Zuordnung des gewählten Elements zum Spieler
            switch(spieler){
                case 1:
                    elSpieler1 = auswahl;
                    break;
                case 2:
                    elSpieler2 = auswahl;
                    break;
                //Dieser Fall ist ein Programmierfehler
                default:
                    System.err.println("Programmfehler bei spieler in eingabe()");
                    System.exit(0);
                    return;
            }
            lblStatus.setText("Spieler " + spieler + " hat gewählt.");
        }
        if(!spieler2.isHuman()){
            Random rnd = new Random();
            int npcChoice = rnd.nextInt(3) + 1;
            elSpieler2 = npcChoice;
        }
        //1 - Stein, 2 - Papier, 3 - Schere, 0 - keine
        if(elSpieler1 != 0 && elSpieler2 != 0){
            if(elSpieler1 - 1 == elSpieler2 ||  elSpieler1 + 2 == elSpieler2){
                //Spieler 1 gewinnt
                spieler1.addWin();
                spieler2.addLose();
                updateRatios();
                new gcMsgBox("Spieler 1 gewinnt!");
            }
            else if(elSpieler1 + 1 == elSpieler2 || elSpieler1 - 2 == elSpieler2){
                //Spieler 2 gewinnt
                spieler1.addLose();
                spieler2.addWin();
                updateRatios();
                new gcMsgBox("Spieler 2 gewinnt!");
            }
            else if(elSpieler1 == elSpieler2){
                spieler1.addRemis();
                spieler2.addRemis();
                updateRatios();
                new gcMsgBox("Unentschieden!");
            }
            switch(elSpieler1){
                case 1:
                    iElement1 = loadImage("stein.jpg");
                    break;
                case 2:
                    iElement1 = loadImage("papier.jpg");
                    break;
                case 3:
                    iElement1 = loadImage("schere.jpg");
                    break;
                default:
                    iElement1 = null;
                    break;
            }
            switch(elSpieler2){
                case 1:
                    iElement2 = loadImage("stein.jpg");
                    break;
                case 2:
                    iElement2 = loadImage("papier.jpg");
                    break;
                case 3:
                    iElement2 = loadImage("schere.jpg");
                    break;
                default:
                    iElement2 = null;
                    break;
            }
            elSpieler1 = 0;
            elSpieler2 = 0;
        }
        
    }
    
    //Funktion zum Update der Siege/Niederlagen Statistik der Spieler
    public void updateRatios(){
        lblRate1.setText(spieler1.getRatio());
        lblRate2.setText(spieler2.getRatio());
        iHP1 = loadImage("hp" + spieler1.getHP() + ".jpg");
        iHP2 = loadImage("hp" + spieler2.getHP() + ".jpg");
        if(spieler1.getHP() == 0 || spieler2.getHP() == 0){
            if(spieler1.getHP() == 0){
                lblStatus.setText("Spiel vorbei! Spieler 2 gewinnt!");
                iPlayer1 = loadImage("player1down.jpg");
            }
            else{
                lblStatus.setText("Spiel vorbei! Spieler 1 gewinnt!");
                iPlayer2 = loadImage("player2down.jpg");
            }
            kActive = false;
            btnRestart.setVisible(true);
        }
        repaint();
    }
    
    //Funktion zum Neustarten des Spiels
    public void restartGame(){
        btnRestart.setVisible(false);
        spieler1.reset();
        spieler2.reset();
        updateRatios();
        iPlayer1 = loadImage("player1.jpg");
        iPlayer2 = loadImage("player2.jpg");
        repaint();
        kActive = true;
    }
    
    //Lade Bild aus String
    public BufferedImage loadImage(String path){
        try{
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    //Behandlung des ActionListeners
    public void actionPerformed(ActionEvent evt){
        //Bestätigen der Spielerauswahl
        if(evt.getSource() == btnName){
            //Initialisierung der Spielerklassen
            String plName1 = txtName1.getText();
            String plName2 = txtName2.getText();
            spieler1 = new plHuman(1, plName1);
            if(chName2.getSelectedIndex() == 0){
                spieler2 = new plHuman(2, plName2);
                iHelp2 = loadImage("key2.jpg");
            }
            else{
                spieler2 = new plComputer(2, plName2);
            }
            
            //Wechsel zum Spielbildschirm
            hideScreen1();
            showScreen2();
            setSize(640, 480);
            lblSpieler1.setText(spieler1.getName());
            lblSpieler2.setText(spieler2.getName());
            updateRatios();
            kActive = true;
            requestFocusInWindow();
            iPlayer1 = loadImage("player1.jpg");
            iPlayer2 = loadImage("player2.jpg");
            iHelp1 = loadImage("key1.jpg");
            repaint();
        }
        //Drücken des Neustart-Buttons
        else if(evt.getSource() == btnRestart){
            restartGame();
        }
    }
    
    //Behandlung des KeyListeners
    public void keyPressed(KeyEvent evt){
        if(!kActive)
            return;
        if(evt.getKeyCode() == KeyEvent.VK_A)
            eingabe(1, 1);
        else if(evt.getKeyCode() == KeyEvent.VK_S)
            eingabe(1, 2);
        else if(evt.getKeyCode() == KeyEvent.VK_D)
            eingabe(1, 3);
        if(!spieler2.isHuman())
            return;
        if(evt.getKeyCode() == KeyEvent.VK_J)
            eingabe(2, 1);
        else if(evt.getKeyCode() == KeyEvent.VK_K)
            eingabe(2, 2);
        else if(evt.getKeyCode() == KeyEvent.VK_L)
            eingabe(2, 3);
    }
    
    public void keyReleased(KeyEvent evt){
    }
    
    public void keyTyped(KeyEvent evt){
    }
    
    public void paint(Graphics g){
        //Anzeigen der Grafiken sofern diese gesetzt sind
        if(iPlayer1 != null)
            g.drawImage(iPlayer1, 20, 100, this);
        if(iPlayer2 != null)
            g.drawImage(iPlayer2, 450, 100, this);
        if(iElement1 != null)
            g.drawImage(iElement1, 150, 200, this);
        if(iElement2 != null)
            g.drawImage(iElement2, 340, 200, this);
        if(iHP1 != null)
            g.drawImage(iHP1, 50, 80, this);
        if(iHP2 != null)
            g.drawImage(iHP2, 390, 80, this);
        if(iHelp1 != null)
            g.drawImage(iHelp1, 100, 310, this);
        if(iHelp2 != null)
            g.drawImage(iHelp2, 100, 370, this);
    }
}
