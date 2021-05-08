package projeto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gonçalo Fernandes e Henrique Leote
 */
public class Layout {
    
    public static Random random;                            //variavel para gerar valores
    public static Position tempDock;                        //variavel para a posição da doca no tabuleiro
    public static Position tempBoat;                        //variavel para a posição do barco no tabuleiro
    public static int fillingSpaces;                        //variavel para contar quanto espaços a ocupar
    public static List<Position> validBoatPlacements;       //variavel para armazenar todas as possiveis posições do barco
    public static List<Position> nextPlacements;            //variavel para armazenar todas as posições que são desconhecidas
    public static List<Position> adjacent;                  //variavel para armazenar todas as posições junto de uma posição especifica 
    public static  List<Position> neighbors;                //variavel para armazenar todas as posições à volta de uma posição especifica 
    
    //metodo para gerar um tabuleiro aleatorio com um tamanho pedido
    public static Board generateBoard(int size) {
        Type[][] newBoard = new Type[size][size];   //inicializar o objeto tamanho pedido no menu
        random = new Random();   //inicializar o objeto
        int[] newRowCount = new int[size];     //inicializa array para as linhas com o tamanho pedido no menu
        int[] newColumnCount = new int[size];  //inicializa array para as colunas com o tamanho pedido no menu
        fillingSpaces = (size * size) / 4;   //inicializa a variavel sendo um quarto do tamanho do tabuleiro

        //enche todos com desconhecidos
        for (Type[] row: newBoard){ //percorre o array
            Arrays.fill(row, Type.UNKNOWN); //e define todos os campos para desconhecidos
        }
        
        for (int i = 0; i < fillingSpaces; i++) { //ciclo repetitivo
            tempDock = new Position(random.nextInt(size), random.nextInt(size));    //inicializa a variavel com um valor aleatorio no tabuleiro

            if (newBoard[tempDock.getRow()][tempDock.getColumn()] == Type.UNKNOWN) {    //se a posição selecionada for "Desconhecida", devolve uma possivel posição para o barco
                
                tempBoat = getRandomValidBoatNext(new Board(newBoard, null, null), tempDock);

                //Se a posição anterior foi válida, mete no tabuleiro a doca e o barco
                if (tempBoat != null) {
                    newBoard[tempDock.getRow()][tempDock.getColumn()] = Type.DOCK;  //insere a doca no tabuleiro
                    newBoard[tempBoat.getRow()][tempBoat.getColumn()] = Type.BOAT;  //insere o barco no tabuleiro
                }
            }
        }
        return new Board(newBoard, newColumnCount, newRowCount);    //devolve o novo tabuleiro com as novas posições
    }

    //metodo para converter do enum para int para facilitar na passagem de dados
    public static int typeToInt(Type input){
        switch (input){
            case DOCK:
                return -1;
            case UNKNOWN:
                return 0;
            case WATER:
                return 1;
            case BOAT:
                return 2;
            default:
                throw new IllegalArgumentException("Erro.");
        }
    }

    //metodo para converter de int para o enum para facilitar na receção de dados
    static Type intToType(int input){
        switch (input){
            case -1:
                return Type.DOCK;
            case 0:
                return Type.UNKNOWN;
            case 1:
                return Type.WATER;
            case 2:
                return Type.BOAT;
            default:
                throw new IllegalArgumentException("Erro");
        }
    }

    //metodo que devolve uma posição valida para se pôr um barco junto a uma boca
    private static Position getRandomValidBoatNext(Board gameBoard, Position location) {

        validBoatPlacements = new ArrayList<>();    //inicializa o arraylist vazio

        nextPlacements = filterPositions(gameBoard, getNextAdjacent(gameBoard, location), Type.UNKNOWN); //armazena todas as posições que são desconhecidas

        if (nextPlacements.size() == 0) {   //caso não haja posições desconhecidas
            return null;    //devolve resultado nulo
        }
      
        for (Position position: nextPlacements) {   //percorre todas as posições
            if (!hasNextType(gameBoard, position, Type.BOAT)){//caso não tenha um barco junto da posição, é uma posição válida
                validBoatPlacements.add(position);
            }
        }

        if (validBoatPlacements.size() == 0) {     //caso não haja posições para barcos
            return null;    //devolve resultado nulo
        }

        return validBoatPlacements.get(new Random().nextInt(validBoatPlacements.size()));   //devolve o numero de posições de barcos validas
    }

