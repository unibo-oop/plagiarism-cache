package rombo.discuss;

import java.util.Optional;

import notwist.base.CommentsImplements;

public class SpecificLikeAndDisLikeImplements extends LikeAndDislikeImplements implements SpecificLikeAndDisLike {

	/*Fileds.*/
	private final String Topic;
	private Optional<CommentsImplements> Comment = null;
	
	/*Builder*/
	public SpecificLikeAndDisLikeImplements(String Topic, Optional<CommentsImplements> Comment) {
		super();
		this.Topic = Topic;
		this.Comment = Comment;
	}


	/*Getter methods.*/
	
	/*@Return the topic.*/
	public String GetTopic() {
		return this.Topic;
	}


	/*@Return a Optional about the comment, if the comment is not present return a Oprional.Empty.*/
	public Optional<CommentsImplements> GetComment() {
		
		if(this.Comment != null) {
			return this.Comment;
		}
		else {
			return Optional.empty();
		}
		
	}

}
