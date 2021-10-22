package application.factories;

import java.util.Objects;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import application.PoseidonApplication;

public class RSettings extends Pane {
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    public RSettings(PoseidonApplication.FactoryType factoryType) {
        this.setId(factoryType.name()+"-SETTINGS");
        this.setVisible(false);

        BorderPane borderPane = new BorderPane();
        Button testButton = new Button("test");
        Button saveButton = new Button("save");

        webEngine.setJavaScriptEnabled(true);
        webEngine.load(Objects.requireNonNull(PoseidonApplication.class.getResource("ressources/ace/editor.html")).toExternalForm());

        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        borderPane.setTop(webView);
        borderPane.setLeft(saveButton);
        borderPane.setRight(testButton);

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
    }

    public String[] getFactoryProperties () {
        return new String[]{"gui parameters"};
    }
}
