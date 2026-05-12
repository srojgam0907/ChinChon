package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.*;
import tema2_1_EscrituraEnPantalla.colores.Colors;

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
		discardPile= new DiscardPile();
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
		
		System.out.printf("\n%s------------------------------------------%s\n", Colors.GREEN, Colors.RESET);
        System.out.printf("%s   Empezando configuración...%s\n", Colors.GREEN, Colors.RESET);
        System.out.printf("%s------------------------------------------%s\n", Colors.GREEN, Colors.RESET);
		
        System.out.printf("\n%sPodeis jugar hasta 5 jugadores y minimo 2, ¿Cuántos jugadores vais a ser?%s\n", Colors.BLUE_BRIGHT, Colors.RESET);
        numPlayers= ci.readIntInRange(2, 5); 
        
        System.out.printf("\n%sPodeis jugar con 1 o 2 barajas, ¿Cuántas barajas quereis para jugar?%s\n", Colors.BLUE_BRIGHT, Colors.RESET); 
		numDecks= ci.readIntInRange(1, 2);
		deck= new Deck(numDecks);
		
		for(int i=1; i <= numPlayers; i++) {
			System.out.printf("\n%sConfigurando jugador %d:\n", Colors.BLUE_BRIGHT, i);
			System.out.printf("%sNombre: %s", Colors.BLUE_BRIGHT, Colors.RESET);
			name= ci.readStringNotEmpty();
			
			System.out.printf("\n%s¿Es una IA? [S/N]: %s", Colors.BLUE_BRIGHT, Colors.RESET);
			isAI= ci.readBooleanUsingChar('s', 'n');
			
			if(isAI) {
				players.add(new AIPlayer("IA " + name));
				
			} else {
				players.add(new HumanPlayer(name));
			}
		}
		
		System.out.printf("\n%s------------------------------------------%s\n", Colors.GREEN, Colors.RESET);
        System.out.printf("%s   Configuración finalizada. Iniciando...%s\n", Colors.GREEN, Colors.RESET);
        System.out.printf("%s------------------------------------------%s\n", Colors.GREEN, Colors.RESET);
	}
	
	/**
     * Reparte 7 cartas a cada jugador y pone la primera en el descarte.
     */
	public void setUpGame() {
		System.out.printf("\n%sRepartiendo cartas...%s\n", Colors.YELLOW, Colors.RESET);
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
			System.out.printf("\n%sMazo vacío. Barajando la pila de descartes...%s", Colors.PURPLE_BRIGHT, Colors.RESET); 
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
		
		System.out.printf("\n%s//////////// FIN DE LA PARTIDA ////////////%s\n", Colors.YELLOW_BRIGHT, Colors.RESET);
		
		for(Player p: players) {
			points= CombinationChecker.calculateUncombinedPoints(p.getHand().getCards());
			System.out.printf("\n%sJugador %s:%s %d puntos.\n", Colors.BOLD, p.getName(), Colors.RESET, points);
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
		
		if(minPoints <= 1) {
			System.out.printf("\n%s------------------------------\n¡EL GANADOR ES %s CON %d PUNTO!\n------------------------------%s\n", Colors.YELLOW_BRIGHT, winner.getName().toUpperCase(), minPoints, Colors.RESET);

		} else {
			System.out.printf("\n%s------------------------------\n¡EL GANADOR ES %s CON %d PUNTOS!\n------------------------------%s\n", Colors.YELLOW_BRIGHT, winner.getName().toUpperCase(), minPoints, Colors.RESET);
		}
	}
	
	/**
	 * Bucle principal del juego.
	 * Controla el flujo de la partida.
	 */
	public void startGame() {
		Player currentPlayer; 
		int turnCounter= 0;
		int turnCounterPerRound= 0;
		int round= 1;
		
		System.out.printf("\n%s¡COMENCEMOS LA PARTIDA!%s\n", Colors.YELLOW, Colors.RESET);
		
		while(!gameOver) {
			currentPlayer= players.get(currentPlayerIndex);
			
			if(currentPlayerIndex == 0) {
				System.out.printf("\n%sRONDA %d%s\n", Colors.GREEN, round, Colors.RESET); 
			}
			
			currentPlayer.playTurn(deck, discardPile);
			
			if (turnCounter >= players.size() && currentPlayer.hasClosed()) { //no se puede cerrar en la primera ronda
				System.out.printf("\n%s%s ha cerrado la ronda%s\n", Colors.BLUE_BRIGHT, currentPlayer.getName(), Colors.RESET);
				gameOver= true;
			}
			
			if(turnCounterPerRound < players.size()-1 && !gameOver) {
				System.out.printf("\n%sEMPEZANDO EL SIGUIENTE TURNO%s\n", Colors.YELLOW, Colors.RESET);
				turnCounterPerRound++;
				
			} else if(turnCounterPerRound == players.size()-1 && !gameOver){
				System.out.printf("\n%sEMPEZANDO LA SIGUIENTE RONDA%s\n",  Colors.YELLOW, Colors.RESET);
				turnCounterPerRound= 0;
			}
			
			if(!gameOver) {
				checkDeckStatus();
				nextTurn();
				turnCounter++;
			}
			
			if(currentPlayerIndex == 0) {
				round++;
			}
		}
		
		finish();
	}
}