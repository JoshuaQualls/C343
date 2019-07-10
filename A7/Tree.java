import java.util.function.Function;

@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}

class WrongTreeE extends Exception {
}

abstract class BinTree<N, L> {
    abstract boolean isLeaf();

    abstract L getLeafData() throws WrongTreeE;

    abstract N getNodeData() throws WrongTreeE;

    abstract BinTree<N, L> getLeft() throws WrongTreeE;

    abstract BinTree<N, L> getRight() throws WrongTreeE;

    abstract <R, S> BinTree<R, S> map(Function<N, R> fnode, Function<L, S> fleaf);

    abstract <R> R reduce(Function<L, R> base, TriFunction<N, R, R, R> f);
}

//-----------------------------------------------------------------------

class Leaf<N, L> extends BinTree<N, L> {
    private L data;

    Leaf(L data) {
        this.data = data;
    }

    boolean isLeaf() {
        return true;
    }

    L getLeafData() {
        return data;
    }

    N getNodeData() throws WrongTreeE {
        throw new WrongTreeE();
    }

    BinTree<N, L> getLeft() throws WrongTreeE {
        throw new WrongTreeE();
    }

    BinTree<N, L> getRight() throws WrongTreeE {
        throw new WrongTreeE();
    }

    <R, S> BinTree<R, S> map(Function<N, R> fnode, Function<L, S> fleaf) {
        return new Leaf<>(fleaf.apply(data));
    }

    <R> R reduce(Function<L, R> base, TriFunction<N, R, R, R> f) {
        return base.apply(data);
    }
}

//-----------------------------------------------------------------------

class Node<N, L> extends BinTree<N, L> {
    private N data;
    private BinTree<N, L> left, right;

    Node(N data, BinTree<N, L> left, BinTree<N, L> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    boolean isLeaf() {
        return false;
    }

    L getLeafData() throws WrongTreeE {
        throw new WrongTreeE();
    }

    N getNodeData() {
        return data;
    }

    BinTree<N, L> getLeft() {
        return left;
    }

    BinTree<N, L> getRight() {
        return right;
    }

    <R, S> BinTree<R, S> map(Function<N, R> fnode, Function<L, S> fleaf) {
        return new Node<>(fnode.apply(data), left.map(fnode,fleaf), right.map(fnode,fleaf));
    }

    <R> R reduce(Function<L, R> base, TriFunction<N, R, R, R> f) {
        return f.apply(data, left.reduce(base,f), right.reduce(base,f));
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
