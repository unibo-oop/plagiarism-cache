package game;

public class SpeedManage implements Speed {
	/*
	 * idea di fare due costruttori il primo senza parametri da richiamare per la modalità
	 *  normale e uno con il parametro della difficoltà per inserire semplice/interm/diff
	 */
	public static final float START_SPEED =  3.0f;


	public Integer score;
	public float speedX;
	public float speedY; //final se inteso solo come ampiezza del salto
	

	@Override
	public void reset() {
		score=0;
		speedX = START_SPEED;

	}

	@Override
	public void start() {
		reset();//?

	}

	@Override
	public Integer getScore() {
		return score;
	}

	@Override
	public float getSpeedX() {
		return speedX;
	}

	@Override
	public float getSpeedY() {
		return speedY;
	}
	
	private void acceleration() {
		//log 2 (x)
	}

}
