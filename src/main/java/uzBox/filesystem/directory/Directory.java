package uzBox.filesystem.directory;

public class Directory {
    private final String name;
    private final String filesystemname;
    public Directory(String name, String filesystemname) {
        this.name = name;
        this.filesystemname = filesystemname;
    }

    public String getFilesystemname() {
        return filesystemname;
    }

    public String getName() {
        return name;
    }
}
