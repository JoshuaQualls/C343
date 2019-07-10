import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NoParentE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class BinaryHeap<E> {
    private int size;
    private ArrayList<Item<E>> elems;

    /*
     * Create an empty binary heap. You must initialize position 0 in the array to null.
     */
    BinaryHeap() {
        elems = new ArrayList<>();
        elems.add(0, null);
        size = 0;
    }

    /*
     * Create a binary heap containing all the items in 'es' as
     * follows. First add all the items in 'es' to 'elems' starting
     * from position 1. Make sure that each item is aware of its own
     * position by calling setPosition on each item as it is
     * inserted. After that initial loop, call moveDown on the first
     * half of the array.
     *
     */
    BinaryHeap(ArrayList<Item<E>> es) {
        this.elems = new ArrayList<>();
        this.elems.add(0, null);
        this.size = es.size();
        for(int i = 0; i < size; i++){
            es.get(i).setPosition(i+1);
            this.elems.add(es.get(i));
        }
        for( int i = size/2; i > 0; i--){
            moveDown(i);
        }
    }

    /*
     * Checks if the heap is empty.
     */
    boolean isEmpty() {
        return size == 0;
    }

    /*
     * Returns the number of elements in the heap.
     */
    int getSize() {
        return size;
    }

    /*
     * Returns (but does not remove) the mininum element in the
     * heap. Assume the heap is not empty.
     */
    Item<E> findMin() {
        return elems.get(1);
    }

    /*
     * Returns the actual items in the heap (excluding position 0).
     */
    List<Item<E>> getElems() {
        ArrayList<Item<E>> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            result.add(elems.get(i));
        }
        return result;
    }

    /*
     * Calculates the index of the parent of the item at position 'i'
     * or throws an exception if we are at the root of the binary
     * heap.
     */
    int getParentIndex(int i) throws NoParentE {
        if (i == 1) {
            throw new NoParentE();
        } else {
            return i / 2;
        }
    }

    /*
     * Calculates the index of the left child of the item at position
     * 'i' or throws an exception if no such child exists.
     */
    int getLeftChildIndex(int i) throws NoLeftChildE {
        if (2 * i > size) {
            throw new NoLeftChildE();
        } else {
            return 2 * i;
        }
    }

    /*
     * Calculates the index of the right child of the item at position
     * 'i' or throws an exception if no such child exists.
     */
    int getRightChildIndex(int i) throws NoRightChildE {
        if (2 * i + 1 > size) {
            throw new NoRightChildE();
        } else {
            return 2 * i + 1;
        }
    }

    /*
     * Swaps the items at positions 'i' and 'j' making sure that each
     * item is aware of its new position.
     */
    void swap(int i, int j) {
        Item<E> itemi = elems.get(i);
        Item<E> itemj = elems.get(j);
        itemi.setPosition(j);
        itemj.setPosition(i);
        elems.set(i, itemj);
        elems.set(j, itemi);
    }

    /*
     * Calls getValue() on the element at position 'i'.
     */
    int getKey(int i) {

        return elems.get(i).getValue();
    }

    /*
     * You can assume that the given value will be less than the
     * current value of the item at position 'i'. The update might
     * result in the item having to move up.
     */
    void updateKey(int i, int value) {
        elems.get(i).setValue(value);
        moveUp(i);
    }

    /*
     * Recursively move the item up to ensure the order property of
     * the heap is maintained.
     */
    void moveUp(int i) {
        try{
            int parent = getParentIndex(i);
            if(getKey(i) < getKey(parent)){
                swap(i, parent);
                moveUp(parent);
            }
        }
        catch (NoParentE e){

        }
    }

    /*
     * Inserts the given item in the heap.
     */
    void insert(Item<E> ek) {
        size++;
        ek.setPosition(size);
        elems.add(ek);
        moveUp(size);
    }

    /*
     * Calculates the index of the smallest child. In case of a tie, return the right child.
     */
    int minChildIndex(int i) throws NoLeftChildE {
        try{
            Item<E> left = elems.get(getLeftChildIndex(i));
            Item<E> right = elems.get(getRightChildIndex(i));
            if(left.getValue() < right.getValue()){
                return getLeftChildIndex(i);
            }
            else{
                return getRightChildIndex(i);
            }
        }
        catch (NoRightChildE e){
            return getLeftChildIndex(i);
        }
    }

    /*
     * Recursively moves the item down to maintain the order property of the heap.
     */
    void moveDown(int i) {
        try {
            int child = minChildIndex(i);
            if (getKey(i) > getKey(child)) {
                swap(i, child);
                moveDown(child);
            }
        } catch (NoLeftChildE e) {
        }
    }

    /*
     * Deletes and returns the mininum element in the heap.
     */
    Item<E> extractMin() {
        Item<E> result = findMin();
        swap(1, size);
        elems.remove(size);
        size--;
        moveDown(1);
        return result;
    }

    public String toString() {
        return getElems().toString();
    }

}
