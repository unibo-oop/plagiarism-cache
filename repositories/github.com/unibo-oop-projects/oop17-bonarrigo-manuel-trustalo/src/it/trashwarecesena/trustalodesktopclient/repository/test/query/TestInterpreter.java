package it.trashwarecesena.trustalodesktopclient.repository.test.query;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.Interpreter;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.MariaDbSqlInterpreter;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.OdataJsonProcessorInterpreter;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.XPathInterpreter;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * A test over the ability of translating a {@link QueryObject} by the
 * implemented {@link Interpreter}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestInterpreter {

    private final QueryObject simpleFilter = 
            new QueryObjectImpl(PrinterCategory.class,
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getName", TestConstants.A_STRING))
                    .build());

    private final QueryObject complexFilter = 
            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION);

    /**
     * A test over the {@link MariaDbSqlInterpreter}.
     */
    @Test
    public void testSqlTranslation() {
        final Interpreter interpreter = new MariaDbSqlInterpreter();
        assertTrue(interpreter.translate(simpleFilter).equals(
                "SELECT * "
                + "FROM PrinterTecnologies "
                + "WHERE Name = 'TheString'"));

        assertTrue((interpreter.translate(complexFilter)).equals(
                    "SELECT * "
                    + "FROM ScreenResolutions "
                    + "WHERE AspectRatio IN "
                        + "(SELECT ID "
                        + "FROM AspectRatios "
                        + "WHERE RatioFactor = '1:1') "
                    + "AND Height = 1 "
                    + "AND Width = 1"));

        /*
         * This snippet of code is commented out because the Interpreter likes to
         * shuffle the order of the SQL statements it produces. This is truly annoying
         * from a testing point of view, but do not affect the functionality of the
         * generated queries.
         */

//        assertTrue((interpreter.translate(complexFilter)).equals(
//                "SELECT * "
//                + "FROM ScreenResolutions "
//                + "WHERE Width = 1 "
//                + "AND AspectRatio IN "
//                    + "(SELECT ID "
//                    + "FROM AspectRatios "
//                    + "WHERE RatioFactor = '1:1') "
//                + "AND Height = 1"));

//        String s = interpreter.translate(complexFilter);
//        System.out.println(s);
//        String s2 = "SELECT * FROM ScreenResolutions WHERE Width = 1 AND AspectRatio IN (SELECT ID FROM AspectRatios "
//                + "WHERE RatioFactor = '1:1') AND Height = 1";
//        String s3 = "SELECT * FROM ScreenResolutions WHERE AspectRatio IN (SELECT ID FROM AspectRatios WHERE "
//                + "RatioFactor = '1:1') AND Height = 1 AND Width = 1"; 
//        String s4 = "ToBeDiscovered";
    }

    /**
     * A test over the {@link XPathInterpreter}.
     */
    @Test
    public void testXPathTranslation() {
        final Interpreter interpreter = new XPathInterpreter();
        assertTrue(interpreter.translate(simpleFilter).equals("*"));
        assertTrue(interpreter.translate(complexFilter).equals("*"));
    }

    /**
     * A test over the {@link ODataJsonInterpreter}.
     */
    @Test
    public void testRestfulTranslation() {
        final Interpreter interpreter = new OdataJsonProcessorInterpreter();
        interpreter.translate(simpleFilter);
        System.out.println();
        assertTrue(interpreter.translate(simpleFilter).equals(
                "/API/v1_0/Products/Processors()"
                + "?&$select=ProductId,ProcessorNumber,CacheKB,ClockSpeedMhz,"
                + "InstructionSet,CacheType,Cache,ProductName"
                + "&$filter=Name+eq+'TheString'&$format=json"));
    }
}
