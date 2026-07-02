package dev.spaccabolle.score;

import java.awt.Graphics;

import dev.spaccabolle.gfx.Assets;

// TODO: Auto-generated Javadoc
/**
 * The Class Score.
 */
public class Score {
	
	/** The temp point. */
	private int tempPoint = 0;
	
	/** The temp flyng point. */
	public static int tempFlyngPoint = 0;
	
	/** The number 1. */
	private int number1;
	
	/** The number 2. */
	private int number2;
	
	/** The number 3. */
	private int number3;
	
	/** The number 4. */
	private int number4;
	
	

	/**
	 * Instantiates a new score.
	 *
	 * @param number1 the number 1
	 * @param number2 the number 2
	 * @param number3 the number 3
	 * @param number4 the number 4
	 */
	public Score( final int number1, final int number2, final int number3, final int number4 ) {
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
		this.number4 = number4;
	}
	
	/**
	 * Adds the point.
	 *
	 * @param point the point
	 * @param flyngPoint the flyng point
	 */
	public void addPoint( int point, int flyngPoint ) {
		this.tempPoint = this.tempPoint + ( point * 10 );
        tempFlyngPoint = (int)(tempFlyngPoint + ( Math.pow(2, flyngPoint) ) );
        this.tempPoint = this.tempPoint + tempFlyngPoint;
       
       
		for (int i = 0; i <= 3; i++) {
			int definitive = this.tempPoint / ( power(10, 3-i) ); //prima le centinaia, poi le decine e infine le unità
			switch(i) {
		    	case 0: this.number1 = definitive;
		    		break;
		    	case 1: this.number2 = definitive;
		    		break;
		    	case 2: this.number3 = definitive;
		    		break;
		    	case 3: this.number4=definitive;
		    		break;
		    	default:
		    		break;
		    }
			
			this.tempPoint = this.tempPoint - definitive * power(10, 3 - i);   
		}	
		 flyngPoint=0;
		 
		
		
	}
	
	/**
	 * Gets the number 1.
	 *
	 * @return the number 1
	 */
	// Getter dei singoli valori per il punteggio
	public int getNumber1() {
		return this.number1;
	}

	/**
	 * Gets the number 2.
	 *
	 * @return the number 2
	 */
	public int getNumber2() {
		return this.number2;
	}

	/**
	 * Gets the number 3.
	 *
	 * @return the number 3
	 */
	public int getNumber3() {
		return this.number3;
	}

	/**
	 * Gets the number 4.
	 *
	 * @return the number 4
	 */
	public int getNumber4() {
		return this.number4;
	}

	/**
	 * Power.
	 *
	 * @param U the u
	 * @param V the v
	 * @return the int
	 */
	
	public static int power(int U, int V) {
		int risp = 1;  // Inizializzo la risposta finale
		for (int i = 1; i <= V; i++) { 
		    risp = risp * U;
		}
		return risp;
	}
	
	/**
	 * Sets the zero.
	 *
	 * @param score the new zero
	 */
	public static void setZero(Score score) {
		score.number1=0;
		score.number2=0;
		score.number3=0;
		score.number4=0;

		
	}
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.numbers[this.number1], 340, 600, 50, 50, null);
		g.drawImage(Assets.numbers[this.number2], 380, 600, 50, 50, null);
		g.drawImage(Assets.numbers[this.number3], 420, 600, 50, 50, null);
		g.drawImage(Assets.numbers[this.number4], 460, 600, 50, 50, null);
	}
}
