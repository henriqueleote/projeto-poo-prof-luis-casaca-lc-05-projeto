package exceptions;

/**
 *
 * @author Leote (200221060)
 */
public enum ErrorCode {
    
    ARRAY_CANT_BE_NEGATIVE,                                                     //Erro nº1
    NICKNAME_ALREADY_EXISTS,                                                    //Erro nº2
    NICKNAME_DOESNT_EXIST,                                                      //Erro nº3
    BOAT_CANT_POSITION,POSITION_OCCUPIED,                                       //Erro nº4
    NO_WATER_UNKNOWN,                                                           //Erro nº5
    NO_FUNCTION;                                                                //Erro nº6

    @Override
    public String toString() {
        switch (this) {
            case NICKNAME_ALREADY_EXISTS:
                return "O nickname inserido já se encontra em uso.";
            case NICKNAME_DOESNT_EXIST:
                return "Não existe nenhum utilizador com o nickname inserido.";
            case BOAT_CANT_POSITION:
                return "Não pode colocar um barco nessa posição.";
            case POSITION_OCCUPIED:
                return "Essa posição já se encontra ocupada.";
            case NO_WATER_UNKNOWN:
                return "Não pode colocar agua num lugar que não se encontra desconhecido.";
            case NO_FUNCTION:
                return "Funcionalidade indisponivel. Obrigado.";
            default:
                return "Desconhecido";
        }
    }
}
