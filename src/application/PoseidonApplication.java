package application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import application.composants.FishPondsWindow;
import application.composants.ApplicationMessages;
import application.builders.ReadBuilder;
import application.builders.RequestBuilder;
import application.builders.WriteBuilder;
import application.factories.*;

public class PoseidonApplication extends javafx.application.Application {
    VBox vBoxFactories = new VBox();
    public static VBox vBoxFactoriesList = new VBox();
    public static Pane paneSettingsContainer = new Pane();
    public SplitPane globalVerticalSplitPane = new SplitPane();

    HBox hboxToolbar = new HBox();
    MenuItem menuItemAddReadFile = new MenuItem("add File read");
    MenuItem menuItemAddWriteFile = new MenuItem("add File Write");
    MenuItem menuItemAddLogger = new MenuItem("add Log");
    MenuItem menuItemAddRequest = new MenuItem("add Request");
    MenuItem menuItemExploreFishponds = new MenuItem("explore");

    public static ApplicationMessages applicationMessages = new ApplicationMessages();

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

        VBox vBoxGlobal = new VBox();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(vBoxGlobal,screenBounds.getWidth()/1.5, screenBounds.getHeight()/1.5, Color.BLUE);

        Menu menuFichiers = new Menu("Fichiers");
        MenuItem menuItemQuitter = new MenuItem("Quitter");
        Menu menuAdd = new Menu("Add");
        Menu menuFishponds = new Menu("Fishponds");
        MenuBar menuBar = new MenuBar();

        ScrollPane scrollPaneFactories = new ScrollPane();

        BorderPane factoriesToolBar = new BorderPane();
        Button runAllFactoriesButton = new Button("Run All");
        Button upFactoryButton = new Button();
        Button downFactoryButton = new Button();
        Button indentFactoryButton = new Button();
        Button unindentFactoryButton = new Button();

        SplitPane rightHorizontalSplitPane = new SplitPane();

        globalVerticalSplitPane.setPrefHeight(initialStage);
        hboxToolbar.setAlignment(Pos.CENTER_RIGHT);

        factoriesToolBar.setPrefHeight(stage.getHeight() - paneSettingsContainer.getHeight());
        upFactoryButton.setGraphic(Ressources.upView);
        downFactoryButton.setGraphic(Ressources.downView);
        runAllFactoriesButton.setGraphic(Ressources.playView);
        indentFactoryButton.setGraphic(Ressources.indentView);
        unindentFactoryButton.setGraphic(Ressources.unindentView);

        runAllFactoriesButton.setTooltip(new Tooltip("run all the factories"));
        upFactoryButton.setTooltip(new Tooltip("move up the selected factory"));
        downFactoryButton.setTooltip(new Tooltip("move down the selected factory"));
        indentFactoryButton.setTooltip(new Tooltip("add the selected factory to the dataFlow subloop"));
        unindentFactoryButton.setTooltip(new Tooltip("remove the selected factory from the dataFlow subloop"));

        rightHorizontalSplitPane.setOrientation(Orientation.VERTICAL);
        ApplicationMessages.loggerTextFlow.setPrefWidth(paneSettingsContainer.getWidth()-55);
        scrollPaneFactories.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        vBoxFactoriesList.setFillWidth(true);
        vBoxFactoriesList.setSpacing(0);

        hboxToolbar.getChildren().addAll(unindentFactoryButton, indentFactoryButton, upFactoryButton, downFactoryButton);
        factoriesToolBar.setLeft(runAllFactoriesButton);
        factoriesToolBar.setRight(hboxToolbar);

        vBoxFactories.getChildren().add(factoriesToolBar);
        vBoxFactories.getChildren().add(scrollPaneFactories);
        scrollPaneFactories.setContent(vBoxFactoriesList);

