package controller;

import java.util.Map;

import model.BookModel;
import model.Model;
import view.WarehousePanel;
import view.observer.WarehouseObserver;

/**
 * @author erik_
 *
 */
public class WarehouseController implements WarehouseObserver {

	private Model model;
	private WarehousePanel view;

	public WarehouseController(Model model) {
		this.model = model;
	}

	public void setView(WarehousePanel wp) {
		this.view = wp;
		this.view.attachObserver(this);
	}

	@Override
	public void addBooksInBookShopClicked(String title, String author, String literaryGenre, int year, double price,
			int amount) {
		if (model.warehouse().getBookQuantity(model.warehouse().searchBook(title, author, year)) == 0) {
			this.view.displayMessage("Quantità esaurita");
		} else {
			if (model.shop().searchBook(title, author, year) == null) {
				model.shop().addNewBookInLibrary(model.warehouse().searchBook(title, author, year), amount);
			} else {
				model.shop().replaceQuantity(model.shop().searchBook(title, author, year),
						model.shop().getBookQuantity(model.shop().searchBook(title, author, year)) + amount);
			}
			model.warehouse().replaceQuantity(model.warehouse().searchBook(title, author, year),
					model.warehouse().getBookQuantity(model.warehouse().searchBook(title, author, year)) - amount);
			this.view.setAllBooks();
			this.view.displayMessage("Libro aggiunto al negozio");
		}

	}

	@Override
	public void addCopyToWarehouse(String title, String author, int year, int amount) {
		model.warehouse().replaceQuantity(model.warehouse().searchBook(title, author, year),
				model.warehouse().getBookQuantity(model.warehouse().searchBook(title, author, year)) + amount);
		this.view.setAllBooks();
	}

	@Override
	public Map<BookModel, Integer> getBooksInWarehouse() {
		Map<BookModel, Integer> booksInWarehouse = model.warehouse().getBooks();
		return booksInWarehouse;
	}

}
