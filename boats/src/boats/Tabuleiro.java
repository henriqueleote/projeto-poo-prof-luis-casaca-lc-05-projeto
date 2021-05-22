/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author CucasPC
 */
public class Tabuleiro {
    
    //gera o board apenas, minuto 12:30
    
    Posicao[][] board;
    
    public Tabuleiro(){
        board = new Posicao[5][5];
        
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                Posicao tempLoc = new Posicao();
                board[row][col] = tempLoc;
            }
        }
        
        start();
        
    }
    
    public void placePlacehold(Casa casa){ //talvez noutro sitio
        int x = casa.getRow();
        int y = casa.getColumn();
        
    }
    
    public void fillBoard(Posicao[][] board){
        Arrays.fill(board,0);
        Arrays.toString(board);
    }
    
    public void start(){
        fillBoard(board);
    }
}
