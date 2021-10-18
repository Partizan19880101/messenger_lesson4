package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngineTest {

    private final TemplateEngine testTemplateEngine = new TemplateEngine();
    private final Client testClient = new Client();
    private final Template testTemplate = new Template();

    @BeforeEach
    public void init() {
        System.setProperty("runMode", "file");
        System.setProperty("inputFile", "input");
    }

    @Test
    public void testTemplateEngineReplacesPlaceholders() {
        String regexp = "#\\{(.+?)}";
        Pattern pattern = Pattern.compile(regexp);

        String generatedMessage = testTemplateEngine.generateMessage(testTemplate, testClient);
        Matcher matcher = pattern.matcher(generatedMessage);
        Assertions.assertFalse(matcher.find(), "Placeholders are still in your template!");
    }
}