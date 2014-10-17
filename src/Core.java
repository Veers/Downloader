import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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

    public Core(EnumMap<Flags, String> params) {
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
        for (String str : readFileToArrayList(this.linksFile))
            downloadFile(str.split(" ")[0], str.split(" ")[1]);

    }

    public boolean checkOutputFolderForExist() {
        Path pathToFolder = Paths.get(this.outputFolder);
        return Files.exists(pathToFolder);
    }

    public void createNewFolder() {
        boolean mkdirs = new File(this.outputFolder).mkdirs();
    }

    public boolean checkFile() {
        File f = new File(this.linksFile);
        if (f.exists() && !f.isDirectory()) {
            double bytes = f.length();
            return bytes > 0;
        }
        return false;
    }

    private ArrayList<String> readFileToArrayList(String fileName) {
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

    public void downloadFile(String url, String name) {
        URL website = null;
        try {
            website = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ReadableByteChannel rbc = null;
        try {
            assert website != null;
            rbc = Channels.newChannel(website.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(this.outputFolder + "/" + name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fos != null;
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
