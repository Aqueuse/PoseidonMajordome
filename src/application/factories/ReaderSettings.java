package application.factories;

import java.io.File;

import application.PoseidonApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import application.builders.ReadBuilder;

public class ReaderSettings extends Pane {
    TextField filePathTextField = new TextField();
    public ReaderSettings() {
        HBox globalHbox = new HBox();

        Button chooseFileButton = new Button("Select File");
        Button readFileContentButton = new Button("read");

        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(this.getParent().getScene().getWindow());
            if (selectedFile != null) {
                filePathTextField.setText(selectedFile.getAbsolutePath());
            }
        });

        readFileContentButton.setOnAction(e -> {
            if (filePathTextField.getText() != null) {
                String path = filePathTextField.getText();
                File textFile = new File(path);
                if (textFile.exists()) {
                    ReadBuilder readBuilder = new ReadBuilder(new String[]{path});
                    PoseidonApplication.paneLogger.AppendToLogger(PoseidonApplication.dataFlow);
                }
            }
        });

        globalHbox.setAlignment(Pos.CENTER);

        globalHbox.getChildren().add(chooseFileButton);
        globalHbox.getChildren().add(filePathTextField);
        globalHbox.getChildren().add(readFileContentButton);
        this.getChildren().add(globalHbox);
    }

    public String[] getFactoryProperties() {
        return new String[]{filePathTextField.getText()};
    }
}
