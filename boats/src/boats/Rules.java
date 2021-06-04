/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Game.i;
import java.util.ArrayList;

/**
 *
 * @author CucasPC
 */
public class Rules {

    public  static boolean checkEmpty(int arrayNumber) {
        boolean result = false;
        if(Game.gameBoard.contains(arrayNumber)){
            if (Game.gameBoard.get(arrayNumber) instanceof Unknown) {
                result = true;
            } else {
                result = false;
            }
        }else
            result = false;
        
        return result;
    }

    public static boolean checkBounds(int x, int y) {
        boolean result = false;
        if (x >= 0 && x < Game.NUMBER_OF_ROWS && y >= 0 && y < Game.NUMBER_OF_COLUMNS) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    
    public static boolean checkBounds(int arrayNumber) {
        boolean result = false;
        if (Game.gameBoard.contains(arrayNumber)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static boolean checkSpotForDock(int random) { // não funciona
        boolean result = false;
        if (checkBounds(Board.getRowFromIndex(random) - 1, Board.getColumnFromIndex(random) - 1) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random) - 1, Board.getColumnFromIndex(random) - 1))) {
            if (checkBounds(Board.getRowFromIndex(random) - 1, Board.getColumnFromIndex(random)) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random) - 1, Board.getColumnFromIndex(random)))) {
                if (checkBounds(Board.getRowFromIndex(random) - 1, Board.getColumnFromIndex(random) + 1) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random) - 1, Board.getColumnFromIndex(random) + 1))) {
                    if (checkBounds(Board.getRowFromIndex(random), Board.getColumnFromIndex(random) - 1) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random), Board.getColumnFromIndex(random) - 1))) {
                        if (checkBounds(Board.getRowFromIndex(random), Board.getColumnFromIndex(random) + 1) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random), Board.getColumnFromIndex(random) + 1))) {
                            if (checkBounds(Board.getRowFromIndex(random) + 1, Board.getColumnFromIndex(random) - 1) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random) + 1, Board.getColumnFromIndex(random) - 1))) {
                                if (checkBounds(Board.getRowFromIndex(random) + 1, Board.getColumnFromIndex(random)) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random) + 1, Board.getColumnFromIndex(random)))) {
                                    if (checkBounds(Board.getRowFromIndex(random) + 1, Board.getColumnFromIndex(random) + 1) && checkEmpty(Board.getIndex(Board.getRowFromIndex(random) + 1, Board.getColumnFromIndex(random) + 1))) {
                                        result = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public static boolean checkSpotForDock(int row, int column){
        boolean result = false;
        for(int x = row - 1; x < row - 1 + 3; x++)
        {
            for (int y = column - 1; y < column - 1 + 3; y++)
            {
                if (!(x == row && y == column))
                {
                    result = true;
                }
            }
        }
        return result;
    }
    
    
    //Verifica se pode ser colocado um barco naquela posição
    public static boolean checkSpotForBoat(int x, int y){
        boolean result = false;
        if((checkBounds(x-1,y) && Game.gameBoard.get(Board.getIndex(x-1, y)) instanceof Dock) || (checkBounds(x+1,y) && Game.gameBoard.get(Board.getIndex(x+1, y)) instanceof Dock) || (checkBounds(x,y-1) && Game.gameBoard.get(Board.getIndex(x, y-1)) instanceof Dock) || (checkBounds(x,y+1) && Game.gameBoard.get(Board.getIndex(x, y+1)) instanceof Dock))
            result = true;
        else
            result = false;
        
        return result;
    }
}
