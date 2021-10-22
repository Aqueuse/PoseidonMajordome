package application;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import application.composants.ApplicationMessages;
import application.factories.FactoryBox;
import application.builders.RequestBuilder;
import application.builders.RBuilder;
import application.builders.GUIBuilder;
import application.factories.RequestSettings;
import application.factories.RSettings;
import application.factories.GUISettings;


public class PoseidonApplication extends javafx.application.Application {
    VBox vBoxFactories = new VBox();
    public static VBox vBoxFactoriesList = new VBox();
    public static Pane paneSettingsContainer = new Pane();
    public SplitPane globalVerticalSplitPane = new SplitPane();

    public static FactoryBox requestFactory = new FactoryBox(FactoryType.REQUEST);
    public static RequestSettings requestSettings = new RequestSettings(FactoryType.REQUEST);

    public static FactoryBox rFactory = new FactoryBox(FactoryType.R);
    public static RSettings rSettings = new RSettings(FactoryType.R);

    public static FactoryBox guiFactory = new FactoryBox(FactoryType.GUI);
    public static GUISettings guiSettings = new GUISettings(FactoryType.GUI);

    public static ApplicationMessages applicationMessages = new ApplicationMessages();

    public enum FactoryType {
        REQUEST,
        R,
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
        Image mascottImage = new Image(String.valueOf(PoseidonApplication.class.getResource("ressources/mascott.png")));
        Image playButtonImage = new Image(String.valueOf(PoseidonApplication.class.getResource("ressources/icons/play.png")));
        ImageView playView = new ImageView(playButtonImage);

        VBox vBoxGlobal = new VBox();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(vBoxGlobal,screenBounds.getWidth()/1.5, screenBounds.getHeight()/1.5, Color.BLUE);

        MenuBar menuBar = new MenuBar();
        Menu menuFichiers = new Menu("Fichiers");
        MenuItem menuItemQuitter = new MenuItem("Quitter");

        BorderPane factoriesToolBar = new BorderPane();
        Button runAllFactoriesButton = new Button("Run");

        SplitPane rightHorizontalSplitPane = new SplitPane();

        runAllFactoriesButton.setGraphic(playView);
        runAllFactoriesButton.setTooltip(new Tooltip("run all the factories"));

        factoriesToolBar.setLeft(runAllFactoriesButton);

        vBoxFactoriesList.setFillWidth(true);
        vBoxFactoriesList.setSpacing(4);

        rightHorizontalSplitPane.setOrientation(Orientation.VERTICAL);

        vBoxFactoriesList.getChildren().addAll(requestFactory, rFactory, guiFactory);
        paneSettingsContainer.getChildren().addAll(requestSettings, rSettings, guiSettings);
        vBoxFactories.getChildren().addAll(factoriesToolBar, vBoxFactoriesList);

        runAllFactoriesButton.setOnAction(e -> {
                    PoseidonApplication.applicationMessages.clearLogger();
                    List<Node> factories = vBoxFactoriesList.getChildren().stream().toList();

                    for (Node factory : factories) {
                        RequestBuilder requestBuilder = new RequestBuilder(requestSettings.getFactoryProperties());
                        RBuilder readBuilder = new RBuilder(rSettings.getFactoryProperties());
                        GUIBuilder writeBuilder = new GUIBuilder(guiSettings.getFactoryProperties());
                    }
                });

        menuItemQuitter.setOnAction(e -> Platform.exit());

        rightHorizontalSplitPane.getItems().addAll(paneSettingsContainer, applicationMessages);
        globalVerticalSplitPane.getItems().addAll(vBoxFactories, rightHorizontalSplitPane);

        menuFichiers.getItems().add(menuItemQuitter);
        menuBar.getMenus().add(menuFichiers);
        vBoxGlobal.getChildren().addAll(menuBar, globalVerticalSplitPane);

        dynamicallyResizeEverything(stage);

        stage.setTitle("Poseidon Majordome");
        stage.getIcons().add(mascottImage);
        stage.setScene(scene);
        stage.show();

        requestFactory.setPrefHeight(stage.getHeight()/3);
        rFactory.setPrefHeight(stage.getHeight()/3);
        guiFactory.setPrefHeight(stage.getHeight()/3);
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

    public static void main(String[] args) {
        System.setProperty( "file.encoding", "ISO-8859-1" );
        launch();
    }
}