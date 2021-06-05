package boats;

/**
 *
 * @author Leote (200221060)
 */
public class Position {

    public int x;                       //variavel para as linhas
    public int y;                       //variavel para as colunas

    //construtor da classe Position que recebe as linhas e colunas
    public Position(int x, int y) {
        this.x = x;                     //inicializar a variavel com o numero de linhas recebida
        this.y = y;                     //inicializar a variavel com o numero de colunas recebida
    }

    public Position() {
    }

    //GETS
    //metodo que devolve o numero de linhas
    public int getRow() {
        return x;
    }

    //metodo que devolve o numero de colunas
    public int getColumn() {
        return y;
    }

    //SETS
    //metodo que define o numero de linhas
    public void setRow(int x) {
        this.x = x;
    }

    //metodo que define o numero de colunas
    public void setColumn(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return getRow()+ ":" + getColumn();
    }
}
