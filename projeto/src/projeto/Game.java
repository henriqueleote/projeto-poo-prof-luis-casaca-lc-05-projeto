/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;

/**
 *
 * @author CucasPC
 */
public class Game{

    String nickname;
    public int numberOfPlayers;

    public Game(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    public void start(){
        int option;

        ArrayList<Player> players;
        players = new ArrayList<Player>();

        printIntro();
        printMenu();
        option = AppStart.input.nextInt();

        switch(option){
            case 1:
                createPlayer(players);
                break;
            case 4:
                printHelp();
                break;

            case 0:
                exitGame();
                break;
            default:
                System.out.println("\nERRO: Opção inválida");
        }
    }

    public void createPlayer(ArrayList<Player> players){
        System.out.print("\nIntroduza o nome do jogador");
        String nickname = AppStart.input.nextLine().trim().toLowerCase();

        if (nickname.length() <= 2){
            System.out.println();
            System.out.println("ERRO: O nome tem que ter 3 ou mais caracteres");
            System.out.println();
        }else{
            Player user = new Player();
            user.setNickname(nickname);
            players.add(user);
        }
    }

    public void displayNoPlayer(){
        System.out.println();
        System.out.println("ERRO: Não existem jogadores!\n");
    }

    public void printIntro(){
        System.out.println();
        System.out.println("::::::::::::::::::::::::::::::::::::::::Intro::::::::::::::::::::::::::::::::::::::::");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();
    }

    public void printMenu(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("||         Bem-Vindo ao Boats & Docks         ||");
        System.out.println("================================================");
        System.out.print("Escolha uma opção: ");
    }
    
    public void printHelp(){
        System.out.println();
        System.out.println("::::::::::::::::::::::::::::::::::::::::Ajuda::::::::::::::::::::::::::::::::::::::::");
        System.out.println("Opção x para jogar");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();
    }

    public void exitGame(){
        System.out.println("::::::::::Até à próxima::::::::::");
        System.exit(0);
    }

    //SETS
    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    //GETS
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

}

//    public void generateLayout(){ //gera o layout do tabuleiro -> Board
//        
//    }
//    
//    public void chooseDificulty(){ //escolhe a dificuldade do jogo através do enum -> Level
//        
//    }
//    
//    public void setUnknown(){ //altera o tipo da casa do jogador para desconhecido (casa alteravel)
//        
//    }
//    
//    public void setWater(){ //altera o tipo da casa do jogador para agua (casa alteravel)
//        
//    }
//    
//    public void setBoatAndPort(){ //altera o tipo da casa do jogador para barco e define um porto para o barco (casa alteravel)
//        
//    }
