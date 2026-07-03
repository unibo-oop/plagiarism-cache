package dodge;

public interface Dodge {

	void processInput(Input input);
	
	void setInput(final Input input);

	int getLife();

	void setLife(final int life);

	int getWumpas();

	/*
	 * add a wumpa fruit and check if there's enough to get an extra life
	 */
	void addWumpas();

	void move();

	double getBoundUp();

	double getBoundDown();

	boolean getImmunity();

	void setImmunity();

	void removeImmunity();

}