package view;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import view.ViewImpl.CardName;

/**
 * Class which implements the ReviewScreen interface.
 *
 * @author Luca Giorgetti
 *
 */
public class ReviewScreenImpl extends JPanel implements ReviewScreen {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static final int MAX_MARK = 5;
	private final JTextArea review;
	private static int score;
	private JButton discard;
	private JButton send;
	private ButtonGroup scoreGroup = new ButtonGroup();

	/**
	 * Builder for ReviewScreen.
	 *
	 * @param v
	 *            the calling class
	 */
	public ReviewScreenImpl(final View v) {

		final JLabel insertReview = new JLabel(
				"Inserisci qui una recensione per l'oggetto selezionato:");
		insertReview.setBounds(43, 110, 745, 45);
		insertReview
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		final JLabel insertMark = new JLabel("Inserisci qui un voto (da 1 a "
				+ ReviewScreenImpl.MAX_MARK + ") per l'oggetto selezionato:");
		insertMark.setBounds(43, 13, 745, 45);
		insertMark.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.setLayout(null);
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

		this.add(insertReview);
		this.add(insertMark);

		this.scoreGroup = new ButtonGroup();
		JRadioButton score1 = new JRadioButton("1");
		score1.setBounds(37, 56, 58, 45);
		score1.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.scoreGroup.add(score1);
		this.add(score1);

		JRadioButton score2 = new JRadioButton("2");
		score2.setBounds(99, 56, 58, 45);
		this.scoreGroup.add(score2);
		score2.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(score2);

		JRadioButton score3 = new JRadioButton("3");
		score3.setBounds(161, 56, 58, 45);
		score3.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.scoreGroup.add(score3);
		this.add(score3);

		JRadioButton score4 = new JRadioButton("4");
		score4.setBounds(223, 56, 58, 45);
		score4.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.scoreGroup.add(score4);
		this.add(score4);

		JRadioButton score5 = new JRadioButton("5");
		score5.setBounds(285, 56, 58, 45);
		score5.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.scoreGroup.add(score5);
		this.add(score5);

		this.discard = new JButton("Annulla");
		this.discard.setBounds(37, 496, 156, 45);
		this.discard
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(this.discard);

		this.send = new JButton("Invia Recensione");
		this.send.setBounds(492, 496, 275, 45);
		this.send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(this.send);

		this.review = new JTextArea();
		this.review.setBounds(43, 168, 704, 264);
		this.add(this.review);
		this.review.setLineWrap(true);
		this.review.setToolTipText("Inserisci qui una recensione");
		this.review.setColumns(10);
		this.review.setWrapStyleWord(true);
		this.review.setLineWrap(true);
		this.add(this.review);
		this.discard.addActionListener(e -> {
			this.review.setText("");
			v.swapView(CardName.BORROWED_LIST);
		});

		this.send.addActionListener(e -> {
			v.controllerGetReview();
			this.review.setText("");
			v.swapView(CardName.BORROWED_LIST);

		});
	}

	@Override
	public int getSelectedScore() {
		for (Enumeration<AbstractButton> scores = this.scoreGroup.getElements(); scores
				.hasMoreElements();) {
			AbstractButton scoreB = scores.nextElement();

			if (scoreB.isSelected()) {
				ReviewScreenImpl.score = Integer.parseInt(scoreB.getText());
			}
		}
		return ReviewScreenImpl.score;
	}

	@Override
	public String getReview() {
		return this.review.getText();
	}
}
