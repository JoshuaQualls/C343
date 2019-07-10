import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void insertB(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        assertEquals(new Node(4,
                BinTree.makeLeaf(3),
                new Node(5,
                        new Empty(),
                        BinTree.makeLeaf(13))), test.insertB(13));
        assertEquals(new Node(5,
                new Empty(),
                BinTree.makeLeaf(13)), test2.insertB(13));
        assertEquals(new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                new Node(15,
                        BinTree.makeLeaf(13),
                        new Empty())),test3.insertB(13));

    }

    @Test
    public void deleteB(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        try{
            assertEquals(new Node(4,
                    BinTree.makeLeaf(3),
                    new Empty()), test.deleteB(5));
        }
        catch (EmptyTreeE e){

        }

        try{
            assertEquals(new Empty(), test2.deleteB(5));
        }
        catch (EmptyTreeE e){

        }

        try{
            assertEquals(new Node(10,
                    BinTree.makeLeaf(2),
                    BinTree.makeLeaf(15)), test3.deleteB(5));
        }
        catch (EmptyTreeE e){

        }

    }

    @Test
    public void easyLeft(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        assertEquals(new Node(5,
                new Node(4,
                        BinTree.makeLeaf(3),
                        new Empty()),
                new Empty()),test.easyLeft());
        assertEquals(new Node(15,
                new Node(10,
                        new Node(5,
                                BinTree.makeLeaf(2),
                                new Empty()),
                        new Empty()),
                new Empty()), test3.easyLeft());

    }

    @Test
    public void easyRight(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        assertEquals(new Node(3,
                new Empty(),
                new Node(4,
                        new Empty(),
                        BinTree.makeLeaf(5))),test.easyRight());
        assertEquals(new Node(5,
                BinTree.makeLeaf(2),
                new Node(10,
                        new Empty(),
                        BinTree.makeLeaf(15))),test3.easyRight());

    }

    @Test
    public void rotateLeft(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        assertEquals(new Node(5,
                new Node(4,
                        BinTree.makeLeaf(3),
                        new Empty()),
                new Empty()),test.rotateLeft());
        assertEquals(new Node(15,
                new Node(10,
                        new Node(5,
                                BinTree.makeLeaf(2),
                                new Empty()),
                        new Empty()),
                new Empty()), test3.rotateLeft());

    }

    @Test
    public void rotateRight(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        assertEquals(new Node(3,
                new Empty(),
                new Node(4,
                        new Empty(),
                        BinTree.makeLeaf(5))),test.rotateRight());
        assertEquals(new Node(5,
                BinTree.makeLeaf(2),
                new Node(10,
                        new Empty(),
                        BinTree.makeLeaf(15))),test3.rotateRight());

    }

    @Test
    public void shrink(){
        BinTree test = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

    }
}
