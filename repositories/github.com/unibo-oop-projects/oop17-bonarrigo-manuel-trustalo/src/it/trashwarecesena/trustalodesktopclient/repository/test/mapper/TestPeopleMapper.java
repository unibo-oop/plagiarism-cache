package it.trashwarecesena.trustalodesktopclient.repository.test.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.ContactImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.JuridicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PersonCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PhysicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.people.Contact;
import it.trashwarecesena.trustalodesktopclient.model.people.ContactCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.Persistence;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.test.Persistences;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * A test class for the people mapper boundary.
 * <p>
 * The scheme of every testing method is similar, even if it can not made
 * abstract due to the differences between every mapped entity.
 * 
 * <ul>
 * <li>The references needed by the tested entity are created upon the
 * persistence storage</li>
 * <li>The set retrieved by reading all the tested entity is asserted as
 * empty</li>
 * <li>Two instances known to be different are created</li>
 * <li>The set retrieved by reading all the tested entity is asserted as
 * containing two elements, referred from now on as <i>normal</i> and
 * <i>different</i></li>
 * <li>The equality of the two elements in the set is asserted to the two
 * references which were created. The behaviour is slightly different if the
 * entity are {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable}, since they need to be fetched again and can not be be tested
 * against their original references</li>
 * <li><i>Different</i> is deleted, and <i>normal</i> is updated to hold the
 * same value of the <i>different</i> <i>entity</i></li>
 * <li>The set retrieved by reading all the tested entity is asserted as
 * containing only one element, which is later asserted to be what used to be
 * <i>normal</i> holding the value of <i>different</i></li>
 * <li>The updated entity is deleted</li>
 * <li>The storage is asserted as empty</li>
 * <li>All the references created to support the testing are deleted</li>
 * </ul>
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@FixMethodOrder(MethodSorters.JVM)
public class TestPeopleMapper {

