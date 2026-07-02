package jwhale.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import jwhale.model.engine.Method;
import jwhale.model.engine.Operation;
import jwhale.model.engine.operations.object.ContainerPlayback;
import jwhale.model.engine.operations.object.ContainerRemove;
import jwhale.model.engine.operations.object.ContainerRename;
import jwhale.model.engine.operations.object.ImageDelete;
import jwhale.model.engine.operations.object.NetworkRemove;
import jwhale.model.engine.operations.object.ObjectOp;
import jwhale.model.engine.operations.object.Playback;
import jwhale.model.engine.operations.object.VolumeRemove;

public class ObjectOperationTest {

    @Test
    public void playbackContainerTest() {
        final Operation samplePlayBack = new ContainerPlayback()
                .target("test")
                .setOperation(Playback.RESTART)
                .configOperation();
        assertThrows(IllegalStateException.class, () -> new ContainerPlayback().configOperation());
        assertEquals(Method.POST, samplePlayBack.getMethod());
        assertNotEquals(Optional.empty(), samplePlayBack.getLastParam());
        assertEquals(Optional.empty(), samplePlayBack.getQueryParams());
    }

    @Test
    public void removeContainerTest() {
        final Operation sampleRemove = new ContainerRemove()
                .target("test")
                .configOperation();
        assertThrows(IllegalStateException.class, () -> new ContainerRemove().configOperation());
        assertNotEquals(Optional.empty(), sampleRemove.getLastParam());
        assertEquals(Optional.empty(), sampleRemove.getQueryParams());
        assertEquals(Method.DELETE, sampleRemove.getMethod());
    }

    @Test
    public void renameContainerTest() {
        final ObjectOp<ContainerRename> sampleRename = new ContainerRename()
                .target("test").setNewName("newName");
        assertThrows(IllegalStateException.class, () -> new ContainerRename().configOperation());
        assertNotEquals(Optional.empty(), sampleRename.configOperation().getPathParam());
        assertEquals(Method.POST, sampleRename.configOperation().getMethod());
    }

    @Test
    public void imageDeleteTest() {
        final ObjectOp<ImageDelete> sampleImage = new ImageDelete().target("imageName");
        assertThrows(IllegalStateException.class, () -> new ImageDelete().configOperation());
        assertEquals(Method.DELETE, sampleImage.configOperation().getMethod());
        assertEquals(Optional.empty(), sampleImage.configOperation().getPathParam());
    }

    @Test
    public void networkRemoveTest() {
        final ObjectOp<NetworkRemove> sampleNetwork = new NetworkRemove().target("imageName");
        assertThrows(IllegalStateException.class, () -> new NetworkRemove().configOperation());
        assertNotEquals(Method.GET, sampleNetwork.configOperation().getMethod());
        assertNotEquals(Optional.empty(), sampleNetwork.configOperation().getPathParam());
    }

    @Test
    public void volumeRemoveTest() {
        final Operation sampleVolume = new VolumeRemove().target("volumeName").configOperation();
        assertEquals(Method.DELETE, sampleVolume.getMethod());
        assertNotEquals(Optional.empty(), sampleVolume.getPathParam());
    }
}
