package domain;

/**
 * Clase abstracta que representa a un jugador en la partida.
 * Define los atributos comunes como el nombre, la mano y la puntuación,
 * y establece el contrato para el comportamiento del turno.
 */
public abstract class Player {

	  protected String name;
	  protected Hand hand;
	  protected int totalScore;
	  
	  /**
	   * Constructor base para cualquier jugador.
	   * @param name Nombre elegido para el jugador.
	   */
	  public Player(String name) {
		  this.name= name;
		  hand= new Hand();
		  totalScore= 0;
	  }
	  
	  /**
	   * Define la lógica de ejecución del turno. 
	   * Cada subclase implementa cómo interactúa con el mazo y los descartes.
	   * @param deck El mazo principal de la mesa.
	   * @param discardPile El montón de cartas descartadas.
	   */
	  public abstract void playTurn(Deck deck, DiscardPile discardPile);
	  
	  /**
	   * Suma puntos al marcador acumulado del jugador.
	   * 
	   * @param points Puntos calculados al finalizar una ronda.
	   */
	  public void addPoints(int points) {
		  totalScore += points;
	  }

	  /**
	   * @return name Devuelve el nombre del jugador
	   */
	  public String getName() {
		  return name;
	  }

	  /**
	   * @return hand Devuelve la mano del jugador
	   */
	  public Hand getHand() {
		  return hand;
	  }

	  /**
	   * @return totalScore Devuelve los puntos del jugador
	   */
	  public int getTotalScore() {
		  return totalScore;
	  }
	  
	  
	  @Override
	  public String toString() {
		  return String.format("Jugador: %-15s | Puntuación Total: %d", name, totalScore);
	  }
}