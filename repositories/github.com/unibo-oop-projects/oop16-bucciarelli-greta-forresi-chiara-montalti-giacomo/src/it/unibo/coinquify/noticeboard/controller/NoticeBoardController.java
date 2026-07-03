package it.unibo.coinquify.noticeboard.controller;

import java.io.IOException;
import java.util.List;

import it.unibo.coinquify.noticeboard.model.PostIt;

/**
 * a NoticeBoard controller manager.
 */
public interface NoticeBoardController {

    /**
     * 
     * @return a list of rules
     */
    List<String> getRules();

    /**
     * 
     * @throws IOException if occurs some problem during saving of rules on a file
     */
    void saveRules() throws IOException;

    /**
     * 
     * @return a list of postIt
     */
    List<PostIt> getPostIt();

    /**
     * 
     * @throws IOException if occurs some problem during saving of postin on a file
     */
    void savePostIt() throws IOException;

    /**
     * 
     * @param postToModify the postIt to modify
     * @param title the new title of postit
     * @param body the new body of postit
     */
    void modifyPostIt(PostIt postToModify, String title, String body);

    /**
     * 
     * @param newRules new list of rules to store
     */
    void updateRules(List<String> newRules);

    /**
     * 
     * @param postToDelete the postIt to remove from list
     */
    void deletePostIt(PostIt postToDelete);

    /**
     * Create a new empty postIt.
     */
    void addNewPost();
}
