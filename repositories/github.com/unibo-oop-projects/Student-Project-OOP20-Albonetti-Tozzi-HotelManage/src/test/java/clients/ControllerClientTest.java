package clients;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import controller.Client.ControllerClient;
import controller.Client.ControllerClientImpl;


public class ControllerClientTest {

    private ControllerClient controller = new ControllerClientImpl();

    /**
     * Test get all client.
     */
    @Test
    public void testgetList() {
        final ControllerClient cTest = new ControllerClientImpl();
        assertEquals(controller.getAllClient(), cTest.getAllClient());
    }
    /**
     * Test get client.
     */
    @Test
    public void testGetClient() {
        final ControllerClient cTest = new ControllerClientImpl();
        Set<String> set = controller.getAllClient();
        for (var i : set) {
            assertEquals(controller.getClient(i), cTest.getClient(i));
        }
    }
    /**
     * Test search client.
     */
    @Test 
    public void testSearchClient() {
        final ControllerClient cTest = new ControllerClientImpl();
        Set<String> set = controller.getAllClient();
        for  (var i : set) {
            assertEquals(true, cTest.searchClient(i));
        }
    }
    /**
     * Test writing and deleting.
     */
    @Test
    public void testWriteDelete() {
        final ControllerClient cTest = new ControllerClientImpl();
        cTest.insertClient("prova", "prova", "prova1452");
        String sprova = "prova.prova.prova1452";
        assertEquals(true, cTest.searchClient(sprova));
        cTest.deleteLine(sprova);
        assertEquals(false, cTest.searchClient(sprova));
    }

}
