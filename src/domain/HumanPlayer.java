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
		boolean drawn;
		boolean discarded;
		boolean choice;
		int cardPosition;
		
		System.out.printf("\n=================================\nTURNO DE: %s\n=================================\n", name.toUpperCase());
		
		// Mostrar estado actual
        System.out.println(discardPile.toString());
        hand.sortHand();
        System.out.println(hand.toString());
		
        //ROBO
        drawn= false;
        
        while(!drawn) {
        	System.out.println("¿Robas del [D]escarte o del [M]azo?:");
        	choice= ci.readBooleanUsingChar('d', 'm'); //d = true, m = false
        	
        	if(choice) {
        		if(!discardPile.isEmpty()) {
        			discardPile.takeCard().ifPresent(hand::addCard);
        			drawn= true;
        		
        		} else {
        			System.out.println("La pila de descartes está vacía");
        		}
        	}else {
        		deck.drawCard().ifPresent(hand::addCard);
        		drawn= true;
        	}
        }
        
        hand.sortHand();
        System.out.printf("\nHas robado. Tu mano actual es:\n%s", hand.toString());
        
        //DESCARTE
        discarded= false;
        
        while(!discarded) {
        	System.out.print("Elige la posición de la carta que quieres descartar: ");
        	cardPosition= ci.readIntInRange(1, hand.size());
        	
        	Card c= hand.discardCard(cardPosition -1);
        	discardPile.pushCard(c);
        	
        	System.out.println("Has descartado: " + c.toString());
        	discarded= true;
        }
	}
}