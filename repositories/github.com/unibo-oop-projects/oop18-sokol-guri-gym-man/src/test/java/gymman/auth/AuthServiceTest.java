package gymman.auth;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class AuthServiceTest {
	
	public AuthService auth;
	
	@Before
	public void setUp() throws Exception {
		User user1 = new User.Builder("pippo").withId("123").build();
		user1.setPassword("pass");
		
		User user2 = new User.Builder("pluto").withId("456").build();
		user2.setPassword("pass");
		
		UserRepository userRepo = mock(UserRepository.class);
		when(userRepo.get("123")).thenReturn(Optional.of(user1));
		when(userRepo.hasUsername("pippo")).thenReturn(true);
		when(userRepo.getByUsername("pippo")).thenReturn(Optional.of(user1));
		
		when(userRepo.get("456")).thenReturn(Optional.of(user2));
		when(userRepo.hasUsername("pluto")).thenReturn(true);
		when(userRepo.getByUsername("pluto")).thenReturn(Optional.of(user2));
		
		
		this.auth = new AuthService(userRepo);
	}

	@Test
	public void testCanLogin() throws NotLoggedInException, LoginException {
		this.auth.login("pippo", "pass");
		assertTrue(this.auth.isLoggedIn());
		assertThat(this.auth.getLoggedInUser().getUsername(), is("pippo"));
	}
	
	@Test
	public void testCanLogout() throws LoginException {
		this.auth.login("pippo", "pass");
		assertTrue(this.auth.isLoggedIn());
		
		this.auth.logout();
		assertFalse(this.auth.isLoggedIn());
	}
	
	@Test
	public void testLogoutWhenNotLoggedInDoesNothing() {
		this.auth.logout();
		assertFalse(this.auth.isLoggedIn());
	}
	
	@Test
	public void testLoggedInIsFalseOnStartup() {
		assertFalse(this.auth.isLoggedIn());
	}
	
	@Test
	public void testCanGetLoggedInUser() throws NotLoggedInException, LoginException {
		this.auth.login("pippo", "pass");
		assertTrue(this.auth.getLoggedInUser() instanceof User);
	}
	
	@Test(expected = NotLoggedInException.class)
	public void testErrorWhenGettingUserWithoutLoggingIn() throws NotLoggedInException {
		assertFalse(this.auth.isLoggedIn());
		this.auth.getLoggedInUser();
	}
	
	@Test(expected = LoginException.class)
	public void testErrorWhenLoginWithUnknownUser() throws LoginException {
		this.auth.login("IDontExist", "foobar");
	}
	
	@Test(expected = LoginException.class)
	public void testErrorWhenWrongPassword() throws LoginException {
		this.auth.login("pippo", "wrongpassword");
	}
}
