package it.unibo.coinquify.noticeboard.controller;

import java.io.IOException;
import java.util.List;

import it.unibo.coinquify.file.NoticeBoardFile;
import it.unibo.coinquify.noticeboard.model.NoticeBoard;
import it.unibo.coinquify.noticeboard.model.NoticeBoardImpl;
import it.unibo.coinquify.noticeboard.model.PostIt;
import it.unibo.coinquify.noticeboard.model.PostItImpl;

/**
 * Controller for NoticeBoard.
 */
public class NoticeBoardControllerImpl implements NoticeBoardController {

    private final NoticeBoard model;

    /**
     * Costructor for Controller of NoticeBoard.
     * 
     * @throws ClassNotFoundException
     *             if there's no PostIt class loaded
     * @throws IOException
     *             if occurs some problems with saving or loading of files
     */
    public NoticeBoardControllerImpl() throws ClassNotFoundException, IOException {
        this.model = new NoticeBoardImpl();
        this.model.loadRules(NoticeBoardFile.readRules());
        this.model.loadPostIt(NoticeBoardFile.readPostIt());
    }

    @Override
    public List<String> getRules() {
        return this.model.getRules();
    }

    @Override
    public void saveRules() throws IOException {
        NoticeBoardFile.saveRules(this.model.getRules());
    }

    @Override
    public List<PostIt> getPostIt() {
        return this.model.getPostIt();
    }

    @Override
    public void savePostIt() throws IOException {
        NoticeBoardFile.savePostIt(this.model.getPostIt());
    }

    @Override
    public void modifyPostIt(final PostIt postToModify, final String title, final String body) {
        this.model.editPostIt(postToModify, title, body);
    }

    @Override
    public void deletePostIt(final PostIt postToDelete) {
        this.model.deletePostIt(postToDelete);
    }

    @Override
    public void addNewPost() {
        this.model.addPostIt(new PostItImpl("Nuovo PostIt", "Testo del PostIt"));
    }

    @Override
    public void updateRules(final List<String> newRules) {
        this.model.loadRules(newRules);
    }
}
