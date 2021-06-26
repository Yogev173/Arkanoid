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
public class Hypernym implements Comparable{

    public static final int MIN_SIZE = 3;
    private String name;
    private Map<String, Hyponym> hyponyms;

    public Hypernym(String name) {
        this.name = name;
        this.hyponyms = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public int getOccurs() {
        int counter = 0;
        for (Hyponym hyponym : this.hyponyms.values()) {
            counter += hyponym.getOccur();
        }

        return counter;
    }

    public void addHyponym(String name) {
        String nameUpperCase = name.toUpperCase();
        if (this.hyponyms.containsKey(nameUpperCase)) {
            this.hyponyms.get(nameUpperCase).addOccur();
        } else {
            this.hyponyms.put(nameUpperCase, new Hyponym(name));
        }
    }

    public String toString() {
        List<Hyponym> hyponymList = new ArrayList<>(this.hyponyms.values());
        Collections.sort(hyponymList);

        String string = this.name + ":";
        for (Hyponym hyponym : hyponymList) {
            string += " " + hyponym.toString() + ",";
        }
        return string.substring(0, string.length() - 1);
    }

    public boolean isValid() {
        return (this.hyponyms.size() >= MIN_SIZE);
    }


    @Override
    public int compareTo(Object o) {
        return this.name.toUpperCase().compareTo(((Hypernym) o).getName().toUpperCase());
    }
}
