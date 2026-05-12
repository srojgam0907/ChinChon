package app;

import tema2_1_EscrituraEnPantalla.colores.Colors; 
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
		System.out.printf("%s******************************************%s\n", Colors.YELLOW_BRIGHT, Colors.RESET);
		System.out.printf("%s      BIENVENIDO AL JUEGO DEL CHINCHÓN    %s\n", Colors.YELLOW_BRIGHT, Colors.RESET);
		System.out.printf("%s******************************************%s\n", Colors.YELLOW_BRIGHT, Colors.RESET);

		GameEngine game= new GameEngine();
        
		game.configurationGame();
        game.setUpGame();
        game.startGame();
	}
}


















