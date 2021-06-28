import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * Discover Hypernym of a lemma.
 */
public class DiscoverHypernym {

    private static final Pattern NP_PATTERN = CreateHypernymDatabase.NP_PATTERN;
    private static final Pattern[] PATTERNS = CreateHypernymDatabase.PATTERNS;
    private static final Pattern WHICH_IS_PATTERN = CreateHypernymDatabase.WHICH_IS_PATTERN;

    private HypernymManager manager;
    private String lemma;

    /**
     * found the hypernyms that related to the lemma.
     * first-pathToCorpusDirectory
     * second-the lemma
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String pathToCorpusDirectory = args[0];
        String  lemma = args[1];
        File[] files = (new File(pathToCorpusDirectory)).listFiles();

        DiscoverHypernym discoverHypernym = new DiscoverHypernym(lemma);
        for (File file : files) {
            discoverHypernym.scanFile(file);
        }

        discoverHypernym.printHypernyms();
    }

    /**
     * Constructor.
     * @param lemma the lemma to find it Hypernyms.
     */
    public DiscoverHypernym(String lemma) {
        this.manager = new HypernymManager();
        this.lemma = lemma;
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
                    String hypoName = stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end()));
                    if (hypoName.equals(this.lemma)) {
                        hyponymNames.add(hypoName);
                    }
                }

                if (hyponymNames.size() != 0) {
                    this.manager.add(hypernymName, hyponymNames);
                }
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
            String hypoName = stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end()));
            if (hypoName.equals(this.lemma)) {
                hyponymNames.add(hypoName);

                hearstMatcher.find();
                String hypernymName = stripNP(string.substring(hearstMatcher.start(), hearstMatcher.end()));

                this.manager.add(hypernymName, hyponymNames);
            }
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
     * print all the Hypernyms related to the lemma.
     */
    private void printHypernyms() {
        this.manager.printHypernyms();
    }

    /**
     * store the information of Hypernyms related to the lemma.
     */
    private class HypernymManager {

        private Map<String, Hypernym> hypernyms;

        /**
         * Constructor.
         */
        public HypernymManager() {
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
         * print the Hypernyms related to the lemma.
         */
        private void printHypernyms() {
            List<Hypernym> hypernymsList = new ArrayList<>(this.hypernyms.values());
            Collections.sort(hypernymsList, new HypernymComparatorByOccur());

            boolean isFirst = true;
            for (Hypernym hyp : hypernymsList) {
                if (!isFirst) {
                    System.out.println("\n" + hyp.getName() + ": (" + hyp.getOccurs() + ")");

                } else {
                    isFirst = false;
                    System.out.println(hyp.toString());
                }
            }
        }
    }
}
