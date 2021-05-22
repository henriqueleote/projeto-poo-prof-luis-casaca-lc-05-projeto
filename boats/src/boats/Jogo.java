/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CucasPC
 */
public class Jogo{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        create();
    }

    public static void create() {
        ArrayList<Casa> board = new ArrayList<Casa>();
        for (int i = 0; i < 36; i++) {
            board.add(new Desconhecido(2, 1));
        }
        //posição=row*coluna
        for (int i = 0; i < 6; i++) {
            for (int j= 0; j < 6; j++) {
                System.out.print(board.get(i) + " ");
            }
            System.out.println();
        }
    }

}
