package avl_tree;

import org.junit.Test;

import static org.junit.Assert.*;


public class TreeAVLTest {


    @Test
    public void add() {
        TreeAVL tree = new TreeAVL();


        tree.add(10);
        tree.add(3);
        tree.add(5);
        tree.add(2);
        tree.add(15);



        tree.printer.print(tree.getRoot());

        assertEquals(true, tree.contains(15));
        assertEquals(true, tree.contains(10));
        assertEquals(true, tree.contains(5));
        assertEquals(true, tree.contains(2));
        assertEquals(true, tree.contains(3));


        assertEquals(false, tree.contains(100));
        assertEquals(false, tree.contains(228));
        assertEquals(false, tree.contains(666));

        assertEquals(5, tree.NodesList.size());

        tree.remove(5);

        assertEquals(4, tree.NodesList.size());
        assertEquals(false, tree.contains(5));
        assertEquals(true, tree.contains(10));
        assertEquals(true, tree.contains(3));
        assertEquals(true, tree.contains(2));
        assertEquals(true, tree.contains(15));
    }

    @Test
    public void remove() {
        TreeAVL tree1 = new TreeAVL();
        TreeAVL tree2 = new TreeAVL();


        tree1.add(10);
        tree1.add(9);
        tree1.add(8);
        tree1.add(5);
        tree1.add(1);

        assertEquals(true, tree1.remove(8));
        assertEquals(false, tree1.contains(8));
        assertEquals(true, tree1.remove(9));
        assertEquals(false, tree1.contains(9));
        assertEquals(true, tree1.contains(10));
        assertEquals(true, tree1.contains(5));
        assertEquals(true, tree1.contains(1));

        tree2.add(100);
        tree2.add(110);
        tree2.add(120);
        tree2.add(105);

        assertEquals(true, tree2.contains(100));
        assertEquals(true, tree2.contains(110));
        assertEquals(true, tree2.contains(120));
        assertEquals(true, tree2.contains(105));

        assertEquals(true, tree2.remove(110));
        assertEquals(true, tree2.remove(105));
        assertEquals(false, tree2.contains(110));
        assertEquals(false, tree2.contains(105));
        assertEquals(true, tree2.contains(100));
        assertEquals(true, tree2.contains(120));

    }

    @Test
    public void testingEqualsTrees() {
        TreeAVL tree1 = new TreeAVL();
        TreeAVL tree2 = new TreeAVL();

        tree1.add(15);
        tree1.add(10);
        tree1.add(5);
        tree1.add(17);
        tree1.add(16);
        tree1.add(1);
        tree1.add(100);

        tree2.add(15);
        tree2.add(10);
        tree2.add(5);
        tree2.add(17);
        tree2.add(16);
        tree2.add(1);
        tree2.add(100);


        assertEquals(tree1.NodesList, tree2.NodesList);

        tree1.remove(16);
        tree1.add(16);

        for (Object node : tree1.NodesList) {
            assertTrue(tree2.NodesList.contains(node));
        }

        tree2.add(18);
        tree1.add(18);

        assertTrue(tree1.NodesList.containsAll(tree2.NodesList));

        tree1.remove(15);

        assertFalse(tree1.NodesList.containsAll(tree2.NodesList));
    }
}