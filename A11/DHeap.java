import java.util.*;

class NoChildE extends Exception {
}

class DHeap<E> {
    private int d;
    private int size;
    private ArrayList<Item<E>> elems;

    DHeap(int d) {
        elems = new ArrayList<>();
        elems.add(0, null);
        size = 0;
        this.d = d;
    }

    DHeap(int d, ArrayList<Item<E>> es) {
        this.elems = new ArrayList<>();
        this.elems.add(0, null);
        this.size = es.size();
        this.d = d;
        for (int i = 0; i < size; i++) {
            es.get(i).setPosition(i + 1);
            this.elems.add(es.get(i));
        }
        for (int i = size / d; i > 0; i--) {
            moveDown(i);
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    Item<E> findMin() {
        return elems.get(1);
    }

    List<Item<E>> getElems() {
        ArrayList<Item<E>> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            result.add(elems.get(i));
        }
        return result;
    }

    int getParentIndex(int i) throws NoParentE {
        if (i == 1) {
            throw new NoParentE();
        } else {
            return ((i - 2) / d) + 1;
        }
    }

    int getChildIndex(int i, int j) throws NoChildE {
        if ((i - 1) * d + 1 + j > size) {
            throw new NoChildE();
        } else {
            return (i - 1) * d + 1 + j;
        }
    }

    void swap(int i, int j) {

        Item<E> itemi = elems.get(i);
        Item<E> itemj = elems.get(j);
        itemi.setPosition(j);
        itemj.setPosition(i);
        elems.set(i, itemj);
        elems.set(j, itemi);
    }

    int getKey(int i) {
        return elems.get(i).getValue();
    }

    void updateKey(int i, int value) {
        elems.get(i).setValue(value);
        moveUp(i);
    }

    void moveUp(int i) {
        try {
            int parent = getParentIndex(i);
            if (getKey(i) < getKey(parent)) {
                swap(i, parent);
                moveUp(parent);
            }
        } catch (NoParentE e) {

        }
    }

    void insert(Item<E> ek) {
        size++;
        ek.setPosition(size);
        elems.add(ek);
        moveUp(size);
    }

    /*
     * Returns the index of the smallest child.
     */
    int minChildIndex(int i) throws NoChildE {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int j = 1; j <= d; j++){
            try{
                Integer current = getChildIndex(i, j);
                arr.add(current);
            }
            catch (NoChildE e){

            }
        }
        try{
            return arr.stream().min(Comparator.comparing(a -> elems.get(a))).get();
        }
        catch (NoSuchElementException e){
            throw new NoChildE();
        }
    }

    void moveDown(int i) {
        try {
            int child = minChildIndex(i);
            if (getKey(i) > getKey(child)) {
                swap(i, child);
                moveDown(child);
            }
        } catch (NoChildE e) {
        }
    }

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

