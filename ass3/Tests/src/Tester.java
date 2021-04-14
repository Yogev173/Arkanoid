import java.util.Locale;
import java.util.Scanner;

public class Tester {

    static final double COMPARISON_THRESHOLD = 0.00001;
    private static Scanner in = new Scanner(System.in);
    private static final int TABLE_COLUMN_WIDTH = 9;

    private String fileName;
    private int testNumber = 0;

    private int amountOfTestsSucceed = 0;
    private int amountOfTestsFailed = 0;
    private int amountOfTests = 0;

    private boolean isTestSucceed = true;
    private int[] failedNumbers = new int[0];
    private StackTraceElement[] failedLines = new StackTraceElement[0];

    /**
     * Constructor
     * @param fileName the name of the file that been tested.
     */
    public Tester(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Constructor
     */
    public Tester() {
        this(Thread.currentThread().getStackTrace()[2].getFileName());
    }

    public void compare(String rightAnswer, String yourAnswer) {
        boolean isTestSucceed = rightAnswer.equals(yourAnswer);
        this.testsCounter(rightAnswer.equals(yourAnswer), 2);
    }

    /**
     * testCounter.
     * @param isTestSucceed id the test succeeded or failed.
     */
    public void testsCounter(boolean isTestSucceed) {
        this.testsCounter(isTestSucceed, 1);
    }

    /**
     * testCounter.
     * @param isTestSucceed is the test succeeded or failed.
     * @param functionNumber number of function that been called
     */
    public void testsCounter(boolean isTestSucceed, int functionNumber) {
        this.testNumber++;
        this.amountOfTests++;

        //printing the message according to the test result.
        if (isTestSucceed) {
            System.out.println(" NUMBER: " + testNumber + ", LINE: " +
                    Thread.currentThread().getStackTrace()[2 + functionNumber].getLineNumber() + " SUCCEEDED.");
            this.amountOfTestsSucceed++;
        } else {
            System.out.println(" NUMBER: " + testNumber + ", LINE: " +
                    Thread.currentThread().getStackTrace()[2 + functionNumber].getLineNumber() + " FAILED.");
            this.amountOfTestsFailed++;

            int[] newFailedNumbers = new int[this.amountOfTestsFailed];
            StackTraceElement[] newFailedLines = new StackTraceElement[this.amountOfTestsFailed];
            for (int i = 0; i < this.failedNumbers.length; i++) {
                newFailedNumbers[i] = this.failedNumbers[i];
                newFailedLines[i] = this.failedLines[i];
            }
            newFailedNumbers[this.amountOfTestsFailed - 1] = this.testNumber;
            newFailedLines[this.amountOfTestsFailed - 1] = Thread.currentThread().getStackTrace()[2 + functionNumber];

            this.failedNumbers = newFailedNumbers;
            this.failedLines = newFailedLines;
            this.isTestSucceed = false;
        }
    }

    /**
     * Status summery of the result until now.
     */
    public void status() {
        String resultOfTest = "SUCCEEDED";
        if (this.amountOfTestsSucceed != this.amountOfTests) {
            resultOfTest = "FAILED   ";
            printFailed();
        }

        System.out.println("                ****************************************");
        System.out.println("                **                                    **");
        System.out.println("                **              " + resultOfTest + "             **");
        System.out.println("                **                                    **");
        System.out.println("                ****************************************");
    }

    /**
     * print the test that the program failed in.
     */
    public void printFailed() {
        System.out.println("\n\nFAILED in:");
        for (int i = 0; i < this.failedNumbers.length; i++) {
            System.out.println(" -FAILED " + this.failedNumbers[i] + " PATH: " + this.failedLines[i]);
        }
    }

    /**
     * isDoubleTheSame.
     * Check if tow number are the same until 0.00001.
     * @param num1 the first number.
     * @param num2 the second number.
     * @return true if equals, and false otherwise.
     */
    public static boolean isDoubleTheSame(double num1, double num2) {
        if (num1 - COMPARISON_THRESHOLD <= num2 && num2 <= num1 + COMPARISON_THRESHOLD) {
            return true;
        }

        return false;
    }

    /**
     * headLine.
     * print the the current test name.
     */
    public void headLine() {
        System.out.println("Test for " + fileName);
    }

    /* Getters and Setters */
    public int getAmountOfTests() {
        return amountOfTests;
    }

    public int getAmountOfTestsFailed() {
        return amountOfTestsFailed;
    }

    public int getAmountOfTestsSucceed() {
        return amountOfTestsSucceed;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public boolean isTestSucceed() {
        return isTestSucceed;
    }
}

