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
public enum ErrorCode {
    ARRAY_GONE,ARRAY_CANT_BE_NEGATIVE,NICKNAME_ALREADY_EXISTS,NICKNAME_DOESNT_EXIST,BOAT_CANT_POSITION,POSITION_OCCUPIED,NO_WATER_UNKNOWN,NO_FUNCTION;

    @Override
    public String toString() {
        switch (this) {
            case ARRAY_CANT_BE_NEGATIVE:
                return "O array não pode conter valores negativos.";
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
