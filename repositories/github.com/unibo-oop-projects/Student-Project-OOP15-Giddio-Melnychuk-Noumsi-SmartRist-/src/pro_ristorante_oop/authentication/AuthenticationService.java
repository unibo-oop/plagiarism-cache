package pro_ristorante_oop.authentication;

public interface AuthenticationService
{
	public AuthenticationState authenticate(String username, String password);
}
