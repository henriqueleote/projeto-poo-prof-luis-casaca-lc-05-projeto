package game;

import board.Dock;
import board.Unknown;
import board.Board;

/**
 *
 * @author Leote (200221060)
 */
public class Rules {
    
    public static Game game = new Game();   
    public static Board board = new Board();
    
    //CONSTRUTOR VAZIO
    public Rules(){}

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

    public boolean checkBounds(int x, int y) {
        boolean result = false;
        if (x >= 0 && x < game.NUMBER_OF_ROWS && y >= 0 && y < game.NUMBER_OF_COLUMNS) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    
    public boolean checkBounds(int arrayNumber) {
        boolean result = false;
        if (game.gameBoard.contains(arrayNumber)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

//    public boolean checkSpotForDock(int random) {
//        
//        boolean result = false;
//        if (checkBounds(board.getRowFromIndex(random) - 1, board.getColumnFromIndex(random) - 1) && checkEmpty(board.getIndex(board.getRowFromIndex(random) - 1, board.getColumnFromIndex(random) - 1))) {
//            if (checkBounds(board.getRowFromIndex(random) - 1, board.getColumnFromIndex(random)) && checkEmpty(board.getIndex(board.getRowFromIndex(random) - 1, board.getColumnFromIndex(random)))) {
//                if (checkBounds(board.getRowFromIndex(random) - 1, board.getColumnFromIndex(random) + 1) && checkEmpty(board.getIndex(board.getRowFromIndex(random) - 1, board.getColumnFromIndex(random) + 1))) {
//                    if (checkBounds(board.getRowFromIndex(random), board.getColumnFromIndex(random) - 1) && checkEmpty(board.getIndex(board.getRowFromIndex(random), board.getColumnFromIndex(random) - 1))) {
//                        if (checkBounds(board.getRowFromIndex(random), board.getColumnFromIndex(random) + 1) && checkEmpty(board.getIndex(board.getRowFromIndex(random), board.getColumnFromIndex(random) + 1))) {
//                            if (checkBounds(board.getRowFromIndex(random) + 1, board.getColumnFromIndex(random) - 1) && checkEmpty(board.getIndex(board.getRowFromIndex(random) + 1, board.getColumnFromIndex(random) - 1))) {
//                                if (checkBounds(board.getRowFromIndex(random) + 1, board.getColumnFromIndex(random)) && checkEmpty(board.getIndex(board.getRowFromIndex(random) + 1, board.getColumnFromIndex(random)))) {
//                                    if (checkBounds(board.getRowFromIndex(random) + 1, board.getColumnFromIndex(random) + 1) && checkEmpty(board.getIndex(board.getRowFromIndex(random) + 1, board.getColumnFromIndex(random) + 1))) {
//                                        result = true;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
    
    public boolean checkSpotForDock(int row, int column){
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
    public boolean checkSpotForBoat(int x, int y){
        boolean result = false;
        if((checkBounds(x-1,y) && game.gameBoard.get(board.getIndex(x-1, y)) instanceof Dock) || (checkBounds(x+1,y) && game.gameBoard.get(board.getIndex(x+1, y)) instanceof Dock) || (checkBounds(x,y-1) && game.gameBoard.get(board.getIndex(x, y-1)) instanceof Dock) || (checkBounds(x,y+1) && game.gameBoard.get(board.getIndex(x, y+1)) instanceof Dock))
            result = true;
        else
            result = false;
        
        return result;
    }
}
