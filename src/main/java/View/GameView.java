package View;

import Controller.GameController;
import Controller.PlayerController;
import Model.Tile;
import View.PastViews.MapMaker;
import enums.Types.FogState;
import javafx.application.Platform;
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
    private static Pane root;
    private static Pane map;
    private static HBox topPane;
    private static Label topPaneLabel;
    private static VBox notificationPane;
    private static VBox militaryPane;
    private static Alert alert;
    private static DialogPane dialogPane;
    private static Stage stage;
    private static int selectedRow = -1, selectedColumn = -1;
    private static Button nextTurnButton;
    private static Button quitButton;
    private static ToggleButton autoSave;

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

    public static void makeMap(){
        map.getChildren().clear();

        // putting rivers in
        for(int row=0;row<GameController.getMap().getHeight();row++){
            for(int column=0;column<GameController.getMap().getWidth();column++){
                int x, y;
                if(column % 2 == 1){
                    y = row * 130;
                    x = column * 115;
                } else {
                    y = row * 130 + 65;
                    x = column * 115;
                }
                Tile tile = GameController.getCurrentPlayerMap().getTile(row, column);
                if(tile.getFogState() == FogState.UNKNOWN) continue;
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
                if(tile.getRiverInDirection(10) == 1){
                    putRiver(x - 10, y - 10, 40, 70);

                }
            }
        }

        // putting tiles in place
        for(int row=0;row<GameController.getMap().getHeight();row++){
            for(int column=0;column<GameController.getMap().getWidth();column++){
                int x, y;
                if(column % 2 == 1){
                    y = row * 130;
                    x = column * 115;
                } else {
                    y = row * 130 + 65;
                    x = column * 115;
                }
                Pane image = GameController.getCurrentPlayerMap().getTile(row, column).getTileImage();
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
                button.setTooltip(getToolTip(GameController.getCurrentPlayerMap().getTile(row, column)));
                map.getChildren().add(button);
            }
        }
        map.setOnKeyPressed(e -> moveMap(e));
    }

    public static void show(Stage primaryStage) {
        stage = primaryStage;
        root = new Pane();
        alert = initAlert();
        Pane pane = root;
        map = new Pane();
        root.getChildren().add(map);
        makeMap();
        // initing panes
       /* initTopPane();
        initNotificationPane();
        initMilitaryPane();*/
        //militaryPane.setVisible(true);
        //pane.getChildren().addAll(topPane, notificationPane, militaryPane);
//        pane.getChildren().addAll(topPane, notificationPane);
        Platform.runLater(() -> map.requestFocus());
        initElements();
        pane.getChildren().add(topPane);
        // adding buttons
        pane.getChildren().addAll(nextTurnButton, quitButton, autoSave);
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add(LoginMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private static void initElements() {
        initTopPane();
        // pass turn
        nextTurnButton = new Button("pass turn");
        nextTurnButton.setOnMouseClicked(e -> passTurn());
        nextTurnButton.setFocusTraversable(false);
        nextTurnButton.setLayoutX(0);
        nextTurnButton.setLayoutY(HEIGHT - 100);
        nextTurnButton.setMinWidth(100);
        nextTurnButton.setMinHeight(100);
        // quit
        quitButton = new Button("pause and exit");
        quitButton.setOnMouseClicked(e -> quit());
        quitButton.setFocusTraversable(false);
        quitButton.setLayoutX(WIDTH - 100);
        quitButton.setLayoutY(0);
        quitButton.setMinWidth(100);
        quitButton.setMinHeight(100);
        // auto save
        autoSave = new ToggleButton("auto save");
        autoSave.setFocusTraversable(false);
        autoSave.setLayoutX(WIDTH - 100);
        autoSave.setLayoutY(HEIGHT - 100);
        autoSave.setMinWidth(100);
        autoSave.setMinHeight(100);
    }

    private static void passTurn() {
        PlayerController.nextTurn();
        if(autoSave.isSelected()){
            GameController.saveGame();
        }
        show(stage);
    }

    private static void quit() {
        if(autoSave.isSelected()){
            GameController.saveGame();
        }
        Menu.changeMenu(MenuType.MAIN_MENU);
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
        topPaneLabel.setText(MapMaker.getColorlessTopBar());
        topPane.getChildren().addAll(topPaneLabel);
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
