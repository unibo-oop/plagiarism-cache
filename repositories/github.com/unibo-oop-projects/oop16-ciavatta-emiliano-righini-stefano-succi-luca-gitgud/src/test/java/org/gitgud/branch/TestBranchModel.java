package org.gitgud.branch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.gitgud.model.branch.BranchError;
import org.gitgud.model.branch.BranchModel;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.TestingUtils;
import org.junit.Test;

public class TestBranchModel {

    private static final BranchModel MODEL = TestingUtils.openTestRepo().getBranchModel();

    @Test
    public void createAndDeleteBranchCommands() {
        assertEquals(CommandStatus.SUCCESS, MODEL.createBranch("fuffo", "HEAD"));
        assertTrue(MODEL.getLocalBranches().contains("fuffo"));
        assertEquals(CommandStatus.SUCCESS, MODEL.deleteBranch(Arrays.asList("fuffo")));
        assertFalse(MODEL.getLocalBranches().contains("fuffo"));

        // now I try to create a branch that already exists
        assertEquals(CommandStatus.ERROR, MODEL.createBranch("master", "HEAD"));
        assertEquals(BranchError.BRANCH_NAME_IN_USE, MODEL.getError());
        // now it's time to try to delete the branch in which we are now
        MODEL.checkoutToLocal("master");
        assertEquals(CommandStatus.ERROR, MODEL.deleteBranch(Arrays.asList("master")));
        assertEquals(BranchError.BRANCH_IN_USE, MODEL.getError());
        // Let's see if i can demolish a non-merged branch
        assertEquals(CommandStatus.ERROR, MODEL.deleteBranch(Arrays.asList("new")));
        assertEquals(BranchError.BRANCH_NOT_MERGED, MODEL.getError());
    }

    @Test
    public void getBranchListCommand() {
        System.out.println("Branch locali:" + MODEL.getLocalBranches().toString());
        System.out.println("Branch remoti:" + MODEL.getRemoteBranches().toString());
    }

    @Test
    public void getCheckedOutBranchCommand() {
        System.out.println("Current branch is: " + MODEL.getCheckedOutBranch());
    }

    @Test
    public void localeBranchCheckoutCommand() {
        if (!MODEL.getLocalBranches().contains("new")) {
            MODEL.checkoutToRemote("refs/remotes/origin/new");
        }
        MODEL.checkoutToLocal("new");
        assertEquals("new", MODEL.getCheckedOutBranch());
        MODEL.checkoutToLocal("master");
        assertEquals("master", MODEL.getCheckedOutBranch());
        // now with a fake branch name
        assertEquals(CommandStatus.ERROR, MODEL.checkoutToLocal("pimp-me"));
    }

    @Test
    public void remoteBranchCheckoutCommand() {
        if (MODEL.getLocalBranches().contains("test")) {
            MODEL.createBranch("fuffa", "test");
            MODEL.checkoutToLocal("fuffa");
            MODEL.deleteBranch(Arrays.asList("test"));
        }
        MODEL.checkoutToRemote("refs/remotes/origin/test");
        assertEquals("test", MODEL.getCheckedOutBranch());
        MODEL.checkoutToLocal("new");
        MODEL.deleteBranch(Arrays.asList("master"));
        MODEL.checkoutToRemote("refs/remotes/origin/master");
        assertEquals(BranchError.BRANCH_ALREADY_EXISTS, MODEL.getError());
    }

    @Test
    public void renameBranchCommand() {
        if (!MODEL.getLocalBranches().contains("test")) {
            MODEL.checkoutToRemote("refs/remotes/origin/test");
        }
        assertEquals(CommandStatus.SUCCESS, MODEL.renameBranch("master", "protozoo"));
        assertTrue(MODEL.getLocalBranches().contains("protozoo"));
        assertEquals(CommandStatus.SUCCESS, MODEL.renameBranch("protozoo", "master"));
        assertTrue(MODEL.getLocalBranches().contains("master"));
        // Now i try to rename a fake branch
        assertEquals(CommandStatus.ERROR, MODEL.renameBranch("fragolino", "protozoo"));
        assertEquals(BranchError.BRANCH_NOT_FOUND, MODEL.getError());
        // Now i choose a name already used
        assertEquals(CommandStatus.ERROR, MODEL.renameBranch("master", "master"));
        assertEquals(BranchError.BRANCH_NAME_IN_USE, MODEL.getError());
    }

}
