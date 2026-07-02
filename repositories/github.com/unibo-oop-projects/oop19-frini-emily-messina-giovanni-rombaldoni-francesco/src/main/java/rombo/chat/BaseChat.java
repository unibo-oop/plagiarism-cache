package rombo.chat;

import java.util.Optional;

import rombo.discuss.*;

public interface BaseChat extends BaseAccount {
	
	/*This method must return the Account ID2 of the operations.*/
	public int GetID2();
	
	/*This optional method return the username2 of the account, this will be useful for relieve the other operation.*/
	public Optional<String> GetUserName2();

}
