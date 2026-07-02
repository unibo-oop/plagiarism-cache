package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import utils.TypeItem;
import utils.UserInfo;
import view.ItemScreenImpl.ItemScreenType;
import view.ListScreenImpl.ListScreenType;
import view.UserLoginImpl.LoginType;
import view.UserScreenImpl.UserScreenType;
import controller.Controller;

/**
 * Main class of the view. Contains the implementation of methods which can be
 * used by controller.
 *
 * @author Luca
 *
 */

public class ViewImpl implements View {

	private static final JPanel CONTAINER = new JPanel();
	private static final CardLayout CL = new CardLayout();
	static final int SCREEN_LENGHT = 800;
	static final int SCREEN_WIDTH = 600;
	static final int STUDY_ROOM_SITS = 100;
	static final int FONT_SIZE = 25;
	static final int TITLE_SIZE = 40;
	static final int SMALL_SIZE = 20;

	static final int IMAGE_LENGHT = 140;
	static final int IMAGE_WIDTH = 100;
	private UserShow userScreen;
	private ItemShow itemShow;

	private JPanel card0;
	private JPanel card1;
	private JPanel card2;
	private JPanel card3;
	private JPanel card4;
	private JPanel card5;
	private JPanel card6;
	private JPanel card7;
	private JPanel card8;
	private JPanel card9;
	private JPanel card10;
	private JPanel card11;
	private JPanel card12;
	private JPanel card13;
	private JPanel card14;
	private JPanel card15;
	private JPanel card16;
	private JPanel card17;

	private final JFrame mainFrame = new JFrame();

	/**
	 * enum for List screen type
	 *
	 * @author Luca Giorgetti
	 *
	 */
	private Controller c;

	@Override
	public void setController(final Controller a) {
		this.c = a;
	}

