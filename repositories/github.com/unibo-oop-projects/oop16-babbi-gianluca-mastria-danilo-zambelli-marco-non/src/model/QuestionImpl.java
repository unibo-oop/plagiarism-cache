package model;

public class QuestionImpl implements Question{

	private int idQuestion;
	private String textQuestion;  	//This variable contains the text of the question
	private String textSolutionA;	//This variable contains the text of the solution A.
	private String textSolutionB;	//This variable contains the text of the solution B.
	private String textSolutionC;	//This variable contains the text of the solution C.
	private char solution;			//This variable contains the correct answer.
	
	public QuestionImpl(int idQuestion, String textQuestion, String textSolutionA, String textSolutionB, String textSolutionC,
			char solution){
		this.idQuestion = idQuestion;
		this.textQuestion = textQuestion;
		this.textSolutionA = textSolutionA;
		this.textSolutionB = textSolutionB;
		this.textSolutionC = textSolutionC;
		this.solution= solution;
	}

	@Override
	public int getIdQuestion() {
		return this.idQuestion;
	}

	@Override
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	@Override
	public String getTextQuestion() {
		return this.textQuestion;
	}

	@Override
	public void setTextQuestion(String textQuestion) {
		this.textQuestion = textQuestion;
	}

	@Override
	public String getTextSolutionA() {
		return this.textSolutionA;
	}

	@Override
	public void setTextSolutionA(String textSolutionA) {
		this.textSolutionA = textSolutionA;
	}

	@Override
	public String getTextSolutionB() {
		return this.textSolutionB;
	}

	@Override
	public void setTextSolutionB(String textSolutionB) {
		this.textSolutionB = textSolutionB;
	}

	@Override
	public String getTextSolutionC() {
		return this.textSolutionC;
	}

	@Override
	public void setTextSolutionC(String textSolutionC) {
		this.textSolutionC = textSolutionC;
	}

	@Override
	public char getSolution() {
		return this.solution;
	}

	@Override
	public void setSolution(char solution) {
		this.solution = solution;
	}
	
	public String toString(){
		String s = "Question number: " + this.idQuestion + "\n" + this.textQuestion + "?\nSolution A: " + this.textSolutionA +
				"\nSolution B: " + this.textSolutionB + "\nSolution C: " + this.textSolutionC + "\nThe correct solution is: " +
				this.solution;
		return s;
	}
}
