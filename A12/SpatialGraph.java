import java.util.*;

public class SpatialGraph {
    private Rect grid;
    private ArrayList<Point> points;

    private ArrayList<Item<Point>> nodes;
    private Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors;
    private Hashtable<Edge, Integer> weights;

    private XTree kdtree; // initialized by preprocess
    private Hashtable<Edge, HashSet<Region>> regionalEdges; // initialized by preprocess

    SpatialGraph(
            Rect grid, ArrayList<Point> points,
            ArrayList<Item<Point>> nodes,
            Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.grid = grid;
        this.points = points;
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    SpatialGraph(Random r, int numberNodes, int maxNeighbors, int maxWeight, int gridSize) {
        this.grid = new Rect(0, 0, gridSize, gridSize);
        this.points = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.neighbors = new Hashtable<>();
        this.weights = new Hashtable<>();

        for (int i = 0; i < numberNodes; i++) {
            Point p = new Point(r.nextInt(gridSize), r.nextInt(gridSize));
            Item<Point> n = new Item<>(p, p.toString(), Integer.MAX_VALUE);
            while (nodes.contains(n)) {
                p = new Point(r.nextInt(gridSize), r.nextInt(gridSize));
                n = new Item<>(p, p.toString(), Integer.MAX_VALUE);
            }
            points.add(p);
            nodes.add(n);
        }

        for (int i = 0; i < numberNodes; i++) {
            ArrayList<Item<Point>> neighbors = new ArrayList<>();
            int numberNeighbors = r.nextInt(maxNeighbors);

            for (int j = 0; j < numberNeighbors; j++) {
                int neighborIndex = r.nextInt(numberNodes);
                while (i == neighborIndex) {
                    neighborIndex = r.nextInt(numberNodes);
                }
                Item<Point> neighbor = nodes.get(neighborIndex);
                neighbors.add(neighbor);
                weights.put(new Edge(nodes.get(i).getData(), neighbor.getData()), r.nextInt(maxWeight));
            }

            this.neighbors.put(nodes.get(i), neighbors);
        }
    }

    // -----

    Rect getGrid() {
        return grid;
    }

    ArrayList<Point> getPoints() {
        return points;
    }

    ArrayList<Item<Point>> getNodes() {
        return nodes;
    }

    Hashtable<Item<Point>, ArrayList<Item<Point>>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    XTree getKdtree() {
        return kdtree;
    }

    Hashtable<Edge, HashSet<Region>> getRegionalEdges() {
        return regionalEdges;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item<Point> source) {
        nodes.forEach(Item::reset);
        for (Item<Point> node : nodes) {
            node.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        source.setPosition(0);
        BinaryHeap<Point> heap = new BinaryHeap<>(nodes);
        while (!heap.isEmpty()) {
            Item<Point> min = heap.extractMin();
            if (!min.isVisited()) {
                min.setVisited(true);
                for (Item<Point> neighbor : neighbors.get(min)) {
                    int d;
                    if (weights.get(new Edge(min.getData(), neighbor.getData())) == Integer.MAX_VALUE || min.getValue() == Integer.MAX_VALUE) {
                        d = Integer.MAX_VALUE;
                    } else {
                        d = min.getValue() + weights.get(new Edge(min.getData(), neighbor.getData()));
                    }
                    if (d < neighbor.getValue()) {
                        neighbor.setPrevious(min);
                        heap.updateKey(neighbor.getPosition(), d);
                    }
                }
            }
        }
    }

    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination

    ArrayList<Edge> shortestPath(Item<Point> source, Item<Point> dest) {
        nodes.forEach(Item::reset);
        for (Item<Point> node : nodes) {
            node.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        source.setPosition(0);
        BinaryHeap<Point> heap = new BinaryHeap<>(nodes);
        while (!heap.isEmpty()) {
            Item<Point> min = heap.extractMin();
            if (min.equals(dest)) {
                break;
            }
            if (!min.isVisited()) {
                min.setVisited(true);
                for (Item<Point> neighbor : neighbors.get(min)) {
                    int d;
                    if (weights.get(new Edge(min.getData(), neighbor.getData())) == Integer.MAX_VALUE || min.getValue() == Integer.MAX_VALUE) {
                        d = Integer.MAX_VALUE;
                    } else {
                        d = min.getValue() + weights.get(new Edge(min.getData(), neighbor.getData()));
                    }
                    if (d < neighbor.getValue()) {
                        neighbor.setPrevious(min);
                        heap.updateKey(neighbor.getPosition(), d);
                    }
                }
            }
        }
        return Item.pathToSource(dest);
    }

    // -----

    void buildKDTree(int bound) {
        ArrayList<Point> xpoints = new ArrayList<>(points);
        ArrayList<Point> ypoints = new ArrayList<>(points);
        xpoints.sort((o1, o2) -> {
            if (o1.getX() > o2.getX()) {
                return 1;
            } else if (o1.getX() < o2.getX()) {
                return -1;
            } else {
                return 0;
            }
        });
        ypoints.sort((o1, o2) -> {
            if (o1.getY() > o2.getY()) {
                return 1;
            } else if (o1.getY() < o2.getY()) {
                return -1;
            } else {
                return 0;
            }
        });

        kdtree = XTree.makeXTree(xpoints, ypoints, bound);
    }

    void preprocess(int bound) {
        buildKDTree(bound);
        regionalEdges = new Hashtable<>();
        for (Edge e : weights.keySet()) {
            regionalEdges.put(e, new HashSet<>());
        }
        for (Item<Point> node : nodes) {
            allShortestPaths(node);
            for (Item<Point> dest : nodes) {
                ArrayList<Edge> edges = Item.pathToSource(dest);
                if (edges.size() > 0) {
                    Edge nD = edges.get(edges.size() - 1);
                    nD = nD.flip();
                    Region r = kdtree.findRegion(dest.getData());
                    regionalEdges.get(nD).add(r);
                }
            }
        }
    }

    // -----

    ArrayList<Edge> regionalShortestPath(Item<Point> source, Item<Point> dest) {
        nodes.forEach(Item::reset);
        for (Item<Point> node : nodes) {
            node.setValue(Integer.MAX_VALUE);
        }
        Region r = kdtree.findRegion(dest.getData());
        source.setValue(0);
        source.setPosition(0);
        BinaryHeap<Point> heap = new BinaryHeap<>(nodes);
        while (!heap.isEmpty()) {
            Item<Point> min = heap.extractMin();
            if (min.equals(dest) || min.getValue() == Integer.MAX_VALUE) {
                break;
            }
            if (!min.isVisited()) {
                min.setVisited(true);
                for (Item<Point> neighbor : neighbors.get(min)) {
                    Edge edge = new Edge(min.getData(), neighbor.getData());
                    if (regionalEdges.get(edge).contains(r)) {
                        int d;
                        if (weights.get(new Edge(min.getData(), neighbor.getData())) == Integer.MAX_VALUE || min.getValue() == Integer.MAX_VALUE) {
                            d = Integer.MAX_VALUE;
                        } else {
                            d = min.getValue() + weights.get(new Edge(min.getData(), neighbor.getData()));
                        }
                        if (d < neighbor.getValue()) {
                            neighbor.setPrevious(min);
                            heap.updateKey(neighbor.getPosition(), d);
                        }
                    }
                }
            }
        }
        return Item.pathToSource(dest);
    }

        // -----

        public String toString () {
            StringBuilder res = new StringBuilder();
            res.append(String.format("Nodes:%n%s%n", nodes));
            res.append(String.format("Neighbors:%n%s%n", neighbors));
            res.append(String.format("Weights:%n%s%n", weights));
            return new String(res);
        }
    }
