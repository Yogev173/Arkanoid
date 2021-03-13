/**
 * @author yogev abarbanel
 */
public class Str {

    /**
     * Main
     * print 2 groups:
     * 1- all the String that start with the query.
     * 2- all the String that contains the query, and not at group 1.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input");
            return;
        }

        String query = args[0];
        String[] sequenceArray = args[1].split("_");

        /*printing the first group (all the String that start with the query). */
        for (String str : sequenceArray) {
            if (str.indexOf(query) == 0) {
                System.out.println(str);
            }
        }

        /*printing the second group (all the String that contains the query, and not at group 1). */
        for (String str : sequenceArray) {
            if (str.contains(query) && str.indexOf(query) != 0) {
                System.out.println(str);
            }
        }
    }
}
