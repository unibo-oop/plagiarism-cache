package it.trashwarecesena.trustalodesktopclient.repository.test.query;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * Test over the {@link QueryObject} abstraction.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestQueryObject {

    /**
     * Test over construction, getters and equality.
     */
    @Test
    public void testQueryObjectFunctionalities() {
        final QueryObject filter = 
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION);
        final QueryObject differentFilter = 
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION);
        final QueryObject sameFilter = 
                Queries.getTestFilter(TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION);

        assertTrue(filter.equals(sameFilter));
        assertTrue(sameFilter.equals(filter));
        assertFalse(filter.equals(differentFilter));
        assertFalse(sameFilter.equals(differentFilter));

        assertTrue(filter.getDesiredHandler().equals(ScreenResolution.class));
        assertFalse(filter.getDesiredHandler()
                .equals(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION.getClass()));

        assertTrue(new HashSet<>(filter.getCriterionList()).equals(new HashSet<>(Arrays.asList(
                CriterionImpl.equality("getAspectRatio", TestEntityConstants.RATIO),
                CriterionImpl.equality("getWidth", TestConstants.A_POSITIVE_INTEGER),
                CriterionImpl.equality("getHeight", TestConstants.A_POSITIVE_INTEGER)
        ))));
    }

    /**
     * Test over the exceptions thrown.
     */
    @Test
    @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
    public void testQueryObjectExceptions() {
        @SuppressWarnings("unused")
        final QueryObject legal = new QueryObjectImpl(PrinterCategory.class,
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getName", TestConstants.A_STRING))
                    .build());

        assertThrows(IllegalArgumentException.class, () -> {
            new QueryObjectImpl(PrinterCategory.class,
                    new CriteriaImpl.Builder()
                        .addCriterion(CriterionImpl.equality("getName", TestConstants.A_DIFFERENT_POSITIVE_INTEGER))
                        .build());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new QueryObjectImpl(PrinterCategory.class,
                    new CriteriaImpl.Builder()
                        .addCriterion(CriterionImpl.equality("getInexistentField", TestConstants.A_STRING))
                        .build());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new QueryObjectImpl(Integer.class,
                    new CriteriaImpl.Builder()
                        .addCriterion(CriterionImpl.equality("getName", TestConstants.A_STRING))
                        .build());
        });
    }

}
