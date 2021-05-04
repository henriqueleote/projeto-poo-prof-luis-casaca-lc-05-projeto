package projeto;

/**
 *
 * @author CucasPC
 */
public class Player {
    
    public String nickname;
    public int score;
    public int highScore;
    public int DEFAULT_VALUE = 0;
    public int numberOfGames;
    
    
    //Construtor sem parâmetros
    public Player(){
        this.nickname = "";
        this.score = DEFAULT_VALUE;
        this.highScore = DEFAULT_VALUE;
        this.numberOfGames = DEFAULT_VALUE;        
    }
    
    //Construtor com parâmetros
    public Player(String nickname){
        this.nickname = nickname;
        this.score = DEFAULT_VALUE;
        this.numberOfGames = DEFAULT_VALUE;
    }
    
    //GETS
    public String getNickname(){  //Devolve o nickname do Jogador
        return nickname;
    }
    
    public int getScore(){  //Devolve o Score
        return score;
    }
    
    public int getHighScore(){  //Devolve o HighScore
        return highScore;
    }
    
    public int getNumberOfGames(){  //Devolve o numero de jogos
        return numberOfGames;
    }
    
    //SETS
    public void setNickname(String nickname){    //Define o nickname to jogador
        this.nickname = nickname;
    }
    
    public void setScore(int score){    //Define o score
        this.score = score;
    }
    
    public void setHighScore(int score){    //Define um novo HighScore
        if(score>=this.highScore){
            this.highScore=score;
        }
    }
    
    //to String
    @Override
    public String toString(){   //Devolve em String os dados do jogador
        return "Nickname: " + this.nickname + "\nCurrent score: " + this.score + "\nGames: " + this.numberOfGames + "\nHighScore: " + this.highScore;
    }
}
