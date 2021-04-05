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
    private int[] failedNumbers = new int [0];

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
        this("Test");
    }

    /**
     * testCounter.
     * @param isTestSucceed id the test succeeded or failed.
     */
    public void testsCounter(boolean isTestSucceed) {
        this.testNumber++;
        this.amountOfTests++;

        //printing the message according to the test result.
        if (isTestSucceed) {
            System.out.println("Test for " + fileName + " number: " + testNumber + " SUCCEEDED.");
            this.amountOfTestsSucceed++;
        } else {
            System.out.println("Test for " + fileName + " number: " + testNumber + " FAILED. xxxxx");
            this.amountOfTestsFailed++;
            int[] newFailedNumbers = new int[this.amountOfTestsFailed];
            for (int i = 0; i < this.failedNumbers.length; i++) {
                newFailedNumbers[i] = this.failedNumbers[i];
            }
            newFailedNumbers[this.amountOfTestsFailed - 1] = this.testNumber;
            this.failedNumbers = newFailedNumbers;
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
        System.out.println("FAILED in:");
        for (int i = 0; i < this.failedNumbers.length; i++) {
            System.out.println(" -FAILED " + this.failedNumbers[i]);
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

