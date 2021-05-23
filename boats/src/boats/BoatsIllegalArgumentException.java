/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

/**
 *
 * @author CucasPC
 */
public class BoatsIllegalArgumentException extends IllegalArgumentException {
    
    ErrorCode code;
    
    public BoatsIllegalArgumentException(ErrorCode code){
        super();
    }
    
    public ErrorCode getCode(){
        return code;
    }
}
