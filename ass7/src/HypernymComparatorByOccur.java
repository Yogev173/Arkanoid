import java.util.Comparator;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Hypernym Comparator By Occur.
 */
public class HypernymComparatorByOccur implements Comparator {


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
