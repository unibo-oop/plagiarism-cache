package gymman.customers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gymman.common.DuplicateEntityException;
import gymman.common.MemoryRepository;

/**
 * The Class RegistrationRepositoryImpl represent the repository for registrations.
 */
public final class RegistrationRepositoryImpl extends MemoryRepository<Registration> implements RegistrationRepository {

    /**
     * Instantiates a new registration repository.
     */
    public RegistrationRepositoryImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Registration registration) throws DuplicateEntityException {
        final List<Registration> existing = this.items.stream()
                .filter(e -> e instanceof TermRegistration && e.getIdClient().equals(registration.getIdClient())
                && !e.getId().equals(registration.getId()))
                .map(e -> (TermRegistration) e)
                .collect(Collectors.toList());
        List<Boolean> activeRegistration = Stream.of(false).collect(Collectors.toList());
        if(registration instanceof TermRegistration) {
            final TermRegistration termRegistration = TermRegistration.class.cast(registration);
        	activeRegistration = existing.stream()
                    .filter(e -> e.getType().equals(registration.getType()))
                    .map(e -> e.isActive(termRegistration.getSigningDate()))
                    .collect(Collectors.toList());
        }
        if (!(registration instanceof NumberedRegistration) && activeRegistration.contains(true)) {
            throw new DuplicateEntityException(registration.toString(), existing.toString());
        }
        super.add(registration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Registration> getList() {
        return this.items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Registration> getByIdClient(final String idClient) {
        return this.items.stream()
                .filter(e -> e.getIdClient().equals(idClient)).collect(Collectors.toList());
    }

}
