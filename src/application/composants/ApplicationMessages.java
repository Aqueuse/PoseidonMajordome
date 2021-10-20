package application.composants;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import application.PoseidonApplication;

public class ApplicationMessages extends Pane {
    String loggerMessage = "";
    public static TextFlow loggerTextFlow = new TextFlow();

    Image cleanButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/clean.png")));
    ImageView cleanView = new ImageView(cleanButtonImage);

    ToolBar verticalLoggerToolbar = new ToolBar();
    Button cleanLogButton = new Button();

    public ApplicationMessages() {
        HBox loggerHBOX = new HBox();
        ScrollPane textFlowScrollPane = new ScrollPane();

        verticalLoggerToolbar.setOrientation(Orientation.VERTICAL);
        cleanLogButton.setGraphic(cleanView);

        textFlowScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loggerTextFlow.setPadding(new Insets(10,20,10,10));
        loggerTextFlow.setLineSpacing(2);

        textFlowScrollPane.vvalueProperty().bind(loggerTextFlow.heightProperty());

        cleanLogButton.setOnAction(e -> clearLogger());
        cleanLogButton.setTooltip(new Tooltip("clean the logger"));

        verticalLoggerToolbar.getItems().add(cleanLogButton);

        textFlowScrollPane.setContent(loggerTextFlow);
        loggerHBOX.getChildren().add(verticalLoggerToolbar);
        loggerHBOX.getChildren().add(textFlowScrollPane);

        this.getChildren().add(loggerHBOX);
    }

    public void writeInLogger(String message) {
        clearLogger();
        Text loggerMessageText = new Text(message);
        loggerTextFlow.getChildren().add(loggerMessageText);
    }

    public void appendToLogger(String message) {
        Text logMessageText = new Text(loggerMessage+"[LOG]\n");
        logMessageText.setFill(Color.GREEN);
        loggerTextFlow.getChildren().add(logMessageText);

        Text loggerMessageText = new Text(loggerMessage+message+"\n");
        loggerTextFlow.getChildren().add(loggerMessageText);
    }

    public void appendWarningToLogger(String factoryType, String warningMessage) {
        Text warningMessageText = new Text(loggerMessage+"["+factoryType+"]"+" Warning : "+warningMessage+"\n");
        warningMessageText.setFill(Color.RED);
        loggerTextFlow.getChildren().add(warningMessageText);
    }

    public void appendSuccessToLogger(String factoryType, String successMessage) {
        Text successMessageText = new Text(loggerMessage+"["+factoryType+"]"+" Success : "+successMessage+"\n");
        successMessageText.setFill(Color.GREEN);
        loggerTextFlow.getChildren().add(successMessageText);
    }

    public void clearLogger() {
        loggerMessage = "";
        loggerTextFlow.getChildren().clear();
    }
}

