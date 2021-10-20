package application.factories;

import application.PoseidonApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LoggerSettings extends Pane {
    public LoggerSettings() {
        BorderPane borderPane = new BorderPane();
        HBox centerHbox = new HBox();
        HBox bottomHbox = new HBox();

        Label mascottWords = new Label("Hi, test me if you dare ! \n I'm Poseidon Majordome");
        Image mascottImage = new Image(String.valueOf(PoseidonApplication.class.getResource("mascott.png")));
        ImageView mascottView = new ImageView(mascottImage);
        Button testButton = new Button("test");

        mascottView.setPreserveRatio(true);
        mascottView.setFitHeight(PoseidonApplication.paneSettingsContainer.getHeight()-50);

        borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
        borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight());

        bottomHbox.setAlignment(Pos.CENTER_RIGHT);

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) ->
                borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()));

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        testButton.setOnAction(e -> {
           PoseidonApplication.applicationMessages.appendSuccessToLogger("LOG", "You have successfully writed in the LOG pane");
            PoseidonApplication.applicationMessages.appendToLogger(
                    """
                            I am at your service to accelerate your use of data science
                            the LOG factory will print here your dataflow
                            you can also save it in a file with the WRITE factory
                    """
            );
        });
        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        centerHbox.setAlignment(Pos.CENTER);
        centerHbox.getChildren().add(mascottWords);
        centerHbox.getChildren().add(mascottView);
        bottomHbox.getChildren().add(testButton);

        borderPane.setCenter(centerHbox);
        borderPane.setBottom(bottomHbox);

        this.getChildren().add(borderPane);
    }
}
