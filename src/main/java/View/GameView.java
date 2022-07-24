package View;
// THIS IS FOR ONLINE PLAYING ...

import Controller.GameController;
import Model.*;
import Model.Units.Unit;
import View.ClientPanels.ClientCitySelectedPanel;
import View.ClientPanels.ClientUnitSelectedPanel;
import View.Panels.*;
import View.PastViews.MapMaker;
import enums.RequestActions;
import enums.Types.BuildingType;
import enums.Types.CombatType;
import enums.Types.FogState;
import enums.Types.UnitType;
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

import java.util.ArrayList;

public class GameView extends Menu {
    private final static int RIGHT_WIDTH = 500;

    protected static Pane root;
    protected static Pane map = new Pane();
    protected static HBox topPane;
    protected static Label topPaneLabel;
    protected static VBox notificationPane;
    protected static VBox militaryPane;
    protected static VBox economyPane;

    protected static VBox unitSelectedPane;
    protected static VBox citySelectedPane;

    protected static Alert infoAlert;
    protected static Alert invalidAlert;
    protected static DialogPane dialogPane;
    protected static final Label waitiingLable = new Label("NOT your turn. wait ...");
    protected static Stage stage;

    protected static int selectedRow = -1, selectedColumn = -1;
    protected static Unit selectedUnit;
    protected static City selectedCity;

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
        map.getChildren().clear();
        if (!(Boolean) Network.getResponseObjOf(RequestActions.IS_MY_TURN.code, null)) {
            map.setVisible(false);
            waitiingLable.setVisible(true);
            waitiingLable.setLayoutX(WIDTH / 2);
            return;
        }
        waitiingLable.setVisible(false);

        Map gameMap = ((Map) Network.getResponseObjOf(RequestActions.GET_THIS_PLAYERS_MAP.code, null));

