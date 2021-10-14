package application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import application.composants.FishPondsWindow;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import application.composants.LoggerTab;
import application.builders.ReadBuilder;
import application.builders.RequestBuilder;
import application.builders.WriteBuilder;
import application.factories.*;

public class PoseidonApplication extends javafx.application.Application {
    VBox vBoxFactories = new VBox();
    public static VBox vBoxFactoriesList = new VBox();
    public static Pane paneSettingsContainer = new Pane();
    public SplitPane globalVerticalSplitPane = new SplitPane();

    public static TabPane paneMessages = new TabPane();
    public static Tab applicationMessagesTab = new Tab();
    public static TextArea applicationMessagesTextArea = new TextArea();
    public static LoggerTab paneLogger = new LoggerTab();

    HBox messagesHbox = new HBox();
    HBox hboxToolbar = new HBox();
    MenuItem menuItemAddReadFile = new MenuItem("add File read");
    MenuItem menuItemAddWriteFile = new MenuItem("add File Write");
    MenuItem menuItemAddLogger = new MenuItem("add Log");
    MenuItem menuItemAddRequest = new MenuItem("add Request");
    MenuItem menuItemExploreFishponds = new MenuItem("explore");

    public static List<RequestSettings> requestSettingsList = new ArrayList<>();
    public static List<ReaderSettings> readerSettingsList = new ArrayList<>();
    public static List<WriterSettings> writerSettingsList = new ArrayList<>();

    public static String selectedFactoryID = "";
    public static String dataFlow;

    public enum FactoryType {
        LOG,
        REQUEST,
        WRITE,
        READ,
        R,
        CLI,
        GUI
    }

    // VBOX global
    //      |___ menu
    //      |___ splitpane
    //            |__ vboxFactories  (LEFT)
    //                  |__ toolbar
    //                  |__ scrollpane
    //                       |__ vboxFactoriesList
    //                             |___ Pane
    //                             |___ Pane
    //                             |___ Pane
    //            |__ rightHorizontalSplitPane (RIGHT)
    //                  |___ paneSettingsContainer (UP)
    //                  |___ paneMessages (DOWN)
    //                          |___ tabPaneApplicationMessages
    //                          |___ tabPaneLogger

