package view;

public interface ViewObserver {

	/**
	 * inserimento nodi per la grafica
	 */
    void newGame();

    /**
     * risposta alla pressione tasto W
     */
	void upPressed();

	/**
	 * risposta alla pressione tasto D
	 */
	void rightPressed();

	/**
	 * risposta alla pressione tasto A
	 */
	void leftPressed();

	/**
	 * risposta alla pressione tasto S
	 */
	void downPressed();

	/**
	 * risposta al click del mouse
	 * @param mousex
	 * @param mousey
	 */
	void click(double mousex, double mousey);
}
