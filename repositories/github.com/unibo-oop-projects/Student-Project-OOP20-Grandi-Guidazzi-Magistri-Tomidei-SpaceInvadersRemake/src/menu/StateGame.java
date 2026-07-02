package menu;

import java.io.IOException;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.entitiesutil.MappedEntity;
import view.GraphicsView;


/**
 *	A class that contains all the object to create the StateGame
 */
public class StateGame implements State{
	
	private GraphicsView graphicsPanel;
	
	/**
	 * The constructor of the StateGame,
	 * this state is showed when the button Start is pressed.
	 * @param board
	 * @param skinUri 
	 */
	public StateGame(Board board, String skinUri) {
		if(skinUri.isBlank()) {
			JOptionPane.showMessageDialog(board.getFrame(), "Can't find the skin!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		else {
			try {
				this.graphicsPanel = new GraphicsView(skinUri, board.getController());
			}
			catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			this.graphicsPanel.setOpaque(false);
			this.graphicsPanel.addKeyListener(board.getController().getView());
			this.graphicsPanel.setFocusable(true);
		}
	}
	/**
	 * The constructor of the StateGame,
	 * this state is showed when the button Start is pressed.
	 * @param board
	 */
	public StateGame(Board board) {
		this(board, board.getPlayerSkin());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.graphicsPanel;
	}
	
	/**
	 * Method that update the screen.
	 */
	public void refresh(Set<MappedEntity> entity) {
		this.graphicsPanel.refreshGui(entity);
	}

}
