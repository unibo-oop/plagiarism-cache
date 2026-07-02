package it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq; //NOPMD by Manuel Bonarrigo: This is a boundary
//class towards the database. Something had to take that burden.

import java.sql.Connection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;

import it.trashwarecesena.trustalodesktopclient.model.people.Contact;
import it.trashwarecesena.trustalodesktopclient.model.people.ContactCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.Person;
import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.ContactCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.ContactImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.JuridicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PersonCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PhysicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.PeopleDomain;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConcreteFragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq.trustalodb.trustalo.Tables;

/**
 * A PeopleJooqMapper is a container of method implementation directly able to
 * write information over a database using the JOOQ library. This mapper manages
 * the four effective implementation of the CRUD requests upon the following
 * classes:
 * 
 * <ul>
 * <li>{@link Contact}</li>
 * <li>{@link ContactCategory}</li>
 * <li>{@link JuridicalPerson}</li>
 * <li>{@link PersonCategory}</li>
 * <li>{@link PhysicalPerson}</li>
 * <li>{@link TrashwareWorker}</li>
 * <li>{@link TrashwareWorkerCategory}</li>
 * </ul>
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class PeopleJooqMapper extends AbstractJooqMapper implements PeopleDomain {

    private static final String TRASHWARE_WORKER_CATEGORY = "TrashwareWorkerCategory";
    private static final String PERSON_CATEGORY = "PersonCategory";
    private static final String CONTACT_CATEGORY = "ContactCategory";

    /**
     * Constructs a PeopleJooqMapper able to perform its methods over a database
     * represented by the connection provided.
     * 
     * @param connection
     *            a {@link Connection} to a database.
     * @param dialect
     *            a specific feature of JOOQ, which makes the library able to change
     *            the behavior of its queries accordingly to the necessities of the
     *            related database.
     */
    public PeopleJooqMapper(final Connection connection, final SQLDialect dialect) {
        super(connection, dialect);
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof Contact) {
            createEntry((Contact) payload);
        } else if (payload instanceof ContactCategory) {
            createEntry((ContactCategory) payload);
        } else if (payload instanceof TrashwareWorker) {
            createEntry((TrashwareWorker) payload);
        } else if (payload instanceof TrashwareWorkerCategory) {
            createEntry((TrashwareWorkerCategory) payload);
        } else if (payload instanceof JuridicalPerson) {
            createEntry((JuridicalPerson) payload);
        } else if (payload instanceof PersonCategory) {
            createEntry((PersonCategory) payload);
        } else if (payload instanceof PhysicalPerson) {
            createEntry((PhysicalPerson) payload);
        } else {
            throw new IllegalStateException(
                    "No handler available for a create request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        final Class<?> handler = request.getQueryType();
        if (handler.isAssignableFrom(PhysicalPerson.class)) {
            return new ConcreteFragmentedSet(readPeople(request.getQueryObject()), PhysicalPerson.class);
        } else if (handler.isAssignableFrom(JuridicalPerson.class)) {
            return new ConcreteFragmentedSet(readJuridicalPeople(request.getQueryObject()), JuridicalPerson.class);
        } else if (handler.isAssignableFrom(PersonCategory.class)) {
            return new ConcreteFragmentedSet(readJuridicalPersonCategories(request.getQueryObject()),
                    PersonCategory.class);
        } else if (handler.isAssignableFrom(TrashwareWorker.class)) {
            return new ConcreteFragmentedSet(readTrashwareWorkers(request.getQueryObject()), TrashwareWorker.class);
        } else if (handler.isAssignableFrom(TrashwareWorkerCategory.class)) {
            return new ConcreteFragmentedSet(readTrashwareWorkerCategories(request.getQueryObject()),
                    TrashwareWorkerCategory.class);
        } else if (handler.isAssignableFrom(Contact.class)) {
            return new ConcreteFragmentedSet(readContact(request.getQueryObject()), Contact.class);
        } else if (handler.isAssignableFrom(ContactCategory.class)) {
            return new ConcreteFragmentedSet(readContactCategories(request.getQueryObject()), ContactCategory.class);
        } else {
            throw new IllegalStateException("No handler found in " + this.getClass() + " to handle the read request of "
                    + request.getQueryType());
        }

    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        final Object oldValue = biRequest.getPayload();
        final Object newValue = biRequest.getSecondPayload();
        if (oldValue instanceof PhysicalPerson) {
            updateEntry((PhysicalPerson) oldValue, (PhysicalPerson) newValue);
        } else if (oldValue instanceof JuridicalPerson) {
            updateEntry((JuridicalPerson) oldValue, (JuridicalPerson) newValue);
        } else if (oldValue instanceof PersonCategory) {
            updateEntry((PersonCategory) oldValue, (PersonCategory) newValue);
        } else if (oldValue instanceof TrashwareWorker) {
            updateEntry((TrashwareWorker) oldValue, (TrashwareWorker) newValue);
        } else if (oldValue instanceof TrashwareWorkerCategory) {
            updateEntry((TrashwareWorkerCategory) oldValue, (TrashwareWorkerCategory) newValue);
        } else if (oldValue instanceof Contact) {
            updateEntry((Contact) oldValue, (Contact) newValue);
        } else if (oldValue instanceof ContactCategory) {
            updateEntry((ContactCategory) oldValue, (ContactCategory) newValue);
        } else {
            throw new IllegalStateException(
                    "No handler available for an update request containing " + biRequest.getDesiredHandler());
        }
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof Contact) {
            deleteEntry((Contact) payload);
        } else if (payload instanceof ContactCategory) {
            deleteEntry((ContactCategory) payload);
        } else if (payload instanceof TrashwareWorker) {
            deleteEntry((TrashwareWorker) payload);
        } else if (payload instanceof TrashwareWorkerCategory) {
            deleteEntry((TrashwareWorkerCategory) payload);
        } else if (payload instanceof JuridicalPerson) {
            deleteEntry((JuridicalPerson) payload);
        } else if (payload instanceof PersonCategory) {
            deleteEntry((PersonCategory) payload);
        } else if (payload instanceof PhysicalPerson) {
            deleteEntry((PhysicalPerson) payload);
        } else {
            throw new IllegalStateException(
                    "No handler available for a delete request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public void createEntry(final Contact contact) {
        try {
            this.getContext()
                .insertInto(Tables.CONTACTS, 
                            Tables.CONTACTS.CATEGORY, 
                            Tables.CONTACTS.PERSON, 
                            Tables.CONTACTS.VALUE)
                .values(UByte.valueOf(discoverContactCategoryId(contact.getCategory())
                            .orElseThrow(() -> new IllegalArgumentException(CONTACT_CATEGORY))),
                        UInteger.valueOf(assertPersonNumericIdentifierValidity(contact.getOwner())),
                        contact.getValue())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Contact.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(Contact.class, iae.getMessage()));
        }
    }

    @Override
    public void createEntry(final ContactCategory category) {
        try {
            this.getContext()
                .insertInto(Tables.CONTACTCATEGORIES, 
                            Tables.CONTACTCATEGORIES.NAME)
                .values(category.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(ContactCategory.class, dax);

        }
    }

    @Override
    public void createEntry(final JuridicalPerson organization) {
        try {
            this.getContext()
                .insertInto(Tables.PEOPLE, 
                            Tables.PEOPLE.CATEGORY, 
                            Tables.PEOPLE.NAME, 
                            Tables.PEOPLE.FISCALCODE,
                            Tables.PEOPLE.ANNOTATIONS)
                .values(UByte.valueOf(discoverJuridicalPersonCategoryId(organization.getCategory())
                            .orElseThrow(() -> new IllegalArgumentException(PERSON_CATEGORY))),
                        organization.getName(), 
                        organization.getFiscalCode().orElse(null),
                        organization.getAnnotations().orElse(null))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(JuridicalPerson.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(JuridicalPerson.class, iae.getMessage()));
        }
    }

    @Override
    public void createEntry(final PersonCategory category) {
        try {
            this.getContext()
                .insertInto(Tables.PERSONCATEGORIES, 
                            Tables.PERSONCATEGORIES.NAME)
                .values(category.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(PersonCategory.class, dax);
        }
    }

    @Override
    public void createEntry(final PhysicalPerson person) {
        try {
            this.getContext()
                .insertInto(Tables.PEOPLE, 
                            Tables.PEOPLE.CATEGORY, 
                            Tables.PEOPLE.NAME, 
                            Tables.PEOPLE.BIRTHDATE,
                            Tables.PEOPLE.BIRTHLOCATION, 
                            Tables.PEOPLE.FISCALCODE, 
                            Tables.PEOPLE.ANNOTATIONS)
                .values(UByte.valueOf(discoverJuridicalPersonCategoryId(new PersonCategoryImpl("Privato"))
                            .orElseThrow(() -> new IllegalArgumentException(PERSON_CATEGORY))),
                        person.getName(),
                        person.getBirthDate().orElse(null),
                        person.getBirthLocation().orElse(null),
                        person.getFiscalCode().orElse(null),
                        person.getAnnotations().orElse(null))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(PhysicalPerson.class, dax);
        }
    }

    @Override
    public void createEntry(final TrashwareWorker worker) {
        try {
            this.getContext()
                .insertInto(Tables.WORKERS, 
                            Tables.WORKERS.PERSON, 
                            Tables.WORKERS.CATEGORY, 
                            Tables.WORKERS.CURRENTLYWORKING)
                .values(UInteger.valueOf(assertPersonNumericIdentifierValidity(worker.getPerson())),
                        UByte.valueOf(discoverTrashwareWorkerCategoryId(worker.getCategory())
                                .orElseThrow(() -> new IllegalArgumentException(TRASHWARE_WORKER_CATEGORY))),
                        worker.isCurrentlyWorking() ? (byte) 1 : (byte) 0)
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(TrashwareWorker.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(TrashwareWorker.class, iae.getMessage()));
        }
    }

    @Override
    public void createEntry(final TrashwareWorkerCategory category) {
        try {
            this.getContext()
                .insertInto(Tables.WORKERCATEGORIES, 
                            Tables.WORKERCATEGORIES.NAME)
                .values(category.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(TrashwareWorkerCategory.class, dax);
        }
    }

    @Override
    public Set<Contact> readContact(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new ContactImpl(
                    discoverContactCategoryById(((Byte) record.get(0)).intValue()),
                    discoverPersonById(record.getValue(Tables.CONTACTS.PERSON).intValue()),
                    record.getValue(Tables.CONTACTS.VALUE)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<JuridicalPerson> readJuridicalPeople(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .filter(record -> (((Byte) record.get(1)).intValue() != 1))
                .map(record -> new JuridicalPersonImpl
                    .Builder()
                    .identifier(record.getValue(Tables.PEOPLE.ID).intValue())
                    .category(discoverJuridicalPersonCategoryById(((Byte) record.get(1)).intValue()))
                    .name(record.getValue(Tables.PEOPLE.NAME))
                    .fiscalCode(record.getValue(Tables.PEOPLE.FISCALCODE))
                    .annotations(record.getValue(Tables.PEOPLE.ANNOTATIONS))
                    .build())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PhysicalPerson> readPeople(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .filter(record -> (((Byte) record.get(1)).intValue() 
                        == discoverJuridicalPersonCategoryId(new PersonCategoryImpl("Privato"))
                            .orElseThrow(() -> new IllegalStateException())))
                .map(record -> new PhysicalPersonImpl
                    .Builder()
                    .identifier(record.getValue(Tables.PEOPLE.ID).intValue())
                    .name(record.getValue(Tables.PEOPLE.NAME))
                    .fiscalCode(record.getValue(Tables.PEOPLE.FISCALCODE))
                    .annotations(record.getValue(Tables.PEOPLE.ANNOTATIONS))
                    .birthDate(record.getValue(Tables.PEOPLE.BIRTHDATE))
                    .birthLocation(record.getValue(Tables.PEOPLE.BIRTHLOCATION))
                    .build())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TrashwareWorkerCategory> readTrashwareWorkerCategories(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new TrashwareWorkerCategoryImpl(record.getValue(Tables.WORKERCATEGORIES.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TrashwareWorker> readTrashwareWorkers(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new TrashwareWorkerImpl(
                    discoverPhysicalPersonById(record.getValue(Tables.WORKERS.PERSON).intValue()),
                    discoverTrashwareWorkerCategoryById(((Byte) record.get(1)).intValue()),
                    ((Byte) record.get(2)).intValue() > 0))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PersonCategory> readJuridicalPersonCategories(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new PersonCategoryImpl(record.getValue(Tables.PERSONCATEGORIES.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ContactCategory> readContactCategories(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new ContactCategoryImpl(record.getValue(Tables.CONTACTCATEGORIES.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public void updateEntry(final Contact oldContact, final Contact newContact) {
        try {
            getContext().update(Tables.CONTACTS)
               .set(Tables.CONTACTS.CATEGORY, 
                       UByte.valueOf(discoverContactCategoryId(newContact.getCategory())
                               .orElseThrow(() -> new IllegalArgumentException(CONTACT_CATEGORY))))
               .set(Tables.CONTACTS.PERSON, 
                       UInteger.valueOf(newContact.getOwner().getNumericIdentifier()
                               .orElseThrow(() -> new IllegalArgumentException("Owner"))))
               .set(Tables.CONTACTS.VALUE, newContact.getValue())
               .where(Tables.CONTACTS.CATEGORY
                   .eq(UByte.valueOf(discoverContactCategoryId(oldContact.getCategory())
                           .orElseThrow(() -> new IllegalArgumentException(CONTACT_CATEGORY))))
               .and(Tables.CONTACTS.VALUE
                   .eq(oldContact.getValue())))
               .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Contact.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(Contact.class, iae.getMessage()));
        }
    }

    @Override
    public void updateEntry(final ContactCategory oldCategory, final ContactCategory newCategory) {
        try {
            getContext().update(Tables.CONTACTCATEGORIES)
               .set(Tables.CONTACTCATEGORIES.NAME, newCategory.getName())
               .where(Tables.CONTACTCATEGORIES.ID
                   .eq(UByte.valueOf((discoverContactCategoryId(oldCategory)
                       .orElseThrow(() -> new IllegalArgumentException(CONTACT_CATEGORY))
                           .intValue()))))
               .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(ContactCategory.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(ContactCategory.class, iae.getMessage()));
        }
    }

    @Override
    public void updateEntry(final PersonCategory oldCategory, final PersonCategory newCategory) {
        try {
            getContext().update(Tables.PERSONCATEGORIES)
                .set(Tables.PERSONCATEGORIES.NAME, newCategory.getName())
                .where(Tables.PERSONCATEGORIES.ID
                    .eq(UByte.valueOf((discoverJuridicalPersonCategoryId(oldCategory)
                            .orElseThrow(() -> new IllegalArgumentException(PERSON_CATEGORY))
                                .intValue()))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(PersonCategory.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(PersonCategory.class, iae.getMessage()));
        }
    }

    @Override
    public void updateEntry(final JuridicalPerson oldJuridicalPerson, final JuridicalPerson newJuridicalPerson) {
        try {
            getContext().update(Tables.PEOPLE)
               .set(Tables.PEOPLE.NAME, newJuridicalPerson.getName())
               .set(Tables.PEOPLE.CATEGORY,
                       UByte.valueOf(discoverJuridicalPersonCategoryId(newJuridicalPerson.getCategory())
                               .orElseThrow(() -> new IllegalArgumentException(PERSON_CATEGORY))))
               .set(Tables.PEOPLE.FISCALCODE, newJuridicalPerson.getFiscalCode().orElse(null))
               .set(Tables.PEOPLE.ANNOTATIONS, newJuridicalPerson.getAnnotations().orElse(null))
               .where(Tables.PEOPLE.ID
                   .eq(UInteger.valueOf(assertPersonNumericIdentifierValidity(oldJuridicalPerson))))
               .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(JuridicalPerson.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(JuridicalPerson.class, iae.getMessage()));
        }
    }

    @Override
    public void updateEntry(final PhysicalPerson oldPerson, final PhysicalPerson newPerson) {
        try {
            getContext().update(Tables.PEOPLE)
               .set(Tables.PEOPLE.NAME, newPerson.getName())
               .set(Tables.PEOPLE.FISCALCODE, newPerson.getFiscalCode().orElse(null))
               .set(Tables.PEOPLE.ANNOTATIONS, newPerson.getAnnotations().orElse(null))
               .set(Tables.PEOPLE.BIRTHDATE, newPerson.getBirthDate().orElse(null))
               .set(Tables.PEOPLE.BIRTHLOCATION, newPerson.getBirthLocation().orElse(null))
               .where(Tables.PEOPLE.ID
                   .eq(UInteger.valueOf(assertPersonNumericIdentifierValidity(oldPerson))))
               .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(PhysicalPerson.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(PhysicalPerson.class, iae.getMessage()));
        }
    }

    @Override
    public void updateEntry(final TrashwareWorker oldTrashwareWorker, final TrashwareWorker newTrashwareWorker) {
        try {
            getContext().update(Tables.WORKERS)
               .set(Tables.WORKERS.PERSON, 
                       UInteger.valueOf(assertPersonNumericIdentifierValidity(newTrashwareWorker.getPerson())))
               .set(Tables.WORKERS.CATEGORY, 
                       UByte.valueOf(discoverTrashwareWorkerCategoryId(newTrashwareWorker.getCategory())
                               .orElseThrow(() -> new IllegalArgumentException(TRASHWARE_WORKER_CATEGORY))))
               .set(Tables.WORKERS.CURRENTLYWORKING, newTrashwareWorker.isCurrentlyWorking() ? (byte) 1 : (byte) 0)
               .where(Tables.WORKERS.PERSON
                   .eq(UInteger.valueOf(assertPersonNumericIdentifierValidity(oldTrashwareWorker.getPerson()))))
               .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(TrashwareWorker.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(TrashwareWorker.class, iae.getMessage()));
        }
    }

    @Override
    public void updateEntry(final TrashwareWorkerCategory oldCategory, final TrashwareWorkerCategory newCategory) {
        try {
            getContext().update(Tables.WORKERCATEGORIES)
                .set(Tables.WORKERCATEGORIES.NAME, newCategory.getName())
                .where(Tables.WORKERCATEGORIES.ID
                    .eq(UByte.valueOf((discoverTrashwareWorkerCategoryId(oldCategory)
                            .orElseThrow(() -> new IllegalArgumentException(TRASHWARE_WORKER_CATEGORY)).intValue()))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(TrashwareWorkerCategory.class, dax);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(TrashwareWorkerCategory.class, iae.getMessage()));
        }
    }

    @Override
    public void deleteEntry(final Contact contact) {
        try {
            getContext().delete(Tables.CONTACTS)
                .where(Tables.CONTACTS.CATEGORY
                    .eq(UByte.valueOf(discoverContactCategoryId(contact.getCategory())
                            .orElseThrow(() -> new IllegalArgumentException(CONTACT_CATEGORY))))
                .and(Tables.CONTACTS.VALUE
                    .eq(contact.getValue())))
                .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Contact.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(Contact.class, iae.getMessage()));
        } 
    }

    @Override
    public void deleteEntry(final ContactCategory category) {
        try {
            getContext().delete(Tables.CONTACTCATEGORIES)
                   .where(Tables.CONTACTCATEGORIES.ID
                       .eq(UByte.valueOf(discoverContactCategoryId(category)
                               .orElseThrow(() -> new IllegalArgumentException(CONTACT_CATEGORY)).intValue())))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(ContactCategory.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(ContactCategory.class, iae.getMessage()));
        }
    }

    @Override
    public void deleteEntry(final PersonCategory category) {
        try {
            getContext().delete(Tables.PERSONCATEGORIES)
                   .where(Tables.PERSONCATEGORIES.ID
                       .eq(UByte.valueOf(discoverJuridicalPersonCategoryId(category)
                               .orElseThrow(() -> new IllegalArgumentException(PERSON_CATEGORY)).intValue())))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(PersonCategory.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(PersonCategory.class, iae.getMessage()));
        }
    }

    @Override
    public void deleteEntry(final PhysicalPerson person) {
        try {
            getContext().delete(Tables.PEOPLE)
                   .where(Tables.PEOPLE.ID
                       .eq(UInteger.valueOf(assertPersonNumericIdentifierValidity(person))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(PhysicalPerson.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(PhysicalPerson.class, iae.getMessage()));
        }
    }

    @Override
    public void deleteEntry(final JuridicalPerson organization) {
        try {
            getContext().delete(Tables.PEOPLE)
                   .where(Tables.PEOPLE.ID
                       .eq(UInteger.valueOf(assertPersonNumericIdentifierValidity(organization))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(JuridicalPerson.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(JuridicalPerson.class, iae.getMessage()));
        }
    }

    @Override
    public void deleteEntry(final TrashwareWorker worker) {
        try {
            getContext().delete(Tables.WORKERS)
                   .where(Tables.WORKERS.PERSON
                       .eq(UInteger.valueOf(assertPersonNumericIdentifierValidity(worker.getPerson()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(TrashwareWorker.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(TrashwareWorker.class, iae.getMessage()));
        }
    }

    @Override
    public void deleteEntry(final TrashwareWorkerCategory category) {
        try {
            getContext().delete(Tables.WORKERCATEGORIES)
                   .where(Tables.WORKERCATEGORIES.ID
                       .eq(UByte.valueOf((discoverTrashwareWorkerCategoryId(category)
                           .orElseThrow(() -> new IllegalArgumentException(TRASHWARE_WORKER_CATEGORY)).intValue()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(TrashwareWorkerCategory.class);
        } catch (IllegalArgumentException iae) {
            throw new NonExistentReferenceException(
                    ErrorString.buildMissingReferenceExceptionMessage(TrashwareWorkerCategory.class, iae.getMessage()));
        }
    }

    private Integer assertPersonNumericIdentifierValidity(final Person person) {
        if (!(person.equals(discoverPersonById(person.getNumericIdentifier().orElseThrow(
                () -> new IllegalStateException(ErrorString.BUG_REPORTING)))))) {
            throw new IllegalStateException("A corrupted Person has been sent to " + this.getClass().getSimpleName()); 
        }
        return person.getNumericIdentifier().get();
    }

    private Optional<Integer> discoverTrashwareWorkerCategoryId(final TrashwareWorkerCategory category) {
        final Optional<Record> queryResult = this.getContext().select()
                                                         .from(Tables.WORKERCATEGORIES)
                                                         .where(Tables.WORKERCATEGORIES.NAME.eq(category.getName()))
                                                         .fetch()
                                                         .stream()
                                                         .findFirst();
        return queryResult.isPresent() 
                ? Optional.of(queryResult.get().getValue(Tables.WORKERCATEGORIES.ID).intValue())
                : Optional.empty();
    }

    private Optional<Integer> discoverJuridicalPersonCategoryId(final PersonCategory category) {
        final Optional<Record> queryResult = this.getContext().select()
                                                         .from(Tables.PERSONCATEGORIES)
                                                         .where(Tables.PERSONCATEGORIES.NAME.eq(category.getName()))
                                                         .fetch()
                                                         .stream()
                                                         .findFirst();

        return queryResult.isPresent() 
                ? Optional.of(queryResult.get().getValue(Tables.PERSONCATEGORIES.ID).intValue())
                : Optional.empty();
    }

    private Optional<Integer> discoverContactCategoryId(final ContactCategory category) {
        final Optional<Record> queryResult = this.getContext().select()
                                                         .from(Tables.CONTACTCATEGORIES)
                                                         .where(Tables.CONTACTCATEGORIES.NAME.eq(category.getName()))
                                                         .fetch()
                                                         .stream()
                                                         .findFirst();
        return queryResult.isPresent() 
                ? Optional.of(queryResult.get().getValue(Tables.CONTACTCATEGORIES.ID).intValue())
                : Optional.empty();
    }

    private ContactCategory discoverContactCategoryById(final Integer identifier) {
        final Record r = this.getContext().select()
                                     .from(Tables.CONTACTCATEGORIES)
                                     .where(Tables.CONTACTCATEGORIES.ID.eq(UByte.valueOf(identifier)))
                                     .fetch()
                                     .get(0);
        return new ContactCategoryImpl(r.getValue(Tables.CONTACTCATEGORIES.NAME));
    }

    private PhysicalPerson discoverPhysicalPersonById(final Integer identifier) {
        return (PhysicalPerson) discoverPersonById(identifier);
    }

    private Person discoverPersonById(final Integer identifier) {
        final Record r = this.getContext().select()
                                     .from(Tables.PEOPLE)
                                     .where(Tables.PEOPLE.ID.eq(UInteger.valueOf(identifier)))
                                     .fetch()
                                     .get(0);
        return discoverJuridicalPersonCategoryById(
                r.getValue(Tables.PEOPLE.CATEGORY).intValue()).getName().equals("Privato")
                ? new PhysicalPersonImpl.Builder()
                        .identifier(r.getValue(Tables.PEOPLE.ID).intValue())
                        .name(r.getValue(Tables.PEOPLE.NAME))
                        .fiscalCode(r.getValue(Tables.PEOPLE.FISCALCODE))
                        .birthDate(r.getValue(Tables.PEOPLE.BIRTHDATE))
                        .birthLocation(r.getValue(Tables.PEOPLE.BIRTHLOCATION))
                        .annotations(r.getValue(Tables.PEOPLE.ANNOTATIONS))
                        .build()
                : new JuridicalPersonImpl.Builder()
                        .identifier(r.getValue(Tables.PEOPLE.ID).intValue())
                        .category(discoverJuridicalPersonCategoryById(r.getValue(Tables.PEOPLE.CATEGORY).intValue()))
                        .name(r.getValue(Tables.PEOPLE.NAME))
                        .fiscalCode(r.getValue(Tables.PEOPLE.FISCALCODE))
                        .annotations(r.getValue(Tables.PEOPLE.ANNOTATIONS))
                        .build();
    }

    private PersonCategory discoverJuridicalPersonCategoryById(final Integer identifier) {
        final Record r = this.getContext().select()
                                     .from(Tables.PERSONCATEGORIES)
                                     .where(Tables.PERSONCATEGORIES.ID.eq(UByte.valueOf(identifier)))
                                     .fetch()
                                     .get(0);
        return new PersonCategoryImpl(r.getValue(Tables.PERSONCATEGORIES.NAME));
    }

    private TrashwareWorkerCategory discoverTrashwareWorkerCategoryById(final Integer identifier) {
        final Record r = this.getContext().select()
                                      .from(Tables.WORKERCATEGORIES)
                                      .where(Tables.WORKERCATEGORIES.ID.eq(UByte.valueOf(identifier)))
                                      .fetch()
                                      .get(0);
        return new TrashwareWorkerCategoryImpl(r.getValue(Tables.PERSONCATEGORIES.NAME));
    }
}
