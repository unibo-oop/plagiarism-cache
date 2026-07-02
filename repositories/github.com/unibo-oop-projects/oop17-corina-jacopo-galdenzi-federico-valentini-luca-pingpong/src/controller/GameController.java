package controller;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import model.GameModel;
import model.TimerObserver;
import resources.ResourcesManagement;
import utilities.Utility;
import view.Application;


public class GameController implements TimerObserver{
	private Application theView;
	private GameModel theModel;
	
	public GameController(Application theView) {
		this.theView = theView;
		this.theModel = new GameModel(this);
		theView.getStartView().addPlayButtonListener(e ->{
			ResourcesManagement.playSound(ResourcesManagement.getMenuButtonSoundPath());
			goToPanel(theView.getStartView().getName(),theView.getGameView().getName());
		    theModel.resetScore();
		    theModel.startMatch();
			theView.getGameView().setScore(theModel.getScore1(),theModel.getScore2());
		});
		theView.getStartView().addStatisticsButtonListener(e ->{
			ResourcesManagement.playSound(ResourcesManagement.getMenuButtonSoundPath());
		    goToPanel(theView.getStartView().getName(),theView.getStatisticsView().getName());
		});
	}
	
	private void goToPanel(String oldPanel,String newPanel) {
		CardLayout cl = (CardLayout)(theView.getMainPane().getLayout());
		theModel.savePreviousPanelName(oldPanel);
		checkBarAndUpdateIt(newPanel);
	    cl.show(theView.getMainPane(),newPanel);
	}
	private void goToPanel(String newPanel) {
		CardLayout cl = (CardLayout)(theView.getMainPane().getLayout());
		checkBarAndUpdateIt(newPanel);
	    cl.show(theView.getMainPane(),newPanel);
	}
	private void checkBarAndUpdateIt(String newPanel) {
		if(newPanel.equals(theView.getStartView().getName())) {
			theView.getMenuBar().setVisible(false);
		}
		else {	
				JButton b = (JButton)theView.getMenuBar().getComponent(0);
				 for( ActionListener al : b.getActionListeners() ) {
				       b.removeActionListener( al );
				    }
				b.addActionListener(e ->{
					String s = 	theModel.getPreviousPanelName();
					System.out.println("il precedente e' :"+s);
					this.goToPanel(s);
				});	
				theView.getMenuBar().setVisible(true);;
			
			
		}

	}
	private void movePlayer(KeyEvent event) {
		if(event.getKeyChar() == KeyEvent.VK_UP) {	//UP
			
		}else if(event.getKeyChar() == KeyEvent.VK_DOWN) {	//DOWN
			
		}
	}

	@Override
	public void update(long remainingTime) {
		theView.getGameView().showRemainingTime((int)(remainingTime/Utility.MSEC_CONST));
	}

}