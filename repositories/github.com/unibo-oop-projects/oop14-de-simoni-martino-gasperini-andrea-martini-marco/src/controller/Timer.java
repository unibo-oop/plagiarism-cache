package controller;

/*
 * Link:
 * https://bitbucket.org/ravaioli_ragazzini_portolani/oop14-prjqfs/src/963aa69d273663b7d796c9410bd1cf72ca844738/src/org/bitbucket/questForStuff/utilities/Timer.java?at=default
 */

/**
 * This thread models a simple timer. 
 * @author Giacomo Ravaioli
 * @author Martino De Simoni
 * 
 */

//Io, Martino, ho operato solo qualche leggera modifica.

public class Timer extends Thread {

	private int ms;
	private int tick = 10; //Se il tick di un orologio da polso è di un secondo, qui è di 10 ms. Con meno, ho paura ci siano problemi di multithreading.
	
	private boolean stopped;
	
	private volatile boolean toStop;
	
	public Timer(){
	
		this.ms = 0;
		
		this.toStop = false;
		this.stopped = false;
	}
	
	public void run(){
		while(!toStop) {
			if(!stopped)  this.ms+=tick;
			
			
			try{
				sleep(tick);
			} catch(Exception e){
			}
		}
	}
	
	public void forceStop(){
		toStop = true;
		interrupt();
	}
	
	public int getTime(){
		return  this.ms;
	}
	
	public void stopTimer(){
		stopped = true;
	}
	
	public void resumeTimer(){
		stopped = false;
	}
	
	public boolean isStopped(){
		return this.stopped;
	}
	
	public void restart(){
		
		this.ms=0;
		
		
	}
	
	public String toString(){
		return  Integer.toString(this.ms);
	}
}
