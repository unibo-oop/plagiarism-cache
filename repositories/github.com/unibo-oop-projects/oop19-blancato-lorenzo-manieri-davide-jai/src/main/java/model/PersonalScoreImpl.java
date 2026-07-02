package model;


public class PersonalScoreImpl implements PersonalScore {
	
	private int position;
	private String user; 
	private int score = 0; 
	private int enemyKilled = 0;
	
	public PersonalScoreImpl() {
		this.score = 0;
		this.enemyKilled = 0;
	}
	

	
	public PersonalScoreImpl(int position, String user, int enemyKilled, int score) {
		this.position = position; 
		this.user = user;
		this.score = score;
		this.enemyKilled = enemyKilled;
	}
	

	
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getEnemyKilled() {
		return this.enemyKilled;
	}
	
	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}
	
	
	
	@Override
	public String toString() {
		return this.position + " | " +this.user + " | " + this.enemyKilled + " | " + this.score;
	}
	
	public static PersonalScoreImpl  getPersonalScoreFromString(String score) {
		String regex = " | ";
		String[] stringa;
		stringa = score.split(regex);
		PersonalScoreImpl p = null;
		try {
			p = new PersonalScoreImpl(Integer.parseInt(stringa[0]), stringa[2],
					Integer.parseInt(stringa[4]),Integer.parseInt(stringa[6]));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return p;
	}

	@Override
	public int compareTo(PersonalScore o) {
		if (this.score > o.getScore() || (this.score == o.getScore() && this.enemyKilled > o.getEnemyKilled())) {
			return 1; 
		}else {
			if (this.score < o.getScore() || (this.score == o.getScore() && this.enemyKilled > o.getEnemyKilled())) {
				return -1;
			}
		}
		return 0;
	}



	

}
