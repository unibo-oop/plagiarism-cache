package it.bomberman.hud;

import java.util.Timer;
import java.util.TimerTask;;

public class Clock {

	private Timer timer;
	private RemindTask remindTask;

	public Clock(int seconds) {
		this.timer = new Timer();
		remindTask = new RemindTask(seconds);
		timer.schedule(remindTask, 0, // ritardo
				1 * 1000); // Intervallo esecuzioni ogni sec
	}
		
	public String getTime() {
		if (remindTask.getTime() == 0) {
			return "000";
		} else {
			return remindTask.getTime() + "";
		}
	}

	public class RemindTask extends TimerTask {
		int time;

		public RemindTask(int time) {
			this.time = time;
		}

		@Override
		public void run() {
			if (time != 0) {
				time--;
			}
		}

		public int getTime() {
			return this.time;
		}
	}
}
