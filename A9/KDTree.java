import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

class EmptyTreeE extends Exception {
}

abstract class XTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract Point getPoint() throws EmptyTreeE;

    abstract YTree getLeftT() throws EmptyTreeE;

    abstract YTree getRightT() throws EmptyTreeE;

    abstract boolean isEmpty();

    //--------------------------
    // Insertion and queries
    //--------------------------

    abstract XTree insert(Point p);

    abstract boolean find(Point p);

    abstract Set<Point> rangeSearch(Rect range, Rect region);

    abstract Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;

    abstract Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;
}

abstract class YTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract Point getPoint() throws EmptyTreeE;

    abstract XTree getUnderT() throws EmptyTreeE;

    abstract XTree getAboveT() throws EmptyTreeE;

    abstract boolean isEmpty();

    //--------------------------
    // Insertion and queries
    //--------------------------

    abstract YTree insert(Point p);

    abstract boolean find(Point p);

    abstract Set<Point> rangeSearch(Rect range, Rect region);

    abstract Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;

    abstract Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;

}

//-----------------------------------------------------------------------
// Empty trees

class XEmpty extends XTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    YTree getLeftT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    YTree getRightT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    XTree insert(Point p) {
        return new XNode(p, new YEmpty(), new YEmpty());
    }

    boolean find(Point p) {
        return false;
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        return new HashSet<>();
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

}

class YEmpty extends YTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    XTree getUnderT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    XTree getAboveT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    YTree insert(Point p) {
        return new YNode(p, new XEmpty(), new XEmpty());
    }

    boolean find(Point p) {
        return false;
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        return new HashSet<>();
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        throw new EmptyTreeE();
    }
}

//-----------------------------------------------------------------------
// Non-Empty trees

class XNode extends XTree {
    private Point point;
    private YTree left, right;

    XNode(Point point, YTree left, YTree right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() {
        return point;
    }

    YTree getLeftT() {
        return left;
    }

    YTree getRightT() {
        return right;
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    XTree insert(Point p) {
        if (p.getX() < point.getX())
            return new XNode(point, left.insert(p), right);
        else
            return new XNode(point, left, right.insert(p));
    }

    boolean find(Point p) {
        if (p == point) return true;
        if (p.getX() < point.getX()) return left.find(p);
        else return right.find(p);
    }

    /*
     * We want to find all the points that lie inside the rectangle
     * 'range'. The parameter 'region' is the entire region
     * represented by this tree.
     *
     * The idea is to check if the region represented by this tree
     * intersects the range of interest. If there is no intersection,
     * we return an empty set. Otherwise, we search this tree in
     * detail as follows. We split the region represented by this tree
     * into a left region and a right region around the current point
     * and make recursive calls. The find set is the union of the the
     * two sets produced by the recursive calls additionally including the
     * current point if it is contained in the desired range.
     */
    Set<Point> rangeSearch(Rect range, Rect region) {
        LinkedHashSet<Point> result = new LinkedHashSet<>();
        if (!range.intersect(region)) return new HashSet<>();
        if (range.contains(this.point)) {
            result.add(this.point);
        }
        result.addAll(this.right.rangeSearch(range, region.rightOf(point)));
        result.addAll(this.left.rangeSearch(range, region.leftOf(point)));
        return result;
    }

    /*
     * This is the most complicated method in this assignment.
     *
     * The three parameters are as follows. We are looking for the
     * nearest neighbor of p in the given region but excluding every
     * point in the set 'excludes'.
     *
     * Here is the strategy I recommend:
     *
     * - start with a safe approximation for the 'result' and
     *   'distance' by creating a new point at "infinity": use
     *   Integer.MAX_VALUE for the coordinate and use
     *   Integer.MAX_VALUE as the distance. We will now try to refine
     *   this approximation by trying to find a better point as
     *   'result' and update 'distance' accordingly. If however there
     *   are no more points, this bad approximation will be our final
     *   result. In other words, we may get the point at infinity as
     *   the nearest neighbor if no other points remain.
     *
     * - the first refinement we can try to 'result' and 'distance' is
     *   to check if we can use the current point (the root of the
     *   current tree). If that point is not in the 'excludes' set,
     *   then we can use and we can use it to update 'result' and
     *   'distance'.
     *
     * - The next refinement is to try to find a closer point by
     *   recursively visiting the subtrees. But which subtree to visit
     *   first? If the point p happens to be to the left of the
     *   current point, then it is more likely that its closest
     *   neighbor is to the left, so we formulate a plan to visit the
     *   left subtree first and then the right subtree. Otherwise we
     *   try to visit the right subtree first and then the left
     *   subtree.
     *
     * - Now we put our plan into action. We want to make a recursive
     *   call to one of the subtrees hoping to improve our
     *   approximation. However, sometimes we might be able to detect,
     *   before making the recursive call, that the call will be
     *   useless and can be avoided. This happens if the closest
     *   distance from p to the region represented by the subtree is
     *   greater than or equal to the current approximation. In other
     *   words, every point in that region is farther than our current
     *   approximation so there is no reason to visit that subtree at
     *   all.
     *
     * - If we decide that it is worth it to visit the subtree, then
     *    we making the recursive call, and update our approximation
     *    if the recursive call returns a point that is closer to p.
     *
     */
    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {

        Point result = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int distance = Integer.MAX_VALUE;


        if (!excludes.contains(this.point)) {
            if (region.distanceSquaredTo(result) > region.distanceSquaredTo(this.point)) {
                result = this.point;
                distance = p.distanceSquaredTo(this.point);
            }
        }

        try {
            if (this.point.getX() > p.getX()) {
                Point LResult = this.left.nearestNeighbor(p, region.leftOf(this.point), excludes);
                int LDistance = LResult.distanceSquaredTo(p);
                if (LDistance < distance) {
                    result = LResult;
                    distance = LDistance;
                }

                Point RResult = this.right.nearestNeighbor(p, region.rightOf(this.point), excludes);
                int RDistance = RResult.distanceSquaredTo(p);
                if (RDistance < distance) {
                    result = RResult;
                }

            } else {

                Point RResult = this.right.nearestNeighbor(p, region.rightOf(this.point), excludes);
                int RDistance = RResult.distanceSquaredTo(p);
                if (RDistance < distance) {
                    result = RResult;
                    distance = RDistance;
                }

                Point LResult = this.left.nearestNeighbor(p, region.leftOf(this.point), excludes);
                int LDistance = LResult.distanceSquaredTo(p);
                if (LDistance < distance) {
                    result = LResult;
                }
            }


            return result;
        } catch (EmptyTreeE e) {
            return result;
        }
    }

    /*
     * If your nearestNeighbor works correctly, this method is
     * relatively easy. You repeatedly compute the nearest neighbor
     * making sure each successive call excludes the previous
     * neighbors from the search.
     */
    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        LinkedHashSet result = new LinkedHashSet();
        if(k == 0){
            return new LinkedHashSet<>();
        }
        try{
            for(int i = 0; i < k; i++){
                Point r = nearestNeighbor(p, region, result);
                result.add(r);
            }
        }

        catch (EmptyTreeE e){
        }
        return result;
    }
}

class YNode extends YTree {
    private Point point;
    private XTree under, above;

