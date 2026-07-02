package model;

import java.util.Optional;

import memento.MementoPanelChange;
import utilities.Utility;


public class GameModel {
	private MementoPanelChange memento;
	private model.Timer timer;
	private Score score;
	private Optional<TimerObserver> obs;
	public GameModel() {
		this.memento = new MementoPanelChange();
		this.obs = Optional.empty();
		score = new ScoreImpl();
	}
	public GameModel(TimerObserver obs) {
		this();
		this.obs = Optional.of(obs);
	}
	public GameModel(int max) {
		this();
		score = new ScoreImpl(max);
		
	}
	
	public void savePreviousPanelName(String panelName) {
		memento.savePanelName(new String(panelName));
	}
	public String getPreviousPanelName() {
		return new String(memento.getPreviousPanelName());
	}
	public int getScore1() {
		return score.getScore1();
	}
	public int getScore2() {
		return score.getScore2();
	}
	public void resetScore() {
		score.reset();
	}
	public void startMatch() {
		if(obs.isPresent()) {
			startMatchTimer(5);
			score.addScorePlayer1();
			score.addScorePlayer1();
			score.addScorePlayer2();
		}
	}
	public void startMatchTimer(int seconds) {
		timer = new TimerImpl(Utility.MSEC_CONST,Utility.MSEC_CONST*seconds,obs.get());
		timer.start();	
		/*Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				timer.pause();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timer.resume();
			}
		}, 3000);*/
	}
	/*public void startMatchTimer(int seconds) {
		if(timerThread== null || !timerThread.isAlive()) {
			if(timerThread== null ) {
				System.out.println("era null");
			}else if(!timerThread.isAlive()) {
				System.out.println("non era vivo");
			}
			timerThread = new MatchTimer(seconds);
			timerThread.start();
			if(i==0) {
				i=1;
				Timer t = new Timer();
				t.schedule(new TimerTask() {
					
					@Override
					public void run() {
						pauseMatchTimer();
						
					}
				}, 3000);
			}
			
			
		}
		else {
			System.out.println("svegliati");
			timerThread.notify();
		}
	}*/
	public void pauseMatchTimer() {
		timer.pause();
	}
	public void resumeMatchTimer() {
		timer.resume();
	}
	
		
}

