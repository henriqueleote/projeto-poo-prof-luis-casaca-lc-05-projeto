package game;

import player.Player;
import player.Score;
import board.Dock;
import board.Spot;
import board.Boat;
import board.Board;
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
public class Game extends Application {

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
    public static int attempts;                                                 //Variavel inteira para a dificuldade facil
    public static int position = 0;                                             //Variavel inteira para a posição
    public static int counter;                                                  //Variavel inteira para o timer
    public static int seconds;                                                  //Variavel inteira para contar os segundos

    public static List<Spot> gameBoard = new ArrayList<Spot>();                                 //Coleção da classe Spot para o tabuleiro
    public static ObservableList<Player> players = FXCollections.observableArrayList();        //Coleção da classe Player para os jogadores
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
    public Game() {
    }

    //MAIN - A FUNCIONAR (PHASE 3 COMPLETE)
    public static void main(String[] args) {
        launch(args);
    }

    //JAVAFX
    
    
    //PAINEL PRINCIPAL - A FUNCIONAR (PHASE 3 COMPLETE)
    public void start(Stage stage) {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 200);                                //Scene do JavaFX
        Button fxButton = new Button();                                         //Botão do JavaFX
        Button consoleButton = new Button();                                    //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX

        stage.setTitle("Docks & Boats");                                        //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Como pretende jogar?");                               //Definir texto na label
        labelOne.setTranslateX(0);                                              //Definir posição x do texto
        labelOne.setTranslateY(-50);                                            //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        fxButton.setText("JavaFX");                                             //Definir texto do botão                                         
        fxButton.setPrefSize(120, 30);                                          //Definir tamanho do botão
        fxButton.setTranslateX(-70);                                            //Definir posição x do botão
        fxButton.setTranslateY(20);                                             //Definir posição y do botão
        fxButton.setFont(Font.font("Dialog", 12));                              //Definir fonte e tamanho do botão
        root.getChildren().add(fxButton);                                       //Adicionar o botão ao stack pane
        fxButton.setOnAction(event -> {                                         //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            startFX();                                                      //Abrir o menu de inicio
        });