    private static final String PRIVATO = "Privato";
    private final Persistence persistence = Persistences.retrieveFullyInstantiatedTestingPersistenceSystem();
    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency = 
        TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    /**
     * Tests the system capability to handle the CRUD operations on a
     * {@link Contact}.
     */
    @Test
    public void testPersistenceContact() {
        Contact normal;
        Contact different;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));
        persistence.createEntry(TestEntityConstants.CON_CATEGORY);
        persistence.createEntry(TestEntityConstants.CON_DIFFERENT_CATEGORY);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        normal = new ContactImpl(TestEntityConstants.CON_CATEGORY,
                        persistence.readPeople(
                                Queries.getTestFilter(
                                        TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next(), 
                        TestConstants.A_STRING);

        different = new ContactImpl(TestEntityConstants.CON_DIFFERENT_CATEGORY,
                        persistence.readPeople(
                                Queries.getTestFilter(
                                        TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next(), 
                        TestConstants.A_DIFFERENT_STRING);

        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).size() == 2);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).contains(normal));
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).size() == 1);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).contains(normal));
        assertFalse(persistence.readContact(Queries.getAll(Contact.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).size() == 1);
        assertFalse(persistence.readContact(Queries.getAll(Contact.class)).contains(normal));
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readContact(Queries.getAll(Contact.class)).isEmpty());

        persistence.deleteEntry(persistence.readPeople(Queries.getTestFilter(
                TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(Queries.getTestFilter(
                TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.CON_DIFFERENT_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.CON_CATEGORY);
        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));

    }

    /**
     * Tests the system capability to handle the CRUD operations on an
     * {@link ContactCategory}.
     */
    @Test
    public void testPersistenceContactCategory() {
        final ContactCategory normal = TestEntityConstants.CON_CATEGORY;
        final ContactCategory different = TestEntityConstants.CON_DIFFERENT_CATEGORY;

        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).size() == 2);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(normal));
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).size() == 1);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(normal));
        assertFalse(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).size() == 1);
        assertFalse(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(normal));
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readContactCategories(Queries.getAll(ContactCategory.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an
     * {@link JuridicalPerson}.
     */
    @Test
    public void testPersistenceJuridicalPerson() {
        persistence.createEntry(TestEntityConstants.PER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_PER_CATEGORY);

        final JuridicalPerson normal = new JuridicalPersonImpl.Builder()
                                            .category(TestEntityConstants.PER_CATEGORY)
                                            .name(TestConstants.A_STRING)
                                            .fiscalCode(TestConstants.A_STRING)
                                            .annotations(TestConstants.A_STRING)
                                            .build();
        final JuridicalPerson different = new JuridicalPersonImpl.Builder()
                                            .category(TestEntityConstants.DIFFERENT_PER_CATEGORY)
                                            .name(TestConstants.A_DIFFERENT_STRING)
                                            .fiscalCode(TestConstants.A_DIFFERENT_STRING)
                                            .annotations(TestConstants.A_DIFFERENT_STRING)
                                            .build();
        JuridicalPerson identifiedNormal;
        JuridicalPerson identifiedDifferent;

        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).size() == 2);
        assertFalse(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).contains(normal));
        assertFalse(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).contains(different));
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(different, normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(different);
        });

        identifiedNormal = persistence.readJuridicalPeople(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readJuridicalPeople(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readJuridicalPeople(
                Queries.getAll(JuridicalPerson.class)).contains(identifiedNormal));
        assertTrue(persistence.readJuridicalPeople(
                Queries.getAll(JuridicalPerson.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).size() == 1);
        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).contains(identifiedNormal));
        assertFalse(persistence.readJuridicalPeople(
                Queries.getAll(JuridicalPerson.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, identifiedDifferent);
        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).size() == 1);
        assertFalse(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).contains(identifiedNormal));
        assertFalse(persistence.readJuridicalPeople(
                Queries.getAll(JuridicalPerson.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readJuridicalPeople(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).size() == 1);
        assertTrue(persistence.readJuridicalPeople(
                Queries.getAll(JuridicalPerson.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readJuridicalPeople(Queries.getAll(JuridicalPerson.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.PER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_PER_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an
     * {@link PersonCategory}.
     */
    @Test
    public void testPersistencePersonCategory() {
        final PersonCategory normal = TestEntityConstants.PER_CATEGORY;
        final PersonCategory different = TestEntityConstants.DIFFERENT_PER_CATEGORY;

        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).size() == 2);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).contains(normal));
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).size() == 1);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).contains(normal));
        assertFalse(persistence.readJuridicalPersonCategories(
                Queries.getAll(PersonCategory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).size() == 1);
        assertFalse(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).contains(normal));
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readJuridicalPersonCategories(Queries.getAll(PersonCategory.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an
     * {@link PhysicalPerson}.
     */
    @Test
    public void testPersistencePhysicalPerson() {
        persistence.createEntry(new PersonCategoryImpl(PRIVATO));

        final PhysicalPerson normal = new PhysicalPersonImpl.Builder()
                                        .name(TestConstants.A_STRING)
                                        .birthDate(TestConstants.DATE)
                                        .birthLocation(TestConstants.A_STRING)
                                        .fiscalCode(TestConstants.A_STRING)
                                        .annotations(TestConstants.A_STRING)
                                        .build();
        final PhysicalPerson different = new PhysicalPersonImpl.Builder()
                                        .name(TestConstants.A_DIFFERENT_STRING)
                                        .birthDate(TestConstants.DIFFERENT_DATE)
                                        .birthLocation(TestConstants.A_DIFFERENT_STRING)
                                        .fiscalCode(TestConstants.A_DIFFERENT_STRING)
                                        .annotations(TestConstants.A_DIFFERENT_STRING)
                                        .build();
        PhysicalPerson identifiedNormal;
        PhysicalPerson identifiedDifferent;

        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).size() == 2);
        assertFalse(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(normal));
        assertFalse(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(different));
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(different, normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(different);
        });

        identifiedNormal = persistence.readPeople(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readPeople(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedNormal));
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).size() == 1);
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedNormal));
        assertFalse(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, identifiedDifferent);
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).size() == 1);
        assertFalse(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedNormal));
        assertFalse(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readPeople(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).size() == 1);
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readPeople(Queries.getAll(PhysicalPerson.class)).isEmpty());

        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on an
     * {@link TrashwareWorker}.
     */
    @Test
    public void testPersistenceTrashwareWorker() {
        TrashwareWorker normal;
        TrashwareWorker different;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));
        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        normal = new TrashwareWorkerImpl(
                        persistence.readPeople(
                                Queries.getTestFilter(
                                        TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next(),
                        TestEntityConstants.WORKER_CATEGORY,
                        true);

        different = new TrashwareWorkerImpl(
                        persistence.readPeople(
                                Queries.getTestFilter(
                                        TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next(),
                        TestEntityConstants.DIFFERENT_WORKER_CATEGORY,
                        true);

        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).size() == 2);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(normal));
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).size() == 1);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(normal));
        assertFalse(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).size() == 1);
        assertFalse(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(normal));
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readTrashwareWorkers(Queries.getAll(TrashwareWorker.class)).isEmpty());

        persistence.deleteEntry(persistence.readPeople(Queries.getTestFilter(
                TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(Queries.getTestFilter(
                TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on an
     * {@link TrashwareWorkerCategory}.
     */
    @Test
    public void testPersistenceTrashwareWorkerCategory() {
        final TrashwareWorkerCategory normal = TestEntityConstants.WORKER_CATEGORY;
        final TrashwareWorkerCategory different = TestEntityConstants.DIFFERENT_WORKER_CATEGORY;

        assertTrue(persistence.readTrashwareWorkerCategories(Queries.getAll(TrashwareWorkerCategory.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).size() == 2);
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(normal));
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).size() == 1);
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(normal));
        assertFalse(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).size() == 1);
        assertFalse(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(normal));
        assertTrue(persistence.readTrashwareWorkerCategories(
                Queries.getAll(TrashwareWorkerCategory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readTrashwareWorkerCategories(Queries.getAll(TrashwareWorkerCategory.class)).isEmpty());
    }

}
