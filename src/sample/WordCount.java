package sample;

//Author Name: Alex Porter
//Date: 10/11/2020
//Program Name: WordCount
//Purpose: Counts the words in test.txt

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//Uncomment if including Jsoup
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.parser.Parser;

class WordCount {

    public static final int DISPLAY_COUNT = 20; //We want to display 20 words
    public static final boolean USE_FILE = true; //Didn't realize it had to be from a file so heres the swap

    public static HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

    public static void start() throws IOException {

        String text = "";

        if(!USE_FILE) {
            System.out.println("Downloading text from website");
            //======= Uncomment if including Jsoup =======
            //Define the URL and connect to it
            //String url = "http://shakespeare.mit.edu/macbeth/full.html";
            //Document doc = Jsoup.connect(url).get();

            //Get all BlockQuote Elements and add in the <a> tag (for some reason text is stored in there)
            //text = doc.select("blockquote").text();
            //text += doc.select("a").text();
            //System.out.println("Completed downloading text from [" + url + "]");
        }
        else {

            File currentDirectory = new File("./src/sample");
            File fileToRead = null;

            String files = Arrays.toString(currentDirectory.listFiles(//Filter out the text files
                    (dir, name) -> name.endsWith(".txt")
            ));

            if(files.equals("null")) {
                System.exit(1);
            }

            //Create the file
            fileToRead = new File(
                    files
                            .replaceAll("]", "")
                            .replaceAll("\\[", "")
            );

            System.out.println("Opening up file found in directory [" + fileToRead + "]");

            //Create the Scanner
            Scanner fileReader = new Scanner(fileToRead);
            while(fileReader.hasNextLine()) {
                text += (fileReader.nextLine() + " ");
            }

        }

        //processing text
        System.out.println("Processing text");
        processText(text);

    }

    public static void processText(String text) {

        String cleanedText = cleanText(text);

        //Split all of the text into individual words
        String[] words = cleanedText.split(" ");
        for(String s : words) {
            if(wordCount.containsKey(s)) {
                //if it does have the word, add 1 to it's appearance
                wordCount.put(s, wordCount.get(s) + 1);
            }
            else {
                //if it doesn't have the word, it needs to be added with a appearance of 1
                wordCount.put(s, 1);
            }
        }

        //Now it needs to be sorted so we can tell what is actually being found
        //So this is pretty complicated, but essentially what we're doing is taking advantage
        //of the lambda feature in java. We are "streaming" the values through a compare function
        //and then adding them to the sorted word count.

        HashMap<String, Integer> sortedWordCount =
                wordCount.entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue(Collections.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new
                        ));

        //displayText(sortedWordCount);
        wordCount = sortedWordCount;

    }

    public static HashMap<String, Integer> returnText() {
        return wordCount;
    }

    public static void displayText(HashMap<String, Integer> wordCount) {

        for(int i = 0; i < DISPLAY_COUNT; i++) {
            System.out.println("[" + (i + 1) + "] Word: "
                    +  wordCount.keySet().toArray()[i]
                    + " was used "
                    + wordCount.get(wordCount.keySet().toArray()[i])
                    + " times");
        }

    }

    public static String cleanText(String text) {

        //We need to clean the text up, get rid of punctuation etc
        text = text.replaceAll("[.]", "");
        text = text.replaceAll("[?]", "");
        text = text.replaceAll("[!]", "");
        text = text.replaceAll("[,]", "");
        text = text.replaceAll("[:]", "");
        text = text.replaceAll("[;]", "");
        text = text.replaceAll("[{]", "");
        text = text.replaceAll("[-]", " ");

        //This is all case sensitive so we need all lowercase letters
        text = text.toLowerCase();

        return text;

    }

}

