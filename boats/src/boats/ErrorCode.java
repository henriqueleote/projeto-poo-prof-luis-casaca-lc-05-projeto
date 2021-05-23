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
    ARRAY_CANT_BE_NEGATIVE,NICKNAME_ALREADY_EXISTS,NICKNAME_DOESNT_EXIST;

    @Override
    public String toString() {
        switch (this) {
            case ARRAY_CANT_BE_NEGATIVE:
                return "O array não pode conter valores negativos";
            case NICKNAME_ALREADY_EXISTS:
                return "O nickname inserido já se encontra em uso";
            case NICKNAME_DOESNT_EXIST:
                return "Não existe nenhum utilizador com o nickname inserido";
            default:
                return "desconhecido";
        }
    }
}
