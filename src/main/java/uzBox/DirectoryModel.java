package uzBox;

import javafx.beans.property.SimpleStringProperty;

public class DirectoryModel {
    private final SimpleStringProperty nazwa;
    private final SimpleStringProperty folderRodzic;

    public DirectoryModel(String nazwa, String folderRodzic) {
        this.nazwa = new SimpleStringProperty(nazwa);
        this.folderRodzic = new SimpleStringProperty(folderRodzic);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getFolderRodzic() {
        return folderRodzic.get();
    }

    public SimpleStringProperty folderRodzicProperty() {
        return folderRodzic;
    }

    public void setFolderRodzic(String folderRodzic) {
        this.folderRodzic.set(folderRodzic);
    }
}
