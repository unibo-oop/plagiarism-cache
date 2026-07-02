package rombo.discuss;

public interface LikeAndDislike {
	
	/*Method for add a like to a comment or topic.*/
	public void ADDLike(BaseAccountImplements ID);
	
	/*Method that return the number of likes.*/
	public int GetLikeNumber();
	
	/*Method for add a dislike to a comment or topic.*/
	public void ADDDislike(BaseAccountImplements ID);
	
	/*Method that return the number of likes.*/
	public int GetDislikeNumber();

}
