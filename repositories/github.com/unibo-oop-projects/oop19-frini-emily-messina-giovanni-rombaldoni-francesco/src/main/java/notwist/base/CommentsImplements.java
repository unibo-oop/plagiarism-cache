package notwist.base;

import java.util.Optional;

import rombo.discuss.BaseAccountImplements;

public class CommentsImplements extends BaseAccountImplements implements Comments{

	/*Fields.*/
	private final String Comment;
	private Optional<String> Topic = null;
	private Optional<Integer> number = null;
	
	/*Builder.*/
	public CommentsImplements(int ID, Optional<String> UserName , String Comment, Optional<String> Topic , Optional<Integer> NumberOfComment) {
		super(ID,UserName);
		this.Comment = Comment;
		this.Topic = Topic;
		this.number = NumberOfComment;
	}
	

	/*Return methods.*/
	
	/*@Retun the comment.*/
	public String GetComment() {
		
		return this.Comment;
	}

	/*@Return a Optional about the topic, if the topic not exist return Optional.Empty.*/
	public Optional<String> GetTopic() {
		if(this.Topic != null) {
			return this.Topic;
		}
		else {
			return Optional.empty();
		}
	}

	/*@Return a Optional about the number of the comment, if the number of the comment
	 is not counted return Optional.Empty.*/
	public Optional<Integer> GetNumberOfCommet() {
		if(this.number !=null) {
			return this.number;
		}
		else {
		return Optional.empty();
		}
	}
}
