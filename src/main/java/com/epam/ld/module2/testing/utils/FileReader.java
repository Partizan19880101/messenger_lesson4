package com.epam.ld.module2.testing.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to read lines from a file.
 */
public class FileReader {

    private final Logger logger = LogManager.getLogger(FileReader.class);

    /**
     * @param fileName name of file in resources folder.
     * @return Collection of file lines converted to strings.
     */

    public List<String> getLinesFromFile(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/").resolve(fileName))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Failed to read data from file " + e.getLocalizedMessage());
        }
        return Collections.emptyList();
    }
}