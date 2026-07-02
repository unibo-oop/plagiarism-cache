package other;
import java.util.TimerTask;

import javax.swing.JLabel;

import interfaces.ChronometerInterface;

/**
 * classe cronometro che gestice la durata della partita
 * 
 * @author Alessandro
 *
 */
public class Chronometer extends TimerTask implements ChronometerInterface{
	private int hours = 0;
	private int minuts = 0;
	private int second = 0;
	
	private boolean hasStarted = false;
	
	private JLabel chronometer = new JLabel();

	/**
	 * costruttore di cronometro
	 * 
	 * @param chronometer
	 *     la label che sarà da modificare con il passare dei secondi
	 */
	public Chronometer(JLabel chronometer){
		this.chronometer = chronometer;
	}
	
	/**
	 * avvio della task
	 */
	@Override
	public void run() {
		this.completeTask();
	}
	
	/**
	 * imposta il cronometro partito e aggiorna la label ogni secondo
	 */
	private void completeTask() {
		hasStarted = true;
		this.second++;
		if(this.second == 60) {
			this.minuts++;
			this.second = 0;
			if(this.minuts == 60) {
				this.hours++;
				this.minuts = 0;
				this.second = 0;
			}
		}
		this.chronometer.setText("Tempo : " + Integer.toString(this.hours) + " : " + Integer.toString(this.minuts) + " : " + Integer.toString(this.second));
	}
	
	/**
	 * imposta la label a 0 secondi
	 * N.B. il cronometro non si ferma
	 */
	public void restartChronometer() {
		this.second = 0;
		this.minuts = 0;
		this.hours = 0;
	}
	
	/**
	 * 
	 * @return se il cronometro è partito
	 */
	public boolean hasTimerStarted() {
		return this.hasStarted;
	}
	
	/**
	 * 
	 * @return il tempo in secondi
	 */
	public int getTime(){
		return (this.hours*3600)+(this.minuts*60)+this.second;
	}
}
