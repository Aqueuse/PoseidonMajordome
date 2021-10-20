package application.factories;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

import application.PoseidonApplication;
import application.builders.WriteBuilder;

import java.io.File;

public class WriterSettings extends Pane {
    TextField filePathTextField = new TextField();
    TextField filenameTextField = new TextField();

    public WriterSettings() {
        BorderPane borderPane = new BorderPane();

        HBox directoryHbox = new HBox();
        HBox saveHbox = new HBox();

        Button selectDirectoryButton = new Button("select directory ...");
        Button testButton = new Button("test");

        filePathTextField.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-300);

        borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
        borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight());

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
            filePathTextField.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-300);
        });

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        filePathTextField.setPromptText("directory");
        filenameTextField.setPromptText("filename");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");

        selectDirectoryButton.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(null);
            if (selectedDirectory != null) filePathTextField.setText(selectedDirectory.getAbsolutePath());
        });

        testButton.setOnAction(e -> {
            WriteBuilder writeBuilder = new WriteBuilder(new String[]{filePathTextField.getText(), filenameTextField.getText(), PoseidonApplication.dataFlow});
        });

        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        directoryHbox.setAlignment(Pos.CENTER_LEFT);
        saveHbox.setAlignment(Pos.CENTER_RIGHT);

        directoryHbox.getChildren().add(selectDirectoryButton);
        directoryHbox.getChildren().add(filePathTextField);
        directoryHbox.getChildren().add(new Label(" / "));
        directoryHbox.getChildren().add(filenameTextField);
        saveHbox.getChildren().add(testButton);

        borderPane.setTop(directoryHbox);
        borderPane.setBottom(saveHbox);

        this.getChildren().add(borderPane);
    }
    public String[] getFactoryProperties(String dataFlowElement) {
        String textToWrite;
        if (dataFlowElement != null) {
            System.out.println("replace $dataFlow");
            textToWrite = dataFlowElement;
        }
        else {
            textToWrite = PoseidonApplication.dataFlow;
        }
        return new String[]{filePathTextField.getText(), filenameTextField.getText(), textToWrite};
    }
}
