import java.util.Random;
import java.util.function.Function;


// -------------------------------------------------------

abstract class HashFunction implements Function<Integer,Integer> {}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with a parameter 'bound'. Then
 * every time the hash function is applied to an argument 'x', it
 * returns the value of 'x' modulo the 'bound'. See the test cases in
 * HashFunctionTest for some examples.
 *
 */
class HashMod extends HashFunction {

    private int bound;

    public HashMod (int bound){
        this.bound = bound;
    }


    @Override
    public Integer apply(Integer x) {
        return x % bound;
    }
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with two parameters: a first
 * argument 'bound' that is used to create an instance of the
 * superclass, and a second argument 'after' of type
 * Function<Integer,Integer> that is used as follows. When the hash
 * function is invoked, the basic functionality of the super class is
 * first invoked, and then that result is given to the function
 * 'after'. See the test cases in HashFunctionTest for some examples.
 *
 */

class HashModThen extends HashMod {
    private HashMod hm;
    private Function<Integer,Integer> after;

    public HashModThen(int bound, Function<Integer, Integer> after){
        super(bound);
        hm = new HashMod(bound);
        this.after = after;
    }

    @Override
    public Integer apply(Integer x){
       return after.apply(hm.apply(x));
    }
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with three parameters: a
 * random number generator 'r' of type Random and two integers 'p' and
 * 'm' where 'p' should be a prime number greater than or equal to
 * 'm'.
 *
 * Using the random number generator, the constructor generates
 * two random numbers 'a' and 'b' such that 'a' is in the range 1
 * (inclusive) and p (exclusive) and 'b' is in the range 0 (inclusive)
 * and p (exclusive).
 *
 * The resulting hash function should be a
 * universal hash function as explained in the book. Again see the
 * test cases in HashFunctionTest for some examples.
 *
 */

class HashUniversal extends HashFunction {
    private int a, b, p , m;

    public HashUniversal(Random r, int p, int m){
        if(isPrime(p)){
            throw new java.lang.RuntimeException("p must be prime.");
        }
        else if(p < m){
            throw new java.lang.RuntimeException("p must be greater than m.");
        }
        else{
            this.p = p;
            this.m = m;
            this.a = r.nextInt(p-1) + 1;
            this.b = r.nextInt(p);
        }

    }

    @Override
    public Integer apply(Integer integer) {
        return (((a * integer) + b) % p) % m;
    }

    private boolean isPrime(int x){
        if(x == 2) return true;
        if(x % 2 == 0) return false;
        for(int i = 1; i < x; i++){
            if(x%i==0){
                return false;
            }
        }
        return true;
    }
}

// -------------------------------------------------------
// -------------------------------------------------------
