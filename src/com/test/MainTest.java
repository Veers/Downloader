package com.test;

import org.apache.commons.cli.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.EnumMap;

public class MainTest {
    @Test
    public void testMain() throws Exception {
        String[] args = "-n 5 -l 2000k -o output_folder -f links.txt".split(" ");

        Option countThreadsOption = new Option("n", "count", true, "ограничивает количество отображаемых изменений");
        Option downloadLimitOption = new Option("l", "limit", true, "ограничивает количество отображаемых изменений");
        Option outputFolderOption = new Option("o", "output_folder", true, "ограничивает количество отображаемых изменений");
        Option linksFileOption = new Option("f", "linksFile", true, "ограничивает количество отображаемых изменений");
        Options options = new Options();
        options.addOption(countThreadsOption);
        options.addOption(downloadLimitOption);
        options.addOption(outputFolderOption);
        options.addOption(linksFileOption);

        CommandLineParser parser = new PosixParser();
        CommandLine line = parser.parse(options, args);
        EnumMap<TestFlags, String> paramsHash = new EnumMap<TestFlags, String>(TestFlags.class);
        if (line.hasOption("n")) {
            paramsHash.put(TestFlags.countThreads, line.getOptionValue("n"));
        }
        if (line.hasOption("l")) {
            paramsHash.put(TestFlags.downloadLimit, line.getOptionValue("l"));
        }
        if (line.hasOption("o")) {
            paramsHash.put(TestFlags.outputFolder,  line.getOptionValue("o"));
        }
        if (line.hasOption("f")) {
            paramsHash.put(TestFlags.linksFile,  line.getOptionValue("f"));
        }
        assertTrue(paramsHash.size() > 0);
    }

    @Test
    public void testStartDownload() throws Exception {

    }
}
