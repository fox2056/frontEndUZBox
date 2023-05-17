package uzBox;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uzBox.user.UserSession;

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

    @FXML
    private Label nazwaUzytkownika;

    @FXML
    private TreeView<String> folderTree;

    @FXML
    private UserSession userSession;

    private ObservableList<FileModel> fileModels = FXCollections.observableArrayList(
            new FileModel("plik1", "User:/Desktop/Folder/", ".png", "Oleksii"),
            new FileModel("plik2", "User:/System/", ".exe", "Krystian")
    );

    private ObservableList<DirectoryModel> folderList = FXCollections.observableArrayList(
            new DirectoryModel("folder1", "folderGlowny"),
            new DirectoryModel("folder2", "folderGlowny"),
            new DirectoryModel("folder3", "folder1")
    );

    TreeItem<String> rootNode =
            new TreeItem<String>("MyCompany Human Resources");


    public void setUserSession(UserSession session){
        userSession = session;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // sciągemy z Sesji nazwe użytkownika
        Platform.runLater(() -> {
            nazwaUzytkownika.setText(this.userSession.getUserName());
                });


        // uzupełniamy TreeView
        rootNode.setExpanded(true);
        for (DirectoryModel folder : folderList) {
            TreeItem<String> empLeaf = new TreeItem<String>(folder.getNazwa());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(folder.getFolderRodzic())){
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<String>(
                        folder.getFolderRodzic()
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        folderTree.setRoot(rootNode);
        // Uzupełniamy tabele plików
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        owner.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        systemName.setCellValueFactory(new PropertyValueFactory<>("SystemName"));
        systemPath.setCellValueFactory(new PropertyValueFactory<>("SystemPath"));
        tbData.setItems(fileModels);
    }

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

    @FXML
    public void loginOut(ActionEvent event) throws IOException {
        //TODO: przeslanie requestu logout do serwera
        userSession.cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addFolder(ActionEvent event) {

    }

    @FXML
    void deleteFolder(ActionEvent event) {

    }

}