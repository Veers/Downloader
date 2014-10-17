public interface ThreadAbstractFactory {
    public Thread createNewThread();
    public Thread searchFreeThread();
    public void setData(String src, String dest);
    public FileInfo getInfo();
}
