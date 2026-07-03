package model;

/**
 * Question is the interface that corresponds to the dice of the game.
 *
 */
public interface Question {

	/**
	   *
	   * @return the question's Id.
	   */
	int getIdQuestion();
	
	/**
	   * This method set the question's Id.
	   *
	   * @param idQuestion
	   *          the question's Id.
	   */
	void setIdQuestion(int idQuestion);
	
	/**
	   *
	   * @return the text of the question.
	   */
	String getTextQuestion();
	
	/**
	   * This method set the text of the question.
	   *
	   * @param name
	   *          player's name.
	   */
	void setTextQuestion(String textQuestion);
	
	/**
	   *
	   * @return the text of the solution A.
	   */
	String getTextSolutionA();
	
	/**
	   * This method set the text of the solution A.
	   *
	   * @param textSolutionA
	   *          text of the solution A.
	   */
	void setTextSolutionA(String textSolutionA);
	
	/**
	   *
	   * @return the text of the solution B.
	   */
	String getTextSolutionB();
	
	/**
	   * This method set the text of the solution B.
	   *
	   * @param textSolutionB
	   *          text of the solution B.
	   */
	void setTextSolutionB(String textSolutionB);
	
	/**
	   *
	   * @return the text of the solution C.
	   */
	String getTextSolutionC();
	
	/**
	   * This method set the text of the solution C.
	   *
	   * @param textSolutionC
	   *          text of the solution C.
	   */
	void setTextSolutionC(String textSolutionC);
	
	/**
	   *
	   * @return the solution of the question.
	   */
	char getSolution();
	
	/**
	   * This method set the solution of the question.
	   *
	   * @param solution
	   *          solution of the question.
	   */
	void setSolution(char solution);
	
	String toString();
}
