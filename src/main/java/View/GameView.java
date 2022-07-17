package View;

import Controller.GameController;
import Controller.PlayerController;
import Model.Tile;
import View.PastViews.MapMaker;
import enums.Types.FogState;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

    private static void putRiver(int x, int y, int w, int h){
        ImageView river = new ImageView(GameView.class.getClassLoader().getResource("images/river.png").toExternalForm());
        river.setTranslateX(x);
        river.setTranslateY(y);
        river.setFitWidth(w);
        river.setFitHeight(h);
        map.getChildren().add(river);
    }

    public static void makeMap(){
        map = new Pane();
        root.getChildren().add(map);
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
        for(int row=0;row<GameController.getMap().getHeight();row++){
            for(int column=0;column<GameController.getMap().getWidth();column++){
                int x, y;
                if(column % 2 == 0){
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
                if(column % 2 == 0){
                    y = row * 130;
                    x = column * 115;
                } else {
                    y = row * 130 + 65;
                    x = column * 115;
                }
                System.err.println(x + "," + y);
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
                map.getChildren().add(button);
            }
        }
        map.setOnKeyPressed(e -> moveMap(e));
    }

    public static void show(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new Pane();
        alert = initAlert();
        Pane pane = root;
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
        PlayerController.nextTurn();
//        makeMap();
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
/*
    private static void initNotificationPane() {
        notificationPane = new VBox();
        notificationPane.setVisible(false);
        notificationPane.setAlignment(Pos.CENTER);
        notificationPane.setLayoutX(1000);
        notificationPane.setMinWidth(400);
        notificationPane.setMinHeight(500);
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
        militaryPane.setLayoutX(1000);
        militaryPane.setMinWidth(400);
        militaryPane.setMinHeight(500);
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
 */

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
