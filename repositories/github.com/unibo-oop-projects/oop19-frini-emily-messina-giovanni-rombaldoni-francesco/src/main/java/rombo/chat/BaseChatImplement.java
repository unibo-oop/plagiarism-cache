package rombo.chat;

import java.util.Optional;

import rombo.discuss.*;

public class BaseChatImplement extends BaseAccountImplements implements BaseChat {
	
	/*Fields.*/
	private final int ID2;
	private Optional<String> UserName2 = null;
	
	/*Builder.*/
	public BaseChatImplement(int ID, int ID2, Optional<String> UserName, Optional<String> UserName2) {
		super(ID,UserName);
		this.ID2 = ID2;
		this.UserName2 = UserName2;
	}
	
	
	
	

	/*Method that return the ID of the second user.*/
	public int GetID2() {
	
		return this.ID2;
	}

	/*Method that return the User name of the second user.*/
	public Optional<String> GetUserName2() {
		
		if(this.UserName2==null) {
			return Optional.empty();
		}
		else {
			
			return this.UserName2;
		}
	}
}
