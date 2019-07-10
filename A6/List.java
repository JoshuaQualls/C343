import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

class EmptyListE extends Exception {}

abstract class List<E> {
    static <E> List<E> singleton (E e) {
        return new NodeL<>(e, new EmptyL<>());
    }

    static <E> List<E> MakeList (Function<Void,E> g, int size) {
        List<E> result = new EmptyL<>();
        for (int i = 0; i < size; i++) {
            result = new NodeL<>(g.apply(null), result);
        }
        return result;
    }

    static List<Integer> MakeIntList (Random r, int bound, int size) {

        return MakeList(v -> r.nextInt(bound), size);
    }

    List<E> push (E elem) { return new NodeL<>(elem, this); }

    abstract E getFirst () throws EmptyListE;
    abstract List<E> getRest () throws EmptyListE;
    abstract E get (int i) throws EmptyListE;
    abstract boolean isEmpty ();
    abstract boolean isSingleton ();
    abstract int length();
    abstract List<E> add (E elem);
    abstract List<E> append (List<E> other);
    abstract boolean contains (E e);
    abstract List<E> drop (int i);
    abstract <R> List<R> map (Function<E,R> f);
    abstract void forEach (Function<E,Void> f);
    abstract <R> R reduce (R base, BiFunction<E,R,R> f);
}

class EmptyL<E> extends List<E> {
    E getFirst () throws EmptyListE { throw new EmptyListE(); }
    List<E> getRest () throws EmptyListE { throw new EmptyListE(); }
    E get (int i) throws EmptyListE { throw new EmptyListE(); }
    boolean isEmpty () { return true; }
    boolean isSingleton () { return false; }
    int length() { return 0; }
    List<E> add (E elem) { return new NodeL<>(elem, this); }
    List<E> append (List<E> other) { return other; }
    boolean contains (E e) { return false; }
    List<E> drop (int i) { return this; }
    <R> List<R> map (Function<E,R> f) { return new EmptyL<>(); }
    void forEach (Function<E,Void> f) { return; }
    <R> R reduce (R base, BiFunction<E,R,R> f) { return base; }

    public String toString () { return "#"; }
}

class NodeL<E> extends List<E> {
    private E data;
    private List<E> rest;

    NodeL(E data, List<E> rest) {
        this.data = data;
        this.rest = rest;
    }

    E getFirst () { return data; }
    List<E> getRest () { return rest; }
    E get (int i) throws EmptyListE {
        if (i == 0) return data;
        else return rest.get(i-1);
    }
    boolean isEmpty () { return false; }
    boolean isSingleton () { return rest.isEmpty(); }
    int length() { return 1 + rest.length(); }
    List<E> add (E elem) { return new NodeL<>(data, rest.add(elem)); }
    List<E> append (List<E> other) {
        return new NodeL<>(data, rest.append(other));
    }
    boolean contains (E e) {
        return data.equals(e) || rest.contains(e);
    }
    List<E> drop (int i) {
        if (i == 0) return this;
        else return rest.drop(i-1);
    }
    <R> List<R> map (Function<E,R> f) {
        return new NodeL<>(f.apply(data), rest.map(f));
    }
    void forEach (Function<E,Void> f) {
        f.apply(data);
        rest.forEach(f);
    }
    <R> R reduce (R base, BiFunction<E,R,R> f) {
        return f.apply(data,rest.reduce(base,f));
    }

    public String toString () { return data + "," + rest; }
}
