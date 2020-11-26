package tests.files;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.FileUtils;
import utils.ZipUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.FileUtils.readStringFromFile;

public class ZipTests {

    @Test
    void verifyContentInTxtTest() {
        String source = "src/test/resources/html/1.zip";
        String destination = "src/test/resources/html/unzip";

        String expectedFileText = "hello from qa.guru";

        new ZipUtils().unzip(source, destination);
        sleep(1000);

        String actualFileText = readStringFromFile(destination + "/1.txt");
        System.out.println("Actual text from file: \n" + actualFileText);

        assertTrue(actualFileText.contains(expectedFileText));

    }
}
