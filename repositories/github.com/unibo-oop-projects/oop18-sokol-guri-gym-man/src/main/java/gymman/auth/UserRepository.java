package gymman.auth;

import java.util.Optional;

import gymman.common.Repository;

public interface UserRepository extends Repository<User> {
	Optional<User> getByUsername(String username);
	boolean hasUsername(String username);
}
