package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.FileReader;
import com.epam.ld.module2.testing.utils.exceptions.NoProvidedValueException;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MailServerTest {

    MailServer mailServer = new MailServer();
    TemplateEngine templateEngine = new TemplateEngine();
    Messenger messenger = new Messenger(mailServer, templateEngine);
    Client client = new Client();
    Template template = new Template();
    FileReader fileReader = new FileReader();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        System.setProperty("runMode", "file");
        System.setProperty("inputFile", "input");
        System.setProperty("outputFile", "output");
    }

    @Test
    public void testMailServerFileMode() {
        client.setAddresses("testAddress@Gmail.com");
        messenger.sendMessage(client, template);
        String fileOutput = fileReader.getLinesFromFile("output").toString();
        assertTrue(fileOutput.contains(client.getAddresses()), "Output file does not contain client email");
    }

    @Test
    public void testMissedPlaceholderThrowsException() {
        FileReader fileReader = mock(FileReader.class);
        List<String> wrongInput = new LinkedList<>();
        wrongInput.add("wrongValue");
        when(fileReader.getLinesFromFile(eq("input"))).thenReturn(wrongInput);
        assertThrows(NoProvidedValueException.class, () -> {
            templateEngine.generateMessage(template, client);
        });
    }

    @Test
    public void testTemplateEngineIgnoresExcessiveValues() {
        List<String> lines = fileReader.getLinesFromFile("input");
        String excessiveValue = lines.get(lines.size()-1);
        messenger.sendMessage(client, template);
        String fileOutput = fileReader.getLinesFromFile("output").toString();
        assertFalse(fileOutput.contains(excessiveValue), "Output file contains excessive data");
    }

    @AfterEach
    public void tearDown() {
        System.clearProperty("inputFile");
        System.clearProperty("outputFile");
    }
}