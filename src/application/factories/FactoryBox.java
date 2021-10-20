package application.factories;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import application.PoseidonApplication;

public class FactoryBox extends BorderPane {
    Image closeButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/delete.png")));
    ImageView closeView = new ImageView(closeButtonImage);

    public FactoryBox(PoseidonApplication.FactoryType factoryType) {
        Label labelFactoryType = new Label();
        Button deleteButton = new Button();

        this.setPadding(new Insets(0, 1, 0, 1));

        labelFactoryType.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        labelFactoryType.setTextFill(Color.DARKSLATEBLUE);
        labelFactoryType.setText(factoryType.name());

        deleteButton.setGraphic(closeView);
        deleteButton.setOnAction(e -> PoseidonApplication.removeFactory(this, factoryType));

        this.setCenter(labelFactoryType);
        this.setRight(deleteButton);
        this.setStyle("-fx-background-insets:0 0 0 0; -fx-background-color:#B7D3FB; -fx-border-color:#78B7CF #78B7CF #F4F4F4 #F4F4F4;");

        this.setOnMousePressed(e -> {
            showFactorySettings(this.getId()+"-SETTINGS");
            colorSelectedFactory(this.getId());
        });
    }

    public void showFactorySettings(String factoryID) {
        List<Node> factoriesSettings = PoseidonApplication.paneSettingsContainer.getChildren().stream().toList();

        for (Node factorySettings : factoriesSettings) {
            if (!factorySettings.getId().equals("#" + factoryID)) {
                factorySettings.setVisible(false);
            }
        }

        PoseidonApplication.paneSettingsContainer.lookup("#" + factoryID).setVisible(true);
    }

    public void colorSelectedFactory(String factoryID) {
        PoseidonApplication.selectedFactoryID = factoryID;
        List<Node> factories = PoseidonApplication.vBoxFactoriesList.getChildren().stream().toList();
        int midSize = (int) PoseidonApplication.vBoxFactoriesList.getPrefWidth()/3;

        for (Node factory : factories) {
           if (!factory.getId().equals("#" + factoryID)) {
               if (PoseidonApplication.isMidSized(factory.getId())) {
                   factory.setStyle("-fx-background-insets:0 0 0 "+midSize+"; -fx-background-color:#B7D3FB; -fx-border-color:#78B7CF #78B7CF #F4F4F4 #F4F4F4;");
               }
               else {
                   factory.setStyle("-fx-background-insets:0 0 0 0; -fx-background-color:#B7D3FB; -fx-border-color:#78B7CF #78B7CF #F4F4F4 #F4F4F4;");
               }
            }
        }

        if (PoseidonApplication.isMidSized(factoryID)) {
            PoseidonApplication.vBoxFactoriesList.lookup("#" + factoryID).setStyle("-fx-background-insets:0 0 0 "+midSize+"; -fx-background-color:#84B3F9; -fx-border-color:#F4F4F4 #F4F4F4 #78B7CF #78B7CF;");
        }
        else {
            PoseidonApplication.vBoxFactoriesList.lookup("#" + factoryID).setStyle("-fx-background-insets:0 0 0 0; -fx-background-color:#84B3F9; -fx-border-color:#F4F4F4 #F4F4F4 #78B7CF #78B7CF;");
        }
    }
}