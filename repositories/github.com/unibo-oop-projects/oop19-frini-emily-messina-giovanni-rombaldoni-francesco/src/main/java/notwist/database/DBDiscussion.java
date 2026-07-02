package notwist.database;

import java.util.List;
import java.util.Optional;

import notwist.base.Category;
import notwist.base.Discussion;
import notwist.base.User;

public interface DBDiscussion {

	/**
	 * Search discussion/s of the user
	 * 
	 * @param idUser identifier of user that create the return discussion
	 * @return Optional empty if user hasn't create a discussion, Optional list of
	 *         discussions otherwise
	 */
	public Optional<List<Discussion>> getDiscussion(final User user);

	/**
	 * Create a new discussion and load on database
	 * @param idUser
	 * @param title
	 * @param description
	 * @param topic
	 * @return
	 */
	public boolean createDiscussion(final Integer idUser, final String title, final String description,final String topic);

	/**
	 * 
	 * @return all the Discussion on database
	 */
	public Optional<List<Discussion>> getAllDiscussion();

	/**
	 * 
	 * @param title title to search in discussion
	 * @return all the discussion filter by that search
	 */
	public Optional<List<Discussion>> getAllDiscussion(final String title);

	/**
	 * 
	 * @param category search discussion by category
	 * @return all the discussion filter by that search
	 * 
	 */
	public Optional<List<Discussion>> getAllDiscussion(final Category category);

	/**
	 * 
	 * @param idDiscussion	of the discussion to search
	 * @return	Optional of Discussion, empty otherwise
	 */
	public Optional<Discussion> getDiscussionFromId(final Integer idDiscussion);

}
