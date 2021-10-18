package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.TemplateEngine;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Mail server class.
 */
public class MailServer {

    private final Logger logger = LogManager.getLogger(MailServer.class);

    /**
     * Send notification.
     *
     * @param addresses      the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        String content = "Just sent message to the following addresses: " + addresses +
                ". Message content: \n" + messageContent;
        if (System.getProperty("outputFile") != null) {
            try {
                Files.write(Paths.get("src/test/resources/").resolve(System.getProperty("outputFile")), content.getBytes());
            } catch (IOException e) {
                logger.error("Failed to write to file: " + e.getLocalizedMessage());
            }
        } else {
            logger.info(content);
        }
    }
}