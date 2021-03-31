import java.util.Locale;
import java.util.Scanner;

public class Tester {

    private static Scanner in = new Scanner(System.in);
    private static final int TABLE_COLUMN_WIDTH = 9;

    private String fileName;
    private int testNumber = 0;
    private int amountOfTestsSucceed = 0;
    private int amountOfTestsFailed = 0;
    private int amountOfTests;
    private int[] failedNumbers = new int [0];

    /**
     * Constructor
     * @param fileName the name of the file that been tested.
     * @param amountOfTests num of test to perform.
     */
    public Tester(String fileName, int amountOfTests) {
        this.fileName = fileName;
        this.amountOfTests = amountOfTests;
    }

    /**
     * Constructor
     * @param amountOfTests num of test to perform.
     */
    public Tester(int amountOfTests) {
        this.amountOfTests = amountOfTests;
    }

    /**
     * Constructor
     * @param fileName the name of the file that been tested.
     */
    public Tester(String fileName) {
        this(fileName, 100);
    }

    /**
     * Constructor
     */
    public Tester() {
        this("", 100);
    }

    /**
     * printStatus.
     * @param isTestSucceed id the test succeeded or failed.
     */
    public void printMessage(boolean isTestSucceed) {
        if (this.testNumber == this.amountOfTests) {
            System.out.println("All test have been done already,\n Chose How to continue:" +
                    "\n I - try to increase the amount of Tests," +
                    "\n S - or print status (summery)" +
                    "\n N - nan of the above");
            String userChoice = in.next().toUpperCase(Locale.ROOT);
            if (userChoice.equals("I")) {
                System.out.println("Enter the new amount of tests");
                setAmountOfTests(in.nextInt());
            } else if (userChoice.equals("S")) {
                System.out.println("THE TEST DIDN'T COUNT!!!");
                status();
                return;
            } else {
                System.out.println("THE TEST DIDN'T COUNT!!!");
                return;
            }
        }
        this.testNumber++;

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
        }
    }

    /**
     * Status summery of the result until now.
     */
    public void status() {

        String testNumber = String.valueOf(this.testNumber);
        String amountOfTestsSucceed = String.valueOf(this.amountOfTestsSucceed);
        String amountOfTestsFailed = String.valueOf(this.amountOfTestsFailed);
        String amountOfTestsRemind = String.valueOf(this.amountOfTests - this.testNumber);
        String amountOfTests = String.valueOf(this.amountOfTests);

        System.out.println("\n\n                     "+ fileName +" status            ");
        System.out.println("          | Performed | SUCCEEDED | FAILED    | Remind    | Total");
        System.out.println("  ---------------------------------------------------------------");

        //printing second row - number
        System.out.print("  Numbers | ");
        System.out.print(testNumber);
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - testNumber.length());
        System.out.print(amountOfTestsSucceed);
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - amountOfTestsSucceed.length());
        System.out.print(amountOfTestsFailed);
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - amountOfTestsFailed.length());
        System.out.print(amountOfTestsRemind);
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - amountOfTestsRemind.length());
        System.out.println(amountOfTests);

        // printing third line - Present
        String testNumberP = String.valueOf(this.testNumber * 100 / this.amountOfTests);
        String amountOfTestsSucceedP = String.valueOf(this.amountOfTestsSucceed * 100 / this.amountOfTests);
        String amountOfTestsFailedP = String.valueOf(this.amountOfTestsFailed * 100 / this.amountOfTests);
        String amountOfTestsRemindP = String.valueOf((this.amountOfTests - this.testNumber) * 100 / this.amountOfTests);
        String amountOfTestsP = String.valueOf(this.amountOfTests * 100 / this.amountOfTests);

        System.out.print("  Present | ");
        System.out.print(testNumberP + "%");
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - testNumberP.length() - 1);
        System.out.print(amountOfTestsSucceedP + "%");
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - amountOfTestsSucceedP.length() - 1);
        System.out.print(amountOfTestsFailedP + "%");
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - amountOfTestsFailedP.length() - 1);
        System.out.print(amountOfTestsRemindP + "%");
        printSpaceAndBreak(TABLE_COLUMN_WIDTH - amountOfTestsRemindP.length() - 1);
        System.out.println(amountOfTestsP + "%");
        System.out.print("\n");

        //If Test done
        if(this.testNumber == this.amountOfTests) {
            printFailure();
        }
    }

    public void printFailure() {
        String resultOfTest = "SUCCEEDED";
        if (this.amountOfTestsSucceed != this.testNumber) {
            resultOfTest = "FAILED   ";
            printFailed();
        }

        System.out.println("                ****************************************");
        System.out.println("                **                                    **");
        System.out.println("                **              " + resultOfTest + "             **");
        System.out.println("                **                                    **");
        System.out.println("                ****************************************");
    }

    private void printSpaceAndBreak(int numOfSpace) {
        for (int i = 0; i < numOfSpace; i++) {
            System.out.print(" ");
        }

        System.out.print(" | ");
    }

    public void printFailed() {
        System.out.println("FAILED in:");
        for (int i = 0; i < this.failedNumbers.length; i++) {
            System.out.println(" -FAILED " + this.failedNumbers[i]);
        }
    }

    /* Getters and Setters */
    public int getAmountOfTests() {
        return amountOfTests;
    }

    public void setAmountOfTests(int amountOfTests) {
        this.amountOfTests = amountOfTests;
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

    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }
}
