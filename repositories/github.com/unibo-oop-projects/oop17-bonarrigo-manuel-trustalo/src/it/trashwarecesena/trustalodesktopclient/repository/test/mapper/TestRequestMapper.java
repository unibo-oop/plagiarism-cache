package it.trashwarecesena.trustalodesktopclient.repository.test.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PersonCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.GenericDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.RefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.GenericDeviceRequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RefinedRequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestDetailImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.Persistence;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.test.Persistences;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * A test class for the request mapper boundary.
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
public class TestRequestMapper {

    private static final String PRIVATO = "Privato";
    private final Persistence persistence = Persistences.retrieveFullyInstantiatedTestingPersistenceSystem();
    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency = 
        TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    /**
     * Tests the system capability to handle the CRUD operations on an {@link GenericDeviceRequest}.
     */
    @Test
    public void testPersistenceGenericDeviceRequest() {
        GenericDeviceRequest normal;
        GenericDeviceRequest different;

        RequestDetail normalRequestDetail;
        RequestDetail differentRequestDetail;
        Request normalRequest;
        Request differentRequest;
        TrashwareWorker normalWorker;
        TrashwareWorker differentWorker;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));
        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        normalWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true);

        differentWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);

        persistence.createEntry(normalWorker);
        persistence.createEntry(differentWorker);

        persistence.createEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);

        normalRequest = new RequestImpl.Builder()
                        .applicant(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                        .signer(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                        .referee(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                        .creationDate(TestConstants.DATE)
                        .progress(TestEntityConstants.REQ_PROGRESS)
                        .lastUpdate(TestConstants.DATE)
                        .lastCommitter(normalWorker)
                        .trelloLink(TestConstants.U_R_L)
                        .annotations(TestConstants.A_STRING)
                        .priority(TestConstants.A_POSITIVE_INTEGER)
                        .build();

        differentRequest = new RequestImpl.Builder()
                            .applicant(persistence.readPeople(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                    .iterator().next())
                            .signer(persistence.readPeople(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                    .iterator().next())
                            .referee(persistence.readPeople(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                    .iterator().next())
                            .creationDate(TestConstants.DIFFERENT_DATE)
                            .progress(TestEntityConstants.DIFFERENT_REQ_PROGRESS)
                            .lastUpdate(TestConstants.DIFFERENT_DATE)
                            .lastCommitter(differentWorker)
                            .trelloLink(TestConstants.DIFFERENT_U_R_L)
                            .annotations(TestConstants.A_DIFFERENT_STRING)
                            .priority(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                            .build();

        persistence.createEntry(normalRequest);
        persistence.createEntry(differentRequest);

        normalRequestDetail = new RequestDetailImpl.Builder()
                                .associatedRequest(persistence.readRequest(
                                        Queries.getTestFilter(normalRequest)).iterator().next())
                                .category(TestEntityConstants.DEV_CATEGORY)
                                .annotations(TestConstants.A_STRING)
                                .quantity(TestConstants.A_POSITIVE_INTEGER)
                                .build();

        differentRequestDetail = new RequestDetailImpl.Builder()
                                .associatedRequest(persistence.readRequest(
                                        Queries.getTestFilter(differentRequest)).iterator().next())
                                .category(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                                .annotations(TestConstants.A_DIFFERENT_STRING)
                                .quantity(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                                .build();

        persistence.createEntry(normalRequestDetail);
        persistence.createEntry(differentRequestDetail);

        normal = new GenericDeviceRequestImpl(
                    persistence.readRequestDetail(Queries.getTestFilter(normalRequestDetail)).iterator().next(),
                    persistence.readGenericDevice(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE))
                        .iterator().next(),
                    TestConstants.A_POSITIVE_INTEGER);

        different = new GenericDeviceRequestImpl(
                persistence.readRequestDetail(Queries.getTestFilter(differentRequestDetail)).iterator().next(),
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                .iterator().next(),
                TestConstants.A_DIFFERENT_POSITIVE_INTEGER);

        /*
         * Test Start
         */
        assertTrue(persistence.readGenericDeviceRequest(Queries.getAll(GenericDeviceRequest.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readGenericDeviceRequest(Queries.getAll(GenericDeviceRequest.class)).size() == 2);
        assertTrue(persistence.readGenericDeviceRequest(
                Queries.getAll(GenericDeviceRequest.class)).contains(normal));
        assertTrue(persistence.readGenericDeviceRequest(
                Queries.getAll(GenericDeviceRequest.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readGenericDeviceRequest(Queries.getAll(GenericDeviceRequest.class)).size() == 1);
        assertTrue(persistence.readGenericDeviceRequest(Queries.getAll(GenericDeviceRequest.class)).contains(normal));
        assertFalse(persistence.readGenericDeviceRequest(
                Queries.getAll(GenericDeviceRequest.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readGenericDeviceRequest(Queries.getAll(GenericDeviceRequest.class)).size() == 1);
        assertFalse(persistence.readGenericDeviceRequest(
                Queries.getAll(GenericDeviceRequest.class)).contains(normal));
        assertTrue(persistence.readGenericDeviceRequest(
                Queries.getAll(GenericDeviceRequest.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readGenericDeviceRequest(Queries.getAll(GenericDeviceRequest.class)).isEmpty());
        /*
         * Test End
         */
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(persistence.readRequest(Queries.getTestFilter(differentRequest)).iterator().next());
        persistence.deleteEntry(persistence.readRequest(Queries.getTestFilter(normalRequest)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.deleteEntry(differentWorker);
        persistence.deleteEntry(normalWorker);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link RefinedDeviceRequest}.
     */
    @Test
    public void testPersistenceRefinedDeviceRequest() {
        RefinedDeviceRequest normal;
        RefinedDeviceRequest different;

        RequestDetail normalRequestDetail;
        RequestDetail differentRequestDetail;
        Request normalRequest;
        Request differentRequest;
        TrashwareWorker normalWorker;
        TrashwareWorker differentWorker;
        RefinedDevice normalRefined;
        RefinedDevice differentRefined;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));
        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        normalWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true);

        differentWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);

        persistence.createEntry(normalWorker);
        persistence.createEntry(differentWorker);

        persistence.createEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);

        normalRequest = new RequestImpl.Builder()
                        .applicant(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                        .signer(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                        .referee(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                        .creationDate(TestConstants.DATE)
                        .progress(TestEntityConstants.REQ_PROGRESS)
                        .lastUpdate(TestConstants.DATE)
                        .lastCommitter(normalWorker)
                        .trelloLink(TestConstants.U_R_L)
                        .annotations(TestConstants.A_STRING)
                        .priority(TestConstants.A_POSITIVE_INTEGER)
                        .build();

        differentRequest = new RequestImpl.Builder()
                            .applicant(persistence.readPeople(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                    .iterator().next())
                            .signer(persistence.readPeople(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                    .iterator().next())
                            .referee(persistence.readPeople(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                    .iterator().next())
                            .creationDate(TestConstants.DIFFERENT_DATE)
                            .progress(TestEntityConstants.DIFFERENT_REQ_PROGRESS)
                            .lastUpdate(TestConstants.DIFFERENT_DATE)
                            .lastCommitter(differentWorker)
                            .trelloLink(TestConstants.DIFFERENT_U_R_L)
                            .annotations(TestConstants.A_DIFFERENT_STRING)
                            .priority(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                            .build();

        persistence.createEntry(normalRequest);
        persistence.createEntry(differentRequest);

        normalRequestDetail = new RequestDetailImpl.Builder()
                                .associatedRequest(persistence.readRequest(
                                        Queries.getTestFilter(normalRequest)).iterator().next())
                                .category(TestEntityConstants.DEV_CATEGORY)
                                .annotations(TestConstants.A_STRING)
                                .quantity(TestConstants.A_POSITIVE_INTEGER)
                                .build();

        differentRequestDetail = new RequestDetailImpl.Builder()
                                .associatedRequest(persistence.readRequest(
                                        Queries.getTestFilter(differentRequest)).iterator().next())
                                .category(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                                .annotations(TestConstants.A_DIFFERENT_STRING)
                                .quantity(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                                .build();

        persistence.createEntry(normalRequestDetail);
        persistence.createEntry(differentRequestDetail);
        persistence.createEntry(TestEntityConstants.WORK_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);

        normalRefined = new RefinedDeviceImpl.Builder()
                            .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
                            .refining(persistence.readGenericDevice(
                                    Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE))
                                        .iterator().next())
                            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                            .available(true)
                            .progress(TestEntityConstants.WORK_PROGRESS)
                            .lastCommitter(normalWorker)
                            .annotations(TestConstants.A_STRING)
                            .lastUpdate(TestConstants.DATE)
                            .build();

        differentRefined = new RefinedDeviceImpl.Builder()
                            .categoryDeviceId(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                            .refining(persistence.readGenericDevice(
                                    Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                        .iterator().next())
                            .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                            .available(true)
                            .progress(TestEntityConstants.DIFFERENT_WORK_PROGRESS)
                            .lastCommitter(normalWorker)
                            .annotations(TestConstants.A_DIFFERENT_STRING)
                            .lastUpdate(TestConstants.DIFFERENT_DATE)
                            .build();

        persistence.createEntry(normalRefined);
        persistence.createEntry(differentRefined);

        normal = new RefinedRequestImpl(
                    persistence.readRefinedDevice(Queries.getTestFilter(normalRefined))
                        .iterator().next(),
                    persistence.readRequestDetail(Queries.getTestFilter(normalRequestDetail)).iterator().next());

        different = new RefinedRequestImpl(
                persistence.readRefinedDevice(
                        Queries.getTestFilter(differentRefined))
                                .iterator().next(),
                persistence.readRequestDetail(Queries.getTestFilter(differentRequestDetail)).iterator().next());
        /*
         * Test Start
         */
        assertTrue(persistence.readRefinedDeviceRequest(Queries.getAll(RefinedDeviceRequest.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readRefinedDeviceRequest(Queries.getAll(RefinedDeviceRequest.class)).size() == 2);
        assertTrue(persistence.readRefinedDeviceRequest(
                Queries.getAll(RefinedDeviceRequest.class)).contains(normal));
        assertTrue(persistence.readRefinedDeviceRequest(
                Queries.getAll(RefinedDeviceRequest.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readRefinedDeviceRequest(Queries.getAll(RefinedDeviceRequest.class)).size() == 1);
        assertTrue(persistence.readRefinedDeviceRequest(Queries.getAll(RefinedDeviceRequest.class)).contains(normal));
        assertFalse(persistence.readRefinedDeviceRequest(
                Queries.getAll(RefinedDeviceRequest.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readRefinedDeviceRequest(Queries.getAll(RefinedDeviceRequest.class)).size() == 1);
        assertFalse(persistence.readRefinedDeviceRequest(
                Queries.getAll(RefinedDeviceRequest.class)).contains(normal));
        assertTrue(persistence.readRefinedDeviceRequest(
                Queries.getAll(RefinedDeviceRequest.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readRefinedDeviceRequest(Queries.getAll(RefinedDeviceRequest.class)).isEmpty());
        /*
         * Test End
         */
        persistence.deleteEntry(persistence.readRefinedDevice(Queries.getTestFilter(differentRefined))
                .iterator().next());
        persistence.deleteEntry(persistence.readRefinedDevice(Queries.getTestFilter(normalRefined)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.WORK_PROGRESS);

        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(persistence.readRequest(Queries.getTestFilter(differentRequest)).iterator().next());
        persistence.deleteEntry(persistence.readRequest(Queries.getTestFilter(normalRequest)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.deleteEntry(differentWorker);
        persistence.deleteEntry(normalWorker);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link Request}.
     */
    @Test
    public void testPersistenceRequest() {
        Request normal;
        Request different;
        Request identifiedNormal;
        Request identifiedDifferent;

        TrashwareWorker normalWorker;
        TrashwareWorker differentWorker;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);
        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);

        normalWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true);

        differentWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);

        persistence.createEntry(normalWorker);
        persistence.createEntry(differentWorker);

        normal = new RequestImpl.Builder()
                    .applicant(persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                    .signer(persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                    .referee(persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                    .creationDate(TestConstants.DATE)
                    .progress(TestEntityConstants.REQ_PROGRESS)
                    .lastUpdate(TestConstants.DATE)
                    .lastCommitter(normalWorker)
                    .trelloLink(TestConstants.U_R_L)
                    .annotations(TestConstants.A_STRING)
                    .priority(TestConstants.A_POSITIVE_INTEGER)
                    .build();

        different = new RequestImpl.Builder()
                        .applicant(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                .iterator().next())
                        .signer(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                .iterator().next())
                        .referee(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                .iterator().next())
                        .creationDate(TestConstants.DIFFERENT_DATE)
                        .progress(TestEntityConstants.DIFFERENT_REQ_PROGRESS)
                        .lastUpdate(TestConstants.DIFFERENT_DATE)
                        .lastCommitter(differentWorker)
                        .trelloLink(TestConstants.DIFFERENT_U_R_L)
                        .annotations(TestConstants.A_DIFFERENT_STRING)
                        .priority(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                        .build();

        /*
         * Test Start
         */
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).size() == 2);
        assertFalse(persistence.readRequest(Queries.getAll(Request.class)).contains(normal));
        assertFalse(persistence.readRequest(Queries.getAll(Request.class)).contains(different));
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

        identifiedNormal = persistence.readRequest(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readRequest(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedNormal));
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).size() == 1);
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedNormal));
        assertFalse(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, identifiedDifferent);
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).size() == 1);
        assertFalse(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedNormal));
        assertFalse(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readRequest(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).size() == 1);
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readRequest(Queries.getAll(Request.class)).isEmpty());
        /*
         * Test End
         */

        persistence.deleteEntry(differentWorker);
        persistence.deleteEntry(normalWorker);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on a {@link RequestDetail}.
     */
    @Test
    public void testPersistenceRequestDetail() {

        RequestDetail normal;
        RequestDetail different;
        RequestDetail identifiedNormal;
        RequestDetail identifiedDifferent;

        Request normalRequest;
        Request differentRequest;
        TrashwareWorker normalWorker;
        TrashwareWorker differentWorker;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);
        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);

        normalWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true);

        differentWorker = new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);

        persistence.createEntry(normalWorker);
        persistence.createEntry(differentWorker);

        normalRequest = new RequestImpl.Builder()
                    .applicant(persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                    .signer(persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                    .referee(persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next())
                    .creationDate(TestConstants.DATE)
                    .progress(TestEntityConstants.REQ_PROGRESS)
                    .lastUpdate(TestConstants.DATE)
                    .lastCommitter(normalWorker)
                    .trelloLink(TestConstants.U_R_L)
                    .annotations(TestConstants.A_STRING)
                    .priority(TestConstants.A_POSITIVE_INTEGER)
                    .build();

        differentRequest = new RequestImpl.Builder()
                        .applicant(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                .iterator().next())
                        .signer(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                .iterator().next())
                        .referee(persistence.readPeople(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                .iterator().next())
                        .creationDate(TestConstants.DIFFERENT_DATE)
                        .progress(TestEntityConstants.DIFFERENT_REQ_PROGRESS)
                        .lastUpdate(TestConstants.DIFFERENT_DATE)
                        .lastCommitter(differentWorker)
                        .trelloLink(TestConstants.DIFFERENT_U_R_L)
                        .annotations(TestConstants.A_DIFFERENT_STRING)
                        .priority(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                        .build();

        persistence.createEntry(normalRequest);
        persistence.createEntry(differentRequest);

        normal = new RequestDetailImpl.Builder()
                        .associatedRequest(persistence.readRequest(
                                Queries.getTestFilter(normalRequest)).iterator().next())
                        .category(TestEntityConstants.DEV_CATEGORY)
                        .annotations(TestConstants.A_STRING)
                        .quantity(TestConstants.A_POSITIVE_INTEGER)
                        .build();

        different = new RequestDetailImpl.Builder()
                        .associatedRequest(persistence.readRequest(
                                Queries.getTestFilter(differentRequest)).iterator().next())
                        .category(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                        .annotations(TestConstants.A_DIFFERENT_STRING)
                        .quantity(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                        .build();

        /*
         * Test Start
         */
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).size() == 2);
        assertFalse(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(normal));
        assertFalse(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(different));
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

        identifiedNormal = persistence.readRequestDetail(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readRequestDetail(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedNormal));
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).size() == 1);
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedNormal));
        assertFalse(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, identifiedDifferent);
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).size() == 1);
        assertFalse(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedNormal));
        assertFalse(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readRequestDetail(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).size() == 1);
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readRequestDetail(Queries.getAll(RequestDetail.class)).isEmpty());
        /*
         * Test End
         */

        persistence.deleteEntry(persistence.readRequest(Queries.getTestFilter(differentRequest)).iterator().next());
        persistence.deleteEntry(persistence.readRequest(Queries.getTestFilter(normalRequest)).iterator().next());
        persistence.deleteEntry(differentWorker);
        persistence.deleteEntry(normalWorker);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_REQ_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.REQ_PROGRESS);
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link AspectRatio}.
     */
    @Test
    public void testPersistenceRequestProgress() {
        final RequestProgress normal = TestEntityConstants.REQ_PROGRESS;
        final RequestProgress different = TestEntityConstants.DIFFERENT_REQ_PROGRESS;

        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).size() == 2);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(normal));
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).size() == 1);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(normal));
        assertFalse(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).size() == 1);
        assertFalse(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(normal));
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readRequestProgress(Queries.getAll(RequestProgress.class)).isEmpty());
    }
}
