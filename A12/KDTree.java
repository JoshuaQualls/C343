import java.util.ArrayList;
import java.util.List;

// -----

abstract class XTree implements TreePrinter.PrintableNode {
    abstract Region findRegion(Point p);

    static XTree makeXTree(List<Point> xPoints, List<Point> yPoints, int bound) {
        if ((xPoints.size() == 0 && yPoints.size() == 0) || xPoints.size() <= bound) {
            return new XEmpty();
        } else {
            Point middle = xPoints.get(xPoints.size()/2);
            List<Point> left = xPoints.subList(0, xPoints.indexOf(middle));
            List<Point> right = xPoints.subList(xPoints.indexOf(middle) + 1, xPoints.size());
            List<Point> yLeft = new ArrayList<>();
            List<Point> yRight = new ArrayList<>();
            for (int i = 0; i < yPoints.size(); i++) {
                if (left.contains(yPoints.get(i))) {
                    yLeft.add(yPoints.get(i));
                } else if(right.contains(yPoints.get(i))) {
                    yRight.add(yPoints.get(i));
                }
            }
            return new XNode(middle,
                    YTree.makeYTree(yLeft, left, bound),
                    YTree.makeYTree(yRight, right, bound));
        }
    }
}

abstract class YTree implements TreePrinter.PrintableNode {
    abstract Region findRegion(Point p);

    static YTree makeYTree(List<Point> yPoints, List<Point> xPoints, int bound) {
        if ((xPoints.size() == 0 && yPoints.size() == 0) || yPoints.size() <= bound) {
            return new YEmpty();
        } else {
            Point middle = yPoints.get(yPoints.size()/2);
            List<Point> below = yPoints.subList(0, yPoints.indexOf(middle));
            List<Point> above = yPoints.subList(yPoints.indexOf(middle) + 1, yPoints.size());
            List<Point> xBelow = new ArrayList<>();
            List<Point> xAbove = new ArrayList<>();
            for (int i = 0; i < xPoints.size(); i++) {
                if (below.contains(xPoints.get(i))) {
                    xBelow.add(xPoints.get(i));
                } else if(above.contains(xPoints.get(i))) {
                    xAbove.add(xPoints.get(i));
                }
            }

            return new YNode(middle,
                    XTree.makeXTree(xBelow, below, bound),
                    XTree.makeXTree(xAbove, above, bound));
        }
    }
}

// -----

class XEmpty extends XTree {
    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public Region findRegion(Point p) {
        return new Region();
    }
}

class YEmpty extends YTree {
    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public Region findRegion(Point p) {
        return new Region();
    }
}

// -----

class XNode extends XTree {
    private Point point;
    private YTree left, right;

    XNode(Point point, YTree left, YTree right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    public Region findRegion(Point p) {
        if (p.equals(this.point)) {
            return new Region();
        }
        if (p.getX() < this.point.getX()) {
            Region r = this.left.findRegion(p);
            r.push(DIR.LEFT);
            return r;
        } else {
            Region r = this.right.findRegion(p);
            r.push(DIR.RIGHT);
            return r;
        }
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

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return under;
    }

    public TreePrinter.PrintableNode getRight() {
        return above;
    }

    public Region findRegion(Point p) {
        if (p.equals(this.point)) {
            return new Region();
        }
        if (p.getY() < this.point.getY()) {
            Region r = this.under.findRegion(p);
            r.push(DIR.UNDER);
            return r;
        } else {
            Region r = this.above.findRegion(p);
            r.push(DIR.ABOVE);
            return r;
        }
    }
}

// -----
