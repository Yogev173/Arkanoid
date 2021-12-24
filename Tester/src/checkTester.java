public class checkTester {

    public static void main(String[] args) {
        Tester tester = new Tester("checkTester");
        stam();

    }

    /** Get the current line number.
     * @return int - Current line number.
     */
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }
    public static void stam() {
        Tester tester = new Tester();
        tester.testsCounter(true);
        tester.testsCounter(false);

        tester.testsCounter(true);
        tester.testsCounter(false);
        tester.status();
    }
}
