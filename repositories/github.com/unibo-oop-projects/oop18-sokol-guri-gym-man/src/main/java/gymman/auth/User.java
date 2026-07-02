package gymman.auth;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import gymman.common.BaseEntity;

public class User extends BaseEntity {
	private String username;
	private HashCode password;

	private User() { }

	public String getUsername() {
		return this.username;
	}

	public boolean verifyPassword(String password) {
		return Hashing.sha256().hashString(password, Charsets.UTF_8).equals(this.password);
	}

	public void setPassword(String password) {
		this.password = Hashing.sha256().hashString(password, Charsets.UTF_8);
	}

	@Override
	public String toString() {
		return String.format("User<id='%s' username='%s'>", this.id, this.username);
	}

	public static class Builder {
		private String id;
		private String username;

		public Builder(String username) {
			this.username = username;
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public User build() {
			User user = new User();

			if (this.id != null) {
				user.id = this.id;
			}

			user.username = this.username;
			return user;
		}
	}
}
