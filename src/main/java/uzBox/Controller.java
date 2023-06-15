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
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uzBox.filesystem.directory.DirectoryController;
import uzBox.filesystem.file.FileModel;
import uzBox.popups.AlertBox;
import uzBox.popups.InputDialog;
import uzBox.user.session.authorization.SessionController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private SessionController sessionController;

    @FXML
    private DirectoryController directoryController;

    private final ObservableList<FileModel> fileModels = FXCollections.observableArrayList(
            new FileModel("plik1", "User:/Desktop/Folder/", ".png", "Oleksii"),
            new FileModel("plik2", "User:/System/", ".exe", "Krystian")
    );

    /*
    private ObservableList<DirectoryModel> folderList = FXCollections.observableArrayList(
            new DirectoryModel("folder1", "folderGlowny"),
            new DirectoryModel("folder2", "folderGlowny"),
            new DirectoryModel("folder3", "folder1")
    );
*/
    TreeItem<String> rootNode =
            new TreeItem<String>("Folder główny");


    public void setUserSession(SessionController session) {
        sessionController = session;
    }

    public void setDirectoryController(DirectoryController directoryController) {
        this.directoryController = directoryController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // sciągemy z Sesji nazwe użytkownika
        Platform.runLater(() -> {
            nazwaUzytkownika.setText(this.sessionController.getUsernane());
            System.out.println(this.sessionController);
            rootNode.setExpanded(true);
            folderTree.setRoot(rootNode);
            directoryController.listDirectory("/")
                    .forEach(directory -> {
                        TreeItem<String> leaf = new TreeItem<>(directory.getFilesystemname());
                        leaf.setExpanded(true);
                        folderTree.getRoot()
                                .getChildren()
                                .add(leaf);
                    });
            folderTree.refresh();
        });


        // uzupełniamy TreeView

        /*
        for (DirectoryModel folder : folderList) {
            TreeItem<String> empLeaf = new TreeItem<String>(folder.getDirectoryName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(folder.getParentDirectoryName())){
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<String>(
                        folder.getParentDirectoryName()
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }
*/

        folderTree.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                TreeItem<String> selectedItem = folderTree.getSelectionModel()
                        .getSelectedItem();
                selectedItem.setExpanded(true);
                if (selectedItem.isLeaf()) {
                    String path = selectedItem.getValue();
                    directoryController.listDirectory(path + "/")
                            .forEach(directory -> {
                                TreeItem<String> leaf = new TreeItem<>(directory.getFilesystemname());
                                selectedItem.getChildren().add(leaf);
                            });
                }
                folderTree.refresh();
            }
        });

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
        AlertBox result = new AlertBox();
        String response = sessionController.logoutSession();
        if (response == "200") {
            result.alertOk("Wylogowanie użytkownia", "Wylogowano użytkowania", "OK");
        } else {
            result.alertErr("Wylogowanie użytkownika", "Błąd podczas wylogowania", response);
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/uzBox/loginForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene()
                .getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addFolder(ActionEvent event) {
        TreeItem<String> selectedItem = folderTree.getSelectionModel()
                .getSelectedItem();
        InputDialog inputDialog = new InputDialog("Dodaj folder", "Podaj nazwę folderu", selectedItem.getValue() + "/");
        inputDialog.createFolderDialog()
                .showAndWait()
                .ifPresent(inputvalue -> {
                    String status = directoryController.createDirectory(selectedItem.getValue() + "/" + inputvalue);
                    if(status != "200"){
                        AlertBox alertBox = new AlertBox();
                        alertBox.alertErr("Błąd", "Błąd podczas tworzenia", status);
                    }
                    selectedItem.getChildren().clear();
                    directoryController.listDirectory(selectedItem.getValue());
                    selectedItem.setExpanded(true);
                });

    }

    @FXML
    void deleteFolder(ActionEvent event) {
        TreeItem<String> selectedItem = folderTree.getSelectionModel().getSelectedItem();
        AlertBox dialog = new AlertBox();
        Optional<ButtonType> result = dialog.confirmationDialog("Usuniecie pliku", "Czy na pewno chcesz usunac ten folder?", "Potwierdz usuniecie pliku")
                .showAndWait();
        if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.YES){
          directoryController.deleteDirectory(selectedItem.getValue());
          selectedItem.getChildren().clear();
          directoryController.listDirectory(selectedItem.getValue());
          selectedItem.setExpanded(true);
        }
        //result.ifPresent(System.out::println);
    }

}