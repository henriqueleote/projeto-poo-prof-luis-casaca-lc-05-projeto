/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Game.input;
import static boats.Game.menu;
import static boats.Game.playerCount;
import static boats.Game.playerID;
import static boats.Game.players;
import static boats.Game.start;

/**
 *
 * @author CucasPC
 */
public class Player {
    int id;
    String nickname;
    Score score;
    Player player;
    
    public Player(int id, String nickname, int score){
        this.id = id;
        this.nickname = nickname;
        this.score = new Score(score);
    }
    
    public int getId(){
        return id;
    }
    
    public String getNickname(){
        return nickname;
    }
   
    
    public Score getScore(){
        return score;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void setId(){
        this.id = id;
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    public void setPlayer(Player player){
        this.player=player;
    }
    
        //VERIFICA SE EXISTE ALGUM JOGADOR COM O NICKNAME INTRODUZIDO
    public static Player getPlayerByNickname(String nickname) {
        for (Player playerObj : players) { // Ciclo for que percorre a coleção de jogadores
            if (playerObj.getNickname().toLowerCase().trim().equals(nickname)) { // Caso exista um jogador com o respetivo nickname, devolve-o
                return playerObj;
            }
        }
        return null; // Caso não exista, retorna o valor NULL
    }
    
        //CRIA UM JOGADOR - REVER JUST IN CASE (TRATAR DA CENA DOS PONTOS)
    public static void createPlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim(); // Leitura do nickname digitado pelo utilizador
        Player player = getPlayerByNickname(nickname); // Associação do jogador ao nickname

        if (player != null) { // Caso o nickname pretendido pelo utilizador já esteja registado na aplicação, é emitida uma mensagem de erro 
            if (players.contains(player)) {
                System.out.println("O nickname inserido já se encontra em uso\n");
            }
        } else { // Caso o nickname pretendido pelo utilizador ainda não exista em utilização, é criado um novo jogador com o respetivo nickname associado
            players.add(new Player(playerCount, nickname, 0));
            playerID = players.get(playerCount).getId();
            playerCount++;
            menu(); // Retrocede-se ao menu
        }
    }

    //ESCOLHE O JOGADOR - REVER JUST IN CASE (TRATAR DA CENA DOS PONTOS)
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
            System.out.println("Não existe nenhum utilizador com o nickname inserido\n");
            start(); // Retrocede-se ao menu principal
        }
    }
    
    @Override
    public String toString(){
        return "\nID: " + getId() + "\nNickname: " + getNickname() + getScore().toString();
    }
}
