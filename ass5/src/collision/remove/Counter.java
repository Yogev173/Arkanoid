package collision.remove;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Counter Object.
 */
public class Counter {

    private int counter;

    /**
     * @param initialValue the starting value of the Counter.
     */
    public Counter(int initialValue) {
        this.counter = initialValue;
    }

    /**
     * initial the Counter to 0.
     */
    public Counter() {
        this(0);
    }

    /**
     * add number to current count.
     * @param number how much to increase the Counter.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number to current count.
     * @param number how much to decrease the Counter.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return this.counter;
    }
}
