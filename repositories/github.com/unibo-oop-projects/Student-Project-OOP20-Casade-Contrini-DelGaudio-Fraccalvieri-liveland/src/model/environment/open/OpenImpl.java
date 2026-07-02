package model.environment.open;

import model.person.environment.EnvironmentImpl;
import model.person.ticket.PersonTicket;

public final class OpenImpl implements Open {

    private final Integer numPerson;
    private final EnvironmentImpl environment;

    public OpenImpl(final Integer numPerson, final EnvironmentImpl environment) {
        super();
        this.numPerson = numPerson;
        this.environment = environment;
    }
    /**
     * {@inheritDoc}
     */
    public void firstEntrance() {
        for (int i = 0; i < numPerson; i++) {
            final PersonTicket person = new PersonTicket();
            environment.peopleEntrance(person);
        }
    }
}
