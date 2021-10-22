package application.factories;

import java.io.File;

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
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import application.PoseidonApplication;
import application.builders.RequestBuilder;

public class RequestSettings extends Pane {
    enum requestMethod { GET, POST }
    ChoiceBox<String> choiceDialogProtocol = new ChoiceBox<>(FXCollections.observableArrayList("http://", "https://"));
    TextField textFieldRequestUrl = new TextField();
    TextArea textAreaBodyRequest = new TextArea();
    ChoiceBox<requestMethod> choiceDialogRequestMethod = new ChoiceBox<>();
    ObservableList<requestMethod> requestTypes = FXCollections.observableArrayList(requestMethod.values());

    TextField filePathTextField = new TextField();
    TextField filenameTextField = new TextField();

    public RequestSettings(PoseidonApplication.FactoryType factoryType) {
        this.setId(factoryType.name()+"-SETTINGS");
        this.setVisible(false);

        BorderPane borderPane = new BorderPane();
        HBox tophBOX = new HBox();
        HBox centerhBOX = new HBox();

        VBox fileVBOX = new VBox();
        HBox directoryHBOX = new HBox();

        VBox bottomVBOX = new VBox();
        HBox bottomhBOX = new HBox();

        Label headLabel = new Label("BODY ");
        Button testButton = new Button("test");

        Button selectDirectoryButton = new Button("select directory ...");

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

        filePathTextField.setPromptText("directory");
        filenameTextField.setPromptText("filename");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");

        selectDirectoryButton.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(null);
            if (selectedDirectory != null) filePathTextField.setText(selectedDirectory.getAbsolutePath());
        });

        testButton.setOnMouseClicked(e -> {
            boolean isValidUrl = validateUrl(textFieldRequestUrl.getText());

            if (isValidUrl) {
                textFieldRequestUrl.setStyle("");
                RequestBuilder requestBuild = new RequestBuilder(new String[]{
                        choiceDialogProtocol.getValue(),
                        textFieldRequestUrl.getText(),
                        textAreaBodyRequest.getText(),
                        choiceDialogRequestMethod.getValue().toString(),
                        "application/json",
                        filePathTextField.getText(),
                        filenameTextField.getText()
                });
            }
            else {
                textFieldRequestUrl.setStyle("-fx-control-inner-background: #FFAEAE; -fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            }
        });
        testButton.setStyle("-fx-background-color: #2A7FF0; -fx-text-fill: white; -fx-font-weight: bold");

        PoseidonApplication.paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            borderPane.setPrefWidth((double)newSettingsContainerWidth);
            textAreaBodyRequest.setPrefWidth((double)newSettingsContainerWidth-100);
            textFieldRequestUrl.setPrefWidth((double)newSettingsContainerWidth-100);
            filePathTextField.setPrefWidth((double)newSettingsContainerWidth-100);
            filenameTextField.setPrefWidth((double)newSettingsContainerWidth-100);
        });

        PoseidonApplication.paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
                borderPane.setPrefHeight(PoseidonApplication.paneSettingsContainer.getHeight()));

        tophBOX.setAlignment(Pos.CENTER_LEFT);
        centerhBOX.setPadding(new Insets(5, 5, 5, 5));
        centerhBOX.setVisible(false);
        centerhBOX.setAlignment(Pos.TOP_LEFT);
        bottomhBOX.setAlignment(Pos.CENTER_RIGHT);
        headLabel.setAlignment(Pos.CENTER_LEFT);

        tophBOX.getChildren().addAll(choiceDialogRequestMethod, choiceDialogProtocol, textFieldRequestUrl);
        centerhBOX.getChildren().addAll(headLabel, textAreaBodyRequest);

        directoryHBOX.getChildren().addAll(filePathTextField, selectDirectoryButton);
        fileVBOX.getChildren().addAll(new Label("Save response as ..."), directoryHBOX, filenameTextField);

        bottomhBOX.getChildren().add(testButton);
        bottomVBOX.getChildren().addAll(fileVBOX, bottomhBOX);

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
        filePathTextField.setText("");
        filenameTextField.setText("");
    }

    public String[] getFactoryProperties () {
        return new String[]{
                choiceDialogProtocol.getValue(),
                textFieldRequestUrl.getText(),
                textAreaBodyRequest.getText(),
                String.valueOf(choiceDialogRequestMethod.getValue()),
                "application/json",
                filePathTextField.getText(),
                filenameTextField.getText()
        };
    }
}