    //metodo que devolve true caso haja pelos menos uma localização com o tipo pedido
    private static boolean hasNextType(Board gameBoard, Position location, Type type) {
        return (filterPositions(gameBoard, getNext(gameBoard, location), type).size() != 0);
    }

    
    //metodo que devolve true caso haja pelos menos uma localização com o tipo pedido, -1 se for uma doca, 0 se for desconhecido, 1 se for agua e 2 se for barco
    private static boolean hasAdjacentOfType(Board gameBoard, Position location, Type type) {
        return (filterPositions(gameBoard, getNextAdjacent(gameBoard, location), type).size() != 0);
    }

    //metodo que devolve uma lista de posições junto da posição forneceida
    private static List<Position> getNextAdjacent(Board gameBoard, Position location){
        adjacent = new ArrayList<>(); //inicializa o arraylist vazio
        int row = location.getRow();            //inicizaliza a variavel com a linha
        int column = location.getColumn();      //inicizaliza a variavel com a coluna

        adjacent.add(new Position(row, column - 1)); // E - Esquerda
        adjacent.add(new Position(row - 1, column)); // N - Cima
        adjacent.add(new Position(row, column + 1)); // O - Direita
        adjacent.add(new Position(row + 1, column)); // S - Sul

        return filterPositions(gameBoard, adjacent);  //devolve a lista de posições
    }

   //metodo que devolve uma lista de posições à volta da posição forneceida
    private static List<Position> getNext(Board gameBoard, Position location){
        neighbors = new ArrayList<>();    //inicializa o arraylist vazio
        int row = location.getRow();            //inicizaliza a variavel com a linha
        int column = location.getColumn();      //inicizaliza a variavel com a coluna

        neighbors.add(new Position(row, column - 1));        // O - Esquerda
        neighbors.add(new Position(row - 1, column - 1));  // NO - Cima Esquerda
        neighbors.add(new Position(row - 1, column));        // N - Cima
        neighbors.add(new Position(row - 1, column + 1));  // NE - Cima Direita
        neighbors.add(new Position(row, column + 1));        // E - Direita
        neighbors.add(new Position(row + 1, column + 1));  // SE - Baixo Direito
        neighbors.add(new Position(row + 1, column));        // S - Baixo
        neighbors.add(new Position(row + 1, column - 1));  // SO - Baixo Esquerda

        return filterPositions(gameBoard, neighbors);   //devolve a lista de posições
    }


    //metodo que devolve true se a posição pedida estiver dentro dos limites do jogo
    private static boolean validLocation(Board gameBoard, Position location) {
        int row = location.getRow();            //inicizaliza a variavel com a linha
        int column = location.getColumn();      //inicizaliza a variavel com a coluna
        return (row >= 0 && row < gameBoard.getBoardSize() && column >= 0 && column < gameBoard.getBoardSize());    //devolve verdadeiro ou falso
    }


    //metodo que devolve true se a posição pedida estiver dentro dos limites do jogo com o tipo pedido, -1 se for uma doca, 0 se for desconhecido, 1 se for agua e 2 se for barco
    private static boolean validLocationType(Board gameBoard, Position location, Type type){
        int row = location.getRow();            //inicizaliza a variavel com a linha
        int column = location.getColumn();      //inicizaliza a variavel com a coluna
        return (validLocation(gameBoard, location) && gameBoard.getType(row, column) == type);     //devolve verdadeiro ou falso
    }

    //metodo que devolve uma lista com todas as posições removidas
    private static List<Position> filterPositions(Board gameBoard, List<Position> list){
        list.removeIf(c -> !validLocation(gameBoard, c));
        return list;
    }

    //metodo que devolve uma lista com todas posições e tipo, -1 se for uma doca, 0 se for desconhecido, 1 se for agua e 2 se for barco
    private static List<Position> filterPositions(Board gameBoard, List<Position> list, Type type){
        list.removeIf(c -> gameBoard.getType(c.getRow(), c.getColumn()) != type);
        return list;
    }
}
