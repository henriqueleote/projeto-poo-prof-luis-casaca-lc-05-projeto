/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

/**
 *
 * @author CucasPC
 */
public class Jogador {
    int id;
    String nickname;
    Pontuacao score;
    Jogador player;
    
    public Jogador(int id, String nickname, int score){
        this.id = id;
        this.nickname = nickname;
        this.score = new Pontuacao(score);
    }
    
    public int getId(){
        return id;
    }
    
    public String getNickname(){
        return nickname;
    }
   
    
    public Pontuacao getScore(){
        return score;
    }
    
    public Jogador getPlayer(){
        return player;
    }
    
    public void setId(){
        this.id = id;
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    public void setPlayer(Jogador player){
        this.player=player;
    }
}
