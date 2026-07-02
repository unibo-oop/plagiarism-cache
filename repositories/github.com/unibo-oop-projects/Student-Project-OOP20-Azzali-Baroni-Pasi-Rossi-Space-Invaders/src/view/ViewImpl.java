package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.view.ViewObserver;
import model.Entity;
import view.MenuP.MenuState;

/**
 * The Class ViewImpl.
 */
public class ViewImpl extends JFrame implements View{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant SCREEN_WIDTH. */
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 6*5;
	
	/** The Constant SCREEN_HEIGHT. */
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height /6*5;
	
	/** The observer. */
	private ViewObserver observer;
	
	/** The main. */
	private final JPanel main;
	
	/** The card. */
	private final CardLayout card = new CardLayout();
	
	/** The Cards. */
	private final List<String> Cards;
	
	
	/**
	 * Instantiates a new view impl for all the windows.
	 */
	public ViewImpl() {
		super();
		this.setTitle("Space Invaders");
		this.Cards = new ArrayList<>();
		this.main= new JPanel(card);
		this.main.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.add(main);
		this.switchWindow(new MenuP(this, MenuState.Start), MenuP.TITLE);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	/**
	 * Start.
	 */
	@Override
	public void start() {
		this.setVisible(true);
		
	}

	/**
	 * Draw.
	 *
	 * @param list the list
	 * @param score the score
	 * @param level the level
	 */
	@Override
	public void draw(List<Entity> list, int score, int level) {
		final ArenaView arena = (ArenaView) Arrays.stream(this.main.getComponents())
				.filter(e-> e instanceof ArenaView)
				.findFirst()
				.get();
		
		arena.render(list,score, level);
		
	}

	/**
	 * Gets the observer.
	 *
	 * @return the observer
	 */
	@Override
	public ViewObserver getObserver() {
		return this.observer;
	}

	/**
	 * Switch window.
	 *
	 * @param windows the windows
	 * @param Title the title
	 */
	@Override
	public void switchWindow(final JPanel windows, String Title) {
		if(this.Cards.contains(Title) == false) {
			this.Cards.add(Title);
			this.card.addLayoutComponent(windows, Title);
			this.main.add(windows,Title);
		}
		this.card.show(this.main, Title);
	}

	/**
	 * Reset to menu.
	 */
	@Override
	public void resetToMenu() {
		Arrays.stream(this.main.getComponents()).skip(1).forEach(p -> this.main.remove(p));
		this.Cards.removeIf(k -> !k.equals(MenuP.TITLE));
		
	}

	/**
	 * Attach.
	 *
	 * @param observer the observer
	 */
	@Override
	public void attach(ViewObserver observer) {
		this.observer = observer;
		
	}

	

}
