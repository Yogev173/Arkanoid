import java.util.*;

public class Hypernym implements Comparable{

    public static final int MIN_SIZE = 3;
    private String name;
    private Map<String, Hyponym> hyponyms;

    public Hypernym(String name) {
        this.name = name;
        this.hyponyms = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addHyponym(String name) {
        if (this.hyponyms.containsKey(name)) {
            this.hyponyms.get(name).addOccur();
        } else {
            this.hyponyms.put(name, new Hyponym(name));
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
        return this.name.toLowerCase().compareTo(((Hypernym) o).getName().toLowerCase());
    }
}
