import java.util.Random;

class EmptyTreeE extends Exception {}

/**
 * This hierarcy implements m-ary trees.
 * An m-ary tree is either empty, or
 * it is a node with a list of m-subtrees
 * 
 * The parameter m is often called the branchingFactor
 * 
 * It is convenient to recognize trees with no children which are 
 * called 'leaves'. Technically an m-leaf is an m-ary tree with 
 * one node and a list of length m of empty subtrees.
 * 
 * In the three methods makeTree* below, you may assume the incoming
 * parameter is tuned to create a perfect tree in which every level
 * has the same number of nodes. So for example for a branching 
 * factor of 3, you can assume the argument n to makeTreeNodes will be 
 * one of:
 * 0, 1, 1+3, 1+3+9, 1+3+9+27, 1+3+9+27+81, ...
 * and nothing else. This simplifies your implementation considerably.
 * We will never test your code with other numbers.
 * 
 * The Random number generator and bound are used to create the data
 * at each node: use r.nextInt(bound). We will not assume you are 
 * creating the nodes in any particular order.
 */
abstract class Tree<E> {
    abstract E getData() throws EmptyTreeE;
    abstract List<Tree<E>> getChildren() throws EmptyTreeE;
    
    abstract boolean isEmpty();
    abstract boolean isLeaf();

    abstract int getBranchingFactor();
    abstract int getNumberOfNodes(); 
    abstract int numberOfInternalNodes();
    abstract int numberOfLeaves();
    abstract int getHeight();

    static <E> Tree<E> makeLeaf(E elem, int m) {

        return new NodeT<>(elem, new EmptyL<>(), m);
    }

    /*
     * Assume that 'n' is perfect
     */
    static Tree<Integer> makeTreeNodes(int n, int m, Random r, int bound) {

        if(n == 0){
            return new EmptyT<>(m);
        }
        else {
            return new NodeT<>(r.nextInt(bound),
                    List.MakeList(x->makeTreeNodes((n-1)/m, m, r, bound), m) ,
                    m);
        }
    }

    static Tree<Integer> makeTreeInternals(int i, int m, Random r, int bound) {
            return makeTreeNodes(m * i +1, m, r, bound);

    }

    static Tree<Integer> makeTreeLeaves(int ell, int m, Random r, int bound) {

        return makeTreeNodes(((m*ell)-1)/(m-1),m,r,bound);
    }

}

class EmptyT<E> extends Tree<E> {
    private int branchingFactor;

    public EmptyT(int m ){
        this.branchingFactor = m;
    }


    @Override
    E getData() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @Override
    List<Tree<E>> getChildren() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @Override
    boolean isEmpty() {
        return true;
    }

    @Override
    boolean isLeaf() {
        return false;
    }

    @Override
    int getBranchingFactor() {
        return branchingFactor;
    }

    @Override
    int getNumberOfNodes() {
        return 0;
    }

    @Override
    int numberOfInternalNodes() {
        return 0;
    }

    @Override
    int numberOfLeaves() {
        return 0;
    }

    @Override
    int getHeight() {
        return 0;
    }
}

class NodeT<E> extends Tree<E> {
    private E data;
    private List<Tree<E>> children;
    private int branchingFactor;


    public NodeT(E data, List<Tree<E>> children, int m){
        this.data = data;
        this.children = children;
        this.branchingFactor = m;
    }


    @Override
    E getData() throws EmptyTreeE {
        return this.data;
    }


    @Override
    List<Tree<E>> getChildren() throws EmptyTreeE {
        return children;
    }

    @Override
    boolean isEmpty() {
        return false;
    }

    @Override
    boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    int getBranchingFactor() {
        return branchingFactor;
    }

    @Override
    int getNumberOfNodes() {
        return children.map(Tree::getNumberOfNodes).reduce(1,(i, j) -> i + j );
    }

    @Override
    int numberOfInternalNodes() {
        return (getNumberOfNodes()-1)/getBranchingFactor();
    }

    @Override
    int numberOfLeaves() {
        return getNumberOfNodes()-numberOfInternalNodes();
    }

    @Override
    int getHeight() {
        return 1 + children.map(Tree::getHeight).reduce(0,(i, j) -> Math.max(i,j));
    }
}


