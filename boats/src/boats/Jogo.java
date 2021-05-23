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
    static int i, j, playerCount;
    static ArrayList<Casa> placeholders = new ArrayList<Casa>();
    static ArrayList<Integer> forbiden = new ArrayList<Integer>();
    static ArrayList<Jogador> players = new ArrayList<Jogador>();
    static ArrayList<Integer> points = new ArrayList<Integer>();
    static Regras rules = new Regras();
    static Scanner input = new Scanner(System.in);
    static int playerID;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("Seja bem-vindo ao Docks & Boats\n");
        System.out.println("Escolha uma opção");
        System.out.println();
        if (players.isEmpty()) {
            System.out.println("1 - Criar Jogador");
            System.out.println("0 - Sair\n");

            System.out.print("Opção -> ");
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
                    //
                    break;
            }
        } else {
            System.out.println("1 - Criar Jogador");
            System.out.println("2 - Escolher jogador");
            System.out.println("0 - Sair\n");

            System.out.print("Opção -> ");
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

    public static void createPlayer() {
        System.out.print("Nickname-> ");
        String nickname = input.next().toLowerCase().trim();
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getNickname().toLowerCase().trim().equals(nickname)) {
                System.out.println("O nickname inserido já se encontra em uso\n");
                break;
            }else{
                players.add(new Jogador(playerCount, nickname, 0));
                playerID = players.get(playerCount).getId();
                playerCount++;
                menu();
            }
        }
    }

    public static void choosePlayer() {
        System.out.print("Nickname-> ");
        String nickname = input.next().toLowerCase().trim();
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getNickname().toLowerCase().trim().equals(nickname)) {
                playerID = players.get(i).getId();
                menu();
                break;
            }else{
                System.out.println("Não existe nenhum utilizador com o nickname inserido\n");
                start();
            }
        }
    }

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
        int option = input.nextInt();

        switch (option) {
            case 1:
                chooseDificulty();
                break;
            case 2:
                //
                break;
            case 3:
                printPlayerScore(players.get(0));
                break;
            case 4:
                printScore();
                break;
            case 5:
                //
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

    public static void printHelp() {
        System.out.println("Legenda:");
        System.out.println("A: Água");
        System.out.println("-: Desconhecido");
        System.out.println("P: Porto");
        System.out.println("B: Barco");
        System.out.println("-------------------\n");

        //imprime as posições no tabuleiro
        System.out.println("Exemplo de Posições:");
        for (i = 0; i < placeholders.size(); i++) {
            System.out.print(placeholders.get(i).getPosition().toString() + " ");
            if (placeholders.get(i).getPosition().getColumn() == 4) {
                System.out.println();
            }
        }

        System.out.println("\nPara jogar introduza o tipo de casa (B) e em seguida a posição (2,3)");
    }

    public static void updateScore() {
        players.forEach((n) -> points.add(n.getScore().getPoints()));
    }

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

    public static void chooseDificulty() {
        System.out.println("Escolha a dificuldade:");
        System.out.println();
        System.out.println("1 - Iniciar jogo aleatório");
        System.out.println("2 - Iniciar jogo – Fácil");
        System.out.println("3 - Iniciar jogo – Médio");
        System.out.println("4 - Iniciar jogo - Difícil");
        System.out.println("0 - Voltar\n");

        System.out.print("Opção -> ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                //
                break;
            case 2:
                generateBoard(DIFFICULTY_BOARD_EASY);
                //setTimer(30)
                players.get(0).getScore().setPoints(50);
                break;
            case 3:
                generateBoard(DIFFICULTY_BOARD_MEDIUM);
                //setTimer(90)
                players.get(0).getScore().setPoints(100);
                break;
            case 4:
                generateBoard(DIFFICULTY_BOARD_HARD);
                //setTimer(150)
                players.get(0).getScore().setPoints(150);
                break;
            case 0:
                start();
                break;
            default:
                chooseDificulty();
                break;
        }
    }

    public static void print() {
        //imprime o tabuleiro
        System.out.println("\nTabuleiro de Jogo:");
        for (i = 0; i < placeholders.size(); i++) {
            System.out.print(placeholders.get(i).toString() + " ");
            if (placeholders.get(i).getPosition().getColumn() == 4) {
                System.out.println();
            }
        }
        play();
    }

    public static void play() {
        System.out.print("Campo (A ou B) -> ");
        String move = input.next().trim();
        System.out.print("Linha (x) -> ");
        int x = input.nextInt();
        System.out.print("Coluna (y) -> ");
        int y = input.nextInt();
        if (move.equals("B")) {
            placeBoat(checkExists(x, y));
        }
        //if(move.equals("A"))
        //TODO
    }

//    for(i=0;i<6;i++)
//       System.out.println(placeholders.get(i).getPosition().toString());
    public static int checkExistsIndentifier(int x, int y, char indentifier) {
        int arrayNumber = -1;
        for (i = 0; i < placeholders.size(); i++) {
            if (placeholders.get(i).getPosition().getRow() == x && placeholders.get(i).getPosition().getColumn() == y && placeholders.get(i).toString().equals("-")) {
                arrayNumber = i; //verificar porque começa em 0
            }
        }
        return arrayNumber;
    }

    public static int checkExists(int x, int y) {
        int arrayNumber = -1;
        for (i = 0; i < placeholders.size(); i++) {
            if (placeholders.get(i).getPosition().getRow() == x && placeholders.get(i).getPosition().getColumn() == y) {
                arrayNumber = i; //verificar porque começa em 0
            }
        }
        return arrayNumber;
    }

    public static void validateMove(int x, int y) {
        if (placeholders.get(0).toString().equals("P")) {
            forbiden.add(1);
            forbiden.add(5);
            forbiden.add(6);
        }
        if (placeholders.get(1).toString().equals("P")) {
            forbiden.add(0);
            forbiden.add(2);
            forbiden.add(5);
            forbiden.add(6);
            forbiden.add(7);
        }
        if (placeholders.get(2).toString().equals("P")) {
            forbiden.add(1);
            forbiden.add(3);
            forbiden.add(6);
            forbiden.add(7);
            forbiden.add(8);
        }
        if (placeholders.get(3).toString().equals("P")) {
            forbiden.add(2);
            forbiden.add(4);
            forbiden.add(7);
            forbiden.add(8);
            forbiden.add(9);
        }
        if (placeholders.get(4).toString().equals("P")) {
            forbiden.add(3);
            forbiden.add(8);
            forbiden.add(9);
        }
        if (placeholders.get(5).toString().equals("P")) {
            forbiden.add(0);
            forbiden.add(1);
            forbiden.add(6);
            forbiden.add(10);
            forbiden.add(11);
        }
        if (placeholders.get(6).toString().equals("P")) {
            forbiden.add(0);
            forbiden.add(1);
            forbiden.add(6);
            forbiden.add(10);
            forbiden.add(11);
        }
        if (placeholders.get(7).toString().equals("P")) {
            forbiden.add(1);
            forbiden.add(2);
            forbiden.add(3);
            forbiden.add(6);
            forbiden.add(8);
            forbiden.add(11);
            forbiden.add(12);
            forbiden.add(13);
        }
        if (placeholders.get(8).toString().equals("P")) {
            forbiden.add(2);
            forbiden.add(3);
            forbiden.add(4);
            forbiden.add(7);
            forbiden.add(9);
            forbiden.add(12);
            forbiden.add(13);
            forbiden.add(14);
        }
        if (placeholders.get(9).toString().equals("P")) {
            forbiden.add(4);
            forbiden.add(3);
            forbiden.add(8);
            forbiden.add(13);
            forbiden.add(14);
        }
    }

    public static void generateBoard(int difficulty) {
        if (difficulty == DIFFICULTY_BOARD_EASY) {
            for (i = 0; i < DIFFICULTY_BOARD_EASY; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_EASY; j++) {
                    placeholders.add(new Desconhecido(i, j));
                }
            }
        }

        if (difficulty == DIFFICULTY_BOARD_MEDIUM) {
            for (i = 0; i < DIFFICULTY_BOARD_MEDIUM; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_MEDIUM; j++) {
                    placeholders.add(new Desconhecido(i, j));
                }
            }
        }

        if (difficulty == DIFFICULTY_BOARD_HARD) {
            for (i = 0; i < DIFFICULTY_BOARD_HARD; i++) {
                for (j = 0; j < DIFFICULTY_BOARD_HARD; j++) {
                    placeholders.add(new Desconhecido(i, j));
                }
            }
        }
        placeDock(difficulty);
        print();
    }

    public static void placeDock(int difficulty) {
        int turn = 0;
        while (turn < difficulty) {
            int x = new Random().nextInt(5);
            int y = new Random().nextInt(5);
            int arrayNumber = checkExistsIndentifier(x, y, '-');
            //check exists boolean
            System.out.println("number: " + arrayNumber);
            if (arrayNumber != -1) {
                placeholders.set(arrayNumber, new Porto(x, y));
            }
            turn++;
        }
    }

    //transforma em agua no fim do jogo
    public static void placeRemainWater() {
        for (i = 0; i < placeholders.size(); i++) {
            if (placeholders.get(i).toString().equals("-")) {
                placeholders.set(i, new Agua(placeholders.get(i).getPosition().getRow(), placeholders.get(i).getPosition().getColumn()));
            }
        }
    }

    //falta rever as validações - tratar dos pontos caso erre na casa
    public static void placeBoat(int arrayNumber) {
        if (placeholders.get(arrayNumber).toString().equals("-") || placeholders.get(arrayNumber).toString().equals("A")) {
            //validar se pode ser posto naquele sitio
            //caso haja um porto à esquerda
            if (placeholders.get(arrayNumber).getPosition().getRow() > 0 && placeholders.get(arrayNumber).getPosition().getColumn() - 1 > 0 && placeholders.get(arrayNumber).toString().equals("-")) {
                placeholders.set(arrayNumber, new Barco(placeholders.get(arrayNumber).getPosition().getRow(), placeholders.get(arrayNumber).getPosition().getColumn()));
                print();
            } //caso haja um porto em cima
            else if (placeholders.get(arrayNumber).getPosition().getRow() - 1 > 0 && placeholders.get(arrayNumber).getPosition().getColumn() > 0 && placeholders.get(arrayNumber).toString().equals("-")) {
                placeholders.set(arrayNumber, new Barco(placeholders.get(arrayNumber).getPosition().getRow(), placeholders.get(arrayNumber).getPosition().getColumn()));
                print();
            } //caso haja um porto à direita
            else if (placeholders.get(arrayNumber).getPosition().getRow() > 0 && placeholders.get(arrayNumber).getPosition().getColumn() < DIFFICULTY_BOARD_EASY - 1 && placeholders.get(arrayNumber).toString().equals("-")) {
                placeholders.set(arrayNumber, new Barco(placeholders.get(arrayNumber).getPosition().getRow(), placeholders.get(arrayNumber).getPosition().getColumn()));
                print();
            } //caso haja um porto em baixo
            else if (placeholders.get(arrayNumber).getPosition().getRow() < DIFFICULTY_BOARD_EASY - 1 && placeholders.get(arrayNumber).getPosition().getColumn() > 0 && placeholders.get(arrayNumber).toString().equals("-")) {
                placeholders.set(arrayNumber, new Barco(placeholders.get(arrayNumber).getPosition().getRow(), placeholders.get(arrayNumber).getPosition().getColumn()));
                print();
            }
        }
    }

//    public void test(){
//                    if (placeholders.get(arrayNumber).getPosition().getRow()-1 >= placeholders.size() && placeholders.get(i).getPosition().getColumn() - 1 >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() - 1 >= placeholders.size() && placeholders.get(i).getPosition().getColumn() >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() - 1 >= placeholders.size() && placeholders.get(i).getPosition().getColumn() + 1 >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() >= placeholders.size() && placeholders.get(i).getPosition().getColumn() - 1 >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() >= placeholders.size() && placeholders.get(i).getPosition().getColumn() + 1 >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() + 1 >= placeholders.size() && placeholders.get(i).getPosition().getColumn() - 1 >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() + 1 >= placeholders.size() && placeholders.get(i).getPosition().getColumn() >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                if (placeholders.get(arrayNumber).getPosition().getRow() + 1 >= placeholders.size() && placeholders.get(i).getPosition().getColumn() + 1 >= placeholders.size()) {
//                    forbiden.add(arrayNumber);
//                }
//                for (int i = 0; i < forbiden.size(); i++) {
//                    System.out.println(i + forbiden.get(i).toString());
//                }
//    }
}
