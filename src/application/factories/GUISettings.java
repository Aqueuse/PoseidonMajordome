package application.factories;

import application.PoseidonApplication;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class GUISettings extends Pane {
    public GUISettings(PoseidonApplication.FactoryType factoryType) {
        double initialWidth = (Screen.getPrimary().getBounds().getWidth()/1.5)/2;

        this.setId(factoryType.name()+"-SETTINGS");
        this.setVisible(false);

        BorderPane borderPane = new BorderPane();

        borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
        borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight());

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) ->
                borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()));

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        this.getChildren().add(borderPane);
    }

    public void flushParameters() {
        // flush parameters to blank
    }

    public String[] getFactoryProperties () {
        return new String[]{"gui parameters"};
    }
}
