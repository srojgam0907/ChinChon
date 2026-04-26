package domain;

public class AIPlayer extends Player{

	/**
	 * Constructor del jugador IA
	 * @param name variable heredada de Player
	 */
	public AIPlayer(String name) {
		super(name);
	}

	@Override
	public void playTurn(Deck deck, DiscardPile discardPile) {
		boolean tookDiscard= false;
		Card topCard;
		Card toDiscard;
		
		System.out.printf("\n>>> TURNO DE LA IA: %s\n", name);
		
		if(!discardPile.isEmpty()) {
			topCard= discardPile.peekLastCard().get();
			
			if(topCard.getPoints() <=5) {
				discardPile.takeCard().ifPresent(hand::addCard);
				System.out.printf("IA %s ha robado del descarte\n", name);
				tookDiscard= true;
			}
		}
		
		if(!tookDiscard) {
			deck.drawCard().ifPresent(hand::addCard);
			System.out.printf("IA %s ha robado del mazo\n", name);
		}
		
		hand.sortHand();
		toDiscard= hand.discardCard(hand.size() -1);
		discardPile.pushCard(toDiscard);
		
		System.out.printf("IA %s ha descartado: %s\n", name, toDiscard);
	}
}
