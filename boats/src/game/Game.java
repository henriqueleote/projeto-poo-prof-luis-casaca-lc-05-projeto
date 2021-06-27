package game;

import player.Player;
import player.Score;
import board.Dock;
import board.Spot;
import board.Unknown;
import board.Water;
import board.Boat;
import board.Board;
import static board.Board.game;
import static board.Board.input;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Leote (200221060)
 */
public class Game extends Application{

    public static int DIFFICULTY_BOARD_EASY = 5;                                //Variavel inteira para a dificuldade facil
    public static int DIFFICULTY_BOARD_MEDIUM = 7;                              //Variavel inteira para a dificuldade media
    public static int DIFFICULTY_BOARD_HARD = 10;                               //Variavel inteira para a dificuldade dificl
    public static int SET_DIFFICULTY = 0;                                       //Variavel inteira para definir a dificuldade
    public static int NUMBER_OF_ROWS = 0;                                       //Variavel inteira para definir as linhas
    public static int NUMBER_OF_COLUMNS = 0;                                    //Variavel inteira para definir as colunas
    public static int i;                                                        //Variavel inteira do contador i
    public static int j;                                                        //Variavel inteira do contador j
    public static int playerCount;                                              //Variavel inteira para contar os jogadores
    public static int playerID;                                                 //Variavel inteira para a dificuldade facil
    public static int boardID;                                                  //Variavel inteira para a dificuldade facil
    public static int attempts;                                                 //Variavel inteira para a dificuldade facil
    public static boolean boardReady = false;
    public static int k = 0;
    public static int position = 0;
    int counter, seconds;
    
    public static List<List> boards = new ArrayList<List>();                                 //Coleção da classe Spot para o tabuleiro
    public static List<Spot> gameBoard = new ArrayList<Spot>();                                 //Coleção da classe Spot para o tabuleiro
    public static ObservableList<Player> players =  FXCollections.observableArrayList();         //Coleção da classe Player para os jogadores
    public static List<Integer> docks = new ArrayList<Integer>();                               //Coleção do tipo inteiro para os portos
    public static List<Integer> boat = new ArrayList<Integer>();                                //Coleção do tipo inteiro para os barcos
    public static ObservableList<Button> buttons = FXCollections.observableArrayList();         //Coleção da classe Button para os butoes de jogo

    public static Scanner input = new Scanner(System.in);                       //Objeto da classe Scanner para receber dados
    public static Game game = new Game();                                       //Objeto da classe Game
    public static Player player = new Player();                                 //Objeto da classe Player
    public static Board board = new Board();                                    //Objeto da classe Board
    public static Score score = new Score();                                    //Objeto da classe Score
    public static Rules rules = new Rules();                                    //Objeto da classe Rules
        
    //CONSTRUTOR VAZIO
    public Game() {}

    //MAIN - A FUNCIONAR (PHASE 3 COMPLETE)
    public static void main(String[] args) {
        launch(args);
        game.startConsole();
    }
    
    //JAVAFX
    
    //PAINEL PRINCIPAL - A FUNCIONAR (PHASE 3 COMPLETE)
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
        Button createPlayerButton = new Button();                               //Botão do JavaFX
        Button choosePlayerButton = new Button();                               //Botão do JavaFX
        Button exitButton = new Button();                                       //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        
        stage.setTitle("Docks & Boats");
        stage.setScene(scene);
        stage.show();
                        
