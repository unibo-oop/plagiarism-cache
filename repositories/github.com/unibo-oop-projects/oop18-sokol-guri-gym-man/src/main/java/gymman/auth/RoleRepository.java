package gymman.auth;

import java.util.Optional;

import gymman.common.Repository;

public interface RoleRepository extends Repository<Role> {
	Optional<Role> getByName(String name);
}
