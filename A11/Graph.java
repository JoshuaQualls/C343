import java.util.*;

// ---------------------------------------------------------------------------

abstract class Graph<E> {
    private Hashtable<Item<E>, ArrayList<Item<E>>> neighbors;
    protected Hashtable<Pair<Item<E>, Item<E>>, Integer> weights;
    protected NodeCollection<E> ncoll;

    public String output = "";

    Graph(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
          Hashtable<Pair<Item<E>, Item<E>>, Integer> weights,
          NodeCollection<E> ncoll) {
        this.neighbors = neighbors;
        this.weights = weights;
        this.ncoll = ncoll;
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> s) {

        ncoll.setSource(nodes,s);
    }

    void traverse() {
        while (!ncoll.isEmpty()) visitPrint(ncoll.extract());
    }

    void collect(){while(!ncoll.isEmpty()) collectHelper(ncoll.extract());}

    private void collectHelper(Item<E> u){
        if(!u.isVisited()){
            u.setVisited(true);
            output = output + u.toString();
            neighbors.get(u).forEach(v -> relax(u, v));
        }
    }

    void visitPrint(Item<E> u) {
        if (!u.isVisited()) {
            u.setVisited(true);
            System.out.print(u);
            neighbors.get(u).forEach(v -> relax(u, v));
        }
    }

    abstract void relax(Item<E> u, Item<E> v);
}

// ---------------------------------------------------------------------------

class BFS<E> extends Graph<E> {
    BFS(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors) {

        super(neighbors, null, NodeCollection.make(KIND.QUEUE));
    }

    void relax(Item<E> u, Item<E> v) {

        ncoll.insert(v);
    }
}

// ---------------------------------------------------------------------------

class DFS<E> extends Graph<E> {
    DFS(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors) {
        super(neighbors, null, NodeCollection.make(KIND.STACK));
    }

    void relax(Item<E> u, Item<E> v) {

        ncoll.insert(v);
    }
}

// ---------------------------------------------------------------------------

class SP<E> extends Graph<E> {
    SP(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
       Hashtable<Pair<Item<E>, Item<E>>, Integer> weights) {
        super(neighbors, weights, NodeCollection.make(KIND.BINARY_HEAP));
    }

    void relax(Item<E> u, Item<E> v) {
        Pair<Item<E>, Item<E>> x = new Pair(u,v);
        int weight = weights.get(x);
        int compVal = weight + u.getValue();
        if(compVal < v.getValue()){
            ncoll.updateKey(v.getPosition(), compVal);
        }
    }
}

// ---------------------------------------------------------------------------

class SPD<E> extends Graph<E> {
    SPD(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
        Hashtable<Pair<Item<E>, Item<E>>, Integer> weights) {
        super(neighbors, weights, NodeCollection.make(KIND.DHEAP_3));
    }
    void relax(Item<E> u, Item<E> v) {
        Pair<Item<E>, Item<E>> x = new Pair(u,v);
        int weight = weights.get(x);
        int compVal = weight + u.getValue();
        if(compVal < v.getValue()){
            ncoll.updateKey(v.getPosition(), compVal);
        }
    }
}

// ---------------------------------------------------------------------------

class MSP<E> extends Graph<E> {
    MSP(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
        Hashtable<Pair<Item<E>, Item<E>>, Integer> weights) {
        super(neighbors, weights, NodeCollection.make(KIND.BINARY_HEAP));
    }

    void relax(Item<E> u, Item<E> v) {
        Pair<Item<E>, Item<E>> x = new Pair<>(u,v);
        int weight = weights.get(x);
        if(!v.isVisited()){
            if(weight < v.getValue()){
                ncoll.updateKey(v.getPosition(), weight);
            }
        }
    }
}

// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------

