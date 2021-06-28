package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import board.Board;
import board.Dock;
import game.Game;
import static game.Game.board;
import player.Player;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import player.Score;


/**
 *
 * @author CucasPC
 */
public class ProjectTest {
    
    private Game game;
    private Player player;
    private Board board;
    private Score score;
    
    public ProjectTest() {
        score = new Score(350);
        player = new Player(0,"Teste", score.getPoints());

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
    
    @Test
    public void testPlayer(){
        String name = "Teste";
        assertEquals(name, player.getNickname());
        assertEquals(350, player.getScore().getPoints());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
