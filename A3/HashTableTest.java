import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashTableTest {

    void assertEqualsL (long i, long j) {
        assertEquals(i,j);
    }

    @Test
    public void fig55 () throws TableFullE {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(10, hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        ht.delete(1);
        System.out.println("Fig. 5.5");
        System.out.println(ht);
    }

    @Test
    public void SC1() throws TableFullE{
        HashFunction hf = new HashModThen(13, (h)-> 7 + h);
        HashTable ht = new HashSeparateChaining(20, hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void SC2() throws TableFullE{
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r, 51, 10);
        HashTable ht = new HashSeparateChaining(20, hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void fig511 () throws TableFullE {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(10, hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.11");
        System.out.println(ht);
    }

    @Test
    public void LP1 () throws TableFullE {
        HashFunction hf = new HashModThen(13, (h)-> 7 + h);
        HashTable ht = new HashLinearProbing(20, hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(44);
        ht.insert(16);
        ht.insert(2);
        ht.insert(77);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void LP2 () throws TableFullE {
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r, 51, 10);
        HashTable ht = new HashLinearProbing(20, hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(44);
        ht.insert(16);
        ht.insert(2);
        ht.insert(77);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void fig513 () throws TableFullE {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashQuadProbing (10, hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.13");
        System.out.println(ht);
    }

    @Test
    public void HQP1 () throws TableFullE {
        HashFunction hf = new HashModThen(13, (h)-> 7 + h);
        HashTable ht = new HashQuadProbing (20, hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(44);
        ht.insert(16);
        ht.insert(2);
        ht.insert(77);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void HQP2 () throws TableFullE {
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r, 51, 10);
        HashTable ht = new HashQuadProbing (20, hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(44);
        ht.insert(16);
        ht.insert(2);
        ht.insert(77);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void fig518 () throws TableFullE {
        HashFunction hf = new HashMod(10);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (10, hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.18");
        System.out.println(ht);
    }

    @Test
    public void HD1() throws TableFullE {
        HashFunction hf = new HashMod(30);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (10, hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(44);
        ht.insert(16);
        ht.insert(2);
        ht.insert(77);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

    @Test
    public void HD2() throws TableFullE {
        HashFunction hf = new HashModThen(7, h -> 7 - h);
        Random r = new Random(1);
        HashFunction hf2 = new HashUniversal(r, 51, 10);
        HashTable ht = new HashDouble (12, hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(44);
        ht.insert(16);
        ht.insert(2);
        ht.insert(77);
        ht.insert(1);
        assertTrue(ht.search(1));
        ht.delete(1);
        assertFalse(ht.search(1));
        System.out.println(ht);
    }

}
