public class Rect {
    private int xmin, ymin, xmax, ymax;

    Rect(int xmin, int ymin, int xmax, int ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    int getXmin() {
        return xmin;
    }

    int getYmin() {
        return ymin;
    }

    int getXmax() {
        return xmax;
    }

    int getYmax() {
        return ymax;
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the left portion.
     */
    Rect leftOf(Point p) {
        return new Rect(this.xmin, this.ymin, p.getX(), this.ymax);
    }

    /*
     * Split the current rectangle at the x-coordinate of the given
     * point; return a new rectangle for the right portion.
     */
    Rect rightOf(Point p) {
        return new Rect(p.getX(), this.ymin,this.xmax, this.ymax);
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the below portion.
     */
    Rect underOf(Point p) {
        return new Rect(this.xmin, this.ymin, this.xmax, p.getY());
    }

    /*
     * Split the current rectangle at the y-coordinate of the given
     * point; return a new rectangle for the above portion.
     */
    Rect aboveOf(Point p) {
        return new Rect(this.xmin, p.getY(), this.xmax, this.ymax);
    }

    /*
     * Checks if the given point is contained in the rectangle. A
     * point lying on the edge counts as being contained in the
     * rectangle.
     */
    boolean contains(Point p) {
        int otherX = p.getX();
        int otherY = p.getY();
        return otherX >= xmin && otherX <= xmax && otherY >= ymin && otherY <= ymax;
    }

    /*
     * Checks if this rectangle intersects the given rectangle.
     */
    boolean intersect(Rect r) {
        int otherXmin = r.getXmin();
        int otherXmax = r.getXmax();
        int otherYmin = r.getYmin();
        int otherYMax = r.getYmax();

        Point otherLowerLeft = new Point(otherXmin, otherYmin);
        Point otherUpperLeft = new Point(otherXmin, otherYMax);
        Point otherLowerRight = new Point(otherXmax, otherYmin);
        Point otherUpperRight = new Point(otherXmax, otherYMax);

        Point thisLowerLeft = new Point(xmin, ymin);
        Point thisUpperLeft = new Point(xmin, ymax);
        Point thisLowerRight = new Point(xmax, ymin);
        Point thisUpperRight = new Point(xmax, ymax);

        Boolean smaller = contains(otherLowerLeft) || contains(otherUpperLeft) || contains(otherLowerRight) || contains(otherUpperRight);
        Boolean larger = r.contains(thisLowerLeft) || r.contains(thisUpperLeft) || r.contains(thisLowerRight) || r.contains(thisUpperRight);


        return smaller || larger;
    }

    /*
     * Calculates the shortest distance from the given point to the
     * current rectangle. If the point is contained in the rectangle,
     * the distance is 0.
     */
    int distanceSquaredTo(Point p) {
        int otherX = p.getX();
        int otherY = p.getY();

        if(contains(p)) return 0;
        else {
            if (otherX >= xmin && otherX <= xmax) {
                int distance = Math.min(Math.abs(otherY - this.ymin), Math.abs(otherY - this.ymax));
                return distance * distance;
            } else if (otherY >= ymin && otherY <= ymax) {
                int distance = Math.min(Math.abs(otherX - this.xmin), Math.abs(otherX - this.xmax));
                return distance * distance;
            } else {
                Point thisLowerLeft = new Point(xmin, ymin);
                Point thisUpperLeft = new Point(xmin, ymax);
                Point thisLowerRight = new Point(xmax, ymin);
                Point thisUpperRight = new Point(xmax, ymax);

                return Math.min(
                        p.distanceSquaredTo(thisLowerLeft),
                        Math.min(p.distanceSquaredTo(thisUpperLeft),
                                Math.min(p.distanceSquaredTo(thisLowerRight),
                                        p.distanceSquaredTo(thisUpperRight))));
            }
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Rect) {
            Rect other = (Rect) o;
            return xmin == other.xmin && xmax == other.xmax &&
                    ymin == other.ymin && ymax == other.ymax;
        } else return false;
    }

    public String toString() {
        return String.format("R[(%d,%d)--(%d,%d)]", xmin, ymin, xmax, ymax);
    }
}
