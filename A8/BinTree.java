import java.util.function.BiFunction;

class EmptyTreeE extends Exception {
}

//-----------------------------------------------------------------------
// Abstract tree class

abstract class BinTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyTreeE ETExc = new EmptyTreeE();

    static BinTree EMPTY = new Empty();

    static BinTree makeLeaf(int elem) {
        return new Node(elem, EMPTY, EMPTY);
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int getData() throws EmptyTreeE;

    abstract BinTree getLeftT() throws EmptyTreeE;

    abstract BinTree getRightT() throws EmptyTreeE;

    abstract int getHeight();

    abstract boolean isEmpty();

    abstract boolean isLeaf();

    //--------------------------
    // reduce either left or right traversal pattern
    //--------------------------

    abstract <R> R reduceLeft(R base, BiFunction<Integer, R, R> f);

    abstract <R> R reduceRight(R base, BiFunction<Integer, R, R> f);

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    abstract boolean find(int key);

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    abstract BinTree insert(int key);

    abstract BinTree delete(int key) throws EmptyTreeE;

    abstract Pair<Integer, BinTree> deleteLeftMostLeaf() throws EmptyTreeE;

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    abstract BinTree insertB(int key);

    abstract BinTree easyRight();

    abstract BinTree rotateRight();

    abstract BinTree easyLeft();

    abstract BinTree rotateLeft();

    abstract BinTree deleteB(int key) throws EmptyTreeE;

    abstract Pair<Integer, BinTree> shrink() throws EmptyTreeE;
}

//-----------------------------------------------------------------------
// Empty tree cases

class Empty extends BinTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int getData() throws EmptyTreeE {
        throw ETExc;
    }

    BinTree getLeftT() throws EmptyTreeE {
        throw ETExc;
    }

    BinTree getRightT() throws EmptyTreeE {
        throw ETExc;
    }

    int getHeight() {
        return 0;
    }

    boolean isEmpty() {
        return true;
    }

    boolean isLeaf() {
        return false;
    }

    //--------------------------
    // reduce either left or right traversal pattern
    //--------------------------

    <R> R reduceLeft(R base, BiFunction<Integer, R, R> f) {
        return base;
    }

    <R> R reduceRight(R base, BiFunction<Integer, R, R> f) {
        return base;
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    boolean find(int key) {
        return false;
    }

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    BinTree insert(int key) {
        return makeLeaf(key);
    }

    BinTree delete(int key) throws EmptyTreeE {
        throw ETExc;
    }

    Pair<Integer, BinTree> deleteLeftMostLeaf() throws EmptyTreeE {
        throw ETExc;
    }

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    BinTree insertB(int key) {
        return makeLeaf(key);
    }

    BinTree easyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    BinTree rotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    BinTree easyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    BinTree rotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    BinTree deleteB(int key) throws EmptyTreeE {
        throw ETExc;
    }

    Pair<Integer, BinTree> shrink() throws EmptyTreeE {
        throw ETExc;
    }

    public boolean equals(Object other) {return other instanceof Empty;}
}

//-----------------------------------------------------------------------
// Non-empty tree case

class Node extends BinTree {
    private int data;
    private BinTree left, right;
    private int height;

