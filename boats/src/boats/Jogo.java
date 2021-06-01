/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author CucasPC
 */
public class Jogo {

    public static int DIFFICULTY_BOARD_EASY = 5;
    public static int DIFFICULTY_BOARD_MEDIUM = 7;
    public static int DIFFICULTY_BOARD_HARD = 10;
    public static int SET_DIFFICULTY = 0;
    public static int NUMBER_OF_ROWS = 0;
    public static int NUMBER_OF_COLUMNS = 0;
    public static int i, j, playerCount;
    public static List<Casa> board = new ArrayList<Casa>();
    public static List<Integer> forbiden = new ArrayList<Integer>();
    public static List<Jogador> players = new ArrayList<Jogador>();
    public static List<Integer> docks = new ArrayList<Integer>();
    public static List<Integer> water = new ArrayList<Integer>();
    public static List<Integer> boat = new ArrayList<Integer>();
    public static List<Integer> unknown = new ArrayList<Integer>();
    public static Regras rules = new Regras();
    public static Scanner input = new Scanner(System.in);
    public static int playerID;
    public static Jogador player;

    //MAIN
    public static void main(String[] args) {
        start(); //Inicializa o programa, exibindo o menu principal
    }

    //IMPRIME O MENU PRINCIPAL - FEITO MAS FALTA COMENTARIO
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
                    createPlayer();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    start(); // no caso de não ser escolhida nenhuma das opções facultadas ao utilizador, volta ao menu principal
                    break;
            }
        } else { // ! POSSÍVEL REIMPLEMENTAÇÃO, DEIXADO PARA DEPOIS !
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
                    createPlayer();
                    break;
                case 2:
                    choosePlayer();
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
    
    //IMPRIME O MENU - /////////////////////////////////////////////////////////TODO
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
                chooseDificulty(); // Redireciona o utilizar para o menu de escolha da dificuldade do jogo a ser jogado
                break;
            case 2:
                // ! TODO !
                break;
            case 3:
                printPlayerScore(players.get(playerID)); // Exibe as pontuações pessoais do utilizador
                break;
            case 4:
                printScore(); // Exibe a pontuação geral dos jogadores 
                break;
            case 5: // ! TODO !
                System.out.println("Funcionalidade indisponivel. Obrigado");
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


    //IMPRIME AJUDA PARA O JOGO
    public static void printHelp() { // Exibe a legenda para as siglas do jogo
        System.out.println("Legenda:");
        System.out.println("A: Água");
        System.out.println("-: Desconhecido");
        System.out.println("P: Porto");
        System.out.println("B: Barco");
        System.out.println("-------------------");

        // Criação de um tabuleiro ilustrativo de tamanho 5x5
        System.out.println("Exemplo de Posições 5x5:\n");
        for (i = 0; i < 5; i++) {
            System.out.print("\t");
            for (j = 0; j < 5; j++) {
                System.out.print("- ");
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




    //IMPRIME O TABULEIRO DE JOGO
    public static void print() {
        System.out.println("\nTabuleiro de Jogo:");
        for (i = 0; i < board.size(); i++) { // Criação do tabuleiro de jogo
            System.out.print(board.get(i).toString() + " ");
            if (board.get(i).getPosition().getColumn() == (NUMBER_OF_COLUMNS)) { // É criado um tabuleiro correspondente à dificuldade selecionada
                System.out.println();
            }
        }
        play();
    }

    //JOGADAS DO UTILIZADOR - \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TODO
    public static void play() {
        System.out.println("-----------");
        //por condição de caso ele escreva -1, acaba o jogo, x termina e por assim adiante
        System.out.print("Campo (A ou B) -> ");
        while (!input.equals("A") || !input.equals("B") || input.toString().length() != 1) {
            System.out.print("-> ");
            input.next().trim().charAt(1);
        }
        String move = input.next().trim();
        System.out.print("Linha (x) -> ");
        while (!input.hasNextInt() || input.nextInt() >= 0 || input.nextInt() <= NUMBER_OF_ROWS) {
            System.out.print("-> ");
            input.next().charAt(1);
        }
        int x = input.nextInt();
        System.out.print("Coluna (y) -> ");
        while (!input.hasNextInt() || input.nextInt() >= 0 || input.nextInt() <= NUMBER_OF_ROWS) {
            System.out.print("-> ");
            input.next().charAt(1);
        }
        int y = input.nextInt();
        int position = getIndex(x, y);
        //FALTAM VALIDAÇÕES
        if (move.equals("B")) {
            placeBoat(position);
        }
        if (move.equals("A")) {
            placeWater(position);
        }
        //CASO PONHA NO SITIO ERRADO, PORQUITOS
    }

    //DEFINE TODAS AS VARIAVEIS COM O SEU VALOR INICIAL 
    public static void clearValues() {
        DIFFICULTY_BOARD_EASY = 5;
        DIFFICULTY_BOARD_MEDIUM = 7;
        DIFFICULTY_BOARD_HARD = 10;
        SET_DIFFICULTY = 0;
        NUMBER_OF_ROWS = 0;
        NUMBER_OF_COLUMNS = 0;
        i = 0;
        j = 0;
        playerCount = 0;
        board.clear();
        forbiden.clear();
        players.clear();
        docks.clear();
        water.clear();
        boat.clear();
        unknown.clear();
        playerID = -1;
        player = null;
    }

    //COLOCA OS PORTOS NO TABULEIRO DE JOGO - REVER
    public static void placeDock() { // REVER
        while (docks.size() < SET_DIFFICULTY) { // Enquanto a quantidade de docas no tabuleiro for menor do que a pré-estabelecida pela dificuldade, adiciona uma doca numa posição aleatória
            int random = new Random().nextInt(board.size()); // Adição de uma doca aleatória
            if (!docks.contains(random)) { // Adiciona uma doca aleatória caso ainda não exista nenhuma                
                if(checkBounds(getRowFromIndex(random) - 1, getColumnFromIndex(random) - 1) && checkEmpty(getIndex(getRowFromIndex(random) - 1, getColumnFromIndex(random) - 1)))
                    if(checkBounds(getRowFromIndex(random) - 1, getColumnFromIndex(random)) && checkEmpty(getIndex(getRowFromIndex(random) - 1, getColumnFromIndex(random))))
                        if(checkBounds(getRowFromIndex(random) - 1, getColumnFromIndex(random) + 1) && checkEmpty(getIndex(getRowFromIndex(random) - 1, getColumnFromIndex(random) + 1)))
                            if(checkBounds(getRowFromIndex(random), getColumnFromIndex(random) - 1) && checkEmpty(getIndex(getRowFromIndex(random), getColumnFromIndex(random) - 1)))
                                if(checkBounds(getRowFromIndex(random), getColumnFromIndex(random) + 1) && checkEmpty(getIndex(getRowFromIndex(random), getColumnFromIndex(random) + 1)))
                                    if(checkBounds(getRowFromIndex(random) + 1, getColumnFromIndex(random) - 1) && checkEmpty(getIndex(getRowFromIndex(random) + 1, getColumnFromIndex(random) - 1)))
                                        if(checkBounds(getRowFromIndex(random) + 1, getColumnFromIndex(random)) && checkEmpty(getIndex(getRowFromIndex(random) + 1, getColumnFromIndex(random))))
                                            if(checkBounds(getRowFromIndex(random) + 1, getColumnFromIndex(random) + 1) && checkEmpty(getIndex(getRowFromIndex(random) + 1, getColumnFromIndex(random) + 1)))
                                                docks.add(random);
            }
        }

        for (i = 0; i < SET_DIFFICULTY; i++) {
            board.set(docks.get(i), new Porto(getRowFromIndex(docks.get(i)), getColumnFromIndex(docks.get(i))));
        }

        print(); // Exibe o tabuleiro de jogo

        //docks.forEach((n) -> System.out.println(n));  //DEVOLVE PARA O ECRÃ AS POSIÇÕES DOS PORTOS NUM ARRAYLIST
    }

    public static boolean checkEmpty(int arrayNumber) {
        boolean state = false;
        if (board.get(arrayNumber).toString().equals("-")) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    public static boolean checkBounds(int x, int y) {
        if (x >= 0 && x < NUMBER_OF_ROWS && y >= 0 && y < NUMBER_OF_COLUMNS) {
            return true;
        } else {
            return false;
        }
    }

    //transforma em agua no fim do jogo
    public static void placeRemainWater() {
        for (i = 0; i < board.size(); i++) { // Ciclo for que cria objetos do tipo "Água" no tabuleiro
            if (board.get(i).toString().equals("-")) {
                board.set(i, new Agua(board.get(i).getPosition().getRow(), board.get(i).getPosition().getColumn()));
            }
        }
    }

    //COLOCA A AGUA NO TABULEIRO DE JOGO - REVER
    public static void placeWater(int arrayNumber) {
        if (board.get(arrayNumber).toString().equals("P") || board.get(arrayNumber).toString().equals("B") || board.get(arrayNumber).toString().equals("A")) { // Caso a posição verificada já possua um estado que não seja "Desconhecido, é emitida uma mensagem
            System.out.println("Não pode colocar agua num lugar que não se encontra desconhecido.");
        } else {
            board.set(arrayNumber, new Agua(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Água", com as coordenadas correspondentes à posição a ser alterada
            water.add(arrayNumber); // Adição do objeto do tipo "Água" no array que guarda as posições do tipo "Água"
        }
    }

    //COLOCA OS BARCOS NO TABULEIRO DE JOGO - \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TODO
    public static void placeBoat(int arrayNumber) {
        if (board.get(arrayNumber).toString().equals("P")) { // Caso a posição verificada já possua o estado "Porto", emite uma mensagem de erro
            System.out.println("Não pode colocar um barco onde está um porto de atracagem");
        } else {
            board.set(arrayNumber, new Barco(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn())); // Criação de um objeto do tipo "Barco", com as coordenadas correspondentes à posição a ser alterada
            boat.add(arrayNumber); // Adição do objeto do tipo "Barco" no array que guarda as posições do tipo "Barco"

            /*if (board.get(arrayNumber).toString().equals("-") || board.get(arrayNumber).toString().equals("A")) {
            //validar se pode ser posto naquele sitio
            //caso haja um porto à esquerda
            if (board.get(arrayNumber).getPosition().getRow() > 0 && board.get(arrayNumber).getPosition().getColumn() - 1 > 0 && board.get(arrayNumber).toString().equals("-")) {
                board.set(arrayNumber, new Barco(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn()));
                print();
            } //caso haja um porto em cima
            else if (board.get(arrayNumber).getPosition().getRow() - 1 > 0 && board.get(arrayNumber).getPosition().getColumn() > 0 && board.get(arrayNumber).toString().equals("-")) {
                board.set(arrayNumber, new Barco(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn()));
                print();
            } //caso haja um porto à direita
            else if (board.get(arrayNumber).getPosition().getRow() > 0 && board.get(arrayNumber).getPosition().getColumn() < DIFFICULTY_BOARD_EASY - 1 && board.get(arrayNumber).toString().equals("-")) {
                board.set(arrayNumber, new Barco(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn()));
                print();
            } //caso haja um porto em baixo
            else if (board.get(arrayNumber).getPosition().getRow() < DIFFICULTY_BOARD_EASY - 1 && board.get(arrayNumber).getPosition().getColumn() > 0 && board.get(arrayNumber).toString().equals("-")) {
                board.set(arrayNumber, new Barco(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn()));
                print();
            }
        }*/     //falta rever as validações - tratar dos pontos caso erre na casa
        }
    }

    
    
    
    
    
    
    //IMPRIME O MENU PRINCIPAL - FEITO MAS FALTA COMENTARIO
    public static void givePoints() {
        System.out.println("Escolha uma opção");
        System.out.println();

            System.out.println("1 - Falhar agua");
            System.out.println("2 - Falhar barco");
            System.out.println("3 - Tempo de jogo inferior ao tempo base");
            System.out.println("4 - Tempo de jogo para além do tempo base ");
            System.out.println("5 - Mais que uma tentativa de valiação");
            System.out.println("6 - Apenas uma validação ");
            System.out.println("7 - Mostrar pontos");
            System.out.println("8 - Escolher jogador");

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
                    players.get(playerID).getScore().missedWater();
                    break;
                case 2:
                    players.get(playerID).getScore().missedBoat();
                    break;
                case 3:
                    players.get(playerID).getScore().minusTime(5);
                    break;
                case 4:
                    players.get(playerID).getScore().plusTime(6);
                    break;
                case 5:
                    players.get(playerID).getScore().missedAttempts(8);
                    break;
                case 6:
                    players.get(playerID).getScore().singleCheck();
                    break;
                case 7:
                    System.out.println(players.get(playerID).getScore().getPoints()); // Atribuição de 50 pontos iniciais ao jogador
                    break;
                case 8:
                    choosePlayer();
                    break;
                default:
                    start(); // no caso de não ser escolhida nenhuma das opções facultadas ao utilizador, volta ao menu principal
                    break;
            }
    }

}
