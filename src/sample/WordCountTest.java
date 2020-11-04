package sample;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WordCountTest {

    @Test
    public void canFindFile() throws Exception {

        File currentDirectory = new File("./src/sample");
        File fileToRead = null;
        String files = Arrays.toString(currentDirectory.listFiles(//Filter out the text files
                (dir, name) -> name.endsWith(".txt")
        ));
        files.replaceAll("]", "");
        files.replaceAll("\\[", "");

        Assert.assertEquals("[.\\src\\sample\\test.txt]", files);

    }

}