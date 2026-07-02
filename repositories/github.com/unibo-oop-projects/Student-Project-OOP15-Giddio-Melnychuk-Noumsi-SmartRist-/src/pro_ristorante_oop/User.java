package pro_ristorante_oop;

public class User
{
	private String username;
	private String password;

	private Persona persona;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void assignPersona(Persona p)
	{
		this.persona=p;
	}

	public Persona getPersona()
	{
		return persona;
	}

}
