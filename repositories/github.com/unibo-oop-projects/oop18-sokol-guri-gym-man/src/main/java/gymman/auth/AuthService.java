package gymman.auth;

import java.util.Optional;

public class AuthService {
	private UserRepository userRepo;
	
	private Optional<User> user;
	private boolean loggedIn = false;
	
	public AuthService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void login(String username, String password) throws LoginException {
		Optional<User> user = this.userRepo.getByUsername(username);
		if (!user.isPresent()) {
			throw new LoginException(String.format("User '%s' not found", username));
		}
		
		if (!user.get().verifyPassword(password)) {
			throw new LoginException(String.format("Invalid password for user '%s'", username));
		}
		
		this.user = user;
		this.loggedIn = true;
	}
	
	public void logout() {
		this.user = Optional.empty();
		this.loggedIn = false;
	}
	
	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	public User getLoggedInUser() throws NotLoggedInException {
		if (!this.loggedIn) {
			throw new NotLoggedInException();
		}
		return this.user.get();
	}
}
