/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Game.SET_DIFFICULTY;
import static boats.Game.input;
import java.util.Random;

/**
 *
 * @author CucasPC
 */
public class Board {
    
    public static int i,j;
   
    //MENU PARA ESCOLHA DE DIFICULDADE - REVER
    public static void chooseDificulty() {
        System.out.println("Escolha a dificuldade:");
        System.out.println();
        System.out.println("1 - Iniciar Jogo aleatório");
        System.out.println("2 - Iniciar Jogo – Fácil");
        System.out.println("3 - Iniciar Jogo – Médio");
        System.out.println("4 - Iniciar Jogo - Difícil");
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
                int[] intArray = {5, 7, 10};    //array com os 3 tabuleiros possiveis
                int idx = new Random().nextInt(intArray.length);    //escolhe aleatoriamente um tabuleiro                
                if(intArray[idx] == 5){ //caso seja o 5 o escolhido, gera um tabuleiro facil
                    SET_DIFFICULTY = Game.DIFFICULTY_BOARD_EASY;
                    Game.NUMBER_OF_ROWS = 4;
                    Game.NUMBER_OF_COLUMNS = 4;
                    generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    //setTimer(30)
                    Game.players.get(Game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                }
                if(intArray[idx] == 7){ //caso seja o 7 o escolhido, gera um tabuleiro medio
                    SET_DIFFICULTY = Game.DIFFICULTY_BOARD_MEDIUM;
                    Game.NUMBER_OF_ROWS = 6;
                    Game.NUMBER_OF_COLUMNS = 6;
                    generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    //setTimer(90)
                    Game.players.get(Game.playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                }
                if(intArray[idx] == 10){    //caso seja o 10 o escolhido, gera um tabuleiro dificil
                    SET_DIFFICULTY = Game.DIFFICULTY_BOARD_HARD;
                    Game.NUMBER_OF_ROWS = 9;
                    Game.NUMBER_OF_COLUMNS = 9;
                    generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    //setTimer(150)
                    Game.players.get(Game.playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                }
                break;
            case 2: // Criação de um tabuleiro de Game de dificuldade "Fácil"
                SET_DIFFICULTY = Game.DIFFICULTY_BOARD_EASY;
                Game.NUMBER_OF_ROWS = 4;
                Game.NUMBER_OF_COLUMNS = 4;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(30)
                Game.players.get(Game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                break;
            case 3: // Criação de um tabuleiro de Game de dificuldade "Médio"
                SET_DIFFICULTY = Game.DIFFICULTY_BOARD_MEDIUM;
                Game.NUMBER_OF_ROWS = 6;
                Game.NUMBER_OF_COLUMNS = 6;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(90)
                Game.players.get(Game.playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                break;
            case 4: // Criação de um tabuleiro de Game de dificuldade "Difícil"
                SET_DIFFICULTY = Game.DIFFICULTY_BOARD_HARD;
                Game.NUMBER_OF_ROWS = 9;
                Game.NUMBER_OF_COLUMNS = 9;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(150)
                Game.players.get(Game.playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                break;
            case 0:
                Game.start(); // Retrocede-se ao menu principal
                break;
            default:
                chooseDificulty(); // Caso não seja selecionada nenhuma das opções disponíveis, é exibido novamente o menu de  escolha de dificuldade
                break;
        }
    }
    
    //GERA O TABULEIRO DE JOGO
    public static void generateBoard() {
        if (SET_DIFFICULTY == Game.DIFFICULTY_BOARD_EASY) { // Caso a dificuldade definida seja "Fácil", cria um tabuleiro de Game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < Game.DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < Game.DIFFICULTY_BOARD_EASY; j++) {
                    Game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                    Game.unknown.add(getIndex(i, j)); // Atribuição do estado "Unknown" à posição referente às cordenadas anteriores
                }
            }
        }

        if (SET_DIFFICULTY == Game.DIFFICULTY_BOARD_MEDIUM) { // Caso a dificuldade definida seja "Médio", cria um tabuleiro de Game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < Game.DIFFICULTY_BOARD_MEDIUM; i++) {
                for (j = 0; j < Game.DIFFICULTY_BOARD_MEDIUM; j++) {
                    Game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                    Game.unknown.add(getIndex(i, j)); // Atribuição do estado "Unknown" à posição referente às cordenadas anteriores
                }
            }
        }

        if (SET_DIFFICULTY == Game.DIFFICULTY_BOARD_HARD) { // Caso a dificuldade definida seja "Difícil", cria um tabuleiro de Game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < Game.DIFFICULTY_BOARD_HARD; i++) {
                for (j = 0; j < Game.DIFFICULTY_BOARD_HARD; j++) {
                    Game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                    Game.unknown.add(getIndex(i, j)); // Atribuição do estado "Unknown" à posição referente às cordenadas anteriores
                }
            }
        }
        Game.placeDock(); // Colocação de portos no tabuleiro de Game
    }
    
    
    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS E A CASA - REVER
    public static int getIndexFromPlace(int x, int y, char indentifier) {
        int arrayNumber = -1;
        for (i = 0; i < Game.gameBoard.size(); i++) { // Ciclo for que percorre as posições do tabuleiro
            if (Game.gameBoard.get(i).getPosition().getRow() == x && Game.gameBoard.get(i).getPosition().getColumn() == y && Game.gameBoard.get(i)  instanceof Unknown) {
                arrayNumber = i; //verificar porque começa em 0
            }
        }
        return arrayNumber; // retorna o índice da coordenada e da casa inserida
    }



    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS
    public static int getIndex(int x, int y) {
        int arrayNumber = -1;
        for (Spot boardObj : Game.gameBoard) { // Ciclo for que percorre as posições do tabuleiro
            if (boardObj.getPosition().getRow() == x && boardObj.getPosition().getColumn() == y) { // Caso o objeto casa possua uma coordenada X e Y, guarda a posição dessa mesma casa
                arrayNumber = Game.gameBoard.indexOf(boardObj); // Variável onde é guardado o valor relativo à posição da casa
            }
        }
        return arrayNumber; // Retorna a posição no array da casa
    }

    //DEVOLVE AS COORDENADAS (x) DADA A POSIÇÃO NO ARRAYLIST
    public static int getRowFromIndex(int arrayNumber) {
        int position = Game.gameBoard.get(arrayNumber).getPosition().getRow(); // Guarda a coordenada X referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

    //DEVOLVE AS COORDENADAS (y) DADA A POSIÇÃO NO ARRAYLIST
    public static int getColumnFromIndex(int arrayNumber) {
        int position = Game.gameBoard.get(arrayNumber).getPosition().getColumn(); // Guarda a coordenada Y referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

}