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
			System.out.println("\nMazo vacío. Barajando la pila de descartes");
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
		
	}
	
	/**
	 * Informa de quién es el ganador de la ronda.
	 */
	public void showWinner() {
		
	}
	
	/**
	 * Bucle principal del juego.
	 * Controla el flujo de la partida.
	 */
	public void startGame() {
		Player currentPlayer;
		boolean close;
		
		System.out.println("¡COMENCEMOS!");
		
		while(!gameOver) {
			currentPlayer= players.get(currentPlayerIndex);
			
			currentPlayer.playTurn(deck, discardPile);
			
			checkDeckStatus();
			
			if(currentPlayer instanceof HumanPlayer && !gameOver) {
				System.out.printf("\n%s, ¿Quieres cerrar la ronda? s/n\n", currentPlayer.getName());
				close= ci.readBooleanUsingChar('s', 'n');
				
				if(close) {
					System.out.printf("\n==============================\n%s HA CERRADO LA RONDA\n==============================\n");
					gameOver= true;
				}
			}
			
			if(!gameOver) {
				nextTurn();
			}
		}
		
		finish();
	}
}