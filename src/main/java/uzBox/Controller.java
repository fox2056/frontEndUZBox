package uzBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {



    @FXML
    private TableView<FileModel> tbData;

    @FXML
    private TableColumn<FileModel, String> name;

    @FXML
    private TableColumn<FileModel, String> owner;

    @FXML
    private TableColumn<FileModel, String> systemName;

    @FXML
    private TableColumn<FileModel, String> systemPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        owner.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        systemName.setCellValueFactory(new PropertyValueFactory<>("SystemName"));
        systemPath.setCellValueFactory(new PropertyValueFactory<>("SystemPath"));
        //add your data to the table here.
        tbData.setItems(fileModels);
    }

    private ObservableList<FileModel> fileModels = FXCollections.observableArrayList(
            new FileModel("plik1", "User:/Desktop/Folder/", ".png", "Oleksii"),
            new FileModel("plik2", "User:/System/", ".exe", "Krystian")
            );


    @FXML
    public void ustawieniaWindow(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ustawienia");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Ustawienia.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
        window.showAndWait();
    }
}