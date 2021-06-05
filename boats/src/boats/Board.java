package boats;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Leote (200221060)
 */
public class Board {
    
    public static int i;                                                        //Variavel inteira do contador i
    public static int j;                                                        //Variavel inteira do contador j
    
    public static Scanner input = new Scanner(System.in);                       //Objeto da classe Scanner para receber dados
    public static Game game = new Game();                                       //Objeto da classe Game
    
    
    //CONSTRUTOR VAZIO
    public Board(){}
   
    
    //MENU PARA ESCOLHA DE DIFICULDADE - REVER
    public void chooseDificulty() {
        Game game = new Game();
        
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
                int[] array = {5, 7, 10};    //array com os 3 tabuleiros possiveis
                int index = new Random().nextInt(array.length);    //escolhe aleatoriamente um tabuleiro                
                if(array[index] == 5){ //caso seja o 5 o escolhido, gera um tabuleiro facil
                    game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_EASY;
                    game.NUMBER_OF_ROWS = 4;
                    game.NUMBER_OF_COLUMNS = 4;
                    generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    //setTimer(30)
                    game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                }
                if(array[index] == 7){ //caso seja o 7 o escolhido, gera um tabuleiro medio
                    game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_MEDIUM;
                    game.NUMBER_OF_ROWS = 6;
                    game.NUMBER_OF_COLUMNS = 6;
                    generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    //setTimer(90)
                    game.players.get(game.playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                }
                if(array[index] == 10){    //caso seja o 10 o escolhido, gera um tabuleiro dificil
                    game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_HARD;
                    game.NUMBER_OF_ROWS = 9;
                    game.NUMBER_OF_COLUMNS = 9;
                    generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    //setTimer(150)
                    game.players.get(game.playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                }
                break;
            case 2: // Criação de um tabuleiro de game de dificuldade "Fácil"
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_EASY;
                game.NUMBER_OF_ROWS = 4;
                game.NUMBER_OF_COLUMNS = 4;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(30)
                game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                break;
            case 3: // Criação de um tabuleiro de game de dificuldade "Médio"
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_MEDIUM;
                game.NUMBER_OF_ROWS = 6;
                game.NUMBER_OF_COLUMNS = 6;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(90)
                game.players.get(game.playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                break;
            case 4: // Criação de um tabuleiro de game de dificuldade "Difícil"
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_HARD;
                game.NUMBER_OF_ROWS = 9;
                game.NUMBER_OF_COLUMNS = 9;
                generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                //setTimer(150)
                game.players.get(game.playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                break;
            case 0:
                game.start(); // Retrocede-se ao menu principal
                break;
            default:
                chooseDificulty(); // Caso não seja selecionada nenhuma das opções disponíveis, é exibido novamente o menu de  escolha de dificuldade
                break;
        }
    }
    
    //GERA O TABULEIRO DE JOGO
    public void generateBoard() {
        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_EASY) { // Caso a dificuldade definida seja "Fácil", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_EASY; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                    game.unknown.add(getIndex(i, j)); // Atribuição do estado "Unknown" à posição referente às cordenadas anteriores
                }
            }
        }

        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_MEDIUM) { // Caso a dificuldade definida seja "Médio", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_MEDIUM; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_MEDIUM; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                    game.unknown.add(getIndex(i, j)); // Atribuição do estado "Unknown" à posição referente às cordenadas anteriores
                }
            }
        }

        if (game.SET_DIFFICULTY == game.DIFFICULTY_BOARD_HARD) { // Caso a dificuldade definida seja "Difícil", cria um tabuleiro de game com base nas propriedades dessa mesma dificuldade
            for (i = 0; i < game.DIFFICULTY_BOARD_HARD; i++) {
                for (j = 0; j < game.DIFFICULTY_BOARD_HARD; j++) {
                    game.gameBoard.add(new Unknown(i, j)); // Adiciona posições do tipo "Unknown" ao tabuleiro, com as suas respetivas coordenadas
                    game.unknown.add(getIndex(i, j)); // Atribuição do estado "Unknown" à posição referente às cordenadas anteriores
                }
            }
        }
        game.placeDock(); // Colocação de portos no tabuleiro de game
    }
    
    
    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS E A CASA - REVER
    public int getIndexFromPlace(int x, int y, char indentifier) {
        int arrayNumber = -1;
        for (i = 0; i < game.gameBoard.size(); i++) { // Ciclo for que percorre as posições do tabuleiro
            if (game.gameBoard.get(i).getPosition().getRow() == x && game.gameBoard.get(i).getPosition().getColumn() == y && game.gameBoard.get(i)  instanceof Unknown) {
                arrayNumber = i; //verificar porque começa em 0
            }
        }
        return arrayNumber; // retorna o índice da coordenada e da casa inserida
    }



    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS
    public int getIndex(int x, int y) {
        int arrayNumber = -1;
        for (Spot boardObj : game.gameBoard) { // Ciclo for que percorre as posições do tabuleiro
            if (boardObj.getPosition().getRow() == x && boardObj.getPosition().getColumn() == y) { // Caso o objeto casa possua uma coordenada X e Y, guarda a posição dessa mesma casa
                arrayNumber = game.gameBoard.indexOf(boardObj); // Variável onde é guardado o valor relativo à posição da casa
            }
        }
        return arrayNumber; // Retorna a posição no array da casa
    }

    //DEVOLVE AS COORDENADAS (x) DADA A POSIÇÃO NO ARRAYLIST
    public int getRowFromIndex(int arrayNumber) {
        int position = game.gameBoard.get(arrayNumber).getPosition().getRow(); // Guarda a coordenada X referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

    //DEVOLVE AS COORDENADAS (y) DADA A POSIÇÃO NO ARRAYLIST
    public int getColumnFromIndex(int arrayNumber) {
        int position = game.gameBoard.get(arrayNumber).getPosition().getColumn(); // Guarda a coordenada Y referente à posição (casa) recebida por parâmetro pelo método (arrayNumber)
        return position; // Retorna a coordenada guardada na variável position
    }

}