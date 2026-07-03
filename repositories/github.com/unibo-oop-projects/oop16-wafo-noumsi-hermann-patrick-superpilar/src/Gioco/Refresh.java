package Gioco;

public class Refresh implements Runnable {

	private final int PAUSE = 3 ; // tempo di attesa per aggiornare lo schermo
	
	public void run() {
		
		// si ridesegna lo schermo ogni pausa millisecondi
		while (true){
			Main.scene.repaint();
			//Main.test.paintAll(Main.test.getGraphics());
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
	}
	
} 
