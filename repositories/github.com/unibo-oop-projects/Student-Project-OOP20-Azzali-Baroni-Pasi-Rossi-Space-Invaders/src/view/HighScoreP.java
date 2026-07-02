package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * The Class HighScoreP.
 */
public class HighScoreP extends GameP {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant TITLE. */
	public static final String TITLE = "HighScores";
	
	/** The Jt P. */
	private final JTextPane JtP = new JTextPane();
	
	/** The highscores. */
	private final List<String> highscores;
	
	/**
	 * Instantiates a new high score P.
	 *
	 * @param view the view
	 */
	public HighScoreP(final View view) {
		super(view,HighScoreP.TITLE);
		this.highscores = new ArrayList<>();
		JtP.setEditable(false);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
			final JLabel title = new JLabel("HighScores");
			title.setFont(new Font("Bauhaus 93",Font.BOLD,BUTTON_DIMENSION.height / 3));
			title.setForeground(Color.YELLOW);
			title.setAlignmentX(CENTER_ALIGNMENT);
			this.add(title);
			
		this.add(Box.createVerticalGlue());
		this.add(JtP);
		this.add(Box.createVerticalGlue());
		
		final SimpleAttributeSet att = new SimpleAttributeSet();
		StyleConstants.setAlignment(att, StyleConstants.ALIGN_CENTER);
		JtP.setParagraphAttributes(att, true);
		JtP.setMaximumSize(new Dimension(ViewImpl.SCREEN_WIDTH/3, ViewImpl.SCREEN_HEIGHT/3));
		
		JtP.setOpaque(true);
		this.prepareButton("Back", this).addActionListener(e -> {
			this.getView().switchWindow(this,MenuP.TITLE);
		});
		this.add(Box.createHorizontalGlue());
	}

	/**
	 * Display.
	 */
	@Override
	public void display() {
		this.updateHighScoreP();
		this.getView().switchWindow(this, HighScoreP.TITLE);	
	}
	
	/**
	 * Update high score P.
	 */
	private void updateHighScoreP() {
		this.getView().getObserver().update(this, StateV.HIGHSCORES);
		this.JtP.setText(this.highscores.stream().collect(Collectors.joining("\n")));
	}
	
	/**
	 * Sets the scores.
	 *
	 * @param highscores the new scores
	 */
	public void setScores(final List<String> highscores) {
		this.highscores.clear();
		this.highscores.addAll(highscores);
	}

}
