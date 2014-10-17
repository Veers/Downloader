import java.util.concurrent.TimeUnit;

public class FileInfo {
    private long time;
    private String l_time;
    private long size;
    private String l_size;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setL_size() {
        int unit = 1000;
        if (getSize() < unit) this.l_size = getSize() + " B";
        int exp = (int) (Math.log(getSize()) / Math.log(unit));
        String pre = ("kMGTPE").charAt(exp - 1) + ("");
        this.l_size = String.format("%.1f %sB", getSize() / Math.pow(unit, exp), pre);
    }

    public String getL_size() {
        return this.l_size == null ? this.l_size : "";
    }

    public void setL_time() {
        this.l_time = String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(getTime()),
                TimeUnit.MILLISECONDS.toSeconds(getTime()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getTime()))
        );
    }

    public String getL_time() {
        return this.l_time == null ? this.l_time : "";
    }
}
