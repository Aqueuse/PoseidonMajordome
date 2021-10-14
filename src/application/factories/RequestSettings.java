package application.factories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import application.builders.RequestBuilder;

public class RequestSettings extends Pane {
    enum requestMethod { GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH }
    TextField textFieldRequestUrl = new TextField();
    ChoiceBox<String> choiceDialogProtocol = new ChoiceBox<>(FXCollections.observableArrayList("http://", "https://"));
    ChoiceBox<requestMethod> choiceDialogRequestMethod = new ChoiceBox<>();

    public RequestSettings() {
        BorderPane borderPane = new BorderPane();
        Button buttonExecuteRequest = new Button("exécuter");

        ObservableList<requestMethod> requestTypes = FXCollections.observableArrayList(requestMethod.values());

        choiceDialogProtocol.setValue("http://");
        textFieldRequestUrl.setPromptText("domaine.org");
        choiceDialogRequestMethod.setItems(requestTypes);
        choiceDialogRequestMethod.setValue(requestTypes.get(0));

    buttonExecuteRequest.setOnMouseClicked(e -> {
            boolean isValidUrl = validateUrl(textFieldRequestUrl.getText());

            if (isValidUrl) {
                textFieldRequestUrl.setStyle("");
                RequestBuilder requestBuild = new RequestBuilder(new String[]{
                        choiceDialogRequestMethod.getValue().toString(),
                        choiceDialogProtocol.getValue() + textFieldRequestUrl.getText(),
                        "application/json"}
                );
            }
            else {
                textFieldRequestUrl.setStyle("-fx-control-inner-background: #FFAEAE; -fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            }
        });

        borderPane.setLeft(choiceDialogProtocol);
        borderPane.setCenter(textFieldRequestUrl);
        borderPane.setRight(choiceDialogRequestMethod);
        borderPane.setBottom(buttonExecuteRequest);

        this.getChildren().add(borderPane);
    }

    private boolean validateUrl(String urlWithoutProtocol) {
        return urlWithoutProtocol.startsWith("http://") |
                urlWithoutProtocol.startsWith("https://") |
                urlWithoutProtocol.contains(".");
    }

    public String[] getFactoryProperties () {
        return new String[]{
                choiceDialogProtocol.getValue(),
                textFieldRequestUrl.getText(),
                String.valueOf(choiceDialogRequestMethod.getValue()),
                "application/json"
        };
    }
}
