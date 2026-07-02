package it.trashwarecesena.trustalodesktopclient.model.test.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link TrashwareWorker} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestTrashwareWorker {

    private final TrashwareWorker worker;
    private final TrashwareWorker differentWorker;
    private final TrashwareWorker sameWorker;

    private final Executable nullFirstParameter = () -> {
        new TrashwareWorkerImpl(null, TestEntityConstants.WORKER_CATEGORY, true);
    };

    private final Executable nullSecondParameter = () -> {
        new TrashwareWorkerImpl(TestIdentifiableConstants.IDENTIFIED_PH_PERSON, null, true);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestTrashwareWorker() {
        this.worker = new TrashwareWorkerImpl(TestIdentifiableConstants.IDENTIFIED_PH_PERSON,
                TestEntityConstants.WORKER_CATEGORY, true);
        this.differentWorker = new TrashwareWorkerImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_PH_PERSON,
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);
        this.sameWorker = new TrashwareWorkerImpl(TestIdentifiableConstants.SAME_IDENTIFIED_PH_PERSON,
                TestEntityConstants.SAME_WORKER_CATEGORY, true);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(worker.getPerson().equals(TestIdentifiableConstants.IDENTIFIED_PH_PERSON));
        assertTrue(worker.getCategory().equals(TestEntityConstants.WORKER_CATEGORY));
        assertTrue(worker.isCurrentlyWorking());

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(worker.equals(sameWorker));
        assertTrue(sameWorker.equals(worker));
        assertFalse(worker.equals(differentWorker));
        assertFalse(sameWorker.equals(differentWorker));
    }

}
