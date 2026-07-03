package org.gitgud.stage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.gitgud.model.Model;
import org.gitgud.model.repository.RepositoryModel;
import org.gitgud.model.stage.CleanParamBuilder;
import org.gitgud.model.stage.CommitParamBuilder;
import org.gitgud.model.stage.ResetParamBuilder;
import org.gitgud.model.stage.StageError;
import org.gitgud.model.stage.StageModel;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.TestingUtils;
import org.junit.Test;

public class TestStageModel {

    public static final String HOME = System.getProperty("user.home");
    public static final String SLASH = System.getProperty("file.separator");
    public static final String TEST_REPO = HOME + SLASH + "gitgud" + SLASH + "test_repo_one";

    @Test
    public void addCommand() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();

        // add files
        assertEquals(CommandStatus.ERROR, sm.add(new HashSet<>()));
        assertEquals(StageError.MISSING_PATHS, sm.getWarning().get());
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();

        // check staging area
        this.checkStagingArea(sm, untracked, unstaged, staged);
        sm.getStatusStaged().forEach(p -> {
            assertTrue(p.getY().equals(ChangeType.ADDED));
        });

        // reset repository
        this.cleanRepo(sm);
    }

    @Test
    public void modifyFiles() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        // modify files
        staged.forEach(f -> this.writeOnFile(f, "modify_" + f));
        unstaged.addAll(staged);

        // check staging area
        this.checkStagingArea(sm, untracked, unstaged, staged);
        sm.getStatusStaged().forEach(p -> assertTrue(p.getY().equals(ChangeType.ADDED)));
        sm.getStatusNotStaged().forEach(p -> assertTrue(p.getY().equals(ChangeType.MODIFIED)));

        // reset repository
        this.cleanRepo(sm);
    }

    @Test
    public void removeCommand() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        // this.cleanRepo(sm);
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        // modify files
        staged.forEach(f -> this.writeOnFile(f, "modify_0_" + f));
        unstaged.addAll(staged);
        // add files
        untracked.addAll(this.createRandomFiles(2));
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        // remove files from the index
        assertEquals(CommandStatus.ERROR, sm.remove(new HashSet<>(), true));
        assertEquals(StageError.MISSING_PATHS, sm.getWarning().get());
        assertEquals(CommandStatus.SUCCESS, sm.remove(staged, true));
        untracked.addAll(staged);
        staged.clear();
        unstaged.clear();
        // check staging area
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        // remove files from the index and the working tree
        assertEquals(CommandStatus.ERROR, sm.remove(new HashSet<>(), false));
        assertEquals(StageError.MISSING_PATHS, sm.getWarning().get());
        assertEquals(CommandStatus.SUCCESS, sm.remove(staged, false));

        // reset repository
        this.cleanRepo(sm);
    }

    @Test
    public void cleanCommand() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();
        final Set<String> temp = new HashSet<>();

        // clean files
        assertEquals(CommandStatus.SUCCESS,
                sm.clean(CleanParamBuilder.createCleanParamBuilder().paths(untracked).build()));
        untracked.clear();

        // check staging area
        this.checkStagingArea(sm, untracked, unstaged, staged);

        untracked.addAll(this.createRandomFiles(2));
        temp.clear();
        temp.addAll(this.createRandomFiles(2));
        untracked.addAll(temp);

        // clean selected files
        assertEquals(CommandStatus.SUCCESS, sm.clean(CleanParamBuilder.createCleanParamBuilder().paths(temp).build()));
        untracked.removeAll(temp);

        // check staging area
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // reset repository
        this.cleanRepo(sm);
    }

    @Test
    public void resetCommand() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        final RepositoryModel rm = model.getRepositoryModel();
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();
        final Set<String> temp = new HashSet<>();

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // reset files
        assertEquals(CommandStatus.SUCCESS,
                sm.reset(ResetParamBuilder.createResetParamBuilder().paths(staged).build()));
        untracked.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // modify files
        staged.forEach(f -> this.writeOnFile(f, "modify_0_" + f));
        unstaged.addAll(staged);
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(unstaged));
        unstaged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // reset files
        assertEquals(CommandStatus.SUCCESS,
                sm.reset(ResetParamBuilder.createResetParamBuilder().paths(staged).build()));
        untracked.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // modify files
        staged.forEach(f -> this.writeOnFile(f, "modify_1_" + f));
        unstaged.addAll(staged);
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(unstaged));
        unstaged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // commit changes
        final String name = rm.getGlobalIdentity().get().getX();
        final String email = rm.getGlobalIdentity().get().getY();
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test").author(name, email)
                        .committer(name, email).build()));
        temp.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // modify files
        temp.forEach(f -> this.writeOnFile(f, "modify_2_" + f));
        unstaged.addAll(temp);
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(unstaged));
        staged.addAll(unstaged);
        unstaged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // reset files
        assertEquals(CommandStatus.ERROR,
                sm.reset(ResetParamBuilder.createResetParamBuilder().build()));
        assertEquals(StageError.MISSING_PATHS, sm.getWarning().get());
        assertEquals(CommandStatus.SUCCESS,
                sm.reset(ResetParamBuilder.createResetParamBuilder().paths(staged).build()));
        unstaged.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // reset repository
        this.cleanRepo(sm);
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().amend(true).message("test").author(name, email)
                        .committer(name, email).build()));
        this.checkStagingArea(sm, Collections.emptySet(), Collections.emptySet(), Collections.emptySet());
    }

    @Test
    public void commitCommand() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        final RepositoryModel rm = model.getRepositoryModel();
        final String name = rm.getGlobalIdentity().get().getX();
        final String email = rm.getGlobalIdentity().get().getY();
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();
        final Set<String> temp = new HashSet<>();
        final Random r = new Random();

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // commit changes
        assertEquals(CommandStatus.ERROR,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test_A_").build()));
        assertEquals(StageError.MISSING_AUTHOR, sm.getWarning().get());
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test_A_" + r.nextInt())
                        .author(name, email).committer(name, email).build()));
        temp.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // modify files
        temp.forEach(f -> this.writeOnFile(f, "modify_0_" + f));
        unstaged.addAll(temp);
        this.checkStagingArea(sm, untracked, unstaged, staged);
        sm.getStatusNotStaged().forEach(p -> assertEquals(ChangeType.MODIFIED, p.getY()));
        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(unstaged));
        staged.addAll(unstaged);
        unstaged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        sm.getStatusStaged().forEach(p -> assertEquals(ChangeType.MODIFIED, p.getY()));
        untracked.addAll(this.createRandomFiles(2));
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        temp.clear();
        temp.addAll(untracked);
        untracked.clear();
        // modify files
        temp.forEach(f -> this.writeOnFile(f, "modify_1_" + f));
        unstaged.addAll(temp);
        this.checkStagingArea(sm, untracked, unstaged, staged);
        sm.getStatusNotStaged().forEach(p -> assertEquals(ChangeType.MODIFIED, p.getY()));
        // add files
        untracked.addAll(this.createRandomFiles(2));
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // commit changes
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test_B_" + r.nextInt())
                        .author(name, email).committer(name, email).build()));
        assertEquals(CommandStatus.ERROR,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test").author(name, email)
                        .committer(name, email).build()));
        assertEquals(StageError.EMPTY_COMMIT, sm.getWarning().get());
        temp.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(unstaged));
        staged.addAll(unstaged);
        unstaged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        sm.getStatusStaged().forEach(p -> assertEquals(ChangeType.MODIFIED, p.getY()));
        // commit changes
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test_C_" + r.nextInt())
                        .author(name, email).committer(name, email).build()));

        assertEquals(CommandStatus.SUCCESS, sm.remove(staged, false));
        assertEquals(CommandStatus.SUCCESS, sm.remove(temp, false));
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test_D_" + r.nextInt())
                        .author(name, email).committer(name, email).build()));
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
    }

    @Test
    public void amendCommand() {
        final Model model = TestingUtils.openTestRepo();
        final StageModel sm = model.getStageModel();
        final RepositoryModel rm = model.getRepositoryModel();
        final String name = rm.getGlobalIdentity().get().getX();
        final String email = rm.getGlobalIdentity().get().getY();
        final Set<String> untracked = this.createRandomFiles(2);
        final Set<String> unstaged = new HashSet<>();
        final Set<String> staged = new HashSet<>();
        final Set<String> temp = new HashSet<>();
        final Random r = new Random();

        // add files
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // commit changes
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().message("test_commit" + r.nextInt())
                        .author(name, email).committer(name, email).build()));
        temp.addAll(staged);
        staged.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);

        // add files
        untracked.addAll(this.createRandomFiles(2));
        assertEquals(CommandStatus.SUCCESS, sm.add(untracked));
        staged.addAll(untracked);
        untracked.clear();
        this.checkStagingArea(sm, untracked, unstaged, staged);
        // commit changes
        assertEquals(CommandStatus.SUCCESS, sm.commit(
                CommitParamBuilder.createCommitParamBuilder().amend(true).message("amend_A_" + r.nextInt())
                        .author(name, email).committer(name, email).build()));
        temp.addAll(staged);
        staged.clear();
        assertEquals(CommandStatus.SUCCESS, sm.remove(temp, false));
        assertEquals(CommandStatus.SUCCESS,
                sm.commit(CommitParamBuilder.createCommitParamBuilder().amend(true).message("amend_B_" + r.nextInt())
                        .author(name, email).committer(name, email).build()));
        this.checkStagingArea(sm, untracked, unstaged, staged);
    }

    private void cleanRepo(final StageModel sm) {
        final Set<String> paths = sm.getStatusStaged().stream().map(p -> p.getX()).collect(Collectors.toSet());
        paths.addAll(sm.getStatusNotStaged().stream().map(p -> p.getX()).collect(Collectors.toSet()));
        sm.remove(paths, false);
        sm.clean(CleanParamBuilder.createCleanParamBuilder()
                .paths(sm.getStatusUntracked().stream().map(p -> p.getX()).collect(Collectors.toSet())).build());
    }

    private boolean createFile(final String fileName) {
        try {
            final Path path = Paths.get(TEST_REPO + SLASH + fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
                return true;
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Set<String> createRandomFiles(final int n) {
        final Set<String> l = new HashSet<>();
        final Random r = new Random();
        for (int i = 0; i < n; i++) {
            final String s = "file_" + String.valueOf(r.nextInt()) + ".txt";
            if (this.createFile(s)) {
                l.add(s);
            } else {
                i--;
            }
        }
        return l;
    }

    private void writeOnFile(final String fileName, final String text) {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(TEST_REPO + SLASH + fileName)))) {
            dos.writeChars(text);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e1) {
            e1.printStackTrace();
        }
    }

    private void checkStagingArea(final StageModel sm, final Set<String> untracked, final Set<String> unstaged,
            final Set<String> staged) {
        Set<String> temp = new HashSet<>();
        temp = sm.getStatusUntracked().stream().map(p -> p.getX()).collect(Collectors.toSet());
        assertTrue(untracked.containsAll(temp) && untracked.size() == temp.size());
        temp = sm.getStatusNotStaged().stream().map(p -> p.getX()).collect(Collectors.toSet());
        assertTrue(unstaged.containsAll(temp) && unstaged.size() == temp.size());
        temp = sm.getStatusStaged().stream().map(p -> p.getX()).collect(Collectors.toSet());
        assertTrue(staged.containsAll(temp) && staged.size() == temp.size());
    }

    @SuppressWarnings("unused")
    private void printStage(final StageModel sm) {
        System.out.println("Untracked");
        System.out.println(sm.getStatusUntracked().stream().map(p -> p.getX()).collect(Collectors.toSet()));
        System.out.println("Unstaged");
        System.out.println(sm.getStatusNotStaged().stream().map(p -> p.getX()).collect(Collectors.toSet()));
        System.out.println("Staged");
        System.out.println(sm.getStatusStaged().stream().map(p -> p.getX()).collect(Collectors.toSet()));
    }
}
