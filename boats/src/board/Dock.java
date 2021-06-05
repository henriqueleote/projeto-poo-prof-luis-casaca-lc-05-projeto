package board;

import board.Position;
import board.Spot;

/**
 *
 * @author Leote (200221060)
 */
public class Dock extends Spot{

    public char indentifier;                                                    //Variavel char para o identificador no tabuleiro de jogo
    
    //CONSTRUTOR - Define valores recebidos
    public Dock(int x, int y){
        super(x,y);
        position = new Position(x,y);
        indentifier = 'P';
    }
    
    //DEVOLVE O IDENTIFICADOR - A FUNCIONAR
    public char getIndentifier(){
        return indentifier;
    }

    //IMPRIME PARA STRING - A FUNCIONAR
    @Override
    public String toString(){
        return Character.toString(getIndentifier());                            //Converte de char para String no output
    }
    
}