	/**
	 * enum for iteminfo.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum OtherItemFilter {
		RELEASE_NUMBER, COPIES_NUMBER
	}

	/**
	 * enum for card name.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum CardName {
		START("Start Card"), MAIN("Main Card"), LOGIN("Login Card"), MENU(
				"Menu Card"), ITEM("Item Card"), USER_MODIFY("User Modify Card"), LIKE_LIST(
						"LikeList Screen Card"), BORROWED_LIST(
								"BorrowedList Screen Card"), REVIEW("Review Card"), USER_CREATE(
										"User Create Card"), MANAGER_LOGIN("Manager Login"), BOOK_CREATE(
												"Book Create Card"), FILM_CREATE("Film Create Card"), MANAGER_MENU(
														"Manager Menu Card"), STUDY_ROOM("Study Room Card"), WISHLIST(
																"Wishlist Card"), BOOK_MODIFY("Book Modify Card"), FILM_MODIFY(
																		"Film Modify Card"), ALL_REVIEWS("All Reviews Card"), USERS_BORROWED_LIST(
																				"Users Borrowed List Card");

		private final String name;

		/**
		 * @param text
		 */
		CardName(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startView() {

		this.card1 = new UserLoginImpl(this, LoginType.USER);
		this.card2 = new UserMenuImpl(this);
		this.card3 = new MediatecaScreenImpl(this);
		this.card4 = new UserScreenImpl(this, UserScreenType.MODIFY);
		this.card5 = new ListScreenImpl(this, ListScreenType.WISH);
		this.card6 = new ListScreenImpl(this, ListScreenType.BORROWED);
		this.card7 = new ReviewScreenImpl(this);
		this.card8 = new UserScreenImpl(this, UserScreenType.CREATE);
		this.card9 = new UserLoginImpl(this, LoginType.MANAGER);
		this.card10 = new ItemScreenImpl(this, ItemScreenType.CREATE,
				TypeItem.BOOK);
		this.card15 = new ItemScreenImpl(this, ItemScreenType.CREATE,
				TypeItem.MOVIE);
		this.card11 = new view.StudyRoomImpl(this);
		this.card13 = new ItemScreenImpl(this, ItemScreenType.MODIFY,
				TypeItem.BOOK);
		this.card16 = new ItemScreenImpl(this, ItemScreenType.MODIFY,
				TypeItem.MOVIE);
		this.card12 = new ManagerScreenImpl(this);
		this.card14 = new ListScreenImpl(this, ListScreenType.REVIEWS);
		this.card17 = new ListScreenImpl(this, ListScreenType.MANAGER_BORROW);
		this.card0 = new JPanel();

		this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.mainFrame.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.mainFrame.setResizable(false);
		this.mainFrame.setTitle("Mediateca");
		ViewImpl.CONTAINER.setLayout(ViewImpl.CL);
		this.card0 = new JPanel();
		final JLabel welcome = new JLabel("Benvenuto in Mediateca!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.TITLE_SIZE));
		welcome.setBounds(12, 13, 776, 82);
		final JButton login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.FONT_SIZE));
		final JButton userCreate = new JButton("Registrati");
		userCreate.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.FONT_SIZE));
		userCreate.setBounds(435, 164, 281, 138);
		login.setBounds(70, 164, 281, 138);

		this.card0.setLayout(null);
		this.card0.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.card0.add(welcome);
		this.card0.add(login);
		this.card0.add(userCreate);
		this.card1.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

		ViewImpl.CONTAINER.add(this.card0, CardName.MAIN.toString());

		JLabel managerLogin = new JLabel("Accedi come Gestore");
		managerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		managerLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				ViewImpl.this.swapView(CardName.MANAGER_LOGIN);
			}
		});
		managerLogin.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.FONT_SIZE));
		managerLogin.setBounds(17, 484, 334, 64);
		this.card0.add(managerLogin);
		ViewImpl.CONTAINER.add(this.card0, CardName.START.toString());
		ViewImpl.CONTAINER.add(this.card1, CardName.LOGIN.toString());
		ViewImpl.CONTAINER.add(this.card2, CardName.MENU.toString());
		ViewImpl.CONTAINER.add(this.card3, CardName.ITEM.toString());
		ViewImpl.CONTAINER.add(this.card4, CardName.USER_MODIFY.toString());
		ViewImpl.CONTAINER.add(this.card5, CardName.WISHLIST.toString());
		ViewImpl.CONTAINER.add(this.card6, CardName.BORROWED_LIST.toString());
		ViewImpl.CONTAINER.add(this.card7, CardName.REVIEW.toString());
		ViewImpl.CONTAINER.add(this.card8, CardName.USER_CREATE.toString());
		ViewImpl.CONTAINER.add(this.card9, CardName.MANAGER_LOGIN.toString());
		ViewImpl.CONTAINER.add(this.card10, CardName.BOOK_CREATE.toString());
		ViewImpl.CONTAINER.add(this.card15, CardName.FILM_CREATE.toString());
		ViewImpl.CONTAINER.add(this.card11, CardName.STUDY_ROOM.toString());
		ViewImpl.CONTAINER.add(this.card13, CardName.BOOK_MODIFY.toString());
		ViewImpl.CONTAINER.add(this.card16, CardName.FILM_MODIFY.toString());
		ViewImpl.CONTAINER.add(this.card12, CardName.MANAGER_MENU.toString());
		ViewImpl.CONTAINER.add(this.card14, CardName.ALL_REVIEWS.toString());
		ViewImpl.CONTAINER.add(this.card17,
				CardName.USERS_BORROWED_LIST.toString());

		this.swapView(CardName.MAIN);
		login.addActionListener(e -> this.swapView(CardName.LOGIN));
		userCreate.addActionListener(e -> this.swapView(CardName.USER_CREATE));
		this.mainFrame.getContentPane().add(ViewImpl.CONTAINER);
		this.mainFrame.setVisible(true);
	}

	// //OK

	@Override
	public void swapView(final CardName name) {
		ViewImpl.CL.show(ViewImpl.CONTAINER, name.toString());

	}

	// //OK
	@Override
	public String getUsername() {
		return ((UserLogin) this.card1).getUserUsername();
	}

	// //OK
	@Override
	public String getPassword() {
		return ((UserLogin) this.card1).getUserPassword();

	}

	// //OK
	@Override
	public String getSearchFilter() {
		return ((MediatecaScreen) this.card3).getSearchFilter();

	}

	// //OK
	@Override
	public String getItemFilter() {
		return ((MediatecaScreen) this.card3).getItemType();

	}

	// //OK
	@Override
	public String getSearchText() {
		return ((MediatecaScreen) this.card3).getSearchText();

	}

	// //OK
	@Override
	public int getScore() {
		return ((ReviewScreen) this.card7).getSelectedScore();
	}

	// //OK
	@Override
	public String getReview() {
		return ((ReviewScreen) this.card7).getReview();
	}

	// //OK
	@Override
	public void setFilteredList(final String[] filteredList) {
		((MediatecaScreen) this.card3).setFilteredList(filteredList);
	}

	// //OK
	@Override
	public void setBorrowedItemList(final String[] borrowedItemsList) {
		((ListScreen) this.card6).setBorrowedList(borrowedItemsList);
		((ListScreen) this.card17).setBorrowedList(borrowedItemsList);

	}

	// //OK
	@Override
	public void borrowItem() {
		this.c.borrowItem();
	}

	// //OK
	@Override
	public void giveBackItem() {
		this.c.giveBackItemSelectedByUser();
	}

	// //OK
	@Override
	public void likeItem() {
		this.c.addLike();
	}

	// //OK
	@Override
	public String getUserRegistration(final utils.UserInfo info) {
		return ((UserScreen) this.card8).getInfo(info);

	}

	// //OK
	@Override
	public void sendLogin() {
		this.c.userLogin();
	}

	// //OK
	@Override
	public String getManagerPassword() {
		return ((UserLogin) this.card9).getManagerPassword();
	}

	// //OK
	@Override
	public void sendUserModify() {
		this.c.userModify();
	}

	// //OK
	@Override
	public void setUserModifyField(final String name, final String surname,
			final String username, final String password,
			final String birthDate_day, final String birthDate_month,
			final String birthDate_year, final String email,
			final String telephone, final String bPref1, final String bPref2,
			final String bPref3, final String fPref1, final String fPref2,
			final String fPref3) {
		((UserScreen) this.card4).setField(name, surname, username, password,
				birthDate_day, birthDate_month, birthDate_year, email,
				telephone, bPref1, bPref2, bPref3, fPref1, fPref2, fPref3);
	}

	// //OK
	@Override
	public void giveMeUserInfo() {
		this.c.setUserInfo();
	}

	// //OK
	@Override
	public String getModifiedInfo(final UserInfo info) {
		return ((UserScreen) this.card4).getInfo(info);
	}

	// //OK
	@Override
	public void giveMeBorrowList() {
		this.c.borrowList();
	}

	// //OK
	@Override
	public void controllerGetReview() {
		this.c.addReview();

	}

	// //OK
	@Override
	public void sendUserCreate() {
		this.c.registerNewUser();

	}

	// //OK
	@Override
	public void sendManagerLogin() {
		this.c.managerLogin();

	}

	// //OK
	@Override
	public void giveMeFilteredList() {
		this.c.itemElaboration();
	}

	// //OK
	@Override
	public void sendItemCreate(final TypeItem type) {
		this.c.itemCreate(type);

	}

	// //OK
	@Override
	public String getBookCreateInfo(final utils.TypeItemInfo info) {
		return ((ItemScreen) this.card10).getItemInfo(info);
	}

	// //OK
	@Override
	public String getBookModifiedInfo(final utils.TypeItemInfo info) {
		return ((ItemScreen) this.card13).getItemInfo(info);
	}

	// //OK
	@Override
	public String getFilmCreateInfo(final utils.TypeItemInfo info) {
		return ((ItemScreen) this.card15).getItemInfo(info);
	}

	// //OK
	@Override
	public String getFilmModifiedInfo(final utils.TypeItemInfo info) {
		return ((ItemScreen) this.card16).getItemInfo(info);
	}

	// //OK
	@Override
	public String getOtherItemInfo(final view.ViewImpl.OtherItemFilter info2,
			final TypeItem type) {
		if (type == utils.TypeItem.BOOK) {
			return ((ItemScreen) this.card10).getItemInfo(info2);
		} else if (type == utils.TypeItem.MOVIE) {
			return ((ItemScreen) this.card15).getItemInfo(info2);
		}
		return null;

	}

	// //OK
	@Override
	public void setBookField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String isbn,
			final utils.Language language, final int copies, final int release) {

		((ItemScreen) this.card13).setBookField(title, author, manifacturer,
				year, genre, isbn, language, copies, release);
	}

	// //OK
	@Override
	public void setFilmField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String duration,
			final utils.TypeColor color, final utils.Language language,
			final int copies) {
		((ItemScreen) this.card16).setFilmField(title, author, manifacturer,
				year, genre, duration, color, language, copies);
	}

	// //OK
	@Override
	public void giveMeItemInfoMediateca() {
		this.c.setItemInfoMediateca();
	}

	// //OK
	@Override
	public void sendItemModify() {
		this.c.itemModify();
	}

	// //OK
	@Override
	public void showItemInfoManager() {
		this.itemShow = new ItemShowImpl();
		this.giveMeItemShowFromManager();
		this.itemShow.startItemShow(this);
	}

	// //OK
	@Override
	public void giveMeItemShowFromManager() {
		this.c.elementSelectedInManager();

	}

	// //OK
	@Override
	public void showItemInfoMediateca() {
		this.itemShow = new ItemShowImpl();
		this.giveMeItemInfoMediateca();
		this.itemShow.startItemShow(this);
	}

	// //OK
	@Override
	public void goodLogin() {
		this.giveMeSuggestedItems();
		this.swapView(CardName.MENU);
	}

	// //OK
	@Override
	public void goodManagerLogin() {
		this.swapView(CardName.MANAGER_MENU);
	}

	// //OK
	@Override
	public void showError(final String errorMessage) {
		JOptionPane.showMessageDialog(this.mainFrame, errorMessage);
	}

	// //OK
	@Override
	public void showMessage(final String message) {
		JOptionPane.showMessageDialog(this.mainFrame, message);
	}

	// //OK
	@Override
	public void takeSit() {
		this.c.takeSit();
	}

	// //OK
	@Override
	public void giveMeStudyRoomStatus() {
		this.c.setTakenSitsList();
	}

	// //OK
	@Override
	public void setStudyRoomStatus(final int[] status) {

		((StudyRoom) this.card11).setStudyRoomStatus(status);
	}

	// //OK
	@Override
	public int getTakenSits() {
		return ((StudyRoom) this.card11).getTakenSit();
	}

	// //OK
	@Override
	public int getStudyRoomSelectedDay() {
		return ((StudyRoom) this.card11).getDateDay();
	}

	// //OK
	@Override
	public int getStudyRoomSelectedMonth() {
		return ((StudyRoom) this.card11).getDateMonth();
	}

	// //OK
	@Override
	public int getStudyRoomSelectedYear() {
		return ((StudyRoom) this.card11).getDateYear();
	}

	// //OK
	@Override
	public void giveMeSuggestedItems() {
		this.c.suggestedItems();
	}

	// //OK
	@Override
	public void removeFromWishlist() {
		this.c.removeFromWishList();

	}

	// //OK
	@Override
	public String getItemToRemoveFromLikeBorrowWish() {
		return ((ListScreen) this.card5).getSelectedItem();
	}

	// //OK
	@Override
	public void giveMeWishlist() {
		this.c.setWishlist();

	}

	// //OK
	@Override
	public void setWishlist(final String[] list) {
		((ListScreen) this.card5).setWishlist(list);
	}

	// //OK
	@Override
	public void setSuggestedBooks(final String[] bList) {
		((UserMenu) this.card2).setSuggestedBooks(bList);
	}

	// //OK
	@Override
	public void setSuggestedMovies(final String[] mList) {
		((UserMenu) this.card2).setSuggestedMovies(mList);
	}

	// //OK
	@Override
	public void giveMeUserList() {
		this.c.setAllUserList();

	}

	// //OK
	@Override
	public void giveMeItemList() {
		this.c.setAllItemList();

	}

	// //OK
	@Override
	public void deleteUser() {
		this.c.deleteUser();

	}

	// //OK
	@Override
	public void deleteItem() {
		this.c.deleteItem();

	}

	// //OK
	@Override
	public String getUserItemSelectedByManager() {
		return ((ManagerScreen) this.card12).getSelected();
	}

	// //OK
	@Override
	public void showUserInfo() {
		this.userScreen = new UserShowImpl();
		this.giveMeOtherUserInfo();
		this.userScreen.startUserShow(this);
	}

	// //OK
	@Override
	public void showGiveBackOptionMessage(final String book) {

		final Object[] options = { "Consegna",
		"Aumenta il prestito di un altro mese" };

		final JOptionPane optionPane = new JOptionPane(
				"Dovresti consegnare il seguente libro:\n" + book
				+ "\nCosa vuoi fare?\n", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, options);

		final JDialog dialog = new JDialog(this.mainFrame,
				"Notifica di scadenza", true);
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.setLocationRelativeTo(this.mainFrame);
		optionPane.addPropertyChangeListener(e -> {
			String prop = e.getPropertyName();

			if (dialog.isVisible() && (e.getSource() == optionPane)
					&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
				dialog.setVisible(false);
			}
		});
		dialog.pack();
		dialog.setVisible(true);

		Object value = optionPane.getValue();
		if (value.equals(options[0])) {
			this.giveBackItemAfterNotify(book);
		} else if (value.equals(options[1])) {
			this.extendBorrow(book);
		}

	}

	// //OK
	@Override
	public void extendBorrow(final String book) {
		this.c.extendBorrow(book);

	}

	// //OK

	@Override
	public void showGiveBackMessage(final String book) {

		final Object[] options = { "Consegna", "Esegui il logout" };
		final JOptionPane optionPane = new JOptionPane(
				"Devi consegnare il seguente libro:\n" + book
				+ "\nCosa vuoi fare?\n", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, options);

		final JDialog dialog = new JDialog(this.mainFrame,
				"Notifica di scadenza", true);
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.setLocationRelativeTo(this.mainFrame);
		optionPane.addPropertyChangeListener(e -> {
			String prop = e.getPropertyName();

			if (dialog.isVisible() && (e.getSource() == optionPane)
					&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
				dialog.setVisible(false);
			}
		});
		dialog.pack();
		dialog.setVisible(true);

		Object value = optionPane.getValue();
		if (value.equals(options[0])) {
			this.giveBackItemAfterNotify(book);
		} else if (value.equals(options[1])) {
			this.logOut();
			this.swapView(CardName.LOGIN);
		}

	}

	// //OK
	@Override
	public void setUserList(final String[] list) {
		((ManagerScreen) this.card12).setUserList(list);
	}

	// //OK
	@Override
	public void setItemList(final String[] list) {
		((ManagerScreen) this.card12).setItemList(list);
	}

	// //OK
	@Override
	public String getItemSelectedByUser() {
		return ((MediatecaScreen) this.card3).getSelectedItemFromList();
	}

	// //OK
	@Override
	public int getSelectedSit() {
		return ((StudyRoom) this.card11).getTakenSit();
	}

	// //OK
	@Override
	public void giveManagerBorrowList() {
		this.c.otherUserBorrowList();

	}

	// //OK
	@Override
	public void setManagerBorrowList(final String[] borrowedList) {
		((ListScreen) this.card17).setBorrowedList(borrowedList);
	}

	// //OK
	@Override
	public void giveMeAllItemReviews() {
		this.c.allItemReviews();
	}

	// //OK
	@Override
	public void setItemReviewsList(final String[] reviewsList) {
		((ListScreen) this.card14).setReviewslist(reviewsList);
	}

	// //OK
	@Override
	public void giveBackItemAfterNotify(final String item) {
		this.c.giveBackItem(item);
	}

	// //ok
	@Override
	public void cancelSit() {
		this.c.cancelSit();
	}

	// //OK
	@Override
	public void logOut() {
		this.c.logOut();
	}

	// //OK
	@Override
	public void giveMeOtherUserInfo() {
		this.c.giveOtherUserInfo();

	}

	// //OK
	@Override
	public void giveMeItemInfoFromManager() {
		this.c.setItemModifyField();

	}

	// //OK
	@Override
	public void setUserInfoDoubleClick(final String nameS,
			final String surnameS, final String usernameS,
			final String passwordS, final String birthDateS,
			final String emailS, final String telephoneS,
			final String bookPref1S, final String bookPref2S,
			final String bookPref3S, final String filmPref1S,
			final String filmPref2S, final String filmPref3S) {
		this.userScreen.setUserField(nameS, surnameS, usernameS, passwordS,
				birthDateS, emailS, telephoneS, bookPref1S, bookPref2S,
				bookPref3S, filmPref1S, filmPref2S, filmPref3S);
	}

	// //OK
	@Override
	public void setBookInfoDoubleClick(final String titleS,
			final String authorS, final String manifacturerS,
			final String yearS, final String genreS,
			final String reviewAvarageS, final String availabilityS,
			final String isbnS, final String languageS) {
		this.itemShow.setBookField(titleS, authorS, manifacturerS, yearS,
				genreS, reviewAvarageS, availabilityS, isbnS, languageS);
	}

	// //OK
	@Override
	public void setFilmInfoDoubleClick(final String titleS,
			final String authorS, final String manifacturerS,
			final String yearS, final String genreS,
			final String reviewAvarageS, final String availabilityS,
			final String durationS, final String colorS, final String languageS) {
		this.itemShow.setFilmField(titleS, authorS, manifacturerS, yearS,
				genreS, reviewAvarageS, availabilityS, durationS, colorS,
				languageS);
	}

	@Override
	public String getDoubleClickedInManager() {
		return ((ManagerScreen) this.card12).getDClicked();
	}

	@Override
	public String getDoubleClickedItemInMediateca() {
		return ((MediatecaScreen) this.card3).getDClicked();
	}

	@Override
	public boolean itemIsBook(final String item) {
		return this.c.tellMeIfItemIsBook(item);
	}

	@Override
	public int numberOfSits() {
		return this.c.numberOfSits();
	}

}
