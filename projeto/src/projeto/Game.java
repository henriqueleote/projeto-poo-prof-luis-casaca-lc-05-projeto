package projeto;

import java.util.Scanner;

/**
 *
 * @author CucasPC
 */
public class Game {

    public String nickname;         //variavel para o nome do jogador
    public int numberOfPlayers;     //variavel para o limite de jogadores
    public String[][] players;      //array para os jogadores
    public int userCount;           //variavel o numero de jogadores criados
    public int userID;              //variavel para o id do jogador
    public int DEFAULT_VALUE = 0;   //variavel definida para 0
    public static Scanner input;    //varivel para receber input de dados

    //construtor da classe Game que recebe o numero de jogadores
    public Game(int numberOfPlayers) {              
        players = new String[numberOfPlayers][5];   //inicializar o array com o numero de jogadores recebido e 5 colunas
        this.numberOfPlayers = numberOfPlayers;     //inicializar a variavel global com o numero de jogadores recebido
        input = new Scanner(System.in);             //inicializar a variavel para receber dados
    }

    //metodo para criar um jogador
    public void createPlayer() {
        if (userCount < numberOfPlayers) {  //se ainda houver "espaço para jogadores"
            System.out.print("\nIntroduza o nome do jogador: ");    //pede o nome do jogador
            String nickname = input.nextLine().trim().toLowerCase();    //recebe o input em string, retirar espaços e pôr em minusculas 
            if (nickname.length() >= 3) {    //caso o nickname seja maior ou igual a 3 caracteres
                for (int i = 0; i < numberOfPlayers; i++) { //percorre o array de utilizadores 
                    if (players[i][0].equals(null)) {   //até encontrar o primeiro espaço e insere os dados caso haja
                        players[i][0] = String.valueOf(userCount);       //id
                        players[i][1] = nickname;                        //nickname
                        players[i][2] = String.valueOf(DEFAULT_VALUE);   //score
                        players[i][3] = String.valueOf(DEFAULT_VALUE);   //highScore
                        players[i][4] = String.valueOf(DEFAULT_VALUE);   //games
                        userCount++;    //aumenta a variavel do numero de jogadores criados em +1
                        System.out.println("\nO seu ID de utilizador é : " + userCount);    //apresenta o id
                        System.out.println("Não se esqueça!");
                        break;  //trava o ciclo visto que já fez o necessário
                    }
                }
            } else {    //caso o nickname seja menor que 3 caracteres
                System.out.println();
                System.out.println("ERRO: O nome tem que ter 3 ou mais caracteres");
                System.out.println();
            }
        } else {    //caso já não haja "espaço" para jogadores
            System.out.println("Limite de jogadores atingido.");
            printIntro();   //volta ao inicio
        }
    }
    
    //metodo para escolher um jogador
    public void choosePlayer() {
        System.out.print("\nIntroduza o ID do jogador: ");      //pede o nome do jogador
        int ID = input.nextInt();       //recebe o input em inteiro
        if (ID >= 0) {  //caso o id seja maior a 0
            if (!players[0][0].equals(null)) {  //caso o utilizador exista
                userID = ID;    //define o id do utilizador do atual jogo como o id inserido
                printMenu();
            } else {    //caso o jogador nao exista
                System.out.println("Esse utilizador não existe");
                printIntro();   //volta ao inicio
            }
        }
    }

    //metodo que apresenta o menu inicial
    public void printIntro() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("||         Bem-Vindo ao Boats & Docks:        ||");
        System.out.println("================================================");
        System.out.println();

        //caso nao haja jogadores, apresenta o menu para criar
        if (userCount == 0) {
            createPlayer();
        }
        //caso só haja um jogador, apresenta diretamente o menu de jogo
        if (userCount == 1) {
            userID = 1;
            printMenu();
        }
        //caso haja mais que um jogador, apresenta um menu para escolher
        if (userCount > 1) {
            choosePlayer();
        }
    }

    //metodo que apresenta o menu principal do jogo
    public void printMenu() {
        System.out.println("\n\t=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("\t||         Bem-Vindo ao Boats & Docks:        ||");
        System.out.println("\t||      Escolha uma das seguintes opções:     ||");
        System.out.println("\t||        Tabuleiro Facil (5x5): Opção 1      ||");
        System.out.println("\t||        Tabuleiro Medio (7x7): Opção 2      ||");
        System.out.println("\t||      Tabuleiro Dificil (10x10): Opção 3    ||");
        System.out.println("\t||           Listar jogadores: Opção 4        ||");
        System.out.println("\t||             Ajuda/Regras: Opção 5          ||");
        System.out.println("\t||             Sair do jogo: Opção 0          ||");
        System.out.println("\t================================================");
        System.out.print("\n\tEscolha uma opção: ");
        
        int option = input.nextInt();   //recebe o input em inteiro

        //switch para opção
        switch (option) {
            case 1:
                //gerar tabuleiro facil
                Score score = new Score(Integer.parseInt(players[userID][2]));
                score.boatFail();
                break;
            case 2:
                //gerar tabuleiro medio
                break;
            case 3:
                //gerar tabuleiro dificil
                break;
            case 4:
                printPlayers(); //apresenta todos os jogadores
                break;
            case 5:
                printHelp();    //apresenta o menu de ajuda
                break;
            case 0:
                exitGame();     //sai do jogo
                break;
            default:
                printMenu();    //por default, volta a apresentar este menu
        }
    }

    //metodo que apresenta todos os jogadores
    public void printPlayers() {
        System.out.println("----------------------------------------------");
        for (int i = 0; i < numberOfPlayers; i++) {  //percorre o array de utilizadores e apresenta todos os dados de todos os utilizadores
            System.out.println("ID: " + players[i][0]);
            System.out.println("Nome: " + players[i][1]);
            System.out.println("Pontuação: " + players[i][2]);
            System.out.println("Recorde de Pontuação: " + players[i][3]);
            System.out.println("Numero de Jogos: " + players[i][4]);
            System.out.println("----------------------------------------------");
        }
    }

    //metodo que apresenta o menu de ajuda do jogo
    public void printHelp() {
        System.out.println();
        System.out.println("::::::::::::::::::::::::::::::::::::::::Ajuda::::::::::::::::::::::::::::::::::::::::");
        System.out.println("A preencher");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();
    }
    
    //metodo que sai do jogo
    public void exitGame() {
        System.out.println("::::::::::Até à próxima::::::::::");
        System.exit(0);
    }
    
    //GETS
    
    //metodo que devolve o numero de jogadores
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    //SETS
    //metodo que define o numero de jogadores
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
