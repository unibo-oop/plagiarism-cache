package util;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import controller.MainController;
import view.SouthPanel;

public class ProgressBarThread extends Thread {
	private JSlider progressSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
	private JPanel southP;
	private MainController controller;
	private JLabel timerCounter;
	static int min = 0;
	static int h = 0;
	private int sec;
	private boolean songInPause;

	public ProgressBarThread(JSlider progressSlider, SouthPanel southPanel, MainController contr, JLabel labelTimeCounter) {
		this.progressSlider = progressSlider;
		this.southP = southPanel;
		controller = contr;
		timerCounter = labelTimeCounter;
	}
	

	@Override
	public void run() {
		while (true) {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					

					@Override
					public void run() {
						southP.revalidate();
						if (!songInPause) {
							
								h= controller.splitToComponentTimes(controller.getSongPos())[0];
								min= controller.splitToComponentTimes(controller.getSongPos())[1];
								sec= controller.splitToComponentTimes(controller.getSongPos())[2];
								timerCounter.setText(" "+h+":"+min+":"+sec);
								// System.out.println(controller.getPos());
								
								progressSlider.setValue(controller.getSongPos());
							
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setPaused( boolean value) {
		this.songInPause = value;
	}

	public  void cleanBarData() {
		// TODO Auto-generated method stub

		progressSlider.setValue(0);
		this.setPaused(false);
		
	}

}