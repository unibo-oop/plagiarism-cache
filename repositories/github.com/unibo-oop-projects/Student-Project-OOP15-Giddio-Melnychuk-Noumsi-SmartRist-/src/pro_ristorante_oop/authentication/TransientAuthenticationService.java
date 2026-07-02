package pro_ristorante_oop.authentication;

import pro_ristorante_oop.User;
import pro_ristorante_oop.persistence.PersistenceService;

public class TransientAuthenticationService implements AuthenticationService
{

	private PersistenceService persistenceService;
	
	public TransientAuthenticationService(PersistenceService persistenceService)
	{
		this.persistenceService = persistenceService;
	}
	
	@Override
	public AuthenticationState authenticate(String username, String password)
	{
		User user = (User)this.persistenceService.readUser(new String[]{username}).get(0);
		if (user.getPassword().equals(password))
		{
			return AuthenticationState.SUCCESS; 
		}
		return AuthenticationState.WRONG_PASSWORD;
	}

}
