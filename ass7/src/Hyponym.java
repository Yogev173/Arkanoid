public class Hyponym implements Comparable{

    private String name;
    private int occur;

    public Hyponym(String name, int occur) {
        this.name = name;
        this.occur = occur;
    }

    public Hyponym(String name) {
        this(name, 1);
    }

    public String getName() {
        return name;
    }

    public int getOccur() {
        return occur;
    }

    public void setOccur(int occur) {
        this.occur = occur;
    }

    public void addOccur() {
        this.setOccur(this.occur + 1);
    }


    public String toString() {
        return this.name + " (" +Integer.toString(this.occur) + ")";
    }

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
