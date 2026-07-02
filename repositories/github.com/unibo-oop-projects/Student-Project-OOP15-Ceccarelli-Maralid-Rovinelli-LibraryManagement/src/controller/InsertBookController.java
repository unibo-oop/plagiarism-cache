package controller;

import model.BookImpl;
import model.BookModel;
import model.Model;
import view.AddBookPanel;
import view.MainView;
import view.WarehousePanelImpl;
import view.observer.AddBookObserver;

/**
 * @author erik_
 *
 */
public class InsertBookController implements AddBookObserver {

	private Model model;
	private MainView mainView;
	private AddBookPanel view;
	private BookModel book;

	public InsertBookController(MainView mainView, Model model) {
		this.mainView = mainView;
		this.model = model;
	}

	public void setView(AddBookPanel ab) {
		this.view = ab;
		this.view.attachObserver(this);
	}

	@Override
	public void addBookClicked(String title, String author, String literaryGenre, int year, double price, int ammount) {
		if (model.warehouse().searchBook(title, author, year) == null) {
			book = new BookImpl(title, author, literaryGenre, year, price);
			model.warehouse().addNewBookInLibrary(book, ammount);
			this.view.displayMessage("Il libro è stato aggiunto");
			this.view.clearView();
		} else {
			this.view.displayMessage("Il libro esiste già");
		}
	}

	@Override
	public void backToWharehouseClicked() {
		WarehousePanelImpl wp = new WarehousePanelImpl();
		WarehouseController wc = new WarehouseController(model);
		wc.setView(wp);
		mainView.replaceMainPanel(wp);
	}
}
