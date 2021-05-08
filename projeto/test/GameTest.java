/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projeto.Game;

/**
 *
 * @author CucasPC
 */
public class GameTest {
    
    public static Game game;    //variavel da classe Game
    
    public GameTest() {
        game = new Game(5);  //inicializar a variavel com o valore 5 para o numero de jogadores
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //Teste para mudar a variavel do numero de jogadores
    @Test
    public void testGetSetGetNumberOfPlayers(){
        game.getNumberOfPlayers();   //devolver a linha
        game.setNumberOfPlayers(25); //definir a linha
        game.getNumberOfPlayers();   //devolver a linha
    }
}
