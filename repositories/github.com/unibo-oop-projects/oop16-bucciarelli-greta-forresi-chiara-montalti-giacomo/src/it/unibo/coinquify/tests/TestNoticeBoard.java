package it.unibo.coinquify.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.unibo.coinquify.noticeboard.model.NoticeBoard;
import it.unibo.coinquify.noticeboard.model.NoticeBoardImpl;
import it.unibo.coinquify.noticeboard.model.PostIt;
import it.unibo.coinquify.noticeboard.model.PostItImpl;

//CHECKSTYLE:OFF
public class TestNoticeBoard {

    @Test
    public void testPostIt() throws ClassNotFoundException, IOException {
        final NoticeBoard bacheca = new NoticeBoardImpl();
        final int size = bacheca.getPostIt().size();
        final PostIt post = new PostItImpl("Titolo", "Corpo iniziale");
        bacheca.addPostIt(post);
        bacheca.editPostIt(post, "nuovo titolo", "Corpo modificato");
        assertTrue(post.getTitle().equals("nuovo titolo"));
        assertTrue(post.getBody().equals("Corpo modificato"));
        assertEquals(bacheca.getPostIt().size(), size + 1);
        bacheca.deletePostIt(post);
        assertEquals(bacheca.getPostIt().size(), size + 0);
        bacheca.addPostIt(post);
        /*
         * Try to edit a postit, but with an empty title
         */
        try {
            bacheca.editPostIt(post, "", "Corpo");
        } catch (Exception e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }

        final List<String> rules = Arrays.asList("a", "b", "c", "", "d");
        bacheca.loadRules(rules);
        assertEquals(bacheca.getRules().size(), 4);
    }
}
