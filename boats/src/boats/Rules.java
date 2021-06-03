/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Game.i;
import static boats.Game.board;
import java.util.ArrayList;

/**
 *
 * @author CucasPC
 */
public class Rules {

    public boolean placeBoatLeft(ArrayList<Spot> board) {
        boolean result = false;
        for (i = 0; i < board.size(); i++) {
            if (board.get(i).toString().equals("-") || board.get(i).toString().equals("A")) {
                //caso haja um porto à esquerda
                if (board.get(i).getPosition().getRow() > 0 && board.get(i).getPosition().getColumn() - 1 > 0 && board.get(i).toString().equals("-")) {
                    result = true;
                }
            }
        }
        return result;
    }
}
