package application.factories;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import application.PoseidonApplication;
import application.builders.RequestBuilder;

@SuppressWarnings("InstantiationOfUtilityClass")
public class RequestSettings extends Pane {
    enum requestMethod { GET, POST }
    ChoiceBox<String> choiceDialogProtocol = new ChoiceBox<>(FXCollections.observableArrayList("http://", "https://"));
    TextField textFieldRequestUrl = new TextField();
    TextArea textAreaBodyRequest = new TextArea();
    ChoiceBox<requestMethod> choiceDialogRequestMethod = new ChoiceBox<>();
    ObservableList<requestMethod> requestTypes = FXCollections.observableArrayList(requestMethod.values());

    TextField textFieldResponsePath = new TextField();

    public RequestSettings(PoseidonApplication.FactoryType factoryType) {
        this.setId(factoryType.name()+"-SETTINGS");
        this.setVisible(false);

        BorderPane borderPane = new BorderPane();
        HBox tophBOX = new HBox();
        HBox centerhBOX = new HBox();

        VBox fileVBOX = new VBox();
        HBox directoryHBOX = new HBox();

        Label headLabel = new Label("BODY ");
        Button testButton = new Button("test");

        VBox bottomVBOX = new VBox();
        HBox bottomHBOX = new HBox();

        textFieldResponsePath.setPromptText("response filepath");

        choiceDialogProtocol.setValue("http://");
        textFieldRequestUrl.setPromptText("domaine.org");
        choiceDialogRequestMethod.setItems(requestTypes);
        choiceDialogRequestMethod.setValue(requestTypes.get(0));

        choiceDialogRequestMethod.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldChoice, newChoice) -> {
            if (choiceDialogRequestMethod.getItems().get((Integer)newChoice).toString().equals("POST")) {
                centerhBOX.setVisible(true);
            }
            if (choiceDialogRequestMethod.getItems().get((Integer)newChoice).toString().equals("GET")) {
                centerhBOX.setVisible(false);
            }
        });

        Button saveAsButton = new Button("save response as ...");
        saveAsButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File fileToSave = fileChooser.showSaveDialog(null);
            if (fileToSave != null) {
                textFieldResponsePath.setText(fileToSave.getAbsoluteFile().getAbsolutePath());
                textFieldResponsePath.setStyle("");
            }
        });

        testButton.setOnMouseClicked(e -> {
            boolean isValidUrl = validateUrl(textFieldRequestUrl.getText());

            if (textFieldResponsePath.getText().equals("")) {
                textFieldResponsePath.setStyle("-fx-control-inner-background: #FFAEAE; -fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
                FileChooser fileChooser = new FileChooser();
                File fileToSave = fileChooser.showSaveDialog(null);
                if (fileToSave != null) {
                    textFieldResponsePath.setText(fileToSave.getAbsoluteFile().getAbsolutePath());
                }
            }

            if (!textFieldResponsePath.getText().equals("")) {
                textFieldResponsePath.setStyle("");
            }

            if (isValidUrl) {
                textFieldRequestUrl.setStyle("");
                RequestBuilder requestBuild = new RequestBuilder(new String[]{
                        choiceDialogProtocol.getValue(),
                        textFieldRequestUrl.getText(),
                        textAreaBodyRequest.getText(),
                        choiceDialogRequestMethod.getValue().toString(),
                        "application/json",
                        textFieldResponsePath.getText()
                });
            }
            if (!isValidUrl) {
                textFieldRequestUrl.setStyle("-fx-control-inner-background: #FFAEAE; -fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            }
        });
        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            borderPane.setPrefWidth((double)newSettingsContainerWidth);
            textAreaBodyRequest.setPrefWidth((double)newSettingsContainerWidth-50);
            textFieldRequestUrl.setPrefWidth((double)newSettingsContainerWidth-100);
            textFieldResponsePath.setPrefWidth((double)newSettingsContainerWidth-120);
        });

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        tophBOX.setAlignment(Pos.CENTER_LEFT);
        centerhBOX.setPadding(new Insets(5, 5, 5, 5));
        centerhBOX.setVisible(false);
        centerhBOX.setAlignment(Pos.TOP_LEFT);
        bottomHBOX.setAlignment(Pos.CENTER_RIGHT);
        headLabel.setAlignment(Pos.CENTER_LEFT);

        tophBOX.getChildren().addAll(choiceDialogRequestMethod, choiceDialogProtocol, textFieldRequestUrl);
        centerhBOX.getChildren().addAll(headLabel, textAreaBodyRequest);

        directoryHBOX.getChildren().addAll(saveAsButton, textFieldResponsePath);
        bottomHBOX.getChildren().add(testButton);
        bottomVBOX.getChildren().addAll(directoryHBOX, bottomHBOX);

        borderPane.setTop(tophBOX);
        borderPane.setCenter(centerhBOX);
        borderPane.setBottom(bottomVBOX);

        this.getChildren().add(borderPane);
    }

    private boolean validateUrl(String urlWithoutProtocol) {
        return urlWithoutProtocol.startsWith("http://") |
                urlWithoutProtocol.startsWith("https://") |
                urlWithoutProtocol.contains(".");
    }

    public void flushParameters() {
        choiceDialogRequestMethod.setValue(requestTypes.get(0));
        choiceDialogProtocol.setValue("http://");
        textFieldRequestUrl.setText("");
        textAreaBodyRequest.setText("");
        textFieldResponsePath.setText("");
    }

    public String[] getFactoryProperties () {
        return new String[]{
                choiceDialogProtocol.getValue(),
                textFieldRequestUrl.getText(),
                textAreaBodyRequest.getText(),
                String.valueOf(choiceDialogRequestMethod.getValue()),
                "application/json",
                textFieldResponsePath.getText()
        };
    }
}
