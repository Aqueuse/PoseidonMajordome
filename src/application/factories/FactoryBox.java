package application.factories;

import java.util.List;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import application.PoseidonApplication;

public class FactoryBox extends BorderPane {
    String emptyBoxStyle = "-fx-border-style: segments(10, 15, 10, 15)  line-cap round ; -fx-border-width: 2; -fx-border-color: gray";
    String inactiveBoxStyle = "-fx-background-insets:0 0 0 0; -fx-background-color:#B7D3FB; -fx-border-color:#F4F4F4 #F4F4F4 #78B7CF #78B7CF;";
    String activeBoxStyle = "-fx-background-insets:0 0 0 0; -fx-background-color:#84B3F9; -fx-border-color:#F4F4F4 #F4F4F4 #78B7CF #78B7CF;";

    public FactoryBox(PoseidonApplication.FactoryType factoryType) {
        Image closeButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("ressources/icons/close.png")));
        ImageView closeView = new ImageView(closeButtonImage);

        Label labelFactoryType = new Label();
        Button addButton = new Button();
        Button closeButton = new Button();

        this.setId(factoryType.name());
        this.setPadding(new Insets(0, 1, 0, 1));

        labelFactoryType.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        labelFactoryType.setTextFill(Color.DARKSLATEBLUE);
        labelFactoryType.setUnderline(true);
        labelFactoryType.setText("add "+factoryType.name().toLowerCase(Locale.ROOT));

        closeButton.setGraphic(closeView);
        closeButton.setVisible(false);

        closeButton.setOnAction(e -> {
            this.setStyle(emptyBoxStyle);
            labelFactoryType.setText("add " + factoryType.name().toLowerCase(Locale.ROOT));
            labelFactoryType.setUnderline(true);
            closeButton.setVisible(false);

            switch (this.getId()) {
                case "REQUEST" -> {
                    PoseidonApplication.requestSettings.flushParameters();
                    PoseidonApplication.requestSettings.setVisible(false);
                }
                case "R" -> {
                    PoseidonApplication.rSettings.flushParameters();
                    PoseidonApplication.rSettings.setVisible(false);
                }
                case "GUI" -> {
                    PoseidonApplication.guiSettings.flushParameters();
                    PoseidonApplication.guiSettings.setVisible(false);
                }
            }
        });

        labelFactoryType.setOnMouseClicked(e -> {
            labelFactoryType.setText(factoryType.name());
            labelFactoryType.setUnderline(false);
            this.setStyle(activeBoxStyle);
            closeButton.setVisible(true);

            switch (factoryType.name()) {
                case "REQUEST" -> {
                    PoseidonApplication.requestSettings.setVisible(true);
                    PoseidonApplication.rSettings.setVisible(false);
                    PoseidonApplication.guiSettings.setVisible(false);
                }
                case "R" -> {
                    PoseidonApplication.requestSettings.setVisible(false);
                    PoseidonApplication.rSettings.setVisible(true);
                    PoseidonApplication.guiSettings.setVisible(false);
                }
                case "GUI" -> {
                    PoseidonApplication.requestSettings.setVisible(false);
                    PoseidonApplication.rSettings.setVisible(false);
                    PoseidonApplication.guiSettings.setVisible(true);
                }
            }
        });

        this.setCenter(labelFactoryType);
        this.setRight(closeButton);
        this.setStyle(emptyBoxStyle);

        this.setOnMousePressed(e -> {
            showFactorySettings(this);
            colorSelectedFactory(this);
        });
    }

    public void showFactorySettings(FactoryBox factoryBox) {
        String factorySettingsID = this.getId()+"-SETTINGS";
        String factoryStyle = this.getStyle();

        List<Node> factoriesSettings = PoseidonApplication.paneSettingsContainer.getChildren().stream().toList();

        for (Node factorySettings : factoriesSettings) {
            if (!factorySettings.getId().equals("#" + factorySettingsID)) {
                factorySettings.setVisible(false);
            }
        }

        if (!factoryStyle.equals(emptyBoxStyle)) {
            PoseidonApplication.paneSettingsContainer.lookup("#" + factorySettingsID).setVisible(true);
        }
    }

    public void colorSelectedFactory(FactoryBox factoryBox) {
        String factoryID = factoryBox.getId();
        String factoryStyle = factoryBox.getStyle();

        List<Node> factories = PoseidonApplication.vBoxFactoriesList.getChildren().stream().toList();

        for (Node factory : factories) {
            if (!factory.getStyle().equals(emptyBoxStyle)) {
                factory.setStyle(inactiveBoxStyle);
            }
        }

        if (!factoryStyle.equals(emptyBoxStyle)) {
            PoseidonApplication.vBoxFactoriesList.lookup("#" + factoryID).setStyle(activeBoxStyle);
        }
    }
}