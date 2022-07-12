package View;

import View.Panels.MilitaryPanel;
import View.Panels.NotificationsPanel;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import static View.Menu.MenuType.EXIT;
import static View.Menu.MenuType.LOGIN_MENU;


public class GameView extends Menu {
    private static Pane root;
    private static HBox topPane;
    private static VBox notificationPane;
    private static VBox militaryPane;
    private static VBox menuBox;
    private static Alert alert;
    private static DialogPane dialogPane;
    private static Stage stage;
    private static final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("R E G I S T E R", () -> {

            }),
            new Pair<String, Runnable>("G O   T O   L O G I N", () -> {
                Menu.changeMenu(LOGIN_MENU);
            }),
            new Pair<String, Runnable>("E x i t", () -> {
                Menu.changeMenu(EXIT);
            })
    );


    private static Parent createContent() {
        addBackground(root, "Background_B");
        addItems();
        return root;
    }

    private static void addItems() {
    }

//    private static void addLoginItems() {
//        VBox vBox = new VBox(10);
//        Label username = new Label("username:");
//        Label password = new Label("password:");
//        Label nickname = new Label("nickname:");
//        usernameField = new TextField();
//        nicknameField = new TextField();
//        passwordField = new PasswordField();
//        username.setTextFill(Color.WHITE);
//        password.setTextFill(Color.WHITE);
//        nickname.setTextFill(Color.WHITE);
//        username.setFont(Font.font(14));
//        password.setFont(Font.font(14));
//        nickname.setFont(Font.font(14));
//        vBox.getChildren().addAll(username, usernameField, nickname, nicknameField, password, passwordField, new Text());
//        menuBox.getChildren().addAll(vBox);

//    }

    public static void show(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new Pane();
        alert = initAlert();
        menuBox = new VBox(-5);
        createContent();
        Pane pane = root;

        // initing panes
        initTopPane();
        initNotificationPane();
        initMilitaryPane();
        militaryPane.setVisible(true);
        pane.getChildren().addAll(topPane, notificationPane, militaryPane);
//        pane.getChildren().addAll(topPane, notificationPane);
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add(LoginMenu.class.getClassLoader().getResource("css/MenuStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private static void initTopPane() {
        topPane = new HBox();
        topPane.setLayoutX(0);
        topPane.setLayoutY(0);
        topPane.setMinWidth(stage.getWidth());
        topPane.setStyle("-fx-background-color: #C0C0C0");

        Label test = new Label("This is topPane!!!!!!!!!!!!!!!");
        test.setFont(Font.font(14));
        test.setTextFill(Color.WHITE);
        topPane.getChildren().addAll(new TextField("hello"), test);
    }

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
}
