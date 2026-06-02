# CHINCHÓN 🎮

Juego de cartas español programado en Java.

## Objetivo 🚩

Ser el jugador con menos puntos al final de la partida, formando combinaciones de cartas o sacando un chinchón.

## Como se juega 🕹️

Primero el usuario debe configurar el juego según:
  - El número de jugadores en total (2 - 5)
  - Cuántos de esos jugadores son IAs 
  - Cuántas barajas quieren para jugar (1 o 2)
  - Cómo se llaman los jugadores

Después se reparten 7 cartas a cada jugador y se empieza la partida.

La partida se desarrolla por turnos y rondas. En cada turno, el jugador, ya sea humano o IA, debe decidir diferentes jugadas segun la fase del juego en la que esté:
  1. **Fase de robo**: El jugador debe elegir entre robar la carta superior del Mazo (desconocida) o la última carta de la Pila de Descartes (visible).
  2. **Fase de Evaluación**: El jugador añade la carta a su mano (teniendo temporalmente 8 cartas) y busca combinaciones (tríos, escaleras o chinchón).
  3. **Fase de Cierre o Descarte**:
       - Si el jugador logra combinar sus cartas de forma que los puntos no combinados sean iguales o
         inferiores a 3 y las combinaciones sean válidas, puede elegir Cerrar.
       - Si no puede o no quiere cerrar, debe Descartar una carta en la pila de descartes, volviendo a tener 7
         cartas y cediendo el turno.

Cuando uno de los jugadores cierre la partida, se contarán todos los puntos y el que tenga la menor cantidad de puntos será el ganador.

## Reglas 📜

- No se puede cerrar la partida en la primera ronda. Todos los jugadores deben jugar al menos 1 vez.
- Las combinaciones válidas son:
    - Trios: 3 cartas del mismo valor
    - Escaleras: 3 o más cartas del mismo palo en orden consecutivo
    - Chinchón: 7 cartas del mismo palo en orden consecutivo
- La puntuación se consigue sumando el valor de las cartas no combinadas
- Si un jugador consigue un chinchón, gana automáticamente con -10 puntos
- Cuando uno de los jugadores llega a los 100 puntos, se cierra automáticamente la partida y se anuncia al ganador

# Ejemplo de Jugada 

**Bienvenida y configuración**

Muestra la bienvenida y la configuracion del juego. Puedes configurar la cantidad de jugadores que vais a jugar la partida, la cantidad de barajas que vais a usar, el nombre de los jugadores y si son IAs o no.

![configuracion](assets/configuracion.png)

**Ejemplo de ronda jugada**

Primero muestra el principio de la partida cuando se reparten las cartas la primera vez y luego empieza la partida. A continuación, muestra el numero de la ronda que se esta jugando y a quién le corresponde el turno. 

En el caso del turno del Jugador Humano, le enseña la carta disponible en el descarte y la mano que tiene disponible para jugar, le pregunta de donde quiere robar y luego vuelve a mostrar la mano y pregunta que carta quiere descartar.

En el caso del turno de la IA, solo muestra por pantalla de donde ha robado y que carta a descartado ya que la IA hace su jugada automaticamente.

![ejemplo de ronda](assets/ejemploRonda.png)

**Control de errores**

El juego contempla un control de errores hacia el usuario. Por ejemplo, cuando te pregunta de donde quieres robar, controla que se escriba una d o una m y si escribes cualquier otro caracter informa al usuario del error y da la opcion de volver a escribirlo.

![control de errores](assets/controlErrores.png)

**Cierre y ganador**

Se informa por pantalla quien ha cerrado la partida, se muestran los puntos de cada jugador y quien ha ganado la partida.

![cierre y ganador](assets/cierrePartida.png)

## Estructura del proyecto

    Chinchon/
    |-- src/
    |   |-- app/
    |   |   |-- ConsoleInput.java
    |   |   |-- GameEngine.java
    |   |   |-- Main.java
    |   |
    |   |-- domain/
    |   |   |-- AIPlayer.java
    |   |   |-- Card.java
    |   |   |-- CombinationChecker.java
    |   |   |-- Deck.java
    |   |   |-- DeckFactory.java
    |   |   |-- DiscardPile.java
    |   |   |-- Hand.java
    |   |   |-- HumanPlayer.java
    |   |   |-- Player.java
    |   |   |-- Suit.java
    |   |   |-- Value.java
    |
    |-- test/
    |   |-- test/
    |   |   |-- CombinationCheckerTest.java
    |
    |-- assets/
    |   |-- uml-Chinchon.drawio.pdf
    |   |-- uml-Chinchon.drawio.png
    |   |-- ejecucionTest.png
    |   |-- cierrePartida.png
    |   |-- controlErrores.png
    |   |-- ejemploRonda.png
    |   |-- configuracion.png
    |
    |-- README.md

## Clases 📑

### Paquete app

**>> Main**

Punto de entrada principal de la aplicación del juego del Chinchón. Esta clase se encarga de arrancar el entorno del programa y ceder el control al motor del juego.

[Ver Main](src/app/Main.java)

**>> GameEngine**

Motor principal que controla el flujo de la partida.

[Ver GameEngine](src/app/GameEngine.java)

**>> ConsoleInput**

