package projeto;

/**
 *
 * @author Gonçalo Fernandes e Henrique Leote
 */
public class AppStart {
    public static void main(String[] args) {
        Game game = new Game(5);    //cria e inicializa um objeto da classe game com o numero de utilizadores definido a 5
        game.printIntro();          //apresenta o menu de inicio do jogo   
    }
}
