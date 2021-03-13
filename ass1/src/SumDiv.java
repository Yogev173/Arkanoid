/**
 * @author yogev abarbanel
 */
public class SumDiv {

    /**
     * Main
     * print all the number that between 1-first arguments, that divided by the second or third arguments.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid input");
            return;
        }

        int sum = 0;
        int limit = Integer.valueOf(args[0]);
        int divider1 = Integer.valueOf(args[1]), divider2 = Integer.valueOf(args[2]);

        /* checking all the arguments are a positive number. */
        if (limit <= 0 || divider1 <= 0 || divider2 <= 0) {
            System.out.println("Invalid input");
            return;
        }

        /* printing the number that between 1-first arguments, that divided by the second or third arguments. */
        for (int num = 1; num <= limit; num++) {
            if (num % divider1 == 0 || num % divider2 == 0) {
                System.out.println(num);
                sum += num;
            }
        }

        System.out.println(sum);
    }
}
