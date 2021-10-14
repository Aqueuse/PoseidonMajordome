package application.composants;

import application.PoseidonApplication;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LoggerTab extends Tab {
    public static HBox loggerHBOX = new HBox();
    String loggerMessage = "";
    TextArea loggerTextArea = new TextArea();

    public LoggerTab() {
        Pane loggerPane = new Pane();

        Image cleanButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/clean.png")));
        ImageView cleanView = new ImageView(cleanButtonImage);

        ToolBar verticalLoggerToolbar = new ToolBar();
        Button cleanLogButton = new Button();

        verticalLoggerToolbar.setOrientation(Orientation.VERTICAL);
        cleanLogButton.setGraphic(cleanView);

        cleanLogButton.setOnAction(e -> PoseidonApplication.paneLogger.clearLogger());

        verticalLoggerToolbar.getItems().add(cleanLogButton);

        this.setText("Logger");
        loggerTextArea.setWrapText(true);
        loggerTextArea.setEditable(false);
        loggerTextArea.setText(loggerMessage);
        loggerTextArea.setPrefWidth(10000);
        loggerTextArea.setPrefHeight(10000);

        loggerHBOX.getChildren().add(verticalLoggerToolbar);
        loggerHBOX.getChildren().add(loggerTextArea);
        loggerPane.getChildren().add(loggerHBOX);
        this.setContent(loggerPane);
    }

    public void writeInLogger(String message) {
        loggerTextArea.setText(message);
        this.getTabPane().getSelectionModel().select(this);
    }

    public void AppendToLogger(String message) {
        loggerMessage = loggerTextArea.getText()+message+"\n";
        loggerTextArea.setText(loggerMessage);
        this.getTabPane().getSelectionModel().select(this);
    }

    public void clearLogger() {
        loggerMessage = "";
        loggerTextArea.setText(loggerMessage);
    }
}
