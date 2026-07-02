package gymman.auth;

public class Permission {
	private String name;
	private String description;
	
	public Permission(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Permission)) {
			return false;
		}
		return this.name.equals(((Permission) other).getName());
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
