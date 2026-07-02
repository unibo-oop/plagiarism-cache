package notwist.database;

import java.util.List;

public interface DBLikeDislike{

//	public List<Like> getLikes(final Integer idDiscussion);
//	
//	public List<Like> getDislike(final Integer idDiscussion);
	
	public boolean isStillLiked(final Integer idDiscussion, final Integer idUser);
	
	public boolean isStillDisliked(final Integer idDiscussion, final Integer idUser);
	
}
