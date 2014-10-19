import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader implements Runnable {
    private String url;
    private String name;
    private String destination;

    public Downloader(String url, String name, String destination) {
        this.url = url;
        this.name = name;
        this.destination = destination;
    }

    public synchronized void downloadFile() {
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
            fos = new FileOutputStream(this.destination + "/" + this.name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fos != null;
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            //this.fileSizes.add(fos.getChannel().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        downloadFile();
    }
}
