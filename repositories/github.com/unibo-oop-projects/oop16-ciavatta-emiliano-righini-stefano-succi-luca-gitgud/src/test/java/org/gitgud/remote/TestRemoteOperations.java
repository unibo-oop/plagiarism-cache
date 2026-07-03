package org.gitgud.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.gitgud.model.remote.RemoteError;
import org.gitgud.model.remote.RemoteOperation;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;
import org.gitgud.utils.TestingUtils;
import org.junit.Test;

public class TestRemoteOperations {

    private static final RemoteOperation MODEL = TestingUtils.openTestRepo().getRemoteModel().getOperations();

    @Test
    public void createAndDeleteRemoteTest() {
        MODEL.removeRemote("foo");

        assertEquals(CommandStatus.SUCCESS, MODEL.addRemote("foo", "https://git.foo.com/foo.git"));
        assertTrue(MODEL.getRemotes().containsKey("foo"));
        assertTrue(MODEL.getRemotes().get("foo")
                .equals(new Pair<>("https://git.foo.com/foo.git", "https://git.foo.com/foo.git")));

        assertEquals(CommandStatus.ERROR, MODEL.addRemote("foo", "https://git.foo.com/foo.git"));
        assertEquals(RemoteError.NAME_ALREADY_EXISTS, MODEL.getError());

        assertEquals(CommandStatus.ERROR, MODEL.addRemote("", "https://git.nope.com/nope.git"));
        assertEquals(RemoteError.NAME_EMPTY, MODEL.getError());

        assertEquals(CommandStatus.SUCCESS, MODEL.removeRemote("foo"));
        assertEquals(CommandStatus.ERROR, MODEL.removeRemote("foo"));
        assertEquals(RemoteError.NAME_INEXISTENT, MODEL.getError());
    }

    @Test
    public void editRemoteTest() {
        MODEL.removeRemote("foo");
        MODEL.removeRemote("bar");

        assertEquals(CommandStatus.SUCCESS, MODEL.addRemote("foo", "https://git.foo.com/foo.git"));
        assertEquals(CommandStatus.SUCCESS, MODEL.editRemote("foo", "https://git.bar.com/bar.git"));
        assertTrue(MODEL.getRemotes().get("foo")
                .equals(new Pair<>("https://git.bar.com/bar.git", "https://git.bar.com/bar.git")));

        assertEquals(CommandStatus.ERROR, MODEL.editRemote("bar", "https://git.bar.com/bar.git"));
        assertEquals(RemoteError.NAME_INEXISTENT, MODEL.getError());

        assertEquals(CommandStatus.ERROR, MODEL.editRemote("", "https://git.nope.com/nope.git"));
        assertEquals(RemoteError.NAME_EMPTY, MODEL.getError());

        assertEquals(CommandStatus.SUCCESS, MODEL.removeRemote("foo"));
    }

}
