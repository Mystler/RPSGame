/*==LICENSE==

This file is part of RPSGame.
Copyright (C) 2012 Florian Meiﬂner

RPSGame is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

RPSGame is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with RPSGame. If not, see <http://www.gnu.org/licenses/>.

*==LICENSE==*/

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
