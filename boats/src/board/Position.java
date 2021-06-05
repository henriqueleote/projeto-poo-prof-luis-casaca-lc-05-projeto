package board;

/**
 *
 * @author Leote (200221060)
 */
public class Position {

    public int x;                                                               //Variavel inteira para as linhas
    public int y;                                                               //Variavel inteira para as colunas

    
    //CONSTRUTOR - Define valores recebidos
    public Position(int x, int y) {
        this.x = x; 
        this.y = y;
    }

    
    //CONSTRUTOR VAZIO
    public Position() {}

    
    //DEVOLVE A LINHA - A FUNCIONAR
    public int getRow() {
        return x;
    }

    //DEVOLVE A COLUNA - A FUNCIONAR
    public int getColumn() {
        return y;
    }

    //DEFINE A LINHA - A FUNCIONAR
    public void setRow(int x) {
        this.x = x;
    }

    //DEFINE A COLUNA - A FUNCIONAR
    public void setColumn(int y) {
        this.y = y;
    }

    //IMPRIME PARA STRING - A FUNCIONAR
    @Override
    public String toString(){
        return getRow()+ ":" + getColumn();
    }
}
