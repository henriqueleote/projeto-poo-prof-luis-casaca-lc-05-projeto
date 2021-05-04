/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Scanner;

/**
 *
 * @author CucasPC
 */
public class Projeto {

   
    public static Scanner input;
    
    public static void main(String[] args) {
        
        input = new Scanner(System.in); 
        
        Game game = new Game(2);
        game.start();
        
    }
}
