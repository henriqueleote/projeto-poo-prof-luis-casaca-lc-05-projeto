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
public class Barco extends Casa{
    public Barco(int row, int column){
        super(row,column);
    }
    
    @Override
    public String toString(){
        return(super.toString() + "Tipo: Barco");
    }
}