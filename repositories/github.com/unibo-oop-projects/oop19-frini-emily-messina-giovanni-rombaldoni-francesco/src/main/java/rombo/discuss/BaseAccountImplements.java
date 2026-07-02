package rombo.discuss;

import java.util.Optional;

public class BaseAccountImplements implements BaseAccount {

	/*Fields.*/
	private final int ID;
	private Optional<String> UserName = null;
	
	/*Builder.*/
	public BaseAccountImplements(int ID ,Optional<String> username) {
		this.ID = ID;
		this.UserName = UserName;
	}
	
	
	/*Get methods.*/
	
	/*@Return the ID of the user.*/
	public int GetID() {
		return this.ID;
	}

	/*@Retunr a Optional abought the username of the user, if the user is not anonymous return 
	 the username, else return an Optional.Empty*/
	public Optional<String> GetUserName() {
		if(this.UserName != null) {
			return this.UserName;
		}
		else {
			return Optional.empty();
		}
	}

}
