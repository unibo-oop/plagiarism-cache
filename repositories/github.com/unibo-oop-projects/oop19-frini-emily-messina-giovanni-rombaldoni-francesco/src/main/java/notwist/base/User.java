package notwist.base;

public class User {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private boolean isModerator;
	
	public User(Integer id,String username,String password,String email,boolean isMod) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isModerator = isMod;
	}

	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public boolean isModerator() {
		return isModerator;
	}
	
	public String toString() {
		return this.getUsername() + " (" + this.getId() + ")";
	}
	
	
	
}