        labelOne.setText("Seja bem-vindo ao Docks & Boats");
        labelOne.setTranslateX(0);
        labelOne.setTranslateY(-50);
        labelOne.setFill(Color.BLACK);
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelOne);

        createPlayerButton.setTranslateX(-70);
        createPlayerButton.setTranslateY(0);
        createPlayerButton.setPrefSize(120, 30);
        createPlayerButton.setFont(Font.font("Dialog", 12));
        createPlayerButton.setText("Criar Jogador");
        root.getChildren().add(createPlayerButton);
        createPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createPlayerFX(stage);
                stage.close();
            }
        });
        
        choosePlayerButton.setTranslateX(-70);
        choosePlayerButton.setTranslateY(40);
        choosePlayerButton.setPrefSize(120, 30);
        choosePlayerButton.setFont(Font.font("Dialog", 12));
        choosePlayerButton.setText("Escolher Jogador");
        root.getChildren().add(choosePlayerButton);
        choosePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                choosePlayerFX(stage);
                stage.close();
            }
        });

        if (players.isEmpty()) {
            choosePlayerButton.setVisible(false);
            createPlayerButton.setTranslateX(-70);
            createPlayerButton.setTranslateY(20);
        }
           
        exitButton.setTranslateX(90);
        exitButton.setTranslateY(20);
        exitButton.setPrefSize(80, 30);
        exitButton.setFont(Font.font("Dialog", 12));
        exitButton.setText("Sair");
        root.getChildren().add(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    
    //PAINEL PARA CRIAR JOGADOR - A FUNCIONAR (PHASE 3 COMPLETE)
    public void createPlayerFX(Stage oldStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        Button backButton = new Button();                                       //Botão do JavaFX
        Button createButton = new Button();                                     //Botão do JavaFX
        Text labelOne = new Text();
        Text labelTwo = new Text();
        TextField nicknameField = new TextField();
        
        stage.setTitle("Criar jogador");
        stage.setScene(scene);
        stage.show();
        
        labelOne.setText("Criar jogador");
        labelOne.setTranslateX(0);
        labelOne.setTranslateY(-75);
        labelOne.setFill(Color.BLACK);
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelOne);
        
        labelTwo.setText("Nome:");
        labelTwo.setTranslateX(-80);
        labelTwo.setTranslateY(0);
        labelTwo.setFill(Color.BLACK);
        labelTwo.setFont(Font.font("Dialog", 12));
        root.getChildren().add(labelTwo);
               
        nicknameField.setTranslateX(0);
        nicknameField.setTranslateY(0);
        nicknameField.setPrefWidth(100);
        nicknameField.setMaxWidth(100);
        root.getChildren().add(nicknameField);
                
        createButton.setTranslateX(130);
        createButton.setTranslateY(75);
        createButton.setPrefSize(110, 30);
        createButton.setFont(Font.font("Dialog", 12));
        createButton.setText("Criar Jogador");
        root.getChildren().add(createButton);
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nicknameField.getText() == null || nicknameField.getText().trim().isEmpty())
                    createAlert("Alerta","Falta de dados","Tem que preencher o nome do jogador.",AlertType.WARNING);
                else{
                    player.createPlayer(nicknameField.getText().toString().trim(), stage);
                    //stage.close();
                }
            }
        });
       
        backButton.setTranslateX(-150);
        backButton.setTranslateY(75);
        backButton.setPrefSize(80, 30);
        backButton.setFont(Font.font("Dialog", 12));
        backButton.setText("Voltar");
        root.getChildren().add(backButton);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                start(stage);
            }
        });
    }
    
    //PAINEL PARA ESCOLHER JOGADOR - A FUNCIONAR (PHASE 3 COMPLETE)
    public void choosePlayerFX(Stage oldStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        Button backButton = new Button();                                       //Botão do JavaFX
        Button chooseButton = new Button();                                     //Botão do JavaFX
        Text labelOne = new Text();
        Text labelTwo = new Text();
        TextField nicknameField = new TextField();
        
        stage.setTitle("Escolher jogador");
        stage.setScene(scene);
        stage.show();
        
        labelOne.setText("Escolher jogador");
        labelOne.setTranslateX(0);
        labelOne.setTranslateY(-75);
        labelOne.setFill(Color.BLACK);
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelOne);
        
        labelTwo.setText("Nome:");
        labelTwo.setTranslateX(-80);
        labelTwo.setTranslateY(0);
        labelTwo.setFill(Color.BLACK);
        labelTwo.setFont(Font.font("Dialog", 12));
        root.getChildren().add(labelTwo);
               
        nicknameField.setTranslateX(0);
        nicknameField.setTranslateY(0);
        nicknameField.setPrefWidth(100);
        nicknameField.setMaxWidth(100);
        root.getChildren().add(nicknameField);
        
        chooseButton.setTranslateX(130);
        chooseButton.setTranslateY(75);
        chooseButton.setPrefSize(120, 30);
        chooseButton.setFont(Font.font("Dialog", 12));
        chooseButton.setText("Escolher Jogador");
        root.getChildren().add(chooseButton);
        chooseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nicknameField.getText() == null || nicknameField.getText().trim().isEmpty())
                    createAlert("Alerta","Falta de dados","Tem que preencher o nome do jogador.",AlertType.WARNING);
                else{
                    player.choosePlayer(nicknameField.getText().toString().trim(), stage);
                }     
            }
        });
        
        backButton.setTranslateX(-150);
        backButton.setTranslateY(75);
        backButton.setPrefSize(80, 30);
        backButton.setFont(Font.font("Dialog", 12));
        backButton.setText("Voltar");
        root.getChildren().add(backButton);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                start(stage);
            }
        });
    }
    
    //PAINEL DE MENU - A FUNCIONAR (PHASE 3 COMPLETE)
    public void menuFX(Stage oldStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 280);
        Stage stage = new Stage();
        Button startGameButton = new Button();                                        //Botão do JavaFX
        Button loadGameButton = new Button();                                         //Botão do JavaFX
        Button personalScoreButton = new Button();                                         //Botão do JavaFX
        Button generalScoreButton = new Button();                                         //Botão do JavaFX
        Button levelCreationButton = new Button();                                         //Botão do JavaFX
        Button helpButton = new Button();                                         //Botão do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button exitButton = new Button();                                         //Botão do JavaFX
        Text labelOne = new Text();
        Text labelTwo = new Text();
        Text labelThree = new Text();
        
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
        
        labelOne.setText("Olá " + players.get(playerID).getNickname());
        labelOne.setTranslateX(-100);
        labelOne.setTranslateY(-120);
        labelOne.setFill(Color.BLACK);
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelOne);
        
        labelTwo.setText("Escolha uma opção:");
        labelTwo.setTranslateX(0);
        labelTwo.setTranslateY(-82);
        labelTwo.setFill(Color.BLACK);
        labelTwo.setFont(Font.font("Dialog", 14));
        root.getChildren().add(labelTwo);
        
        labelThree.setText("Pontos: " + players.get(playerID).getScore().getPoints());
        labelThree.setTranslateX(105);
        labelThree.setTranslateY(-120);
        labelThree.setFill(Color.GREEN);
        labelThree.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelThree);
              
        startGameButton.setTranslateX(-70);
        startGameButton.setTranslateY(-40);
        startGameButton.setPrefSize(120, 30);
        startGameButton.setFont(Font.font("Dialog", 12));
        startGameButton.setText("Iniciar Jogo");
        root.getChildren().add(startGameButton);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                chooseDifficultyFX(stage);
            }
        });
        
        //Botão para carregar o jogo
        loadGameButton.setTranslateX(70);
        loadGameButton.setTranslateY(-40);
        loadGameButton.setPrefSize(120, 30);
        loadGameButton.setFont(Font.font("Dialog", 12));
        loadGameButton.setText("Carregar Jogo");
        loadGameButton.setDisable(true);
        root.getChildren().add(loadGameButton);
        
        personalScoreButton.setTranslateX(-70);
        personalScoreButton.setTranslateY(0);
        personalScoreButton.setPrefSize(120, 30);
        personalScoreButton.setFont(Font.font("Dialog", 12));
        personalScoreButton.setText("Consultar Pontos");
        root.getChildren().add(personalScoreButton);
        personalScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createAlert("Pontuação", players.get(playerID).getNickname(), "A sua pontuação é de " + players.get(playerID).getScore().getPoints() + " pontos.\n"
                        + "O seu recorde é de " + players.get(playerID).getScore().getHigh() + " pontos.", AlertType.INFORMATION);
            }
        });
        
        generalScoreButton.setTranslateX(70);
        generalScoreButton.setTranslateY(0);
        generalScoreButton.setPrefSize(120, 30);
        generalScoreButton.setFont(Font.font("Dialog", 12));
        generalScoreButton.setText("Pontuação Geral");
        root.getChildren().add(generalScoreButton);
        generalScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String result="";
                for(i=0;i<players.size();i++){
                    result = result + "Jogador: " + players.get(i).getNickname() + " - " + players.get(i).getScore().getPoints() + " pontos\n";
                }
                createAlert("Pontuação", "Pontuação", "Pontuações:\n" + result, AlertType.INFORMATION);
                
            }
        });
        
        levelCreationButton.setTranslateX(-70);
        levelCreationButton.setTranslateY(40);
        levelCreationButton.setPrefSize(120, 30);
        levelCreationButton.setFont(Font.font("Dialog", 12));
        levelCreationButton.setText("Criar Nivel");
        levelCreationButton.setDisable(true);
        root.getChildren().add(levelCreationButton);
        
        helpButton.setTranslateX(70);
        helpButton.setTranslateY(40);
        helpButton.setPrefSize(120, 30);
        helpButton.setFont(Font.font("Dialog", 12));
        helpButton.setText("Ajuda");
        root.getChildren().add(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpMenuFX(stage);
            }
        });
        
        //Botão para voltar atrás
        backButton.setTranslateX(-70);
        backButton.setTranslateY(80);
        backButton.setPrefSize(120, 30);
        backButton.setFont(Font.font("Dialog", 12));
        backButton.setText("Voltar");
        root.getChildren().add(backButton);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                start(stage);
            }
        });
        
        //Botão para voltar atrás
        exitButton.setTranslateX(70);
        exitButton.setTranslateY(80);
        exitButton.setPrefSize(120, 30);
        exitButton.setFont(Font.font("Dialog", 12));
        exitButton.setText("Sair");
        root.getChildren().add(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    
    //PAINEL DE DIFICULDADE - POR FAZER (PHASE 3 POR FAZER)
    public void chooseDifficultyFX(Stage oldStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        Button backButton = new Button();                                       //Botão do JavaFX
        Button easyButton = new Button();
        Button mediumButton = new Button();
        Button hardButton = new Button();
        Text labelOne = new Text();
        Text labelTwo = new Text();
        
        stage.setTitle("Escolher dificuldade");
        stage.setScene(scene);
        stage.show();
        
        labelOne.setText("Escolher dificuldade");
        labelOne.setTranslateX(0);
        labelOne.setTranslateY(-80);
        labelOne.setFill(Color.BLACK);
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelOne);
        
        labelTwo.setText("Escolha uma dificuldade:");
        labelTwo.setTranslateX(0);
        labelTwo.setTranslateY(-45);
        labelTwo.setFill(Color.BLACK);
        labelTwo.setFont(Font.font("Dialog", 14));
        root.getChildren().add(labelTwo);
        
        easyButton.setTranslateX(0);
        easyButton.setTranslateY(-5);
        easyButton.setPrefSize(120, 30);
        easyButton.setFont(Font.font("Dialog", 12));
        easyButton.setText("Fácil");
        root.getChildren().add(easyButton);
        easyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.resetValue();
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_EASY;
                game.NUMBER_OF_ROWS = 4;
                game.NUMBER_OF_COLUMNS = 4;
                stage.close();
                game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                displayBoardFX(stage);
                printConsole(); // Exibe o tabuleiro de jogo
                //play();
            }
        });
        
        mediumButton.setTranslateX(0);
        mediumButton.setTranslateY(35);
        mediumButton.setPrefSize(120, 30);
        mediumButton.setFont(Font.font("Dialog", 12));
        mediumButton.setText("Médio");
        root.getChildren().add(mediumButton);
        mediumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.resetValue();
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_MEDIUM;
                game.NUMBER_OF_ROWS = 6;
                game.NUMBER_OF_COLUMNS = 6;
                stage.close();
                game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                displayBoardFX(stage);
                printConsole(); // Exibe o tabuleiro de jogo
                //play();
            }
        });       
        
        hardButton.setTranslateX(0);
        hardButton.setTranslateY(75);
        hardButton.setPrefSize(120, 30);
        hardButton.setFont(Font.font("Dialog", 12));
        hardButton.setText("Difícil");
        root.getChildren().add(hardButton);
        hardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.resetValue();
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_HARD;
                game.NUMBER_OF_ROWS = 9;
                game.NUMBER_OF_COLUMNS = 9;
                stage.close();
                game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                displayBoardFX(stage);
                printConsole(); // Exibe o tabuleiro de jogo
                //play();
            }
        });
        
        backButton.setTranslateX(-150);
        backButton.setTranslateY(75);
        backButton.setPrefSize(80, 30);
        backButton.setFont(Font.font("Dialog", 12));
        backButton.setText("Voltar");
        root.getChildren().add(backButton);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                game.menuFX(oldStage);
            }
        });
    }
    
    //MENU PARA ESCOLHA DE DIFICULDADE - REVER
    public void chooseDifficultyConsole() {       
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
                    game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    printConsole();
                    playConsole();
                }
                if(array[index] == 7){ //caso seja o 7 o escolhido, gera um tabuleiro medio
                    game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_MEDIUM;
                    game.NUMBER_OF_ROWS = 6;
                    game.NUMBER_OF_COLUMNS = 6;
                    game.players.get(game.playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    printConsole();
                    playConsole();
                }
                if(array[index] == 10){    //caso seja o 10 o escolhido, gera um tabuleiro dificil
                    game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_HARD;
                    game.NUMBER_OF_ROWS = 9;
                    game.NUMBER_OF_COLUMNS = 9;
                    game.players.get(game.playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    printConsole();
                    playConsole();
                }
                break;
            case 2: // Criação de um tabuleiro de game de dificuldade "Fácil"
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_EASY;
                game.NUMBER_OF_ROWS = 4;
                game.NUMBER_OF_COLUMNS = 4;
                game.players.get(game.playerID).getScore().setPoints(50); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    printConsole();
                    playConsole();
                break;
            case 3: // Criação de um tabuleiro de game de dificuldade "Médio"
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_MEDIUM;
                game.NUMBER_OF_ROWS = 6;
                game.NUMBER_OF_COLUMNS = 6;
                game.players.get(game.playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    printConsole();
                    playConsole();
                break;
            case 4: // Criação de um tabuleiro de game de dificuldade "Difícil"
                game.SET_DIFFICULTY = game.DIFFICULTY_BOARD_HARD;
                game.NUMBER_OF_ROWS = 9;
                game.NUMBER_OF_COLUMNS = 9;
                game.players.get(game.playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    printConsole();
                    playConsole();
                break;
            case 0:
                game.startConsole(); // Retrocede-se ao menuOLD principal
                break;
            default:
                chooseDifficultyConsole(); // Caso não seja selecionada nenhuma das opções disponíveis, é exibido novamente o menuOLD de  escolha de dificuldade
                break;
        }
    }
    
    //PAINEL DE AJUDA - POR FAZER (PHASE 3 POR FAZER)
    public void helpMenuFX(Stage oldStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        Button backButton = new Button();                                       //Botão do JavaFX
        Text labelOne = new Text();
        Text labelTwo = new Text();        
        stage.setTitle("Ajuda");
        stage.setScene(scene);
        stage.show();
        
        labelOne.setText("Ajuda");
        labelOne.setTranslateX(0);
        labelOne.setTranslateY(-75);
        labelOne.setFill(Color.BLACK);
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        root.getChildren().add(labelOne);
        
        labelTwo.setText("Nome:");
        labelTwo.setTranslateX(-80);
        labelTwo.setTranslateY(0);
        labelTwo.setFill(Color.BLACK);
        labelTwo.setFont(Font.font("Dialog", 12));
        root.getChildren().add(labelTwo);
        
        backButton.setTranslateX(-150);
        backButton.setTranslateY(75);
        backButton.setPrefSize(80, 30);
        backButton.setFont(Font.font("Dialog", 12));
        backButton.setStyle("-fx-text-fill: white; ");
        backButton.setText("Voltar");
        root.getChildren().add(backButton);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }
        
    //PAINEL PARA APRESENTAR O TABULEIRO
    public void displayBoardFX(Stage oldStage) {
        int SCENE_WIDTH = 0;
        int SCENE_HEIGHT = 0;
        
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_EASY) {
            SCENE_WIDTH = 400;
            SCENE_HEIGHT = 400;
            counter = 30;
            seconds = 30;
        }
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_MEDIUM) {
            SCENE_WIDTH = 500;
            SCENE_HEIGHT = 500;
            counter = 90;
            seconds = 90;
        }
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_HARD) {
            SCENE_WIDTH = 600;
            SCENE_HEIGHT = 600;
            counter = 150;
            seconds = 150;
        }
        
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        Text pointsLabel = new Text();
        Text timerLabel = new Text();
        Button backButton = new Button();                                       //Botão do JavaFX
        Button validateButton = new Button();
        stage.setTitle("Tabuleiro de Jogo");
        stage.setScene(scene);
        stage.show();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerLabel.setText(String.valueOf(counter) + " segundos");
                counter--;
                if(counter==-1){
                    System.out.println("Timer chegou ao fim");
                    timer.cancel();
                }
            }
        }, 0, 1000);

        for (i = 0; i < gameBoard.size(); i++) {
            buttons.add(new Button());
        }

        for (i = 0; i <= NUMBER_OF_ROWS; i++) {
            for (j = 0; j <= NUMBER_OF_COLUMNS; j++) {
                Button btn = new Button();
                gridPane.add(btn, j, i);
                int x = GridPane.getRowIndex(btn);
                int y = GridPane.getColumnIndex(btn);
                btn.setText(gameBoard.get(board.getIndex(x, y)).toString());
                btn.setFocusTraversable(false);
                btn.setPrefSize(30, 30);
                btn.setOnAction(actionEvent -> {
                    btn.setDisable(true);
                    position = board.getIndex(x, y);
                    if(boat.size()>=SET_DIFFICULTY-1){
                        validateButton.setDisable(false);
                    }
                    if (!(gameBoard.get(position) instanceof Dock) && !(gameBoard.get(position) instanceof Boat)) {
                        if (rules.checkSpotForBoat(x, y)) {
                            board.placeBoat(position);
                            btn.setText("B");
                        } else {
                            players.get(playerID).getScore().missedBoat();
                            attempts++;
                            board.placeWater(position);
                            btn.setText("A");
                            createAlert("Aviso", "Casa errada", "Não pode colocar um barco nessa posição.\nFoi punido em x pontos", AlertType.WARNING);
                        }
                    } else {
                        attempts++;
                        createAlert("Aviso", "Casa ocupada", "Essa posição já se encontra ocupada.\nFoi punido em x pontos", AlertType.WARNING);
                    }
                    pointsLabel.setText("Pontuação: " + players.get(playerID).getScore().getPoints() + " pontos");
                    printConsole();
                });
            }
        }

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        pointsLabel.setFill(Color.GREEN);
        pointsLabel.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        pointsLabel.setText("Pontuação: " + players.get(playerID).getScore().getPoints() + " pontos");
        gridPane.add(pointsLabel, 0, NUMBER_OF_ROWS + 3, NUMBER_OF_COLUMNS + 1, 1);
        
        timerLabel.setFill(Color.RED);
        timerLabel.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
        gridPane.add(timerLabel, 0, NUMBER_OF_ROWS + 4, NUMBER_OF_COLUMNS + 1, 1);

        backButton.setPrefSize(70, 30);
        backButton.setFont(Font.font("Dialog", 12));
        backButton.setText("Voltar");
        gridPane.add(backButton, 0, NUMBER_OF_ROWS + 5, 2, 1);

        validateButton.setPrefSize(70, 30);
        validateButton.setFont(Font.font("Dialog", 12));
        validateButton.setText("Validar");
        validateButton.setDisable(true);
        gridPane.add(validateButton, NUMBER_OF_COLUMNS - 1, NUMBER_OF_ROWS + 5, 2, 1);     

        ButtonType yes = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.WARNING, "Tem a certeza que quer sair do tabuleiro?\nVai perder todo o seu progresso.", yes, cancel);
        alert.setTitle("Atenção");

        backButton.setOnAction(e -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(cancel) == yes) {
                players.get(playerID).getScore().setPoints(0);
                stage.close();
                oldStage.show();
            }
        });

        validateButton.setOnAction(e -> {
            timer.cancel();
            board.endValidateBoard((seconds-counter));
        });
    }
      
    //PAINEL DE ALERT (DIALOG BOX) - A FUNCIONAR (PHASE 3 COMPLETE)
    public void createAlert(String title, String header, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    //JAVA CONSOLE
   
    //IMPRIME O MENU PRINCIPAL - A FUNCIONAR
    public void startConsole() {
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
                    player.createPlayer();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    startConsole(); // no caso de não ser escolhida nenhuma das opções facultadas ao utilizador, volta ao menuOLD principal
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
                    player.createPlayer();
                    break;
                case 2:
                    player.choosePlayer();
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
    public void menuConsole() {
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
                if (gameBoard.isEmpty()) {
                    chooseDifficultyConsole(); // Redireciona o utilizar para o menuOLD de escolha da dificuldade do jogo a ser jogado
                } else {
                    printConsole();
                }
                break;
            case 2:
                System.out.println("Funcionalidade indisponivel. Obrigado.");
                //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//                try {
//                    throw new BoatsIllegalArgumentException(ErrorCode.NO_FUNCTION.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                menuConsole();
                break;
            case 3:
                score.printPlayerScore(players.get(playerID)); // Exibe as pontuações pessoais do utilizador
                break;
            case 4:
                score.printScore(); // Exibe a pontuação geral dos jogadores 
                break;
            case 5:
                System.out.println("Funcionalidade indisponivel. Obrigado.");
                //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//                try {
//                    throw new BoatsIllegalArgumentException(ErrorCode.NO_FUNCTION.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                menuConsole();
                break;
            case 6:
                printHelpConsole(); // O utilizador é redirecionado para um menuOLD de ajuda à compreensão das diferentes siglas inerentes ao jogo
                break;
            case 7:
                game.startConsole(); // Retrocede-se ao menuOLD principal
                break;
            case 0:
                System.exit(0); // O programa é finalizado
                break;
            default:
                //
                break;
        }
    }

    //IMPRIME AJUDA PARA O JOGO - A  FUNCIONAR
    public void printHelpConsole() { // Exibe a legenda para as siglas do jogo
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
        } catch (Exception e) {
        }
        menuConsole(); // Retrocede-se ao menuOLD
    }

    //IMPRIME O TABULEIRO FINAL - A FUNCIONAR
    public void printEndConsole() {
        System.out.println("O jogo terminou, e você terminou com " + players.get(playerID).getScore().getPoints() + " pontos");
        System.out.println("Prima enter para sair do jogo.");
        try {
            System.in.read(); // É lida a tecla premida pelo utilizador
        } catch (Exception e) {
        }
        System.exit(0);// Retrocede-se ao menuOLD
    }

    //IMPRIME O TABULEIRO DE JOGO - A FUNCIONAR
    public void printConsole() {
        System.out.println("\nTabuleiro de Jogo:");
        for (i = 0; i < gameBoard.size(); i++) { // Criação do tabuleiro de jogo
            System.out.print("|");
            System.out.print(gameBoard.get(i).toString());
            if (gameBoard.get(i).getPosition().getColumn() == (NUMBER_OF_COLUMNS)) { // É criado um tabuleiro correspondente à dificuldade selecionada
                System.out.print("|");
                System.out.println();
            }
        }
    }

    //JOGADAS DO UTILIZADOR - A FUNCIONAR
    public void playConsole() {
        System.out.println("-----------");
        boolean done = false;
        String move = "";
        int x = 0;
        int y = 0;
        System.out.println("Escreva \"sair\" para sair e \"validar\" para validar e terminar o jogo:");

        System.out.println("Campo (A ou B):");
        while (done == false) {
            try {
                System.out.print("-> ");
                move = input.next();
                if (move.equals("sair") || move.equals("validar")) {
                    if (move.equals("sair")) {
                        menuConsole();
                    }
                    if (move.equals("validar")) {
                        //endValidateBoard();
                    }
                } else {
                    if (move.equals("A") || move.equals("B")) {
                        done = true;
                    }
                }
            } catch (Exception e) {
            }
        }

        done = false;

        System.out.println("Linha (x):");
        while (done == false) {
            try {
                System.out.print("-> ");
                String string = input.next();
                if (string.equals("sair") || string.equals("validar")) {
                    if (string.equals("sair")) {
                        menuConsole();
                    }
                    if (string.equals("validar")) {
                        //endValidateBoard();
                    }
                } else {
                    if (Integer.parseInt(string) >= 0 && Integer.parseInt(string) <= NUMBER_OF_ROWS) {
                        x = Integer.parseInt(string);
                        done = true;
                    }
                }
            } catch (Exception e) {
            }
        }

        done = false;

        System.out.println("Coluna (y):");
        while (done == false) {
            try {
                System.out.print("-> ");
                String string = input.next();
                if (string.equals("sair") || string.equals("validar")) {
                    if (string.equals("sair")) {
                        menuConsole();
                    }
                    if (string.equals("validar")) {
                        //endValidateBoard();
                    }
                } else {
                    if (Integer.parseInt(string) >= 0 && Integer.parseInt(string) <= NUMBER_OF_ROWS) {
                        y = Integer.parseInt(string);
                        done = true;
                    }
                }
            } catch (Exception e) {
            }
        }

        done = false;

        int position = board.getIndex(x, y);
        if (!(gameBoard.get(position) instanceof Dock) && !(gameBoard.get(position) instanceof Boat)) {
            if (move.equals("B")) {
                if (rules.checkSpotForBoat(x, y)) {
                    board.placeBoat(position);
                } else {
                    score.missedBoat();
                    attempts++;
                    System.out.println("Não pode colocar um barco nessa posição.");
                    //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//                    try {
//                        throw new BoatsIllegalArgumentException(ErrorCode.BOAT_CANT_POSITION.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }
            if (move.equals("A")) {
                board.placeWater(position);
            }
        } else {
            attempts++;
            System.out.println("Essa posição já se encontra ocupada.");
            //Não foram utilizadas as exceções visto que interrompiam o programa aquando necessitadas, funcionam, mas estão em comentário
//            try {
//                throw new BoatsIllegalArgumentException(ErrorCode.POSITION_OCCUPIED.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
}
