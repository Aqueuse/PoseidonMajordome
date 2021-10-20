package application.factories;

import application.PoseidonApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import application.builders.RequestBuilder;

public class RequestSettings extends Pane {
    enum requestMethod { GET, POST }
    ChoiceBox<String> choiceDialogProtocol = new ChoiceBox<>(FXCollections.observableArrayList("http://", "https://"));
    TextField textFieldRequestUrl = new TextField();
    TextArea textAreaBodyRequest = new TextArea();
    ChoiceBox<requestMethod> choiceDialogRequestMethod = new ChoiceBox<>();

    public RequestSettings() {
        BorderPane borderPane = new BorderPane();
        HBox tophBOX = new HBox();
        HBox centerhBOX = new HBox();
        HBox bottomhBOX = new HBox();
        Label headLabel = new Label("HEAD ");
        Button testButton = new Button("test");

        ObservableList<requestMethod> requestTypes = FXCollections.observableArrayList(requestMethod.values());

        borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
        borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight());
        textAreaBodyRequest.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-50);

        choiceDialogProtocol.setValue("http://");
        textFieldRequestUrl.setPromptText("domaine.org");
        choiceDialogRequestMethod.setItems(requestTypes);
        choiceDialogRequestMethod.setValue(requestTypes.get(0));

        testButton.setOnMouseClicked(e -> {
            boolean isValidUrl = validateUrl(textFieldRequestUrl.getText());

            if (isValidUrl) {
                textFieldRequestUrl.setStyle("");
                RequestBuilder requestBuild = new RequestBuilder(new String[]{
                        choiceDialogProtocol.getValue(),
                        textFieldRequestUrl.getText(),
                        textAreaBodyRequest.getText(),
                        choiceDialogRequestMethod.getValue().toString(),
                        "application/json"
                });
                PoseidonApplication.applicationMessages.appendToLogger(PoseidonApplication.dataFlow);
            }
            else {
                textFieldRequestUrl.setStyle("-fx-control-inner-background: #FFAEAE; -fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            }
        });
        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        choiceDialogRequestMethod.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldChoice, newChoice) -> {
            if (choiceDialogRequestMethod.getItems().get((Integer)newChoice).toString().equals("POST")) {
                centerhBOX.setVisible(true);
            }
            if (choiceDialogRequestMethod.getItems().get((Integer)newChoice).toString().equals("GET")) {
                centerhBOX.setVisible(false);
            }
        });

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            borderPane.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth());
            textAreaBodyRequest.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-100);
        });

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        tophBOX.setAlignment(Pos.CENTER_LEFT);
        centerhBOX.setPadding(new Insets(5, 5, 5, 5));
        centerhBOX.setVisible(false);
        centerhBOX.setAlignment(Pos.TOP_LEFT);
        bottomhBOX.setAlignment(Pos.CENTER_RIGHT);
        textFieldRequestUrl.setPrefWidth(PoseidonApplication.paneSettingsContainer.getWidth()-150);
        headLabel.setAlignment(Pos.CENTER_LEFT);

        tophBOX.getChildren().add(choiceDialogRequestMethod);
        tophBOX.getChildren().add(choiceDialogProtocol);
        tophBOX.getChildren().add(textFieldRequestUrl);
        centerhBOX.getChildren().add(headLabel);
        centerhBOX.getChildren().add(textAreaBodyRequest);
        bottomhBOX.getChildren().add(testButton);

        borderPane.setTop(tophBOX);
        borderPane.setCenter(centerhBOX);
        borderPane.setBottom(bottomhBOX);

        this.getChildren().add(borderPane);

    }

    private boolean validateUrl(String urlWithoutProtocol) {
        return urlWithoutProtocol.startsWith("http://") |
                urlWithoutProtocol.startsWith("https://") |
                urlWithoutProtocol.contains(".");
    }

    public String[] getFactoryProperties (String dataFlowElement) {
        if (dataFlowElement != null) {
            System.out.println("replace $dataFlow");
            // search for $dataFlow in fields
            // replace it with dataFlowElement
        }
        return new String[]{
                choiceDialogProtocol.getValue(),
                textFieldRequestUrl.getText(),
                textAreaBodyRequest.getText(),
                String.valueOf(choiceDialogRequestMethod.getValue()),
                "application/json"
        };
    }
}
