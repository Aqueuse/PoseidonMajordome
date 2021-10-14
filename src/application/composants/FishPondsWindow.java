package application.composants;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FishPondsWindow extends Stage {
    public FishPondsWindow() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        this.setWidth(screenBounds.getWidth()/3);
        this.setHeight(screenBounds.getHeight()/3);

        TextArea explanationFishPonds = new TextArea("a window with a list of datascience API to use and a button to prepare a request to use it");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(explanationFishPonds);

        Scene scene = new Scene(borderPane);
        this.setScene(scene);

        this.show();
    }
}
