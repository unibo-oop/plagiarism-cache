package it.trashwarecesena.trustalodesktopclient.repository.test.metamapping;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;

/**
 * Tests over the utility class {@link MetamappingKnowledge}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestMetamappingKnowlege {

    private static final String GET_NAME = "getName";

    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency =
            TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    private final PrinterCategory domainModelEntity = TestEntityConstants.CATEGORY;
    private final Integer randomObject = Integer.valueOf(1);
    private final Class<?> identifiedInterface =
            MetamappingKnowledge.discoverDomainModelInterfaceImplemented(domainModelEntity.getClass()).get();

    /**
     * A test over the retrieval of the identifying field for the entity.
     */
    @Test
    public void testEntityIdentifierFieldRetrieval() {
       Optional<String> identifierName;
       identifierName = MetamappingKnowledge.getMappedEntityIdentifierField(domainModelEntity.getClass());
        assertTrue(identifierName.equals(Optional.empty()));

        identifierName = MetamappingKnowledge.getMappedEntityIdentifierField(randomObject.getClass());
        assertTrue(identifierName.equals(Optional.empty()));

        identifierName = MetamappingKnowledge.getMappedEntityIdentifierField(identifiedInterface);
        assertTrue(identifierName.get().equals("ID"));
    }

    /**
     * A test over the retrieval of the entity mapped to the class.
     */
    @Test
    public void testEntityNameRetrieval() {
        Optional<String> entityName = MetamappingKnowledge.getMappedEntityName(domainModelEntity.getClass());
         assertTrue(entityName.equals(Optional.empty()));

         entityName = MetamappingKnowledge.getMappedEntityName(randomObject.getClass());
         assertTrue(entityName.equals(Optional.empty()));

         entityName = MetamappingKnowledge.getMappedEntityName(identifiedInterface);
         assertTrue(entityName.get().equals("PrinterTecnologies"));
    }

    /**
     * Test over the retrieval of the entity field mapped to the class method.
     */
    @Test
    public void testMethodToFieldNameRetrieval() {
        Optional<String> entityFieldName = 
                MetamappingKnowledge.getMappedFieldName(domainModelEntity.getClass(), GET_NAME);
         assertTrue(entityFieldName.equals(Optional.empty()));

         entityFieldName = MetamappingKnowledge.getMappedFieldName(randomObject.getClass(), "getValue");
         assertTrue(entityFieldName.equals(Optional.empty()));

         entityFieldName = MetamappingKnowledge.getMappedFieldName(identifiedInterface, GET_NAME);
         assertTrue(entityFieldName.get().equals("Name"));
    }

    /**
     * A test over all the boolean returning methods.
     */
    @Test
    public void testBooleanAssertor() {

        assertFalse(MetamappingKnowledge.isLegalSelector(domainModelEntity.getClass(), GET_NAME));
        assertFalse(MetamappingKnowledge.isLegalSelector(randomObject.getClass(), "getValue"));
        assertTrue(MetamappingKnowledge.isLegalSelector(identifiedInterface, GET_NAME));

        assertFalse(MetamappingKnowledge.isLegalSelectorAndValueTypeCombination(
                domainModelEntity.getClass(), GET_NAME, Optional.of(String.class)));
        assertFalse(MetamappingKnowledge.isLegalSelectorAndValueTypeCombination(
                randomObject.getClass(), "getValue", Optional.of(Integer.class)));
        assertTrue(MetamappingKnowledge.isLegalSelectorAndValueTypeCombination(
                identifiedInterface, GET_NAME, Optional.of(String.class)));

        assertFalse(MetamappingKnowledge.isEntityInterface(domainModelEntity.getClass()));
        assertFalse(MetamappingKnowledge.isEntityInterface(randomObject.getClass()));
        assertTrue(MetamappingKnowledge.isEntityInterface(identifiedInterface));

        assertTrue(MetamappingKnowledge.isMetamappingAvailable(domainModelEntity.getClass()));
        assertFalse(MetamappingKnowledge.isMetamappingAvailable(randomObject.getClass()));
        assertTrue(MetamappingKnowledge.isMetamappingAvailable(identifiedInterface));

        assertFalse(MetamappingKnowledge.isSchemaEntityNameAvailable(domainModelEntity.getClass()));
        assertFalse(MetamappingKnowledge.isSchemaEntityNameAvailable(randomObject.getClass()));
        assertTrue(MetamappingKnowledge.isSchemaEntityNameAvailable(identifiedInterface));

    }

    /**
     * A test over the retrieval of all the available selectors in a domain model
     * class.
     */
    @Test
    public void testAvailableSelectorsRetrieval() {
        final Set<String> actuallyAvailableSelectors = new HashSet<>(Arrays.asList(GET_NAME));
        Set<String> availableSelectors = MetamappingKnowledge.getAvailableSelectors(domainModelEntity.getClass());
        assertFalse(availableSelectors.equals(actuallyAvailableSelectors));

        availableSelectors = MetamappingKnowledge.getAvailableSelectors(identifiedInterface);
        assertTrue(availableSelectors.equals(actuallyAvailableSelectors));
    }

    /**
     * A test over the method ability to navigate the hierarchy tree and discover
     * the original interface implemented by a domain model class.
     */
    @Test
    public void testDomainModelInterfaceImplementedRetrieval() {
        assertTrue(MetamappingKnowledge.discoverDomainModelInterfaceImplemented(
                domainModelEntity.getClass()).get().equals(PrinterCategory.class));

        assertTrue(MetamappingKnowledge.discoverDomainModelInterfaceImplemented(
                PrinterCategory.class).get().equals(PrinterCategory.class));

        assertTrue(MetamappingKnowledge.discoverDomainModelInterfaceImplemented(
                randomObject.getClass()).equals(Optional.empty()));

        assertTrue(MetamappingKnowledge.discoverDomainModelInterfaceImplemented(
                Object.class).equals(Optional.empty()));
    }

}
