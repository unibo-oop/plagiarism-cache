
package view;
import javax.swing.*;
import controller.GameController;
import model.Score;
import model.ScoreImpl;
import resources.ResourcesManagement;
public class StatisticsView extends JPanel{
	private GameController controllerGame;
	public StatisticsView() {
		this.add(new JLabel("switched"));
		/*Score s; //esempio di serializzaizone
		s = new ScoreImpl();
		s.addScorePlayer1();
		s.addScorePlayer2();
		ResourcesManagement.saveScore(s);
		s = new ScoreImpl();
		s.addScorePlayer2();
		s.addScorePlayer2();
		ResourcesManagement.saveScore(s);
	    ResourcesManagement.getScoreHistory().forEach(x -> System.out.println(x));*/
	}
	
	public String getName() {
		return "STATISTICSVIEW";
	}
}

