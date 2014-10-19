public class ThreadFactory implements ThreadAbstractFactory {
    private final ThreadGroup downloadGroup;

    public ThreadFactory() {
        downloadGroup = new ThreadGroup("downloadThreads");
    }

    @Override
    public Thread createNewEmptyThread() {
        return null;
    }

    @Override
    public Thread createThreadWithTask(String url, String name, String dest) {
        return new Thread(downloadGroup, new Downloader(url, name, dest));
    }

    @Override
    public Thread searchFreeThread() {
        if (downloadGroup == null)
            throw new NullPointerException("Null thread group");
        int nAlloc = downloadGroup.activeCount();
        int cnt = 0;
        Thread[] threads;
        do {
            nAlloc *= 2;
            threads = new Thread[nAlloc];
            cnt = downloadGroup.enumerate(threads);
        } while (cnt == nAlloc);
        Thread[] arrThreads = java.util.Arrays.copyOf(threads, cnt);
        return null;
    }

    @Override
    public void setData(String src, String dest) {

    }

    @Override
    public FileInfo getInfo() {
        return null;
    }
}
