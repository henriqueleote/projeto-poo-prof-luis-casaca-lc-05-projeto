import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projeto.Score;

/**
 *
 * @author CucasPC
 */
public class ScoreTest {
    
    public static Score score;
    
    public ScoreTest() {
        score = new Score(0);
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

    //Teste para mudar as variaveis da do score para saber se funciona
    @Test
    public void testGetSetGetPrintScore(){
        score.getScore();             //devolver pontuação
        score.singleValidate();       //atribuir 20 pontos
        score.minimumTime(30);        //atribuir 30 pontos
        score.boatFail();             //penalizar 5 pontos
        score.getFinalScore();        //calcular a pontuação final
        score.toString();             //devolver todos os dados
    }
}
