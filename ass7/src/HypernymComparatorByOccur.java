import java.util.Comparator;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Hypernym Comparator By Occur.
 */
public class HypernymComparatorByOccur implements Comparator {

    /**
     * compare between tow Hypernym by occur.
     * @param hypernym1 the first hypernym.
     * @param hypernym2 the second hypernym.
     * @return the value 0 if the Hypernyms equals; a value less than 0 if the first Hypernym is
     * less than the second Hypernyms; and a value greater than 0 if the first Hypernym is lexicographically
     * greater than the second Hypernyms.
     */
    public int compare(Object hypernym1, Object hypernym2) {
        if (hypernym1 instanceof Hypernym && hypernym2 instanceof Hypernym) {
            Hypernym h1 = (Hypernym) hypernym1;
            Hypernym h2 = (Hypernym) hypernym2;

            if (h1.getOccurs() < h2.getOccurs()) {
                return -1;
            } else if (h1.getOccurs() > h2.getOccurs()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            throw new RuntimeException("one of the Object wasn't Hypernym");
        }
    }
}
