package projeto;

/**
 *
 * @author Gonçalo Fernandes e Henrique Leote
 */
public class Score {
    public int highScore;           //variavel para o recorde de pontuação
    public int score;               //variavel para a pontuação
    public int fail;                //variavel para os pontos perdidos
    public int gain;                //variavel para os pontos ganhos
    
    //construtor da classe Score que recebe o score do jogador
    public Score(int score){
        this.score=score;
    }
    
    
    //metodo que adiciona 2 pontos de penalização por falha na casa validada (água) 
    public void waterFail(){    
        fail=fail+2;
    }
    
    //metodo que adiciona 5 pontos de penalização por falha na casa validada (barco)
    public void boatFail(){ 
        fail=fail+5;
    }
    
    //metodo que adiciona 10 pontos de penalização por mais do que uma tentativa de validação
    public void validateAttempt(){  
        fail=fail+10;
    }
    
    //metodo que adiciona 1 ponto de penalização por tempo de jogo para além do tempo base (1 por segundo)
    public void extraTime(int seconds){    
        fail=fail+(1*seconds);
    }
    
    //metodo que adiciona 20 pontos de bonificação por apenas uma validação
    public void singleValidate(){   
        gain=gain+20;
    }
    
    //metodo que adiciona 1 ponto de bonificação por tempo de jogo inferior ao tempo base (1 por segundo)
    public void minimumTime(int seconds){    
        gain=gain+(1*seconds);
    }
    
    //GETS
    
    //metodo que devolve a pontuação
    public int getScore(){
        return score;
    }
    //metodo que devolve o recorde de pontuação
    public int getHighScore(){
        return highScore;
    }
    //metodo que devolve os pontos perdidos
    public int getFail(){
        return fail;
    }
    //metodo que devolve os pontos ganhos
    public int getGain(){
        return gain;
    }
    
    //metodo que devolve a pontuação final
    public int getFinalScore(){    
        return score+gain-fail;
    }
    
    //SETS
    
    //metodo que altera o recorde de pontuação
    public void setHighScore(int highScore) { 
        this.highScore = highScore;
    }

    //metodo que altera a pontuação
    public void setScore(int score) {
        this.score = score;
    }

    //metodo que altera os pontos perdidos
    public void setFail(int fail) {
        this.fail = fail;
    }

    //metodo que altera os pontos ganhos
    public void setGain(int gain) {
        this.gain = gain;
    }
    
    //metodo que verifica e devolve um novo record
    public int newHighScore(){  
        if(score>=highScore){
            highScore=score;
        }
        return highScore;
    }
    
    //metodo que define todos os valores a 0
    public void resetValues(){
        score=0;
        fail=0;
        gain=0;
        highScore=0;
    }
    
    //metodo que devolve os dados da classe em string
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Pontos Ganhos: " + gain);
        str.append("Pontos Perdido: " + fail);
        str.append("Pontos Finais: " + score);
        str.append("Recorde de Pontos: " + highScore);
        return str.toString();
    }
}
