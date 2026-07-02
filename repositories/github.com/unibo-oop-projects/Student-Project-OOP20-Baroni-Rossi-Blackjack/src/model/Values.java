package model;
/**
 * 
 * @author bon98
 *
 */
public enum Values {
	one(1),two(2),three(3),four(4),five(5),six(6),seven(7),eight(8),
	nine(9),ten(10),jack(10),queen(10),king(10),ace(11);

	private int v;
	
	Values(int v) {
		// TODO Auto-generated constructor stub
		this.v = v;
	}
	/**
	 * 
	 * @return
	 */
	public int getV() {
		return this.v;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static int getValues(Values value) {
		switch (value) {
		case one:
			return 1;
		case two:
			return 2;
		case three:
			return 3;
		case four:
			return 4;
		case five:
			return 5;
		case six:
			return 6;
		case seven:
			return 7;
		case eight:
			return 8;
		case nine:
			return 9;
		case ten:
			return 10;
		case jack:
			return 10;
		case queen:
			return 10;
		case king:
			return 10;
		case ace:
			return 11;
		default:
			return 0;
		}

	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Values getValue(int value) {
		switch (value) {
		case 1:
			return one;
		case 2:
			return two;
		case 3:
			return three;
		case 4:
			return four;
		case 5:
			return five;
		case 6:
			return six;
		case 7:
			return seven;
		case 8:
			return eight;
		case 9:
			return nine;
		case 10:
			return ten;
		case 11:
			return jack;
		case 12:
			return queen;
		case 13:
			return king;
		default:
			return ace;
		}
	}
	
	
	
	
}
