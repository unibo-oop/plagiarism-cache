package controller.RulesBook;

import view.RulesBook.PageWarning;
import view.RulesBook.RulesBookLoading;

/**
 * BookControl instantiate a Singleton object that control the Rules Book in view
 * @author Alex Ravaglia
 *
 */
public final class BookControl implements BookControlInterface {
	
	private static BookControl bookControl;
	private static final RulesBookLoading RULESBOOK = RulesBookLoading.getLog(); 
	private static final PageWarning PAGE_NOT_FUOND = PageWarning.getLog();
	private static final String FIRST_PAGE = "WARNING :\n This is the first page";
	private static final String END_PAGE = "WARNING :\n This is the last page";
	private static final int MAX_PAGE = 16;
	private static final int MIN_PAGE = 0;
	private int page = MIN_PAGE;
	
	/**
	 * private constructor: only one object is instantiated
	 */
	private BookControl(){}
	
	public void nextPage(){
	    if(this.page < MAX_PAGE){		
	        this.page++;
		RULESBOOK.openChapter(this.page);
	    }else{
	        PAGE_NOT_FUOND.display(FIRST_PAGE);
	        
	    }
	}
	
	public void previousPage(){ 
	    if(this.page > MIN_PAGE){		
		this.page--;
		RULESBOOK.openChapter(this.page);		
	    }else{
	        PAGE_NOT_FUOND.display(END_PAGE);
	    }
	}
	
	public void setPage(final int p){
	    this.page=p;
	    RULESBOOK.openChapter(this.page);	
	}
	
	public void resetPage(){
	    this.page=MIN_PAGE;
	}
	
	/**
	 * get the SINGLETON object of the BookControl
	 * @return the BookControl Object
	 */
	public static synchronized BookControl getLog(){
	    if(bookControl==null){
	        bookControl=new BookControl();
	    }
	    return bookControl;
	}
}
