/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Jogo.i;
import static boats.Jogo.placeholders;
import java.util.ArrayList;

/**
 *
 * @author CucasPC
 */
public class Regras {

    public boolean placeBoatLeft(ArrayList<Casa> placeholders) {
        boolean result = false;
        for (i = 0; i < placeholders.size(); i++) {
            if (placeholders.get(i).toString().equals("-") || placeholders.get(i).toString().equals("A")) {
                //caso haja um porto à esquerda
                if (placeholders.get(i).getPosition().getRow() > 0 && placeholders.get(i).getPosition().getColumn() - 1 > 0 && placeholders.get(i).toString().equals("-")) {
                    result = true;
                }
            }
        }
        return result;
    }
}
