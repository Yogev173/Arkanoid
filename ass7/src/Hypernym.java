import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Hypernym Object.
 */
public class Hypernym implements Comparable {

    public static final int MIN_SIZE = 3;
    private String name;
    private Map<String, Hyponym> hyponyms;

    /**
     * Constructor.
     * @param name the name.
     */
    public Hypernym(String name) {
        this.name = name;
        this.hyponyms = new HashMap<>();
    }

    /**
     * @return the name of the Hypernym.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the time it occur.
     */
    public int getOccurs() {
        int counter = 0;
        for (Hyponym hyponym : this.hyponyms.values()) {
            counter += hyponym.getOccur();
        }

        return counter;
    }

    /**
     * @param hyponymName the name of the Hyponym to add.
     */
    public void addHyponym(String hyponymName) {
        String nameUpperCase = hyponymName.toUpperCase();
        if (this.hyponyms.containsKey(nameUpperCase)) {
            this.hyponyms.get(nameUpperCase).addOccur();
        } else {
            this.hyponyms.put(nameUpperCase, new Hyponym(hyponymName));
        }
    }

    /**
     * @return a String representing the Object.
     */
    public String toString() {
        List<Hyponym> hyponymList = new ArrayList<>(this.hyponyms.values());
        Collections.sort(hyponymList);

        String string = this.name + ":";
        for (Hyponym hyponym : hyponymList) {
            string += " " + hyponym.toString() + ",";
        }
        return string.substring(0, string.length() - 1);
    }

    /**
     * check that it valid (more then 3 different Hyponyms).
     * @return if it valid.
     */
    public boolean isValid() {
        return (this.hyponyms.size() >= MIN_SIZE);
    }


    /**
     * compare between tow Objects of Hypernym.
     * @param o the other Object.
     * @return the value 0 if the argument string is equal to this string; a value less than 0 if this string is
     * lexicographically less than the string argument; and a value greater than 0 if this string is lexicographically
     * greater than the string argument.
     */
    @Override
    public int compareTo(Object o) {
        return this.name.toUpperCase().compareTo(((Hypernym) o).getName().toUpperCase());
    }
}
