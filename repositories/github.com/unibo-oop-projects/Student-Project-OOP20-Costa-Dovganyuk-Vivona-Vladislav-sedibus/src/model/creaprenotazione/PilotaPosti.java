package model.creaprenotazione;

public final class PilotaPosti {

	private final static int MIN_POSTI = 1;
	private int nPosti;
	private int maxPosti;
	
	public PilotaPosti(int max) {
		this.maxPosti = max;
	}
	
	public void azzeraPosti() {
		this.nPosti = MIN_POSTI;
	}
	
	public int getNumeroPosti() {
		return this.nPosti;
	}
	
	public void setNumeroPosti(int n) {
		this.nPosti = n;
	}
	
	public void aggiungiPosto() {
		if(this.nPosti < this.maxPosti) {
			this.nPosti++;
		}
	}
	
	public void togliPosto() {
		if(this.nPosti > MIN_POSTI) {
			this.nPosti--;
		}
	}
	
}
