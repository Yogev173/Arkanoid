import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * This class consists of a test function for other
 * classes
 */
public class Test {

    public static void main(String[] args) throws Exception {

        /*
        *****************************************************************
        * test for ass1
        ******************************************************************
        * */

        // save old out stream
        PrintStream oldout = System.out;
        // set output to file
        PrintStream out =
                new PrintStream(new FileOutputStream
                        ("output.txt", false), true);
        System.setOut(out);

        //********************************************************************
        //change directories to the ones on your PC

        String SumDivFile = "C:\\Yogev\\BIU\\First year\\Intro to OOP\\HomeWork\\ass1\\src\\SumDivTest.txt";
        String FermatFile = "C:\\Yogev\\BIU\\First year\\Intro to OOP\\HomeWork\\ass1\\src\\FermatTest.txt";
        String StrFile = "C:\\Yogev\\BIU\\First year\\Intro to OOP\\HomeWork\\ass1\\src\\StrTest.txt";
        //********************************************************************

        //create list of paths
        List<String> paths = new ArrayList<String>();
        paths.add(SumDivFile);
        paths.add(FermatFile);
        paths.add(StrFile);

        //test number counter
        int j = 0;
        String testName;
        for (String file : paths) {
            File file1 = new File(file);
            if (j == 0) {
                testName = "SumDiv";
            } else if (j == 1) {
                testName = "Fermat";
            } else {
                testName = "Str";
            }
            System.out.println("tests For: " + testName);
            BufferedReader br = new BufferedReader(new FileReader(file1));
            // creating array of lines in file
            List<String[]> lines = new ArrayList<String[]>();
            String line;
            String[] inputList = new String[0];

            /*
             * run through all lines in file and add
             *  as string list to list of all lines
             */
            while ((line = br.readLine()) != null) {
                /*split long string by underscore and create word list*/
                inputList = line.split(" ");
                lines.add(inputList);
            }

            /*
             * call each line as input of correct function we are at
             */
            int i = 1;
            for (String[] obj :lines) {
                System.out.println("test # " + i);
                if (j == 0) {
                    SumDiv.main(obj);
                } else if (j == 1) {
                    Fermat.main(obj);
                } else {
                    Str.main(obj);
                }
                i++;
            }
            // increase test number counter
            j++;

        }
        System.setOut(oldout);

        //********************************************************************
        //change directories to the ones on your PC

        BufferedReader reader1 = new BufferedReader(new
                FileReader("C:\\Yogev\\BIU\\First year\\Intro to OOP\\HomeWork\\ass1\\src\\output.txt"));
        BufferedReader reader2 = new BufferedReader(new
                FileReader("C:\\Yogev\\BIU\\First year\\Intro to OOP\\HomeWork\\ass1\\src\\expected.txt"));
        //********************************************************************

        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        boolean areEqual = true;
        int lineNum = 1;

        while (line1 != null || line2 != null) {
            if(line1 == null || line2 == null) {
                areEqual = false;

                break;
            } else if(! line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                break;
            }
            line1 = reader1.readLine();

            line2 = reader2.readLine();

            lineNum++;
        }

        if(areEqual) {
            System.out.println("Passed All tests!" +
                    " Two files have same content.");
        } else {
            System.out.println("Test failed!" +
                    " output.txt is not as expected.\n" +
                    "They differ at line "+ lineNum);

            System.out.println("output.txt has-\n"+ line1 +
                    "\nand expected.txt has-\n" + line2 + "\n at line " + lineNum);
        }

        reader1.close();
        reader2.close();
    }
}




