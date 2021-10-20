package application.factories;

import java.io.File;

import application.PoseidonApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import application.builders.ReadBuilder;

public class ReaderSettings extends Pane {
    TextField filePathTextField = new TextField();
    public ReaderSettings() {
        BorderPane borderPane = new BorderPane();
        HBox topHbox = new HBox();
        HBox bottomHbox = new HBox();

        Button chooseFileButton = new Button("Select File ...");
        Button testButton = new Button("test");

        filePathTextField.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-100);
        filePathTextField.setPromptText("file path");

        borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
        borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight());

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
            filePathTextField.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-100);
        });

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(this.getParent().getScene().getWindow());
            if (selectedFile != null) {
                filePathTextField.setText(selectedFile.getAbsolutePath());
            }
        });

        testButton.setOnAction(e -> {
            if (!filePathTextField.getText().equals("")) {
                String path = filePathTextField.getText();
                File textFile = new File(path);
                if (textFile.exists()) {
                    ReadBuilder readBuilder = new ReadBuilder(new String[]{path});
                    PoseidonApplication.applicationMessages.appendToLogger(PoseidonApplication.dataFlow);
                }
            }
            else {
                PoseidonApplication.applicationMessages.appendWarningToLogger("READ","file not defined");
            }
        });
        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        topHbox.setAlignment(Pos.CENTER_LEFT);
        bottomHbox.setAlignment(Pos.CENTER_RIGHT);

        topHbox.getChildren().add(chooseFileButton);
        topHbox.getChildren().add(filePathTextField);
        bottomHbox.getChildren().add(testButton);

        borderPane.setTop(topHbox);
        borderPane.setBottom(bottomHbox);

        this.getChildren().add(borderPane);
    }

    public String[] getFactoryProperties(String dataFlowElement) {
        if (!dataFlowElement.equals("")) {
            System.out.println("replace $dataFlow");
            // search for $dataFlow in fields
            // replace it with dataFlowElement
        }
        return new String[]{filePathTextField.getText()};
    }
}
