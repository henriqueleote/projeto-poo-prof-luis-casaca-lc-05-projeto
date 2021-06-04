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
public class Dock extends Spot{

    char indentifier;
    
    public Dock(int x, int y){
        super(x,y);
        position = new Position(x,y);
        indentifier = 'P';
    }
    
    public char getIndentifier(){
        return indentifier;
    }

    @Override
    public String toString(){
        return Character.toString(getIndentifier());
    }
    
}
