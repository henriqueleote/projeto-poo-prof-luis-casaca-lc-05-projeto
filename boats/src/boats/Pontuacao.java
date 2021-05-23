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
}
