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
public class Pontuacao {
    int points;
    int high;
    int level;
    
    public Pontuacao(int points){
        this.points = points;
        this.high = 0;
    }
    
    public int getPoints(){
        return points;
    }
    
    public int getHigh(){
        return high;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public void setHigh(int high){
        this.high = high;
    }
    
    public String toString(){
        return "\nPontos: " + getPoints() + "\nRecord: " + getHigh();
    }
   
    public void gameEndVerification(int points){
        // Verificação do preenchimento do tabuleiro
        /* Caso os espaços não estejam todos corretamente preenchidos, o jogo continua.
        -> Criação da variável "int numberOfFails" que deduzirá 10 pontos ao jogador por cada tentativa falhada.
        -> Criação da variável "int pointsToDeduct", que deduzirá à pontuação final do jogador:
        -> 2 pontos por falha na validação de casas do tipo "Água"; 
        -> 5 pontos por cada segmento de um barco falhado;
        */
        
        /* Caso os espaços do tabuleiro estejam todos preenchidos corretamente, o jogo termina e a pontuação final é calculada:
        -> Criação da variável "int startingPontuation", que atribui uma pontuação base consoante o nível de dificuldade do jogo; */
    
        /* -> Criação da variável "int bonusPontuation", onde serão adicionados:
        -> 20 pontos caso jogador tenha validado o tabuleiro corretamente à primeira tentativa (numberOfFails = 0);
        -> Por cada segundo ultrapassado em relação ao tempo base, deduzir 1 ponto à pontuação final; por cada segundo que o jogador não tenha utilizado, incrementar 1 ponto à pontuação final */
        
        /* -> Criação da variável "int finalPontuation", que calculará a pontuação final do jogador através da seguinte equação: finalPontuation = startingPontuation - pointsToDeduct + bonusPontuation
        -> Se o valor da variável finalPontuation for um número negativo, colocar a variável finalPontuation com o valor 0 (int finalPontuation = 0) */
        
    }
}
