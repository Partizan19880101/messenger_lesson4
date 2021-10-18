package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.utils.FileReader;
import com.epam.ld.module2.testing.utils.exceptions.NoProvidedValueException;
import org.apache.commons.text.StringSubstitutor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    private final Logger logger = LogManager.getLogger(TemplateEngine.class);
    private final String regexp = "#\\{(.+?)}";
    private final FileReader fileReader = new FileReader();

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String input = template.templateMessage;
        Map<String, String> replacementStrings = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        int increment = 0;
        while (matcher.find()) {
            if (System.getProperty("runMode") != null) {
                List<String> lines = fileReader.getLinesFromFile(System.getProperty("inputFile"));
                try {
                    replacementStrings.put(matcher.group(1), lines.get(increment));
                    increment++;
                } catch (IndexOutOfBoundsException e) {
                    throw new NoProvidedValueException("No value specified for a placeholder");
                }
            } else {
                logger.info("Please enter " + matcher.group(1).toLowerCase() + " :");
                replacementStrings.put(matcher.group(1), scanner.nextLine());
            }
        }

        StringSubstitutor sub = new StringSubstitutor(replacementStrings, "#{", "}");
        return sub.replace(input);
    }
}