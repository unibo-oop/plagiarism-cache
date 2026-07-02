package util;

import controller.MainController;

/**
 * Create to count second simlating song position
 * @author rrok
 *
 */
public class PositionSongThread extends Thread {
		private int sec=0;
		private MainController controller;
		public Object SongPosThread;
		private boolean paused=true;
	    public PositionSongThread(MainController controller) {
			// TODO Auto-generated constructor stub
	    	this.controller=controller;
		}
		@Override
		public void run() {
			while (true) {
				try {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(!paused){
					sec++;
					try {
						if(sec==controller.getReproducingSongSeconds())
							controller.onTrackFinished();
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					}
				} catch (java.lang.NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	    	
	    }
		/**
		 * get The position, value that this thread has incremented
		 * @return int
		 */
		public int getValue() {
			// TODO Auto-generated method stub
			return this.sec;
		}
		
		/**
		 * used to stop incremetenting of counter
		 * @param paused boolean
		 */
		public void setPausedThread (boolean paused) {
			this.paused = paused;
		}
		
		/**
		 * reset counter starting from zero
		 */
		public void resetPositionThread(){
			this.sec=0;
			paused=false;
		}

}
