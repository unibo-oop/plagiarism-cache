package controller;

import java.util.Map;

import model.BookModel;
import model.Model;
import view.BookshopPanel;
import view.MainView;
import view.ReceiptPanelImpl;
import view.observer.BookshopObserver;

/**
 * @author erik_
 *
 */
public class BookshopController implements BookshopObserver {

	private Model model;
	private MainView maninView;
	private BookshopPanel view;
	Map<BookModel, Integer> booksInShop;

	public BookshopController(MainView mainView, Model model) {
		this.maninView = mainView;
		this.model = model;

	}

	public void setView(BookshopPanel bp) {
		this.view = bp;
		this.view.attachObserver(this);
	}

	@Override
	public void shopPurchaseItClicked(Map<BookModel, Integer> purchaseList) {
		ReceiptPanelImpl ri = new ReceiptPanelImpl();
		ReceiptController rc = new ReceiptController(this.maninView, model, purchaseList);
		rc.setView(ri);
		this.maninView.replaceMainPanel(ri);
	}

	@Override
	public Map<BookModel, Integer> getBookInShop(String type, String value) {
		if (value.equals("")) {
			booksInShop = model.shop().getBooks();
		} else {
			booksInShop = model.shop().searchBookByField(type, value);
		}

		return booksInShop;
	}

	@Override
	public Map<BookModel, Integer> searchType(String type, String value) {
		booksInShop = model.shop().searchBookByField(type, value);
		return booksInShop;
	}
}