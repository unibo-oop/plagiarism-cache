package model;

import java.io.Serializable;

public class Score implements Serializable{
	/**
	 * Create object score with name and score
	 */
	private static final long serialVersionUID = 1L;
    private String name;
	private int score;

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
    
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
