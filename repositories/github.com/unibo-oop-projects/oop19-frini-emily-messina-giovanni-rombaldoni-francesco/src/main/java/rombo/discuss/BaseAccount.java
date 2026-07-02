package rombo.discuss;

/*This is the base interface  will be used to do the other specific interface and class.*/
import java.util.Optional;

public interface BaseAccount {

	/*This method must return the Account ID of the operations.*/
	public int GetID();
	
	/*This optional method return the username of the account, this will be useful for relieve the other operation.*/
	public Optional<String> GetUserName();
	
}
