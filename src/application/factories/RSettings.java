package application.factories;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import application.builders.RBuilder;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import application.PoseidonApplication;

public class RSettings extends Pane {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    String codePath;

    public RSettings(PoseidonApplication.FactoryType factoryType) {
        this.setId(factoryType.name()+"-SETTINGS");
        this.setVisible(false);

        BorderPane borderPane = new BorderPane();
        HBox bottomRightHBOX = new HBox();

        Button saveButton = new Button("save");
        saveButton.setOnAction(e -> {
            String aceEditorContent = webView.getEngine().executeScript("editor.getValue()").toString();

            if (codePath == null || !Files.exists(Paths.get(codePath))) {
                FileChooser fileChooser = new FileChooser();
                File fileToSave = fileChooser.showSaveDialog(null);
                codePath = fileToSave.getAbsoluteFile().getAbsolutePath();
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(codePath).toFile()));
                writer.write(aceEditorContent);
                writer.close();
                PoseidonApplication.applicationMessages.appendSuccessToLogger("R", "File saved to "+codePath);
            }
            catch (IOException ioException) {
                PoseidonApplication.applicationMessages.appendWarningToLogger("R", ioException.getMessage());
            }
        });

        Button saveAsButton = new Button("save as ...");
        saveAsButton.setOnAction(e -> {
            String aceEditorContent = webView.getEngine().executeScript("editor.getValue()").toString();

            FileChooser fileChooser = new FileChooser();
            File fileToSave = fileChooser.showSaveDialog(null);
            if (fileToSave != null) codePath = fileToSave.getAbsoluteFile().getAbsolutePath();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(codePath).toFile()));
                writer.write(aceEditorContent);
                writer.close();
                PoseidonApplication.applicationMessages.appendSuccessToLogger("R", "File saved to "+codePath);
            }
            catch (IOException ioException) {
                PoseidonApplication.applicationMessages.appendWarningToLogger("R", ioException.getMessage());
            }
        });

        Button openButton = new Button("open");
        openButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File fileToOpen = fileChooser.showOpenDialog(null);
            if (fileToOpen != null) codePath = fileToOpen.getAbsoluteFile().getAbsolutePath();

            StringBuilder codeContent = new StringBuilder();
            String line;

            if (fileToOpen != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToOpen.getAbsoluteFile()));
                    while ((line = bufferedReader.readLine()) != null) {
                        codeContent.append(line);
                    }
                } catch (IOException fileNotFoundException) {
                    PoseidonApplication.applicationMessages.appendWarningToLogger("R", fileNotFoundException.getMessage());
                }

                String codeContentString = codeContent.toString();
                codeContentString = codeContentString.replaceAll("'", "\\\\'");
                codeContentString = codeContentString.replaceAll("\"", "\\\\\"");

                webView.getEngine().executeScript("editor.setValue('"+codeContentString+"')");
            }
        });

        Button testButton = new Button("test");
        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");
        testButton.setOnAction(e -> {
            String aceEditorContent = webView.getEngine().executeScript("editor.getValue()").toString();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get("./", "temp.r").toFile()));
                writer.write(aceEditorContent);
                writer.close();
                RBuilder rBuilder = new RBuilder(new String[]{"./temp.r"});
            }
            catch (IOException ioException) {
                PoseidonApplication.applicationMessages.appendWarningToLogger("R", ioException.getMessage());
            }
        });

        webEngine.setJavaScriptEnabled(true);
        webEngine.load(Objects.requireNonNull(PoseidonApplication.class.getResource("ressources/ace/editor.html")).toExternalForm());

        bottomRightHBOX.setAlignment(Pos.CENTER_RIGHT);
        bottomRightHBOX.getChildren().addAll(saveButton, saveAsButton, testButton);

        borderPane.setTop(webView);
        borderPane.setLeft(openButton);
        borderPane.setRight(bottomRightHBOX);

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) ->
                borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()));


        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) -> {
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight());
                webView.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()-30);
        });

        this.getChildren().add(borderPane);
    }

    public void flushParameters() {
        webEngine.load(Objects.requireNonNull(PoseidonApplication.class.getResource("ressources/ace/editor.html")).toExternalForm());
        codePath = null;
    }

    public String[] getFactoryProperties () {
        return new String[]{codePath};
    }
}
