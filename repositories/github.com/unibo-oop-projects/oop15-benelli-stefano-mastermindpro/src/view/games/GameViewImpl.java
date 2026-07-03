package view.games;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;
import controller.data.PersistentException;
import model.games.Game;
import view.images.ImageSetFactory;
import view.interfaces.GameView;
import view.interfaces.ViewBase;
import view.navigator.Navigator;
import view.utils.FrameHelper;
import view.utils.InputFieldFactory;

public class GameViewImpl extends ViewBase<Game> implements GameView {

	/*
	 * this class manages key events: it is used to intercept ctrl+alt+Y which toggles the debug mode
	 */
	private class DebugModeDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        	
        	if(e.isAltDown() && 
			   e.isControlDown() && 
			   e.getKeyCode() == KeyEvent.VK_Y &&
			   e.getID() == KeyEvent.KEY_PRESSED ) {
				//debug mode
				controller.toggleDebugMode();
			}
        	
            return false;
       }
    }
	
	private static final long serialVersionUID = 9171658068315238226L;

	private final Navigator navigator;
	private final EncoderPanel encoderPanel;
	private final JPanel decodersPanel;

	private GameController controller;

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public GameViewImpl(Navigator nav) {

		this.navigator = nav;
		
		FrameHelper.setupWindow(this, new Dimension(600,800));

		this.setTitle("Mastermind PRO");

		JPanel contentPane = new JPanel(new BorderLayout());
		this.setContentPane(contentPane);

		encoderPanel = new EncoderPanel();
		encoderPanel.setPreferredSize(new Dimension(0, 60));
		contentPane.add(encoderPanel, BorderLayout.NORTH);

		decodersPanel = new JPanel();
		contentPane.add(decodersPanel, BorderLayout.CENTER);

		//aggiungo handler per debugmode
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new DebugModeDispatcher());

        //aggiungo handler per conferma chiusura (e salvataggio partita)

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int PromptResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        	    if(PromptResult == JOptionPane.YES_OPTION) {
        	    	try {
						controller.saveGame();
					} catch (PersistentException e) {
						JOptionPane.showMessageDialog(null, "Could not save Game to file", "Error", JOptionPane.ERROR_MESSAGE);
					}

        	    	navigator.home();
        	    }
			}
        });
        
        JComboBox<ImageSetFactory.SetGroup> cbImages = InputFieldFactory.getImageSetsComboBox();
        
        cbImages.setSelectedItem(ImageSetFactory.getCurrentGroup());
        
        cbImages.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cbImages.getSelectedItem() != null)
				{
					controller.changeImageSet(cbImages.getItemAt(cbImages.getSelectedIndex()));
				}
			}
			
		});

        contentPane.add(InputFieldFactory.getLabelTextPanel(new JLabel("Image Set"), cbImages), BorderLayout.SOUTH);
        
		this.pack();
	}

	public void initialize(Game game) {

		//init encoder
		encoderPanel.initialize(game.getEncoder());
		encoderPanel.setController(this.controller);

		//init decoders
		decodersPanel.setLayout(new GridLayout(1, game.getDecoders().size(), 10, 10));
		decodersPanel.removeAll();

		for (int i = 0; i < game.getDecoders().size(); i++) {
			DecoderPanel dPanel = new DecoderPanel(i);
			dPanel.initialize(game.getDecoders().get(i));
			dPanel.setController(this.controller);
			decodersPanel.add(dPanel);
		}

		this.pack();
	}

	@Override
	public void update(Game game) {
		encoderPanel.Update(game.getEncoder());

		for(int i=0; i < game.getDecoders().size(); i++) {
			DecoderPanel dPanel = (DecoderPanel) decodersPanel.getComponent(i);
			dPanel.update(game.getDecoders().get(i));
		}
	}

	@Override
	public void fillModel(Game t) {
		
	}
}
