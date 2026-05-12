package app;
import java.util.*;
import tema2_1_EscrituraEnPantalla.colores.Colors; 

public class ConsoleInput {

	private Scanner kb;
	private static ConsoleInput instance;
	
    private ConsoleInput(Scanner keyboard) {
    	kb= keyboard; 
    }
    
    public static ConsoleInput getInstance(Scanner keyboard) {
    	if(instance == null) {
    		instance= new ConsoleInput(keyboard);
    	}
    	return instance; 
    }
    
    private void cleanInput() { //Limpiar el buffer
    	kb.nextLine();
    }
    
    public int readInt() { //Leer un entero
    	int numInt= 0;
    	boolean error= false; 
    	do {
	    	try {
	    		numInt= kb.nextInt();
	    		error= false;
	    		
	    	} catch (InputMismatchException e){
	    		System.out.printf("%sERROR. Rango válido: %d a %d. Intentalo otra vez%s\n", Colors.RED, Integer.MIN_VALUE, Integer.MAX_VALUE, Colors.RESET);
	    		error= true;
	    		
	    	} finally {
	    		cleanInput();
	    		
	    	}

    	} while(error);
    	
    	return numInt;
    }
    
    public int readIntLessThan(int upperBound) { //Leer un entero < parametro
    	int numInt= 0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero menor a %d, ", upperBound);
			numInt = readInt();

			if (numInt >= upperBound) {
				System.out.printf("%sERROR, numero igual o mayor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numInt;
    }
    
    public int readIntLessOrEqualThan(int upperBound) { //Leer un entero <= parametro
    	int numInt= 0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero menor o igual a %d, ", upperBound);
			numInt = readInt();

			if (numInt > upperBound) {
				System.out.printf("%sERROR, numero mayor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);

    	return numInt;
    }
    
    public int readIntGreaterThan(int lowerBound) { //Leer un entero > parametro
    	int numInt= 0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero mayor a %d, ", lowerBound);
			numInt = readInt();

			if (numInt <= lowerBound) {
				System.out.printf("%sERROR, numero igual o menor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numInt;
    }
    
    public int readIntGreaterOrEqualThan(int lowerBound) { //Leer un entero >= parametro
    	int numInt= 0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero mayor o igual a %d, ", lowerBound);
			numInt = readInt();

			if (numInt < lowerBound) {
				System.out.printf("%sERROR, numero menor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;
				
			} else {
				error= false;
			}

		} while (error);
    	
    	return numInt;
    }
    
    public int readIntInRange(int lowerBound, int upperBound) { //Leer un entero en un rango
    	int numInt= 0;
    	boolean error= false;
		do {
			numInt = readInt();

			if (numInt < lowerBound || numInt > upperBound) {
				System.out.printf("%sERROR, numero fuera de rango. Introduce un numero entre %d y %d%s\n", Colors.RED, lowerBound, upperBound, Colors.RESET);
				error = true;
				
			} else {
				error= false;
			}

		} while(error);
    	
    	return numInt; 
    }
    
    public double readDouble() { //Leer un double
    	double numDouble= 0.0;
    	boolean error= false;
    	do {
	    	try {
	    		numDouble= kb.nextDouble();
	    		error= false;
	    		
	    	} catch (InputMismatchException e){
	    		System.out.printf("%sERROR. Rango válido: %f a %f. Intentalo otra vez%s\n", Colors.RED, Double.MIN_VALUE, Double.MAX_VALUE, Colors.RESET);
	    		error= true;
	    		 
	    	} finally {
	    		cleanInput();
	    		
	    	}
	    	
    	} while(error);
    	
    	return numDouble;
    }
    
    public double readDoubleLessThan(double upperBound) { //Leer un double < parametro
    	double numDouble= 0.0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero menor a %f, ", upperBound);
			numDouble = readDouble();

			if (numDouble >= upperBound) {
				System.out.printf("%sERROR, numero igual o mayor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;
				
			} else {
				error= false;
			}

		} while (error);
    	
    	return numDouble;
    }
    
    public double readDoubleLessOrEqualThan(double upperBound) { //Leer un double <= parametro
    	double numDouble= 0.0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero menor o igual a %f, ", upperBound);
			numDouble = readDouble();

			if (numDouble > upperBound) {
				System.out.printf("%sERROR, numero mayor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numDouble;
    }
    
    public double readDoubleGreaterThan(double lowerBound) { //Leer un double > parametro
    	double numDouble= 0.0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero mayor a %f, ", lowerBound);
			numDouble = readDouble();

			if (numDouble <= lowerBound) {
				System.out.printf("%sERROR, numero igual o menor al indicado%s\n", Colors.RED, Colors.RESET); 
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numDouble;
    }
    
    public double readDoubleGreaterOrEqualThan(double lowerBound) { //Leer un double >= parametro
    	double numDouble= 0.0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero mayor o igual a %f, ", lowerBound);
			numDouble = readDouble();

			if (numDouble < lowerBound) {
				System.out.printf("%sERROR, numero menor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numDouble;
    }
    
    public double readIDoubleInRange(double lowerBound, double upperBound) { //Leer un double en un rango
    	double numDouble= 0.0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero entre %f y %f, ", lowerBound, upperBound);
			numDouble = readDouble();

			if (numDouble < lowerBound || numDouble > upperBound) {
				System.out.printf("%sERROR, numero fuera de rango%s\n", Colors.RED, Colors.RESET);
				error = true;
				
			} else {
				error= false;
			}

		} while (error);
    	
    	return numDouble; 
    }
    
    public float readFloat() { //Leer un entero
    	float numFloat= 0.0f;
    	boolean error= false;
    	do {
	    	try {
	    		numFloat= kb.nextFloat();
	    		error= false;
	    		
	    	} catch (InputMismatchException e){
	    		System.out.printf("%sERROR. Rango válido: %d a %d. Intentalo otra vez%s\n",  Colors.RED, Float.MIN_VALUE, Float.MAX_VALUE, Colors.RESET);
	    		error= true;
	    		
	    	} finally {
	    		cleanInput();
	    		
	    	}

    	} while(error);
    	
    	return numFloat;
    }
    
    public float readFloatLessThan(float upperBound) { //Leer un entero < parametro
    	float numFloat= 0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero menor a %d, ", upperBound);
			numFloat = readFloat(); 

			if (numFloat >= upperBound) {
				System.out.printf("%sERROR, numero igual o mayor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numFloat;
    }
    
    public float readFloatLessOrEqualThan(float upperBound) { //Leer un entero <= parametro
    	float numFloat= 0;
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero menor o igual a %d, ", upperBound);
			numFloat = readFloat(); 

			if (numFloat > upperBound) {
				System.out.printf("%sERROR, numero mayor al indicad%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);

    	return numFloat;
    }
    
    public float readFloatGreaterThan(float lowerBound) { //Leer un entero > parametro
    	float numFloat= 0; 
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero mayor a %d, ", lowerBound);
			numFloat = readFloat(); 

			if (numFloat <= lowerBound) {
				System.out.printf("%sERROR, numero igual o menor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;

			} else {
				error = false;
			}

		} while (error);
    	
    	return numFloat;
    }
    
    public float readFloatGreaterOrEqualThan(float lowerBound) { //Leer un entero >= parametro
    	float numFloat= 0; 
    	boolean error= false;
		do {
			System.out.printf("Introduce un numero mayor o igual a %d, ", lowerBound);
			numFloat = readFloat(); 

			if (numFloat < lowerBound) {
				System.out.printf("%sERROR, numero menor al indicado%s\n", Colors.RED, Colors.RESET);
				error = true;
				
			} else {
				error= false;
			}

		} while (error);
    	
    	return numFloat;
    }
    
    public float readFloatInRange(float lowerBound, float upperBound) { //Leer un entero en un rango
    	float numFloat= 0; 
    	boolean error= false; 
		do {
			System.out.printf("Introduce un numero entre %d y %d, ", lowerBound, upperBound);
			numFloat = readFloat(); 

			if (numFloat < lowerBound || numFloat > upperBound) {
				System.out.printf("%sERROR, numero fuera de rango%s\n", Colors.RED, Colors.RESET);
				error = true;
				
			} else {
				error= false;
			}

		} while (error);
    	
    	return numFloat; 
    }
    
    public char readChar() { //Leer un character
    	String character= " ";
    	boolean error= false;
    	do {
    		character= readString(); 
    		
    		if(character.length() == 1) {
    			error= false;
    		
    		} else {
    			error= true;
    			System.out.printf("%sERROR. Has introducido más de un caracter%s\n", Colors.RED, Colors.RESET);
    		}

    	}while(error);
    	
    	return character.charAt(0);
    }
    
    public char readLetter() { //Leer un letra
    	String character= " ";
    	boolean error= false;
    	do {
    		character= kb.nextLine();
    		
    		if(character.length() == 1 && Character.isLetter(character.charAt(0))) {
    			error= false;
    		
    		} else {
    			error= true;
    			System.out.printf("%sERROR. Debe ser una letra. Intentalo otra vez.%s\n", Colors.RED, Colors.RESET);
    		}

    	}while(error);
    	
    	return character.charAt(0);
    }
    
    public String readString() { //Leer una cadena
    	String text= " ";

    	text= kb.nextLine();
    	
    	return text;
    }
    
    public String readStringNotEmpty() {
    	String text= " ";
    	boolean error= false;
    	do {
    		text=readString( );
    		text.trim();
    		
    		if(text.isEmpty()) {
    			System.out.printf("%sLa cadena de texto no puede estar vacía. Intentelo otra vez%s\n", Colors.RED, Colors.RESET);
    			error= true;
    			
    		} else {
    			error= false;
    		}
    		
    	}while(error);
    	
    	return text;
    }
    
    public String readString(int maxLenght) { //Leer una cadena con un max
    	String text= " ";
    	boolean error= false;
    	do {
    		System.out.printf("Introduce una cadena de texto de máximo %d caracteres", maxLenght);
        	text= readString();
        	
        	if(text.length() > maxLenght) {
        		error= true;
        		System.out.printf("%sERROR. Has sobrepasado el limite%s\n", Colors.RED, Colors.RESET);
        		
        	} else {
        		error= false;
        	}
        	
    	} while(error);
    	
    	return text; 
    }
    
    public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue) { //Leer un booleano con dos valores
    	char caracter = ' ';
    	boolean error= false;
    	
    	do {
    		caracter= readChar();
    		
    		caracter= Character.toLowerCase(caracter); 
    		
    		if(caracter == affirmativeValue || caracter == negativeValue) {
    			error= false;
    		
    		} else {
    			error= true;
    			System.out.printf("%sERROR. Introduce %c o %c%s\n",  Colors.RED,affirmativeValue, negativeValue, Colors.RESET);
    		}
    		
    	} while(error);
    	
    	if (caracter == affirmativeValue) {
    		return true;
    		
    	} else {
    		return false;
    	}
    }
}
