package rombo.chat;

import java.util.Optional;

import rombo.discuss.BaseAccountImplements;

public class UserImplements extends BaseAccountImplements implements User {

	/*Fields.*/
	private final int Number;
	
	/*Builder.*/
	public UserImplements(int ID, Optional<String> Username, int Number) {
		super(ID,Username);
		this.Number=Number;
	}
	
	/*@Return the number of the message saved.*/
	public int GetNumber() {
		return this.Number;
	}

}
