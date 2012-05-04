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

public class plComputer extends Spieler{
    public plComputer(int id, String name){
        plID = id;
        plName = name + (" (Comp.)");
        plMaxHP = 3;
        plHuman = false;
        reset();
    }
}
