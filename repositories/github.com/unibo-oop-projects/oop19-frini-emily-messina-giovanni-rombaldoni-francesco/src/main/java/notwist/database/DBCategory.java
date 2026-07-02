package notwist.database;

import java.util.List;
import java.util.Optional;
import notwist.base.Category;


public interface DBCategory {

		/**
		 * search all the categories in Database
		 * @return	List of all the Category
		 */
		public Optional<List<Category>> getCategories();

		/**
		 * Check if that category still exist
		 * @param name	of the category to check
		 * @return	True if that category exist, false otherwise 
		 */
		public boolean existCategory(String name);
		
		/**
		 * 		
		 * @param name	of the category to search
		 * @return	object instance of Category 
		 */
		public Category getCategoryByName(final String name);
		
		/**
		 * 
		 * @param id	of the category mapped in database
		 * @return	object instance of Category
		 */
		public Category getCategoryById(final Integer id);

		/**
		 * 
		 * @param newCat new name of the category to add in database
		 * @return	True	if the operation is completed successfully, false otherwise
		 */
		public boolean addCategory(final String newCat);
}