        runAllFactoriesButton.setOnAction(e -> {
                    PoseidonApplication.applicationMessages.clearLogger();
                    if (vBoxFactoriesList.getChildren().size() == 0) {
                        applicationMessages.writeInLogger("nothing to run, click on add in the application menu to add a factory");
                    }
                    else {
                        List<Node> factories = vBoxFactoriesList.getChildren().stream().toList();

                        for (Node factory : factories) {
                            if (isDataFlowArray()) {
                                for (String dataFlowElement : dataFlow.substring(11).split(",")) {
                                    if (isMidSized(factory.getId())) {
                                        runFactory(factory, dataFlowElement.trim());
                                    }
                                }
                            }
                            else {
                                runFactory(factory, "");
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

        indentFactoryButton.setOnAction(e -> {
            if (!selectedFactoryID.equals("")) {
                Node factory = vBoxFactoriesList.lookup("#" + selectedFactoryID);
                int midSize = (int) PoseidonApplication.vBoxFactoriesList.getPrefWidth()/3;
                factory.setStyle("-fx-background-insets:0 0 0 "+midSize+"; -fx-background-color:#84B3F9; -fx-border-color:#F4F4F4 #F4F4F4 #78B7CF #78B7CF;");
            }
        });

        unindentFactoryButton.setOnAction(e -> {
            if (!selectedFactoryID.equals("")) {
                Node factory = vBoxFactoriesList.lookup("#" + selectedFactoryID);
                factory.setStyle("-fx-background-insets:0 0 0 0; -fx-background-color:#84B3F9; -fx-border-color:#F4F4F4 #F4F4F4 #78B7CF #78B7CF;");
            }
        });

        setFactoriesMenuItemEvents();

        menuItemQuitter.setOnAction(e -> Platform.exit());

        rightHorizontalSplitPane.getItems().add(paneSettingsContainer);
        rightHorizontalSplitPane.getItems().add(applicationMessages);

        globalVerticalSplitPane.getItems().add(vBoxFactories);
        globalVerticalSplitPane.getItems().add(rightHorizontalSplitPane);

        menuFichiers.getItems().add(menuItemQuitter);
        menuFishponds.getItems().add(menuItemExploreFishponds);
        menuAdd.getItems().add(menuItemAddRequest);
        menuAdd.getItems().add(menuItemAddLogger);
        menuAdd.getItems().add(menuItemAddWriteFile);
        menuAdd.getItems().add(menuItemAddReadFile);
        menuBar.getMenus().add(menuFichiers);
        menuBar.getMenus().add(menuAdd);
        menuBar.getMenus().add(menuFishponds);

        vBoxGlobal.getChildren().add(menuBar);
        vBoxGlobal.getChildren().add(globalVerticalSplitPane);

        dynamicallyResizeEverything(stage);

        stage.setTitle("Poseidon Majordome");
        stage.getIcons().add(new Image(String.valueOf(PoseidonApplication.class.getResource("mascott.png"))));
        stage.setScene(scene);
        stage.show();
    }

    private void runFactory(Node factory, String dataFlowElement) {
        String factoryID = factory.getId().split("-")[0];
        String factoryType = factory.getId().split("-")[1];

        if (factoryType.equals("REQUEST")) {
            for (RequestSettings requestSettings : requestSettingsList) {
                if (requestSettings.getId().equals(factoryID+"-"+factoryType+"-SETTINGS")) {
                    RequestBuilder requestBuilder = new RequestBuilder(requestSettings.getFactoryProperties(dataFlowElement));
                }
            }
        }

        if (factoryType.equals("READ")) {
            for (ReaderSettings readerSettings : readerSettingsList) {
                if (readerSettings.getId().equals(factoryID+"-"+factoryType+"-SETTINGS")) {
                    ReadBuilder readBuilder = new ReadBuilder(readerSettings.getFactoryProperties(dataFlowElement));
                }
            }
        }

        if (factoryType.equals("WRITE")) {
            for (WriterSettings writerSettings : writerSettingsList) {
                if (writerSettings.getId().equals(factoryID + "-" + factoryType + "-SETTINGS")) {
                    WriteBuilder writeBuilder = new WriteBuilder(writerSettings.getFactoryProperties(dataFlowElement));
                }
            }
        }

        if (factoryType.equals("LOG")) {
            if (!dataFlowElement.equals("")) {
                applicationMessages.appendToLogger(dataFlowElement);
            }
            else {
                applicationMessages.appendToLogger(dataFlow);
            }
        }
    }

    private boolean isDataFlowArray() {
        if (dataFlow != null) {
            return dataFlow.startsWith("$dataFlow =") && dataFlow.contains(",");
        }
        return false;
    }

    public static boolean isMidSized(String factoryID) {
        String midSizedProperty = PoseidonApplication.vBoxFactoriesList.lookup("#"+factoryID).getStyle().split(";")[0];
        return !midSizedProperty.equals("-fx-background-insets:0 0 0 0");
    }

    public void dynamicallyResizeEverything(Stage stage) {
        stage.widthProperty().addListener((observableValue, oldStageWidth, newStageWidth) -> {
            ApplicationMessages.loggerTextFlow.setPrefWidth(paneSettingsContainer.getWidth()-55);
            vBoxFactoriesList.setPrefWidth((double)newStageWidth-(paneSettingsContainer.getWidth()+30));
        });

        stage.heightProperty().addListener((observableValue, oldStageHeight, newStageHeight) -> {
            ApplicationMessages.loggerTextFlow.setPrefHeight((double)newStageHeight- ( paneSettingsContainer.getHeight() + 78 ));
            globalVerticalSplitPane.setPrefHeight((double)newStageHeight);
        });

        paneSettingsContainer.widthProperty().addListener((observableValue, oldSettingsContainerWidth, newSettingsContainerWidth) -> {
            ApplicationMessages.loggerTextFlow.setPrefWidth(paneSettingsContainer.getWidth()-55);
            vBoxFactoriesList.setPrefWidth(stage.getWidth()-((double)newSettingsContainerWidth+30));
        });

        paneSettingsContainer.heightProperty().addListener((observableValue, oldSettingsContainerHeight, newSettingsContainerHeight) ->
            ApplicationMessages.loggerTextFlow.setPrefHeight(stage.getHeight() - ( (double)newSettingsContainerHeight+78 )));
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

            writerSettingsList.add(writerFactorySettings);
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
        if (selectedFactoryID.equals(factoryBox.getId())) { // factory removed, so not selected of course
            selectedFactoryID = "";
        }
    }

    public static void main(String[] args) {
        System.setProperty( "file.encoding", "ISO-8859-1" );
        launch();
    }
}