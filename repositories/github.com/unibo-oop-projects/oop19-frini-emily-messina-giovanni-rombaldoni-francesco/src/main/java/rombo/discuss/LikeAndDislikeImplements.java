package rombo.discuss;

import java.util.*;

public class LikeAndDislikeImplements implements LikeAndDislike {
	
	/*Fields.*/
	final private static int LIKE = 1;
	final private static int DISLIKE = -1;
	
	private int NumberLike;
	private int NumberDisLike;
	
	HashMap<BaseAccountImplements, Integer> User = new HashMap<BaseAccountImplements, Integer>();
	
	/*Builder.*/
	public LikeAndDislikeImplements() {
		this.NumberLike = 0;
		this.NumberDisLike = 0;
	}
	
	
	/*@Input the ID of the user the pouted like.*/
	public void ADDLike(BaseAccountImplements ID) {
		
		/*Control if this is a new user.*/
		if(User.containsKey(ID)) {
			/*Control if the user made other action.*/
			if(User.get(ID) == DISLIKE) {
				this.NumberDisLike--;
				this.NumberLike++;
			}
			else {
				if(User.get(ID) == LIKE) {
					User.remove(ID);
					this.NumberLike--;
				}
			}
		}
		/*If this is a new user*/
		else {
			User.put(ID, LIKE);
			this.NumberLike++;
		}
		

	}

	/*@Return the current number of like*/
	public int GetLikeNumber() {
		return this.NumberLike;
	}

	
	/*@Input the ID of the user the pouted dislike.*/
	public void ADDDislike(BaseAccountImplements ID) {
		
		/*Control if this is a new user.*/
		if(User.containsKey(ID)) {
			/*Control if the user made other action.*/
			if(User.get(ID) == LIKE) {
				this.NumberLike--;
				this.NumberDisLike++;
			}
			else {
				if(User.get(ID) == DISLIKE) {
					this.NumberDisLike--;
					User.remove(ID);
				}
			}
		}
		/*If this is a new user*/
		else {
			User.put(ID, DISLIKE);
			this.NumberDisLike++;
		}

	}

	/*@Return the current number of like*/
	public int GetDislikeNumber() {
		return this.NumberDisLike;
	}

}
