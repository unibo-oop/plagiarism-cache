package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.ItemException;
import model.Model;
import model.ModelImpl;
import model.Pair;
import model.UserException;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.user.User;
import model.user.UserImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItem;
import utils.TypeItemInfo;
import utils.UserInfo;
import view.View;
import view.ViewImpl;

/**
 * Class which implements the controller interface.
 *
 * @author
 *
 */
public class ControllerImpl implements Controller {
	private View v;
	private Model m;
	// after the login, the corrispondent user will be saved here
	private UserImpl actualUser;
	private static final int FIRSTDEADLINE = 30;
	private static final int LASTDEADLINE = 60;
	private final FileManager fm = new FileManager();

	/**
	 * Constructor for ControllerImpl.
	 *
	 * @throws Exception
	 *             in the case which singleton already exist.
	 */
	public ControllerImpl() throws Exception {

		final File fileItem = new File(FileManager.PATH + FileManager.FILENAMEITEM);
		final File fileUser = new File(FileManager.PATH + FileManager.FILENAMEUSER);
		final File fileStudyRoom = new File(FileManager.PATH + FileManager.FILENAMESTUDYROOM);

		/*
		 * se i file non esistono, li creo con i dati per effettuare il testing
		 * del programma
		 */
		if (!fileItem.exists() && !fileUser.exists() && !fileStudyRoom.exists()) {
			this.m = new ModelImpl();
			this.writeTestOnFile();
		}

		final Map<Integer, UserImpl> userArchive = this.fm.readArchiveUserFromFile(FileManager.FILENAMEUSER);
		final Map<Integer, Pair<ItemImpl, ItemInfo>> itemArchive = this.fm
				.readArchiveItemFromFile(FileManager.FILENAMEITEM);
		final Map<GregorianCalendar, ArrayList<Integer>> studyRoomArchive = this.fm
				.readStudyRoomFromFile(FileManager.FILENAMESTUDYROOM);
		this.m = new ModelImpl(itemArchive, userArchive, studyRoomArchive);
	}

