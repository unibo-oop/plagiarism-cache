package model;

import java.io.Serializable;

public class AccountImpl implements Account, Serializable {

	private static final long serialVersionUID = 1L;
	private final Worker staff;
	private String psw;
	private String user;

	private AccountImpl(final Worker staff, final String user, final String psw) {
		this.user = user;
		this.psw = psw;
		this.staff = staff;
	}

	public void setPsw(final String psw) {
		if (psw.equals("")) {
			throw new IllegalStateException("Complete all fields!");
		} else {
			this.psw = psw;
		}
	}

	public void setUser(final String user) {
		if (user.equals("")) {
			throw new IllegalStateException("Complete all fields!");
		} else {
			this.user = user;
		}
	}

	public String getPsw() {
		return this.psw;
	}

	public String getUser() {
		return this.user;
	}

	public Worker getStaff() {
		return this.staff;
	}

	public static class Builder {
		private Worker bStaff;
		private String bPsw;
		private String bUser;

		public Builder staff(final Worker staff) {
			this.bStaff = staff;
			return this;
		}

		public Builder psw(final String psw) {
			this.bPsw = psw;
			return this;
		}

		public Builder user(final String user) {
			this.bUser = user;
			return this;
		}

		public Account build() {
			if (this.bStaff == null || this.bPsw.equals("") || this.bUser.equals("")) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new AccountImpl(this.bStaff, this.bUser, this.bPsw);
			}
		}
	}



}
