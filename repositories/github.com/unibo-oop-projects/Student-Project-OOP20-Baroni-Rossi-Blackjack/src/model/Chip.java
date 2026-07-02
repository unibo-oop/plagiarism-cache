package model;
/**
 * 
 * @author
 *
 */
public enum Chip {
	five(5),
	twenty(20),
	fifty(50),
	hundred(100);
	
	private int chip;
	/**
	 * 
	 * @param i
	 */
	Chip(int i) {
		this.setChip(i);
	}
	/**
	 * 
	 * @param chip
	 * @return
	 */
	public static int getChipValue(Chip chip) {
		switch (chip) {
		case five:
			return 5;
		case twenty:
			return 20;
		case fifty:
			return 50;
		case hundred:
			return 100;
		default:
			return 0;
		}
	}
	/**
	 * 
	 * @return
	 */
	public int getChip() {
		return chip;
	}
	/**
	 * 
	 * @param chip
	 */
	public void setChip(int chip) {
		this.chip = chip;
	}
	
}
