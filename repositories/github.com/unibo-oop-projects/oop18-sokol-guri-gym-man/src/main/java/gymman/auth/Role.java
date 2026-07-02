package gymman.auth;

import java.util.HashSet;
import java.util.Set;

import gymman.common.BaseEntity;

public class Role extends BaseEntity {
	private String name;
	private String description;
	private Set<Permission> permissions = new HashSet<Permission>();

	private Role() {
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean hasPermission(Permission permission) {
		return this.permissions.contains(permission);
	}

	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}

	public void removePermission(Permission permission) {
		this.permissions.remove(permission);
	}

	public Set<Permission> getPermissions() {
		return new HashSet<Permission>(this.permissions);
	}

	public int getPermissionsCount() {
		return this.permissions.size();
	}

	public static class Builder {
		private String id;
		private String name;
		private String description = "";

		public Builder(String name) {
			this.name = name;
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Role build() {
			Role role = new Role();

			// avoid overwriting auto-generated id if not custom id
			// has been specified
			if (this.id != null) {
				role.id = this.id;
			}

			role.name = this.name;
			role.description = this.description;
			return role;
		}
	}

}
