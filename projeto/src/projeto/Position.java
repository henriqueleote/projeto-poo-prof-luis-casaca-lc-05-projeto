package projeto;

/**
 *
 * @author Gonçalo Fernandes e Henrique Leote
 */
public class Position {
    public int row;                        //variavel para as linhas
    public int column;                     //variavel para as colunas

    //construtor da classe Position que recebe as linhas e colunas
    public Position(int row, int column){
        this.row = row;             //inicializar a variavel com o numero de linhas recebida
        this.column = column;       //inicializar a variavel com o numero de colunas recebida
    }
    
    //GETS
    
    //metodo que devolve o numero de linhas
    public int getRow() {
        return row;
    }

    //metodo que devolve o numero de colunas
    public int getColumn() {
        return column;
    }

    //SETS
    //metodo que define o numero de linhas
    public void setRow(int row){
        this.row = row;
    }

    //metodo que define o numero de colunas
    public void setColumn(int column) {
        this.column = column;
    }
}
