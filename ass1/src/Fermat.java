/**
 * @author yogev abarbanel
 */
public class Fermat {

    /**
     * Main
     * print all the pythagorean equation, from power of the first argument in args, and all the number smaller then
     * the second arguments of args.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input");
            return;
        }

       int power = Integer.valueOf(args[0]), range = Integer.valueOf(args[1]);
       /* checking all the arguments are a positive number. */
       if (power <= 0 || range <= 0) {
           System.out.println("Invalid input");
           return;
       } else if (power > 2 || pythagoreanEquation(power, range) == 0) {
           System.out.println("no");
           return;
       }
    }

    /**
     * pythagoreanEquation
     * print all the pythagorean equation, from power of power (the param), and all the number smaller then range.
     * @param power the power of the pythagorean equation.
     * @param range the limit of the number at the pythagorean equation.
     * @return int, the number of the pythagorean equation.
     */
    public static int pythagoreanEquation(int power, int range) {
        int numOfEquation = 0;

        /* going throw all the option for the first number */
        for (int number1 = 1; number1 < range; number1++) {
            /* going throw all the option for the second number */
            for (int number2 = number1; number2 < range; number2++) {
                /* going throw all the option for the third number */
                for (int number3 = number2; number3 < range; number3++) {
                    /* checking if the pythagorean equation correct */
                    if (Math.pow(number1, power) + Math.pow(number2, power) == Math.pow(number3, power)) {
                        System.out.println(number1 + "," + number2 + "," + number3);
                        numOfEquation++;
                    }
                }
            }
        }
        return numOfEquation;
    }
}
