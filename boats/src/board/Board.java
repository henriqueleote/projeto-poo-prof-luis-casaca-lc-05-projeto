package board;

import game.Game;
import static game.Game.players;
import game.Rules;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Leote (200221060)
 */
public class Board {
    
    public int i;                                                               //Variavel inteira do contador i
    public int j;                                                               //Variavel inteira do contador j
    
    public static Scanner input = new Scanner(System.in);                       //Objeto da classe Scanner para receber dados
    public static Game game = new Game();                                       //Objeto da classe Game
    public static Rules rules = new Rules();                                    //Objeto da classe Rules
    
    
    //CONSTRUTOR VAZIO
    public Board(){}
    
    //REINICIA TODOS OS VALORES
    public void resetValue(){
        game.gameBoard.clear();
        game.docks.clear();
        game.boat.clear();
        game.buttons.clear();
        game.players.get(game.playerID).getScore().setPoints(0);
    }
     
    //GERA O TABULEIRO DE JOGO
    public void generateBoard() {
        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_EASY) { // Caso a dificuldade definida seja "Fácil", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_EASY; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                }
            }
        }

        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_MEDIUM) { // Caso a dificuldade definida seja "Médio", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_MEDIUM; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_MEDIUM; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                }
            }
        }

        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_HARD) { // Caso a dificuldade definida seja "Difícil", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_HARD; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_HARD; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                }
            }
        }
        placeDock(); // Colocação de portos no tabuleiro de game
    }
    
    //CRIA O TABULEIRO DE JOGO
    public void createBoard(ArrayList<Integer> arrayDocks) {
        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_EASY) { // Caso a dificuldade definida seja "Fácil", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_EASY; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                }
            }
        }
        for(i=0;i<game.gameBoard.size();i++){
            game.gameBoard.set(arrayDocks.get(i), new Dock(getRowFromIndex(arrayDocks.get(i)), getColumnFromIndex(arrayDocks.get(i))));
        }
        arrayDocks.forEach((n) -> System.out.println(n));
        game.playConsole();
    }
    
    //COLOCA OS PORTOS NO TABULEIRO DE JOGO - A FUNCIONAR
    public void placeDock() {
        while (game.docks.size() < game.SET_DIFFICULTY) { // Enquanto a quantidade de docas no tabuleiro for menor do que a pré-estabelecida pela dificuldade, adiciona uma doca numa posição aleatória
            int random = new Random().nextInt(game.gameBoard.size()); // Adição de uma doca aleatória
            if (!game.docks.contains(random)) { // Adiciona uma doca aleatória caso ainda não exista nenhuma                
                if (rules.checkSpotForDock(getRowFromIndex(random), getColumnFromIndex(random))) {
                    game.docks.add(random);
                }
                game.gameBoard.set(random, new Dock(getRowFromIndex(random), getColumnFromIndex(random)));
            }
        }
    }
    
    //VALIDA O TABULEIRO NO FIM DO JOGO - A FUNCIONAR
    public void endValidateBoard(int timer) {
        int count = 0;
//        for (i = 0; i < gameBoard.size(); i++) { //Verifica se algum campo à volta ficou por validar
//            if (gameBoard.get(i) instanceof Dock) {
//                if ((!(gameBoard.get(board.getIndex(board.getRowFromIndex(i), board.getColumnFromIndex(i) - 1)) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i), board.getColumnFromIndex(i) - 1) >= 0 && board.getIndex(board.getRowFromIndex(i), board.getColumnFromIndex(i) - 1) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i) - 1)) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i) - 1) >= 0 && board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i) - 1) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i))) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i)) >= 0 && board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i)) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i) + 1)) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i) + 1) >= 0 && board.getIndex(board.getRowFromIndex(i) - 1, board.getColumnFromIndex(i) + 1) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i), board.getColumnFromIndex(i) + 1)) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i), board.getColumnFromIndex(i) + 1) >= 0 && board.getIndex(board.getRowFromIndex(i), board.getColumnFromIndex(i) + 1) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i) + 1)) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i) + 1) >= 0 && board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i) + 1) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i))) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i)) >= 0 && board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i)) < gameBoard.size()))
//                        && (!(gameBoard.get(board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i) - 1)) instanceof Boat) || (board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i) - 1) >= 0 && board.getIndex(board.getRowFromIndex(i) + 1, board.getColumnFromIndex(i) - 1) < gameBoard.size()))) {
//                    count++;
//                }
//            }
//        }
        if (count == 0)
            game.players.get(game.playerID).getScore().singleCheck();
        if(timer != -9999)   //no caso do jogo sem timer
            game.players.get(game.playerID).getScore().plusTime(0);
        if(timer<0)
            game.players.get(game.playerID).getScore().minusTime(timer);
        if(timer>0)
            game.players.get(game.playerID).getScore().plusTime(timer);
        
        game.players.get(game.playerID).getScore().missedAttempts(game.attempts);
        game.players.get(game.playerID).getScore().checkRecord();
        placeRemainWater();
    }
    
    //TRANSFORMA OS RESTANTES EM AGUA - A FUNCIONAR
    public void placeRemainWater() {
        for (i = 0; i < game.gameBoard.size(); i++) { // Ciclo for que cria objetos do tipo "Água" no tabuleiro
            if (game.gameBoard.get(i) instanceof Unknown) {
                game.gameBoard.set(i, new Water(game.gameBoard.get(i).getPosition().getRow(), game.gameBoard.get(i).getPosition().getColumn()));
            }
        }
    }

    //COLOCA A AGUA NO TABULEIRO DE JOGO - A FUNCIONAR
    public void placeWater(int arrayNumber) {
        if (game.gameBoard.get(arrayNumber) instanceof Dock || game.gameBoard.get(arrayNumber) instanceof Boat || game.gameBoard.get(arrayNumber) instanceof Water) { // Caso a posição verificada já possua um estado que não seja "Desconhecido, é emitida uma mensagem
            game.players.get(game.playerID).getScore().missedWater();
        } else {
            game.gameBoard.set(arrayNumber, new Water(game.gameBoard.get(arrayNumber).getPosition().getRow(), game.gameBoard.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Água", com as coordenadas correspondentes à posição a ser alterada
        }
    }

    //COLOCA OS BARCOS NO TABULEIRO DE JOGO - A FUNCIONAR
    public void placeBoat(int arrayNumber) {
        game.gameBoard.set(arrayNumber, new Boat(game.gameBoard.get(arrayNumber).getPosition().getRow(), game.gameBoard.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Boat", com as coordenadas correspondentes à posição a ser alterada
        game.boat.add(arrayNumber); // Adição do objeto do tipo "Boat" no array que guarda as posições do tipo "Boat"
    }
        
    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS
    public int getIndex(int x, int y) {
        int arrayNumber = -1;
        for (Spot boardObj : game.gameBoard) { // Ciclo for que percorre as posições do tabuleiro
            if (boardObj.getPosition().getRow() == x && boardObj.getPosition().getColumn() == y) { // Caso o objeto casa possua uma coordenada X e Y, guarda a posição dessa mesma casa
                arrayNumber = game.gameBoard.indexOf(boardObj); // Variável onde é guardado o valor relativo à posição da casa
            }
        }
        return arrayNumber; // Retorna a posição no array da casa
    }

    //DEVOLVE AS COORDENADAS (x) DADA A POSIÇÃO NO ARRAYLIST
    public int getRowFromIndex(int arrayNumber) {
        int position = game.gameBoard.get(arrayNumber).getPosition().getRow(); // Guarda a coordenada X referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

    //DEVOLVE AS COORDENADAS (y) DADA A POSIÇÃO NO ARRAYLIST
    public int getColumnFromIndex(int arrayNumber) {
        int position = game.gameBoard.get(arrayNumber).getPosition().getColumn(); // Guarda a coordenada Y referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

}