	/**
	 * Method which creates some users and items for testing.
	 */
	public void writeTestOnFile() {
		final GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 1994);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DAY_OF_MONTH, 6);

		final GregorianCalendar cal2 = new GregorianCalendar();
		cal2.set(Calendar.YEAR, 1995);
		cal2.set(Calendar.MONTH, 5);
		cal2.set(Calendar.DAY_OF_MONTH, 5);

		final GregorianCalendar cal3 = new GregorianCalendar();
		cal3.set(Calendar.YEAR, 1995);
		cal3.set(Calendar.MONTH, 7);
		cal3.set(Calendar.DAY_OF_MONTH, 7);
		try {

			this.m.registerUser("Enrico", "Casanova", cal, "clover", "crocks", "enrico.casanova@gmail.it",
					"334534534534",
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.SCI_FI, ItemGenre.FANTASY, ItemGenre.MUSICAL)));
			this.m.registerUser("Edoardo", "Frati", cal2, "anime", "ironman", "edoardo.frati@gmail.it", "321342111",
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.ANIMATION)),
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.ANIMATION)));
			this.m.registerUser("Luca", "Giorgetti", cal3, "giorgit", "lukegeorge", "luca.giorgetti@gmail.it",
					"321342333",
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT,
							ItemGenre.BIOGRAPHICAL, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT,
							ItemGenre.BIOGRAPHICAL, ItemGenre.HORROR)));
			final User u = new UserImpl("Enrico", "Casanova", cal, "clover", "crocks", "enrico.casanova@gmail.it",
					"334534534534",
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.ADVENTURE_HISTORY, ItemGenre.FANTASY, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.SCI_FI, ItemGenre.FANTASY, ItemGenre.MUSICAL)));

			final User u2 = new UserImpl("Edoardo", "Frati", cal2, "anime", "ironman", "edoardo.frati@gmail.it",
					"321342111",
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.ANIMATION)),
					new ArrayList<ItemGenre>(
							Arrays.asList(ItemGenre.SCI_FI, ItemGenre.ADVENTURE, ItemGenre.ANIMATION)));

			final User u3 = new UserImpl("Luca", "Giorgetti", cal3, "giorgit", "lukegeorge", "luca.giorgetti@gmail.it",
					"321342333",
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT,
							ItemGenre.BIOGRAPHICAL, ItemGenre.HORROR)),
					new ArrayList<ItemGenre>(Arrays.asList(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT,
							ItemGenre.BIOGRAPHICAL, ItemGenre.HORROR)));

			this.m.registerBook("Il signore degli anelli", 1945, "J.R.R. Tolkien", Language.ENGLISH, "23123121",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0011, 3);
			this.m.registerBook("Lo hobbit", 1953, "J.R.R. Tolkien", Language.ENGLISH, "23123100",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0012, 3);
			this.m.registerBook("Il Silmarillion", 1939, "J.R.R. Tolkien", Language.ENGLISH, "23123000",
					ItemGenre.ADVENTURE_HISTORY, "Mondadori", 0013, 3);
			this.m.registerBook("Shining", 1960, "Stephen King", Language.ENGLISH, "23121000", ItemGenre.HORROR,
					"Mondadori", 0017, 1);
			this.m.registerBook("Misery non deve morire", 1966, "Stephen King", Language.ENGLISH, "231210072",
					ItemGenre.HORROR, "Mondadori", 0111, 2);
			this.m.registerBook("La macchina fantasma", 1970, "Stephen Cronenberg", Language.ENGLISH, "231110073",
					ItemGenre.HORROR, "Mondadori", 0110, 3);
			this.m.registerBook("Il vecchio e il mare", 1956, "Ernest Hemingway", Language.ENGLISH, "235210074",
					ItemGenre.FANTASY, "Mondadori", 0101, 2);
			this.m.registerBook("Il vecchio e il mare 2: La vendetta", 1986, "Ernest Hemingway", Language.ENGLISH,
					"335210075", ItemGenre.FANTASY, "Mondadori", 0301, 3);
			this.m.registerBook("IT", 1966, "Stephen King", Language.ENGLISH, "231210076", ItemGenre.HORROR,
					"Mondadori", 0111, 2);
			this.m.registerBook("Il settimo sigillo", 1966, "Stephen King", Language.ENGLISH, "231210077",
					ItemGenre.HORROR, "Mondadori", 0113, 2);
			this.m.registerBook("Cats: il libro", 1988, "Andy Warhol", Language.ENGLISH, "231219977",
					ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Mondadori", 0114, 2);
			this.m.registerBook("Cats: il libro parte seconda", 1989, "Andy Warhol", Language.ENGLISH, "231219977",
					ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Mondadori", 0115, 2);
			this.m.registerBook("Fahrenehit 451", 1965, "Richard Linklater", Language.ENGLISH, "931219977",
					ItemGenre.SCI_FI, "Mondadori", 0116, 2);
			this.m.registerBook("1984", 1948, "George Orwell", Language.ENGLISH, "831219977", ItemGenre.SCI_FI,
					"Mondadori", 0117, 2);
			this.m.registerBook("La tartaruga e la lepre", 1965, "Richard Linklater", Language.ENGLISH, "931219971",
					ItemGenre.ANIMATION, "Mondadori", 0120, 2);
			this.m.registerBook("Ulisse e compagni", 1948, "George Orwell", Language.ENGLISH, "831219971",
					ItemGenre.ANIMATION, "Mondadori", 0121, 2);
			this.m.registerBook("Odissea", 1265, "Omero", Language.ITALIAN, "931219999", ItemGenre.ADVENTURE,
					"Mondadori", 0120, 2);
			this.m.registerBook("Iliade", 1248, "Omero", Language.ITALIAN, "831219999", ItemGenre.ADVENTURE,
					"Mondadori", 0121, 2);

			this.m.registerMovie("Star Trek", 2009, "Bad Robot", "J.J. Abrams", Language.ENGLISH, ItemGenre.SCI_FI, 120,
					TypeColor.COLOR, 1);
			this.m.registerMovie("Star Trek: Into Darkness", 2012, "Bad Robot", "J.J. Abrams", Language.ENGLISH,
					ItemGenre.SCI_FI, 130, TypeColor.COLOR, 3);
			this.m.registerMovie("Star Trek: Beyond", 2015, "Bad Robot", "J.J. Abrams", Language.ENGLISH,
					ItemGenre.SCI_FI, 144, TypeColor.COLOR, 2);
			this.m.registerMovie("Non aprite quella porta", 2006, "Legendary", "John Nispel", Language.ENGLISH,
					ItemGenre.HORROR, 100, TypeColor.COLOR, 1);
			this.m.registerMovie("Saw 80: la fine", 2012, "Medusa film", "Carlo Vanzina", Language.ENGLISH,
					ItemGenre.HORROR, 180, TypeColor.COLOR, 1);
			this.m.registerMovie("Mamma mia!", 2008, "Warner Bros", "Meryl Streep", Language.ENGLISH, ItemGenre.MUSICAL,
					100, TypeColor.COLOR, 3);
			this.m.registerMovie("The Rocky Horror Picture Show", 1977, "01 Distribution", "Tim Curry",
					Language.ENGLISH, ItemGenre.MUSICAL, 120, TypeColor.COLOR, 2);
			this.m.registerMovie("Tarzan", 1966, "Disney Pictures", "Walt Disney", Language.ENGLISH,
					ItemGenre.ANIMATION, 90, TypeColor.COLOR, 1);
			this.m.registerMovie("Aladdin", 1986, "Disney Pictures", "Walt Disney", Language.ENGLISH,
					ItemGenre.ANIMATION, 100, TypeColor.COLOR, 2);
			this.m.registerMovie("Live at the Empire State Building", 1950, "Warner Bros", "Steven Spielberg",
					Language.ENGLISH, ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, 100, TypeColor.BLACK_AND_WHITE, 2);
			this.m.registerMovie("Live at the Empire State Building 2", 1952, "Warner Bros", "Steven Spielberg",
					Language.ENGLISH, ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, 100, TypeColor.BLACK_AND_WHITE, 2);
			this.m.registerMovie("My life Vol.1", 1950, "Warner Bros", "Steven Spielberg", Language.SPANISH,
					ItemGenre.BIOGRAPHICAL, 110, TypeColor.COLOR, 2);
			this.m.registerMovie("My life Vol.2", 1952, "Warner Bros", "Steven Spielberg", Language.SPANISH,
					ItemGenre.BIOGRAPHICAL, 150, TypeColor.COLOR, 2);
			this.m.registerMovie("Indiana Jones", 1950, "Warner Bros", "Steven Spielberg", Language.ENGLISH,
					ItemGenre.ADVENTURE, 105, TypeColor.COLOR, 2);
			this.m.registerMovie("Indiana Jones 2", 1952, "Warner Bros", "Steven Spielberg", Language.ENGLISH,
					ItemGenre.ADVENTURE, 101, TypeColor.COLOR, 2);
			this.m.registerMovie("Un viaggio inaspettato", 2012, "Legendary Pictures", "Peter Jackson",
					Language.ENGLISH, ItemGenre.FANTASY, 185, TypeColor.COLOR, 2);
			this.m.registerMovie("La battaglia delle cinque armte", 2015, "Legendary Pictures", "Peter Jackson",
					Language.ENGLISH, ItemGenre.FANTASY, 175, TypeColor.COLOR, 2);

			/* L'utente clover prende in prestito tutti i libri e film */
			for (final Integer i : this.m.getItemArchive().keySet()) {
				this.m.borrowItem(i, u.getIdUser());
			}

			/* L'utente clover recensisce vari oggetti */
			final Random random = new Random();
			final int n = 5;
			int index = 0;

			for (final Integer i : this.m.getItemArchive().keySet()) {
				final int k = random.nextInt(n);

				this.m.addReview(i, u.getIdUser(), k, "recensione numero " + Integer.toString(index));
				if ((index % 2) == 0) {
					this.m.addLike(i, u.getIdUser());
				}
				index++;
			}

			this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
			this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
			this.fm.writeObjectIntoFile(FileManager.FILENAMESTUDYROOM, this.m);
		} catch (Exception e) {
		}
	}

	@Override
	public void userLogin() {
		final String username = this.v.getUsername();
		final String password = this.v.getPassword();
		final Map<Integer, UserImpl> map = this.m.getUserArchive();
		final java.util.Optional<UserImpl> user = map.entrySet().stream()
				.filter(e -> e.getValue().getUsername().equals(username))
				.filter(e -> e.getValue().getPassword().equals(password)).map(e -> e.getValue()).findFirst();
		if (user.isPresent()) {
			/*
			 * Se username e password sono corrette, salvo l'utente
			 * corrispondente per poterci eseguire le varie operazioni sopra...
			 */
			this.actualUser = user.get();
			try {
				this.m.setReccomandedList(this.actualUser.getIdUser());
			} catch (Exception e) {
				this.v.showError("Lista consigli utente corrente non inizializzata");
			}
			this.v.goodLogin();
			this.elaborateLoans();

		} else {
			/*
			 * ...altrimenti segnalo all'utente attuale che non e' stato trovato
			 */
			this.v.showError("Utente non trovato");
		}
	}

	@Override
	public void logOut() {
		/* Salvo tutto prima di uscire */
		this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
		this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
		this.fm.writeObjectIntoFile(FileManager.FILENAMESTUDYROOM, this.m);
		/* Elimino la corrispondenza dell'utente memorizzata */
		this.actualUser = null;
	}

	@Override
	public void managerLogin() {
		if (this.m.getSystemPassword().equals(this.v.getManagerPassword())) {
			this.v.goodManagerLogin();
		} else {
			this.v.showMessage("Password manager errata");
		}
	}

	@Override
	public void itemElaboration() {
		int index = 0;
		String[] array = null;
		TypeItem ty = null;
		for (final TypeItem y : TypeItem.values()) {
			if (y.toString().equals(this.v.getItemFilter())) {
				ty = y;
			}
		}

		TypeItemInfo ts = null;
		for (final TypeItemInfo s : TypeItemInfo.values()) {
			if (s.toString().equals(this.v.getSearchFilter())) {
				ts = s;
			}
		}

		final String searchText = this.v.getSearchText();
		if (ty == null) {
			/*
			 * non viene scelto filtrato tra libro o film, prendo tutti gli
			 * oggetti, sia libri che film...
			 */
			array = new String[this.m.getItemArchive().size()];
			if (((searchText == null) || searchText.equals("")) && (ts == null)) {
				/* nessun parametro nei filtri, restituisco tutto */
				try {
					for (final Integer i : this.m.getItemArchive().keySet()) {
						array[index] = this.m.getRequiredItem(i).toString();
						index++;
					}
				} catch (ItemException e) {
					this.v.showError(e.getMessage());
				} catch (UserException e1) {
					this.v.showError(e1.getMessage());
				} catch (Exception e2) {
					this.v.showError(e2.getMessage());
				}
			} else {
				/* gli altri 2 parametri ci sono, filtro su tutti gli oggetti */
				try {
					for (final Integer i : this.m.filtersItem(this.m.getItemArchive().keySet(), ts,
							searchText.toUpperCase())) {
						array[index] = this.m.getRequiredItem(i).toString();
						index++;
					}
				} catch (ItemException e) {
					this.v.showError(e.getMessage());
				} catch (UserException e1) {
					this.v.showError(e1.getMessage());
				} catch (Exception e2) {
					this.v.showError(e2.getMessage());
				}
			}

		} else {
			/* ho scelto tra libro o film */
			array = new String[this.m.getAllItemId(ty).size()];
			if (((searchText == null) || searchText.equals("")) && (ts == null)) {
				/*
				 * altri 2 parametri assenti, restituisco o tutti i libri o
				 * tutti i film
				 */
				try {
					for (final Integer i : this.m.getAllItemId(ty)) {
						array[index] = this.m.getRequiredItem(i).toString();
						index++;
					}
				} catch (ItemException e) {
					this.v.showError(e.getMessage());
				} catch (UserException e1) {
					this.v.showError(e1.getMessage());
				} catch (Exception e2) {
					this.v.showError(e2.getMessage());
				}

			} else {
				/* altrimenti filtro in base a tutti i parametri */
				try {
					for (final Integer i : this.m.filtersItem(this.m.getAllItemId(ty), ts, searchText.toUpperCase())) {
						array[index] = this.m.getRequiredItem(i).toString();
						index++;
					}
				} catch (ItemException e) {
					this.v.showError(e.getMessage());
				} catch (UserException e1) {
					this.v.showError(e1.getMessage());
				} catch (Exception e2) {
					this.v.showError(e2.getMessage());
				}
			}
		}
		this.v.setFilteredList(array);
	}

	@Override
	public void addLike() {
		for (final Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getItemSelectedByUser())) {
					this.m.addLike(i, this.actualUser.getIdUser());
					this.v.showMessage("Oggetto " + this.m.getItemArchive().get(i) + " messo in wishlist");
					this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
					this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
	}

	@Override
	public void addReview() {
		for (final Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getItemToRemoveFromLikeBorrowWish())) {
					this.m.addReview(i, this.actualUser.getIdUser(), this.v.getScore(), this.v.getReview());
					this.v.showMessage("Recensione per l'oggetto " + this.m.getRequiredItem(i).toString() + "inserita");
					this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
					this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
	}

	@Override
	public void borrowList() {
		this.borrowListFromUser(this.actualUser);
	}

	/**
	 * Method which takes the list of the items borrowed by the selected user.
	 *
	 * @param user
	 *            selected user.
	 */
	public void borrowListFromUser(final UserImpl user) {
		try {
			String[] array = new String[user.getNowOnLoan().size()];
			int index = 0;
			for (final Integer i : user.getNowOnLoan()) {
				array[index] = this.m.getRequiredItem(i).toString();
				index++;
			}
			this.v.setBorrowedItemList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void borrowItem() {
		for (final Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getItemSelectedByUser())) {
					try {
						this.m.borrowItem(i, this.actualUser.getIdUser());
						this.v.showMessage("Oggetto " + this.m.getRequiredItem(i).toString() + " preso in prestito!");
						this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
						this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
					} catch (ItemException e) {
						this.v.showError(e.getMessage());
					} catch (UserException e1) {
						this.v.showError(e1.getMessage());
					} catch (Exception e2) {
						this.v.showError(e2.getMessage());
					}
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
	}

	@Override
	public void userModify() {
		final GregorianCalendar cal = new GregorianCalendar();
		Arrays.stream(UserInfo.values())
				.filter(ui -> !ui.equals(UserInfo.BIRTHDATE) && !ui.equals(UserInfo.BOOK_PREF1)
						&& !ui.equals(UserInfo.BOOK_PREF2) && !ui.equals(UserInfo.BOOK_PREF3)
						&& !ui.equals(UserInfo.FILM_PREF1) && !ui.equals(UserInfo.FILM_PREF2)
						&& !ui.equals(UserInfo.FILM_PREF3))
				.forEach(ui -> {
					if (ui.equals(UserInfo.BIRTHDATE_DAY)) {
						cal.set(Calendar.DAY_OF_MONTH,
								Integer.parseInt(this.v.getModifiedInfo(UserInfo.BIRTHDATE_DAY)));
					} else if (ui.equals(UserInfo.BIRTHDATE_MONTH)) {
						cal.set(Calendar.MONTH, Integer.parseInt(this.v.getModifiedInfo(UserInfo.BIRTHDATE_MONTH)));
					} else if (ui.equals(UserInfo.BIRTHDATE_YEAR)) {
						cal.set(Calendar.YEAR, Integer.parseInt(this.v.getModifiedInfo(UserInfo.BIRTHDATE_YEAR)));
					}

					if ((this.v.getModifiedInfo(ui) != null) && (!this.v.getModifiedInfo(ui).equals(""))
							&& !ui.equals(UserInfo.BIRTHDATE_DAY) && !ui.equals(UserInfo.BIRTHDATE_MONTH)
							&& !ui.equals(UserInfo.BIRTHDATE_YEAR)) {
						try {
							this.m.changeUser(ui, this.actualUser.getIdUser(), this.v.getModifiedInfo(ui));
						} catch (ItemException e) {
							this.v.showError(e.getMessage());
						} catch (UserException e1) {
							this.v.showError(e1.getMessage());
						} catch (Exception e2) {
							this.v.showError(e2.getMessage());
						}
					}
				});

		try {
			this.m.changeUser(UserInfo.BIRTHDATE, this.actualUser.getIdUser(), cal);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void itemModify() {
		Integer itemId = null;
		for (final Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getUserItemSelectedByManager())) {
					itemId = i;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
		for (final TypeItemInfo ti : TypeItemInfo.values()) {
			try {
				Object x = null;
				switch (ti) {
				case TITLE:
				case AUTHOR:
				case PRODUCER:
					x = (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
							&& (!this.v.getBookModifiedInfo(ti).isEmpty() && !this.v.getBookModifiedInfo(ti).equals(""))
									? this.v.getBookModifiedInfo(ti)
									: (!this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
											&& (!this.v.getFilmModifiedInfo(ti).isEmpty()
													&& !this.v.getFilmModifiedInfo(ti).equals(""))
															? this.v.getFilmModifiedInfo(ti) : null));
					break;
				case RELEASE_YEAR:
					x = (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
							&& (!this.v.getBookModifiedInfo(ti).isEmpty() && !this.v.getBookModifiedInfo(ti).equals(""))
									? Integer.parseInt(this.v.getBookModifiedInfo(ti))
									: (!this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
											&& (!this.v.getFilmModifiedInfo(ti).isEmpty()
													&& !this.v.getFilmModifiedInfo(ti).equals(""))
															? Integer.parseInt(this.v.getFilmModifiedInfo(ti)) : null));
					break;
				case GENRE:
					x = (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
							&& (!this.v.getBookModifiedInfo(ti).isEmpty() && !this.v.getBookModifiedInfo(ti).equals(""))
									? CastManager.castToItemGenre(this.v.getBookModifiedInfo(ti))
									: (!this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
											&& (!this.v.getFilmModifiedInfo(ti).isEmpty()
													&& !this.v.getFilmModifiedInfo(ti).equals(""))
															? CastManager.castToItemGenre(
																	this.v.getFilmModifiedInfo(ti))
															: null));
					break;
				case LANGUAGE:
					x = (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
							&& (!this.v.getBookModifiedInfo(ti).isEmpty() && !this.v.getBookModifiedInfo(ti).equals(""))
									? CastManager.castToLanguage(this.v.getBookModifiedInfo(ti))
									: (!this.m.getAllItemId(TypeItem.BOOK).contains(itemId)
											&& (!this.v.getFilmModifiedInfo(ti).isEmpty()
													&& !this.v.getFilmModifiedInfo(ti).equals(""))
															? CastManager.castToLanguage(this.v.getFilmModifiedInfo(ti))
															: null));
				default:
					break;
				}
				if (x != null) {
					this.m.changeItem(ti, itemId, x);
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
		this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
	}

	@Override
	public void setItemInfoMediateca() {
		this.setSelectedItemInfo(this.v.getDoubleClickedItemInMediateca());
	}

	/**
	 * Method which returns all the infos for the item passed as string.
	 *
	 * @param string
	 *            Item passed as string
	 */
	public void setSelectedItemInfo(final String string) {
		Integer itemId = 0;
		for (final Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(string)) {
					itemId = i;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}

		try {
			final String title = this.m.getRequiredItem(itemId).getTitle();
			final int releaseYear = this.m.getRequiredItem(itemId).getReleaseYear();
			final String author = this.m.getRequiredItem(itemId).getAuthor();
			final Language language = this.m.getRequiredItem(itemId).getCurrentLanguage();
			final ItemGenre genre = this.m.getRequiredItem(itemId).getGenre();
			final String publisher = this.m.getRequiredItem(itemId).getPublisher();
			final Integer numCopy = this.m.getItemArchive().get(itemId).getSecond().getQuantity();

			if (this.m.getAllItemId(TypeItem.BOOK).contains(itemId)) {
				final String isbn = this.m.getRequiredItem(itemId).getIsbn();
				this.v.setBookInfoDoubleClick(title, author, publisher, Integer.toString(releaseYear), genre.toString(),
						Float.toString(this.m.getRequiredItem(itemId).getAverageVote()), Integer.toString(numCopy),
						isbn, language.toString());
			} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(itemId)) {
				final String duration = Integer.toString(this.m.getRequiredItem(itemId).getDuration());
				final TypeColor color = this.m.getRequiredItem(itemId).getColour();
				this.v.setFilmInfoDoubleClick(title, author, publisher, Integer.toString(releaseYear), genre.toString(),
						Float.toString(this.m.getRequiredItem(itemId).getAverageVote()), Integer.toString(numCopy),
						duration, color.toString(), language.toString());
			} else {
				this.v.showError("Item " + Integer.toString(itemId) + " not found in the archive!");
			}
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setUserInfo() {
		this.setSelectedUserInfo(this.actualUser);
	}

	/**
	 * Method which returns all the infos for the user passed.
	 *
	 * @param user
	 *            user which informations are going to be showed
	 */
	public void setSelectedUserInfo(final UserImpl user) {
		if (user == null) {
			this.v.showError("Errore! Utente corrente non ancora inizializzato");
		} else {
			this.v.setUserModifyField(user.getName(), user.getSurname(), user.getUsername(), user.getPassword(),
					String.valueOf(user.getBirthdate().get(Calendar.DAY_OF_MONTH)),
					String.valueOf(user.getBirthdate().get(Calendar.MONTH)),
					String.valueOf(user.getBirthdate().get(Calendar.YEAR)), user.getEmail(), user.getTelephoneNumber(),
					user.getBookPreferences().get(0).toString(), user.getBookPreferences().get(1).toString(),
					user.getBookPreferences().get(2).toString(), user.getMoviePreferences().get(0).toString(),
					user.getMoviePreferences().get(1).toString(), user.getMoviePreferences().get(2).toString());
			try {
				this.m.setReccomandedList(user.getIdUser());
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
	}

	@Override
	public void suggestedItems() {
		String[] arrayBook = new String[this.actualUser.getBookPreferences().size()];
		String[] arrayMovie = new String[this.actualUser.getMoviePreferences().size()];
		int indexBook = 0;
		int indexMovie = 0;
		try {
			for (final Integer i : this.actualUser.getRecommendedList()) {
				if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
					arrayBook[indexBook] = this.m.getRequiredItem(i).toString();
					indexBook++;
				} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
					arrayMovie[indexMovie] = this.m.getRequiredItem(i).toString();
					indexMovie++;
				}
			}
			this.v.setSuggestedBooks(arrayBook);
			this.v.setSuggestedMovies(arrayMovie);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void registerNewUser() {
		final String name = this.v.getUserRegistration(UserInfo.NAME);
		final String surname = this.v.getUserRegistration(UserInfo.SURNAME);
		final GregorianCalendar day = new GregorianCalendar();
		day.set(Integer.parseInt(this.v.getUserRegistration(UserInfo.BIRTHDATE_YEAR)),
				Integer.parseInt(this.v.getUserRegistration(UserInfo.BIRTHDATE_MONTH)),
				Integer.parseInt(this.v.getUserRegistration(UserInfo.BIRTHDATE_DAY)));
		final String username = this.v.getUserRegistration(UserInfo.USERNAME);
		final String password = this.v.getUserRegistration(UserInfo.PASSWORD);
		final String email = this.v.getUserRegistration(UserInfo.EMAIL);
		final String telephoneNumber = this.v.getUserRegistration(UserInfo.TELEPHONE_NUMBER);
		final ItemGenre bookPref1 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.BOOK_PREF1));
		final ItemGenre bookPref2 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.BOOK_PREF2));
		final ItemGenre bookPref3 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.BOOK_PREF3));
		final List<ItemGenre> bookList = new ArrayList<>(Arrays.asList(bookPref1, bookPref2, bookPref3));
		final ItemGenre moviePref1 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.FILM_PREF1));
		final ItemGenre moviePref2 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.FILM_PREF2));
		final ItemGenre moviePref3 = CastManager.castToItemGenre(this.v.getUserRegistration(UserInfo.FILM_PREF3));
		final List<ItemGenre> movieList = new ArrayList<>(Arrays.asList(moviePref1, moviePref2, moviePref3));
		try {
			this.m.registerUser(name, surname, day, username, password, email, telephoneNumber, bookList, movieList);
			this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
			this.v.showMessage("Utente " + username + " registrato con successo!");
		} catch (IOException e4) {
			this.v.showError(e4.getMessage());
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	/**
	 * Method which adds a new Book to the archive.
	 */
	public void registerNewBook() {
		final String title = this.v.getBookCreateInfo(TypeItemInfo.TITLE);
		final int releaseYear = Integer.parseInt(this.v.getBookCreateInfo(TypeItemInfo.RELEASE_YEAR));
		final String author = this.v.getBookCreateInfo(TypeItemInfo.AUTHOR);
		final Language language = CastManager.castToLanguage(this.v.getBookCreateInfo(TypeItemInfo.LANGUAGE));
		final String isbn = this.v.getBookCreateInfo(TypeItemInfo.ISBN);
		final ItemGenre genre = CastManager.castToItemGenre(this.v.getBookCreateInfo(TypeItemInfo.GENRE));
		final String publisher = this.v.getBookCreateInfo(TypeItemInfo.PRODUCER);
		final Integer numRelease = Integer
				.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.RELEASE_NUMBER, TypeItem.BOOK));
		final Integer numCopy = Integer
				.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER, TypeItem.BOOK));

		try {
			this.m.registerBook(title, releaseYear, author, language, isbn, genre, publisher, numRelease, numCopy);
			this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
		} catch (IOException e4) {
			this.v.showError(e4.getMessage());
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	/**
	 * Method which adds a new Movie to the archive.
	 */
	public void registerNewMovie() {
		final String title = this.v.getFilmCreateInfo(TypeItemInfo.TITLE);
		final int releaseYear = Integer.parseInt(this.v.getFilmCreateInfo(TypeItemInfo.RELEASE_YEAR));
		final String publisher = this.v.getFilmCreateInfo(TypeItemInfo.PRODUCER);
		final String author = this.v.getFilmCreateInfo(TypeItemInfo.AUTHOR);
		final Language language = CastManager.castToLanguage(this.v.getFilmCreateInfo(TypeItemInfo.LANGUAGE));
		final ItemGenre genre = CastManager.castToItemGenre(this.v.getFilmCreateInfo(TypeItemInfo.GENRE));
		final Integer duration = Integer.parseInt(this.v.getFilmCreateInfo(TypeItemInfo.DURATION));
		final TypeColor color = CastManager.castToTypeColor((this.v.getFilmCreateInfo(TypeItemInfo.COLOR)));
		final Integer numCopy = Integer
				.parseInt(this.v.getOtherItemInfo(ViewImpl.OtherItemFilter.COPIES_NUMBER, TypeItem.MOVIE));

		try {
			this.m.registerMovie(title, releaseYear, publisher, author, language, genre, duration, color, numCopy);
			this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
		} catch (IOException e4) {
			this.v.showError(e4.getMessage());
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void itemCreate(final TypeItem type) {
		if (type.equals(TypeItem.BOOK)) {
			this.registerNewBook();
		} else if (type.equals(TypeItem.MOVIE)) {
			this.registerNewMovie();
		}
	}

	@Override
	public void elaborateLoans() {
		Map<Integer, Double> map;
		try {
			map = this.m.checkDeadlineas(this.actualUser.getIdUser());
			map.keySet().stream().forEach(i -> {
				if (map.get(i) > ControllerImpl.LASTDEADLINE) {
					try {
						/*
						 * Se l'utente decide di eseguire il logout, non mostro
						 * altri messaggi
						 */
						if (this.actualUser != null) {
							this.v.showGiveBackMessage(this.m.getRequiredItem(i).toString());
						}
					} catch (Exception e) {
						this.v.showError("Errore! Oggetto non presente nell'archivio");
					}
				} else if (map.get(i) > ControllerImpl.FIRSTDEADLINE) {
					try {
						this.v.showGiveBackOptionMessage(this.m.getRequiredItem(i).toString());
					} catch (ItemException e) {
						this.v.showError(e.getMessage());
					} catch (UserException e1) {
						this.v.showError(e1.getMessage());
					} catch (Exception e2) {
						this.v.showError(e2.getMessage());
					}
				}
			});
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void giveBackItem(final String item) {
		try {
			for (final Integer i : this.actualUser.getLoanArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString().equals(item)) {
					this.m.returnItem(i, this.actualUser.getIdUser());
					this.v.showMessage("Oggetto " + this.m.getRequiredItem(i) + " restituito!");
					this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
					this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
					/*
					 * ho gia' trovato l'elemento che mi serve, quindi chiudo il
					 * metodo
					 */
					return;
				}
			}
			this.v.showMessage("Oggetto da restituire non trovato!");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void giveBackItemSelectedByUser() {
		this.giveBackItem(this.v.getItemToRemoveFromLikeBorrowWish());
	}

	@Override
	public void setTakenSitsList() {
		int[] arrayInt = new int[this.m.getStudyRoomSit()];
		for (int i = 0; i < this.m.getStudyRoomSit(); i++) {
			if (this.m
					.getAllUserSit(new GregorianCalendar(this.v.getStudyRoomSelectedYear(),
							this.v.getStudyRoomSelectedMonth(), this.v.getStudyRoomSelectedDay()))
					.get(i).equals(this.actualUser.getIdUser())) {
				arrayInt[i] = 1;
			} else {
				arrayInt[i] = this.m.getAllUserSit(new GregorianCalendar(this.v.getStudyRoomSelectedYear(),
						this.v.getStudyRoomSelectedMonth(), this.v.getStudyRoomSelectedDay())).get(i);
			}
		}
		this.v.setStudyRoomStatus(arrayInt);
	}

	@Override
	public void takeSit() {
		try {
			this.m.bookSit(new GregorianCalendar(this.v.getStudyRoomSelectedYear(), this.v.getStudyRoomSelectedMonth(),
					this.v.getStudyRoomSelectedDay()), this.v.getSelectedSit(), this.actualUser.getIdUser());
			this.fm.writeObjectIntoFile(FileManager.FILENAMESTUDYROOM, this.m);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void cancelSit() {
		try {
			this.m.cancelSit(new GregorianCalendar(this.v.getStudyRoomSelectedYear(),
					this.v.getStudyRoomSelectedMonth(), this.v.getStudyRoomSelectedDay()), this.v.getSelectedSit(),
					this.actualUser.getIdUser());
			this.fm.writeObjectIntoFile(FileManager.FILENAMESTUDYROOM, this.m);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setWishlist() {
		String[] array = new String[this.actualUser.getWishlist().size()];
		int index = 0;
		try {
			for (final Integer i : this.actualUser.getWishlist()) {
				array[index] = this.m.getRequiredItem(i).toString();
				index++;
			}
			this.v.setWishlist(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void removeFromWishList() {
		try {
			for (final Integer i : this.actualUser.getWishlist()) {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getItemToRemoveFromLikeBorrowWish())) {
					this.m.removeLike(i, this.actualUser.getIdUser());
					this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
					this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
				}
			}
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setAllUserList() {
		int index = 0;
		String[] array = new String[this.m.getUserArchive().size()];
		try {
			for (final Integer i : this.m.getUserArchive().keySet()) {
				array[index] = this.m.getRequiredUser(i).toString();
				index++;
			}
			this.v.setUserList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void setAllItemList() {
		int index = 0;
		String[] array = new String[this.m.getItemArchive().size()];
		try {
			for (final Integer i : this.m.getItemArchive().keySet()) {
				array[index] = this.m.getRequiredItem(i).toString();
				index++;
			}
			this.v.setItemList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void deleteItem() {
		int itemIdReceived = 0;
		try {
			for (final Integer i : this.m.getItemArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getUserItemSelectedByManager())) {
					itemIdReceived = i;
				}
			}
			this.m.deleteItem(itemIdReceived);
			this.fm.writeObjectIntoFile(FileManager.FILENAMEITEM, this.m);
			this.v.showMessage("Oggetto " + itemIdReceived + " cancellato");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void deleteUser() {
		try {
			this.m.deleteUser(this.actualUser.getIdUser());
			this.fm.writeObjectIntoFile(FileManager.FILENAMEUSER, this.m);
			this.v.showMessage("Utente " + this.actualUser.getIdUser() + " cancellato");
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void extendBorrow(final String book) {
		this.v.showMessage("Prestito esteso per l'oggetto " + book);
	}

	@Override
	public void allItemReviews() {
		String[] array;
		int id = 0;
		try {
			for (final Integer i : this.m.getItemArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getItemSelectedByUser())) {
					id = i;
					break;
				}
			}
			array = new String[this.m.getAllItemReview(id).size()];
			for (int i = 0; i < this.m.getAllItemReview(id).size(); i++) {
				array[i] = this.m.getAllItemReview(id).get(i).toString();
			}
			this.v.setItemReviewsList(array);
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public void giveOtherUserInfo() {
		UserImpl user = null;
		for (final Integer i : this.m.getUserArchive().keySet()) {
			try {
				if (this.m.getRequiredUser(i).toString().equals(this.v.getDoubleClickedInManager())) {
					user = this.m.getRequiredUser(i);
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
		GregorianCalendar cal = user.getBirthdate();
		String s = cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR);
		this.v.setUserInfoDoubleClick(user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), s,
				user.getEmail(), user.getTelephoneNumber(), user.getBookPreferences().get(0).toString(),
				user.getBookPreferences().get(1).toString(), user.getBookPreferences().get(2).toString(),
				user.getMoviePreferences().get(0).toString(), user.getMoviePreferences().get(1).toString(),
				user.getMoviePreferences().get(2).toString());
	}

	@Override
	public void elementSelectedInManager() {
		for (final Integer i : this.m.getItemArchive().keySet()) {
			try {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getDoubleClickedInManager())) {
					this.setSelectedItemInfo(this.v.getDoubleClickedInManager());
					return;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}

		for (final Integer i : this.m.getUserArchive().keySet()) {
			try {
				if (this.m.getRequiredUser(i).toString().equals(this.v.getDoubleClickedInManager())) {
					this.setSelectedUserInfo(this.m.getRequiredUser(i));
					return;
				}
			} catch (ItemException e) {
				this.v.showError(e.getMessage());
			} catch (UserException e1) {
				this.v.showError(e1.getMessage());
			} catch (Exception e2) {
				this.v.showError(e2.getMessage());
			}
		}
	}

	@Override
	public void otherUserBorrowList() {
		try {
			for (final Integer i : this.m.getUserArchive().keySet()) {
				if (this.m.getRequiredUser(i).toString().equals(this.v.getUserItemSelectedByManager())) {
					final UserImpl user = this.m.getRequiredUser(i);
					this.borrowListFromUser(user);
				}
			}
		} catch (UserException e) {
			this.v.showError(e.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}

	}

	@Override
	public void setView(final view.View v) {
		this.v = v;

	}

	@Override
	public void setItemModifyField() {
		Integer numRelease = 0;
		try {
			for (final Integer i : this.m.getItemArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString().equals(this.v.getUserItemSelectedByManager())) {
					if (this.m.getRequiredItem(i).getNumRelease().isPresent()) {
						numRelease = this.m.getRequiredItem(i).getNumRelease().get();
					}
					if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
						this.v.setBookField(this.m.getRequiredItem(i).getTitle(), this.m.getRequiredItem(i).getAuthor(),
								this.m.getRequiredItem(i).getPublisher(),
								Integer.toString(this.m.getRequiredItem(i).getReleaseYear()),
								this.m.getRequiredItem(i).getGenre(), this.m.getRequiredItem(i).getIsbn(),
								this.m.getRequiredItem(i).getCurrentLanguage(),
								this.m.getItemArchive().get(i).getSecond().getQuantity(), numRelease);
					} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
						this.v.setFilmField(this.m.getRequiredItem(i).getTitle(), this.m.getRequiredItem(i).getAuthor(),
								this.m.getRequiredItem(i).getPublisher(),
								Integer.toString(this.m.getRequiredItem(i).getReleaseYear()),
								this.m.getRequiredItem(i).getGenre(),
								Integer.toString(this.m.getRequiredItem(i).getDuration()),
								this.m.getRequiredItem(i).getColour(), this.m.getRequiredItem(i).getCurrentLanguage(),
								this.m.getItemArchive().get(i).getSecond().getQuantity());
					}
				}
			}
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (UserException e1) {
			this.v.showError(e1.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
	}

	@Override
	public boolean tellMeIfItemIsBook(final String string) {
		try {
			for (final Integer i : this.m.getItemArchive().keySet()) {
				if (this.m.getRequiredItem(i).toString().equals(string)) {
					if (this.m.getAllItemId(TypeItem.BOOK).contains(i)) {
						return true;
					} else if (this.m.getAllItemId(TypeItem.MOVIE).contains(i)) {
						return false;
					}
				}
			}
		} catch (ItemException e) {
			this.v.showError(e.getMessage());
		} catch (Exception e2) {
			this.v.showError(e2.getMessage());
		}
		return false;
	}

	@Override
	public int numberOfSits() {
		return this.m.getStudyRoomSit();
	}
}
