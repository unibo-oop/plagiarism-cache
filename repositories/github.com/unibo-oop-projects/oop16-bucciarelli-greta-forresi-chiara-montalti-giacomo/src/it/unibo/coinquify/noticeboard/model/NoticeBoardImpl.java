package it.unibo.coinquify.noticeboard.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.coinquify.file.NoticeBoardFile;

/**
 * NoticeBoardController implementation.
 */
public class NoticeBoardImpl implements NoticeBoard {

    private List<String> rules;
    private List<PostIt> postIt;

    /**
     * Default constructor for NoticeboardController.
     * 
     * @throws ClassNotFoundException
     *             if class is not loaded
     * @throws IOException
     *             if occurs some problems with loading of files
     */
    public NoticeBoardImpl() throws ClassNotFoundException, IOException {
        this.postIt = NoticeBoardFile.readPostIt();
        this.rules = NoticeBoardFile.readRules();
    }

    @Override
    public List<PostIt> getPostIt() {
        return this.postIt;
    }

    @Override
    public void addPostIt(final PostIt postIt) {
        this.postIt.add(postIt);
    }

    @Override
    public List<String> getRules() {
        return this.rules;
    }

    @Override
    public void deletePostIt(final PostIt postItToDelete) {
        this.postIt.remove(postItToDelete);

    }

    @Override
    public void editPostIt(final PostIt postIt, final String title, final String body) {
        postIt.editPostIt(title, body);
    }

    @Override
    public void loadRules(final List<String> readRules) {
        final List<String> tempRules = new ArrayList<>();
        for (final String string : readRules) {
            if (!string.isEmpty()) {
                tempRules.add(string);
            }
        }
        this.rules = tempRules;
    }

    @Override
    public void loadPostIt(final List<PostIt> readPostIt) {
        this.postIt = readPostIt;
    }
}
