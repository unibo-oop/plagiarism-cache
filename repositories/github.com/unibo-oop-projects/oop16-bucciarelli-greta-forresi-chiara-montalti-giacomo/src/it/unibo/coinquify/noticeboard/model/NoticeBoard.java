package it.unibo.coinquify.noticeboard.model;

import java.util.List;

/**
 * Shared notice board of postit.
 */
public interface NoticeBoard {
    /**
     * 
     * @return the list of all postIts in NoticeBoard
     */
    List<PostIt> getPostIt();

    /**
     * Add a postIt to notice board.
     * 
     * @param postIt
     *            to add
     */
    void addPostIt(PostIt postIt);

    /**
     * 
     * @return a list of rules
     */
    List<String> getRules();

    /**
     * 
     * @param postItToDelete the postIt to remove from noticeboard
     */
    void deletePostIt(PostIt postItToDelete);

    /**
     * 
     * @param postIt the postit to edit
     * @param title the new title postit
     * @param body the new body of postit
     */
    void editPostIt(PostIt postIt, String title, String body);

    /**
     * 
     * @param readRules a list of readed rules 
     */
    void loadRules(List<String> readRules);

    /**
     * 
     * @param readPostIt a list of readed postit
     */
    void loadPostIt(List<PostIt> readPostIt);
}
