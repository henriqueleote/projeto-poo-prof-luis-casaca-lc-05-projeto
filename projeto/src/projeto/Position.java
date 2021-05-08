package projeto;

/**
 *
 * @author CucasPC
 */
public class Position {
    private int row;                        //variavel para as linhas
    private int column;                     //variavel para as colunas

    //construtor da classe Position que recebe as linhas e colunas
    Position(int row, int column){
        this.row = row;             //inicializar a variavel com o numero de linhas recebida
        this.column = column;       //inicializar a variavel com o numero de colunas recebida
    }
    
    //GETS
    
    //metodo que devolve o numero de linhas
    int getRow() {
        return row;
    }

    //metodo que devolve o numero de colunas
    int getColumn() {
        return column;
    }

    //SETS
    //metodo que define o numero de linhas
    void setRow(int row){
        this.row = row;
    }

    //metodo que define o numero de colunas
    void setColumn(int column) {
        this.column = column;
    }
}
