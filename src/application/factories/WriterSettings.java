package application.factories;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import application.PoseidonApplication;
import application.builders.WriteBuilder;

import java.io.File;

public class WriterSettings extends Pane {
    TextField filePathTextField = new TextField();
    TextField filenameTextField = new TextField();

    public WriterSettings() {
        VBox globalVbox = new VBox();
        HBox directoryHbox = new HBox();
        HBox filenameHbox = new HBox();
        HBox saveHbox = new HBox();

        Button selectDirectoryButton = new Button("select directory ...");
        Button saveFileButton = new Button("Save on File");

        filePathTextField.setPromptText("directory");
        filenameTextField.setPromptText("filename");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");

        selectDirectoryButton.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(null);
            if (selectedDirectory != null) filePathTextField.setText(selectedDirectory.getAbsolutePath());
        });

        saveFileButton.setOnAction(e -> {
            WriteBuilder writeBuilder = new WriteBuilder(new String[]{filePathTextField.getText(), filenameTextField.getText()});
            PoseidonApplication.paneLogger.AppendToLogger(PoseidonApplication.dataFlow);
        });

        directoryHbox.setAlignment(Pos.CENTER_LEFT);
        filenameHbox.setAlignment(Pos.CENTER_LEFT);
        saveHbox.setAlignment(Pos.CENTER_RIGHT);

        directoryHbox.getChildren().add(filePathTextField);
        directoryHbox.getChildren().add(selectDirectoryButton);
        filenameHbox.getChildren().add(filenameTextField);
        saveHbox.getChildren().add(saveFileButton);

        globalVbox.getChildren().add(directoryHbox);
        globalVbox.getChildren().add(filenameHbox);
        globalVbox.getChildren().add(saveHbox);

        this.getChildren().add(globalVbox);
    }
    public String[] getFactoryProperties() {
        return new String[]{filePathTextField.getText(), filenameTextField.getText()};
    }
}
