package model;

import java.io.Serializable;

public class TokenImpl implements Token, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idToken;
	private Color team;
	private Standing standing;
	private int nStep;
	
	public TokenImpl(int idToken, Color team, Standing standing){
		this.idToken = idToken;
		this.team = team;
		this.standing = standing;
		this.nStep = 0;
	}
	
	@Override
	public int getIdToken() {
		return this.idToken;
	}

	@Override
	public void setIdToken(int idToken) {
		this.idToken = idToken;
	}

	@Override
	public Color getTeam() {
		return this.team;
	}

	@Override
	public void setTeam(Color team) {
		this.team = team;
	}

	@Override
	public Standing getStanding() {
		return this.standing;
	}

	@Override
	public void setStanding(Standing standing) {
		this.standing = standing;
	}
	
	public String toString(){
		String s = "Token number: " + this.idToken + ", team: " + this.team + ", standing: " + this.standing + ", nStep: " + this.nStep + ".";
		return s;
	}
	
	public int getnStep(){
		return this.nStep;
	}
	
	public void setnStep(int nStep){
		this.nStep = nStep;
	}

}
