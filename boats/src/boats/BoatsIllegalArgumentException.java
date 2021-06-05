package boats;

/**
 *
 * @author Leote (200221060)
 */
public class BoatsIllegalArgumentException extends Exception{

    private String message;
    ErrorCode code;
 
    public BoatsIllegalArgumentException(String msg)
    {
        this.message = msg;
    }
    //Message can be retrieved using this accessor method
    public String getMessage() {
        return message;
    }
    
    public ErrorCode getCode(){
        return code;
    }
}
