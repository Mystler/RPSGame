import java.awt.*;
import java.awt.event.*;

//Die Klasse zur erstellung von Nachrichten Dialogen
public class gcMsgBox extends Frame implements ActionListener, KeyListener{
    private static final long serialVersionUID = 8285835182320840860L;
    
    private Button btnOK;
    
    public gcMsgBox(String msg){
        //Allg. Aufbau des Fensters
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                setVisible(false);
            }
        });
        setTitle("Info");
        setLayout(null);
        setLocation(200, 200);
        setSize(500, 120);
        setVisible(true);
        
        Label lblMsg = new Label(msg);
        lblMsg.setBounds(20, 40, 460, 20);
        add(lblMsg);
        btnOK = new Button("OK!");
        btnOK.setBounds(200, 70, 100, 25);
        add(btnOK);
        btnOK.addActionListener(this);
        btnOK.addKeyListener(this);
    }
    
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnOK)
            setVisible(false);
    }
    
    //Behandlung des KeyListeners
    public void keyPressed(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            setVisible(false);
    }
    
    public void keyReleased(KeyEvent evt){
    }
    
    public void keyTyped(KeyEvent evt){
    }
}
