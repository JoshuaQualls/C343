
public class Main {

    public static void main (String[] args) throws EmptyTreeE {
        BinTree t = new Node(50, new Node(30, BinTree.makeLeaf(20), BinTree.makeLeaf(40)),
                new Node(70, BinTree.makeLeaf(60), BinTree.makeLeaf(80)));

        System.out.println("Original tree: ");
        TreePrinter.print(t);

        System.out.println("Insert 41: ");
        TreePrinter.print(t.insertB(41));

        BinTree test = new Node(35,
                new Node(20,
                        new Node(7,
                                new Node(5,
                                        new Empty(),
                                        new Empty()),
                                new Node(10,
                                        new Empty(),
                                        new Empty())),
                        new Node(30,
                                new Empty(),
                                new Empty())),
                new Node(40,
                        new Empty(),
                        new Node(50,
                                new Empty(),
                                new Empty())));


        System.out.println("Original tree:");
        TreePrinter.print(test);
        System.out.println("Insert 12:");
        TreePrinter.print(test.insertB(12));
        System.out.println("Delete 35:");
        TreePrinter.print(test.deleteB(35));
        System.out.println("Easy Left:");
        TreePrinter.print(test.easyLeft());
        System.out.println("Easy Right:");
        TreePrinter.print(test.easyRight());
        System.out.println("Rotate Left:");
        TreePrinter.print(test.rotateLeft());
        System.out.println("Rotate Right:");
        TreePrinter.print(test.rotateRight());



        BinTree test4 = new Node(4,
                BinTree.makeLeaf(3),
                BinTree.makeLeaf(5));

        BinTree test2 = BinTree.makeLeaf(5);

        BinTree test3 = new Node(10,
                new Node(5,
                        BinTree.makeLeaf(2),
                        new Empty()),
                BinTree.makeLeaf(15));

        TreePrinter.print(test4.rotateLeft());

        TreePrinter.print(test3.rotateLeft());
    }
}
