
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void emptytree () {
        Random r = new Random();
        Tree<Integer> t1 = Tree.makeTreeNodes(0,2, r, 10);
        Tree<Integer> t2 = Tree.makeTreeNodes(0, 16, r, 20);
        assertEquals(2, t1.getBranchingFactor());
        assertEquals(16, t2.getBranchingFactor());
        assertEquals(0, t1.getHeight());
        assertEquals(0, t2.getHeight());
        assertEquals(0, t1.getNumberOfNodes());
        assertEquals(0, t2.getNumberOfNodes());
        assertTrue(t1.isEmpty());
        assertTrue(t2.isEmpty());
        assertFalse(t1.isLeaf());
        assertFalse(t2.isLeaf());
        assertEquals(0, t1.numberOfInternalNodes());
        assertEquals(0, t2.numberOfInternalNodes());
        assertEquals(0, t1.numberOfLeaves());
        assertEquals(0, t2.numberOfLeaves());
    }

    @Test
    public void leaf () {
        Random r = new Random();
        Tree<Integer> t1 = Tree.makeLeaf(5, 8);
        Tree<Integer> t2 = Tree.makeLeaf(12, 2);
        assertEquals(8, t1.getBranchingFactor());
        assertEquals(2, t2.getBranchingFactor());
        assertEquals(1, t1.getHeight());
        assertEquals(1, t2.getHeight());
        assertEquals(1, t1.getNumberOfNodes());
        assertEquals(1, t2.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t2.isEmpty());
        assertTrue(t1.isLeaf());
        assertTrue(t2.isLeaf());
        assertEquals(0, t1.numberOfInternalNodes());
        assertEquals(0, t2.numberOfInternalNodes());
        assertEquals(1, t1.numberOfLeaves());
        assertEquals(1, t2.numberOfLeaves());
    }


    @Test
    public void tree1 () {
        Random r = new Random();
        Tree<Integer> t1;
        Tree<Integer> t2;

        t1 = Tree.makeTreeNodes(1+3+9+27,3,r,10);
        t2 = Tree.makeTreeNodes(46,5,r,10);
        assertEquals(3, t1.getBranchingFactor());
        assertEquals(5, t2.getBranchingFactor());
        assertEquals(4, t1.getHeight());
        assertEquals(3, t2.getHeight());
        assertEquals(40, t1.getNumberOfNodes());
        assertEquals(31, t2.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t2.isEmpty());
        assertFalse(t1.isLeaf());
        assertFalse(t2.isLeaf());
        assertEquals(13, t1.numberOfInternalNodes());
        assertEquals(6, t2.numberOfInternalNodes());
        assertEquals(27, t1.numberOfLeaves());
        assertEquals(25, t2.numberOfLeaves());

        t1 = Tree.makeTreeInternals(13,3,r,10);
        t2 = Tree.makeTreeInternals(13,5,r,10);
        assertEquals(3, t1.getBranchingFactor());
        assertEquals(5, t2.getBranchingFactor());
        assertEquals(4, t1.getHeight());
        assertEquals(3, t2.getHeight());
        assertEquals(40, t1.getNumberOfNodes());
        assertEquals(31, t2.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t2.isEmpty());
        assertFalse(t1.isLeaf());
        assertFalse(t2.isLeaf());
        assertEquals(13, t1.numberOfInternalNodes());
        assertEquals(6, t2.numberOfInternalNodes());
        assertEquals(27, t1.numberOfLeaves());
        assertEquals(25, t2.numberOfLeaves());

        t1 = Tree.makeTreeLeaves(27,3,r,10);
        t2 = Tree.makeTreeLeaves(20, 5, r, 10);
        assertEquals(3, t1.getBranchingFactor());
        assertEquals(5, t2.getBranchingFactor());
        assertEquals(4, t1.getHeight());
        assertEquals(2, t2.getHeight());
        assertEquals(40, t1.getNumberOfNodes());
        assertEquals(6, t2.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t2.isEmpty());
        assertFalse(t1.isLeaf());
        assertFalse(t2.isLeaf());
        assertEquals(13, t1.numberOfInternalNodes());
        assertEquals(13, t1.numberOfInternalNodes());

    }

}
