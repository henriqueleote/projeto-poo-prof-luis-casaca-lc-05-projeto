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
    int row;
    int column;
    
    public Casa(int row, int column){
        this.row=row;
        this.column=column;
    }
    
    public void setLocation(int row, int column){
        this.row=row;
        this.column=column;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
    
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        //string.append("Linha: " + row + "\n");
        //string.append("Coluna: " + column + "\n");
        return string.toString();
    }
}
