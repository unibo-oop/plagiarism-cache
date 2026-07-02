package util;

/**
 * Class which manages the application state and transitions.
 * 
 * Each page reflects an application state.
 *
 */
public class ApplicationState {

	public enum PageTransition {
		INITIAL, TO_PAGE_BASE, TO_PAGE_NEW, TO_PAGE_MODIFY, TO_PAGE_DELETE, TO_PAGE_VIEW
	}

	public enum Page {
		INITIAL, PAGE_BASE, PAGE_NEW, PAGE_MODIFY, PAGE_DELETE, PAGE_VIEW
	}

	protected static Page currentPage;

	/**
	 * Gets the value of the application state.
	 * 
	 */
	public static Page getApplicationState() {
		if (currentPage == null) {
			currentPage = Page.INITIAL;
		}
		return currentPage;
	}

	/**
	 * Sets the next application state.
	 * 
	 * @param page
	 */
	public static void setApplicationState(Page page) {
		if (currentPage == null) {
			currentPage = Page.INITIAL;
		}
		currentPage = page;
	}

}
