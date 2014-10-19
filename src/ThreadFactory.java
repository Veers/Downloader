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
        final Thread[] arrThreads = getAllThreadFromGroup(downloadGroup);

        for (Thread thread : arrThreads)
            if (thread.getState() == Thread.State.WAITING)
                return thread;
        return null;
    }

    @Override
    public Thread[] searchFreeThreads() {
        final Thread[] arrThreads = getAllThreadFromGroup(downloadGroup);

        final Thread[] found = new Thread[arrThreads.length];
        int nFound = 0;
        for (Thread thread : arrThreads)
            if (thread.getState() == Thread.State.WAITING)
                found[nFound++] = thread;
        return java.util.Arrays.copyOf(found, nFound);
    }

    @Override
    public void setData(String src, String dest) {

    }

    @Override
    public FileInfo getInfo() {
        return null;
    }

    public Thread[] getAllThreadFromGroup(ThreadGroup threadGroup) throws NullPointerException {
        if (threadGroup == null)
            throw new NullPointerException("Null thread group");
        int nAlloc = threadGroup.activeCount();
        int cnt = 0;
        Thread[] threads;
        do {
            nAlloc *= 2;
            threads = new Thread[nAlloc];
            cnt = threadGroup.enumerate(threads);
        } while (cnt == nAlloc);
        return java.util.Arrays.copyOf(threads, cnt);
    }
}
