/**
 * @author yogev abarbanel
 * Id: 326116910
 * Hyponym Object.
 */
public class Hyponym implements Comparable {

    private String name;
    private int occur;

    /**
     * Constructor.
     * @param name the name.
     * @param occur num of occur.
     */
    public Hyponym(String name, int occur) {
        this.name = name;
        this.occur = occur;
    }

    /**
     * Constructor.
     * @param name the name.
     */
    public Hyponym(String name) {
        this(name, 1);
    }

    /**
     * @return num of occur.
     */
    public int getOccur() {
        return occur;
    }

    /**
     * @param newOccur new value of occur.
     */
    private void setOccur(int newOccur) {
        this.occur = newOccur;
    }

    /**
     * add one to occur.
     */
    public void addOccur() {
        this.setOccur(this.occur + 1);
    }

    /**
     * @return String representing the Object.
     */
    @Override
    public String toString() {
        return this.name + " (" + Integer.toString(this.occur) + ")";
    }

    /**
     * compare between tow Objects of Hypernym.
     * @param o the other Object.
     * @return the value 0 if the other occur is equal to this occur; a value less than 0 if this occur is
     * greater than the other occur; and a value less than 0 if this occur is
     * greater than the other occur.
     */
    @Override
    public int compareTo(Object o) {
        Hyponym other = (Hyponym) o;
        if (this.occur < other.getOccur()) {
            return 1;
        } else if (this.occur > other.getOccur()) {
            return -1;
        } else {
            return 0;
        }
    }
}
