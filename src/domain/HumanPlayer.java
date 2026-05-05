package domain;

import java.util.Scanner;

import app.ConsoleInput;

/**
 * Representa a un jugador controlado por un humano.
 */
public class HumanPlayer extends Player{

	private Scanner kb= new Scanner(System.in);
	private ConsoleInput ci= ConsoleInput.getInstance(kb);
	
	/**
	 * Constructor del jugador humano
	 * @param name variable heredada de Player
	 */
	public HumanPlayer(String name) {
		super(name);
	}

	@Override
	public void playTurn(Deck deck, DiscardPile discardPile) {
		System.out.printf("\n====== TURNO DE: %s ======\n", name); 
		
		//ESTADO ACTUAL
        System.out.println(discardPile.toString());
        hand.sortHand();
        System.out.println(hand.toString());
		
        //ROBO
        executeDrawn(deck, discardPile);
        hand.sortHand();
        System.out.printf("\nHas robado. Tu mano actual es: %s\n", hand.toString());
        
		// DESCARTE o CIERRE
		if (hand.canClose()) {
			System.out.print("\n¡Puedes cerrar¡ ¿Quieres cerrar? [S/N]: ");

			if (ci.readBooleanUsingChar('s', 'n')) {
				executeCloseDiscard(discardPile);

			} else {
				executeNormalDiscard(discardPile);

			} 

		} else {
			executeNormalDiscard(discardPile);
		}
	}
	
	/**
	 * Ejecuta el robo del jugador del mazo o del descarte 
	 */
	private void executeDrawn(Deck deck, DiscardPile discardPile) {
		boolean drawn= false;
		boolean choice;
		
        while(!drawn) {
        	System.out.println("¿Robas del [D]escarte o del [M]azo?:");
        	choice= ci.readBooleanUsingChar('d', 'm'); //d = true, m = false
        	
        	if(choice) {
        		if(!discardPile.isEmpty()) {
        			discardPile.takeCard().ifPresent(hand::addCard);
        			drawn= true;
        		
        		} else {
        			System.out.println("La pila de descartes está vacía. Por favor elije el mazo");
        		}
        		
        	} else {
        		deck.drawCard().ifPresent(hand::addCard);
        		drawn= true;
        	}
        }
	}
	
	/**
	 * Ejecuta un descarte normal cuando todavia el jugador no puede cerrar
	 */
	private void executeNormalDiscard(DiscardPile discardPile) {
		int cardPosition;
		
		System.out.print("\nElige la posición de la carta que quieres descartar: ");
    	cardPosition= ci.readIntInRange(1, hand.size());
    	
    	Card c= hand.discardCard(cardPosition -1);
    	discardPile.pushCard(c);
    	
    	System.out.println("Has descartado: " + c.toString());
	}
	
	/**
	 * Cuando el jugador puede cerrar se activa esta modo de descarte
	 * para descartar la ultima carta antes de cerrar la ronda
	 */
	private void executeCloseDiscard(DiscardPile discardPile) {
		int cardPosition;
		Card card;
		
		System.out.println("Selecciona la carta que vas a soltar (según su numero de posicion): ");
		cardPosition= ci.readIntInRange(1, hand.size());
		
		card= hand.discardCard(cardPosition -1);
		discardPile.pushCard(card);
		
		this.hasClosed= true;
		System.out.printf("Has cerrado la ronda con: %s", card);
	}
}