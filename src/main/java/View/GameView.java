package View;
// THIS IS FOR ONLINE PLAYING ...

import Model.Map;
import Model.Tile;
import View.Panels.DemographicsPanel;
import View.Panels.MilitaryPanel;
import View.Panels.NotificationsPanel;
import View.PastViews.MapMaker;
import enums.RequestActions;
import enums.Types.FogState;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameView extends Menu {
    private final static int RIGHT_WIDTH = 350;

    private static Pane root;
    private static Pane map = new Pane();
    private static HBox topPane;
    private static Label topPaneLabel;
    private static VBox notificationPane;
    private static VBox militaryPane;
    private static VBox demographicsPane;
    private static Alert infoAlert;
    private static Alert invalidAlert;
    private static DialogPane dialogPane;
    private static final Label waitiingLable = new Label("NOT your turn. wait ...");
    private static Stage stage;
    private static int selectedRow = -1, selectedColumn = -1;
    private static Button nextTurnButton;

    private static void putRiver(int x, int y, int w, int h){
        ImageView river = new ImageView(GameView.class.getClassLoader().getResource("images/river.png").toExternalForm());
        river.setTranslateX(x);
        river.setTranslateY(y);
        river.setFitWidth(w);
        river.setFitHeight(h);
        map.getChildren().add(river);
    }

    private static Tooltip getToolTip(Tile tile){
        if(tile.getFogState() == FogState.UNKNOWN){
            return new Tooltip(tile.getRow() + "," + tile.getColumn());
        }
        StringBuilder text = new StringBuilder(tile.getRow() + "," + tile.getColumn() + "\n"
                + tile.getTerrainType().name + "," + tile.getTerrainFeature().name + "," + tile.getResourceType().name + "\n");
        if(tile.getRuin() != null) text.append("Ruin!\n");
        if(tile.getCity() != null) text.append(tile.getCity().getName() + "\n");
        if(tile.getUnit() != null) text.append(tile.getUnit().getUnitType().name);
        text.append(" - ");
        if(tile.getTroop() != null) text.append(tile.getTroop().getUnitType().name);
        Tooltip tooltip = new Tooltip(text.toString());
        tooltip.setWrapText(true);
        tooltip.setShowDelay(Duration.seconds(0));
        return tooltip;
    }

    // should be called after every change to the map :)
    public static void makeMap() {
        if (!(Boolean) Network.getResponseObjOf(RequestActions.IS_MY_TURN.code, null)) {
            map.setVisible(false);
            waitiingLable.setVisible(true);
            waitiingLable.setLayoutX(WIDTH / 2);
            return;
        }
        map.setVisible(true);
        waitiingLable.setVisible(false);
        map = new Pane();

        ImageView imageView1 = new ImageView(GameView.class.getClassLoader().getResource("images/Terrains/hill.png").toExternalForm());
        ImageView imageView2 = new ImageView(GameView.class.getClassLoader().getResource("images/Terrains/hill.png").toExternalForm());
        imageView2.setTranslateY(130);
        ImageView imageView3 = new ImageView(GameView.class.getClassLoader().getResource("images/Terrains/hill.png").toExternalForm());
        imageView3.setTranslateY(65);
        imageView3.setTranslateX(115);
        map.getChildren().add(imageView1);
        map.getChildren().add(imageView2);
        map.getChildren().add(imageView3);

        // putting rivers in
        Map gameMap = (Map) Network.getResponseObjOf(RequestActions.GET_GAME_MAP.code, null);

        for (int row = 0; row < gameMap.getHeight(); row++) {
            for (int column = 0; column < gameMap.getWidth(); column++) {
                int x, y;
                if (column % 2 == 0) {
                    y = row * 130;
                    x = column * 115;
                } else {
                    y = row * 130 + 65;
                    x = column * 115;
                }
                Tile tile = gameMap.getTile(row, column);
                if (tile.getFogState() == FogState.UNKNOWN) continue;
                if(tile.getRiverInDirection(0) == 1){
                    putRiver(x, y - 10, 140, 20);
                }
                if(tile.getRiverInDirection(2) == 1){
                    putRiver(x + 110, y - 5, 40, 70);
                }
                if(tile.getRiverInDirection(4) == 1){
                    putRiver(x + 110, y + 60, 40, 70);
                }
                if(tile.getRiverInDirection(6) == 1){
                    putRiver(x, y + 110, 140, 20);
                }
                if(tile.getRiverInDirection(8) == 1){
                    putRiver(x - 10, y + 60, 40, 70);
                }
                if (tile.getRiverInDirection(10) == 1) {
                    putRiver(x - 10, y - 10, 40, 70);

                }
            }
        }

        // putting tiles in place
        for (int row = 0; row < gameMap.getHeight(); row++) {
            for (int column = 0; column < gameMap.getWidth(); column++) {
                int x, y;
                if (column % 2 == 0) {
                    y = row * 130;
                    x = column * 115;
                } else {
                    y = row * 130 + 65;
                    x = column * 115;
                }
                Pane image = gameMap.getTile(row, column).getTileImage();
                image.setTranslateX(x);
                image.setTranslateY(y);
                map.getChildren().add(image);

                Button button = new Button();
                button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;-fx-text-fill: transparent;");
                button.setPrefWidth(100);
                button.setPrefHeight(100);
                button.setTranslateX(x + 20);
                button.setTranslateY(y + 10);
                int thisRow = row, thisColumn = column;
                button.setOnMouseClicked(e -> {
                    selectedRow = thisRow;
                    selectedColumn = thisColumn;
                    System.out.println(selectedRow + " " + selectedColumn);
                    map.requestFocus();
                });
                button.setFocusTraversable(false);
                button.setTooltip(getToolTip(gameMap.getTile(row, column)));
                map.getChildren().add(button);
                System.err.println(row + "," + column);
            }
        }
        map.setOnKeyPressed(e -> moveMap(e));
    }

    public static void show(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new Pane();
        infoAlert = new Alert(Alert.AlertType.INFORMATION, "Hey!!!", ButtonType.OK);
        invalidAlert = new Alert(Alert.AlertType.ERROR, "invalid!", ButtonType.OK);
        Pane pane = root;
        initTopPane();
        makeMap();
        // initing panes

//        initNotificationPane();
//        initMilitaryPane();
//        initDemographicsPane();
        //militaryPane.setVisible(true);
        //notificationPane.setVisible(true);
//        demographicsPane.setVisible(true);
//        pane.getChildren().addAll(topPane, notificationPane, militaryPane, demographicsPane);
        pane.getChildren().add(map);
        waitiingLable.setVisible(false);
        pane.getChildren().add(waitiingLable);
        pane.getChildren().addAll(topPane);
        Platform.runLater(() -> map.requestFocus());
        initElements();
        pane.getChildren().add(topPane);
        pane.getChildren().add(nextTurnButton);
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add(LoginMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private static void initElements() {
        initTopPane();
        nextTurnButton = new Button("pass turn");
        nextTurnButton.setOnMouseClicked((e -> passTurn()));
        nextTurnButton.setLayoutX(100);
        nextTurnButton.setFocusTraversable(false);
        nextTurnButton.setLayoutY(100);
    }

    private static void passTurn() {
        // TODO: 7/15/2022 in web it must bo into a waiting state?
        System.out.println("passing turn!!");
        Network.sendRequest(RequestActions.PASS_TURN.code, null);
        System.out.println("passing turn!!");

        makeMap();
        System.out.println("passing turn!!");
        updateElements();
    }


    private static void updateElements() {
        topPaneLabel.setText(MapMaker.getColorlessTopBar());
    }

    private static void initTopPane() {
        topPane = new HBox();
        topPane.setLayoutX(0);
        topPane.setLayoutY(0);
        topPane.setMinWidth(stage.getWidth());
        topPane.setStyle("-fx-background-color: #C0C0C0");

        topPaneLabel = new Label(MapMaker.getColorlessTopBar());
        topPaneLabel.setFont(Font.font(14));
        topPaneLabel.setTextFill(Color.WHITE);
        topPane.getChildren().addAll(topPaneLabel);
    }


    private static void initNotificationPane() {
        notificationPane = new VBox();
        notificationPane.setVisible(false);
        notificationPane.setAlignment(Pos.CENTER);
        notificationPane.setLayoutX(WIDTH - RIGHT_WIDTH);
        notificationPane.setMinWidth(WIDTH);
        notificationPane.setMinHeight(HEIGHT);
        notificationPane.setStyle("-fx-background-color: #C0C0C0; -fx-background-size: 100, 100;");
        Label header = new Label("NOTIFICATION PANEL");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(18));
        header.setAlignment(Pos.CENTER);
        Label content = new Label(NotificationsPanel.showPanel());
        content.setFont(Font.font(14));
        content.setTextFill(Color.WHITE);
        notificationPane.getChildren().addAll(header, content);
    }

    private static void initMilitaryPane() {
        militaryPane = new VBox();
        militaryPane.setVisible(false);
        militaryPane.setAlignment(Pos.CENTER);
        militaryPane.setLayoutX(WIDTH - RIGHT_WIDTH);
        militaryPane.setMinWidth(RIGHT_WIDTH);
        militaryPane.setMinHeight(HEIGHT);
        militaryPane.setStyle("-fx-background-color: #C0C0C0; -fx-background-size: 100, 100;");
        Label header = new Label("MILITARY PANEL");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(18));
        header.setAlignment(Pos.CENTER);
        Label content = new Label(MilitaryPanel.printPanel());
        content.setFont(Font.font(14));
        content.setTextFill(Color.WHITE);
        militaryPane.getChildren().addAll(header, content);
    }

    private static void initDemographicsPane() {
        demographicsPane = new VBox();
        demographicsPane.setVisible(false);
        demographicsPane.setAlignment(Pos.CENTER);
        demographicsPane.setLayoutX(WIDTH - RIGHT_WIDTH);
        demographicsPane.setMinWidth(RIGHT_WIDTH);
        demographicsPane.setMinHeight(HEIGHT);
        demographicsPane.setStyle("-fx-background-color: #C0C0C0; -fx-background-size: 100, 100;");
        Label header = new Label("DEMOGRAPHICS PANEL");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(18));
        header.setAlignment(Pos.CENTER);
        Label content = new Label(DemographicsPanel.printPanel());
        content.setFont(Font.font(14));
        content.setTextFill(Color.WHITE);
        demographicsPane.getChildren().addAll(header, content);
    }

    /////////////////////

    public static void moveMap(KeyEvent keyEvent) {
        String keyName = keyEvent.getCode().getName();
        System.err.println("an arrow key was pressed!");
        switch (keyName) {
            case "Down":
                map.setTranslateY(map.getTranslateY() - 15);
                break;
            case "Up":
                map.setTranslateY(map.getTranslateY() + 15);
                break;
            case "Right":
                map.setTranslateX(map.getTranslateX() - 15);
                break;
            case "Left":
                map.setTranslateX(map.getTranslateX() + 15);
                break;
        }
    }
}
