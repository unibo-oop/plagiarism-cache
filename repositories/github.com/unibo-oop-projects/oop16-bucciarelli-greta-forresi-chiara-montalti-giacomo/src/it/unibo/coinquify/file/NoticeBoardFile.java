package it.unibo.coinquify.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import it.unibo.coinquify.noticeboard.model.PostIt;

/**
 * Class that save and load Noticeboard files.
 */
public final class NoticeBoardFile {

    private NoticeBoardFile() {
    }

    /**
     * 
     * @param listToSave
     *            the list og postit to save.
     * @throws IOException
     *             if occurs some errors during saving.
     */
    public static void savePostIt(final List<PostIt> listToSave) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(new File(FilePathsConstants.NOTICE_BOARD_POSTIT)));) {
            for (final PostIt post : listToSave) {
                out.writeObject(post);
            }
            out.writeObject(null);
            out.close();
        }
    }

    /**
     * 
     * @return a readed list of postit from file
     * @throws ClassNotFoundException
     *             if class is not loaded
     * @throws IOException
     *             if occurs some problems during reading
     */
    public static List<PostIt> readPostIt() throws ClassNotFoundException, IOException {
        final List<PostIt> list = new ArrayList<>();
        PostIt post;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File(FilePathsConstants.NOTICE_BOARD_POSTIT)));) {
            post = (PostIt) in.readObject();
            while (post != null) {
                if (post.isValid()) {
                    list.add(post);
                }
                post = (PostIt) in.readObject();
            }
            in.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<PostIt>();
        }
    }

    /**
     * 
     * @param listToSave
     *            the list of rules to save
     * @throws IOException
     *             if occurs some problems during saving
     */
    public static void saveRules(final List<String> listToSave) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(new File(FilePathsConstants.NOTICE_BOARD_RULES)));) {
            for (final String rule : listToSave) {
                out.writeObject(rule);
            }
            out.writeObject(null);
            out.close();
        }
    }

    /**
     * 
     * @return a list of rules loaded from file
     * @throws ClassNotFoundException
     *             if class is not loaded
     * @throws IOException
     *             if occurs some problems during loading
     */
    public static List<String> readRules() throws ClassNotFoundException, IOException {
        final List<String> list = new ArrayList<>();
        String rule;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File(FilePathsConstants.NOTICE_BOARD_RULES)));) {

            rule = (String) in.readObject();
            while (rule != null) {
                if (!rule.isEmpty()) {
                    list.add(rule);
                }
                rule = (String) in.readObject();
            }
            in.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }

}
