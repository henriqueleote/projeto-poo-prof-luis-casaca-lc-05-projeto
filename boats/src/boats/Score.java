package boats;

import static boats.Game.menu;
import static boats.Game.players;

/**
 *
 * @author Leote (200221060)
 */
public class Score {
    int points;                 //Variavel inteira para os pontos
    int high;                   //Variavel inteira para o recorde

    //CONSTRUTOR - Define valores recebidos
    public Score(int points){
        this.points = points;
        this.high = 0;
    }
    
    //DEVOLVE OS PONTOS - A FUNCIONAR
    public int getPoints(){
        return points;
    }
    
    //DEVOLVE O RECORDE - A FUNCIONAR
    public int getHigh(){
        return high;
    }
    
    //DEFINE OS PONTOS - A FUNCIONAR
    public void setPoints(int points){
        this.points = points;
    }
    
    //DEFINE O RECORDE - A FUNCIONAR
    public void setHigh(int high){
        this.high = high;
    }
    
    //RETIRA 2 PONTOS À PONTUACAO POR FALHAR A CASA - A FUNCIONAR
    public void missedWater(){
        points-=2;
    }
    
    //RETIRA 5 PONTOS À PONTUACAO POR FALHAR A CASA - A FUNCIONAR
    public void missedBoat(){
        points-=5;
    }
    
    //RETIRA 10 PONTOS POR CADA TENTATIVA ERRADA - A FUNCIONAR
    public void missedAttempts(int attempts){
        points-=(attempts*10);
    }
    
    //RETIRA 1 PONTO POR CADA SEGUNDO EXTRA - A FUNCIONAR
    public void plusTime(int seconds){
        points-=(seconds*1);
    }
    
    //ADICIONA 1 PONTO POR CADA SEGUNDO EXTRA - A FUNCIONAR
    public void minusTime(int seconds){
        points+=(seconds*1);
    }
    
    //ADICIONA 20 PONTOS SE COMPLETAR EM UMA SÓ VALIDAÇÃO - A FUNCIONAR
    public void singleCheck(){
        points+=20;
    }
    
    public void checkRecord(){
        if(points>=high){
            high=points;
        }
    }
    
    //IMPRIME AS PONTUAÇÕES - A FUNCIONAR
    public static void printScore() {
        System.out.println("Pontuações Gerais:");
        players.forEach((n) -> System.out.println(n.getId() + "º - " + n.getNickname() + " - " + n.getScore().getPoints() + " pontos")); // É exibida no ecrã uma mensagem onde é referido o id, nickname e os pontos do utilizador
        System.out.println("Prima enter para voltar ao menu principal");
        try {
            System.in.read(); // É lida a tecla premida pelo utilizador
            menu(); // Retrocede-se ao menu
        } catch (Exception e) {
        }
    }

    
    //IMPRIME OS PONTOS DO JOGADOR - A FUNCIONAR
    public static void printPlayerScore(Player player) {
        System.out.println("Pontuações de " + player.getNickname() + ":");
        System.out.println(player.getScore().getPoints() + " pontos");
        System.out.println("Prima enter para voltar ao menu principal");
        try {
            System.in.read(); // É lida a tecla premida pelo utilizador
        } catch (Exception e) {}
        menu(); // Retrocede-se ao menu
    }
    
    //IMPRIME PARA STRING - A FUNCIONAR
    @Override
    public String toString(){
        return "\nPontos: " + getPoints() + "\nRecorde: " + getHigh();
    }
}
