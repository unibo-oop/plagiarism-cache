package notwist.base;

/**
 * Category (or topic) when there are all the categories that tag various discussion
 * @author gio
 *
 */
public interface Category {

	/**
	 * 
	 * @return id of category
	 */
	public Integer getId();
	
	/**
	 * 
	 * @return name of the category
	 */
	public String getName();
}
