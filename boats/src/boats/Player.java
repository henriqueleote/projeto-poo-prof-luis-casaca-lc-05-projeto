package boats;

import static boats.Game.input;
import static boats.Game.menu;
import static boats.Game.playerCount;
import static boats.Game.playerID;
import static boats.Game.players;
import static boats.Game.start;

/**
 *
 * @author Leote (200221060)
 */
public class Player {

    int id;                 //Variavel inteira para o ID
    String nickname;        //Variavel string para o nickname
    Score score;            //Objeto da classe score para a pontuação
    Player player;          //Objeto da classe jogador para o jogador definido

    //CONSTRUTOR - Define valores recebidos
    public Player(int id, String nickname, int score) {
        this.id = id;
        this.nickname = nickname;
        this.score = new Score(score);
    }

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
    public static Player getPlayerByNickname(String nickname) {
        for (Player playerObj : players) { // Ciclo for que percorre a coleção de jogadores
            if (playerObj.getNickname().toLowerCase().trim().equals(nickname)) { // Caso exista um jogador com o respetivo nickname, devolve-o
                return playerObj;
            }
        }
        return null; // Caso não exista, retorna o valor NULL
    }

    //CRIA UM JOGADOR - A FUNCIONAR
    public static void createPlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim(); // Leitura do nickname digitado pelo utilizador
        Player player = getPlayerByNickname(nickname); // Associação do jogador ao nickname

        if (player != null) { // Caso o nickname pretendido pelo utilizador já esteja registado na aplicação, é emitida uma mensagem de erro 
            if (players.contains(player)) {
                try {
                    throw new BoatsIllegalArgumentException(ErrorCode.NICKNAME_ALREADY_EXISTS.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else { // Caso o nickname pretendido pelo utilizador ainda não exista em utilização, é criado um novo jogador com o respetivo nickname associado
            players.add(new Player(playerCount, nickname, 0));
            playerID = players.get(playerCount).getId();
            playerCount++;
            menu(); // Retrocede-se ao menu
        }
    }

    //ESCOLHE O JOGADOR - A FUNCIONAR
    public static void choosePlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim(); // O utilizador insere o seu nickname
        Player player = getPlayerByNickname(nickname);

        if (player != null) { // Caso o nickname (jogador) exista, é extraído o index referente ao mesmo, o que carateriza a seleção do jogador a ser utilizado
            if (players.contains(player)) {
                playerID = players.indexOf(player);
                menu(); // Retrocede-se ao menu
            }
        } else { // Caso o nickname inserido não corresponda a nenhum jogador, é emitida uma mensagem de erro
            try {
                    throw new BoatsIllegalArgumentException(ErrorCode.NICKNAME_DOESNT_EXIST.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            start(); // Retrocede-se ao menu principal
        }
    }

    //IMPRIME PARA STRING - A FUNCIONAR
    @Override
    public String toString() {
        return "\nID: " + getId() + "\nNickname: " + getNickname() + getScore().toString();
    }
}
