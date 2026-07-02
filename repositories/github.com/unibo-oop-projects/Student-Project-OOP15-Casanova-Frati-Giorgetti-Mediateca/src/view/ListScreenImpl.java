package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import view.ViewImpl.CardName;

/**
 * Class implementing the interface for ListScreen.
 *
 * @author Luca Giorgetti
 *
 */
public class ListScreenImpl extends JPanel implements ListScreen {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> list = new JList<String>();
	private static String selected;
	JButton removeB = new JButton();
	JButton removeW = new JButton();
	JButton review = new JButton();
	private JScrollPane scroll;

	/**
	 * Enumeration with types of list which can be showed.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum ListScreenType {
		/**
		 *
		 */
		BORROWED, WISH, REVIEWS, MANAGER_BORROW
	}

	/**
	 * builder for ListScreen.
	 *
	 * @param v
	 *            the calling class
	 * @param i
	 *            the type of list to show
	 */
	public ListScreenImpl(final View v, final ListScreenType i) {
		JLabel presentation;
		JButton exit = new JButton();
		JButton exitM = new JButton();

		this.removeB
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.removeB.setBounds(533, 76, 222, 35);
		this.removeB.setText("Consegna");
		this.add(this.removeB);
		this.removeW
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.removeW.setBounds(533, 76, 222, 35);
		this.removeW.setText("Rimuovi");
		this.add(this.removeW);
		presentation = new JLabel();

		presentation.setHorizontalAlignment(SwingConstants.CENTER);
		presentation
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		presentation.setBounds(12, 13, 776, 50);
		this.add(presentation);

		exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exit.setBounds(533, 487, 222, 39);
		this.add(exit);

		exitM = new JButton("Esci");
		exitM.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exitM.setBounds(533, 487, 222, 39);
		this.add(exitM);
		this.review = new JButton("Recensisci");
		this.review.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.review.setBounds(533, 124, 222, 35);
		this.add(this.review);
		this.scroll = new JScrollPane(this.list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scroll.setBounds(42, 76, 460, 450);
		this.add(this.scroll);
		this.list.setModel(this.model);
		if (this.list.isSelectionEmpty()) {
			this.removeB.setEnabled(false);
			this.removeW.setEnabled(false);
			this.review.setEnabled(false);
		}

		switch (i) {
		case BORROWED:
			presentation.setText("Ecco gli oggetti che hai in prestito:");
			this.removeB.setVisible(true);
			this.removeB.setEnabled(false);
			this.removeW.setVisible(false);
			exitM.setVisible(false);
			exit.setVisible(true);
			this.review.setVisible(true);
			this.review.setEnabled(false);
			break;
		case WISH:
			presentation.setText("Ecco gli oggetti che desideri");
			this.removeB.setVisible(false);
			this.removeW.setVisible(true);
			exit.setVisible(true);
			exitM.setVisible(false);
			this.review.setVisible(false);
			break;

		case REVIEWS:
			presentation.setText("Ecco tutte le recensioni");
			this.removeB.setVisible(false);
			this.removeW.setVisible(false);
			exit.setVisible(true);
			exitM.setVisible(false);
			this.review.setVisible(false);
			break;
		case MANAGER_BORROW:
			presentation.setText("Ecco gli oggetti in prestito");
			this.removeB.setVisible(false);
			this.removeW.setVisible(false);
			exit.setVisible(false);
			exitM.setVisible(true);
			this.review.setVisible(false);
		default:
			break;

		}

		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				if ((evt.getClickCount() == 1)
						&& (i == ListScreenType.BORROWED)) {

					if (!ListScreenImpl.this.list.isSelectionEmpty()) {
						ListScreenImpl.this.review.setEnabled(true);
						ListScreenImpl.this.removeB.setEnabled(true);
						ListScreenImpl.selected = ListScreenImpl.this.list
								.getSelectedValue();
					}
					v.swapView(CardName.BORROWED_LIST);
				} else if ((evt.getClickCount() == 1)
						&& (i == ListScreenType.WISH)) {

					if (!ListScreenImpl.this.list.isSelectionEmpty()) {
						ListScreenImpl.this.removeW.setEnabled(true);
						ListScreenImpl.selected = ListScreenImpl.this.list
								.getSelectedValue();
					}
					v.swapView(CardName.WISHLIST);
				}
			}
		});
		exit.addActionListener(e -> {
			v.giveMeFilteredList();
			v.swapView(CardName.ITEM);
		});

		exitM.addActionListener(e -> {
			v.swapView(CardName.MANAGER_MENU);
		});

		this.review.addActionListener(e -> {
			v.swapView(CardName.REVIEW);
		});

		this.removeB.addActionListener(e -> {
			v.giveBackItem();
			v.giveMeBorrowList();
			v.swapView(CardName.BORROWED_LIST);
		});
		this.removeB.setText("Restituisci");

		this.removeW.setText("Rimuovi da Wishlist");
		this.removeW.addActionListener(e -> {
			v.removeFromWishlist();
			v.giveMeWishlist();
			v.swapView(CardName.WISHLIST);
		});

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.setLayout(null);

	}

	@Override
	public void setBorrowedList(final String[] bList) {
		this.model.clear();
		for (String element : bList) {
			this.model.addElement(element);
		}
	}

	@Override
	public void setWishlist(final String[] wishlist) {
		this.model.clear();
		for (String element : wishlist) {
			this.model.addElement(element);
		}
	}

	@Override
	public void setReviewslist(final String[] reviewsList) {
		this.model.clear();
		for (String element : reviewsList) {
			this.model.addElement(element);
		}
	}

	@Override
	public String getSelectedItem() {

		return ListScreenImpl.selected;
	}
}
