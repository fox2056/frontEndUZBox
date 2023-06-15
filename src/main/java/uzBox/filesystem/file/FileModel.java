package uzBox.filesystem.file;

import javafx.beans.property.SimpleStringProperty;

public class FileModel {

    private SimpleStringProperty name;
    private SimpleStringProperty systemPath;
    private SimpleStringProperty systemName;
    private SimpleStringProperty owner;

    public FileModel(String name, String systemPath, String systemName, String owner) {
        this.name = new SimpleStringProperty(name);
        this.systemPath = new SimpleStringProperty(systemPath);
        this.systemName = new SimpleStringProperty(systemName);
        this.owner = new SimpleStringProperty(owner);
    }

    public String getName() {
        return name.get();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public String getSystemPath() {
        return systemPath.get();
    }


    public void setSystemPath(String systemPath) {
        this.systemPath.set(systemPath);
    }

    public String getSystemName() {
        return systemName.get();
    }

    public SimpleStringProperty systemNameProperty() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName.set(systemName);
    }

    public String getOwner() {
        return owner.get();
    }

    public SimpleStringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }
}
