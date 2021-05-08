import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projeto.Position;

/**
 *
 * @author CucasPC
 */
public class PositionTest {
    
    public static Position position;    //variavel da classe Position
            
    public PositionTest() {
        position = new Position(2,3);  //inicializar a variavel com os valores 2 e 3 para linhas e colunas, respetivamente
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

    //Teste para mudar as variaveis da linha para saber se funciona
    @Test
    public void testGetSetGetRow(){
        position.getRow();  //devolver a linha
        position.setRow(9); //definir a linha
        position.getRow();  //devolver a linha
    }
    
    //Teste para mudar as variaveis da coluna para saber se funciona
    @Test
    public void testGetSetGetColumn(){
        position.getRow();  //devolver a coluna
        position.setRow(4); //definir a coluna
        position.getRow();  //devolver a coluna
    }
}
