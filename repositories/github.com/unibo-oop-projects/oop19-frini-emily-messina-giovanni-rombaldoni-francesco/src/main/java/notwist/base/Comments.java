package notwist.base;

/*This interface extend "BaseAccount" that represent the base information of every users.*/

import java.util.Optional;

import rombo.discuss.BaseAccount;

public interface Comments extends BaseAccount {
	
	/*This method must return the comment.*/
	public String GetComment();
	
	/*This optional method if possible return the topic where the comments is wrote.*/
	public Optional<String> GetTopic();
	
	/*this optional method if possible return the number of the comments.*/
	public Optional<Integer> GetNumberOfCommet();
}
