package boats;

/**
 *
 * @author Leote (200221060)
 */
public class Spot{  
    
    public Position position;                                                   //Objeto da classe Position para a posição da casa
    
    //CONSTRUTOR - Define valores recebidos
    public Spot(int x, int y){
        position = new Position(x,y);
    }
    
    //DEFINE A POSIÇÃO DA CASA - A FUNCIONAR
    public void setPosition(int x, int y){
        position.setRow(x);
        position.setColumn(y);
    }
    
    //DEVOLVE A POSIÇÃO DA CASA - A FUNCIONAR
    public Position getPosition(){
        return position;
    }
}