        consoleButton.setText("Consola");                                       //Definir texto do botão 
        consoleButton.setPrefSize(80, 30);                                      //Definir tamanho do botão
        consoleButton.setTranslateX(90);                                        //Definir posição x do botão
        consoleButton.setTranslateY(20);                                        //Definir posição y do botão
        consoleButton.setFont(Font.font("Dialog", 12));                         //Definir fonte e tamanho do botão
        root.getChildren().add(consoleButton);                                  //Adicionar o botão ao stack pane
        consoleButton.setOnAction(event -> {                                         //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            startConsole();                                                 //Abrir a consola
        });
    }

    //PAINEL INICIAL - A FUNCIONAR (PHASE 3 COMPLETE)
    public void startFX() {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 200);                                //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button createPlayerButton = new Button();                               //Botão do JavaFX
        Button choosePlayerButton = new Button();                               //Botão do JavaFX
        Button exitButton = new Button();                                       //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX

        stage.setTitle("Docks & Boats");                                        //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Seja bem-vindo ao Docks & Boats");                    //Definir texto na label
        labelOne.setTranslateX(0);                                              //Definir posição x do texto
        labelOne.setTranslateY(-50);                                            //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        createPlayerButton.setText("Criar Jogador");                            //Definir texto do botão 
        createPlayerButton.setPrefSize(120, 30);                                //Definir tamanho do botão
        createPlayerButton.setTranslateX(-70);                                  //Definir posição x do botão
        createPlayerButton.setTranslateY(0);                                    //Definir posição y do botão
        createPlayerButton.setFont(Font.font("Dialog", 12));                    //Definir fonte e tamanho do botão
        root.getChildren().add(createPlayerButton);                             //Adicionar o botão ao stack pane
        createPlayerButton.setOnAction(event -> {                               //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            createPlayerFX(stage);                                          //Abrir a consola
        });

        choosePlayerButton.setText("Escolher Jogador");                         //Definir texto do botão 
        choosePlayerButton.setPrefSize(120, 30);                                //Definir tamanho do botão
        choosePlayerButton.setTranslateX(-70);                                  //Definir posição x do botão
        choosePlayerButton.setTranslateY(40);                                   //Definir posição y do botão
        choosePlayerButton.setFont(Font.font("Dialog", 12));                    //Definir fonte e tamanho do botão
        root.getChildren().add(choosePlayerButton);                             //Adicionar o botão ao stack pane
        choosePlayerButton.setOnAction(event -> {                               //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            choosePlayerFX(stage);                                          //Abrir a consola
        });

        if (players.isEmpty()) {                                                //Caso ainda não haja um jogador
            choosePlayerButton.setVisible(false);                               //O botão fica desativado
            createPlayerButton.setTranslateX(-70);                              //Definir posição x do botão
            createPlayerButton.setTranslateY(20);                               //Definir posição y do botão
        }

        exitButton.setText("Sair");                                             //Definir texto do botão 
        exitButton.setPrefSize(80, 30);                                         //Definir tamanho do botão
        exitButton.setTranslateX(90);                                           //Definir posição x do botão
        exitButton.setTranslateY(20);                                           //Definir posição y do botão
        exitButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(exitButton);                                     //Adicionar o botão ao stack pane
        exitButton.setOnAction(event -> {                                       //Quando se carrega no botão
            System.exit(0);                                                 //Sai do jogo
        });
    }

    //PAINEL PARA CRIAR JOGADOR - A FUNCIONAR (PHASE 3 COMPLETE)
    public void createPlayerFX(Stage oldStage) {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 200);                                //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button createButton = new Button();                                     //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                              //Texto do JavaFX
        TextField nicknameField = new TextField();                              //TextField do JavaFX

        stage.setTitle("Criar jogador");                                        //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Criar jogador");                                      //Definir texto na label
        labelOne.setTranslateX(0);                                              //Definir posição x do texto
        labelOne.setTranslateY(-75);                                            //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        labelTwo.setText("Nome:");                                              //Definir texto na label
        labelTwo.setTranslateX(-80);                                            //Definir posição x do texto
        labelTwo.setTranslateY(0);                                              //Definir posição y do texto
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        labelTwo.setFont(Font.font("Dialog", 12));                              //Definir tamanho do texto
        root.getChildren().add(labelTwo);                                       //Adicionar o texto ao stack pane

        nicknameField.setTranslateX(0);                                         //Definir posição x do textField
        nicknameField.setTranslateY(0);                                         //Definir posição y do textField
        nicknameField.setPrefWidth(100);                                        //Definir tamanho do textField
        nicknameField.setMaxWidth(100);                                         //Definir tamanho do textField
        root.getChildren().add(nicknameField);                                  //Adicionar o textField ao stack pane

        createButton.setText("Criar Jogador");                                  //Definir texto do botão 
        createButton.setPrefSize(110, 30);                                      //Definir tamanho do botão
        createButton.setTranslateX(130);                                        //Definir posição x do botão
        createButton.setTranslateY(75);                                         //Definir posição y do botão
        createButton.setFont(Font.font("Dialog", 12));                          //Definir fonte e tamanho do botão
        root.getChildren().add(createButton);                                   //Adicionar o botão ao stack pane
        createButton.setOnAction(event -> {                                     //Quando se carrega no botão
            if (nicknameField.getText() == null || nicknameField.getText().trim().isEmpty()) //Verifica se o textField está vazio
            {
                createAlert("Alerta", "Falta de dados", "Tem que preencher o nome do jogador.", AlertType.WARNING);        //Cria um alerta personalizado de erro
            } else {
                player.createPlayer(nicknameField.getText().toString().trim(), stage);                                   //Invoca o metodo para criar o jogador
            }
        });

        backButton.setText("Voltar");                                           //Definir texto do botão 
        backButton.setPrefSize(80, 30);                                         //Definir tamanho do botão
        backButton.setTranslateX(-150);                                         //Definir posição x do botão
        backButton.setTranslateY(75);                                           //Definir posição y do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(backButton);                                     //Adicionar o botão ao stack pane
        backButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                      //Fechar janela atual
            startFX();                                                          //Abre o menu
        });
    }

    //PAINEL PARA ESCOLHER JOGADOR - A FUNCIONAR (PHASE 3 COMPLETE)
    public void choosePlayerFX(Stage oldStage) {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 200);                                //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button chooseButton = new Button();                                     //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                             //Texto do JavaFX
        TextField nicknameField = new TextField();                              //TextField do JavaFX

        stage.setTitle("Escolher jogador");                                     //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Escolher jogador");                                   //Definir texto na label
        labelOne.setTranslateX(0);                                              //Definir posição x do texto
        labelOne.setTranslateY(-75);                                            //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        labelTwo.setText("Nome:");                                              //Definir texto na label
        labelTwo.setTranslateX(-80);                                            //Definir posição x do texto
        labelTwo.setTranslateY(0);                                              //Definir posição y do texto
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        labelTwo.setFont(Font.font("Dialog", 12));                              //Definir tamanho do texto
        root.getChildren().add(labelTwo);                                       //Adicionar o texto ao stack pane

        nicknameField.setTranslateX(0);                                         //Definir posição x do textField
        nicknameField.setTranslateY(0);                                         //Definir posição y do textField
        nicknameField.setPrefWidth(100);                                        //Definir tamanho do textField
        nicknameField.setMaxWidth(100);                                         //Definir tamanho do textField
        root.getChildren().add(nicknameField);                                  //Adicionar o textField ao stack pane

        chooseButton.setText("Escolher Jogador");                               //Definir texto do botão 
        chooseButton.setPrefSize(120, 30);                                      //Definir tamanho do botão
        chooseButton.setTranslateX(130);                                        //Definir posição x do botão
        chooseButton.setTranslateY(75);                                         //Definir posição y do botão
        chooseButton.setFont(Font.font("Dialog", 12));                          //Definir fonte e tamanho do botão
        root.getChildren().add(chooseButton);                                   //Adicionar o botão ao stack pane
        chooseButton.setOnAction(event -> {                                     //Quando se carrega no botão
            if (nicknameField.getText() == null || nicknameField.getText().trim().isEmpty()) //Verifica se o textField está vazio
            {
                createAlert("Alerta", "Falta de dados", "Tem que preencher o nome do jogador.", AlertType.WARNING);        //Cria um alerta personalizado de erro
            } else {
                player.choosePlayer(nicknameField.getText().toString().trim(), stage);                                   //Invoca o metodo para escolher o jogador 
            }
        });

        backButton.setText("Voltar");                                           //Definir texto do botão 
        backButton.setPrefSize(80, 30);                                         //Definir tamanho do botão
        backButton.setTranslateX(-150);                                         //Definir posição x do botão
        backButton.setTranslateY(75);                                           //Definir posição y do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(backButton);                                     //Adicionar o botão ao stack pane
        backButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            startFX();                                                      //Abre o menu
        });
    }

    //PAINEL DE MENU - A FUNCIONAR (PHASE 3 COMPLETE)
    public void menuFX(Stage oldStage) {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 300);                                //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button startGameButton = new Button();                                  //Botão do JavaFX
        Button loadGameButton = new Button();                                   //Botão do JavaFX
        Button personalScoreButton = new Button();                              //Botão do JavaFX
        Button generalScoreButton = new Button();                               //Botão do JavaFX
        Button levelCreationButton = new Button();                              //Botão do JavaFX
        Button helpButton = new Button();                                       //Botão do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button exitButton = new Button();                                       //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                             //Texto do JavaFX
        Text labelThree = new Text();                                           //Texto do JavaFX

        stage.setTitle("Menu");                                                 //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Olá " + players.get(playerID).getNickname());         //Definir texto na label
        labelOne.setTranslateX(-100);                                           //Definir posição x do texto
        labelOne.setTranslateY(-120);                                           //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        labelTwo.setText("Escolha uma opção:");                                 //Definir texto na label
        labelTwo.setTranslateX(0);                                              //Definir posição x do texto
        labelTwo.setTranslateY(-82);                                            //Definir posição y do texto
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        labelTwo.setFont(Font.font("Dialog", 14));                              //Definir tamanho do texto
        root.getChildren().add(labelTwo);                                       //Adicionar o texto ao stack pane

        labelThree.setText("Pontos: " + players.get(playerID).getScore().getPoints());      //Definir texto na label
        labelThree.setTranslateX(105);                                                      //Definir posição x do texto
        labelThree.setTranslateY(-120);                                                     //Definir posição y do texto
        labelThree.setFill(Color.GREEN);                                                    //Definir cor do texto
        labelThree.setFont(Font.font("Dialog", FontWeight.BOLD, 16));                       //Definir tamanho do texto
        root.getChildren().add(labelThree);                                                 //Adicionar o texto ao stack pane

        startGameButton.setText("Iniciar Jogo");                                //Definir texto do botão 
        startGameButton.setPrefSize(120, 30);                                   //Definir tamanho do botão
        startGameButton.setTranslateX(-70);                                     //Definir posição x do botão
        startGameButton.setTranslateY(-40);                                     //Definir posição y do botão
        startGameButton.setFont(Font.font("Dialog", 12));                       //Definir fonte e tamanho do botão
        root.getChildren().add(startGameButton);                                //Adicionar o botão ao stack pane
        startGameButton.setOnAction(event -> {                                  //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            chooseDifficultyFX(stage);                                      //Abre o menu
        });

        loadGameButton.setText("Carregar Jogo");                                //Definir texto do botão 
        loadGameButton.setPrefSize(120, 30);                                    //Definir tamanho do botão
        loadGameButton.setTranslateX(70);                                       //Definir posição x do botão
        loadGameButton.setTranslateY(-40);                                      //Definir posição y do botão
        loadGameButton.setFont(Font.font("Dialog", 12));                        //Definir fonte e tamanho do botão
        loadGameButton.setDisable(true);                                        //Definir botão como desativado
        root.getChildren().add(loadGameButton);                                 //Adicionar o botão ao stack pane

        personalScoreButton.setText("Consultar Pontos");                        //Definir texto do botão 
        personalScoreButton.setPrefSize(120, 30);                               //Definir tamanho do botão
        personalScoreButton.setTranslateX(-70);                                 //Definir posição x do botão
        personalScoreButton.setTranslateY(0);                                   //Definir posição y do botão
        personalScoreButton.setFont(Font.font("Dialog", 12));                   //Definir fonte e tamanho do botão
        root.getChildren().add(personalScoreButton);                            //Adicionar o botão ao stack pane
        personalScoreButton.setOnAction(event -> {                              //Quando se carrega no botão
            createAlert("Pontuação", players.get(playerID).getNickname(), "A sua pontuação é de " + players.get(playerID).getScore().getPoints() + " pontos.\n"
            + "O seu recorde é de " + players.get(playerID).getScore().getHigh() + " pontos.", AlertType.INFORMATION);          //Cria um alerta personalizado de erro
        });

        generalScoreButton.setText("Pontuação Geral");                          //Definir texto do botão 
        generalScoreButton.setPrefSize(120, 30);                                //Definir tamanho do botão
        generalScoreButton.setTranslateX(70);                                   //Definir posição x do botão
        generalScoreButton.setTranslateY(0);                                    //Definir posição y do botão
        generalScoreButton.setFont(Font.font("Dialog", 12));                    //Definir fonte e tamanho do botão
        root.getChildren().add(generalScoreButton);                             //Adicionar o botão ao stack pane
        generalScoreButton.setOnAction(event -> {                               //Quando se carrega no botão
            String result = "";
            for (i = 0; i < players.size(); i++) {
                result = result + "Jogador: " + players.get(i).getNickname() + " - " + players.get(i).getScore().getPoints() + " pontos\n";
            }
            createAlert("Pontuação", "Pontuação", "Pontuações:\n" + result, AlertType.INFORMATION);     //Cria uma mensagem com as pontuações

        });

        levelCreationButton.setText("Criar Nivel");                             //Definir texto do botão 
        levelCreationButton.setPrefSize(120, 30);                               //Definir tamanho do botão
        levelCreationButton.setTranslateX(-70);                                 //Definir posição x do botão
        levelCreationButton.setTranslateY(40);                                  //Definir posição y do botão
        levelCreationButton.setFont(Font.font("Dialog", 12));                   //Definir fonte e tamanho do botão
        root.getChildren().add(levelCreationButton);                            //Adicionar o botão ao stack pane
        levelCreationButton.setOnAction(event -> {                              //Quando se carrega no botão
            stage.close();                                                      //Fechar janela atual
            createLevelDifficultyFX(stage);                                     //Abrir o menu de criar um nivel
        });

        helpButton.setText("Ajuda");                                            //Definir texto do botão 
        helpButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        helpButton.setTranslateX(70);                                           //Definir posição x do botão
        helpButton.setTranslateY(40);                                           //Definir posição y do botão
        helpButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(helpButton);                                     //Adicionar o botão ao stack pane
        helpButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                      //Fechar janela atual
            helpMenuFX(stage);                                                  //Abrir o menu de ajuda
        });

        backButton.setText("Voltar");                                           //Definir texto do botão 
        backButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        backButton.setTranslateX(-70);                                          //Definir posição x do botão
        backButton.setTranslateY(80);                                           //Definir posição y do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(backButton);                                     //Adicionar o botão ao stack pane
        backButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                      //Fechar janela atual
            startFX();                                                          //Abrir o menu inicial
        });

        exitButton.setText("Sair");                                             //Definir texto do botão 
        exitButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        exitButton.setTranslateX(70);                                           //Definir posição x do botão
        exitButton.setTranslateY(80);                                           //Definir posição y do botão
        exitButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(exitButton);                                     //Adicionar o botão ao stack pane
        exitButton.setOnAction(event -> {                                       //Quando se carrega no botão
            System.exit(0);                                                     //Sair do jogo
        });
    }

    //PAINEL DE DIFICULDADE - A FUNCIONAR (PHASE 3 COMPLETE)
    public void chooseDifficultyFX(Stage oldStage) {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 200);                                //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button easyButton = new Button();                                       //Botão do JavaFX
        Button mediumButton = new Button();                                     //Botão do JavaFX
        Button hardButton = new Button();                                       //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                             //Texto do JavaFX

        stage.setTitle("Escolher dificuldade");                                 //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Escolher dificuldade");                               //Definir texto na label
        labelOne.setTranslateX(0);                                              //Definir posição x do texto
        labelOne.setTranslateY(-80);                                            //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        labelTwo.setText("Escolha uma dificuldade:");                           //Definir texto na label
        labelTwo.setTranslateX(0);                                              //Definir posição x do texto
        labelTwo.setTranslateY(-45);                                            //Definir posição y do texto
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        labelTwo.setFont(Font.font("Dialog", 14));                              //Definir tamanho do texto
        root.getChildren().add(labelTwo);                                       //Adicionar o texto ao stack pane

        easyButton.setText("Fácil");                                            //Definir texto do botão 
        easyButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        easyButton.setTranslateX(0);                                            //Definir posição x do botão
        easyButton.setTranslateY(-5);                                           //Definir posição y do botão
        easyButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(easyButton);                                     //Adicionar o botão ao stack pane
        easyButton.setOnAction(event -> {                                       //Quando se carrega no botão
            board.resetValue();                                             //Definir os valores todos a 0
            SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;                         //Definir a dificuldade
            NUMBER_OF_ROWS = 4;                                             //Definir o numero de linhas
            NUMBER_OF_COLUMNS = 4;                                          //Definir o numero de colunas
            stage.close();                                                  //Fechar pagina
            players.get(playerID).getScore().setPoints(50);                 //Atribuição de 50 pontos iniciais ao jogador
            board.generateBoard();                                          //Gerar o tabuleiro
            displayBoardFX(stage);                                          //Apresentar o tabuleiro
        });

        mediumButton.setText("Médio");                                          //Definir texto do botão
        mediumButton.setPrefSize(120, 30);                                      //Definir tamanho do botão
        mediumButton.setTranslateX(0);                                          //Definir posição x do botão
        mediumButton.setTranslateY(35);                                         //Definir posição y do botão
        mediumButton.setFont(Font.font("Dialog", 12));                          //Definir fonte e tamanho do botão
        root.getChildren().add(mediumButton);                                   //Adicionar o botão ao stack pane
        mediumButton.setOnAction(event -> {                                     //Quando se carrega no botão
            board.resetValue();                                             //Definir os valores todos a 0
            SET_DIFFICULTY = DIFFICULTY_BOARD_MEDIUM;                       //Definir a dificuldade
            NUMBER_OF_ROWS = 6;                                             //Definir o numero de linhas
            NUMBER_OF_COLUMNS = 6;                                          //Definir o numero de colunas
            stage.close();                                                  //Fechar pagina
            players.get(playerID).getScore().setPoints(100);                //Atribuição de 100 pontos iniciais ao jogador
            board.generateBoard();                                          //Gerar o tabuleiro
            displayBoardFX(stage);                                          //Apresentar o tabuleiro
        });

        hardButton.setText("Difícil");                                          //Definir texto do botão
        hardButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        hardButton.setTranslateX(0);                                            //Definir posição x do botão
        hardButton.setTranslateY(75);                                           //Definir posição y do botão
        hardButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(hardButton);                                     //Adicionar o botão ao stack pane
        hardButton.setOnAction(event -> {                                       //Quando se carrega no botão
            board.resetValue();                                             //Definir os valores todos a 0
            SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;                         //Definir a dificuldade
            NUMBER_OF_ROWS = 9;                                             //Definir o numero de linhas
            NUMBER_OF_COLUMNS = 9;                                          //Definir o numero de colunas
            stage.close();                                                  //Fechar pagina
            players.get(playerID).getScore().setPoints(150);                //Atribuição de 150 pontos iniciais ao jogador
            board.generateBoard();                                          //Gerar o tabuleiro
            displayBoardFX(stage);                                          //Apresentar o tabuleiro
        });

        backButton.setText("Voltar");                                           //Definir texto do botão
        backButton.setPrefSize(80, 30);                                         //Definir tamanho do botão
        backButton.setTranslateX(-150);                                         //Definir posição x do botão
        backButton.setTranslateY(75);                                           //Definir posição y do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(backButton);                                     //Adicionar o botão ao stack pane
        backButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            menuFX(oldStage);                                               //Abrir o menu
        });
    }

    //PAINEL DE DIFICULDADE - A FUNCIONAR (PHASE 3 COMPLETE)
    public void createLevelDifficultyFX(Stage oldStage) {
        StackPane root = new StackPane();                                       //StackPane do JavaFX
        Scene scene = new Scene(root, 400, 200);                                //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button easyButton = new Button();                                       //Botão do JavaFX
        Button mediumButton = new Button();                                     //Botão do JavaFX
        Button hardButton = new Button();                                       //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                             //Texto do JavaFX

        stage.setTitle("Criar tabuleiro");                                      //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Criar tabuleiro");                                    //Definir texto na label
        labelOne.setTranslateX(0);                                              //Definir posição x do texto
        labelOne.setTranslateY(-80);                                            //Definir posição y do texto
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir tamanho do texto
        root.getChildren().add(labelOne);                                       //Adicionar o texto ao stack pane

        labelTwo.setText("Escolha uma dificuldade:");                           //Definir texto na label
        labelTwo.setTranslateX(0);                                              //Definir posição x do texto
        labelTwo.setTranslateY(-45);                                            //Definir posição y do texto
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        labelTwo.setFont(Font.font("Dialog", 14));                              //Definir tamanho do texto
        root.getChildren().add(labelTwo);                                       //Adicionar o texto ao stack pane

        easyButton.setText("Fácil");                                            //Definir texto do botão 
        easyButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        easyButton.setTranslateX(0);                                            //Definir posição x do botão
        easyButton.setTranslateY(-5);                                           //Definir posição y do botão
        easyButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(easyButton);                                     //Adicionar o botão ao stack pane
        easyButton.setOnAction(event -> {                                       //Quando se carrega no botão
            board.resetValue();                                                 //Definir os valores todos a 0
            SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;                             //Definir a dificuldade
            NUMBER_OF_ROWS = 4;                                                 //Definir o numero de linhas
            NUMBER_OF_COLUMNS = 4;                                              //Definir o numero de colunas
            players.get(playerID).getScore().setPoints(50);                     //Atribuição de 50 pontos iniciais ao jogador
            stage.close();                                                      //Fechar janela atual
            createLevelFX(stage);                                               //Abrir o menu de criar um nivel
        });

        mediumButton.setText("Médio");                                          //Definir texto do botão
        mediumButton.setPrefSize(120, 30);                                      //Definir tamanho do botão
        mediumButton.setTranslateX(0);                                          //Definir posição x do botão
        mediumButton.setTranslateY(35);                                         //Definir posição y do botão
        mediumButton.setFont(Font.font("Dialog", 12));                          //Definir fonte e tamanho do botão
        root.getChildren().add(mediumButton);                                   //Adicionar o botão ao stack pane
        mediumButton.setOnAction(event -> {                                     //Quando se carrega no botão
            board.resetValue();                                                 //Definir os valores todos a 0
            SET_DIFFICULTY = DIFFICULTY_BOARD_MEDIUM;                           //Definir a dificuldade
            NUMBER_OF_ROWS = 6;                                                 //Definir o numero de linhas
            NUMBER_OF_COLUMNS = 6;                                              //Definir o numero de colunas
            players.get(playerID).getScore().setPoints(100);                    // Atribuição de 100 pontos iniciais ao jogador
            stage.close();                                                      //Fechar janela atual
            createLevelFX(stage);                                               //Abrir o menu de criar um nivel
        });

        hardButton.setText("Difícil");                                          //Definir texto do botão
        hardButton.setPrefSize(120, 30);                                        //Definir tamanho do botão
        hardButton.setTranslateX(0);                                            //Definir posição x do botão
        hardButton.setTranslateY(75);                                           //Definir posição y do botão
        hardButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(hardButton);                                     //Adicionar o botão ao stack pane
        hardButton.setOnAction(event -> {                                       //Quando se carrega no botão
            board.resetValue();                                                 //Definir os valores todos a 0
            SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;                             //Definir a dificuldade
            NUMBER_OF_ROWS = 9;                                                 //Definir o numero de linhas
            NUMBER_OF_COLUMNS = 9;                                              //Definir o numero de colunas
            players.get(playerID).getScore().setPoints(150);                    //Atribuição de 150 pontos iniciais ao jogador
            stage.close();                                                      //Fechar janela atual
            createLevelFX(stage);                                               //Abrir o menu de criar um nivel
        });

        backButton.setText("Voltar");                                           //Definir texto do botão
        backButton.setPrefSize(80, 30);                                         //Definir tamanho do botão
        backButton.setTranslateX(-150);                                         //Definir posição x do botão
        backButton.setTranslateY(75);                                           //Definir posição y do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        root.getChildren().add(backButton);                                     //Adicionar o botão ao stack pane
        backButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                      //Fechar janela atual
            menuFX(oldStage);                                                   //Abrir o menu
        });
    }
    
    //PAINEL DE CRIAR O NIVEL - NÃO FUNCIONA
    public void createLevelFX(Stage oldStage) {
        GridPane gridPane = new GridPane();                                     //GridPane do JavaFX
        Scene scene = new Scene(gridPane, 400, 400);                            //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button createButton = new Button();                                     //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                             //Texto do JavaFX
        ArrayList<Integer> arrayDocks = new ArrayList<Integer>();               //Arraylist para guardar as posição dos portos

        stage.setTitle("Escolha a posição dos portos");                         //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("Escolha a posição dos portos");                                      //Definir texto na label
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir fonte e tamanho do botão
        gridPane.add(labelOne, 1, 0, 6, 1);                                     //Adicionar o botão ao grid pane com as posições

        labelTwo.setText("    Selecione a casa");                               //Definir texto na label
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        gridPane.add(labelTwo, 4, 1, 6, 1);                                     //Adicionar o botão ao stack pane com as posições

        for (i = 0; i < gameBoard.size(); i++) {
            buttons.add(new Button());
        }
        
        position=0;
        for (i = 2; i <= 6; i++) {                                              //Mecanismo for para repetir a criação (linhas)
            for (j = 2; j <= 6; j++) {                                          //Mecanismo for para repetir a criação (colunas)
                Button btn = new Button();                                      //Botão do JavaFX
                btn.setPrefSize(30, 30);                                        //Definir tamanho do botao
                gridPane.add(btn, j+1, i);                                      //Adicionar o botão ao stack pane com as posições
                btn.setFocusTraversable(false);                                 //Definir o botão sem o focus
                btn.setText(String.valueOf(position));
                btn.setOnAction(event -> {                                      //Quando se carrega no botão
                    arrayDocks.add(position);                                   //Adiciona posição ao array
                    btn.setDisable(true);
                });
                position++;                                                     //Incrementa o valor da posição
            }
        }

        gridPane.setHgap(10);                                                   //Definir o espaçamento horizontal na grelha
        gridPane.setVgap(10);                                                   //Definir o espaçamento vertical na grelha
        gridPane.setAlignment(Pos.CENTER);                                      //Centrar a grelha na página

        backButton.setText("Voltar");                                           //Definir texto do botão 
        backButton.setPrefSize(70, 30);                                         //Definir tamanho do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        gridPane.add(backButton, 3, 8, 4, 1);                                   //Adicionar o botão ao stack pane com as posições
        backButton.setOnAction(event -> {                                       //Quando se carrega no botão
            stage.close();                                                      //Fechar janela atual
            oldStage.show();                                                    //Abrir o anterior
        });

        createButton.setText("Criar");                                          //Definir texto do botão 
        createButton.setPrefSize(70, 30);                                       //Definir tamanho do botão
        createButton.setFont(Font.font("Dialog", 12));                          //Definir fonte e tamanho do botão
        gridPane.add(createButton, 6, 8, 4, 1);                                 //Adicionar o botão ao stack pane com as posições
        createButton.setDisable(false);
        createButton.setOnAction(event -> {                                     //Quando se carrega no botão
            board.createBoard(5, arrayDocks);
            stage.close();                                                      //Fechar janela atual
            displayBoardFX(stage);                                              //Apresentar o tabuleiro
        });
    }

    //PAINEL DE AJUDA - A FUNCIONAR (PHASE 3 COMPLETE)
    public void helpMenuFX(Stage oldStage) {
        GridPane gridPane = new GridPane();                                     //GridPane do JavaFX
        Scene scene = new Scene(gridPane, 400, 400);                            //Scene do JavaFX
        Stage stage = new Stage();                                              //Stage do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button validateButton = new Button();                                   //Botão do JavaFX
        Text labelOne = new Text();                                             //Texto do JavaFX
        Text labelTwo = new Text();                                             //Texto do JavaFX

        stage.setTitle("Ajuda");                                                //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        labelOne.setText("        Ajuda");                                      //Definir texto na label
        labelOne.setFill(Color.BLACK);                                          //Definir cor do texto
        labelOne.setFont(Font.font("Dialog", FontWeight.BOLD, 16));             //Definir fonte e tamanho do botão
        gridPane.add(labelOne, 3, 0, 6, 1);                                     //Adicionar o botão ao grid pane com as posições

        labelTwo.setText("    Selecione a casa");                               //Definir texto na label
        labelTwo.setFill(Color.BLACK);                                          //Definir cor do texto
        gridPane.add(labelTwo, 3, 1, 6, 1);                                     //Adicionar o botão ao stack pane com as posições

        for (i = 0; i < gameBoard.size(); i++) {
            buttons.add(new Button());
        }

        for (i = 2; i <= 6; i++) {                                              //Mecanismo for para repetir a criação
            for (j = 2; j <= 6; j++) {
                Button btn = new Button();                                      //Botão do JavaFX
                btn.setPrefSize(30, 30);                                        //Definir tamanho do botao
                gridPane.add(btn, j, i);                                        //Adicionar o botão ao stack pane com as posições
                btn.setFocusTraversable(false);                                 //Definir o botão sem o focus
                btn.setOnAction(event -> {                                      //Quando se carrega no botão
                    btn.setDisable(true);                                       //Definir o botão como desativado         
                });
            }
        }

        gridPane.setHgap(10);                                                   //Definir o espaçamento horizontal na grelha
        gridPane.setVgap(10);                                                   //Definir o espaçamento vertical na grelha
        gridPane.setAlignment(Pos.CENTER);                                      //Centrar a grelha na página

        backButton.setText("Voltar");                                           //Definir texto do botão 
        backButton.setPrefSize(70, 30);                                         //Definir tamanho do botão
        backButton.setFont(Font.font("Dialog", 12));                            //Definir fonte e tamanho do botão
        gridPane.add(backButton, 1, 8, 3, 1);                                   //Adicionar o botão ao stack pane com as posições
        backButton.setOnAction(event -> {                                           //Quando se carrega no botão
            stage.close();                                                  //Fechar janela atual
            oldStage.show();                                                //Abrir o anterior
        });

        validateButton.setText("Validar");                                      //Definir texto do botão 
        validateButton.setPrefSize(70, 30);                                     //Definir tamanho do botão
        validateButton.setFont(Font.font("Dialog", 12));                        //Definir fonte e tamanho do botão
        validateButton.setDisable(true);                                        //Definir o botão como desativado
        gridPane.add(validateButton, 5, 8, 3, 1);                               //Adicionar o botão ao stack pane com as posições
    }

    //PAINEL PARA APRESENTAR O TABULEIRO - A FUNCIONAR (PHASE 3 COMPLETE)
    public void displayBoardFX(Stage oldStage) {
        int SCENE_WIDTH = 0;                                                    //Definir a largura da cena
        int SCENE_HEIGHT = 0;                                                   //Definir a altura da cena

        if (SET_DIFFICULTY == DIFFICULTY_BOARD_EASY) {                          //Se a dificuldade é facil
            SCENE_WIDTH = 400;                                                  //Definir a largura da cena
            SCENE_HEIGHT = 400;                                                 //Definir a altura da cena
            counter = 30;                                                       //Definir o counter
            seconds = 30;                                                       //Definir os segundos
        }
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_MEDIUM) {                        //Se a dificuldade é media
            SCENE_WIDTH = 500;                                                  //Definir a largura da cena
            SCENE_HEIGHT = 500;                                                 //Definir a altura da cena
            counter = 90;                                                       //Definir o counter
            seconds = 90;                                                       //Definir os segundos
        }
        if (SET_DIFFICULTY == DIFFICULTY_BOARD_HARD) {
            SCENE_WIDTH = 600;
            SCENE_HEIGHT = 600;
            counter = 150;
            seconds = 150;
        }

        Stage stage = new Stage();                                              //Stage do JavaFX
        GridPane gridPane = new GridPane();                                     //GridPane do JavaFX
        Scene scene = new Scene(gridPane, 400, 400);                            //Scene do JavaFX
        Button backButton = new Button();                                       //Botão do JavaFX
        Button validateButton = new Button();                                   //Botão do JavaFX
        Text pointsLabel = new Text();                                          //Texto do JavaFX
        Text timerLabel = new Text();                                           //Texto do JavaFX
        Timer timer = new Timer();                                              //Timer do JavaFX   

        stage.setTitle("Tabuleiro de Jogo");                                    //Definir o titulo
        stage.setScene(scene);                                                  //Definir a cena
        stage.show();                                                           //Mostrar o stage

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerLabel.setText(String.valueOf(counter) + " segundos");      //Definir texto na label   
                counter--;                                                      //Retira 1 ao contador
            }
        }, 0, 1000);                                                            //Repete o loop a cada 1 segundo

        for (i = 0; i < gameBoard.size(); i++) {                                //Mecanismo para criar botões 
            buttons.add(new Button());                                          //Adicionar Botão do JavaFX ao arraylist
        }

        for (i = 0; i <= NUMBER_OF_ROWS; i++) {                                 //Mecanismo para criar botões e jogar
            for (j = 0; j <= NUMBER_OF_COLUMNS; j++) {
                Button btn = new Button();                                      //Botão do JavaFX
                gridPane.add(btn, j, i);                                        //Adicionar o botão ao stack pane com as posições
                int x = GridPane.getRowIndex(btn);                              //Definir na variavel a posição x do botão
                int y = GridPane.getColumnIndex(btn);                           //Definir na variavel a posição y do botão
                btn.setText(gameBoard.get(board.getIndex(x, y)).toString());    //Definir texto na label
                btn.setFocusTraversable(false);                                 //Definir o botão sem o focus
                btn.setPrefSize(30, 30);                                        //Definir tamanho do botao       
                btn.setOnAction(event -> {                                //Quando se carrega no botão                  
                    attempts++;                                                 //Incrementa 1 ao numero de tentativas
                    btn.setDisable(true);                                       //Definir o botão como desativado
                    position = board.getIndex(x, y);                            //Definir a variavel com a posição do botão clicado
                    if (boat.size() >= SET_DIFFICULTY - 1) {                          //Caso as casas não estejam selecionadas
                        validateButton.setDisable(false);                       //Definir o botão como desativado
                    }
                    if (!(gameBoard.get(position) instanceof Dock) && !(gameBoard.get(position) instanceof Boat)) { //Se a posição escolhida não for porto nem barco
                        if (rules.checkSpotForBoat(x, y)) {                 //Verificar a posição escolhida
                            board.placeBoat(position);                      //Definir a posição como barco
                            btn.setText("B");                               //Definir texto no botão
                        } else {
                            players.get(playerID).getScore().missedBoat();  //Retirar pontos
                            board.placeWater(position);                     //Definir a posição como agua
                            btn.setText("A");                               //Definir texto no botão
                            createAlert("Aviso", "Casa errada", "Não pode colocar um barco nessa posição.\nFoi punido em x pontos", AlertType.WARNING); //Criar um alerta personalizado
                        }
                    } else {
                        createAlert("Aviso", "Casa ocupada", "Essa posição já se encontra ocupada.\nFoi punido em x pontos", AlertType.WARNING); //Criar um alerta personalizado
                    }
                    pointsLabel.setText("Pontuação: " + players.get(playerID).getScore().getPoints() + " pontos");              //Definir texto no botão
                });
            }
        }

        gridPane.setHgap(10);                                                   //Definir o espaçamento horizontal na grelha
        gridPane.setVgap(10);                                                   //Definir o espaçamento vertical na grelha
        gridPane.setAlignment(Pos.CENTER);                                      //Centrar a grelha na página

        ButtonType yes = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);                                                                           //Botão do Alerta
        ButtonType cancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);                                                              //Botão do Alerta
        Alert alert = new Alert(AlertType.WARNING, "Tem a certeza que quer sair do tabuleiro?\nVai perder todo o seu progresso.", yes, cancel);         //Criar alerta
        alert.setTitle("Atenção");                                                                                                                      //Definir titulo do alerta

        pointsLabel.setText("Pontuação: " + players.get(playerID).getScore().getPoints() + " pontos");      //Definir texto na label
        pointsLabel.setFill(Color.GREEN);                                                                   //Definir cor do texto
        pointsLabel.setFont(Font.font("Dialog", FontWeight.BOLD, 16));                                      //Definir fonte e tamanho do botão
        gridPane.add(pointsLabel, 0, NUMBER_OF_ROWS + 3, NUMBER_OF_COLUMNS + 1, 1);                         //Adicionar o botão ao grid pane com as posições

        timerLabel.setFill(Color.RED);                                                                      //Definir cor do texto
        timerLabel.setFont(Font.font("Dialog", FontWeight.BOLD, 16));                                       //Definir fonte e tamanho do botão
        gridPane.add(timerLabel, 0, NUMBER_OF_ROWS + 4, NUMBER_OF_COLUMNS + 1, 1);                          //Adicionar o botão ao grid pane com as posições

        backButton.setText("Voltar");                                                                       //Definir texto do botão 
        backButton.setPrefSize(70, 30);                                                                     //Definir tamanho do botão
        backButton.setFont(Font.font("Dialog", 12));                                                        //Definir fonte e tamanho do botão
        gridPane.add(backButton, 0, NUMBER_OF_ROWS + 5, 2, 1);                                              //Adicionar o botão ao stack pane com as posições
        backButton.setOnAction(event -> {                                                                       //Quando se carrega no botão
            Optional<ButtonType> result = alert.showAndWait();                                              //Definir a opção
            if (result.orElse(cancel) == yes) {                                                             //Caso a opção seja "Sim"
                players.get(playerID).getScore().setPoints(0);                                              //Definir os pontos a 0
                timer.cancel();                                                                             //Cancelar o timer
                stage.close();                                                                              //Fechar janela atual
                oldStage.show();                                                                            //Abrir o anterior
            }
        });

        validateButton.setText("Validar");                                                                  //Definir texto do botão 
        validateButton.setPrefSize(70, 30);                                                                 //Definir tamanho do botão
        validateButton.setFont(Font.font("Dialog", 12));                                                    //Definir fonte e tamanho do botão
        validateButton.setDisable(true);                                                                    //Definir o botão como desativado
        gridPane.add(validateButton, NUMBER_OF_COLUMNS - 1, NUMBER_OF_ROWS + 5, 2, 1);                      //Adicionar o botão ao stack pane com as posições     
        validateButton.setOnAction(event -> {                                                                   //Quando se carrega no botão
            timer.cancel();                                                                                 //Cancelar o timer
            board.endValidateBoard((seconds - counter));                                                      //Terminar a validação do tabuleiro
            createAlert("Jogo Terminado", "Jogo Terminado", "Demorou " + (seconds - counter) + " segundos a terminar o jogo.\n"
                    + "Teve uma pontuação de " + players.get(playerID).getScore().getPoints() + " pontos.", Alert.AlertType.WARNING);   //Apresentar o alerta
            stage.close();
            menuFX(oldStage);
        });
    }

    //PAINEL DE ALERT (DIALOG BOX) - A FUNCIONAR (PHASE 3 COMPLETE)
    public void createAlert(String title, String header, String message, AlertType type) {
        Alert alert = new Alert(type);                                          //Alerta do JavaFX
        alert.setTitle(title);                                                  //Definir o titulo recebido por parametro
        alert.setHeaderText(header);                                            //Definir o header recebido por parametro
        alert.setContentText(message);                                          //Definir a mensagem recebido por parametro
        alert.showAndWait();                                                    //Apresentar o alerta
    }

    //JAVA CONSOLE
    
    
    //IMPRIME O MENU PRINCIPAL - A FUNCIONAR
    public void startConsole() {
        System.out.println("Seja bem-vindo ao Docks & Boats\n");
        System.out.println("Escolha uma opção");
        System.out.println();
        String nickname = "";

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
                    System.out.print("Nickname -> ");
                    nickname = input.next().trim(); // Leitura do nickname digitado pelo utilizador
                    player.createPlayer(nickname, null);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    startConsole(); // no caso de não ser escolhida nenhuma das opções facultadas ao utilizador, volta ao menu inicial
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
                    System.out.print("Nickname -> ");
                    nickname = input.next().trim(); // Leitura do nickname digitado pelo utilizador
                    player.createPlayer(nickname, null);
                    break;
                case 2:
                    System.out.print("Nickname -> ");
                    nickname = input.next().trim(); // Leitura do nickname digitado pelo utilizador
                    player.choosePlayer(nickname, null);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    startConsole(); // no caso de não ser escolhida nenhuma das opções facultadas ao utilizador, volta ao menu inicial
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
                    chooseDifficultyConsole(); // Redireciona o utilizar para o menu de escolha da dificuldade do jogo a ser jogado
                } else {
                    playConsole();
                }
                break;
            case 2:
                System.out.println("Funcionalidade indisponivel. Obrigado.");
                menuConsole();
                break;
            case 3:
                score.printPlayerScore(players.get(playerID)); // Exibe as pontuações pessoais do utilizador
                break;
            case 4:
                score.printScore(); // Exibe a pontuação geral dos jogadores 
                break;
            case 5:
                ArrayList<Integer> arrayDocks = new ArrayList<Integer>();
                System.out.println("1 - (5x5 - 25 casas)");
                System.out.println("2 - (7x7 - 49 casas)");
                System.out.println("3 - (10x10 - 100 casas)");
                System.out.println("4 - Voltar");
                System.out.print("Escolha a opção para o numero de casas -> ");

                while (!input.hasNextInt()) {
                    System.out.print("-> ");
                    input.next();
                }
                option = input.nextInt();

                switch (option) { // Verifica qual a opção escolhida pelo utilizador
                    case 1:
                        board.resetValue();                                     //Definir os valores todos a 0
                        SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;                 //Definir a dificuldade
                        NUMBER_OF_ROWS = 4;                                     //Definir o numero de linhas
                        NUMBER_OF_COLUMNS = 4;                                  //Definir o numero de colunas
                        players.get(playerID).getScore().setPoints(150);        //Atribuição de 150 pontos iniciais ao jogador
                        for (i = 0; i < SET_DIFFICULTY; i++) {
                            System.out.print("Insira a posição do porto -> ");
                            while (!input.hasNextInt()) {
                                System.out.print("-> ");
                                input.next();
                            }
                            arrayDocks.add(input.nextInt());
                        }
                        board.createBoard(SET_DIFFICULTY, arrayDocks);
                        game.playConsole();
                        break;
                    case 2:
                        board.resetValue();                                     //Definir os valores todos a 0
                        SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;                 //Definir a dificuldade
                        NUMBER_OF_ROWS = 6;                                     //Definir o numero de linhas
                        NUMBER_OF_COLUMNS = 6;                                  //Definir o numero de colunas
                        players.get(playerID).getScore().setPoints(100);        //Atribuição de 150 pontos iniciais ao jogador
                        for (i = 0; i < SET_DIFFICULTY; i++) {
                            System.out.print("Insira a posição do porto -> ");
                            while (!input.hasNextInt()) {
                                System.out.print("-> ");
                                input.next();
                            }
                            arrayDocks.add(input.nextInt());
                        }
                        board.createBoard(SET_DIFFICULTY, arrayDocks);
                        game.playConsole();
                        break;
                    case 3:
                        board.resetValue();                                     //Definir os valores todos a 0
                        SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;                 //Definir a dificuldade
                        NUMBER_OF_ROWS = 9;                                     //Definir o numero de linhas
                        NUMBER_OF_COLUMNS = 9;                                  //Definir o numero de colunas
                        players.get(playerID).getScore().setPoints(150);        //Atribuição de 150 pontos iniciais ao jogador
                        for (i = 0; i < SET_DIFFICULTY; i++) {
                            System.out.print("Insira a posição do porto -> ");
                            while (!input.hasNextInt()) {
                                System.out.print("-> ");
                                input.next();
                            }
                            arrayDocks.add(input.nextInt());
                        }
                        board.createBoard(SET_DIFFICULTY, arrayDocks);
                        game.playConsole();
                        break;
                    case 4:
                        game.menuConsole();
                        break;
                    default:
                        game.menuConsole();
                        break;
                }
                break;
            case 6:
                printHelpConsole(); // O utilizador é redirecionado para um menu de ajuda à compreensão das diferentes siglas inerentes ao jogo
                break;
            case 7:
                startConsole(); // Retrocede-se ao menu inicial
                break;
            case 0:
                System.exit(0); // O programa é finalizado
                break;
            default:
                menuConsole();
                break;
        }
    }

    //MENU PARA ESCOLHA DE DIFICULDADE - A FUNCIONAR
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
                if (array[index] == 5) { //caso seja o 5 o escolhido, gera um tabuleiro facil
                    SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;
                    NUMBER_OF_ROWS = 4;
                    NUMBER_OF_COLUMNS = 4;
                    players.get(playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    playConsole();
                }
                if (array[index] == 7) { //caso seja o 7 o escolhido, gera um tabuleiro medio
                    SET_DIFFICULTY = DIFFICULTY_BOARD_MEDIUM;
                    NUMBER_OF_ROWS = 6;
                    NUMBER_OF_COLUMNS = 6;
                    players.get(playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    playConsole();
                }
                if (array[index] == 10) {    //caso seja o 10 o escolhido, gera um tabuleiro dificil
                    SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;
                    NUMBER_OF_ROWS = 9;
                    NUMBER_OF_COLUMNS = 9;
                    players.get(playerID).getScore().setPoints(150); // Atribuição de 100 pontos iniciais ao jogador
                    board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                    playConsole();
                }
                break;
            case 2: // Criação de um tabuleiro de game de dificuldade "Fácil"
                SET_DIFFICULTY = DIFFICULTY_BOARD_EASY;
                NUMBER_OF_ROWS = 4;
                NUMBER_OF_COLUMNS = 4;
                players.get(playerID).getScore().setPoints(50); // Atribuição de 50 pontos iniciais ao jogador
                board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                playConsole();
                break;
            case 3: // Criação de um tabuleiro de game de dificuldade "Médio"
                SET_DIFFICULTY = DIFFICULTY_BOARD_MEDIUM;
                NUMBER_OF_ROWS = 6;
                NUMBER_OF_COLUMNS = 6;
                players.get(playerID).getScore().setPoints(100); // Atribuição de 100 pontos iniciais ao jogador
                board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                playConsole();
                break;
            case 4: // Criação de um tabuleiro de game de dificuldade "Difícil"
                SET_DIFFICULTY = DIFFICULTY_BOARD_HARD;
                NUMBER_OF_ROWS = 9;
                NUMBER_OF_COLUMNS = 9;
                players.get(playerID).getScore().setPoints(150); // Atribuição de 150 pontos iniciais ao jogador
                board.generateBoard(); // Criação do tabuleiro com as caraterísticas acima descritas
                playConsole();
                break;
            case 0:
                menuConsole(); // Retrocede-se ao menu
                break;
            default:
                chooseDifficultyConsole(); // Caso não seja selecionada nenhuma das opções disponíveis, é exibido novamente o menu de  escolha de dificuldade
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
        menuConsole(); // Retrocede-se ao menu
    }

    //IMPRIME O TABULEIRO FINAL - A FUNCIONAR
    public void printEndConsole() {
        System.out.println("O jogo terminou, e você terminou com " + players.get(playerID).getScore().getPoints() + " pontos");
        System.out.println("Prima enter para sair do jogo.");
        try {
            System.in.read(); // É lida a tecla premida pelo utilizador
        } catch (Exception e) {
        }
        System.exit(0);// Retrocede-se ao menu
    }

    //JOGADAS DO UTILIZADOR - A FUNCIONAR
    public void playConsole() {
        attempts++;
        //Imprime o tabuleiro de jogo
        System.out.println("\nTabuleiro de Jogo:");
        for (i = 0; i < gameBoard.size(); i++) {
            System.out.print("|");
            System.out.print(gameBoard.get(i).toString());
            if (gameBoard.get(i).getPosition().getColumn() == (NUMBER_OF_COLUMNS)) { // É criado um tabuleiro correspondente à dificuldade selecionada
                System.out.print("|");
                System.out.println();
            }
        }

        System.out.println("-----------------------");
        boolean done = false;
        String move = "";
        int x = 0;
        int y = 0;
        System.out.println("Escreva \"sair\" para sair e \"validar\" para validar e terminar o jogo:");

        System.out.println("Campo (A ou B):");
        do {
            System.out.print("-> ");
            move = input.next();
            if (move.equals("A") || move.equals("B")) {
                done = true;
            } else {
                if (move.equals("sair") || move.equals("validar")) {
                    if (move.equals("sair")) {
                        menuConsole();
                    }
                    if (move.equals("validar")) {
                        if (attempts == 0) {
                            System.out.println("Não pode validar na primeira jogada");
                        } else {
                            board.endValidateBoard(-9999);
                            System.out.println("Teve uma pontuação de " + players.get(playerID).getScore().getPoints() + " pontos.");
                            menuConsole();
                        }
                    }
                }
            }
        } while (done == false);

        done = false;

        System.out.println("Linha (x):");
        do {
            System.out.print("-> ");
            String row = input.next();
            if (row.equals("sair") || row.equals("validar")) {
                if (row.equals("sair")) {
                    menuConsole();
                }
                if (row.equals("validar")) {
                    if (attempts == 1) {
                        System.out.println("Não pode validar na primeira jogada");
                    } else {
                        board.endValidateBoard(-9999);
                        System.out.println("Teve uma pontuação de " + players.get(playerID).getScore().getPoints() + " pontos.");
                        menuConsole();
                    }
                }
            } else {
                if (Integer.parseInt(row) >= 0 && Integer.parseInt(row) <= NUMBER_OF_ROWS) {
                    x = Integer.parseInt(row);
                    done = true;
                }
            }
        } while (done == false);

        done = false;

        System.out.println("Coluna (y):");
        do {
            System.out.print("-> ");
            String column = input.next();
            if (column.equals("sair") || column.equals("validar")) {
                if (column.equals("sair")) {
                    menuConsole();
                }
                if (column.equals("validar")) {
                    if (attempts == 0) {
                        System.out.println("Não pode validar na primeira jogada");
                    } else {
                        board.endValidateBoard(-9999);
                        System.out.println("Teve uma pontuação de " + players.get(playerID).getScore().getPoints() + " pontos.");
                        menuConsole();
                    }
                }
            } else {
                if (Integer.parseInt(column) >= 0 && Integer.parseInt(column) <= NUMBER_OF_ROWS) {
                    y = Integer.parseInt(column);
                    done = true;
                }
            }
        } while (done == false);

        int position = board.getIndex(x, y);
        if (!(gameBoard.get(position) instanceof Dock) && !(gameBoard.get(position) instanceof Boat)) {
            if (move.equals("B")) {
                if (rules.checkSpotForBoat(x, y)) {
                    board.placeBoat(position);
                } else {
                    players.get(playerID).getScore().missedBoat();
                    board.placeWater(position);
                    System.out.println("Não pode colocar agua num lugar que não se encontra desconhecido.");
                }
            }
            if (move.equals("A")) {
                board.placeWater(position);
                System.out.println("Não pode colocar agua num lugar que não se encontra desconhecido.");
            }
        } else {
            System.out.println("Essa posição já se encontra ocupada.");
        }
        playConsole();
    }
}
