import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.Assert.*;


public class Tests {

    @Test
    public void Rect(){
        Rect r = new Rect(0, 0, 10, 10);
        Rect aboveOf = new Rect(0,5,10, 10);
        Rect underOf = new Rect(0, 0, 10, 5);
        Rect leftOf = new Rect(0, 0, 5, 10);
        Rect rightOf = new Rect(5, 0 , 10 , 10);
        Rect outside = new Rect(-10, -10, -1, -1);
        Point p = new Point(5,5);
        Point x = new Point(-4, 5);

        assertEquals(aboveOf, r.aboveOf(p));
        assertEquals(underOf, r.underOf(p));
        assertEquals(leftOf, r.leftOf(p));
        assertEquals(rightOf, r.rightOf(p));
        assertTrue(r.contains(p));
        assertFalse(r.contains(x));
        assertTrue(r.intersect(aboveOf));
        assertTrue(r.intersect(underOf));
        assertFalse(r.intersect(outside));

        Rect test = new Rect(0, 0, 10, 12);
        Point above = new Point(3, 14);
        Point below = new Point(3, -5);
        Point left = new Point(-2, 5);
        Point right = new Point(13, 9);
        Point upperLeft = new Point(-3, 13);
        Point lowerLeft = new Point(-3, -4);
        Point upperRight = new Point(12, 14);
        Point lowerRight = new Point(15, -10);

        assertEquals(4, test.distanceSquaredTo(above));
        assertEquals(25, test.distanceSquaredTo(below));
        assertEquals(4, test.distanceSquaredTo(left));
        assertEquals(9, test.distanceSquaredTo(right));
        assertEquals(10, test.distanceSquaredTo(upperLeft));
        assertEquals(25,test.distanceSquaredTo(lowerLeft));
        assertEquals(8, test.distanceSquaredTo(upperRight));
        assertEquals(125, test.distanceSquaredTo(lowerRight));
    }

    @Test
    public void KDTree(){
        XTree t = new XEmpty();
        t = t.insert(new Point(7, 2)).insert(new Point(5, 4)).insert(new Point(2, 3)).
                insert(new Point(4, 7)).insert(new Point(9, 6));

        LinkedHashSet<Point> set = new LinkedHashSet<>();
        set.add(new Point(7,2));
        set.add(new Point(9,6));
        set.add(new Point(5,4));
        set.add(new Point(4,7));
        set.add(new Point(2,3));

        HashSet<Point> p = new HashSet<>();
        p.add(new Point(4,5));
        HashSet<Point> x = new HashSet<>();
        x.add(new Point(4,5));

        Rect region = new Rect(0, 0, 10, 10);
        Rect range = new Rect(0, 0, 3, 4);

        Point point = new Point(5, 5);
        Point point2 = new Point(7,3);

        try{
            assertEquals(new Point(5,4),t.nearestNeighbor(point, region, new HashSet<>()));
        }
        catch (EmptyTreeE e){

        }
        try{
            assertEquals(new Point(7,2),t.nearestNeighbor(point2, region, new HashSet<>()));
        }
        catch (EmptyTreeE e){

        };


        //assertEquals(true, x.containsAll(p));

    }
}
