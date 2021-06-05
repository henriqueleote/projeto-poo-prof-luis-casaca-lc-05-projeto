package boats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Leote (200221060)
 */
public class Game {

    public static int DIFFICULTY_BOARD_EASY = 5;
    public static int DIFFICULTY_BOARD_MEDIUM = 7;
    public static int DIFFICULTY_BOARD_HARD = 10;
    public static int SET_DIFFICULTY = 0;
    public static int NUMBER_OF_ROWS = 0;
    public static int NUMBER_OF_COLUMNS = 0;
    public static int i, j, playerCount;
    public static List<Spot> gameBoard = new ArrayList<Spot>();
    public static List<Integer> forbiden = new ArrayList<Integer>();
    public static List<Player> players = new ArrayList<Player>();
    public static List<Integer> docks = new ArrayList<Integer>();
    public static List<Integer> water = new ArrayList<Integer>();
    public static List<Integer> boat = new ArrayList<Integer>();
    public static List<Integer> unknown = new ArrayList<Integer>();
    public static Scanner input = new Scanner(System.in);
    public static int playerID;
    public static Score score;
    public static int attempts;

    //MAIN
    public static void main(String[] args) {
        start(); //Inicializa o programa, exibindo o menu principal
    }

    
    //IMPRIME O MENU PRINCIPAL - A FUNCIONAR
    public static void start() {
        System.out.println("Seja bem-vindo ao Docks & Boats\n");
        System.out.println("Escolha uma opção");
        System.out.println();

        //Verifica se a lista de jogadores está vazia
        if (players.isEmpty()) {
            System.out.println("1 - Criar Jogador");
            System.out.println("0 - Sair\n");

            //Exibe a opção escolhida pelo utilizador
            System.out.print("Opção -> ");
            while (!input.hasNextInt()) {
                System.out.print("-> ");
                input.next();
            }
            int option = input.nextInt();
            System.out.println("-----------");

            //Consoante a opção escolhida, é criado um novo jogador ou é terminado o programa
            switch (option) {
                case 1:
                    Player.createPlayer();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    start(); // no caso de não ser escolhida nenhuma das opções facultadas ao utilizador, volta ao menu principal
                    break;
            }
        } else {
            System.out.println("1 - Criar Jogador");
            System.out.println("2 - Escolher jogador");
            System.out.println("0 - Sair\n");

            System.out.print("Opção -> ");
            while (!input.hasNextInt()) {
                System.out.print("-> ");
                input.next();
            }
            int option = input.nextInt();
            System.out.println("-----------");

            switch (option) {
                case 1:
                    Player.createPlayer();
                    break;
                case 2:
                    Player.choosePlayer();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    //
                    break;
            }
        }
    }
    
    
    //IMPRIME O MENU - A FUNCIONAR
    public static void menu() {
        System.out.println("----------------------------------\n");
        System.out.println("Olá " + players.get(playerID).getNickname());
        System.out.println("Escolha uma opção");
        System.out.println();
        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Carregar Jogo");
        System.out.println("3 - Pontuações Pessoais");
        System.out.println("4 - Pontuações Gerais");
        System.out.println("5 - Criação de Níveis");
        System.out.println("6 - Ajuda");
        System.out.println("7 - Voltar");
        System.out.println("0 - Sair\n");

        System.out.print("Opção -> ");
        while (!input.hasNextInt()) { //É selecionada a opção desejada pelo utilizador
            System.out.print("-> ");
            input.next();
        }
        int option = input.nextInt();
        System.out.println("-----------");

        switch (option) { // Verifica qual a opção escolhida pelo utilizador
            case 1:
                Board.chooseDificulty(); // Redireciona o utilizar para o menu de escolha da dificuldade do jogo a ser jogado
                break;
            case 2:
                try {
                    throw new BoatsIllegalArgumentException(ErrorCode.NO_FUNCTION.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                menu();
                break;
            case 3:
                Score.printPlayerScore(players.get(playerID)); // Exibe as pontuações pessoais do utilizador
                break;
            case 4:
                Score.printScore(); // Exibe a pontuação geral dos jogadores 
                break;
            case 5:
                try {
                    throw new BoatsIllegalArgumentException(ErrorCode.NO_FUNCTION.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                menu();
                break;
            case 6:
                printHelp(); // O utilizador é redirecionado para um menu de ajuda à compreensão das diferentes siglas inerentes ao jogo
                break;
            case 7:
                start(); // Retrocede-se ao menu principal
                break;
            case 0:
                System.exit(0); // O programa é finalizado
                break;
            default:
                //
                break;
        }
    }


    //IMPRIME AJUDA PARA O JOGO - A FUNCIONAR
    public static void printHelp() { // Exibe a legenda para as siglas do jogo
        System.out.println("Legenda:");
        System.out.println(".: Água");
        System.out.println("(Vazio): Desconhecido");
        System.out.println("P: Porto");
        System.out.println("B: Barco");
        System.out.println("-------------------");

        // Criação de um tabuleiro ilustrativo de tamanho 5x5
        System.out.println("Exemplo de Posições 5x5:\n");
        for (i = 0; i < 5; i++) {
            System.out.print("\t");
            System.out.print("|");
            for (j = 0; j < 5; j++) {
                System.out.print("-");
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("\nPara jogar introduza o tipo de casa (B) e em seguida a posição (2,3)"); // Exibição de uma mensagem onde se explica o conceito de introdução de casas no tabuleiro ao jogador
        System.out.println("Prima enter para voltar ao menu principal");
        try {
            System.in.read(); // É lida a tecla premida pelo utilizador
        } catch (Exception e) {}
        menu(); // Retrocede-se ao menu
    }
    
    public static void printEnd(){
        System.out.println("O jogo terminou, e você terminou com " + players.get(playerID).getScore().getPoints() + " pontos");
        System.out.println("Prima enter para sair do jogo.");
        try {
            System.in.read(); // É lida a tecla premida pelo utilizador
        } catch (Exception e) {}
        System.exit(0);// Retrocede-se ao menu
    }

    
    //IMPRIME O TABULEIRO DE JOGO - A FUNCIONAR
    public static void print() {
        System.out.println("\nTabuleiro de Jogo:");
        for (i = 0; i < gameBoard.size(); i++) { // Criação do tabuleiro de jogo
            System.out.print("|");
            System.out.print(gameBoard.get(i).toString());
            
            if (gameBoard.get(i).getPosition().getColumn() == (NUMBER_OF_COLUMNS)) { // É criado um tabuleiro correspondente à dificuldade selecionada
                System.out.print("|");
                System.out.println();
            }
        }
        play();
    }

    
    //JOGADAS DO UTILIZADOR - \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TODO
    public static void play() {
        System.out.println("-----------");
        //por condição de caso ele escreva -1, acaba o jogo, x termina e por assim adiante
        System.out.print("Campo (A ou B) (escreva \"sair\" para sair)-> ");
        /*while (!input.toString().toUpperCase().trim().equals("A") && !input.toString().toUpperCase().trim().equals("B") || input.toString().length() != 1) {
            if(input.next().equals("sair"))
                System.exit(0);
            else
            System.out.print("-> ");
        }*/
        String move = input.next().trim();

        System.out.print("Linha (x) -> ");
//        while (!input.hasNextInt() || input.nextInt() >= 0 || input.nextInt() <= NUMBER_OF_ROWS) {
//            System.out.print("-> ");
//        }
        int x = input.nextInt();
        System.out.print("Coluna (y) -> ");
//        while (!input.hasNextInt() || input.nextInt() >= 0 || input.nextInt() <= NUMBER_OF_ROWS) {
//            System.out.print("-> ");
//        }
        int y = input.nextInt();
        int position = Board.getIndex(x, y);
        if (!(gameBoard.get(position) instanceof Dock) && !(gameBoard.get(position) instanceof Boat)) {
            if (move.equals("B")) {
                if (Rules.checkSpotForBoat(x, y)) {
                    placeBoat(position);
                } else {
                    score.missedBoat();
                    attempts++;
                    try {
                        throw new BoatsIllegalArgumentException(ErrorCode.BOAT_CANT_POSITION.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (move.equals("A")) {
                placeWater(position);
            }
        } else {
            attempts++;
            try {
                throw new BoatsIllegalArgumentException(ErrorCode.POSITION_OCCUPIED.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        print();
        //FALTAM VALIDAÇÕES        
        //CASO PONHA NO SITIO ERRADO, PORQUITOS
    }

    
    //VALIDA O TABULEIRO NO FIM DO JOGO
    public static void endValidateBoard(){
        int count=0;
        for (i = 0; i < gameBoard.size(); i++) { // Ciclo for que cria objetos do tipo "Água" no tabuleiro
            if (gameBoard.get(i) instanceof Dock) {
                if(!(gameBoard.get(Board.getIndex(Board.getRowFromIndex(i), Board.getColumnFromIndex(i)-1)) instanceof Boat) && !(gameBoard.get(Board.getIndex(Board.getRowFromIndex(i)-1, Board.getColumnFromIndex(i))) instanceof Boat) && !(gameBoard.get(Board.getIndex(Board.getRowFromIndex(i), Board.getColumnFromIndex(i)+1)) instanceof Boat) && !(gameBoard.get(Board.getIndex(Board.getRowFromIndex(i)+1, Board.getColumnFromIndex(i))) instanceof Boat))
                  count++;
            }
        }
        if(count==0)
            score.singleCheck();
        else
            System.out.println("Esqueceu-se de preencher" + count + " lugares no tabuleiro");
        score.missedAttempts(attempts);
        score.checkRecord();
        placeRemainWater();
        print();
        printEnd();
    }

    
    //COLOCA OS PORTOS NO TABULEIRO DE JOGO - A FUNCIONAR
    public static void placeDock() {
        while (docks.size() < SET_DIFFICULTY) { // Enquanto a quantidade de docas no tabuleiro for menor do que a pré-estabelecida pela dificuldade, adiciona uma doca numa posição aleatória
            int random = new Random().nextInt(gameBoard.size()); // Adição de uma doca aleatória
            if (!docks.contains(random)) { // Adiciona uma doca aleatória caso ainda não exista nenhuma                
                if(Rules.checkSpotForDock(Board.getRowFromIndex(random),Board.getColumnFromIndex(random)))
                    docks.add(random);
                    gameBoard.set(random, new Dock(Board.getRowFromIndex(random), Board.getColumnFromIndex(random)));
            }
        }
        
        print(); // Exibe o tabuleiro de jogo
    }


    //transforma em agua no fim do jogo - A FUNCIONAR
    public static void placeRemainWater() {
        for (i = 0; i < gameBoard.size(); i++) { // Ciclo for que cria objetos do tipo "Água" no tabuleiro
            if (gameBoard.get(i) instanceof Unknown) {
                gameBoard.set(i, new Water(gameBoard.get(i).getPosition().getRow(), gameBoard.get(i).getPosition().getColumn()));
            }
        }
    }

    
    //COLOCA A AGUA NO TABULEIRO DE JOGO - A FUNCIONAR
    public static void placeWater(int arrayNumber) {
        if (gameBoard.get(arrayNumber) instanceof Dock || gameBoard.get(arrayNumber) instanceof Boat || gameBoard.get(arrayNumber) instanceof Water) { // Caso a posição verificada já possua um estado que não seja "Desconhecido, é emitida uma mensagem
            score.missedWater();
            try {
                throw new BoatsIllegalArgumentException(ErrorCode.NO_WATER_UNKNOWN.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            gameBoard.set(arrayNumber, new Water(gameBoard.get(arrayNumber).getPosition().getRow(), gameBoard.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Água", com as coordenadas correspondentes à posição a ser alterada
            water.add(arrayNumber); // Adição do objeto do tipo "Água" no array que guarda as posições do tipo "Água"
        }
    }

    //COLOCA OS BARCOS NO TABULEIRO DE JOGO - A FUNCIONAR
    public static void placeBoat(int arrayNumber) {
            gameBoard.set(arrayNumber, new Boat(gameBoard.get(arrayNumber).getPosition().getRow(), gameBoard.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Boat", com as coordenadas correspondentes à posição a ser alterada
            boat.add(arrayNumber); // Adição do objeto do tipo "Boat" no array que guarda as posições do tipo "Boat"
    }
}
