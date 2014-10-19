public interface ThreadAbstractFactory {
    public Thread createNewEmptyThread();
    public Thread createThreadWithTask(String url, String name, String dest);
    public Thread searchFreeThread();
    public Thread[] searchFreeThreads();
    public void setData(String src, String dest);
    public FileInfo getInfo();
}
