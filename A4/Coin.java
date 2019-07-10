public class Coin {
    private int value;

    Coin (int value) {
        this.value = value;
    }

    int getValue () { return value; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coin)) return false;
        else {
            Coin that = (Coin) o;
            return value == that.value;
        }
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString () {
        return "@" + value;
    }

}
