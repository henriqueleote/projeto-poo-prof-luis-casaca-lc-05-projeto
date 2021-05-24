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

    static int DIFFICULTY_BOARD_EASY = 5;
    static int DIFFICULTY_BOARD_MEDIUM = 7;
    static int DIFFICULTY_BOARD_HARD = 10;
    static int SET_DIFFICULTY = 0;
    static int NUMBER_OF_ROWS = 0;
    static int NUMBER_OF_COLUMNS = 0;
    static int i, j, playerCount;
    static ArrayList<Casa> board = new ArrayList<Casa>();
    static ArrayList<Integer> forbiden = new ArrayList<Integer>();
    static ArrayList<Jogador> players = new ArrayList<Jogador>();
    static ArrayList<Integer> points = new ArrayList<Integer>();
    static ArrayList<Integer> docks = new ArrayList<Integer>();
    static ArrayList<Integer> water = new ArrayList<Integer>();
    static ArrayList<Integer> boat = new ArrayList<Integer>();
    static ArrayList<Integer> unknown = new ArrayList<Integer>();
    static Regras rules = new Regras();
    static Scanner input = new Scanner(System.in);
    static int playerID;
    static Jogador player;

    //MAIN
    public static void main(String[] args) {
        start();
    }

    //IMPRIME O MENU PRINCIPAL - FEITO MAS FALTA COMENTARIO
    public static void start() {
        System.out.println("Seja bem-vindo ao Docks & Boats\n");
        System.out.println("Escolha uma opção");
        System.out.println();

        if (players.isEmpty()) {
            System.out.println("1 - Criar Jogador");
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
                case 0:
                    System.exit(0);
                    break;
                default:
                    start();
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

    //CRIA UM JOGADOR - FEITO MAS FALTA COMENTARIO - REVER JUST IN CASE (TRATAR DA CENA DOS PONTOS)
    public static void createPlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim();
        player = getPlayerByNickname(nickname);
        
        if (player != null) {
            if (players.contains(player)) {
                System.out.println("O nickname inserido já se encontra em uso\n");
            }
        } else {
            players.add(new Jogador(playerCount, nickname, 0));
            playerID = players.get(playerCount).getId();
            playerCount++;
            menu();
        }
    }

    //ESCOLHE O JOGADOR - FEITO MAS FALTA COMENTARIO - REVER JUST IN CASE (TRATAR DA CENA DOS PONTOS)
    public static void choosePlayer() {
        System.out.print("Nickname -> ");
        String nickname = input.next().trim();
        player = getPlayerByNickname(nickname);

        if (player != null) {
            if (players.contains(player)) {
                playerID = players.indexOf(player);
            }
            menu();
        } else {
            System.out.println("Não existe nenhum utilizador com o nickname inserido\n");
            start();
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
            while (!input.hasNextInt()) {
                System.out.print("-> ");
                input.next();
            }
            int option = input.nextInt();
            System.out.println("-----------");

            switch (option) {
            case 1:
                chooseDificulty();
                break;
            case 2:
                //
                break;
            case 3:
                printPlayerScore(players.get(playerID));
                break;
            case 4:
                printScore();
                break;
            case 5:
                System.out.println("Funcionalidade indisponivel. Obrigado");
                menu();
                break;
            case 6:
                printHelp();
                break;
            case 7:
                start();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                //
                break;
        }
    }

    //IMPRIME OS PONTOS DO JOGADOR - ///////////////////////////////////////////TODO
    public static void printPlayerScore(Jogador player) {
        System.out.println("Pontuações de " + player.getNickname());
        //System.out.print("Pontuações Geral " + player.getNickname()); TODO
        System.out.println("Nivel X - " + player.getScore().getPoints() + " pontos");
        System.out.println("Prima enter para voltar ao menu principal");
        try {
            System.in.read();
            menu();
        } catch (Exception e) {
        }
    }

    //IMPRIME AJUDA PARA O JOGO - FEITO MAS FALTA COMENTARIO
    public static void printHelp() {
        System.out.println("Legenda:");
        System.out.println("A: Água");
        System.out.println("-: Desconhecido");
        System.out.println("P: Porto");
        System.out.println("B: Barco");
        System.out.println("-------------------");

        //imprime as posições no tabuleiro
        System.out.println("Exemplo de Posições 5x5:\n");
        for (i = 0; i < 5; i++) {
            System.out.print("\t");
            for (j = 0; j < 5; j++) {
                System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println("\nPara jogar introduza o tipo de casa (B) e em seguida a posição (2,3)");
        System.out.println("Prima enter para voltar ao menu principal");
        try {
            System.in.read();
        } catch (Exception e) {
        }
        menu();
    }

    //IMPRIME AS PONTUAÇÕES - FEITO MAS FALTA COMENTARIO - REVER
    public static void printScore() {
        System.out.println("Pontuações Gerais");
        players.forEach((n) -> System.out.println(n.getId() + "º - " + n.getNickname() + " - " + n.getScore().getPoints() + " pontos"));
        System.out.println("Prima enter para voltar ao menu principal");
        try {
            System.in.read();
            menu();
        } catch (Exception e) {
        }
    }

    //MENU PARA ESCOLHA DE DIFICULDADE - FEITO MAS FALTA COMENTARIO - REVER
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
            int option = input.nextInt();
            System.out.println("-----------");

            switch (option) {
            case 1:
                //
                break;
            case 2:
                SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;
                NUMBER_OF_ROWS=4;
                NUMBER_OF_COLUMNS=4;
                generateBoard();
                //setTimer(30)
                players.get(playerID).getScore().setPoints(50);
                break;
            case 3:
                SET_DIFFICULTY = DIFFICULTY_BOARD_MEDIUM;
                NUMBER_OF_ROWS=6;
                NUMBER_OF_COLUMNS=6;
                generateBoard();
                //setTimer(90)
                players.get(playerID).getScore().setPoints(100);
                break;
            case 4:
                SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;
                NUMBER_OF_ROWS=9;
                NUMBER_OF_COLUMNS=9;
                generateBoard();
                //setTimer(150)
                players.get(playerID).getScore().setPoints(150);
                break;
            case 0:
                start();
                break;
            default:
                chooseDificulty();
                break;
        }
    }

    //IMPRIME O TABULEIRO DE JOGO - FEITO MAS FALTA COMENTARIO
    public static void print() {
        System.out.println("\nTabuleiro de Jogo:");
        for (i = 0; i < board.size(); i++) {
            System.out.print(board.get(i).toString() + " ");
            if (board.get(i).getPosition().getColumn() == (SET_DIFFICULTY - 1)) {
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
        while (!input.equals("A") || !input.equals("B") || input.toString().length()!=1) {
                System.out.print("-> ");
                input.next().trim().charAt(1);
            }
        String move = input.next().trim();
        System.out.print("Linha (x) -> ");
        while (!input.hasNextInt() || input.nextInt()>=0 || input.nextInt()<=NUMBER_OF_ROWS) {
                System.out.print("-> ");
                input.next().charAt(1);
            }
        int x = input.nextInt();
        System.out.print("Coluna (y) -> ");
        while (!input.hasNextInt() || input.nextInt()>=0 || input.nextInt()<=NUMBER_OF_ROWS) {
                System.out.print("-> ");
                input.next().charAt(1);
            }
        int y = input.nextInt();
        int position = getIndex(x,y);
        //FALTAM VALIDAÇÕES
        if(move.equals("B")){
            placeBoat(position);
        }
        if(move.equals("A")){
            placeWater(position);
        }
        //CASO PONHA NO SITIO ERRADO, PORQUITOS
    }

    //DEFINE TODAS AS VARIAVEIS COM O SEU VALOR INICIAL - FEITO MAS FALTA COMENTARIO
    public static void clearValues() {
        DIFFICULTY_BOARD_EASY = 5;
        DIFFICULTY_BOARD_MEDIUM = 7;
        DIFFICULTY_BOARD_HARD = 10;
        SET_DIFFICULTY = 0;
        i = 0;
        j = 0;
        playerCount = 0;
        board.clear();
        forbiden.clear();
        players.clear();
        points.clear();
        docks.clear();
        water.clear();
        boat.clear();
        unknown.clear();
        playerID = -1;
        player = null;
    }

    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS E A CASA - FEITO MAS FALTA COMENTARIO - REVER
    public static int getIndexFromPlace(int x, int y, char indentifier) {
        int arrayNumber = -1;
        for (i = 0; i < board.size(); i++) {
            if (board.get(i).getPosition().getRow() == x && board.get(i).getPosition().getColumn() == y && board.get(i).toString().equals("-")) {
                arrayNumber = i; //verificar porque começa em 0
            }
        }
        return arrayNumber;
    }

    //VERIFICA SE EXISTE ALGUM JOGADOR COM O NICKNAME INTRODUZIDO - FEITO MAS FALTA COMENTARIO
    public static Jogador getPlayerByNickname(String nickname) {
        for (Jogador playerObj : players) {
            if (playerObj.getNickname().toLowerCase().trim().equals(nickname)) {
                return playerObj;
            }
        }
        return null;
    }

    //DEVOLVE A POSIÇÃO NO ARRAYLIST DADAS AS COORDENADAS - FEITO MAS FALTA COMENTARIO
    public static int getIndex(int x, int y) {
        int arrayNumber = -1;
        for (Casa boardObj : board) {
            if (boardObj.getPosition().getRow() == x && boardObj.getPosition().getColumn() == y) {
                arrayNumber = board.indexOf(boardObj);
            }
        }
        return arrayNumber;
    }

    //DEVOLVE AS COORDENADAS (x) DADA A POSIÇÃO NO ARRAYLIST - FEITO MAS FALTA COMENTARIO
    public static int getRowFromIndex(int arrayNumber) {
        int position = board.get(arrayNumber).getPosition().getRow();
        return position;
    }

    //DEVOLVE AS COORDENADAS (y) DADA A POSIÇÃO NO ARRAYLIST - FEITO MAS FALTA COMENTARIO
    public static int getColumnFromIndex(int arrayNumber) {
        int position = board.get(arrayNumber).getPosition().getColumn();
        return position;
    }

    //VALIDAR JOGADAS - \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TODO
    public static void validateMove(int x, int y) {
        if (board.get(0).toString().equals("P")) {
            forbiden.add(1);
            forbiden.add(5);
            forbiden.add(6);
        }
        if (board.get(1).toString().equals("P")) {
            forbiden.add(0);
            forbiden.add(2);
            forbiden.add(5);
            forbiden.add(6);
            forbiden.add(7);
        }
        if (board.get(2).toString().equals("P")) {
            forbiden.add(1);
            forbiden.add(3);
            forbiden.add(6);
            forbiden.add(7);
            forbiden.add(8);
        }
        if (board.get(3).toString().equals("P")) {
            forbiden.add(2);
            forbiden.add(4);
            forbiden.add(7);
            forbiden.add(8);
            forbiden.add(9);
        }
        if (board.get(4).toString().equals("P")) {
            forbiden.add(3);
            forbiden.add(8);
            forbiden.add(9);
        }
        if (board.get(5).toString().equals("P")) {
            forbiden.add(0);
            forbiden.add(1);
            forbiden.add(6);
            forbiden.add(10);
            forbiden.add(11);
        }
        if (board.get(6).toString().equals("P")) {
            forbiden.add(0);
            forbiden.add(1);
            forbiden.add(6);
            forbiden.add(10);
            forbiden.add(11);
        }
        if (board.get(7).toString().equals("P")) {
            forbiden.add(1);
            forbiden.add(2);
            forbiden.add(3);
            forbiden.add(6);
            forbiden.add(8);
            forbiden.add(11);
            forbiden.add(12);
            forbiden.add(13);
        }
        if (board.get(8).toString().equals("P")) {
            forbiden.add(2);
            forbiden.add(3);
            forbiden.add(4);
            forbiden.add(7);
            forbiden.add(9);
            forbiden.add(12);
            forbiden.add(13);
            forbiden.add(14);
        }
        if (board.get(9).toString().equals("P")) {
            forbiden.add(4);
            forbiden.add(3);
            forbiden.add(8);
            forbiden.add(13);
            forbiden.add(14);
        }
    }

    //GERA O TABULEIRO DE JOGO - FEITO MAS FALTA COMENTARIO
    public static void generateBoard() {
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_EASY) {
            for (i = 0; i < DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_EASY; j++) {
                    board.add(new Desconhecido(i, j));
                    unknown.add(getIndex(i, j));
                }
            }
        }

        if (SET_DIFFICULTY == DIFFICULTY_BOARD_MEDIUM) {
            for (i = 0; i < DIFFICULTY_BOARD_MEDIUM; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_MEDIUM; j++) {
                    board.add(new Desconhecido(i, j));
                    unknown.add(getIndex(i, j));
                }
            }
        }

        if (SET_DIFFICULTY == DIFFICULTY_BOARD_HARD) {
            for (i = 0; i < DIFFICULTY_BOARD_HARD; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_HARD; j++) {
                    board.add(new Desconhecido(i, j));
                    unknown.add(getIndex(i, j));
                }
            }
        }
        placeDock();
        print();
    }

    //COLOCA OS PORTOS NO TABULEIRO DE JOGO - FEITO MAS FALTA COMENTARIO - REVER
    public static void placeDock() { // REVER
        while (docks.size() < SET_DIFFICULTY) {
            int random = new Random().nextInt(board.size());
            if (!docks.contains(random)) {
                docks.add(random);
            }
        }

        for (i = 0; i < SET_DIFFICULTY; i++) {
            board.set(docks.get(i), new Porto(getRowFromIndex(docks.get(i)), getColumnFromIndex(docks.get(i))));
        }

        //docks.forEach((n) -> System.out.println(n));  //DEVOLVE PARA O ECRÃ AS POSIÇÕES DOS PORTOS NUM ARRAYLIST
    }

    //transforma em agua no fim do jogo
    public static void placeRemainWater() {
        for (i = 0; i < board.size(); i++) {
            if (board.get(i).toString().equals("-")) {
                board.set(i, new Agua(board.get(i).getPosition().getRow(), board.get(i).getPosition().getColumn()));
            }
        }
    }
    
    //COLOCA A AGUA NO TABULEIRO DE JOGO - FEITO MAS FALTA COMENTARIO - REVER
    public static void placeWater(int arrayNumber){
        if (board.get(arrayNumber).toString().equals("P") || board.get(arrayNumber).toString().equals("B") || board.get(arrayNumber).toString().equals("A")) {
            System.out.println("Não pode colocar agua num lugar que não se encontra desconhecido.");
        } else {
            board.set(arrayNumber, new Agua(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn()));
            water.add(arrayNumber);
        }
    }

    //COLOCA OS BARCOS NO TABULEIRO DE JOGO - \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\TODO
    public static void placeBoat(int arrayNumber) {
        if (board.get(arrayNumber).toString().equals("P")) {
            System.out.println("Não pode colocar um barco onde está um porto de atracagem");
        } else {
            board.set(arrayNumber, new Barco(board.get(arrayNumber).getPosition().getRow(), board.get(arrayNumber).getPosition().getColumn()));
            boat.add(arrayNumber);
        }
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

//    public void test(){
//                    if (board.get(arrayNumber).getPosition().getRow()-1 >= board.size() && board.get(i).getPosition().getColumn() - 1 >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() - 1 >= board.size() && board.get(i).getPosition().getColumn() >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() - 1 >= board.size() && board.get(i).getPosition().getColumn() + 1 >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() >= board.size() && board.get(i).getPosition().getColumn() - 1 >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() >= board.size() && board.get(i).getPosition().getColumn() + 1 >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() + 1 >= board.size() && board.get(i).getPosition().getColumn() - 1 >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() + 1 >= board.size() && board.get(i).getPosition().getColumn() >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (board.get(arrayNumber).getPosition().getRow() + 1 >= board.size() && board.get(i).getPosition().getColumn() + 1 >= board.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                for (int i = 0; i < forbiden.size(); i++) {
//                    System.out.println(i + forbiden.get(i).toString());
//                }
//    }
//}
