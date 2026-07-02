package jwhale.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
import jwhale.model.engine.operations.list.ContainerList;
import jwhale.model.engine.operations.list.ImageList;
import jwhale.model.engine.operations.list.NetworkList;
import jwhale.model.engine.operations.list.VolumeList;

public class ListOperationTest {

    @Test
    public void listContainerTest() {
        final ContainerList containers = new ContainerList().baseSetup();
        final String last = "/json";
        assertEquals(EndPoint.CONTAINER, containers.configOperation().getEndPoint());
        assertEquals(last, containers.configOperation().getLastParam().get());
        assertNotEquals(Optional.empty(), containers.configOperation().getLastParam());
        assertNotEquals(Optional.empty(), containers.configOperation().getQueryParams());
        assertEquals(Method.GET, containers.configOperation().getMethod());
    }

    @Test
    public void listImageTest() {
        final ImageList images = new ImageList().baseSetup();
        final String last = "/json";
        assertEquals(EndPoint.IMAGE, images.configOperation().getEndPoint());
        assertEquals(last, images.configOperation().getLastParam().get());
        assertNotEquals(Optional.empty(), images.configOperation().getLastParam());
        assertNotEquals(Optional.empty(), images.configOperation().getQueryParams().get());
        assertEquals(Method.GET, images.configOperation().getMethod());
    }

    @Test
    public void listNetworkTest() {
        final NetworkList network = new NetworkList().baseSetup();
        final String last = "";
        assertEquals(EndPoint.NETWORK, network.configOperation().getEndPoint());
        assertEquals(Method.GET, network.configOperation().getMethod());
        assertEquals(last, network.configOperation().getLastParam().get());
        assertNotEquals(Optional.empty(), network.configOperation().getLastParam());
        assertEquals(Optional.empty(), network.configOperation().getQueryParams());
    }

    @Test
    public void listVolumeTest() {
        final VolumeList volume = new VolumeList().baseSetup();
        final String last = "";
        assertEquals(EndPoint.VOLUME, volume.configOperation().getEndPoint());
        assertEquals(last, volume.configOperation().getLastParam().get());
        assertEquals(Method.GET, volume.configOperation().getMethod());
        assertNotEquals(Optional.empty(), volume.configOperation().getLastParam());
        assertEquals(Optional.empty(), volume.configOperation().getQueryParams());
    }
}
