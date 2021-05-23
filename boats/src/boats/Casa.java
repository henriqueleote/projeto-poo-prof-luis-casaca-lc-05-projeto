/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

/**
 * @author CucasPC
 */

public class Casa{  
    Posicao position;
    
    public Casa(int x, int y){
        position = new Posicao(x,y);
    }
    
    public void setPosition(int x, int y){
        position.setRow(x);
        position.setColumn(y);
    }
    
    public Posicao getPosition(){
        return position;
    }
}
