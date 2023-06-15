package uzBox.popups;

import javafx.scene.control.TextInputDialog;

public class InputDialog {
    private final String title;
    private final String headerText;
    private final String contentText;
    private final TextInputDialog textInputDialog;

    public InputDialog(String title, String headerText, String contentText) {
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
        textInputDialog = new TextInputDialog();
    }

    public TextInputDialog createFolderDialog(){
        textInputDialog.setTitle(title);
        textInputDialog.setContentText(contentText);
        textInputDialog.setHeaderText(headerText);
        return this.textInputDialog;
    }

}
