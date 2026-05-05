package app;

import java.util.List;
import domain.*;

/**
 * Motor principal que controla el flujo de la partida.
 */
public class GameEngine {

	private Deck deck;
	private DiscardPile discardPile;
	private List<Player> players;
	private int currentPlayerIndex;
	private boolean gameOver;
	private ConsoleInput ci;
	
	/**
     * @param numberOfDecks Cantidad de barajas (1 o 2).
     * @param players Lista de jugadores (Humanos e IAs) ya configurados.
     */
	public GameEngine(int numberOfDecks, List<Player> players) {
		deck= new Deck(numberOfDecks);
		discardPile= new DiscardPile();
		this.players= players;
		currentPlayerIndex= 0;
		gameOver= false;
		ci= ConsoleInput.getInstance(null); 
	}
	
	/**
     * Reparte 7 cartas a cada jugador y pone la primera en el descarte.
     */
	public void setUpGame() {
		System.out.println("Repartiendo cartas...");
		for(Player p : players) {
			for(int i=0; i<7; i++) {
				deck.drawCard().ifPresent(card -> p.getHand().addCard(card));
			}
		}
		deck.drawCard().ifPresent(discardPile::pushCard);
	}
	
	/**
	 * Comprueba el estado del mazo. 
	 * Si esta vacio baraja la pila de descartes y la usa como nuevo mazo
	 */
	private void checkDeckStatus() {
		if(deck.isEmpty()) {
			System.out.println("\nMazo vacío. Barajando la pila de descartes...");
			List<Card> recycledCards= discardPile.collectAllButLast();
			deck.refillFromDiscardPile(recycledCards);
		}
	}
	
	/**
	 * Gestiona el cambio de turno entre jugadores.
	 * Si llega al final de la lista, vuelve al primer jugador.
	 */
	public void nextTurn() {
		currentPlayerIndex++;
		
		if(currentPlayerIndex == players.size()) {
			currentPlayerIndex= 0;
		}
	}
	
	/**
	 * Informa al jugador de que se ha terminado la ronda y enseña
	 * la puntuación de cada uno.
	 */
	public void finish() {
		int points;
		
		System.out.print("\n//////////// FIN DE LA RONDA ////////////\n");
		
		for(Player p: players) {
			points= CombinationChecker.calculateUncombinedPoints(p.getHand().getCards());
			System.out.printf("\nJugador %s: %d puntos en cartas sueltas.\n", p.getName(), points);
		}
		
		showWinner();
	}
	
	/**
	 * Informa de quién es el ganador de la ronda, según quién sea el
	 * que tiene la menor cantidad de puntos.
	 */
	public void showWinner() {
		Player winner= players.get(0);
		Player p;
		int minPoints= CombinationChecker.calculateUncombinedPoints(winner.getHand().getCards());
		int actualPoints;
		
		for(int i=0; i<players.size(); i++) {
			p= players.get(i);
			actualPoints= CombinationChecker.calculateUncombinedPoints(p.getHand().getCards());
			
			if(actualPoints < minPoints) {
				minPoints= actualPoints;
				winner= p;
			}
		}
		
		System.out.printf("\n------------------------------\n¡EL GANADOR ES %s CON %d PUNTOS!\n------------------------------\n", winner.getName().toUpperCase(), minPoints);
	}
	
	/**
	 * Bucle principal del juego.
	 * Controla el flujo de la partida.
	 */
	public void startGame() {
		Player currentPlayer;
		boolean close; 
		int turnCounter= 0;
		
		System.out.println("¡COMENCEMOS LA PARTIDA!");
		
		while(!gameOver) {
			currentPlayer= players.get(currentPlayerIndex);
			
			currentPlayer.playTurn(deck, discardPile);
			
			if (turnCounter >= players.size()) { //no se puede cerrar en la primera ronda
				if (currentPlayer.getHand().canClose()) {
					if (currentPlayer instanceof HumanPlayer && !gameOver) {
						System.out.printf("\n%s, ¿Quieres cerrar la ronda? s/n\n", currentPlayer.getName());
						close = ci.readBooleanUsingChar('s', 'n');

						if (close) {
							System.out.printf("\n==============================\n%s HA CERRADO LA RONDA\n==============================\n",currentPlayer.getName());
							gameOver = true;
						}

					} else { //Si la IA puede cerrará automáticamente
						gameOver = true;
						System.out.printf("\n==============================\nIA %s HA CERRADO LA RONDA\n==============================\n",currentPlayer.getName());
					}
				}
			}
			
			if(!gameOver) {
				checkDeckStatus();
				nextTurn();
				turnCounter++;
				System.out.print("\nEMPEZANDO LA SIGUIENTE RONDA\n");
			}
		}
		
		finish();
	}
}