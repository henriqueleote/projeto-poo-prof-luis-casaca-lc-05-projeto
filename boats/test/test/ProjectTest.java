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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author CucasPC
 */
public class ProjectTest {
    
    private Game game;
    private Player player;
    private Board board;
    
    public ProjectTest() {
        game = new Game();
        player = new Player();
        board = new Board();
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
    public void test(){
//        game.players.add(new Player(game.playerCount,"teste",0));
//        board.generateBoard();
//        game.gameBoard.set(0, new Dock(board.getRowFromIndex(0), board.getColumnFromIndex(0)));
//        game.print();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
