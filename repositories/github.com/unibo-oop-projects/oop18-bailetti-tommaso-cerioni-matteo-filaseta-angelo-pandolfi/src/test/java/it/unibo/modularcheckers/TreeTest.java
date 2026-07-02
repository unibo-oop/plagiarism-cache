package it.unibo.modularcheckers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;
import it.unibo.modularcheckers.model.move.TreeImpl;

/**
 * Test class for Tree.
 * CHECKSTYLE: MagicNumber OFF
 */
public class TreeTest {

    /**
     * Testing one level tree functionality.
     */
    @Test
    public void testOneLevelTree() {
        final Tree<Integer> t1 = new TreeImpl<Integer>(5);
        assertEquals("Root value is changed", 5, t1.getRoot().intValue());
        assertEquals("Childrens default must be empty", 0, t1.getChildren().size());
        assertEquals("First Childrens must be empty", 0, t1.getFirstChildren().size());
        assertEquals("Height of one level tree must be 1", 1, t1.height());

      //testing all nodes list
        List<Integer> allNodes = t1.getAllValues();
        assertEquals("nodes number must be 1", 1, allNodes.size());
        assertTrue("nodes not contain the right element", allNodes.contains(5));
    }

    /**
     * Testing multiple levels tree functionality.
     */
    @Test
    public void testMultipleLevelsTree() {
        final Tree<String> t = initMultipleLevelTree();
        assertEquals("Root value is changed", "A", t.getRoot());
        assertEquals("First childrens of A must be 3", 3, t.getFirstChildren().size());
        assertTrue("First childrens of A must contain AA", t.getFirstChildren().contains("AA"));
        final List<Tree<String>> childrens = t.getChildren();
        assertEquals("The childrend of A must be 3", 3, childrens.size());
        final Tree<String> aa = childrens.get(0);
        assertEquals("The first children of A must be AA", "AA", aa.getRoot());
        assertEquals("AA should not contain childrens", aa.getChildren().size(), 0);

        final Tree<String> ab = childrens.get(1);
        assertEquals("The second children of A must be AB", "AB", ab.getRoot());
        assertEquals("AB should contain 3 childrens", 3, ab.getChildren().size());

        final Tree<String> ac = childrens.get(2);
        assertEquals("The third children of A must be AC", "AC", ac.getRoot());
        assertEquals("AC should contain 1 children", 1, ac.getChildren().size());

        //testing height
        assertEquals("Tree height must be 3",  3, t.height());

        //testing all nodes list
        List<String> allNodes = t.getAllValues();
        assertEquals("nodes number must be 8", 8, allNodes.size());
        assertTrue("nodes not contains one or more elements that should contains", allNodes.containsAll(Arrays.asList("A", "AA", "AB", "ABA", "ABB", "ABC", "AC", "ACA")));
    }

    /**
     * Testing the balanceToHeight method.
     */
    @Test
    public void testBalanceToHeight() {
        Tree<String> t = initMultipleLevelTree();
        Tree<String> aba = t.getAllNodes().stream().filter(c -> c.getRoot().equals("ABA")).findFirst().get();
        aba.getChildren().add(new TreeImpl<String>("ABAA"));

        t.balanceToHeight(3);
        assertEquals("Nodes number must be 8", 8, t.getAllNodes().size());
        assertEquals("Tree height must be 4", 4, t.height());

        t.balanceToHeight(4);
        assertEquals("Nodes number must be 4", 4, t.getAllNodes().size());
        assertEquals("Tree height must be 4", 4, t.height());
    }

    /**
     * Initialize a tree like this.
     * A (root)
     *     -AA
     *     -AB
     *             -ABA
     *             -ABB
     *             -ABC
     *     -AC
     *             -ACA 
     *
     * @return the multilevel tree
     */
    private Tree<String> initMultipleLevelTree() {
        List<Tree<String>> childrens;

        final Tree<String> aa = new TreeImpl<String>("AA");

        final Tree<String> aba = new TreeImpl<String>("ABA");
        final Tree<String> abb = new TreeImpl<String>("ABB");
        final Tree<String> abc = new TreeImpl<String>("ABC");
        childrens = new ArrayList<Tree<String>>();
        childrens.add(aba);
        childrens.add(abb);
        childrens.add(abc);
        final Tree<String> ab = new TreeImpl<String>("AB", childrens);

        final Tree<String> aca = new TreeImpl<String>("ACA");
        childrens = new ArrayList<Tree<String>>();
        childrens.add(aca);
        final Tree<String> ac = new TreeImpl<String>("AC", childrens);

        childrens = new ArrayList<Tree<String>>();
        childrens.add(aa);
        childrens.add(ab);
        childrens.add(ac);
        return new TreeImpl<String>("A", childrens);
    }
}
