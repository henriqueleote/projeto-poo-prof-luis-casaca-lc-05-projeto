/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author CucasPC
 */
public class Score {
    int highScore;
    int score;
    int fail;
    int gain;
    
    public Score(int score){
        this.score=score;
    }
    
    public void waterFail(){    //adiciona 2 pontos de penalização por falha na casa validada (água) 
        fail=fail+2;
    }
    
    public void boatFail(){ //adiciona 5 pontos de penalização por falha na casa validada (barco)
        fail=fail+5;
    }
    
    public void validateAttempt(){  //adiciona 10 pontos de penalização por mais do que uma tentativa de validação
        fail=fail+10;
    }
    
    public void extraTime(int seconds){    //adiciona 1 ponto de penalização por tempo de jogo para além do tempo base (1 por segundo)
        fail=fail+(1*seconds);
    }
    
    public void singleValidate(){   //adiciona 20 pontos de bonificação por apenas uma validação
        gain=gain+20;
    }
    
    public void minimumTime(int seconds){    //adiciona 1 ponto de bonificação por tempo de jogo inferior ao tempo base (1 por segundo)
        gain=gain+(1*seconds);
    }
    
    public int finalScore(){    //devolve a pontuação final
        return score+gain-fail;
    }
    
    public int newHighScore(){  //verifica um novo record
        if(score>=highScore){
            highScore=score;
        }
        return highScore;
    }
    
    public void resetValues(){  //define todos os valores a 0
        score=0;
        fail=0;
        gain=0;
        highScore=0;
    }
}
