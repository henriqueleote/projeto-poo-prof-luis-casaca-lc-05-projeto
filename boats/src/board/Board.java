package board;

import game.Game;
import static game.Game.SET_DIFFICULTY;
import static game.Game.attempts;
import static game.Game.board;
import static game.Game.boat;
import static game.Game.docks;
import static game.Game.gameBoard;
import static game.Game.i;
import static game.Game.playerID;
import static game.Game.players;
import static game.Game.rules;
import static game.Game.score;
import java.util.Random;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Leote (200221060)
 */
public class Board {
    
    public int i;                                                        //Variavel inteira do contador i
    public int j;                                                        //Variavel inteira do contador j
    
    public static Scanner input = new Scanner(System.in);                       //Objeto da classe Scanner para receber dados
    public static Game game = new Game();                                       //Objeto da classe Game
    
    
    //CONSTRUTOR VAZIO
    public Board(){}
    
    //REINICIA TODOS OS VALORES
    public void resetValue(){
        game.gameBoard.clear();
        game.docks.clear();
        game.boat.clear();
        game.buttons.clear();
        //game.getPlayers().get(game.getPlayerID()).getScore().setPoints(0); // Atribuição de 50 pontos iniciais ao jogador
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
    
    //COLOCA OS PORTOS NO TABULEIRO DE JOGO - A FUNCIONAR
    public void placeDock() {
        while (docks.size() < SET_DIFFICULTY) { // Enquanto a quantidade de docas no tabuleiro for menor do que a pré-estabelecida pela dificuldade, adiciona uma doca numa posição aleatória
            int random = new Random().nextInt(gameBoard.size()); // Adição de uma doca aleatória
            if (!docks.contains(random)) { // Adiciona uma doca aleatória caso ainda não exista nenhuma                
                if (rules.checkSpotForDock(board.getRowFromIndex(random), board.getColumnFromIndex(random))) {
                    docks.add(random);
                }
                gameBoard.set(random, new Dock(board.getRowFromIndex(random), board.getColumnFromIndex(random)));
            }
        }
    }
    
        //VALIDA O TABULEIRO NO FIM DO JOGO - A FUNCIONAR
    public void endValidateBoard(int seconds) {
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
            players.get(playerID).getScore().singleCheck();
        players.get(playerID).getScore().missedAttempts(attempts);
        players.get(playerID).getScore().checkRecord();
        game.createAlert("Jogo Terminado", "Jogo Terminado", "Esqueceu-se de preencher " + count + " casas no tabuleiro.\n"
                    + "Demorou " + seconds + " segundos a terminar o jogo.\nTeve uma pontuação de "
                            + "" + players.get(playerID).getScore().getPoints() + " pontos.", Alert.AlertType.WARNING);
        placeRemainWater();
        //game.printConsole();
        //game.printEndConsole();
    }
    
        //transforma em agua no fim do jogo - A FUNCIONAR
    public void placeRemainWater() {
        for (i = 0; i < gameBoard.size(); i++) { // Ciclo for que cria objetos do tipo "Água" no tabuleiro
            if (gameBoard.get(i) instanceof Unknown) {
                gameBoard.set(i, new Water(gameBoard.get(i).getPosition().getRow(), gameBoard.get(i).getPosition().getColumn()));
            }
        }
    }

    //COLOCA A AGUA NO TABULEIRO DE JOGO - A FUNCIONAR
    public void placeWater(int arrayNumber) {
        if (gameBoard.get(arrayNumber) instanceof Dock || gameBoard.get(arrayNumber) instanceof Boat || gameBoard.get(arrayNumber) instanceof Water) { // Caso a posição verificada já possua um estado que não seja "Desconhecido, é emitida uma mensagem
            score.missedWater();
            System.out.println("Não pode colocar agua num lugar que não se encontra desconhecido.");
            //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//            try {
//                throw new BoatsIllegalArgumentException(ErrorCode.NO_WATER_UNKNOWN.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } else {
            gameBoard.set(arrayNumber, new Water(gameBoard.get(arrayNumber).getPosition().getRow(), gameBoard.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Água", com as coordenadas correspondentes à posição a ser alterada
        }
    }

    //COLOCA OS BARCOS NO TABULEIRO DE JOGO - A FUNCIONAR
    public void placeBoat(int arrayNumber) {
        gameBoard.set(arrayNumber, new Boat(gameBoard.get(arrayNumber).getPosition().getRow(), gameBoard.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Boat", com as coordenadas correspondentes à posição a ser alterada
        boat.add(arrayNumber); // Adição do objeto do tipo "Boat" no array que guarda as posições do tipo "Boat"
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