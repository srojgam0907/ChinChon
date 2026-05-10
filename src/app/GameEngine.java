package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
     * Constructor del motor de juego
     */
	public GameEngine() {
		players= new ArrayList<>();
		currentPlayerIndex= 0;
		gameOver= false;
		ci= ConsoleInput.getInstance(new Scanner(System.in));
	}
	
	/**
	 * Se configura el juego. El numero de jugadores, el numero de barajas,
	 * los nombres de los jugadores y cuales son IAs.
	 * @param players 
	 * @return
	 */
	public void configurationGame() {
		int numDecks= 0;
		int numPlayers= 0;
		String name;
		boolean isAI;
		
		System.out.println("\n------------------------------------------");
        System.out.println("   Empezando configuración...");
        System.out.println("------------------------------------------\n");
		
        System.out.println("Podeis jugar hasta 5 jugadores y minimo 2, ¿Cuántos jugadores vais a ser?");
        numPlayers= ci.readIntInRange(2, 5);
        
        System.out.println("\nPodeis jugar con 1 o 2 barajas, ¿Cuántas barajas quereis para jugar?");
		numDecks= ci.readIntInRange(1, 2);
		deck= new Deck(numDecks);
		
		for(int i=1; i <= numPlayers; i++) {
			System.out.printf("Configurando jugador %d:\n", i);
			System.out.println("Nombre: ");
			name= ci.readStringNotEmpty();
			
			System.out.print("¿Es una IA? [S/N]: ");
			isAI= ci.readBooleanUsingChar('s', 'n');
			
			if(isAI) {
				players.add(new AIPlayer("IA " + name));
				
			} else {
				players.add(new HumanPlayer(name));
			}
		}
		
		System.out.println("\n------------------------------------------");
        System.out.println("   Configuración finalizada. Iniciando...");
        System.out.println("------------------------------------------\n");
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
		int turnCounter= 0;
		int round= 1;
		
		System.out.println("¡COMENCEMOS LA PARTIDA!");
		
		while(!gameOver) {
			currentPlayer= players.get(currentPlayerIndex);
			
			if(currentPlayerIndex == 0) {
				System.out.printf("\nRONDA %d", round); 
			}
			
			currentPlayer.playTurn(deck, discardPile);
			
			if (turnCounter >= players.size() && currentPlayer.hasClosed()) { //no se puede cerrar en la primera ronda
				System.out.printf("\n %s ha cerrado la ronda\n", currentPlayer.getName());
				gameOver= true;
			}
			
			if(!gameOver) {
				checkDeckStatus();
				nextTurn();
				turnCounter++;
				System.out.print("\nEMPEZANDO LA SIGUIENTE RONDA\n");
			}
			
			if(currentPlayerIndex == 0) {
				round++;
			}
		}
		
		finish();
	}
}