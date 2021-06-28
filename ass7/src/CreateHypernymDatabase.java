import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Create a database of hypernyms and hyponyms.
 */
public class CreateHypernymDatabase {

    private static final String NP_STRING = "<np>[^>]+<\\/np>";
    public static final Pattern NP_PATTERN = Pattern.compile(NP_STRING);
    public static final Pattern[] PATTERNS = {Pattern.compile("" + NP_STRING + "( ,)? such as " + NP_STRING + ""
            + "( ?, " + NP_STRING + ")*( ?,? (and|or) " + NP_STRING + ")?")

            , Pattern.compile("(such|Such) " + NP_STRING + " as " + NP_STRING + "( ?, " + NP_STRING + ")"
            + "*( ?,? (and|or) " + NP_STRING + ")?")

            , Pattern.compile("" + NP_STRING + "( ,)? including " + NP_STRING + "( ?, " + NP_STRING + ")"
            + "*( ?,? (and|or) " + NP_STRING + ")?")

            , Pattern.compile("" + NP_STRING + "( ,)? especially " + NP_STRING + "( ?, " + NP_STRING + ")"
            + "*( ?,? (and|or) " + NP_STRING + ")?")};

    public static final Pattern WHICH_IS_PATTERN = Pattern.compile("" + NP_STRING + "( ,)? which is"
            + "( (an example|a kind|a class) of)? " + NP_STRING + "");

    private HypernymManager manager;

    /**
     * create a database of hypernyms and hyponyms.
     * first-pathToCorpusDirectory
     * second-pathToOutputFile
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String pathToCorpusDirectory = args[0];
        String  pathToOutputFile = args[1];
        File[] files = (new File(pathToCorpusDirectory)).listFiles();

        CreateHypernymDatabase dataBase = new CreateHypernymDatabase();
        for (File file : files) {
            dataBase.scanFile(file);
        }

        dataBase.saveData(pathToOutputFile);
    }

    /**
     * Constructor.
     */
    public CreateHypernymDatabase() {
        this.manager = new HypernymManager();
    }

    /**
     * scanning the data file.
     * @param file the file to scan.
     */
    private void scanFile(File file) {
       BufferedReader reader = null;
       try {
           reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
           String line;
           while ((line = reader.readLine()) != null) {
               this.scanLinePatterns(line);
               this.scanLineWhichIsPattern(line);
           }
       } catch (FileNotFoundException e) {
           System.out.println("Failed founding the: " + file.getAbsolutePath() + "File!");
       } catch (IOException e) {
        System.out.println("Failed reading the: " + file.getAbsolutePath() + "File!");
       } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the File!");
                }
            }
       }
    }

    /**
     * search for patterns in a line.
     * @param line the line to scan.
     */
    private void scanLinePatterns(String line) {
        for (Pattern pattern : PATTERNS) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String string = line.substring(matcher.start(), matcher.end());
                Matcher hearstMatcher = NP_PATTERN.matcher(string);
                hearstMatcher.find();
                String hypernymName = stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end()));

                List<String> hyponymNames = new ArrayList<>();
                while (hearstMatcher.find()) {
                    hyponymNames.add(stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end())));
                }

                this.manager.add(hypernymName, hyponymNames);
            }
        }
    }

    /**
     * search for which is pattern in a line.
     * @param line the line to scan.
     */
    private void scanLineWhichIsPattern(String line) {
        Matcher matcher = WHICH_IS_PATTERN.matcher(line);
        while (matcher.find()) {
            String string = line.substring(matcher.start(), matcher.end());
            Matcher hearstMatcher = NP_PATTERN.matcher(string);

            hearstMatcher.find();
            List<String> hyponymNames = new ArrayList<>();
            hyponymNames.add(stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end())));

            hearstMatcher.find();
            String hypernymName = stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end()));

            this.manager.add(hypernymName, hyponymNames);
        }
    }

    /**
     * get the actual np from an np phrase.
     * @param string the entire np.
     * @return the np itself.
     */
    private static String stripNP(String string) {
        return string.substring(4, string.length() - 5);
    }

    /**
     * write the data to a file.
     * @param pathToOutputFile the path to the file.
     */
    private void saveData(String  pathToOutputFile) {
        this.manager.saveData(pathToOutputFile);
    }

    /**
     * store the information that yet been written to a file.
     */
    private final class HypernymManager {

        private Map<String, Hypernym> hypernyms;

        /**
         * Constructor.
         */
        private HypernymManager() {
            this.hypernyms = new HashMap<>();
        }

        /**
         * add a hypernym and its hyponyms to the collected data.
         * @param hypernymName the name of the Hypernym.
         * @param hyponymNames the names of the Hyponyms.
         */
        private void add(String hypernymName, List<String> hyponymNames) {
            String hypernymNameUpperCase = hypernymName.toUpperCase();
            if (!this.hypernyms.containsKey(hypernymNameUpperCase)) {
                this.hypernyms.put(hypernymNameUpperCase, new Hypernym(hypernymName));
            }

            Hypernym thisHypernym = this.hypernyms.get(hypernymNameUpperCase);
            for (String name : hyponymNames) {
                thisHypernym.addHyponym(name);
            }
        }

        /**
         * @return String representing the object.
         */
        @Override
        public String toString() {
            List<String> names = new ArrayList<>(this.hypernyms.keySet());
            Collections.sort(names);

            String string = "";
            for (String name : names) {
                string += this.hypernyms.get(name).toString() + "\n";
            }

            return string;
        }


        /**
         * write the data to a file.
         * @param pathToOutputFile the path to the file.
         */
        private void saveData(String pathToOutputFile) {
            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(pathToOutputFile));
                List<Hypernym> hypernymsList = new ArrayList<>(this.hypernyms.values());
                Collections.sort(hypernymsList);

                boolean isFirst = true;
                for (Hypernym hyp : hypernymsList) {
                    if (hyp.isValid()) {
                        if (!isFirst) {
                            writer.write("\n" + hyp.toString());

                        } else {
                            isFirst = false;
                            writer.write(hyp.toString());
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Failed founding the: " + pathToOutputFile + "File!");
            } catch (IOException e) {
                System.out.println("Failed writing to the: " + pathToOutputFile + "File!");
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Failed closing the File!");
                    }
                }
            }
        }
    }
}
