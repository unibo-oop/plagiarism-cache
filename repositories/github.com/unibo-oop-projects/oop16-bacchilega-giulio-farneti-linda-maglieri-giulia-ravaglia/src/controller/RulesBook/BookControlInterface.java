package controller.RulesBook;

/**
 * interface for the Rules Book controller
 * @author Alex Ravaglia
 *
 */
public interface BookControlInterface {

    /**
     * go to the next page in the rules book
     */
    void nextPage();

    /**
     * go to the previous page in the rules book
     */
    void previousPage();

    /**
     *set the page to go in the rules book
     * @param p: page to go in the rules book
     */
    void setPage(int p);

    /**
     * reset the actual page to the first page
     */
    void resetPage();

}
