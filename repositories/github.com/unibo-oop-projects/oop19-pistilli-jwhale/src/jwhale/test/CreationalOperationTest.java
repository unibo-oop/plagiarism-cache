package jwhale.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import jwhale.model.engine.Method;
import jwhale.model.engine.operations.creation.ContainerCreate;
import jwhale.model.engine.operations.creation.CreationalOp;
import jwhale.model.engine.operations.creation.ImagePull;
import jwhale.model.engine.operations.creation.NetworkCreate;
import jwhale.model.engine.operations.creation.VolumeCreate;

public class CreationalOperationTest {

    private static final String IMAGE = "imageName";
    private static final String VOLUME = "volumeName";
    private static final String NETWORK = "networkName";

    @Test
    public void createContainerTest() {
        final CreationalOp<ContainerCreate> container = new ContainerCreate();
        assertThrows(IllegalStateException.class, () -> container.configOperation());
        assertEquals(Method.POST, container.create(IMAGE).configOperation().getMethod());
        assertNotEquals(Optional.empty(), 
                container.create(IMAGE).setNetwork("rete").configOperation().getRequestBody());
        assertEquals(Optional.empty(), container.configOperation().getQueryParams());
        assertEquals(1, 
                container.create(IMAGE).setName("contName").configOperation().getQueryParams().get().size());
    }

    @Test
    public void createNetworkTest() {
        final CreationalOp<NetworkCreate> network = new NetworkCreate();
        assertThrows(IllegalStateException.class, () -> network.configOperation());
        assertEquals(Method.POST, network.create(NETWORK).configOperation().getMethod());
        assertNotEquals(Optional.empty(), 
                network.create(NETWORK).setDriver("bridge").configOperation().getRequestBody());
        assertEquals(Optional.empty(), network.configOperation().getQueryParams());
        assertEquals(Optional.empty(), network.create(NETWORK).configOperation().getQueryParams());
    }

    @Test
    public void createVolumeTest() {
        final CreationalOp<VolumeCreate> volume = new VolumeCreate();
        assertThrows(IllegalStateException.class, () -> volume.configOperation());
        assertEquals(Method.POST, volume.create(VOLUME).configOperation().getMethod());
        assertNotEquals(Optional.empty(), volume.create(VOLUME).configOperation().getRequestBody());
        assertEquals(Optional.empty(), volume.configOperation().getQueryParams());
        assertEquals(Optional.empty(), volume.create(VOLUME).configOperation().getQueryParams());
    }

    @Test
    public void imagePullTest() {
        final CreationalOp<ImagePull> pull = new ImagePull();
        assertThrows(IllegalStateException.class, () -> pull.configOperation());
        assertEquals(Method.POST, pull.create(IMAGE).configOperation().getMethod());
        assertEquals(Optional.empty(), pull.create(IMAGE).setVersion("3.10").configOperation().getRequestBody());
        assertNotEquals(Optional.empty(), pull.configOperation().getQueryParams());
        assertEquals(2, pull.create(IMAGE).configOperation().getQueryParams().get().size());
    }



}
