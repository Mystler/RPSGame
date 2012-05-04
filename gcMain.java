/*==LICENSE==

This file is part of RPSGame.
Copyright (C) 2012 Florian Meißner

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

//Die ausführbare Klasse, die das Fenster startet
public class gcMain extends gcWindow{
    private static final long serialVersionUID = -5265913855637863804L;
    
    //Programmstart springt in diese Funktion
    public static void main(String[] args) {
        gcMain wnd = new gcMain();
        wnd.setVisible(true);
    }
}
