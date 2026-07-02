package rombo.discuss;

import java.util.Optional;

import notwist.base.CommentsImplements;

public interface SpecificLikeAndDisLike extends LikeAndDislike {
	
	/*This method return the topic where is possible put like and dislike.*/
	public String GetTopic();
	
	/*This method permit to return the comments where is possible put like and dislike.*/
	public Optional<CommentsImplements> GetComment();

}
