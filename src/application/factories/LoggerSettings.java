package application.factories;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LoggerSettings extends Pane {
    public LoggerSettings() {
        VBox globalVbox = new VBox();
        Label descriptionLabel = new Label("Simply write the data flow in the Logger tab");

        globalVbox.getChildren().add(descriptionLabel);
        this.getChildren().add(globalVbox);
    }
}
