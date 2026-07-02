package notwist.database;


import java.util.List;
import java.util.Optional;


import notwist.base.Comments;

public interface DBComments {

	public Optional<List<Comments>> getAllComments(final Integer idDiscussion);
	public void write(final Integer idDiscussion, final Integer idUser, final String comment);

}

