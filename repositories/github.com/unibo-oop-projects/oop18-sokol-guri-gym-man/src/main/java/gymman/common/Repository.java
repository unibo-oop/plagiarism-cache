package gymman.common;

import java.util.Optional;

public interface Repository<T extends Entity> {
	void add(T entity) throws DuplicateEntityException;
	void remove(T entity);
	boolean contains(T entity);
	boolean containsId(String id);
	Optional<T> get(String id);
	int getCount();
}
