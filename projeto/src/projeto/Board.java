package projeto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CucasPC
 */
public class Board {

    private Type[][] gameBoard;                 //variavel array da classe Type para armazenar o tipo de campos na tabuleiro
    private int[] columnCount;                  //variavel de contador de linhas
    private int[] rowCount;                     //variavel de contador de colunas
    private List<Position> dockLocations;       //variavel de lista que armazena as posições das docas
    private List<Position> boatLocations;       //variavel de lista que armazena as posições dos barcos

    
    //construtor da classe Board
    public Board(){
        gameBoard = new Type[0][0];             //inicialização da variavel a 0
        columnCount = new int[0];               //inicialização da variavel a 0
        rowCount = new int[0];                  //inicialização da variavel a 0
        dockLocations = new ArrayList<>();      //inicialização da lista vazia
        boatLocations = new ArrayList<>();      //inicialização da lista vazia
    }
    
    //construtor da classe Board que recebe o array com os campos do tabuleiro, e o contador das linhas e colunas
    public Board(int[][] gameBoard, int[]columnCount, int[] rowCount){
        this(convertIntToTypes(gameBoard), columnCount, rowCount);
    }

    //construtor da classe Board que recebe o array com os tipos dos campos, e o contador das linhas e colunas
    public Board(Type[][] gameBoard, int[] columnCount, int[] rowCount){
        this.gameBoard = gameBoard;         //inicializa a variavel com o valor vindo do parametro
        this.columnCount = columnCount;     //inicializa a variavel com o valor vindo do parametro
        this.rowCount = rowCount;           //inicializa a variavel com o valor vindo do parametro

        dockLocations = new ArrayList<>();      //inicaliza o array vazio

        for (int i = 0; i < gameBoard.length; i++) {    //vai percorrer o array e vai guardar todas as posições de docas
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == Type.DOCK){
                    dockLocations.add(new Position(i, j));
                }
            }
        }
        boatLocations = new ArrayList<>();  
    }
    
    //metodo que converte de int para type para facilitar receção de  dados
    private static Type[][] convertIntToTypes(int[][] gameBoard){
        Type[][] convertedBoard = new Type[gameBoard.length][gameBoard[0].length]; //inicalização da varivel com um tamanho maximo do tabuleiro
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                convertedBoard[i][j] = Layout.intToType(gameBoard[i][j]);   //envio do dados para o conversor
            }
        }
        return convertedBoard;  //devolve o tabuleiro convertido
    }
    
    
    //GETS
    
    //metodo que devolve a lista com a posição dos barcos
    public List<Position> getBoatPositions() {
        return boatLocations;
    }

    //metodo que devolve a lista com a posição das docas
    public List<Position> getDockPositions() {
        return dockLocations;
    }

    //metodo que devolve a lista om a posição dos barcos em forma de array
    public Position[] getBoatPositionsAsArray(){
        return (Position[]) boatLocations.toArray();
    }

    //metodo que devolve a lista om a posição das docas em forma de array
    public Position[] getDockPositionsAsArray(){
        return (Position[]) dockLocations.toArray();
    }
    
    //devolve as linhas em array
    public int[] getRowArray() {
        return rowCount;
    }

    //devolve o tabuleiro
    public Type[][] getBoard() {
        return gameBoard;
    }

    //devolve as colunas em array
    public int[] getColumnArray() {
        return columnCount;
    }

    //devolve o tamanho do tabuleiro
    public int getBoardSize() {
        return gameBoard.length;
    }

    //devolve o tipo da posição
    public Type getType(int row, int column){
        return gameBoard[row][column];
    }

    //devolve o tipo da posição
    public Type getType(Position location) {
        return getType(location.getRow(), location.getColumn());
    }

    //devolve o tipo da posição em int
    public int getTypeAsInt(int row, int column){
        return Layout.typeToInt(getType(row,column));
    }

    //devolve o tipo da posição em int
    public  int getTypeAsInt(Position location){
        return getTypeAsInt(location.getRow(), location.getColumn());
    }
    
    //devolve a posição no array posição da linha
    public int getRowIndex(int row){
        return rowCount[row];
    }

    //devolve a posição no array posição da coluna
    public int getColumnIndex(int column){
        return columnCount[column];
    }

    //devolve o numero de barcos
    public int getBoatCount(){
        return boatLocations.size();
    }

    //devolve o numero de docas
    public int getDockCount(){
        return dockLocations.size();
    }
    
}
