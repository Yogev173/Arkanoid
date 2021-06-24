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
public class CreateHypernymDatabase {

    private final Pattern NP_PATTERN = Pattern.compile("<np>[\\w\\s]+<\\/np>");
    private static final Pattern[] PATTERNS = {Pattern.compile("<np>[\\w\\s]+<\\/np>,? such as <np>[\\w\\s]+<\\/np>( ?, <np>[\\w\\s]+<\\/np>)*( ?,? (and|or) <np>[\\w\\s]+<\\/np>)?")

            , Pattern.compile("(such|Such) <np>[\\w\\s]+<\\/np> as <np>[\\w\\s]+<\\/np>( ?, <np>[\\w\\s]+<\\/np>)*( ?,? (and|or) <np>[\\w\\s]+<\\/np>)?")

            , Pattern.compile("<np>[\\w\\s]+<\\/np>,? including <np>[\\w\\s]+<\\/np>( ?, <np>[\\w\\s]+<\\/np>)*( ?,? (and|or) <np>[\\w\\s]+<\\/np>)?")

            , Pattern.compile("<np>[\\w\\s]+<\\/np>,? especially <np>[\\w\\s]+<\\/np>( ?, <np>[\\w\\s]+<\\/np>)*( ?,? (and|or) <np>[\\w\\s]+<\\/np>)?")};

    private static final Pattern WHICH_IS_PATTERN = Pattern.compile("<np>[\\w\\s]+<\\/np>,? which is( (an example|a kind|a class) of)? <np>[\\w\\s]+<\\/np>");

    private HypernymManager manager;

    public static void main(String[] args) {
        String pathToCorpusDirectory = args[0];
        String  pathToOutputFile = args[1];
        File[] files = (new File(pathToCorpusDirectory)).listFiles();

        CreateHypernymDatabase DataBase = new CreateHypernymDatabase();
        for (File file : files) {
            DataBase.scanFile(file);
        }

        DataBase.saveData(pathToOutputFile);
    }

    public CreateHypernymDatabase() {
        this.manager = new HypernymManager();
    }

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

    private static String stripNP(String string) {
        return string.substring(4, string.length() - 5);
    }

    private void saveData(String  pathToOutputFile) {
        this.manager.saveData(pathToOutputFile);
    }

    private class HypernymManager {

        private Map<String, Hypernym> hypernyms;

        public HypernymManager() {
            this.hypernyms = new HashMap<>();
        }

        private void add(String hypernymName, List<String> hyponymNames) {
            if (!this.hypernyms.containsKey(hypernymName)) {
                this.hypernyms.put(hypernymName, new Hypernym(hypernymName));
            }

            for (String name : hyponymNames) {
                this.hypernyms.get(hypernymName).addHyponym(name);
            }
        }

        @Override
        public String toString() {
            List<String> names = new ArrayList<>(this.hypernyms.keySet());
            Collections.sort(names);

            String string = "";
            for (String name : names) {
                string += this.hypernyms.get(name).toString() +"\n";
            }

            return string;
        }

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
