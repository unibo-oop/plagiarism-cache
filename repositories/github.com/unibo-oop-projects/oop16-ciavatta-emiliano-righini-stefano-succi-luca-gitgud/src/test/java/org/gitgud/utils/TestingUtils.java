package org.gitgud.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.gitgud.model.Model;
import org.gitgud.model.ModelImpl;
import org.gitgud.model.repository.CloneParam;
import org.gitgud.model.repository.OpenParam;
import org.gitgud.model.repository.RepositoryFactory;
import org.gitgud.model.repository.RepositoryModel;

public final class TestingUtils {

    public static final String HOME = System.getProperty("user.home");
    public static final String SLASH = System.getProperty("file.separator");
    public static final String TEST_REPO = HOME + SLASH + "gitgud" + SLASH + "test_repo_one";

    private TestingUtils() {
    }

    /**
     * You can find test_repo folder in your home directory.
     */
    public static Model openTestRepo() {
        final Model model = new ModelImpl();
        final RepositoryModel repoModel = model.getRepositoryModel();
        if (!Files.isDirectory(Paths.get(TEST_REPO))) {
            final CloneParam cloneParam = RepositoryFactory.createCloneBuilder()
                    .directory(TEST_REPO)
                    .remote("https://bitbucket.org/Leotichidas/test_repo_one.git")
                    .build();

            repoModel.cloneRepository(cloneParam);
        } else {
            final OpenParam openParam = RepositoryFactory.createOpenBuilder()
                    .directory(TEST_REPO)
                    .build();

            repoModel.openRepository(openParam);
        }
        return model;
    }

    /*
     * public static String generateRepositoryPath(final String repositoryPrefix) { return getTestPath() +
     * repositoryPrefix + "_" + generateRandomString(8); } private static String generateRandomString(final int
     * numChars) { final Random r = new Random(); final StringBuffer sb = new StringBuffer(); while (sb.length() <
     * numChars) { sb.append(Integer.toHexString(r.nextInt())); } return sb.toString().substring(0, numChars); }
     */

}