    YNode(Point point, XTree under, XTree above) {
        this.point = point;
        this.under = under;
        this.above = above;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return under;
    }

    public TreePrinter.PrintableNode getRight() {
        return above;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() {
        return point;
    }

    XTree getUnderT() {
        return under;
    }

    XTree getAboveT() {
        return above;
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    YTree insert(Point p) {
        if (p.getY() < point.getY())
            return new YNode(point, under.insert(p), above);
        else
            return new YNode(point, under, above.insert(p));
    }

    boolean find(Point p) {
        if (p == point) return true;
        if (p.getY() < point.getY()) return under.find(p);
        else return above.find(p);
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        LinkedHashSet<Point> result = new LinkedHashSet<>();
        if (!range.intersect(region)) return new HashSet<>();
        if (range.contains(this.point)) {
            result.add(this.point);
        }
        result.addAll(this.above.rangeSearch(range, region.aboveOf(point)));
        result.addAll(this.under.rangeSearch(range, region.underOf(point)));
        return result;
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {

        Point result = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int distance = Integer.MAX_VALUE;
        if (!excludes.contains(this.point)) {
            if (region.distanceSquaredTo(result) > region.distanceSquaredTo(this.point)) {
                result = this.point;
                distance = p.distanceSquaredTo(this.point);
            }
        }
        try {
            if (this.point.getY() > p.getY()) {
                Point UResult = this.under.nearestNeighbor(p, region.underOf(this.point), excludes);
                int UDistance = UResult.distanceSquaredTo(p);
                if (UDistance < distance) {
                    result = UResult;
                    distance = UDistance;
                }

                Point AResult = this.above.nearestNeighbor(p, region.aboveOf(this.point), excludes);
                int ADistance = AResult.distanceSquaredTo(p);
                if (ADistance < distance) {
                    result = AResult;
                }

            } else {

                Point AResult = this.above.nearestNeighbor(p, region.aboveOf(this.point), excludes);
                int ADistance = AResult.distanceSquaredTo(p);
                if (ADistance < distance) {
                    result = AResult;
                    distance = ADistance;
                }

                Point UResult = this.under.nearestNeighbor(p, region.underOf(this.point), excludes);
                int UDistance = UResult.distanceSquaredTo(p);
                if (UDistance < distance) {
                    result = UResult;
                }
            }


            return result;
        } catch (EmptyTreeE e) {
            return result;
        }
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        LinkedHashSet result = new LinkedHashSet();
        if(k == 0){
            return new LinkedHashSet<>();
        }
        try{
            for(int i = 0; i < k; i++){
                Point r = nearestNeighbor(p, region, result);
                result.add(r);
            }
        }

        catch (EmptyTreeE e){
        }
        return result;
    }

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------