Clase de utilidad para la gestión de la entrada de datos por consola. Implementa el patrón Singleton para asegurar una única instancia de lectura en toda la aplicación y proporciona métodos validados para leer diferentes tipos de datos, garantizando que el programa no se interrumpa por entradas inválidas.

[Ver ConsoleInput](src/app/ConsoleInput.java)

### Paquete domain

**>> Player**

Clase abstracta que representa a un jugador en la partida. Define los atributos comunes como el nombre, la mano y la puntuación, y establece el contrato para el comportamiento del turno.

[Ver Player](src/domain/Player.java)

**>> HumanPLayer**

Representa a un jugador controlado por un humano.

[Ver HumanPlayer](src/domain/HumanPlayer.java)

**>> AIPlayer**

Representa a un jugador controlado por una IA

[Ver IAPlayer](src/domain/AIPlayer.java)

**>> Card**

Representa una carta individual de la baraja española utilizada en el juego del Chinchón. Cada carta consta de un palo y un valor. Esta clase implementa Comparable para permitir la ordenación automática de la mano del jugador.

[Ver Card](src/domain/Card.java)

**>> Deck**

Gestiona el mazo principal de cartas de la partida. Se encarga de suministrar cartas a los jugadores y de recargarse cuando se agota. Utiliza una estructura de datos Deque para un comportamiento de pila (LIFO).

[Ver Deck](src/domain/Deck.java)

**>> DeckFactory**

Factoría encargada de la creación y configuración inicial del mazo de cartas. Esta clase centraliza la lógica para generar barajas completas mezcladas y listas para jugar.

[Ver DeckFactory](src/domain/DeckFactory.java)

**>> Hand**

Representa la mano de un jugador en el juego del Chinchón. Gestiona un conjunto de 7 cartas (u 8 durante el turno) y contiene la lógica para organizar combinaciones (escaleras o grupos) y calcular puntos.

[Ver Hand](src/domain/Hand.java)

**>> DiscardPile**

Representa el montón de cartas descartadas boca arriba en la mesa. Los jugadores pueden interactuar con esta pila robando la última carta descartada o añadiendo una nueva al finalizar su turno.

[Ver DiscardPile](src/domain/DiscardPile.java)

**>> CombinationChecker**

Clase para detectar combinaciones válidas en una mano. Se encarga de identificar grupos de cartas iguales (Sets) y escaleras (Runs).

[Ver CombinationChecker](src/domain/CombinationChecker.java)

**>> Suit**

Representa los cuatro palos de la baraja española utilizados en el juego del Chinchón. Los palos disponibles son Oros, Copas, Espadas y Bastos. Cada palo tiene asociado un emoji para facilitar su visualización en la consola.

[Ver Suit](src/domain/Suit.java)

**>> Value**

Representa los valores numéricos de las cartas en la baraja española para el Chinchón. Incluye los números del 1 al 7 y las figuras (Sota, Caballo y Rey).
Según las reglas del juego:
- Los números 8 y 9 no se utilizan.
- Las figuras tienen un valor de 10, 11 y 12 puntos respectivamente.

[Ver Value](src/domain/Value.java)

## Patrones de diseño 🎨

**Singleton**

Implementado en la clase ConsoleInput. Garantiza que solo exista una instancia del lector de consola y el Scanner en toda la aplicación, evitando conflictos de flujo de entrada (System.in) y optimizando el uso de recursos.

**Factory**

Utilizado en DeckFactory. Centraliza la lógica de creación de la baraja española, abstrayendo al resto del sistema de la complejidad de instanciar cada carta con su palo y valor correspondiente, permitiendo además generar mazos simples o dobles fácilmente.

## Pruebas 🎲

### De caja blanca

- **Lógica de Combinaciones:** Me aseguré de que los algoritmos de la clase CombinationChecker no dejaran cabos sueltos. Por ejemplo, en las escaleras, comprobé que el código revisa paso a paso que todas las cartas sean del mismo palo y que los números vayan uno detrás de otro sin saltos.
  
- **Inteligencia Artificial:** Comprobé paso a paso cómo la IA toma sus decisiones. Me aseguré de que realmente compare todas sus cartas antes de descartar y que siempre elija la opción que le deje menos puntos, confirmando que el flujo del algoritmo es lógico y eficiente.

### De caja negra

- **Entrada de Datos:** Probé el sistema metiendo datos que no tienen sentido para ver cómo reaccionaba. Por ejemplo, intenté poner letras donde pedía números de jugadores, puse que quería jugar con 5 barajas o cuando pregunta si quieres robar del [M]azo o del [D]escarte escribi la letra A. El resultado fue que el programa aguantó perfectamente; en lugar de cerrarse con un error, se dio cuenta del fallo, me avisó y me pidió el dato otra vez.

- **Flujo del Turno:** Verifiqué que el ciclo de juego fuera fluido. Si robas una carta, aparece en tu mano; si descartas, se va a la pila. Comprobé que el juego hace exactamente lo que el usuario espera que pase en cada paso.

### Unitarias

Se han realizado test para comprobar la correcta funcionalidad de los metodos de CombinationChecker. Comprobando los tríos, las escaleras, el chinchon y los puntos combinados y sin combinar.

[Ver Código Test](src/test/CombinationCheckerTest.java)

**Ejecución de los test**

![Test](assets/ejecucionTest.png)

## UML

![UML](assets/uml-Chinchon.drawio.png)

