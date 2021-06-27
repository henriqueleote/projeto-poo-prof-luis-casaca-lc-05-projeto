package game;

import board.Dock;
import board.Unknown;
import board.Board;

/**
 *
 * @author Leote (200221060)
 */
public class Rules {
    
    public static Game game = new Game();                                       //Objeto da classe Game
    public static Board board = new Board();                                    //Objeto da classe Board
    
    //CONSTRUTOR VAZIO
    public Rules(){}

    //VERIFICA SE A POSIÇÃO PEDIDA EXISTE NA COLEÇÃO E DEVOLVE UM BOOLEAN
    public boolean checkEmpty(int arrayNumber) {
        boolean result = false;
        if(game.gameBoard.contains(arrayNumber)){
            if (game.gameBoard.get(arrayNumber) instanceof Unknown) {
                result = true;
            } else {
                result = false;
            }
        }else
            result = false;
        
        return result;
    }

    //VERIFICA SE A POSIÇÃO (x,y) PEDIDA EXISTE NA COLEÇÃO E DEVOLVE UM BOOLEAN
    public boolean checkBounds(int x, int y) {
        boolean result = false;
        if (x >= 0 && x < game.NUMBER_OF_ROWS && y >= 0 && y < game.NUMBER_OF_COLUMNS) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    
    //VERIFICA SE A POSIÇÃO PEDIDA EXISTE NA COLEÇÃO E DEVOLVE UM BOOLEAN
    public boolean checkBounds(int arrayNumber) {
        boolean result = false;
        if (game.gameBoard.contains(arrayNumber)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    //VERIFICA SE PODE SER POSTO UM PORTO NA POSIÇÃO (x,y) PEDIDA
    public boolean checkSpotForDock(int x, int y){
        boolean result = false;
        for(int row = x - 1; row < x - 1 + 3; row++)
        {
            for (int column = y - 1; column < y - 1 + 3; column++)
            {
                if (!(row == x && column == y))
                {
                    result = true;
                }
            }
        }
        return result;
    }
     
    //VERIFICA SE PODE SER POSTO UM PORTO NA POSIÇÃO (x,y) PEDIDA
    public boolean checkSpotForBoat(int x, int y){
        boolean result = false;
        if((checkBounds(x-1,y) && game.gameBoard.get(board.getIndex(x-1, y)) instanceof Dock) || (checkBounds(x+1,y) && game.gameBoard.get(board.getIndex(x+1, y)) instanceof Dock) || (checkBounds(x,y-1) && game.gameBoard.get(board.getIndex(x, y-1)) instanceof Dock) || (checkBounds(x,y+1) && game.gameBoard.get(board.getIndex(x, y+1)) instanceof Dock))
            result = true;
        else
            result = false;
        
        return result;
    }
}
