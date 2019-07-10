import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void nodeCollectionQueue(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        NodeCollection<String> ncoll =  NodeCollection.make(KIND.QUEUE);


        //ncoll should be empty when initialized
        assertTrue(ncoll.isEmpty());

        //set a as the source for nodes
        //ncoll should no longer be empty
        ncoll.setSource(nodes, a);
        assertFalse(ncoll.isEmpty());

        //extract from ncoll. The only element should be the source, 'a'
        assertEquals(a, ncoll.extract());
        assertTrue(ncoll.isEmpty());

        //becuse this instance of ncoll is a queue, 'a' should be the second item returned
        ncoll.insert(b);
        ncoll.insert(a);
        assertEquals(b, ncoll.extract());
        assertEquals(a, ncoll.extract());
    }

    @Test
    public void nodeCollectionStack(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        NodeCollection<String> ncoll =  NodeCollection.make(KIND.STACK);


        //ncoll should be empty when initialized
        assertTrue(ncoll.isEmpty());

        //set a as the source for nodes
        //ncoll should no longer be empty
        ncoll.setSource(nodes, a);
        assertFalse(ncoll.isEmpty());

        //extract from ncoll. The only element should be the source, 'a'
        assertEquals(a, ncoll.extract());
        assertTrue(ncoll.isEmpty());

        //becuse this instance of ncoll is a stack, 'b' should be the second item returned
        ncoll.insert(b);
        ncoll.insert(a);
        assertEquals(a, ncoll.extract());
        assertEquals(b, ncoll.extract());
    }

    @Test
    public void nodeCollectionBinHeap(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        NodeCollection<String> ncoll =  NodeCollection.make(KIND.BINARY_HEAP);


        //ncoll should be empty when initialized
        assertTrue(ncoll.isEmpty());

        //set a as the source for nodes
        //ncoll should no longer be empty
        ncoll.setSource(nodes, a);
        assertFalse(ncoll.isEmpty());

        //extract from ncoll. The only element should be the source, 'a'
        //ncoll should NOT be empty after extraction of the source
        assertEquals(a, ncoll.extract());
        assertFalse(ncoll.isEmpty());

        //all items are of value 0
        //becuse this instance of ncoll is a binary heap, 'a'should be the second item returned
        ncoll= NodeCollection.make(KIND.BINARY_HEAP);
        ncoll.insert(a);
        ncoll.insert(b);
        assertEquals(a, ncoll.extract());
        assertEquals(b, ncoll.extract());

        //'a' is originally the first item, but is the second item after we update the values
        //for 'a' and 'b' to 5 and 2, respectively
        ncoll =  NodeCollection.make(KIND.BINARY_HEAP);
        ncoll.setSource(nodes, a);
        ncoll.updateKey(1, 5);
        ncoll.updateKey(2, 2);
        assertEquals(b, ncoll.extract());
        assertEquals(a, ncoll.extract());
    }

    @Test
    public void nodeCollectionDHeap(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        NodeCollection<String> ncoll =  NodeCollection.make(KIND.DHEAP_3);


        //ncoll should be empty when initialized
        assertTrue(ncoll.isEmpty());

        //set a as the source for nodes
        //ncoll should no longer be empty
        ncoll.setSource(nodes, a);
        assertFalse(ncoll.isEmpty());

        //extract from ncoll. The only element should be the source, 'a'
        //ncoll should NOT be empty after extraction of the source
        assertEquals(a, ncoll.extract());
        assertFalse(ncoll.isEmpty());

        //all items are of value 0
        //becuse this instance of ncoll is a D heap, 'a' should be the first item returned
        ncoll= NodeCollection.make(KIND.DHEAP_3);
        ncoll.insert(b);
        ncoll.insert(a);
        assertEquals(a, ncoll.extract());
        assertEquals(b, ncoll.extract());

        //'a' is originally the first item, but is the second item after we update the values
        //for 'a' and 'b' to 5 and 2, respectively
        ncoll= NodeCollection.make(KIND.DHEAP_3);
        ncoll.setSource(nodes, a);
        ncoll.updateKey(1, 5);
        ncoll.updateKey(2, 2);
        assertEquals(b, ncoll.extract());
        assertEquals(a, ncoll.extract());
    }

    @Test
    public void DFS(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Graph<String> g = new DFS<>(neighbors);

        g.setSource(nodes, a);
        g.collect();
        assertEquals("a[0]c[0]b[0]e[0]d[0]", g.output);

        Item<String> zero, one ,two, three, four;

        zero = new Item<>("0", "0", 0);
        one = new Item<>("1", "1", 0);
        two = new Item<>("2", "2", 0);
        three = new Item<>("3", "3", 0);
        four = new Item<>("4", "4", 0);

        nodes = new ArrayList<>(Arrays.asList(zero, one, two, three, four));

        neighbors = new Hashtable<>();

        neighbors.put(zero, new ArrayList<>(Arrays.asList(one, two, three, four)));
        neighbors.put(one, new ArrayList<>(Arrays.asList(two ,three)));
        neighbors.put(two, new ArrayList<>(Arrays.asList(one, three)));
        neighbors.put(three, new ArrayList<>(Arrays.asList(two, four)));
        neighbors.put(four, new ArrayList<>(Arrays.asList(two, three)));



        g = new DFS<>(neighbors);
        g.setSource(nodes, zero);
        g.collect();
        assertEquals("0[0]4[0]3[0]2[0]1[0]", g.output);
    }

    @Test
    public void BFS(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Graph<String> g = new BFS<>(neighbors);

        g.setSource(nodes, a);
        g.collect();
        assertEquals("a[0]b[0]c[0]d[0]e[0]", g.output);

        Item<String> zero, one ,two, three, four;

        zero = new Item<>("0", "0", 0);
        one = new Item<>("1", "1", 0);
        two = new Item<>("2", "2", 0);
        three = new Item<>("3", "3", 0);
        four = new Item<>("4", "4", 0);

        nodes = new ArrayList<>(Arrays.asList(zero, one, two, three, four));

        neighbors = new Hashtable<>();

        neighbors.put(zero, new ArrayList<>(Arrays.asList(one, two, three, four)));
        neighbors.put(one, new ArrayList<>(Arrays.asList(two ,three)));
        neighbors.put(two, new ArrayList<>(Arrays.asList(one, three)));
        neighbors.put(three, new ArrayList<>(Arrays.asList(two, four)));
        neighbors.put(four, new ArrayList<>(Arrays.asList(two, three)));
        
        g = new BFS<>(neighbors);
        g.setSource(nodes, zero);
        g.collect();
        assertEquals("0[0]1[0]2[0]3[0]4[0]", g.output);
    }

    @Test
    public void SP(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        Graph<String> g = new SP<>(neighbors, weights);

        g.setSource(nodes, a);
        g.collect();
        assertEquals("a[0]b[2]c[2]d[4]e[4]", g.output);

        Item<String> zero, one ,two, three, four;

        zero = new Item<>("0", "0", 0);
        one = new Item<>("1", "1", 0);
        two = new Item<>("2", "2", 0);
        three = new Item<>("3", "3", 0);
        four = new Item<>("4", "4", 0);

        nodes = new ArrayList<>(Arrays.asList(zero, one, two, three, four));

        neighbors = new Hashtable<>();

        neighbors.put(zero, new ArrayList<>(Arrays.asList(one, two, three, four)));
        neighbors.put(one, new ArrayList<>(Arrays.asList(two ,three)));
        neighbors.put(two, new ArrayList<>(Arrays.asList(one, three)));
        neighbors.put(three, new ArrayList<>(Arrays.asList(two, four)));
        neighbors.put(four, new ArrayList<>(Arrays.asList(two, three)));


        weights = new Hashtable<>();

        weights.put(new Pair<>(zero, one), 20);
        weights.put(new Pair<>(zero, two), 15);
        weights.put(new Pair<>(zero, three), 4);
        weights.put(new Pair<>(zero,four), 10);
        weights.put(new Pair<>(one, two), 28);
        weights.put(new Pair<>(one, three), 3);
        weights.put(new Pair<>(two, one), 5);
        weights.put(new Pair<>(two, three), 8);
        weights.put(new Pair<>(three, four), 20);
        weights.put(new Pair<>(three, two), 4);
        weights.put(new Pair<>(four, two), 7);
        weights.put(new Pair<>(four, three),  30);

        g = new SP<>(neighbors, weights);
        g.setSource(nodes, zero);
        g.collect();
        assertEquals("0[0]3[4]2[8]4[10]1[13]", g.output);
    }

    @Test
    public void SPD(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        Graph<String> g = new SPD<>(neighbors, weights);

        g.setSource(nodes, a);
        g.collect();
        assertEquals("a[0]b[2]c[2]d[4]e[4]", g.output);

        Item<String> zero, one ,two, three, four;

        zero = new Item<>("0", "0", 0);
        one = new Item<>("1", "1", 0);
        two = new Item<>("2", "2", 0);
        three = new Item<>("3", "3", 0);
        four = new Item<>("4", "4", 0);

        nodes = new ArrayList<>(Arrays.asList(zero, one, two, three, four));

        neighbors = new Hashtable<>();

        neighbors.put(zero, new ArrayList<>(Arrays.asList(one, two, three, four)));
        neighbors.put(one, new ArrayList<>(Arrays.asList(two ,three)));
        neighbors.put(two, new ArrayList<>(Arrays.asList(one, three)));
        neighbors.put(three, new ArrayList<>(Arrays.asList(two, four)));
        neighbors.put(four, new ArrayList<>(Arrays.asList(two, three)));


        weights = new Hashtable<>();

        weights.put(new Pair<>(zero, one), 20);
        weights.put(new Pair<>(zero, two), 15);
        weights.put(new Pair<>(zero, three), 4);
        weights.put(new Pair<>(zero,four), 10);
        weights.put(new Pair<>(one, two), 28);
        weights.put(new Pair<>(one, three), 3);
        weights.put(new Pair<>(two, one), 5);
        weights.put(new Pair<>(two, three), 8);
        weights.put(new Pair<>(three, four), 20);
        weights.put(new Pair<>(three, two), 4);
        weights.put(new Pair<>(four, two), 7);
        weights.put(new Pair<>(four, three),  30);

        g = new SPD<>(neighbors, weights);
        g.setSource(nodes, zero);
        g.collect();
        assertEquals("0[0]3[4]2[8]4[10]1[13]", g.output);
    }

    @Test
    public void MSP(){
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        Graph<String> G = new MSP<>(neighbors, weights);

        G.setSource(nodes, a);
        G.collect();
        assertEquals("a[0]b[2]c[1]d[2]e[1]", G.output);

        Item<String> zero, one ,two, three, four;

        zero = new Item<>("0", "0", 0);
        one = new Item<>("1", "1", 0);
        two = new Item<>("2", "2", 0);
        three = new Item<>("3", "3", 0);
        four = new Item<>("4", "4", 0);

        nodes = new ArrayList<>(Arrays.asList(zero, one, two, three, four));

        neighbors = new Hashtable<>();

        neighbors.put(zero, new ArrayList<>(Arrays.asList(one, two, three, four)));
        neighbors.put(one, new ArrayList<>(Arrays.asList(two ,three)));
        neighbors.put(two, new ArrayList<>(Arrays.asList(one, three)));
        neighbors.put(three, new ArrayList<>(Arrays.asList(two, four)));
        neighbors.put(four, new ArrayList<>(Arrays.asList(two, three)));


        weights = new Hashtable<>();

        weights.put(new Pair<>(zero, one), 20);
        weights.put(new Pair<>(zero, two), 15);
        weights.put(new Pair<>(zero, three), 4);
        weights.put(new Pair<>(zero,four), 10);
        weights.put(new Pair<>(one, two), 28);
        weights.put(new Pair<>(one, three), 3);
        weights.put(new Pair<>(two, one), 5);
        weights.put(new Pair<>(two, three), 8);
        weights.put(new Pair<>(three, four), 20);
        weights.put(new Pair<>(three, two), 4);
        weights.put(new Pair<>(four, two), 7);
        weights.put(new Pair<>(four, three),  30);

        Graph<String> g = new MSP<>(neighbors, weights);
        g.setSource(nodes, zero);
        g.collect();
       assertEquals("0[0]3[4]2[4]1[5]4[10]", g.output);
    }

}
