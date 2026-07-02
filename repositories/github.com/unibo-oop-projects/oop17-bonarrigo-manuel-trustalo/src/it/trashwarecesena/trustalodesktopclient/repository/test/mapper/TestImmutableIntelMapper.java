package it.trashwarecesena.trustalodesktopclient.repository.test.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.immutable.ImmutableIntelProcessor;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.Persistence;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.repository.test.Persistences;

/**
 * A test class for the immutable Intel mapper boundary.
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
public final class TestImmutableIntelMapper {

    private static final int PROCESSOR_ID = 31731;
    private static final String INSTRUCTION_SET = "Itanium 64-bit";

    private final Persistence persistence = Persistences.retrieveFullyInstantiatedTestingPersistenceSystem();
    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency = 
        TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    /**
     * A test over the ability of the persistence layer to query the odata.intel.com
     * database to retrieve informations about the processors produced by Intel.
     */
    @Test
    public void testImmutableIntelProcessors() {

        final ImmutableIntelProcessor processor9150M = TestEntityConstants.INTEL_31731_PRODUCT;
        Set<ImmutableIntelProcessor> fetchedProcessors;

        QueryObject filter = 
            new QueryObjectImpl(ImmutableIntelProcessor.class, 
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getProductIdentifier", Integer.valueOf(PROCESSOR_ID)))
                    .build());

        fetchedProcessors = persistence.readIntelProcessors(filter);
        assertTrue(fetchedProcessors.iterator().next().equals(processor9150M));

        filter = new QueryObjectImpl(ImmutableIntelProcessor.class, 
                    new CriteriaImpl.Builder()
                        .addCriterion(CriterionImpl.equality("getInstructionSet", INSTRUCTION_SET))
                        .build());

        fetchedProcessors = persistence.readIntelProcessors(filter);
        assertTrue(fetchedProcessors.contains(processor9150M));
    }
}
