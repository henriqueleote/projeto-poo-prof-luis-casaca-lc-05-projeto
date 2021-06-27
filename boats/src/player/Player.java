package player;

import game.Game;
import player.Score;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author Leote (200221060)
 */
public class Player {

    public int id;                                                              //Variavel inteira para o ID
    
    public String nickname;                                                     //Variavel String para o nickname
    
    public static Game game = new Game();                                       //Objeto da classe Game
    public static Player player = new Player();                                 //Objeto da classe Player
    public static Score score = new Score();                                    //Objeto da classe Score
    public static Scanner input = new Scanner(System.in);                       //Objeto da classe Scanner para receber dados
    

    //CONSTRUTOR - Define valores recebidos
    public Player(int id, String nickname, int score) {
        this.id = id;
        this.nickname = nickname;
        this.score = new Score(score);
    }
    
    //CONSTRUTOR VAZIO
    public Player(){}

    //DEVOLVE O ID DO UTILIZADOR - A FUNCIONAR
    public int getId() {
        return id;
    }

    //DEVOLVE O NICKNAME DO JOGADOR - A FUNCIONAR
    public String getNickname() {
        return nickname;
    }

    //DEVOLVE A PONTUACAO DO JOGADOR - A FUNCIONAR
    public Score getScore() {
        return score;
    }

    //DEVOLVE O OBJETO DO JOGADOR - A FUNCIONAR
    public Player getPlayer() {
        return player;
    }

    //DEFINE O ID - A FUNCIONAR
    public void setId() {
        this.id = id;
    }

    //DEFINE O NICKNAME - A FUNCIONAR
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //DEFINE O OBJETO DO JOGADOR - A FUNCIONAR
    public void setPlayer(Player player) {
        this.player = player;
    }

    //VERIFICA SE EXISTE ALGUM JOGADOR COM O NICKNAME INTRODUZIDO - A FUNCIONAR
    public Player getPlayerByNickname(String nickname) {
        for (Player playerObj : game.players) { // Ciclo for que percorre a coleção de jogadores
            if (playerObj.getNickname().toLowerCase().trim().equals(nickname)) { // Caso exista um jogador com o respetivo nickname, devolve-o
                return playerObj;
            }
        }
        return null; // Caso não exista, retorna o valor NULL
    }

    //CRIA UM JOGADOR - A FUNCIONAR
    public void createPlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim(); // Leitura do nickname digitado pelo utilizador
        Player player = getPlayerByNickname(nickname); // Associação do jogador ao nickname

        if (player != null) { // Caso o nickname pretendido pelo utilizador já esteja registado na aplicação, é emitida uma mensagem de erro 
            if (game.players.contains(player)) {
                System.out.println("O nickname inserido já se encontra em uso.");
                //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//                try {
//                    throw new BoatsIllegalArgumentException(ErrorCode.NICKNAME_ALREADY_EXISTS.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        } else { // Caso o nickname pretendido pelo utilizador ainda não exista em utilização, é criado um novo jogador com o respetivo nickname associado
            game.players.add(new Player(game.playerCount, nickname, 0));
            game.playerID = game.players.get(game.playerCount).getId();
            game.playerCount++;
            game.menuConsole(); // Retrocede-se ao menuOLD
        }
    }
    
    //ESCOLHE O JOGADOR - A FUNCIONAR
    public void choosePlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim(); // O utilizador insere o seu nickname
        Player player = getPlayerByNickname(nickname);

        if (player != null) { // Caso o nickname (jogador) exista, é extraído o index referente ao mesmo, o que carateriza a seleção do jogador a ser utilizado
            if (game.players.contains(player)) {
                game.playerID = game.players.indexOf(player);
                game.menuConsole(); // Retrocede-se ao menuOLD
            }
        } else { // Caso o nickname inserido não corresponda a nenhum jogador, é emitida uma mensagem de erro
            System.out.println("Não existe nenhum utilizador com o nickname inserido.");
            //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//            try {
//                    throw new BoatsIllegalArgumentException(ErrorCode.NICKNAME_DOESNT_EXIST.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            game.startConsole(); // Retrocede-se ao menuOLD principal
        }
    }
    
    //CRIA UM JOGADOR - A FUNCIONAR (PHASE 3 COMPLETE)
    public void createPlayer(String nickname, Stage oldStage) {
        Player player = getPlayerByNickname(nickname); // Associação do jogador ao nickname

        if (player != null) { // Caso o nickname pretendido pelo utilizador já esteja registado na aplicação, é emitida uma mensagem de erro 
            if (game.players.contains(player)) {
                game.createAlert("Erro","Utilizador em uso","O nickname inserido já se encontra em uso.",AlertType.ERROR);
                //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//                try {
//                    throw new BoatsIllegalArgumentException(ErrorCode.NICKNAME_ALREADY_EXISTS.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        } else { // Caso o nickname pretendido pelo utilizador ainda não exista em utilização, é criado um novo jogador com o respetivo nickname associado
            game.players.add(new Player(game.playerCount, nickname, 0));
            game.playerID = game.players.get(game.playerCount).getId();
            game.playerCount++;
            oldStage.close();
            //game.menuOLD(); // Retrocede-se ao menuOLD
            game.menuFX(oldStage);
        }
    }
    
    //ESCOLHE O JOGADOR - A FUNCIONAR (PHASE 3 COMPLETE)
    public void choosePlayer(String nickname, Stage oldStage) {
        Player player = getPlayerByNickname(nickname);

        if (player != null) { // Caso o nickname (jogador) exista, é extraído o index referente ao mesmo, o que carateriza a seleção do jogador a ser utilizado
            if (game.players.contains(player)) {
                game.playerID = game.players.indexOf(player);
                oldStage.close();
                //game.menuOLD(); // Retrocede-se ao menuOLD
                game.menuFX(oldStage);
            }
        } else { // Caso o nickname inserido não corresponda a nenhum jogador, é emitida uma mensagem de erro
            game.createAlert("Erro","Utilizador desconhecido","Não existe nenhum utilizador com o nickname inserido.",AlertType.ERROR);
            //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//            try {
//                    throw new BoatsIllegalArgumentException(ErrorCode.NICKNAME_DOESNT_EXIST.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
        }
    }

    //IMPRIME PARA STRING - A FUNCIONAR
    @Override
    public String toString() {
        return "\nID: " + getId() + "\nNickname: " + getNickname() + getScore().toString();
    }
}
