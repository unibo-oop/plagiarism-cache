package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import utils.TypeItemInfo;
import view.ViewImpl.CardName;

/**
 * Class for ItemScreen.
 *
 * @author Luca Giorgetti
 *
 */
public class MediatecaScreenImpl extends JPanel implements MediatecaScreen {
	private static final long serialVersionUID = 1L;

	private JList<String> filteredJList = new JList<String>();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JComboBox<utils.TypeItemInfo> filterSelect = new JComboBox<TypeItemInfo>();
	private JComboBox<TypeItemInfo> itemSelect = new JComboBox<TypeItemInfo>();
	private JButton reviews;
	private String dClicked;
	private JTextField searchField;
	private JScrollPane scroll;
	private JButton search;

	/**
	 * Builder for MediatecaScreen.
	 *
	 * @param v
	 *            the calling view
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MediatecaScreenImpl(final View v) {
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		final JButton borrowItem;
		final JButton likeItem;
		final JButton seeBorrowedItem;
		final JButton backToMenu;
		likeItem = new JButton("Mi Piace");
		likeItem.setBounds(586, 204, 178, 27);
		likeItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		seeBorrowedItem = new JButton("In prestito");
		seeBorrowedItem.setBounds(586, 284, 178, 27);
		seeBorrowedItem.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		borrowItem = new JButton("Prendi ");
		borrowItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		borrowItem.setBounds(586, 164, 178, 27);
		this.searchField = new JTextField();
		this.searchField.setBounds(21, 84, 521, 27);
		this.filterSelect = new JComboBox(utils.TypeItemInfo.values());
		this.filterSelect.setSelectedIndex(-1);
		this.filterSelect.setBounds(232, 44, 200, 27);
		this.filterSelect.setToolTipText("Dove ricercare");

		this.itemSelect = new JComboBox(utils.TypeItem.values());
		this.itemSelect.setSelectedIndex(-1);
		this.itemSelect.setBounds(21, 44, 200, 27);
		this.itemSelect.setToolTipText("Libro o Film");
		this.add(this.itemSelect);

		this.setLayout(null);
		this.add(this.filterSelect);

		this.search = new JButton("Cerca");
		this.search
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.search.setBounds(586, 84, 178, 27);
		// SEARCH ACTION LISTENER -> REFRESH
		this.search.addActionListener(e -> {
			v.giveMeFilteredList();
			borrowItem.setEnabled(false);
			likeItem.setEnabled(false);
			this.reviews.setEnabled(false);
			v.swapView(CardName.ITEM);
		});

		backToMenu = new JButton("Torna al Menu");
		backToMenu.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		backToMenu.setBounds(586, 495, 178, 27);

		this.reviews = new JButton("Recensioni");
		this.reviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.reviews.setBounds(586, 324, 178, 27);
		this.add(this.reviews);

		this.filteredJList.setModel(this.model);
		this.filteredJList.setBounds(21, 124, 521, 398);
		this.scroll = new JScrollPane(this.filteredJList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scroll.setBounds(21, 164, 521, 358);
		this.add(this.scroll);
		this.filteredJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					MediatecaScreenImpl.this.dClicked = ((JList) evt
							.getSource()).getSelectedValue().toString();
					v.showItemInfoMediateca();
				}
			}
		});

		this.add(borrowItem);
		this.add(likeItem);
		this.add(seeBorrowedItem);
		this.add(this.searchField);
		this.add(this.search);
		this.add(backToMenu);
		borrowItem.setEnabled(false);
		likeItem.setEnabled(false);
		this.reviews.setEnabled(false);

		this.filteredJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					if (!MediatecaScreenImpl.this.filteredJList
							.isSelectionEmpty()) {
						MediatecaScreenImpl.this.reviews.setEnabled(true);
						likeItem.setEnabled(true);
						borrowItem.setEnabled(true);
						v.swapView(CardName.ITEM);
					}
				}
			}
		});
		// BACK ACTION LISTENER -> SUGGESTED LIST
		backToMenu.addActionListener(e -> {
			this.searchField.setText(null);
			borrowItem.setEnabled(false);
			likeItem.setEnabled(false);
			this.reviews.setEnabled(false);
			v.swapView(CardName.MENU);
			v.giveMeSuggestedItems();
		});

		JButton seeWishlist = new JButton("Wishlist");
		seeWishlist.setBounds(586, 244, 178, 27);
		seeWishlist
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		seeWishlist.addActionListener(arg0 -> {
			v.giveMeWishlist();
			v.swapView(CardName.WISHLIST);
		});
		this.add(seeWishlist);

		JLabel pres = new JLabel("Cerca tra libri e film!");
		pres.setHorizontalAlignment(SwingConstants.CENTER);
		pres.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pres.setBounds(42, 13, 722, 27);
		this.add(pres);
		this.reviews.addActionListener(e -> {
			borrowItem.setEnabled(false);
			likeItem.setEnabled(false);
			this.reviews.setEnabled(false);
			v.giveMeAllItemReviews();
			v.swapView(CardName.ALL_REVIEWS);
		});
		borrowItem.addActionListener(e -> {
			v.borrowItem();
		});
		likeItem.addActionListener(e -> {
			v.likeItem();
		});
		seeBorrowedItem.addActionListener(e -> {
			v.giveMeBorrowList();
			borrowItem.setEnabled(false);
			likeItem.setEnabled(false);
			this.reviews.setEnabled(false);
			v.swapView(CardName.BORROWED_LIST);
		});

		seeWishlist.addActionListener(e -> {
			v.giveMeWishlist();
			borrowItem.setEnabled(false);
			likeItem.setEnabled(false);
			this.reviews.setEnabled(false);
			v.swapView(CardName.WISHLIST);
		});
	}

	@Override
	public String getSelectedItemFromList() {
		return this.filteredJList.getSelectedValue().toString();
	}

	@Override
	public String getSearchText() {
		return this.searchField.getText();
	}

	@Override
	public String getSearchFilter() {
		if (this.filterSelect.getSelectedIndex() == -1) {
			return null;
		}
		return this.filterSelect.getSelectedItem().toString();
	}

	@Override
	public String getItemType() {
		if (this.itemSelect.getSelectedIndex() == -1) {
			return null;
		}
		return this.itemSelect.getSelectedItem().toString();
	}

	@Override
	public void setFilteredList(final String[] list) {
		this.model.clear();
		for (String element : list) {
			this.model.addElement(element);
		}
	}

	@Override
	public String getDClicked() {
		return this.dClicked;
	}
}