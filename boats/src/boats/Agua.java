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
public class Agua extends Casa{

    char indentifier;
    
    public Agua(int x, int y){
        super(x,y);
        position = new Posicao(x,y);
        indentifier = 'A';
    }
    
    public char getIndentifier(){
        return indentifier;
    }

    @Override
    public String toString(){
        return Character.toString(getIndentifier());
    }
    
}
