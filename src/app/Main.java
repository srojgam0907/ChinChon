package app;

/**
 * Punto de entrada principal de la aplicación del juego del Chinchón.
 * Esta clase se encarga de arrancar el entorno del programa y ceder el control
 * al motor del juego.
 */
public class Main {

	public static void main(String[] args) {
		new Main().game();

	}
	
	/**
     * Gestiona el ciclo de vida del GameEngine: 
     * configuración, preparación de cartas y comienzo de la partida.
     */
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


















