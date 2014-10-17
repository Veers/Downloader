import org.apache.commons.cli.*;

import java.util.EnumMap;

public class Main {

    public static void main(String[] args) throws ParseException {
        //-n 5 -l 2000k -o output_folder -f links.txt
        Option countThreadsOption = new Option("n", "count", true, "ограничивает количество отображаемых изменений");
        Option downloadLimitOption = new Option("l", "limit", true, "ограничивает количество отображаемых изменений");
        Option outputFolderOption = new Option("o", "output_folder", true, "ограничивает количество отображаемых изменений");
        Option linksFileOption = new Option("f", "linksFile", true, "ограничивает количество отображаемых изменений");
        Options options = new Options();
        options.addOption(countThreadsOption);
        options.addOption(downloadLimitOption);
        options.addOption(outputFolderOption);
        options.addOption(linksFileOption);

        System.out.println("Start");

        CommandLineParser parser = new PosixParser();
        CommandLine line = parser.parse(options, args);
        EnumMap<Flags, String> paramsHash = new EnumMap<Flags, String>(Flags.class);
        if (line.hasOption("n")) {
            paramsHash.put(Flags.countThreads, line.getOptionValue("n"));
        }
        if (line.hasOption("l")) {
            paramsHash.put(Flags.downloadLimit, line.getOptionValue("l"));
        }
        if (line.hasOption("o")) {
            paramsHash.put(Flags.outputFolder,  line.getOptionValue("o"));
        }
        if (line.hasOption("f")) {
            paramsHash.put(Flags.linksFile,  line.getOptionValue("f"));
        }
        startDownload(paramsHash);
    }

    public static void startDownload(EnumMap<Flags, String> params) {
        Core core = new Core(params);
        try {
            core.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
