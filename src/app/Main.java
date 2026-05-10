package app;

public class Main {

	public static void main(String[] args) {
		new Main().game();

	}
	
	public void game() {
		System.out.println("******************************************");
		System.out.println("      BIENVENIDO AL JUEGO DEL CHINCHÓN    ");
		System.out.println("******************************************");

		GameEngine game= new GameEngine();
        
		game.configurationGame();
        game.setUpGame();
        game.startGame();
	}
}


















