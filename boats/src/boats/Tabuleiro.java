/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Jogo.SET_DIFFICULTY;
import static boats.Jogo.board;
import static boats.Jogo.i;
import static boats.Jogo.input;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author CucasPC
 */
public class Tabuleiro {
    
    public static Jogo jogo;
   
    //MENU PARA ESCOLHA DE DIFICULDADE - REVER
    public static void chooseDificulty() {
        System.out.println("Escolha a dificuldade:");
        System.out.println();
        System.out.println("1 - Iniciar jogo aleatório");
        System.out.println("2 - Iniciar jogo – Fácil");
        System.out.println("3 - Iniciar jogo – Médio");
        System.out.println("4 - Iniciar jogo - Difícil");
        System.out.println("0 - Voltar\n");

        System.out.print("Opção -> ");
        while (!input.hasNextInt()) {
            System.out.print("-> ");
            input.next();
        }
        int option = input.nextInt(); // É escolhida uma opção pelo utilizador
        System.out.println("-----------");

        switch (option) { // Verificação da opção escolhida pelo utilizador
            case 1:
                // ! TODO !
                break;
            case 2: // Criação de um tabuleiro de jogo de dificuldade "Fácil"
                SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;
                NUMBER_OF_ROWS = 4;
                NUMBER_OF_COLUMNS = 4;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(30)
                players.get(playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                break;
            case 3: // Criação de um tabuleiro de jogo de dificuldade "Médio"
                SET_DIFFICULTY = DIFFICULTY_BOARD_MEDIUM;
                NUMBER_OF_ROWS = 6;
                NUMBER_OF_COLUMNS = 6;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(90)
                players.get(playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                break;
            case 4: // Criação de um tabuleiro de jogo de dificuldade "Difícil"
                SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;
                NUMBER_OF_ROWS = 9;
                NUMBER_OF_COLUMNS = 9;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(150)
                players.get(playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                break;
            case 0:
                start(); // Retrocede-se ao menu principal
                break;
            default:
                chooseDificulty(); // Caso não seja selecionada nenhuma das opções disponíveis, é exibido novamente o menu de  escolha de dificuldade
                break;
        }
    }
    
    //GERA O TABULEIRO DE JOGO
    public static void generateBoard() {
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_EASY) { // Caso a dificuldade definida seja "Fácil", cria um tabuleiro de jogo com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_EASY; j++) {
                    board.add(new Desconhecido(i, j)); // Adiciona posições do tipo "Desconhecido" ao tabuleiro, com as suas respetivas coordenadas
                    unknown.add(getIndex(i, j)); // Atribuição do estado "Desconhecido" à posição referente às cordenadas anteriores
                }
            }
        }

        if (SET_DIFFICULTY == DIFFICULTY_BOARD_MEDIUM) { // Caso a dificuldade definida seja "Médio", cria um tabuleiro de jogo com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < DIFFICULTY_BOARD_MEDIUM; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_MEDIUM; j++) {
                    board.add(new Desconhecido(i, j)); // Adiciona posições do tipo "Desconhecido" ao tabuleiro, com as suas respetivas coordenadas
                    unknown.add(getIndex(i, j)); // Atribuição do estado "Desconhecido" à posição referente às cordenadas anteriores
                }
            }
        }

        if (SET_DIFFICULTY == DIFFICULTY_BOARD_HARD) { // Caso a dificuldade definida seja "Difícil", cria um tabuleiro de jogo com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < DIFFICULTY_BOARD_HARD; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_HARD; j++) {
                    board.add(new Desconhecido(i, j)); // Adiciona posições do tipo "Desconhecido" ao tabuleiro, com as suas respetivas coordenadas
                    unknown.add(getIndex(i, j)); // Atribuição do estado "Desconhecido" à posição referente às cordenadas anteriores
                }
            }
        }
        placeDock(); // Colocação de portos no tabuleiro de jogo
    }
    
    
    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS E A CASA - REVER
    public static int getIndexFromPlace(int x, int y, char indentifier) {
        int arrayNumber = -1;
        for (i = 0; i < board.size(); i++) { // Ciclo for que percorre as posições do tabuleiro
            if (board.get(i).getPosition().getRow() == x && board.get(i).getPosition().getColumn() == y && board.get(i).toString().equals("-")) {
                arrayNumber = i; //verificar porque começa em 0
            }
        }
        return arrayNumber; // retorna o índice da coordenada e da casa inserida
    }



    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS
    public static int getIndex(int x, int y) {
        int arrayNumber = -1;
        for (Casa boardObj : board) { // Ciclo for que percorre as posições do tabuleiro
            if (boardObj.getPosition().getRow() == x && boardObj.getPosition().getColumn() == y) { // Caso o objeto casa possua uma coordenada X e Y, guarda a posição dessa mesma casa
                arrayNumber = board.indexOf(boardObj); // Variável onde é guardado o valor relativo à posição da casa
            }
        }
        return arrayNumber; // Retorna a posição no array da casa
    }

    //DEVOLVE AS COORDENADAS (x) DADA A POSIÇÃO NO ARRAYLIST
    public static int getRowFromIndex(int arrayNumber) {
        int position = board.get(arrayNumber).getPosition().getRow(); // Guarda a coordenada X referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

    //DEVOLVE AS COORDENADAS (y) DADA A POSIÇÃO NO ARRAYLIST
    public static int getColumnFromIndex(int arrayNumber) {
        int position = board.get(arrayNumber).getPosition().getColumn(); // Guarda a coordenada Y referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

}