    Node(int data, BinTree left, BinTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.getHeight(), right.getHeight());
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return getLeftT();
    }

    public TreePrinter.PrintableNode getRight() {
        return getRightT();
    }

    public String getText() {
        return String.valueOf(getData());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int getData() {
        return data;
    }

    BinTree getLeftT() {
        return left;
    }

    BinTree getRightT() {
        return right;
    }

    int getHeight() {
        return height;
    }

    boolean isEmpty() {
        return false;
    }

    boolean isLeaf() {
        return left.isEmpty() && right.isEmpty();
    }

    //--------------------------
    // reduce either left or right traversal pattern
    //--------------------------

    <R> R reduceLeft(R base, BiFunction<Integer, R, R> f) {
        return f.apply(data, left.reduceLeft(base, f));
    }

    <R> R reduceRight(R base, BiFunction<Integer, R, R> f) {
        return f.apply(data, right.reduceRight(base, f));
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    boolean find(int key) {
        if (data == key) return true;
        else if (key < data) return left.find(key);
        else return right.find(key);
    }

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    BinTree insert(int key) {
        if (key < data) return new Node(data, left.insert(key), right);
        else return new Node(data, left, right.insert(key));
    }

    BinTree delete(int key) throws EmptyTreeE {
        if (key < data) return new Node(data, left.delete(key), right);
        else if (key > data) return new Node(data, left, right.delete(key));
        else {
            try {
                Pair<Integer, BinTree> dr = right.deleteLeftMostLeaf();
                return new Node(dr.getFirst(), left, dr.getSecond());
            } catch (EmptyTreeE e) {
                return left;
            }
        }
    }

    Pair<Integer, BinTree> deleteLeftMostLeaf() throws EmptyTreeE {
        try {
            Pair<Integer, BinTree> dl = left.deleteLeftMostLeaf();
            return new Pair<>(dl.getFirst(), new Node(data, dl.getSecond(), right));
        } catch (EmptyTreeE e) {
            return new Pair<>(data, right);
        }
    }

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    BinTree insertB(int key) {
        if (key < data) {
            BinTree newLeft = left.insertB(key);
            BinTree newNode = new Node(data, newLeft, right);
            if (newLeft.getHeight() > right.getHeight() + 1) return newNode.rotateRight();
            else return newNode;
        } else {
            BinTree newRight = right.insertB(key);
            BinTree newNode = new Node(data, left, newRight);
            if (newRight.getHeight() > left.getHeight() + 1) return newNode.rotateLeft();
            else return newNode;
        }
    }

    BinTree easyRight() {
        try {
            int ld = left.getData();
            BinTree ll = left.getLeftT();
            BinTree lr = left.getRightT();
            return new Node(ld, ll, new Node(data, lr, right));
        } catch (EmptyTreeE e) {
            throw new Error("Internal bug!");
        }
    }

    BinTree rotateRight() {
        try {
            BinTree ll = left.getLeftT();
            BinTree lr = left.getRightT();
            if (ll.getHeight() >= lr.getHeight()) {
                return this.easyRight();
            } else {
                return new Node(data, this.left.easyLeft(), right).easyRight();
            }
        }
        catch (EmptyTreeE e){
            throw new Error();
        }

    }

    BinTree easyLeft() {
        try {
            int rd = right.getData();
            BinTree rl = right.getLeftT();
            BinTree rr = right.getRightT();
            return new Node(rd, new Node(data, left, rl), rr);
        } catch (EmptyTreeE e) {
            throw new Error("Internal bug!");
        }
    }

    BinTree rotateLeft() {

        try {
            BinTree rl = right.getLeftT();
            BinTree rr = right.getRightT();
            if (rr.getHeight() >= rl.getHeight()) {
                return this.easyLeft();
            } else {
                return new Node(data, left, right.easyRight()).easyLeft();

            }
        }
        catch (EmptyTreeE e){
            throw new Error();
        }
    }

    BinTree deleteB(int key) throws EmptyTreeE {
        if(key < data){
            BinTree newLeft = left.deleteB(key);
            BinTree result = new Node(data, newLeft, right);
            if(right.getHeight() > newLeft.getHeight() + 1){
                return result.rotateLeft();
            }
            else{
                return result;
            }
        }
        else if (key > data){
            BinTree newRight = right.deleteB(key);
            BinTree result = new Node(data, left, newRight);
            if(left.getHeight() > newRight.getHeight() + 1){
                return result.rotateRight();
            }
            else{
                return result;
            }
        }
        else{
            try {
                Pair<Integer, BinTree> shrunk = left.shrink();
                BinTree result = new Node(shrunk.getFirst(), shrunk.getSecond(), right);
                if (result.getRightT().getHeight() > result.getLeftT().getHeight() + 1) {
                    return result.rotateLeft();
                } else {
                    return result;
                }
            }
            catch (EmptyTreeE e){
                return right;
            }
        }
    }

    Pair<Integer, BinTree> shrink() {
        try {
            Pair<Integer, BinTree> result = right.shrink();
            if(left.getHeight() >result.getSecond().getHeight() + 1){
                return new Pair<>(result.getFirst(), new Node(data, left, result.getSecond()).rotateRight());
            }
            else{
                return new Pair<>(result.getFirst(), new Node(data,left,result.getSecond()));
            }
        } catch (EmptyTreeE e) {
            return new Pair<>(data, left);
        }
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node xs = (Node) other;
            return this.data == xs.data && this.left.equals(xs.left) && this.right.equals(xs.right);
        } else return false;
    }

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------


