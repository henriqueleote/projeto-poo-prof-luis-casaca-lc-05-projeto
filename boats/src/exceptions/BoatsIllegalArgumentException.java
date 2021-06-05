package exceptions;

import exceptions.ErrorCode;

/**
 *
 * @author Leote (200221060)
 */
public class BoatsIllegalArgumentException extends Exception{

    public String message;                                                      //Variavel String para a mensagem de erro
    public ErrorCode code;                                                      //Objeto da classe ErrorCode para o codigo de erro
 
    //CONSTRUTOR - Define valores recebidos
    public BoatsIllegalArgumentException(String msg){
        this.message = msg;
    }
    
    //DEVOLVE A MENSAGEM DE ERRO - A FUNCIONAR
    public String getMessage() {
        return message;
    }
    
    //DEVOLVE O CODIGO ERRO - A FUNCIONAR
    public ErrorCode getCode(){
        return code;
    }
}
