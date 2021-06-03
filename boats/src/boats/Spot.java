/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

/**
 * @author CucasPC
 */

public class Spot{  
    Position position;
    
    public Spot(int x, int y){
        position = new Position(x,y);
    }
    
    public void setPosition(int x, int y){
        position.setRow(x);
        position.setColumn(y);
    }
    
    public Position getPosition(){
        return position;
    }
}
