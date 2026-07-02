package it.trashwarecesena.trustalodesktopclient.repository.test.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.FrequencyUnit;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.Processor;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.ProcessorImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.Persistence;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.test.Persistences;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * A test class for the processors mapper boundary.
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
public final class TestProcessorsMapper {

    private final Persistence persistence = Persistences.retrieveFullyInstantiatedTestingPersistenceSystem();
    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency = TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    /**
     * Tests the system capability to handle the CRUD operations on a
     * {@link Processor}.
     */
    @Test
    public void testPersistenceProcessor() {
        Processor normal;
        Processor different;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestEntityConstants.FREQ_UNIT);
        persistence.createEntry(TestEntityConstants.DIFFERENT_FREQ_UNIT);
        persistence.createEntry(TestEntityConstants.ISA);
        persistence.createEntry(TestEntityConstants.DIFFERENT_ISA);
        persistence.createEntry(TestEntityConstants.INF_UNIT);
        persistence.createEntry(TestEntityConstants.INF_DIFFERENT_UNIT);

        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).isEmpty());

        normal = new ProcessorImpl.Builder()
                        .device(persistence.readGenericDevice(
                                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next())
                        .frequency(TestConstants.A_POSITIVE_FLOAT)
                        .frequencyUnit(TestEntityConstants.FREQ_UNIT)
                        .instructionSet(TestEntityConstants.ISA)
                        .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
                        .l3CacheUnit(TestEntityConstants.INF_UNIT)
                        .build();

        different = new ProcessorImpl.Builder()
                        .device(persistence.readGenericDevice(
                                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                    .iterator().next())
                        .frequency(TestConstants.A_DIFFERENT_POSITIVE_FLOAT)
                        .frequencyUnit(TestEntityConstants.DIFFERENT_FREQ_UNIT)
                        .instructionSet(TestEntityConstants.DIFFERENT_ISA)
                        .l3CacheAmount(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                        .l3CacheUnit(TestEntityConstants.INF_DIFFERENT_UNIT)
                        .build();

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).size() == 2);
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).contains(normal));
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).size() == 1);
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).contains(normal));
        assertFalse(persistence.readProcessors(Queries.getAll(Processor.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).size() == 1);
        assertFalse(persistence.readProcessors(Queries.getAll(Processor.class)).contains(normal));
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readProcessors(Queries.getAll(Processor.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.INF_DIFFERENT_UNIT);
        persistence.deleteEntry(TestEntityConstants.INF_UNIT);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_ISA);
        persistence.deleteEntry(TestEntityConstants.ISA);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_FREQ_UNIT);
        persistence.deleteEntry(TestEntityConstants.FREQ_UNIT);
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on a
     * {@link FrequencyUnit}.
     */
    @Test
    public void testPersistenceFrequencyUnit() {
        final FrequencyUnit normal = TestEntityConstants.FREQ_UNIT;
        final FrequencyUnit different = TestEntityConstants.DIFFERENT_FREQ_UNIT;

        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).size() == 2);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(normal));
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).size() == 1);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(normal));
        assertFalse(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).size() == 1);
        assertFalse(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(normal));
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readFrequencyUnits(Queries.getAll(FrequencyUnit.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on a
     * {@link InstructionSet}.
     */
    @Test
    public void testPersistenceInstrucionSet() {
        final InstructionSet normal = TestEntityConstants.ISA;
        final InstructionSet different = TestEntityConstants.DIFFERENT_ISA;

        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).size() == 2);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(normal));
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).size() == 1);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(normal));
        assertFalse(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).size() == 1);
        assertFalse(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(normal));
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readInstructionSets(Queries.getAll(InstructionSet.class)).isEmpty());
    }

}
