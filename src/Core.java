import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

public class Core {
    protected final String linksFile;
    protected final String outputFolder;
    protected final String downloadLimit;
    protected final int countThreads;
    protected ArrayList<Long> fileSizes;

    public Core(EnumMap<Flags, String> params) {
        this.fileSizes = new ArrayList<Long>();
        this.linksFile = params.get(Flags.linksFile);
        this.outputFolder = params.get(Flags.outputFolder);
        this.downloadLimit = params.get(Flags.downloadLimit);
        this.countThreads = params.get(Flags.countThreads) != null ? Integer.parseInt(params.get(Flags.countThreads)) : 0;
    }

    public void start() throws Exception {
        if (!checkOutputFolderForExist())
            createNewFolder();
        if (!checkFile())
            throw new Exception("File not exist/No data in file: " + this.linksFile);
        ArrayList<String> filesRecords = readFileDataToArrayListByLines(this.linksFile);
        for (String str : filesRecords) {
            //TODO
        }

    }

    public boolean checkOutputFolderForExist() {
        Path pathToFolder = Paths.get(this.outputFolder);
        return Files.exists(pathToFolder);
    }

    public void createNewFolder() {
        new File(this.outputFolder).mkdirs();
    }

    public boolean checkFile() {
        File f = new File(this.linksFile);
        if (f.exists() && !f.isDirectory()) {
            double bytes = f.length();
            return bytes > 0;
        }
        return false;
    }

    private ArrayList<String> readFileDataToArrayListByLines(String fileName) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean controlDownloading(int controlCount) {
        int count = 0;
        File f = new File(this.outputFolder);
        File[] files = f.listFiles();

        if (files != null)
            for (File file : files) {
                count++;
            }
        return count == controlCount;
    }
}