    @Override
    public void start(Stage stage) {
        double initialStage = stage.getHeight();

        Image upButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/up.png")));
        ImageView upView = new ImageView(upButtonImage);

        Image downButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/down.png")));
        ImageView downView = new ImageView(downButtonImage);

        Image playButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("icons/play.png")));
        ImageView playView = new ImageView(playButtonImage);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        VBox vBoxGlobal = new VBox();
        Scene scene = new Scene(vBoxGlobal,screenBounds.getWidth()/1.5, screenBounds.getHeight()/1.5, Color.BLUE);

        Menu menuFichiers = new Menu("Fichiers");
        MenuItem menuItemQuitter = new MenuItem("Quitter");
        Menu menuAdd = new Menu("Add");
        Menu menuFishponds = new Menu("Fishponds");
        MenuBar menuBar = new MenuBar();

        ScrollPane scrollPaneFactories = new ScrollPane();

        BorderPane toolBar = new BorderPane();
        Button runFactoriesButton = new Button("Run");
        Button upFactoryButton = new Button();
        Button downFactoryButton = new Button();

        SplitPane rightHorizontalSplitPane = new SplitPane();

        Pane applicationMessagesPane = new Pane();
        applicationMessagesTab.setText("Poseidon messages");
        applicationMessagesTextArea.setWrapText(true);
        applicationMessagesTextArea.setEditable(false);
        applicationMessagesTextArea.setPrefWidth(10000);
        applicationMessagesTextArea.setPrefHeight(10000);

        stage.setTitle("Poseidon Majordome");

        globalVerticalSplitPane.setPrefHeight(initialStage);
        hboxToolbar.setAlignment(Pos.CENTER_RIGHT);
        upFactoryButton.setGraphic(upView);
        downFactoryButton.setGraphic(downView);
        runFactoriesButton.setGraphic(playView);
        rightHorizontalSplitPane.setOrientation(Orientation.VERTICAL);
        messagesHbox.setPrefHeight(initialStage-paneSettingsContainer.getHeight());
        LoggerTab.loggerHBOX.setPrefHeight(initialStage-paneSettingsContainer.getHeight());

        scrollPaneFactories.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        vBoxFactoriesList.setFillWidth(true);
        vBoxFactoriesList.setSpacing(0);

        hboxToolbar.getChildren().addAll(upFactoryButton, downFactoryButton);
        toolBar.setLeft(runFactoriesButton);
        toolBar.setRight(hboxToolbar);

        vBoxFactories.getChildren().add(toolBar);
        vBoxFactories.getChildren().add(scrollPaneFactories);
        scrollPaneFactories.setContent(vBoxFactoriesList);

        runFactoriesButton.setOnAction(e -> {
                    PoseidonApplication.paneLogger.clearLogger();
                    if (vBoxFactoriesList.getChildren().size() == 0) {
                        paneMessages.getSelectionModel().select(PoseidonApplication.applicationMessagesTab);
                        applicationMessagesTextArea.setText("nothing to run, click on add in the application menu to add a factory");
                    }
                    else {
                        List<Node> factories = vBoxFactoriesList.getChildren().stream().toList();

                        for (Node factory : factories) {
                            String factoryID = factory.getId().split("-")[0];
                            String factoryType = factory.getId().split("-")[1];

                            switch (factoryType) {
                                case "REQUEST":
                                    for (RequestSettings requestSettings : requestSettingsList) {
                                        if (requestSettings.getId().equals(factoryID+"-"+factoryType+"-SETTINGS")) {
                                            RequestBuilder requestBuilder = new RequestBuilder(requestSettings.getFactoryProperties());
                                        }
                                    }
                                case "READ":
                                    for (ReaderSettings readerSettings : readerSettingsList) {
                                        if (readerSettings.getId().equals(factoryID+"-"+factoryType+"-SETTINGS")) {
                                            ReadBuilder readBuilder = new ReadBuilder(readerSettings.getFactoryProperties());
                                        }
                                    }
                                case "WRITE":
                                    for (WriterSettings writerSettings : writerSettingsList) {
                                        if (writerSettings.getId().equals(factoryID+"-"+factoryType+"-SETTINGS")) {
                                            WriteBuilder writeBuilder = new WriteBuilder(writerSettings.getFactoryProperties());
                                        }
                                    }
                                case "LOG":
                                    paneLogger.AppendToLogger(PoseidonApplication.dataFlow);
                            }
                        }
                    }
                });

        upFactoryButton.setOnAction(e -> {
            if (selectedFactoryID != null) {
                Node factory = vBoxFactoriesList.lookup("#" + selectedFactoryID);
                int lastIndex = vBoxFactoriesList.getChildren().indexOf(factory);

                if (lastIndex > 0) {
                    vBoxFactoriesList.getChildren().remove(factory);
                    vBoxFactoriesList.getChildren().add(lastIndex-1, factory);
                }
            }
        });

        downFactoryButton.setOnAction(e -> {
            if (selectedFactoryID != null) {
                Node factory = vBoxFactoriesList.lookup("#" + selectedFactoryID);
                int lastIndex = vBoxFactoriesList.getChildren().indexOf(factory);

                if (lastIndex < vBoxFactoriesList.getChildren().size()-1) {
                    vBoxFactoriesList.getChildren().remove(factory);
                    vBoxFactoriesList.getChildren().add(lastIndex+1, factory);
                }
            }
        });

        setFactoriesMenuItemEvents();

        messagesHbox.getChildren().add(applicationMessagesTextArea);
        applicationMessagesPane.getChildren().add(messagesHbox);
        applicationMessagesTab.setContent(applicationMessagesPane);
        paneMessages.getTabs().add(applicationMessagesTab);
        paneMessages.getTabs().add(paneLogger);

        rightHorizontalSplitPane.getItems().add(paneSettingsContainer);
        rightHorizontalSplitPane.getItems().add(paneMessages);

        globalVerticalSplitPane.getItems().add(vBoxFactories);
        globalVerticalSplitPane.getItems().add(rightHorizontalSplitPane);

        menuFichiers.getItems().add(menuItemQuitter);
        menuFishponds.getItems().add(menuItemExploreFishponds);
        menuAdd.getItems().add(menuItemAddReadFile);
        menuAdd.getItems().add(menuItemAddWriteFile);
        menuAdd.getItems().add(menuItemAddLogger);
        menuAdd.getItems().add(menuItemAddRequest);
        menuBar.getMenus().add(menuFichiers);
        menuBar.getMenus().add(menuAdd);
        menuBar.getMenus().add(menuFishponds);

        vBoxGlobal.getChildren().add(menuBar);
        vBoxGlobal.getChildren().add(globalVerticalSplitPane);

        dynamicallyResizeEverything(stage);

        stage.setScene(scene);
        stage.show();
    }

    public void dynamicallyResizeEverything(Stage stage) {
        stage.widthProperty().addListener((observableValue, oldStageWidth, newStageWidth) -> {
            messagesHbox.setPrefWidth(paneSettingsContainer.getWidth());
            LoggerTab.loggerHBOX.setPrefWidth(paneSettingsContainer.getWidth());
            vBoxFactoriesList.setPrefWidth((double)newStageWidth-(paneSettingsContainer.getWidth()+30));
        });

        stage.heightProperty().addListener((observableValue, oldStageHeight, newStageHeight) -> {
            messagesHbox.setPrefHeight((double)newStageHeight- ( paneSettingsContainer.getHeight() + 102) );
            LoggerTab.loggerHBOX.setPrefHeight((double)newStageHeight- ( paneSettingsContainer.getHeight() + 102 ));
            globalVerticalSplitPane.setPrefHeight((double)newStageHeight);
        });

        paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            messagesHbox.setPrefWidth((double) newSettingsContainerWidth);
            LoggerTab.loggerHBOX.setPrefWidth((double)newSettingsContainerWidth);
            vBoxFactoriesList.setPrefWidth(stage.getWidth()-((double)newSettingsContainerWidth+30));
        });

        paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) -> {
            messagesHbox.setPrefHeight(stage.getHeight()- ( (double)newSettingsContainerHeight + 102) );
            LoggerTab.loggerHBOX.setPrefHeight(stage.getHeight()- ( (double)newSettingsContainerHeight + 102 ));
        });
    }

    public void setFactoriesMenuItemEvents() {
        menuItemAddRequest.setOnAction(e -> {
            String factoryID = UUID.randomUUID().toString().substring(0, 6);
            FactoryBox requestFactory = new FactoryBox(FactoryType.REQUEST);
            requestFactory.setId(factoryID+"-REQUEST");

            RequestSettings requestFactorySettings = new RequestSettings();
            requestFactorySettings.setId(factoryID+"-REQUEST-SETTINGS");

            requestSettingsList.add(requestFactorySettings);
            vBoxFactoriesList.getChildren().add(requestFactory);
            paneSettingsContainer.getChildren().add(requestFactorySettings);

            requestFactory.showFactorySettings(factoryID+"-REQUEST-SETTINGS");
            requestFactory.colorSelectedFactory(factoryID+"-"+FactoryType.REQUEST.name());
        });

        menuItemAddLogger.setOnAction(e -> {
            String factoryID = UUID.randomUUID().toString().substring(0, 6);

            FactoryBox loggerFactory = new FactoryBox(FactoryType.LOG);
            loggerFactory.setId(factoryID+"-LOG");

            LoggerSettings loggerFactorySettings = new LoggerSettings();
            loggerFactorySettings.setId(factoryID+"-LOG-SETTINGS");

            vBoxFactoriesList.getChildren().add(loggerFactory);
            paneSettingsContainer.getChildren().add(loggerFactorySettings);

            loggerFactory.showFactorySettings(factoryID+"-LOG-SETTINGS");
            loggerFactory.colorSelectedFactory(factoryID+"-"+FactoryType.LOG.name());
        });

        menuItemAddReadFile.setOnAction(e -> {
            String factoryID = UUID.randomUUID().toString().substring(0, 6);

            FactoryBox readerFactory = new FactoryBox(FactoryType.READ);
            readerFactory.setId(factoryID+"-READ");

            ReaderSettings readerFactorySettings = new ReaderSettings();
            readerFactorySettings.setId(factoryID+"-READ-SETTINGS");

            readerSettingsList.add(readerFactorySettings);

            vBoxFactoriesList.getChildren().add(readerFactory);
            paneSettingsContainer.getChildren().add(readerFactorySettings);

            readerFactory.showFactorySettings(factoryID+"-READ-SETTINGS");
            readerFactory.colorSelectedFactory(factoryID+"-"+FactoryType.READ.name());
        });

        menuItemAddWriteFile.setOnAction(e -> {
            String factoryID = UUID.randomUUID().toString().substring(0, 6);

            FactoryBox writerFactory = new FactoryBox(FactoryType.WRITE);
            writerFactory.setId(factoryID+"-WRITE");

            WriterSettings writerFactorySettings = new WriterSettings();
            writerFactorySettings.setId(factoryID+"-WRITE-SETTINGS");

            vBoxFactoriesList.getChildren().add(writerFactory);
            paneSettingsContainer.getChildren().add(writerFactorySettings);

            writerFactory.showFactorySettings(factoryID+"-WRITE-SETTINGS");
            writerFactory.colorSelectedFactory(factoryID+"-"+FactoryType.WRITE.name());
        });

        menuItemExploreFishponds.setOnAction(e -> {
            FishPondsWindow fishPondsWindow = new FishPondsWindow();
        });
    }

    public static void removeFactory(FactoryBox factoryBox, FactoryType factoryType) {
        switch (factoryType) {
            case REQUEST -> PoseidonApplication.requestSettingsList.removeIf(requestSettings -> requestSettings.getId().equals(factoryBox.getId()));
            case READ -> PoseidonApplication.readerSettingsList.removeIf(readerSettings -> readerSettings.getId().equals(factoryBox.getId()));
            case WRITE -> PoseidonApplication.writerSettingsList.removeIf(writerSettings -> writerSettings.getId().equals(factoryBox.getId()));
        }

        PoseidonApplication.paneSettingsContainer.getChildren().remove(
                PoseidonApplication.paneSettingsContainer.lookup("#"+factoryBox.getId()+"-SETTINGS")
        );
        PoseidonApplication.vBoxFactoriesList.getChildren().remove(factoryBox);
    }

    public static void main(String[] args) {
        System.setProperty( "file.encoding", "ISO-8859-1" );
        launch();
    }
}