package player;

import game.Game;
import java.util.Scanner;
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
    public void createPlayer(String nickname, Stage oldStage) {
        Player player = getPlayerByNickname(nickname); // Associação do jogador ao nickname

        if (player != null) { // Caso o nickname pretendido pelo utilizador já esteja registado na aplicação, é emitida uma mensagem de erro 
            if (game.players.contains(player)) {
            if(oldStage == null){
                System.out.println("O nickname inserido já se encontra em uso.\n");
                game.startConsole();
            }
            else
                game.createAlert("Alerta","Nickname em uso","O nickname inserido já se encontra em uso.",AlertType.ERROR); 
            }
        } else { // Caso o nickname pretendido pelo utilizador ainda não exista em utilização, é criado um novo jogador com o respetivo nickname associado
            game.players.add(new Player(game.playerCount, nickname, 0));
            game.playerID = game.players.get(game.playerCount).getId();
            game.playerCount++;
            if(oldStage != null){
                oldStage.close();                                                                                          //Fechar janela atual
                game.menuFX(oldStage);                                                                                     //Abre o menu
            }
            else
                game.menuConsole();
        }
    }
    
    //ESCOLHE O JOGADOR - A FUNCIONAR
    public void choosePlayer(String nickname, Stage oldStage) {
        Player player = getPlayerByNickname(nickname);

        if (player != null) { // Caso o nickname (jogador) exista, é extraído o index referente ao mesmo, o que carateriza a seleção do jogador a ser utilizado
            if (game.players.contains(player)) {
                game.playerID = game.players.indexOf(player);
                if(oldStage != null){
                    oldStage.close();
                    game.menuFX(oldStage); 
                }else
                    game.menuConsole(); // Retrocede-se ao menu
            }
        } else { // Caso o nickname inserido não corresponda a nenhum jogador, é emitida uma mensagem de erro
            if(oldStage == null){
                System.out.println("Não existe nenhum utilizador com o nickname inserido.\n");
                game.startConsole();
            }
            else
                game.createAlert("Alerta","Jogador desconhecido","Não existe nenhum jogador com esse nickname.",AlertType.ERROR); 
                
        }
    }

    //IMPRIME PARA STRING - A FUNCIONAR
    @Override
    public String toString() {
        return "\nID: " + getId() + "\nNickname: " + getNickname() + getScore().toString();
    }
}
