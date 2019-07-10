import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * Solve every problem below using ONE call to reduce. Do not use
 * explicit loops, explicit recursion, or iterators.
 */

public class TreeFunctions {

    /* Count the total number of nodes */
    static <N,L> int countNodes (BinTree<N,L> t) {
        return t.reduce(a->1, (n,l,r) -> 1 + l + r);
    }

    /* Count the leaves */
    static <N,L> int countLeaves (BinTree<N,L> t) {

        return t.reduce(a->1, (x,y,z) -> y + z);
    }

    /* Count the number of internal nodes */
    static <N,L> int countInternalNodes (BinTree<N,L> t) {

        return countNodes(t) - countLeaves(t);
    }

    /* Return the maximum height of the tree */
    static <N,L> int height (BinTree<N,L> t) {

        return t.reduce(a->0, (x,y,z) -> 1 + Math.max(y,z));
    }

    /* Check if a tree is balanced. A tree is balanced if every
     * subtree is balanced and if for every node, the heights of its
     * children are no more than 1 apart. To use 'reduce' here, the
     * result of every tree traversal will be a pair containing the
     * height of the tree and a boolean that states whether it is
     * balanced or not.
     */
    static <N,L> boolean isBalanced (BinTree<N,L> t) {

        return t.reduce(a->new Pair<>(
                        0,
                        true),
                        (n,l,r) -> new Pair<>(
                        1+Math.max(l.getFirst(), r.getFirst()),
                        l.getSecond() && r.getSecond() && Math.abs(r.getFirst()-l.getFirst())<=1))
                .getSecond();
    }

    /* Preorder traversal */
    static List<Integer> preorder (BinTree<Integer,Integer> t) {

        return t.reduce(a->new NodeL<>(a, new EmptyL<>()),(n,l,r) -> new NodeL<>(n,l.append(r)));
    }

    /* Inorder traversal */
    static List<Integer> inorder (BinTree<Integer,Integer> t) {
        return t.reduce(a->new NodeL<>(a, new EmptyL<>()), (n,l,r) -> new NodeL<>(l.getFirst(), l.getRest().add(n).append(r)));
    }

    /* Postorder traversal */
    static List<Integer> postorder (BinTree<Integer,Integer> t) {
        return t.reduce(a->new NodeL<>(a, new EmptyL<>()), (n,l,r) -> new NodeL<>(l.getFirst(), l.getRest().append(r).add(n)));
    }

    /* Here the incoming tree is an expression tree (see book for
     * details). The big difference is that the data at each node is a
     * function that is applied to the results of its children.
     */
    static int eval (BinTree<BinaryOperator<Integer>,Integer> exp) {

        return exp.reduce(a->a, BiFunction::apply);
    }

    public static void main (String[] args) {
        BinTree<Integer,Integer> t123, t456, t7, test, test2;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);
        test = new Node<>(1, t7, new Leaf<>(1));
        test2 = new Node<>(7,
                new Node<>(8,
                        new Node<>(9,
                                new Leaf<>(10),
                                new Leaf<>(5)),
                        new Leaf<>(12)),
                new Leaf<>(14));

        System.out.printf("t7 has %d nodes%n", countNodes(t7));
        System.out.printf("t7 has %d leaves%n", countLeaves(t7));
        System.out.printf("t7 has %d internal nodes%n", countInternalNodes(t7));
        System.out.printf("t7 has height %d%n", height(t7));
        System.out.printf("is t7 balanced? %b%n", isBalanced(t7));
        System.out.printf("is t123 balanced? %b%n", isBalanced(t123));
        System.out.printf("is t456 balanced? %b%n", isBalanced(t456));
        System.out.printf("is test balanced? %b%n", isBalanced(test));
        System.out.printf("is test2 balanced? %b%n", isBalanced(test2));
        System.out.printf("preorder traversal of t7: %s%n", preorder(t7));
        System.out.printf("inorder traversal of t7:  %s%n", inorder(t7));
        System.out.printf("postorder traversal of t7:  %s%n", postorder(t7));

        BinTree<BinaryOperator<Integer>,Integer> exp1, exp2, exp3;
        exp1 = new Node<>((a,b) -> a+b, new Leaf<>(2), new Leaf<>(3));
        exp2 = new Node<>((a,b) -> a-b, new Leaf<>(5), new Leaf<>(6));
        exp3 = new Node<>(Math::max, exp1, exp2);
        System.out.printf("evaluating exp1 => %d%n", eval(exp1));
        System.out.printf("evaluating exp2 => %d%n", eval(exp2));
        System.out.printf("evaluating exp3 => %d%n", eval(exp3));
    }

    /* Expected output:

t7 has 7 nodes
t7 has 4 leaves
t7 has 3 internal nodes
t7 has height 2
is t7 balanced? true
preorder traversal of t7: 7,1,2,3,4,5,6,#
inorder traversal of t7:  2,1,3,7,5,4,6,#
postorder traversal of t7:  2,3,1,5,6,4,7,#
evaluating exp1 => 5
evaluating exp2 => -1
evaluating exp3 => 5

    */

}