        // putting rivers in
        for (int row = 0; row < gameMap.getHeight(); row++) {
            for (int column = 0; column < gameMap.getWidth(); column++) {
                int x, y;
                if (column % 2 == 0) {
                    y = row * 130 + 65;
                    x = column * 115;
                } else {
                    y = row * 130;
                    x = column * 115;
                }
                Tile tile = gameMap.getTile(row, column);
                if(tile.getUnit() != null) {
                    System.err.println("this one has a unit");
                }
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
                    y = row * 130 + 65;
                    x = column * 115;
                } else {
                    y = row * 130;
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
                    selectTile(thisRow, thisColumn);
                    selectedColumn = thisColumn;
                    selectedRow = thisRow;

                    System.out.println(selectedRow + " " + selectedColumn + " is clicked");
                    map.requestFocus();
                });
                button.setFocusTraversable(false);
                button.setTooltip(getToolTip(gameMap.getTile(row, column)));
                map.getChildren().add(button);
            }
        }
        map.setOnKeyPressed(e -> moveMap(e));
    }

    public static void show(Stage primaryStage) {
        try {
            stage = primaryStage;
            root = new Pane();
            infoAlert = new Alert(Alert.AlertType.INFORMATION, "Hey!!!", ButtonType.OK);
            invalidAlert = new Alert(Alert.AlertType.ERROR, "invalid!", ButtonType.OK);
            selectedUnit = null;
            selectedCity = null;
            Pane pane = root;
            initTopPane();
            map = new Pane();
            map.setVisible(true);
            makeMap();
            //map.setTranslateX(GameController.getCurrentPlayer().getCameraColumn() * -130 + WIDTH / 2);
            //map.setTranslateY(GameController.getCurrentPlayer().getCameraRow() * -115 + HEIGHT / 2);
            pane.getChildren().add(map);
            // initing panes
            initElements();

            //militaryPane.setVisible(true);
            //notificationPane.setVisible(true);
            //demographicsPane.setVisible(true);
            //economyPane.setVisible(true);
            pane.getChildren().addAll(topPane, notificationPane, militaryPane, economyPane, unitSelectedPane, citySelectedPane);
            waitiingLable.setVisible(false);
            pane.getChildren().add(waitiingLable);
            Platform.runLater(() -> map.requestFocus());
            pane.getChildren().add(nextTurnButton);
            Scene scene = new Scene(pane, WIDTH, HEIGHT);
            scene.getStylesheets().add(LoginMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void initElements() {
        unitSelectedPane = new VBox();
        citySelectedPane = new VBox();
        initTopPane();
        initNotificationPane();
        initMilitaryPane();
        initDemographicsPane();
        initEconomyPane();
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
        topPaneLabel.setStyle("-fx-font-family: 'monospaced'");
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
        content.setStyle("-fx-font-family: 'monospaced'");

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
        content.setStyle("-fx-font-family: 'monospaced'");

        militaryPane.getChildren().addAll(header, content);
    }

    private static void initDemographicsPane() {
        VBox demographicsPane = new VBox();
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
        content.setStyle("-fx-font-family: 'monospaced'");

        demographicsPane.getChildren().addAll(header, content);
    }

    private static void initEconomyPane() {
        economyPane = new VBox();
        economyPane.setVisible(false);
        economyPane.setAlignment(Pos.CENTER);
        economyPane.setLayoutX(WIDTH - RIGHT_WIDTH);
        economyPane.setMinWidth(RIGHT_WIDTH);
        economyPane.setMinHeight(HEIGHT);
        economyPane.setStyle("-fx-background-color: #C0C0C0; -fx-background-size: 100, 100;");
        Label header = new Label("ECONOMY PANEL");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(18));
        header.setAlignment(Pos.CENTER);
        Label content = new Label(EconomyPanel.printPanel());
        content.setFont(Font.font(14));
        content.setTextFill(Color.WHITE);
        content.setStyle("-fx-font-family: 'monospaced'");

        economyPane.getChildren().addAll(header, content);
    }

    private static void initUnitSelectedPane() {
        unitSelectedPane = new VBox();
        unitSelectedPane.setVisible(false);
        unitSelectedPane.setAlignment(Pos.CENTER);
        unitSelectedPane.setLayoutX(WIDTH - RIGHT_WIDTH);
        unitSelectedPane.setMinWidth(RIGHT_WIDTH);
        unitSelectedPane.setMinHeight(HEIGHT);
        unitSelectedPane.setStyle("-fx-background-color: rgba(33,43,66,0.5); -fx-background-size: 100, 100;");
        Label header = new Label("UNIT PANEL");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(22));
        header.setAlignment(Pos.CENTER);

        Label info = new Label(ClientUnitSelectedPanel.showSelected());
        info.setStyle("-fx-font-family: 'monospaced'");

        Button move = new Button("Move Unit");
        move.setOnMouseClicked(e -> ClientUnitSelectedPanel.moveTo());
        move.setFocusTraversable(false);
        Button buildCity = new Button("Build City");
        buildCity.setOnMouseClicked(e -> ClientUnitSelectedPanel.foundCity());
        buildCity.setFocusTraversable(false);
        Button sleep = new Button("Sleep");
        sleep.setOnMouseClicked(e -> ClientUnitSelectedPanel.sleep());
        sleep.setFocusTraversable(false);
        Button alert = new Button("Alert");
        alert.setOnMouseClicked(e -> ClientUnitSelectedPanel.alert());
        alert.setFocusTraversable(false);
        Button fortify = new Button("Fortify");
        fortify.setOnMouseClicked(e -> ClientUnitSelectedPanel.fortify());
        fortify.setFocusTraversable(false);
        Button heal = new Button("Heal");
        heal.setOnMouseClicked(e -> ClientUnitSelectedPanel.heal());
        heal.setFocusTraversable(false);
        Button wake = new Button("Wake Up");
        wake.setOnMouseClicked(e -> ClientUnitSelectedPanel.wake());
        wake.setFocusTraversable(false);
        Button delete = new Button("Delete");
        delete.setOnMouseClicked(e -> ClientUnitSelectedPanel.delete());
        delete.setFocusTraversable(false);
        Button buildImprovement = new Button("Build Imporvement");
        buildImprovement.setOnMouseClicked(e -> ClientUnitSelectedPanel.buildImprovement());
        buildImprovement.setFocusTraversable(false);
        Button buildRoad = new Button("Build Road");
        buildRoad.setOnMouseClicked(e -> ClientUnitSelectedPanel.buildRoad());
        buildRoad.setFocusTraversable(false);
        Button removeForest = new Button("Remove Forest");
        removeForest.setOnMouseClicked(e -> ClientUnitSelectedPanel.removeForest());
        removeForest.setFocusTraversable(false);
        Button removeJungle = new Button("Remove Jungle");
        removeJungle.setOnMouseClicked(e -> ClientUnitSelectedPanel.removeJungle());
        removeJungle.setFocusTraversable(false);
        Button removeMarsh = new Button("Remove Marsh");
        removeMarsh.setOnMouseClicked(e -> ClientUnitSelectedPanel.removeMarsh());
        removeMarsh.setFocusTraversable(false);
        Button removeRoad = new Button("Remove Road");
        removeRoad.setOnMouseClicked(e -> ClientUnitSelectedPanel.removeRoute());
        removeRoad.setFocusTraversable(false);
        Button pillage = new Button("Pillage");
        pillage.setOnMouseClicked(e -> ClientUnitSelectedPanel.pillage());
        pillage.setFocusTraversable(false);
        Button repair = new Button("Repair");
        repair.setOnMouseClicked(e -> ClientUnitSelectedPanel.repair());
        repair.setFocusTraversable(false);
        Button setUp = new Button("Set Up");
        setUp.setOnMouseClicked(e -> ClientUnitSelectedPanel.setup());
        setUp.setFocusTraversable(false);
        Button garrison = new Button("Garrison");
        garrison.setOnMouseClicked(e -> ClientUnitSelectedPanel.garrison());
        garrison.setFocusTraversable(false);
        Button attack = new Button("Attack");
        attack.setOnMouseClicked(e -> ClientUnitSelectedPanel.attack());
        attack.setFocusTraversable(false);

        unitSelectedPane.getChildren().addAll(header, info, move, buildCity, sleep, alert, fortify, heal, wake, delete, buildImprovement, buildRoad, removeForest, removeJungle, removeMarsh, removeRoad, pillage, repair, setUp, garrison, attack);
    }

    private static void initCitySelectedPane() {
        citySelectedPane = new VBox();
        citySelectedPane.setVisible(false);
        citySelectedPane.setAlignment(Pos.CENTER);
        citySelectedPane.setLayoutX(WIDTH - RIGHT_WIDTH);
        citySelectedPane.setMinWidth(RIGHT_WIDTH);
        citySelectedPane.setMinHeight(HEIGHT);
        citySelectedPane.setStyle("-fx-background-color: rgba(33,43,66,0.5); -fx-background-size: 100, 100;");
        Label header = new Label("CITY PANEL");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(22));
        header.setAlignment(Pos.CENTER);

        Label info = new Label(ClientCitySelectedPanel.showBanner());
        info.setStyle("-fx-font-family: 'monospaced'");
        // setting buttons
        Button assignCitizen = new Button("Assign Citizen");
        assignCitizen.setOnMouseClicked(e -> ClientCitySelectedPanel.assignCitizen());
        assignCitizen.setFocusTraversable(false);
        Button freeCitizen = new Button("Free Citizen");
        freeCitizen.setOnMouseClicked(e -> ClientCitySelectedPanel.freeCitizen());
        freeCitizen.setFocusTraversable(false);
        Button buyTile = new Button("Buy Tile");
        buyTile.setOnMouseClicked(e -> ClientCitySelectedPanel.buyTile());
        buyTile.setFocusTraversable(false);
        Button attack = new Button("attack");
        attack.setOnMouseClicked(e -> ClientCitySelectedPanel.attack());
        attack.setFocusTraversable(false);
        Button delete = new Button("delete");
        delete.setOnMouseClicked(e -> ClientCitySelectedPanel.delete());
        delete.setFocusTraversable(false);

        // builds
        HBox hBox = new HBox(getPossibleUnitsPane(), getPossibleBuildingsPane());

        citySelectedPane.getChildren().addAll(header, info, assignCitizen, freeCitizen, buyTile, attack, delete, hBox);
    }

    private static VBox getPossibleUnitsPane() {
        VBox units = new VBox();
        units.setAlignment(Pos.CENTER);
        units.setMinWidth(RIGHT_WIDTH / 2);
        Label header = new Label("BUILD UNIT");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(20));
        header.setAlignment(Pos.CENTER);
        units.getChildren().add(header);

        for (UnitType possibleUnit : selectedCity.getPossibleUnits()) {
            Button button = new Button(possibleUnit.name);
            button.setFocusTraversable(false);
            button.setOnMouseClicked(e -> ClientCitySelectedPanel.buildUnit(possibleUnit));
            units.getChildren().add(button);
        }

        return units;
    }

    private static VBox getPossibleBuildingsPane() {
        VBox buildings = new VBox();
        buildings.setAlignment(Pos.CENTER);
        buildings.setMinWidth(RIGHT_WIDTH / 2);
        Label header = new Label("BUILD BUILDING");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font(20));
        header.setAlignment(Pos.CENTER);
        buildings.getChildren().add(header);

        for (BuildingType possibleBuilding : selectedCity.getPossibleBuildings()) {
            Button button = new Button(possibleBuilding.name);
            button.setFocusTraversable(false);
            button.setOnMouseClicked(e -> ClientCitySelectedPanel.buildBuilding(possibleBuilding));
            buildings.getChildren().add(button);
        }

        return buildings;
    }

    /////////////////////

    public static void selectTile(int row, int column) {
        if (selectedRow != row || selectedColumn != column) {
            return;
        }
        unitSelectedPane.setVisible(false);
        citySelectedPane.setVisible(false);
        Tile tile = ((Map) Network.getResponseObjOf(RequestActions.GET_THIS_PLAYERS_MAP.code, null)).getTile(row, column);
        if (tile.getCity() != null) {
            if (selectedCity == null){
                selectedCity = tile.getCity();
                selectedUnit = null;
                root.getChildren().remove(citySelectedPane);
                initCitySelectedPane();
                citySelectedPane.setVisible(true);
                root.getChildren().add(citySelectedPane);
                Network.getResponseObjOfPanelCommand("select city -l " + row + " " + column);
                System.err.println("city");
            }
            else selectedCity = null;
        }
        if (selectedCity == null && tile.getUnit() != null) {
            if (selectedUnit == null || selectedUnit.getCombatType() != CombatType.CIVILIAN){
                selectedUnit = tile.getUnit();
                selectedCity = null;
                root.getChildren().remove(unitSelectedPane);
                initUnitSelectedPane();
                unitSelectedPane.setVisible(true);
                root.getChildren().add(unitSelectedPane);
                Network.getResponseObjOfPanelCommand("select unit -l " + row + " " + column);
                System.err.println("unit");
            }
            else selectedUnit = null;
        }
        if (selectedUnit == null && selectedCity == null && tile.getTroop() != null) {
            selectedUnit = tile.getTroop();
            selectedCity = null;
            root.getChildren().remove(unitSelectedPane);
            initUnitSelectedPane();
            unitSelectedPane.setVisible(true);
            root.getChildren().add(unitSelectedPane);
            Network.getResponseObjOfPanelCommand("select troop -l " + row + " " + column);
            System.err.println("troop");
        }
        System.out.println(selectedRow + "," + selectedColumn);
        System.out.println(selectedUnit);
        System.out.println(selectedCity);
    }

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
